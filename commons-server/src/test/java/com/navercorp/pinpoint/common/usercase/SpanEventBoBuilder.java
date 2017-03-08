package com.navercorp.pinpoint.common.usercase;

import com.navercorp.pinpoint.common.bo.SpanEventBo;


public class SpanEventBoBuilder {

    private String destinationId;
    private long nextSpanId = -1L;
    private int endElapsed = 0;
    private short serviceType = -1;

    public SpanEventBoBuilder(){

    }

    public SpanEventBoBuilder endElapsed(int endElapsed) {
        this.endElapsed = endElapsed;
        return this;
    }

    public SpanEventBoBuilder Destination(String destinationId) {
        this.destinationId = destinationId;
        return this;
    }

    public SpanEventBoBuilder NextSpanId(long nextSpanId) {
        this.nextSpanId = nextSpanId;
        return this;
    }

    public SpanEventBoBuilder ServiceType(short serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public SpanEventBo build() {
        SpanEventBo spanEventBo = new SpanEventBo();
        spanEventBo.setNextSpanId(nextSpanId);
        spanEventBo.setDestinationId(destinationId);
        spanEventBo.setEndElapsed(endElapsed);
        spanEventBo.setServiceType(serviceType);
        return spanEventBo;
    }
}
