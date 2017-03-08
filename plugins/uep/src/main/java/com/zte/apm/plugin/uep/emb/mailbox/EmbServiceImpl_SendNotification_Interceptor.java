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
import com.navercorp.pinpoint.bootstrap.context.SpanRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.Scope;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethods;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.zte.apm.plugin.uep.EMessageUtil;
import com.zte.apm.plugin.uep.UepPluginConstants;
import com.zte.ums.uep.api.pfl.emb.EMessage;

/**
 * com.zte.ums.uep.pfl.emb.service.EmbServiceImpl
 *      sendNotification(String mailBoxName, EMessage message) throws EMBException
 *
 * @author 10116285
 */
@Scope(value = UepPluginConstants.SCOPE_UEP)
@TargetMethods({
        @TargetMethod(name="sendNotification", paramTypes={"java.lang.String", "com.zte.ums.uep.api.pfl.emb.EMessage"})
})
public class EmbServiceImpl_SendNotification_Interceptor implements AroundInterceptor {
    private final PLogger logger = PLoggerFactory.getLogger(EmbServiceImpl_SendNotification_Interceptor.class.getName());
    private final MethodDescriptor descriptor;
    private final TraceContext traceContext;

    public EmbServiceImpl_SendNotification_Interceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.descriptor = descriptor;
        this.traceContext = traceContext;
    }

    @Override
    public void before(Object target, Object[] args) {
        String mailBoxName = (String) args[0];
        EMessage eMessage = (EMessage) args[1];
        if (!EMessageUtil.hasValidPath(eMessage)) {
            return;
        }
        if (EMessageUtil.isFromRm(eMessage)) {
            return;
        }

        try {
            Trace trace = traceContext.newTraceObject();

            SpanRecorder spanRecorder = trace.getSpanRecorder();
            spanRecorder.recordApi(descriptor, args);
            spanRecorder.recordServiceType(UepPluginConstants.UEP_CLIENT);
            spanRecorder.recordEndPoint(EMessageUtil.getServerName(eMessage));
            spanRecorder.recordRemoteAddress(EMessageUtil.getClientName(eMessage));
            spanRecorder.recordAcceptorHost(EMessageUtil.getServerName(eMessage));
            spanRecorder.recordRpcName(EMessageUtil.getCommandCode(eMessage));
        } catch (Throwable th) {
            logger.warn("BEFORE. Caused:{}", th.getMessage(), th);
        }
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        EMessage eMessage = (EMessage) args[1];
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
            SpanRecorder spanRecorder = trace.getSpanRecorder();
            if (throwable == null) {
                spanRecorder.recordAttribute(UepPluginConstants.UEP_EMB_RESULT_ANNOTATION_KEY, result);
            } else {
                spanRecorder.recordException(throwable);
            }
        } catch (Throwable th) {
            logger.warn("AFTER. Caused:{}", th.getMessage(), th);
        } finally {
            traceContext.removeTraceObject();
            trace.close();
        }
    }
}
