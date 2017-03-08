package com.zte.apm.plugin.mock;

import com.navercorp.pinpoint.bootstrap.config.ProfilerConfig;
import com.navercorp.pinpoint.bootstrap.context.AsyncTraceId;
import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.ParsingResult;
import com.navercorp.pinpoint.bootstrap.context.ServerMetaDataHolder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.context.TraceId;

/**
 * Created by 10116285 on 16-6-20.
 */
public class ApmTraceContext implements TraceContext {
    private final short serverTypeCode;
    private ApmTrace currentTrace;

    public ApmTraceContext(int serverTypeCode) {
        this.serverTypeCode = (short) serverTypeCode;
    }

    @Override
    public ApmTrace currentTraceObject() {
        return currentTrace;
    }

    @Override
    public ApmTrace currentRawTraceObject() {
        return currentTrace;
    }

    @Override
    public ApmTrace continueTraceObject(TraceId traceId) {
        return new ApmTrace(traceId);
    }

    @Override
    public ApmTrace continueTraceObject(Trace trace) {
        return new ApmTrace(trace.getTraceId());
    }

    @Override
    public ApmTrace newTraceObject() {
        return new ApmTrace();
    }

    @Override
    public ApmTrace continueAsyncTraceObject(AsyncTraceId traceId, int asyncId, long startTime) {
        return null;
    }

    @Override
    public ApmTrace removeTraceObject() {
        return this.currentTrace = null;
    }

    @Override
    public String getAgentId() {
        return "agentId";
    }

    @Override
    public String getApplicationName() {
        return "appName";
    }

    @Override
    public long getAgentStartTime() {
        return 0;
    }

    @Override
    public short getServerTypeCode() {
        return serverTypeCode;
    }

    @Override
    public String getServerType() {
        return null;
    }

    @Override
    public int cacheApi(MethodDescriptor methodDescriptor) {
        return 0;
    }

    @Override
    public int cacheString(String value) {
        return 0;
    }

    @Override
    public ParsingResult parseSql(String sql) {
        return null;
    }

    @Override
    public boolean cacheSql(ParsingResult parsingResult) {
        return false;
    }

    @Override
    public ApmTraceId createTraceId(String transactionId, long parentSpanID, long spanID, short flags) {
        return new ApmTraceId(ApmConst.AGENT_ID, ApmConst.START_TIME, Long.valueOf(transactionId), parentSpanID, spanID);
    }

    @Override
    public ApmTrace disableSampling() {
        return null;
    }

    @Override
    public ProfilerConfig getProfilerConfig() {
        return null;
    }

    @Override
    public ServerMetaDataHolder getServerMetaDataHolder() {
        return null;
    }

    @Override
    public int getAsyncId() {
        return 0;
    }
}
