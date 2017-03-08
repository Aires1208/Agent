package com.navercorp.pinpoint.profiler.monitor.server.cpu;

import com.navercorp.pinpoint.profiler.monitor.server.net.NetsCollector;
import com.navercorp.pinpoint.thrift.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OriCpusCollector implements CpusCollector {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TCpus collect() {
        TCpus tCpus = new TCpus();
        tCpus.setTCpuInfoStatics(getCpuInfoStatic());
        tCpus.setTCpuInfoDynamics(getCpuInfoDynamic());
        return tCpus;
    }


    private List<TCpuInfoStatic> getCpuInfoStatic() {
        Map<String,String> processorMap = getCpuInfoMap("processor","processor");
        Map<String,String> vendorMap = getCpuInfoMap("vendor_id","vendor_id");
        Map<String,String> cpuFamilyMap = getCpuInfoMap("family","cpu family");
        Map<String,String> modelMap = getCpuInfoMap("model","model");
        Map<String,String> modelNameMap = getCpuInfoMap("model","model name");
        Map<String,String> cpuMHzMap = getCpuInfoMap("MHz","cpu MHz");
        Map<String,String> cacheSizeMap = getCpuInfoMap("cache","cache size");

        List<TCpuInfoStatic> tCpuInfoStatics = new ArrayList<TCpuInfoStatic>();
        for (Map.Entry<String, String> entry : processorMap.entrySet()) {
            String key = entry.getKey();
            TCpuInfoStatic tCpuInfoStatic = new TCpuInfoStatic();
            tCpuInfoStatic.setProcessor("cpu"+entry.getValue());
            tCpuInfoStatic.setVendor(vendorMap.get(key));
            tCpuInfoStatic.setCpuFamily(cpuFamilyMap.get(key));
            tCpuInfoStatic.setModel(modelMap.get(key));
            tCpuInfoStatic.setModelName(modelNameMap.get(key));
            tCpuInfoStatic.setCpuMHz(cpuMHzMap.get(key));
            tCpuInfoStatic.setCacheSize(cacheSizeMap.get(key));
            tCpuInfoStatics.add(tCpuInfoStatic);
        }

        return tCpuInfoStatics;
    }


    private List<TCpuInfoDynamic> getCpuInfoDynamic() {

        Process pro;
        Runtime r = Runtime.getRuntime();
        List<TCpuInfoDynamic> tCpuInfoDynamics = new ArrayList<TCpuInfoDynamic>();
        try {

            String command = "cat /proc/stat";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                line=line.trim();
                String[] cpuInfoDynamic = line.split("\\s+");
                if(cpuInfoDynamic[0].startsWith("cpu")){
                    TCpuInfoDynamic tCpuInfoDynamic = new TCpuInfoDynamic();
                    tCpuInfoDynamic.setProcessor(cpuInfoDynamic[0]);
                    tCpuInfoDynamic.setUser(Long.parseLong(cpuInfoDynamic[1]));
                    tCpuInfoDynamic.setNice(Long.parseLong(cpuInfoDynamic[2]));
                    tCpuInfoDynamic.setSystem(Long.parseLong(cpuInfoDynamic[3]));
                    tCpuInfoDynamic.setIdle(Long.parseLong(cpuInfoDynamic[4]));
                    tCpuInfoDynamic.setIowait(Long.parseLong(cpuInfoDynamic[5]));
                    tCpuInfoDynamic.setIrq(Long.parseLong(cpuInfoDynamic[6]));
                    tCpuInfoDynamic.setSoftirq(Long.parseLong(cpuInfoDynamic[7]));
                    tCpuInfoDynamics.add(tCpuInfoDynamic);
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return tCpuInfoDynamics;
    }


    private Map<String, String> getCpuInfoMap(String grepName, String MatchName) {
        Process pro;
        Runtime r = Runtime.getRuntime();

        Map<String, String> cpuInfoMap = new HashMap<String, String>();
        try {
            String[] command = {"/bin/sh", "-c", "cat /proc/cpuinfo | grep " + grepName };
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            int i =0;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                String[] cpuInfo = line.split(":");
                if (MatchName.equals(cpuInfo[0].trim())) {
                    cpuInfoMap.put("p" + i, cpuInfo[1].trim());
                    i++;
                }
            }

            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return cpuInfoMap;

    }
}
