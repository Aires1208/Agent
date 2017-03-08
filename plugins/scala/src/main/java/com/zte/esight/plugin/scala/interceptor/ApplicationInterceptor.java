package com.zte.esight.plugin.scala.interceptor;

import com.navercorp.pinpoint.bootstrap.context.*;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethods;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.util.NumberUtils;
import com.zte.esight.plugin.scala.ScalaConstants;

import javax.servlet.http.HttpServletRequest;

@TargetMethods({
        @TargetMethod(name="handle", paramTypes = {"org.scalatra.servlet.ServletBase","javax.servlet.http.HttpServletRequest","javax.servlet.http.HttpServletResponse"})
})
public class ApplicationInterceptor implements AroundInterceptor {
    private final PLogger logger = PLoggerFactory.getLogger(ApplicationInterceptor.class.getName());
    private final boolean isDebug = logger.isDebugEnabled();
    private final TraceContext traceContext;
    private final MethodDescriptor descriptor;

    public ApplicationInterceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
    }
    @Override
    public void before(Object target, Object[] args) {
        logger.beforeInterceptor(target, args);
        HttpServletRequest request = ((HttpServletRequest) args[1]);

        final TraceId traceId = populateTraceIdFromRequest(request);
        Trace trace;
        if(traceId != null){
            trace = traceContext.continueTraceObject(traceId);
        }else {
            trace = traceContext.newTraceObject();
        }
        SpanRecorder spanRecorder = trace.getSpanRecorder();
        spanRecorder.recordServiceType(ScalaConstants.SCALA_APP);
        spanRecorder.recordApi(descriptor);
        spanRecorder.recordRpcName(request.getRequestURI());
        spanRecorder.recordRemoteAddress(request.getRemoteAddr());

        SpanEventRecorder recorder = trace.traceBlockBegin();
        recorder.recordServiceType(ScalaConstants.SCALA_APP);
        recorder.recordApi(descriptor);
        ServerMetaDataHolder holder = this.traceContext.getServerMetaDataHolder();
        holder.notifyListeners();

    }

    private TraceId populateTraceIdFromRequest(HttpServletRequest request) {
        String transactionId = request.getHeader(Header.HTTP_TRACE_ID.toString());
        if (transactionId != null) {
            long parentSpanID = NumberUtils.parseLong(request.getHeader(Header.HTTP_PARENT_SPAN_ID.toString()), SpanId.NULL);
            long spanID = NumberUtils.parseLong(request.getHeader(Header.HTTP_SPAN_ID.toString()), SpanId.NULL);
            short flags = NumberUtils.parseShort(request.getHeader(Header.HTTP_FLAGS.toString()), (short) 0);

            final TraceId id = traceContext.createTraceId(transactionId, parentSpanID, spanID, flags);
            if (isDebug) {
                logger.debug("TraceID exist. continue trace. {}", id);
            }
            return id;
        } else {
            return null;
        }
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        logger.afterInterceptor(target, args, result, throwable);
        Trace trace = traceContext.currentTraceObject();
        try {
            SpanRecorder spanRecorder = trace.getSpanRecorder();
            SpanEventRecorder recorder = trace.currentSpanEventRecorder();
            if (throwable != null) {
                spanRecorder.recordException(throwable);
                recorder.recordException(throwable);
            }
        } finally {
            trace.traceBlockEnd();
            trace.close();
            traceContext.removeTraceObject();
        }
    }
}
