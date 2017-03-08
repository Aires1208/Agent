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
import com.navercorp.pinpoint.bootstrap.context.SpanId;
import com.navercorp.pinpoint.bootstrap.context.SpanRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.context.TraceId;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.Scope;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethods;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.util.NumberUtils;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.zte.apm.plugin.uep.EMessageUtil;
import com.zte.apm.plugin.uep.UepPluginConstants;
import com.zte.ums.uep.api.pfl.emb.EMessage;

/**
 * com.zte.ums.uep.pfl.emb.service.NotificationDispatcher
 * addNotify(String mailBox, EMBUrl url, InnerEMessage msg)
 *
 * @author 10116285
 */
@Scope(value = UepPluginConstants.SCOPE_UEP)
@TargetMethods({
        @TargetMethod(name = "addNotify", paramTypes = {"java.lang.String", "com.zte.ums.uep.api.pfl.emb.EMBUrl", "com.zte.ums.uep.pfl.emb.service.InnerEMessage"})
})
public class NotificationDispatcher_AddNotify_Interceptor implements AroundInterceptor {
    private final PLogger logger = PLoggerFactory.getLogger(NotificationDispatcher_AddNotify_Interceptor.class.getName());
    private final TraceContext traceContext;
    private final MethodDescriptor descriptor;

    public NotificationDispatcher_AddNotify_Interceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
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

        String transactionId = eMessage.getMsgHeadProperty(UepPluginConstants.META_TRANSACTION_ID);
        long spanID = NumberUtils.parseLong(eMessage.getMsgHeadProperty(UepPluginConstants.META_SPAN_ID), SpanId.NULL);
        long parentSpanID = NumberUtils.parseLong(eMessage.getMsgHeadProperty(UepPluginConstants.META_PARENT_SPAN_ID), SpanId.NULL);
        short parentApplicationType = NumberUtils.parseShort(eMessage.getMsgHeadProperty(UepPluginConstants.META_PARENT_APPLICATION_TYPE), ServiceType.UNDEFINED.getCode());
        String parentApplicationName = eMessage.getMsgHeadProperty(UepPluginConstants.META_PARENT_APPLICATION_NAME);
        short flags = NumberUtils.parseShort(eMessage.getMsgHeadProperty(UepPluginConstants.META_FLAGS), (short) 0);

        if (transactionId == null || transactionId.isEmpty()) {
            return;
        }

        try {
            TraceId traceId = traceContext.createTraceId(transactionId, parentSpanID, spanID, flags);
            Trace trace = traceContext.continueTraceObject(traceId);

            SpanRecorder spanRecorder = trace.getSpanRecorder();
            spanRecorder.recordApi(descriptor, args);
            spanRecorder.recordServiceType(UepPluginConstants.UEP_SERVER);
            spanRecorder.recordRpcName(EMessageUtil.getCommandCode(eMessage));
            spanRecorder.recordEndPoint(EMessageUtil.getServerName(eMessage));
            spanRecorder.recordRemoteAddress(EMessageUtil.getClientName(eMessage));
            spanRecorder.recordAcceptorHost(EMessageUtil.getServerName(eMessage));
            if (!spanRecorder.isRoot()) {
                spanRecorder.recordParentApplication(parentApplicationName, parentApplicationType);
            }
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
            SpanRecorder recorder = trace.getSpanRecorder();
            if (throwable == null) {
                recorder.recordAttribute(UepPluginConstants.UEP_EMB_RESULT_ANNOTATION_KEY, result);
            } else {
                recorder.recordException(throwable);
            }
        } catch (Throwable th) {
            logger.warn("AFTER. Caused:{}", th.getMessage(), th);
        } finally {
            traceContext.removeTraceObject();
            trace.close();
        }
    }
}
