package com.navercorp.pinpoint.common.topo.domain;

/**
 * Created by 10183966 on 7/21/16.
 */
public class XMetricBuilder {
    private long calls;
    private long errors;
    private long responseTime;

    public XMetricBuilder() {
    }

    public XMetricBuilder Calls(long calls) {
        this.calls = calls;
        return this;
    }

    public XMetricBuilder Errors(long errors) {
        this.errors = errors;
        return this;
    }

    public XMetricBuilder Response(long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public XMetric build() {
        XMetric xMetric = new XMetric();
        xMetric.setCalls(calls);
        xMetric.setErrors(errors);
        xMetric.setResponseTime(responseTime);
        return xMetric;
    }

    @Override
    public String toString() {
        return "XMetricBuilder{" +
                "calls=" + calls +
                ", errors=" + errors +
                ", responseTime=" + responseTime +
                '}';
    }
}
