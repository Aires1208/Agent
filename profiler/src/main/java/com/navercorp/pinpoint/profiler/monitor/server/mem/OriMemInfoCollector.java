package com.navercorp.pinpoint.profiler.monitor.server.mem;

import com.navercorp.pinpoint.thrift.dto.TMemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class OriMemInfoCollector implements MemInfoCollector {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TMemInfo collect() {

        TMemInfo tMemInfo = new TMemInfo();

        Process pro;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "cat /proc/meminfo";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            long memTotal = 0, memFree = 0, memUsed;
            long swapTotal = 0, swapFree = 0, swapUsed;
            long vmTotal = 0, vmFree, vmUsed = 0;
            while ((line = in.readLine()) != null) {
                String[] memInfo = line.split("\\s+");
                if (memInfo[0].startsWith("MemTotal")) {
                    memTotal = Long.parseLong(memInfo[1]);
                }
                if (memInfo[0].startsWith("MemFree")) {
                    memFree = Long.parseLong(memInfo[1]);
                }
                memUsed = memTotal - memFree;

                tMemInfo.setMemTotal(memTotal);
                tMemInfo.setMemFree(memFree);
                tMemInfo.setMemUsed(memUsed);

                if (memInfo[0].startsWith("SwapTotal")) {
                    swapTotal = Long.parseLong(memInfo[1]);
                }
                if (memInfo[0].startsWith("SwapFree")) {
                    swapFree = Long.parseLong(memInfo[1]);
                }
                swapUsed = swapTotal - swapFree;

                tMemInfo.setSwapTotal(swapTotal);
                tMemInfo.setSwapFree(swapFree);
                tMemInfo.setSwapUsed(swapUsed);

                if (memInfo[0].startsWith("VmallocTotal")) {
                    vmTotal = Long.parseLong(memInfo[1]);
                }
                if (memInfo[0].startsWith("VmallocUsed")) {
                    vmUsed = Long.parseLong(memInfo[1]);
                }
                vmFree = vmTotal - vmUsed;

                tMemInfo.setVmTotal(vmTotal);
                tMemInfo.setVmFree(vmFree);
                tMemInfo.setVmUsed(vmUsed);
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return tMemInfo;
    }
}
