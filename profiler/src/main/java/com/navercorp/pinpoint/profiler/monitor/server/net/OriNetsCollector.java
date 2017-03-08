package com.navercorp.pinpoint.profiler.monitor.server.net;

import com.navercorp.pinpoint.thrift.dto.TNetInfoDynamic;
import com.navercorp.pinpoint.thrift.dto.TNetInfoStatic;
import com.navercorp.pinpoint.thrift.dto.TNets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class OriNetsCollector implements NetsCollector {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TNets collect() {
        List<TNetInfoStatic> tNetInfoStatics = getNetInfoStatic();
        for (TNetInfoStatic tNetInfoStatic : tNetInfoStatics) {
            tNetInfoStatic.setMacAddress(getLinuxCommandValue(tNetInfoStatic.getName(), "address"));
            tNetInfoStatic.setMtu(Long.parseLong(getLinuxCommandValue(tNetInfoStatic.getName(), "mtu")));
        }

        List<TNetInfoDynamic> tNetInfoDynamics = getNetInfoDynamic();

        TNets tNets = new TNets();
        tNets.setTNetInfoStatics(tNetInfoStatics);
        tNets.setTNetInfoDynamics(tNetInfoDynamics);
        return tNets;
    }


    public List<TNetInfoStatic> getNetInfoStatic() {

        Process pro;
        Runtime r = Runtime.getRuntime();
        List<TNetInfoStatic> tNetInfoStatics = new ArrayList<TNetInfoStatic>();
        try {
            String[] command = {"/bin/sh", "-c", "ip addr show | grep inet"};
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                String[] netInfo = line.split("\\s+");
                if ("inet".equals(netInfo[1])) {
                    TNetInfoStatic tNetInfoStatic = new TNetInfoStatic();
                    tNetInfoStatic.setName(netInfo[netInfo.length - 1]);
                    tNetInfoStatic.setV4Address(netInfo[2].substring(0, netInfo[2].indexOf('/')));
                    tNetInfoStatics.add(tNetInfoStatic);
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return tNetInfoStatics;
    }


    public String getLinuxCommandValue(String name, String type) {

        Process pro;
        Runtime r = Runtime.getRuntime();
        String returnValue = "";
        try {
            String command = "cat /sys/class/net/" + name + "/" + type;
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (!"".equals(line)) {
                    returnValue = line;
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return returnValue;
    }


    public List<TNetInfoDynamic> getNetInfoDynamic() {

        Process pro;
        Runtime r = Runtime.getRuntime();
        List<TNetInfoDynamic> tNetInfoDynamics = new ArrayList<TNetInfoDynamic>();
        try {
            String command = "cat /proc/net/dev";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();

                String[] netInfo = line.split("\\s+");
                if (netInfo[0].contains(":")) {
                    TNetInfoDynamic tNetInfoDynamic = new TNetInfoDynamic();
                    tNetInfoDynamic.setName(netInfo[0].substring(0, netInfo[0].lastIndexOf(":")));
                    tNetInfoDynamic.setReceiveBytes(Long.parseLong(netInfo[1]));
                    tNetInfoDynamic.setTransmitBytes(Long.parseLong(netInfo[9]));
                    tNetInfoDynamic.setReceiveErrors(Long.parseLong(netInfo[3]));
                    tNetInfoDynamic.setTransmitErrors(Long.parseLong(netInfo[11]));
                    tNetInfoDynamic.setColls(Long.parseLong(netInfo[14]));
                    tNetInfoDynamics.add(tNetInfoDynamic);
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return tNetInfoDynamics;
    }
}
