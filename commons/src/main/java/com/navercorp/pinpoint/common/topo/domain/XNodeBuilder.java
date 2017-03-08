package com.navercorp.pinpoint.common.topo.domain;


public class XNodeBuilder {
    private String nodeName;
    private short serviceType;
    private long calls;
    private long errors;
    private long responseTime;

    public XNodeBuilder() {

    }

    public XNodeBuilder Name(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public XNodeBuilder Calls(long calls) {
        this.calls = calls;
        return this;
    }

    public XNodeBuilder Errors(long errors) {
        this.errors = errors;
        return this;
    }

    public XNodeBuilder Response(long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public  XNodeBuilder ServiceType(short serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public XNode build() {
        XNode xNode = new XNode(nodeName);
        xNode.setServiceType(serviceType);
        xNode.setCalls(calls);
        xNode.setErrors(errors);
        xNode.setResponseTime(responseTime);

        return xNode;
    }

}
