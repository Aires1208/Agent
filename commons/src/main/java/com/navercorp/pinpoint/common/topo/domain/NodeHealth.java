package com.navercorp.pinpoint.common.topo.domain;

/**
 * Created by root on 5/26/16.
 */
public class NodeHealth {
    private long critical;
    private long warning;
    private long normal;

    public NodeHealth() {

    }

    public NodeHealth(long critical, long warning, long normal) {
        this.critical = critical;
        this.warning = warning;
        this.normal = normal;
    }

    public long getCritical() {
        return critical;
    }

    public long getWarning() {
        return warning;
    }

    public long getNormal() {
        return normal;
    }
}
