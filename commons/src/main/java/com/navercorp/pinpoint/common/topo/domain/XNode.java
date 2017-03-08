package com.navercorp.pinpoint.common.topo.domain;

import com.navercorp.pinpoint.common.buffer.AutomaticBuffer;
import com.navercorp.pinpoint.common.buffer.Buffer;
import com.navercorp.pinpoint.common.buffer.OffsetFixedBuffer;

import java.util.ArrayList;
import java.util.List;

public class XNode {

    private final String name;
    private long responseTime;
    private long calls;
    private long errors;
    private short serviceType;
    private List<XLink> froms;
    private List<XLink> tos;
    private NodeHealth nodeHealth = new NodeHealth();
    private List<AgentInfoDigest> instances = new ArrayList<AgentInfoDigest>();



    public XNode(String name, short serviceType,long elapsed, long errCode, long calls) {
        this(name);
        this.serviceType = serviceType;
        this.responseTime = elapsed;
        this.errors = errCode;
        this.calls = calls;
    }

    public List<XLink> getFroms() {

        return froms != null ? froms : new ArrayList<XLink>();
    }

    public void setFroms(List<XLink> froms) {
        this.froms = froms;
    }

    public List<XLink> getTos() {
        return tos!=null?tos:new ArrayList<XLink>();
    }

    public void setTos(List<XLink> tos) {
        this.tos = tos;
    }

    public XNode(String name) {
        this.name = name;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public long getCalls() {
        return calls;
    }

    public long getErrors() {
        return errors;
    }

    public String getName() {
        return name;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public void setCalls(long calls) {
        this.calls = calls;
    }

    public void setErrors(long errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XNode xNode = (XNode) o;
        return name.equals(xNode.name) != false;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public String toString() {
        return  name;
    }

    public byte[] writeValue() {
        final Buffer buffer = new AutomaticBuffer();
        buffer.putVar(responseTime);
        buffer.putVar(calls);
        buffer.putVar(errors);
        buffer.put(serviceType);
        return buffer.getBuffer();
    }

    public int readValue(byte[] bytes, int offset) {
        final Buffer buffer = new OffsetFixedBuffer(bytes, offset);
        this.responseTime = buffer.readVarLong();
        this.calls = buffer.readVarLong();
        this.errors = buffer.readVarLong();
        //FIXME for compatibility
        if (buffer.limit() > 1){
            this.serviceType = buffer.readShort();
        }
        return buffer.getOffset();
    }

    public String getMetrics() {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(calls).append("calls/");
        strBuf.append(errors).append("errors/");
        strBuf.append(calls == 0 ? 0 : responseTime/calls).append("ms");

        return strBuf.toString();
    }

    public NodeHealth getNodeHealth() {
        return nodeHealth;
    }

    public void setNodeHealth(NodeHealth nodeHealth) {
        this.nodeHealth = nodeHealth;
    }

    public long getCount() {
        return instances == null ? 0 : instances.size();
    }

    public List<AgentInfoDigest> getInstanceNames() {
        return instances == null || instances.isEmpty() ? new ArrayList<AgentInfoDigest>() : instances;
    }

    public void setInstances(List<AgentInfoDigest> agentInfoDigests) {
        this.instances = agentInfoDigests;
    }

    public short getServiceType() {
        return serviceType;
    }

    public void setServiceType(short serviceType) {
        this.serviceType = serviceType;
    }
}
