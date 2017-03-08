package com.zte.apm.plugin.minos.rm;

import com.navercorp.pinpoint.bootstrap.async.AsyncTraceIdAccessor;
import com.navercorp.pinpoint.bootstrap.context.AsyncTraceId;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.IgnoreMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.InterceptorScope;

/**
 * This interceptor get AsyncTraceId from interceptor scope invocation attachment and set it to the initializing object through AsyncTraceIdAccessor
 */
public class WorkerConstructorInterceptor implements AroundInterceptor {
    private final InterceptorScope scope;
    
    public WorkerConstructorInterceptor(InterceptorScope scope) {
        this.scope = scope;
    }

    @IgnoreMethod
    @Override
    public void before(Object target, Object[] args) {

    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        AsyncTraceId asyncTraceId = (AsyncTraceId)scope.getCurrentInvocation().getAttachment();
        ((AsyncTraceIdAccessor)target)._$PINPOINT$_setAsyncTraceId(asyncTraceId);
    }
}
