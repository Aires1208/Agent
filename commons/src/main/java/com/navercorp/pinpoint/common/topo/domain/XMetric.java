package com.navercorp.pinpoint.common.topo.domain;

/**
 * Created by 10183966 on 7/21/16.
 */
public class XMetric {
    private long responseTime;
    private long calls;
    private long errors;

    public XMetric() {
    }

    public XMetric(long responseTime, long calls, long errors) {
        this.responseTime = responseTime;
        this.calls = calls;
        this.errors = errors;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public long getCalls() {
        return calls;
    }

    public void setCalls(long calls) {
        this.calls = calls;
    }

    public long getErrors() {
        return errors;
    }

    public void setErrors(long errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "XMetric{" +
                "responseTime=" + responseTime +
                ", calls=" + calls +
                ", errors=" + errors +
                '}';
    }
}
