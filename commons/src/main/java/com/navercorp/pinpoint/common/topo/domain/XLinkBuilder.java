package com.navercorp.pinpoint.common.topo.domain;


public class XLinkBuilder {
    private  String from;
    private  String to;
    private long calls;
    private long errors;
    private long responseTime;

    public XLinkBuilder() {

    }

    public XLinkBuilder Link(String from, String to) {
        this.from = from;
        this.to = to;
        return this;
    }

    public XLinkBuilder Calls(long calls) {
        this.calls = calls;
        return this;
    }

    public XLinkBuilder Errors(long errors) {
        this.errors = errors;
        return this;
    }

    public XLinkBuilder Response(long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public XLink build() {
        XLink xLink = new XLink(from, to);
        xLink.setCalls(calls);
        xLink.setErrors(errors);
        xLink.setResponseTime(responseTime);

        return xLink;
    }

}
