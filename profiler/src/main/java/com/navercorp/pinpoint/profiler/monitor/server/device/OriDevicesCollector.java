package com.navercorp.pinpoint.profiler.monitor.server.device;

import com.navercorp.pinpoint.thrift.dto.TDeviceInfo;
import com.navercorp.pinpoint.thrift.dto.TDevices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class OriDevicesCollector implements DevicesCollector {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TDevices collect() {
        Process pro;
        Runtime r = Runtime.getRuntime();
        TDevices tDevices = new TDevices();
        try {
            String command = "iostat -d -k";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            List<TDeviceInfo> deviceList = new ArrayList<TDeviceInfo>();
            while ((line = in.readLine()) != null) {
                String[] diskInfo = line.split("\\s+");

                if (diskInfo.length == 6 && !diskInfo[0].startsWith("Device")) {
                    TDeviceInfo tDeviceInfo = new TDeviceInfo();
                    tDeviceInfo.setDeviceName(diskInfo[0]);
                    tDeviceInfo.setTps(Double.parseDouble(diskInfo[1]));
                    tDeviceInfo.setReadPerSecond(Double.parseDouble(diskInfo[2]));
                    tDeviceInfo.setWritePerSecond(Double.parseDouble(diskInfo[3]));
                    tDeviceInfo.setRead(Long.parseLong(diskInfo[4]));
                    tDeviceInfo.setWrite(Long.parseLong(diskInfo[5]));
                    deviceList.add(tDeviceInfo);
                }

            }
            tDevices.setTDevices(deviceList);
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return tDevices;
    }
}
