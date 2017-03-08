package com.navercorp.pinpoint.common.topo.domain;

/**
 * Created by root on 7/19/16.
 */
public class AgentInfoDigest {
    private String serviceName;
    private String agentId;
    private String hostName;
    private short stateCode;
    private short serviceType;

    public AgentInfoDigest(String serviceName, String agentId,String hostName,short stateCode, short serviceType) {
        this.serviceName = serviceName;
        this.agentId = agentId;
        this.hostName = hostName;
        this.stateCode = stateCode;
        this.serviceType = serviceType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getHostName() {
        return hostName;
    }

    public short getStateCode() {
        return stateCode;
    }

    public short getServiceType() {
        return serviceType;
    }
}
