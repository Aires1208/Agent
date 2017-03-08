package com.navercorp.pinpoint.common.topo.domain;

/**
 * Created by 10183966 on 7/25/16.
 */
public class TransactionsPerSecond {
    private String info;
    private String[] times;
    private long[] sampledNewCount;
    private long[] sampledContinuationCount;
    private long[] unsampledNewCount;
    private long[] unsampledContinuationCount;

    public TransactionsPerSecond(String[] times, long[] sampledNewCount, long[] sampledContinuationCount, long[] unsampledNewCount, long[] unsampledContinuationCount) {
        this.times = times;
        this.sampledNewCount = sampledNewCount;
        this.sampledContinuationCount = sampledContinuationCount;
        this.unsampledNewCount = unsampledNewCount;
        this.unsampledContinuationCount = unsampledContinuationCount;
    }

    public String getInfo() {
        return info;
    }

    public String[] getTimes() {
        return times;
    }

    public long[] getSampledNewCount() {
        return sampledNewCount;
    }

    public long[] getSampledContinuationCount() {
        return sampledContinuationCount;
    }

    public long[] getUnsampledNewCount() {
        return unsampledNewCount;
    }

    public long[] getUnsampledContinuationCount() {
        return unsampledContinuationCount;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
