package com.navercorp.pinpoint.common.usercase;

import com.navercorp.pinpoint.common.bo.SpanBo;
import com.navercorp.pinpoint.common.bo.SpanEventBo;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


public class SpanBoBuilder {
    private short serviceType;
    private String serviceId;
    private String agentId;
    private int errCode;
    private long spanId = -1L;
    private long parentSpanId = -1L;
    private List<SpanEventBo> spanEventBos = newArrayList();
    private int elapsed;

    public SpanBoBuilder(){

    }

    public SpanBoBuilder Service(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public SpanBoBuilder AgentId(String agentId) {
        this.agentId = agentId;
        return this;
    }

    public SpanBoBuilder ErrorCode(int errCode) {
        this.errCode = errCode;
        return this;
    }

    public SpanBoBuilder SpanId(long spanId) {
        this.spanId = spanId;
        return this;
    }

    public SpanBoBuilder SpanEvent(SpanEventBo spanEvent) {
        this.spanEventBos.add(spanEvent);
        return this;
    }

    public SpanBoBuilder ParentSpanId(long parentSpanId) {

        this.parentSpanId = parentSpanId;
        return this;
    }

    public SpanBoBuilder Elapsed(int elapsed) {
        this.elapsed = elapsed;
        return this;
    }

    public SpanBoBuilder ServiceType(short serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public SpanBo build() {
        SpanBo spanBo = new SpanBo();
        spanBo.setParentSpanId(parentSpanId);
        spanBo.setApplicationId(serviceId);
        spanBo.setServiceType(serviceType);
        spanBo.setAgentId(agentId);
        spanBo.setSpanID(spanId);
        spanBo.setElapsed(elapsed);
        spanBo.setErrCode(errCode);

        for(SpanEventBo spanEventBo: spanEventBos) {
            spanBo.addSpanEvent(spanEventBo);
        }
        return spanBo;
    }
}
