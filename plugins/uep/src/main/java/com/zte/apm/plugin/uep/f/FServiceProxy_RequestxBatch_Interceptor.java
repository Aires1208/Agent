package com.zte.apm.plugin.uep.f;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.SpanEventRecorder;
import com.navercorp.pinpoint.bootstrap.context.SpanRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.context.TraceId;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethods;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.zte.apm.plugin.uep.FIMessageUtil;
import com.zte.apm.plugin.uep.UepPluginConstants;
import com.zte.ums.uep.api.pfl.finterface.FClientService;
import com.zte.ums.uep.api.pfl.finterface.FIMessage;

/**
 * com.zte.ums.uep.pfl.finterface.wsf.FServiceProxy
 *
 * Created by 10116285 on 16-6-16.
 */
@TargetMethods({
        @TargetMethod(name = "requestxBatch", paramTypes = {"java.awt.Component", "com.zte.ums.uep.api.pfl.finterface.FIMessage[]"}),
        @TargetMethod(name = "requestxBatch", paramTypes = {"java.awt.Component", "com.zte.ums.uep.api.pfl.finterface.FIMessage[]", "boolean"})
})
public class FServiceProxy_RequestxBatch_Interceptor implements AroundInterceptor {
    private final PLogger logger = PLoggerFactory.getLogger(FServiceProxy_RequestxBatch_Interceptor.class.getName());
    private final TraceContext traceContext;
    private final MethodDescriptor descriptor;

    public FServiceProxy_RequestxBatch_Interceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
    }

    @Override
    public void before(Object target, Object[] args) {
        FIMessage[] messages = (FIMessage[]) args[1];
        if (messages == null || messages.length == 0) {
            return;
        }
        FIMessage firstMessage = messages[0];
        FClientService fClientService = (FClientService) target;
        String serverName = fClientService.getSvrIP();
        String clientName = fClientService.getClientIP();

        try {
            Trace trace = traceContext.newTraceObject();
            TraceId nextId = trace.getTraceId().getNextTraceId();

            SpanRecorder spanRecorder = trace.getSpanRecorder();
            spanRecorder.recordApi(descriptor, args);
            spanRecorder.recordServiceType(UepPluginConstants.UEP_CLIENT);
            spanRecorder.recordEndPoint(serverName);
            spanRecorder.recordRemoteAddress(clientName);
            spanRecorder.recordAcceptorHost(serverName);
            spanRecorder.recordRpcName(FIMessageUtil.getCommandCode(firstMessage));

            SpanEventRecorder spanEventRecorder = trace.traceBlockBegin();
            spanEventRecorder.recordApi(descriptor, args);
            spanEventRecorder.recordServiceType(UepPluginConstants.UEP_CLIENT);
            spanEventRecorder.recordEndPoint(serverName);
            spanEventRecorder.recordDestinationId(serverName);
            spanEventRecorder.recordRpcName(FIMessageUtil.getCommandCode(firstMessage));
            spanEventRecorder.recordNextSpanId(nextId.getSpanId());


            firstMessage.setValue(UepPluginConstants.META_TRANSACTION_ID, nextId.getTransactionId());
            firstMessage.setValue(UepPluginConstants.META_SPAN_ID, Long.toString(nextId.getSpanId()));
            firstMessage.setValue(UepPluginConstants.META_PARENT_SPAN_ID, Long.toString(nextId.getParentSpanId()));
            firstMessage.setValue(UepPluginConstants.META_PARENT_APPLICATION_TYPE, Short.toString(traceContext.getServerTypeCode()));
            firstMessage.setValue(UepPluginConstants.META_PARENT_APPLICATION_NAME, traceContext.getApplicationName());
            firstMessage.setValue(UepPluginConstants.META_FLAGS, Short.toString(nextId.getFlags()));
            firstMessage.setValue(UepPluginConstants.META_F_SERVERNAME, serverName);
            firstMessage.setValue(UepPluginConstants.META_F_CLIENTNAME, clientName);
        } catch (Throwable th) {
            logger.warn("BEFORE. Caused:{}", th.getMessage(), th);
        }
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            return;
        }
        try {
            SpanRecorder spanRecorder = trace.getSpanRecorder();
            SpanEventRecorder spanEventRecorder = trace.currentSpanEventRecorder();

            if (throwable == null) {
                spanRecorder.recordAttribute(UepPluginConstants.UEP_EMB_RESULT_ANNOTATION_KEY, result);
                spanEventRecorder.recordAttribute(UepPluginConstants.UEP_EMB_RESULT_ANNOTATION_KEY, result);
            } else {
                spanRecorder.recordException(throwable);
                spanEventRecorder.recordException(throwable);
            }
        } catch (Throwable th) {
            logger.warn("AFTER. Caused:{}", th.getMessage(), th);
        } finally {
            trace.traceBlockEnd();
            traceContext.removeTraceObject();
            trace.close();
        }
    }
}
