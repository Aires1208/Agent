package com.navercorp.pinpoint.profiler.monitor.sigar.io;

import com.navercorp.pinpoint.thrift.dto.TIOLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class OriIOLoadCollector implements IOLoadCollector {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TIOLoad collect() {
        TIOLoad ioLoad = new TIOLoad();

        Process pro = null;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "df";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            int count = 0;
            long total = 0, free = 0, used = 0;
            while ((line = in.readLine()) != null) {
                if (count >= 1){
                    String[] temp = line.split("\\s+");
                    if (temp.length>=3){
                        total += Long.parseLong(temp[1]);
                        used += Long.parseLong(temp[2]);
                        free += Long.parseLong(temp[3]);
                    }
                }
                count = count + 1;
            }

            double usage = 0;
            if(total!=0){
                usage = ((double) used) / ((double) total);
            }
            logger.info("used = "+used+" total = "+total+" usage = "+usage);
            ioLoad.setTotal(total);
            ioLoad.setFree(free);
            ioLoad.setUsed(used);
            ioLoad.setUsage(usage);

            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return ioLoad;
    }

}
