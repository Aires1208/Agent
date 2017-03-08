package com.zte.apm.plugin.minos.rm;

import com.navercorp.pinpoint.bootstrap.context.AsyncTraceId;
import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.SpanEventRecorder;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.SpanAsyncEventSimpleAroundInterceptor;
import com.zte.apm.plugin.uep.UepPluginConstants;

/**
 * @author Jongho Moon
 */
public class WorkerRunInterceptor extends SpanAsyncEventSimpleAroundInterceptor {

    public WorkerRunInterceptor(TraceContext traceContext, MethodDescriptor methodDescriptor) {
        super(traceContext, methodDescriptor);
    }

    @Override
    protected void doInBeforeTrace(SpanEventRecorder recorder, AsyncTraceId asyncTraceId, Object target, Object[] args) {
        // do nothing
    }

    @Override
    protected void doInAfterTrace(SpanEventRecorder recorder, Object target, Object[] args, Object result, Throwable throwable) {
        recorder.recordServiceType(UepPluginConstants.MINOS_SERVER);
        recorder.recordApi(methodDescriptor);
        recorder.recordException(throwable);
    }
}
