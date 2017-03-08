package com.zte.apm.plugin.mock;

import com.navercorp.pinpoint.bootstrap.context.AsyncTraceId;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceId;
import com.navercorp.pinpoint.bootstrap.context.scope.TraceScope;

/**
 * Created by 10116285 on 16-6-20.
 */
public class ApmTrace implements Trace {
    private TraceId traceId;
    private ApmSpanRecorder spanRecorder = new ApmSpanRecorder();
    private ApmSpanEventRecorder spanEventRecorder = new ApmSpanEventRecorder();

    public ApmTrace(TraceId traceId) {
        this.traceId = traceId;
    }

    public ApmTrace() {
        this.traceId = new ApmTraceId(ApmConst.AGENT_ID, ApmConst.START_TIME, 0L, -1L, 0L);
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public long getStartTime() {
        return traceId.getAgentStartTime();
    }

    @Override
    public Thread getBindThread() {
        return null;
    }

    @Override
    public TraceId getTraceId() {
        return traceId;
    }

    @Override
    public AsyncTraceId getAsyncTraceId() {
        return null;
    }

    @Override
    public boolean canSampled() {
        return true;
    }

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public ApmSpanRecorder getSpanRecorder() {
        return spanRecorder;
    }

    @Override
    public ApmSpanEventRecorder currentSpanEventRecorder() {
        return spanEventRecorder;
    }

    @Override
    public void close() {

    }

    @Override
    public TraceScope getScope(String name) {
        return null;
    }

    @Override
    public TraceScope addScope(String name) {
        return null;
    }

    @Override
    public ApmSpanEventRecorder traceBlockBegin() {
        return spanEventRecorder;
    }

    @Override
    public ApmSpanEventRecorder traceBlockBegin(int stackId) {
        return spanEventRecorder;
    }

    @Override
    public void traceBlockEnd() {

    }

    @Override
    public void traceBlockEnd(int stackId) {

    }

    @Override
    public boolean isRootStack() {
        return false;
    }

    @Override
    public int getCallStackFrameId() {
        return 0;
    }
}
