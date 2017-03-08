package com.navercorp.pinpoint.common.topo.domain;

/**
 * Created by 10183966 on 7/21/16.
 */
public class XDot {
    private XMetric xMetric;
    private final long acceptedTime;

    public long getAcceptedTime() {
        return acceptedTime;
    }

    public XDot(long acceptedTime) {
        this.acceptedTime = acceptedTime;
    }

    public XMetric getxMetric() {
        return xMetric;
    }

    public void setxMetric(XMetric xMetric) {
        this.xMetric = xMetric;
    }
}
