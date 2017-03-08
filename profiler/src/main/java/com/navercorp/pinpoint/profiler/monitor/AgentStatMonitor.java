/*
 * Copyright 2014 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.profiler.monitor;

import com.navercorp.pinpoint.common.util.PinpointThreadFactory;
import com.navercorp.pinpoint.profiler.monitor.codahale.AgentStatCollectorFactory;
import com.navercorp.pinpoint.profiler.monitor.codahale.activetrace.ActiveTraceMetricCollector;
import com.navercorp.pinpoint.profiler.monitor.codahale.cpu.CpuLoadCollector;
import com.navercorp.pinpoint.profiler.monitor.codahale.gc.GarbageCollector;
import com.navercorp.pinpoint.profiler.monitor.codahale.tps.TransactionMetricCollector;
import com.navercorp.pinpoint.profiler.monitor.server.cpu.CpusCollector;
import com.navercorp.pinpoint.profiler.monitor.server.device.DevicesCollector;
import com.navercorp.pinpoint.profiler.monitor.server.filesystem.FileSystemsCollector;
import com.navercorp.pinpoint.profiler.monitor.server.mem.MemInfoCollector;
import com.navercorp.pinpoint.profiler.monitor.server.net.NetsCollector;
import com.navercorp.pinpoint.profiler.monitor.server.process.ProcessesCollector;
import com.navercorp.pinpoint.profiler.monitor.sigar.io.IOLoadCollector;
import com.navercorp.pinpoint.profiler.monitor.sigar.mem.MemLoadCollector;
import com.navercorp.pinpoint.profiler.monitor.sigar.net.NetLoadCollector;
import com.navercorp.pinpoint.profiler.sender.DataSender;
import com.navercorp.pinpoint.thrift.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * AgentStat monitor
 *
 * @author harebox
 * @author hyungil.jeong
 */
public class AgentStatMonitor {

    private static final long DEFAULT_COLLECTION_INTERVAL_MS = 1000 * 5;
    private static final int DEFAULT_NUM_COLLECTIONS_PER_SEND = 6;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final boolean isTrace = logger.isTraceEnabled();
    private final long collectionIntervalMs;
    private final int numCollectionsPerBatch;

    private final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1, new PinpointThreadFactory("Pinpoint-stat-monitor", true));

    private final DataSender dataSender;
    private final String agentId;
    private final AgentStatCollectorFactory agentStatCollectorFactory;
    private final long agentStartTime;

    public AgentStatMonitor(DataSender dataSender, String agentId, long startTime, AgentStatCollectorFactory agentStatCollectorFactory) {
        this(dataSender, agentId, startTime, agentStatCollectorFactory, DEFAULT_COLLECTION_INTERVAL_MS, DEFAULT_NUM_COLLECTIONS_PER_SEND);
    }

    public AgentStatMonitor(DataSender dataSender, String agentId, long startTime, AgentStatCollectorFactory agentStatCollectorFactory, long collectionInterval, int numCollectionsPerBatch) {
        if (dataSender == null) {
            throw new NullPointerException("dataSender must not be null");
        }
        if (agentId == null) {
            throw new NullPointerException("agentId must not be null");
        }
        if (agentStatCollectorFactory == null) {
            throw new NullPointerException("agentStatCollectorFactory must not be null");
        }
        this.dataSender = dataSender;
        this.agentId = agentId;
        this.agentStartTime = startTime;
        this.agentStatCollectorFactory = agentStatCollectorFactory;
        this.collectionIntervalMs = collectionInterval;
        this.numCollectionsPerBatch = numCollectionsPerBatch;
    }

    public void start() {
        CollectJob job = new CollectJob(this.numCollectionsPerBatch);
        executor.scheduleAtFixedRate(job, this.collectionIntervalMs, this.collectionIntervalMs, TimeUnit.MILLISECONDS);
        logger.info("AgentStat monitor started");
    }

    public void stop() {
        executor.shutdown();
        try {
            executor.awaitTermination(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("AgentStat monitor stopped");
    }

    // NotThreadSafe
    private class CollectJob implements Runnable {

        private final GarbageCollector garbageCollector;
        private final CpuLoadCollector cpuLoadCollector;
        private final IOLoadCollector ioLoadCollector;
        private final NetLoadCollector netLoadCollector;
        private final MemLoadCollector memLoadCollector;

        private final MemInfoCollector memInfoCollector;
        private final DevicesCollector devicesCollector;
        private final FileSystemsCollector fileSystemsCollector;
        private final NetsCollector netsCollector;
        private final CpusCollector cpusCollector;
        private final ProcessesCollector processesCollector;


        private final TransactionMetricCollector transactionMetricCollector;

        private final ActiveTraceMetricCollector activeTraceMetricCollector;
        // Not thread safe. For use with single thread ONLY
        private final int numStatsPerBatch;
        private int collectCount = 0;
        private long prevCollectionTimestamp = System.currentTimeMillis();
        private List<TAgentStat> agentStats;

        private CollectJob(int numStatsPerBatch) {
            this.garbageCollector = agentStatCollectorFactory.getGarbageCollector();
            this.cpuLoadCollector = agentStatCollectorFactory.getCpuLoadCollector();
            this.ioLoadCollector = agentStatCollectorFactory.getIOLoadCollector();
            this.netLoadCollector = agentStatCollectorFactory.getNetLoadCollector();
            this.memLoadCollector = agentStatCollectorFactory.getMemLoadCollector();

            this.memInfoCollector = agentStatCollectorFactory.getMemInfoCollector();
            this.devicesCollector = agentStatCollectorFactory.getDevicesCollector();
            this.fileSystemsCollector = agentStatCollectorFactory.getFileSystemsCollector();
            this.netsCollector = agentStatCollectorFactory.getNetsCollector();
            this.cpusCollector = agentStatCollectorFactory.getCpusCollector();
            this.processesCollector = agentStatCollectorFactory.getProcessesCollector();

            this.transactionMetricCollector = agentStatCollectorFactory.getTransactionMetricCollector();
            this.activeTraceMetricCollector = agentStatCollectorFactory.getActiveTraceMetricCollector();
            this.numStatsPerBatch = numStatsPerBatch;
            this.agentStats = new ArrayList<TAgentStat>(this.numStatsPerBatch);
        }

        @Override
        public void run() {
            final long currentCollectionTimestamp = System.currentTimeMillis();
            final long collectInterval = currentCollectionTimestamp - this.prevCollectionTimestamp;
            try {
                final TAgentStat agentStat = collectAgentStat();
                agentStat.setTimestamp(currentCollectionTimestamp);
                agentStat.setCollectInterval(collectInterval);
                this.agentStats.add(agentStat);
                if (++this.collectCount >= this.numStatsPerBatch) {
                    sendAgentStats();
                    this.collectCount = 0;
                }
            } catch (Exception ex) {
                logger.warn("AgentStat collect failed. Caused:{}", ex.getMessage(), ex);
            } finally {
                this.prevCollectionTimestamp = currentCollectionTimestamp;
            }
        }

        private TAgentStat collectAgentStat() {
            final TAgentStat agentStat = new TAgentStat();
            final TJvmGc gc = garbageCollector.collect();
            agentStat.setGc(gc);
            final TCpuLoad cpuLoad = cpuLoadCollector.collect();
            agentStat.setCpuLoad(cpuLoad);
            final TIOLoad ioLoad = ioLoadCollector.collect();
            agentStat.setIOLoad(ioLoad);
            final TNetLoad netLoad = netLoadCollector.collect();
            agentStat.setNetLoad(netLoad);
            final TMemLoad memLoad = memLoadCollector.collect();
            agentStat.setMemLoad(memLoad);

            final TMemInfo memInfo = memInfoCollector.collect();
            agentStat.setMemInfo(memInfo);
            final TDevices tDevices = devicesCollector.collect();
            agentStat.setDevices(tDevices);
            final TFileSystems tFileSystems = fileSystemsCollector.collect();
            agentStat.setFileSystems(tFileSystems);
            final TNets tNets = netsCollector.collect();
            agentStat.setNets(tNets);
            final TCpus tCpus = cpusCollector.collect();
            agentStat.setCpus(tCpus);
            final TProcesses tProcesses = processesCollector.collect();
            agentStat.setProcesses(tProcesses);

            final TTransaction transaction = transactionMetricCollector.collect();
            agentStat.setTransaction(transaction);
            final TActiveTrace activeTrace = activeTraceMetricCollector.collect();
            agentStat.setActiveTrace(activeTrace);
            return agentStat;
        }

        private void sendAgentStats() {
            // prepare TAgentStat object.
            // TODO multi thread issue.
            // If we reuse TAgentStat, there could be concurrency issue because data sender runs in a different thread.
            final TAgentStatBatch agentStatBatch = new TAgentStatBatch();
            agentStatBatch.setAgentId(agentId);
            agentStatBatch.setStartTimestamp(agentStartTime);
            agentStatBatch.setAgentStats(this.agentStats);
            // If we reuse agentStats list, there could be concurrency issue because data sender runs in a different
            // thread.
            // So create new list.
            this.agentStats = new ArrayList<TAgentStat>(this.numStatsPerBatch);
            if (isTrace) {
                logger.trace("collect agentStat:{}", agentStatBatch);
            }
            dataSender.send(agentStatBatch);
        }
    }

}