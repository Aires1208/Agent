package com.zte.apm.plugin.mock;

import com.navercorp.pinpoint.bootstrap.context.TraceId;

/**
 * Created by 10116285 on 16-6-20.
 */
public class ApmTraceId implements TraceId {
    private final String agentId;
    private final long agentStartTime;
    private final long transactionId;
    private final long parentSpanId;
    private final long spanId;

    public ApmTraceId(String agentId, long agentStartTime, long transactionId, long parentSpanId, long spanId) {
        this.agentId = agentId;
        this.agentStartTime = agentStartTime;
        this.transactionId = transactionId;
        this.parentSpanId = parentSpanId;
        this.spanId = spanId;
    }

    @Override
    public TraceId getNextTraceId() {
        return new ApmTraceId(agentId, agentStartTime, transactionId, spanId, -spanId);
    }

    @Override
    public long getSpanId() {
        return spanId;
    }

    @Override
    public String getTransactionId() {
        return agentId + "^" + agentStartTime + "^" + transactionId;
    }

    @Override
    public String getAgentId() {
        return agentId;
    }

    @Override
    public long getAgentStartTime() {
        return agentStartTime;
    }

    @Override
    public long getTransactionSequence() {
        return transactionId;
    }

    @Override
    public long getParentSpanId() {
        return parentSpanId;
    }

    @Override
    public short getFlags() {
        return 0;
    }

    @Override
    public boolean isRoot() {
        return false;
    }
}
