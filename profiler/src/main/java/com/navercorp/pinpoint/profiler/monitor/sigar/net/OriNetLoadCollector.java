package com.navercorp.pinpoint.profiler.monitor.sigar.net;

import com.navercorp.pinpoint.thrift.dto.TNetLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class OriNetLoadCollector implements NetLoadCollector {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static float TotalBandwidth = 1000;


    @Override
    public TNetLoad collect() {
        TNetLoad netLoad = new TNetLoad();
        float inUsage;
        float outUsage;
        Process pro1, pro2;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "cat /proc/net/dev";
            long startTime = System.currentTimeMillis();
            pro1 = r.exec(command);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
            String line;
            long inSize1 = 0, outSize1 = 0;
            while ((line = in1.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("eth0")) {
                    String[] temp = line.split("\\s+");
                    inSize1 = Long.parseLong(temp[1]);
                    outSize1 = Long.parseLong(temp[9]);
                    break;
                } else if (line.startsWith("ens3")) {
                    String[] temp = line.split("\\s+");
                    inSize1 = Long.parseLong(temp[1]);
                    outSize1 = Long.parseLong(temp[9]);
                    break;
                }
            }
            in1.close();
            pro1.destroy();

            Thread.sleep(1000);

            long endTime = System.currentTimeMillis();
            pro2 = r.exec(command);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            long inSize2 = 0, outSize2 = 0;
            while ((line = in2.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("eth0")) {
                    String[] temp = line.split("\\s+");
                    inSize2 = Long.parseLong(temp[1]);
                    outSize2 = Long.parseLong(temp[9]);
                    break;
                } else if (line.startsWith("ens3")) {
                    String[] temp = line.split("\\s+");
                    inSize2 = Long.parseLong(temp[1]);
                    outSize2 = Long.parseLong(temp[9]);
                    break;
                }
            }

            if (inSize1 != 0 && outSize1 != 0 && inSize2 != 0 && outSize2 != 0) {
                logger.info("inSize1 = "+ inSize1 +" outSize1 = "+ outSize1+" inSize2 = "+inSize2 + " outSize2 = "+outSize2);
                float interval = (float) (endTime - startTime) / 1000;
                float inRate = (float) (inSize2 - inSize1) * 8 / (1000000 * interval);
                inUsage = inRate / TotalBandwidth;
                float outRate = (float) (outSize2 - outSize1) * 8 / (1000000 * interval);
                outUsage = outRate / TotalBandwidth;
                netLoad.setInSpeed(inUsage);
                netLoad.setOutSpeed(outUsage);
                netLoad.setSpeed(1000000000);
                logger.info("inSpeed = "+inUsage +" outSpeed = "+outUsage);
            } else {
                netLoad.setInSpeed(0);
                netLoad.setOutSpeed(0);
                netLoad.setSpeed(1000000000);
            }
            in2.close();
            pro2.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }

        return netLoad;
    }
}
