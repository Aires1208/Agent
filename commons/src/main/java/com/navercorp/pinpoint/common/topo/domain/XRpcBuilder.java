package com.navercorp.pinpoint.common.topo.domain;

/**
 * Created by ${10183966} on 11/24/16.
 */
public class XRpcBuilder {
    private String method;
    private int count;
    private int successCount;
    private long minTime;
    private long maxTime;
    private long duration;
    private long avgTime;

    public XRpcBuilder AvgTime(long avgTime) {
        this.avgTime = avgTime;
        return this;
    }


    private String rpc;

    public XRpcBuilder() {
    }

    public XRpcBuilder Method(String method) {
        this.method = method;
        return this;
    }

    public XRpcBuilder Count(int count) {
        this.count = count;
        return this;
    }

    public XRpcBuilder SuccessCount(int successCount) {
        this.successCount = successCount;
        return this;
    }

    public XRpcBuilder MinTime(long minTime) {
        this.minTime = minTime;
        return this;
    }

    public XRpcBuilder MaxTime(long maxTime) {
        this.maxTime = maxTime;
        return this;
    }

    public XRpcBuilder Duration(long duration) {
        this.duration = duration;
        return this;
    }
    public XRpcBuilder Rpc(String rpc) {
        this.rpc = rpc;
        return this;
    }

    public XRpc build() {
        XRpc xRpc = new XRpc();
        xRpc.setMethod(method);
        xRpc.setCount(count);
        xRpc.setSuccessCount(successCount);
        xRpc.setMin_time(minTime);
        xRpc.setMax_time(maxTime);
        xRpc.setDuration(duration);
        xRpc.setRpc(rpc);
        xRpc.setAvg_time(avgTime);
        return xRpc;
    }
}
