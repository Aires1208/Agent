package com.navercorp.pinpoint.common.topo.domain;

/**
 * Created by 10183966 on 7/25/16.
 */
public class HeapInfo {
    private String info;
    private String[] times;
    private long[] maxs;
    private long[] useds;
    private long[] fgcs;

    public void setTimes(String[] times) {
        this.times = times;
    }

    public HeapInfo(String[] times, long[] maxs, long[] useds, long[] fgcs) {
        this.times = times;
        this.maxs = maxs;
        this.useds = useds;
        this.fgcs = fgcs;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public String[] getTimes() {
        return times;
    }

    public long[] getMaxs() {
        return maxs;
    }

    public long[] getUseds() {
        return useds;
    }

    public long[] getFgcs() {
        return fgcs;
    }
}
