package com.navercorp.pinpoint.bootstrap;

import com.sun.tools.attach.VirtualMachine;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * Created by root on 16-10-26.
 */
public class DynamicAgentLoader {

    private static final String SMARTSIGHT_AGENT_ID = "smartsight.agentId";
    private static final String SMARTSIGHT_APPLICATION_NAME = "smartsight.applicationName";
    private static final String COLLECTOR_IP = "collectorIp";
    private static final String LOGSTASH_IP = "logstashIp";

    public static void load(String pid) {
        ProtectionDomain protectionDomain = PinpointBootStrap.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URL location = codeSource.getLocation();
        String jarPath = location.getPath();

        String agentId = System.getProperty(SMARTSIGHT_AGENT_ID);
        String appName = System.getProperty(SMARTSIGHT_APPLICATION_NAME);
        String collectorIp = System.getProperty(COLLECTOR_IP);
        String logstashIp = System.getProperty(LOGSTASH_IP);
        String agentArg = agentId + "," + appName + "," + collectorIp + "," + logstashIp;
        try {
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(jarPath, agentArg);
            vm.detach();
            System.out.println("Smartsight agent injected.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void loadAgentArgs(String agentArg) {
        String[] args = agentArg.split(",");
        String agentId = args[0];
        String appName = args[1];
        String collectorIp = args[2];
        String logstashIp = args[3];

        System.setProperty(SMARTSIGHT_AGENT_ID, agentId);
        System.setProperty(SMARTSIGHT_APPLICATION_NAME, appName);
        System.setProperty(COLLECTOR_IP, collectorIp);
        System.setProperty(LOGSTASH_IP, logstashIp);
    }
}
