package com.zte.apm.plugin.uep.emb.request;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.Scope;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethods;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.ExecutionPolicy;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.zte.apm.plugin.uep.UepPluginConstants;

/**
 * 本地处理emb消息的话，不跟踪，防止出现虚节点在web
 * Created by 10116285 on 16-6-24.
 */
@Scope(value = UepPluginConstants.SCOPE_UEP, executionPolicy = ExecutionPolicy.INTERNAL)
@TargetMethods({
        @TargetMethod(name = "sendCommandAndWait", paramTypes = {"com.zte.ums.uep.pfl.emb.service.InnerEMessage"})
})
public class SameJVMCmdSession_sendCommandAndWait_Interceptor implements AroundInterceptor {
    private final PLogger logger = PLoggerFactory.getLogger(SameJVMCmdSession_sendCommandAndWait_Interceptor.class);
    private final MethodDescriptor descriptor;
    private final TraceContext traceContext;

    public SameJVMCmdSession_sendCommandAndWait_Interceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
    }

    @Override
    public void before(Object target, Object[] args) {
        Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            return;
        }
        traceContext.removeTraceObject();
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {

    }
}
