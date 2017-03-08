package com.navercorp.pinpoint.common.topo.domain;

import java.util.List;

/**
 * Created by root on 16-7-11.
 */
public class TopoLine {
    private final List<XNode> xNodes;
    private final List<XLink> xLinks;
    private long timestamp;

    public TopoLine(List<XNode> xNodes, List<XLink> xLinks) {
        if (xNodes == null || xLinks == null) {
            throw new NullPointerException("xNodes or xLinks must not be null");
        }
        this.xNodes = xNodes;
        this.xLinks = xLinks;
    }

    public List<XNode> getXNodes() {
        return xNodes;
    }

    public List<XLink> getXLinks() {
        return xLinks;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TopoLine{" +
                "xNodes=" + xNodes +
                ", xLinks=" + xLinks +
                ", timestamp=" + timestamp +
                '}';
    }
}
