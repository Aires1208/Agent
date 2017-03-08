package com.zte.esight.plugin.zenap.fm.interceptors;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.SpanEventRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.common.trace.ServiceType;

/**
 * Created by 10116285 on 16-7-21.
 */
public abstract class SpanEventGenerator  implements AroundInterceptor {

    private final PLogger logger = PLoggerFactory.getLogger(SpanEventGenerator.class.getName());
    protected final TraceContext traceContext;
    protected final MethodDescriptor descriptor;

    public SpanEventGenerator(TraceContext traceContext, MethodDescriptor descriptor) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
    }

    @Override
    public void before(Object target, Object[] args) {
        Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            logger.info("trace == null");
            return;
        }
        if (!trace.canSampled()) {
            logger.info("!trace.canSampled()");
            return;
        }

        SpanEventRecorder recorder = trace.traceBlockBegin();
        recorder.recordServiceType(getMethodServiceType());
        recorder.recordApi(descriptor);

    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            logger.info("trace == null");
            return;
        }
        if (!trace.canSampled()) {
            logger.info("!trace.canSampled()");
            return;
        }

        try {
            SpanEventRecorder recorder = trace.currentSpanEventRecorder();
            if (throwable != null) {
                recorder.recordException(throwable);
            }
        } finally {
            trace.traceBlockEnd();
        }
    }

    protected abstract ServiceType getMethodServiceType();
}
