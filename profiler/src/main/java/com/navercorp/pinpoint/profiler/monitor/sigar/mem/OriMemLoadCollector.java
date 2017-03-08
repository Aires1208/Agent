package com.navercorp.pinpoint.profiler.monitor.sigar.mem;

import com.navercorp.pinpoint.thrift.dto.TMemLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class OriMemLoadCollector implements MemLoadCollector {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TMemLoad collect() {

        TMemLoad memLoad = new TMemLoad();
        Process pro;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "cat /proc/meminfo";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            int count = 0;
            long totalMem = 0, freeMem = 0, usedMem;
            while ((line = in.readLine()) != null) {
                String[] memInfo = line.split("\\s+");
                if (memInfo[0].startsWith("MemTotal")) {
                    totalMem = Long.parseLong(memInfo[1]);
                }
                if (memInfo[0].startsWith("MemFree")) {
                    freeMem = Long.parseLong(memInfo[1]);
                }
                if (++count == 2) {
                    break;
                }
            }

            usedMem = totalMem - freeMem;

            memLoad.setTotal(totalMem*1000);
            memLoad.setFree(freeMem*1000);
            memLoad.setUsed(usedMem*1000);

            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return memLoad;
    }
}
