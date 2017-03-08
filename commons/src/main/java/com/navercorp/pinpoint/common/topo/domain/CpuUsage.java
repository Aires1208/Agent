package com.navercorp.pinpoint.common.topo.domain;

/**
 * Created by 10183966 on 7/25/16.
 */
public class CpuUsage {
    private String info;
    private String[] times;
    private double[] jvmCpuUsage;
    private double[] systemCpuUsage;

    public CpuUsage(String[] times, double[] jvmCpuUsage, double[] systemCpuUsage) {
        this.times = times;
        this.jvmCpuUsage = jvmCpuUsage;
        this.systemCpuUsage = systemCpuUsage;

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

    public double[] getJvmCpuUsage() {
        return jvmCpuUsage;
    }

    public double[] getSystemCpuUsage() {
        return systemCpuUsage;
    }
}
