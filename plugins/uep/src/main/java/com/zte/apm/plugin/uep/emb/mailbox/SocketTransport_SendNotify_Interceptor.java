/**
 * Copyright 2014 NAVER Corp.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zte.apm.plugin.uep.emb.mailbox;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.SpanEventRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.context.TraceId;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.Scope;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethods;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.ExecutionPolicy;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.zte.apm.plugin.uep.EMessageUtil;
import com.zte.apm.plugin.uep.UepPluginConstants;
import com.zte.ums.uep.api.pfl.emb.EMessage;

/**
 * com.zte.ums.uep.pfl.emb.transport.socket.SocketTransport
 * sendNotify(EMBUrl url, String inMailBoxName, InnerEMessage notify) throws EMBException
 *
 * @author 10116285
 */
@Scope(value = UepPluginConstants.SCOPE_UEP, executionPolicy = ExecutionPolicy.INTERNAL)
@TargetMethods({
        @TargetMethod(name = "sendNotify", paramTypes = {"com.zte.ums.uep.api.pfl.emb.EMBUrl", "java.lang.String", "com.zte.ums.uep.pfl.emb.service.InnerEMessage"})
})
public class SocketTransport_SendNotify_Interceptor implements AroundInterceptor {
    private final PLogger logger = PLoggerFactory.getLogger(SocketTransport_SendNotify_Interceptor.class.getName());

    private final MethodDescriptor descriptor;
    private final TraceContext traceContext;

    public SocketTransport_SendNotify_Interceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.descriptor = descriptor;
        this.traceContext = traceContext;
    }

    @Override
    public void before(Object target, Object[] args) {
        EMessage eMessage = (EMessage) args[2];

        if (!EMessageUtil.hasValidPath(eMessage)) {
            return;
        }
        if (EMessageUtil.isFromRm(eMessage)) {
            return;
        }

        Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            return;
        }
        try {
            TraceId nextId = trace.getTraceId().getNextTraceId();

            SpanEventRecorder spanEventRecorder = trace.traceBlockBegin();
            spanEventRecorder.recordServiceType(UepPluginConstants.UEP_CLIENT);
            spanEventRecorder.recordApi(descriptor, args);
            spanEventRecorder.recordEndPoint(EMessageUtil.getServerName(eMessage));
            spanEventRecorder.recordDestinationId(EMessageUtil.getServerName(eMessage));
            spanEventRecorder.recordRpcName(EMessageUtil.getCommandCode(eMessage));
            spanEventRecorder.recordNextSpanId(nextId.getSpanId());

            eMessage.setMsgHeadProperty(UepPluginConstants.META_TRANSACTION_ID, nextId.getTransactionId());
            eMessage.setMsgHeadProperty(UepPluginConstants.META_SPAN_ID, Long.toString(nextId.getSpanId()));
            eMessage.setMsgHeadProperty(UepPluginConstants.META_PARENT_SPAN_ID, Long.toString(nextId.getParentSpanId()));
            eMessage.setMsgHeadProperty(UepPluginConstants.META_PARENT_APPLICATION_TYPE, Short.toString(traceContext.getServerTypeCode()));
            eMessage.setMsgHeadProperty(UepPluginConstants.META_PARENT_APPLICATION_NAME, traceContext.getApplicationName());
            eMessage.setMsgHeadProperty(UepPluginConstants.META_FLAGS, Short.toString(nextId.getFlags()));
        } catch (Throwable th) {
            logger.warn("BEFORE. Caused:{}", th.getMessage(), th);
        }
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        EMessage eMessage = (EMessage) args[2];

        if (!EMessageUtil.hasValidPath(eMessage)) {
            return;
        }
        if (EMessageUtil.isFromRm(eMessage)) {
            return;
        }

        Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            return;
        }
        try {
            SpanEventRecorder spanEventRecorder = trace.currentSpanEventRecorder();

            if (throwable == null) {
                spanEventRecorder.recordAttribute(UepPluginConstants.UEP_EMB_RESULT_ANNOTATION_KEY, result);
            } else {
                spanEventRecorder.recordException(throwable);
            }
        } catch (Throwable th) {
            logger.warn("AFTER. Caused:{}", th.getMessage(), th);
        } finally {
            trace.traceBlockEnd();
        }
    }
}
