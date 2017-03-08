package com.navercorp.pinpoint.profiler.monitor.server.process;

import com.navercorp.pinpoint.profiler.monitor.codahale.AgentStatCollector;
import com.navercorp.pinpoint.thrift.dto.TProcesses;

public interface ProcessesCollector extends AgentStatCollector<TProcesses> {

}
