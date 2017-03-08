package com.navercorp.pinpoint.profiler.monitor.server.process;

import com.navercorp.pinpoint.thrift.dto.TProcessInfo;
import com.navercorp.pinpoint.thrift.dto.TProcesses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OriProcessesCollector implements ProcessesCollector {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TProcesses collect() {
        List<TProcessInfo> cpuUsageList;
        List<TProcessInfo> cpuTimeList;
        List<TProcessInfo> virtList;
        if(isDocker()){
            cpuUsageList = getProcessesListForDockerTop("top -b -n 1|sed -n '5,$p' | sed s/\"%\"/\"\"/g | sort  -k 8 -nr|head -n 10");
            cpuTimeList = getProcessesListForDockerAux("ps aux |sed -n '2,$p' | sort  -k 3 -r|head -n 9");
            virtList = getProcessesListForDockerTop("top -b -n 1|sed -n '5,$p' | sed s/\"%\"/\"\"/g | sort  -k 5 -nr|head -n 10");
        }else {
            cpuUsageList = getProcessesList("top -b -n 1|sed -n '8,$p' | sort  -k 9 -nr|head -n 6");
            cpuTimeList = getProcessesList("top -b -n 1|sed -n '8,$p' | sort  -k 11 -r|head -n 6");
            virtList = getProcessesList("top -b -n 1|sed -n '8,$p' | sort  -k 5 -nr|head -n 6");
        }


        TProcesses tProcesses = new TProcesses();
        tProcesses.setTProcessesCpuUsage(cpuUsageList);
        tProcesses.setTProcessesCpuTime(cpuTimeList);
        tProcesses.setTProcessesVirt(virtList);

        return tProcesses;
    }

    private List<TProcessInfo> getProcessesList(String type) {
        Process pro;
        Runtime r = Runtime.getRuntime();

        Map<String, Map<String, String>> processInfoMap = getProcessInfoMap();
        Map<String, String> timeMap = processInfoMap.get("cpuTime");
        Map<String, String> commandMap = processInfoMap.get("command");
        List<TProcessInfo> tProcessInfos = new ArrayList<TProcessInfo>();
        try {

            String[] command = {"/bin/sh", "-c", type};
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;

            while ((line = in.readLine()) != null) {
                line = line.trim();
                String[] processInfo = line.split("\\s+");

                if (processInfo.length >= 0) {
                    TProcessInfo tProcessInfo = new TProcessInfo();
                    tProcessInfo.setPID(processInfo[0]);
                    tProcessInfo.setProcess(processInfo[11]);
                    tProcessInfo.setCommand(commandMap.get(processInfo[0]));
                    tProcessInfo.setCpuUsage(Double.parseDouble(processInfo[8]));

                    long virt;
                    if(processInfo[4].contains("g")){
                        virt = Math.round(Double.parseDouble(processInfo[4].substring(0,processInfo[4].indexOf("g")))*1024*1024);
                    }else if(processInfo[4].contains("m")){
                        virt = Math.round(Double.parseDouble(processInfo[4].substring(0,processInfo[4].indexOf("m")))*1024);
                    }else {
                        virt =Long.parseLong(processInfo[4]);
                    }

                    tProcessInfo.setVirt(virt);

                    String time = timeMap.get(processInfo[0]);
                    String hour = "0";
                    String min = "0";
                    if(time !=null && time.contains(":")){
                        hour = time.substring(0,time.indexOf(":"));
                        min = time.substring(time.indexOf(":")+1);
                    }

                    tProcessInfo.setCpuTime(Long.parseLong(hour)*60+Long.parseLong(min));
                    tProcessInfos.add(tProcessInfo);
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return tProcessInfos;
    }

    private Map<String, Map<String, String>> getProcessInfoMap() {

        Map<String, String> timeMap = new HashMap<String, String>();
        Map<String, String> commandMap = new HashMap<String, String>();

        Process pro;
        Runtime r = Runtime.getRuntime();
        try {

            String[] command = {"/bin/sh", "-c", "ps aux | sed -n '2,$p'"};
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                String[] processInfo = line.split("\\s+");

                if (processInfo.length >= 0) {
                    timeMap.put(processInfo[1], processInfo[9]);
                    commandMap.put(processInfo[1], processInfo[10]);
                }

            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        Map<String, Map<String, String>> processInfoMap = new HashMap<String, Map<String, String>>();
        processInfoMap.put("cpuTime", timeMap);
        processInfoMap.put("command", commandMap);

        return processInfoMap;
    }

    private List<TProcessInfo> getProcessesListForDockerTop(String type) {
        Process pro;
        Runtime r = Runtime.getRuntime();

        Map<String, Map<String, String>> processInfoMap = getProcessInfoMapForDockerAUX();
        Map<String, String> timeMap = processInfoMap.get("cpuTime");
        Map<String, String> commandMap = processInfoMap.get("command");
        List<TProcessInfo> tProcessInfos = new ArrayList<TProcessInfo>();
        try {

            String[] command = {"/bin/sh", "-c", type};
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;

            while ((line = in.readLine()) != null) {
                line = line.trim();
                String[] processInfo = line.split("\\s+");

                if (processInfo.length >= 9 && !checkContains(processInfo[8])) {
                    TProcessInfo tProcessInfo = new TProcessInfo();
                    tProcessInfo.setPID(processInfo[0]);
                    tProcessInfo.setProcess(processInfo[8]);
                    tProcessInfo.setCommand(commandMap.get(processInfo[0]));
                    tProcessInfo.setCpuUsage(Double.parseDouble(processInfo[7]));

                    long virt;
                    if(processInfo[4].contains("m")){
                        virt = Long.parseLong(processInfo[4].substring(0,processInfo[4].indexOf("m")))*1024;
                    }else {
                        virt =Long.parseLong(processInfo[4]);
                    }
                    tProcessInfo.setVirt(virt);

                    String time = timeMap.get(processInfo[0]);
                    String hour = "0";
                    String min = "0";
                    if(time !=null && time.contains(":")){
                        hour = time.substring(0,time.indexOf(":"));
                        min = time.substring(time.indexOf(":")+1);
                    }

                    tProcessInfo.setCpuTime(Long.parseLong(hour)*60+Long.parseLong(min));
                    tProcessInfos.add(tProcessInfo);
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return tProcessInfos;
    }

    private List<TProcessInfo> getProcessesListForDockerAux(String type) {
        Process pro;
        Runtime r = Runtime.getRuntime();

        Map<String, Map<String, String>> processInfoMap = getProcessInfoMapForDockerTop();
        Map<String, String> processMap = processInfoMap.get("process");
        Map<String, String> cpuUsageMap = processInfoMap.get("cpuUsage");
        Map<String, String> virtMap = processInfoMap.get("virt");


        List<TProcessInfo> tProcessInfos = new ArrayList<TProcessInfo>();
        try {

            String[] command = {"/bin/sh", "-c", type};
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;

            while ((line = in.readLine()) != null) {
                line = line.trim();
                String[] processInfo = line.split("\\s+");

                if (processInfo.length >= 4 && !checkContains(processInfo[3]) && cpuUsageMap.get(processInfo[0]) != null) {
                    TProcessInfo tProcessInfo = new TProcessInfo();
                    tProcessInfo.setPID(processInfo[0]);
                    tProcessInfo.setProcess(processMap.get(processInfo[0]));
                    tProcessInfo.setCommand(processInfo[3]);
                    tProcessInfo.setCpuUsage(Double.parseDouble(cpuUsageMap.get(processInfo[0])));


                    long virt;
                    if(virtMap.get(processInfo[0]).contains("m")){
                        virt = Long.parseLong(virtMap.get(processInfo[0]).substring(0,virtMap.get(processInfo[0]).indexOf("m")))*1024;
                    }else {
                        virt =Long.parseLong(virtMap.get(processInfo[0]));
                    }


                    tProcessInfo.setVirt(virt);



                    String time = processInfo[2];
                    String hour = "0";
                    String min = "0";
                    if(time !=null && time.contains(":")){
                        hour = time.substring(0,time.indexOf(":"));
                        min = time.substring(time.indexOf(":")+1);
                    }

                    tProcessInfo.setCpuTime(Long.parseLong(hour)*60+Long.parseLong(min));
                    tProcessInfos.add(tProcessInfo);
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return tProcessInfos;
    }

    private Map<String, Map<String, String>> getProcessInfoMapForDockerTop() {

        Map<String, String> processMap = new HashMap<String, String>();
        Map<String, String> cpuUsageMap = new HashMap<String, String>();
        Map<String, String> virtMap = new HashMap<String, String>();


        Process pro;
        Runtime r = Runtime.getRuntime();
        try {

            String[] command = {"/bin/sh", "-c", "top -b -n 1|sed -n '5,$p' | sed s/\"%\"/\"\"/g"};
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                String[] processInfo = line.split("\\s+");

                if (processInfo.length >= 9) {
                    processMap.put(processInfo[0],processInfo[8]);
                    cpuUsageMap.put(processInfo[0],processInfo[7]);
                    virtMap.put(processInfo[0],processInfo[4]);
                }

            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        Map<String, Map<String, String>> processInfoMapForDocker = new HashMap<String, Map<String, String>>();
        processInfoMapForDocker.put("process", processMap);
        processInfoMapForDocker.put("cpuUsage", cpuUsageMap);
        processInfoMapForDocker.put("virt", virtMap);

        return processInfoMapForDocker;
    }

    private Map<String, Map<String, String>> getProcessInfoMapForDockerAUX() {

        Map<String, String> commandMap = new HashMap<String, String>();
        Map<String, String> cpuTimeMap = new HashMap<String, String>();



        Process pro;
        Runtime r = Runtime.getRuntime();
        try {

            String[] command = {"/bin/sh", "-c", "ps aux |sed -n '2,$p'"};
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                String[] processInfo = line.split("\\s+");

                if (processInfo.length >= 4) {
                    commandMap.put(processInfo[0],processInfo[3]);
                    cpuTimeMap.put(processInfo[0],processInfo[2]);
                }

            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        Map<String, Map<String, String>> processInfoMapForDocker = new HashMap<String, Map<String, String>>();
        processInfoMapForDocker.put("command", commandMap);
        processInfoMapForDocker.put("cpuTime", cpuTimeMap);

        return processInfoMapForDocker;
    }

    private boolean checkContains(String command){
        boolean flag = false;
        String[] oriStrings = {"head","sort","sed","ps","top"};
        for(String oriString : oriStrings){
            if(command.startsWith(oriString)){
                flag = true;
            }
        }
        return flag;
    }

    private boolean isDocker() {
        boolean isDocker = false;
        Process pro;
        Runtime r = Runtime.getRuntime();
        try {
            String[] command = {"/bin/sh", "-c", "cat /proc/1/sched | head -n 1"};
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                String pid = line.substring(line.indexOf("(") + 1, line.indexOf(",")).trim();
                if (!"1".equals(pid)) {
                    isDocker = true;
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return isDocker;
    }

}
