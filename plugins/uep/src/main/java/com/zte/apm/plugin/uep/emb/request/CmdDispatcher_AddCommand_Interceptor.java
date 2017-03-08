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
package com.zte.apm.plugin.uep.emb.request;

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
 * com.zte.ums.uep.pfl.emb.service.CmdDispatcher
 *
 * @author 10116285
 */
@Scope(value = UepPluginConstants.SCOPE_UEP)
@TargetMethods({
        @TargetMethod(name = "addCommand", paramTypes = {"com.zte.ums.uep.pfl.emb.service.InnerEMessage"})
})
public class CmdDispatcher_AddCommand_Interceptor implements AroundInterceptor {
    private final PLogger logger = PLoggerFactory.getLogger(CmdDispatcher_AddCommand_Interceptor.class.getName());
    private final TraceContext traceContext;
    private final MethodDescriptor descriptor;

    public CmdDispatcher_AddCommand_Interceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
    }

    @Override
    public void before(Object target, Object[] args) {
        EMessage eMessage = (EMessage) args[0];
        if (!EMessageUtil.hasValidPath(eMessage)) {
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

            SpanRecorder recorder = trace.getSpanRecorder();
            recorder.recordServiceType(UepPluginConstants.UEP_SERVER);
            recorder.recordApi(descriptor, args);
            recorder.recordRpcName(EMessageUtil.getCommandCode(eMessage));
            recorder.recordEndPoint(EMessageUtil.getServerName(eMessage));
            recorder.recordAcceptorHost(EMessageUtil.getServerName(eMessage));
            recorder.recordRemoteAddress(EMessageUtil.getClientName(eMessage));
            if (!recorder.isRoot()) {
                recorder.recordParentApplication(parentApplicationName, parentApplicationType);
            }
        } catch (Throwable th) {
            logger.warn("BEFORE. Caused:{}", th.getMessage(), th);
        }
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        EMessage eMessage = (EMessage) args[0];
        if (!EMessageUtil.hasValidPath(eMessage)) {
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
        }finally {
            traceContext.removeTraceObject();
            trace.close();
        }
    }
}
