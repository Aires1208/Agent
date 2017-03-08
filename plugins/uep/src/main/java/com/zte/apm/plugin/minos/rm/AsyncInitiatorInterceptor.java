package com.zte.apm.plugin.minos.rm;

import com.navercorp.pinpoint.bootstrap.context.AsyncTraceId;
import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.SpanEventRecorder;
import com.navercorp.pinpoint.bootstrap.context.SpanRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.InterceptorScope;
import com.zte.apm.plugin.uep.EMessageUtil;
import com.zte.apm.plugin.uep.UepPluginConstants;
import com.zte.ums.uep.api.pfl.emb.EMessage;

/**
 * To trace async invocations, you have to begin with the method initiating an async task.
 */
public class AsyncInitiatorInterceptor implements AroundInterceptor {
    private final MethodDescriptor descriptor;
    private final TraceContext traceContext;
    private final InterceptorScope scope;

    public AsyncInitiatorInterceptor(TraceContext traceContext, MethodDescriptor descriptor, InterceptorScope scope) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
        this.scope = scope;
    }

    @Override
    public void before(Object target, Object[] args) {
        EMessage eMessage = (EMessage) args[0];

        Trace trace = traceContext.newTraceObject();

        SpanRecorder spanRecorder = trace.getSpanRecorder();
        spanRecorder.recordApi(descriptor, args);
        spanRecorder.recordServiceType(UepPluginConstants.MINOS_SERVER);
        spanRecorder.recordEndPoint(EMessageUtil.getServerName(eMessage));
        spanRecorder.recordRemoteAddress(EMessageUtil.getClientName(eMessage));
        spanRecorder.recordAcceptorHost(EMessageUtil.getServerName(eMessage));
        spanRecorder.recordRpcName(EMessageUtil.getCommandCode(eMessage));

        SpanEventRecorder recorder = trace.traceBlockBegin();
        recorder.recordServiceType(UepPluginConstants.MINOS_SERVER);
        recorder.recordApi(descriptor, args);

        AsyncTraceId asyncTraceId = trace.getAsyncTraceId();
        recorder.recordNextAsyncId(asyncTraceId.getAsyncId());
        scope.getCurrentInvocation().setAttachment(asyncTraceId);
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            return;
        }

        try {
            if (throwable != null) {
                SpanEventRecorder recorder = trace.currentSpanEventRecorder();
                recorder.recordException(throwable);
            }
        } finally {
            trace.traceBlockEnd();
            traceContext.removeTraceObject();
            trace.close();
        }
    }
}
