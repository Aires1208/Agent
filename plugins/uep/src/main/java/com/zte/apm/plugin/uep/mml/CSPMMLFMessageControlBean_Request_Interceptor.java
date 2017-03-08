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
package com.zte.apm.plugin.uep.mml;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.SpanId;
import com.navercorp.pinpoint.bootstrap.context.SpanRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.context.TraceId;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethods;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.util.NumberUtils;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.zte.apm.plugin.uep.FIMessageUtil;
import com.zte.apm.plugin.uep.UepPluginConstants;
import com.zte.ums.uep.api.pfl.finterface.FIMessage;


/**
 *com.zte.ums.common.mml.osf.clis.cln.wsf.CSPMMLFMessageControlBean
 *
 * @author 10116285
 */
@TargetMethods({
        @TargetMethod(name = "request", paramTypes = {"com.zte.ums.uep.api.pfl.finterface.FIMessage"}),
        @TargetMethod(name = "request", paramTypes = {"com.zte.ums.uep.api.pfl.finterface.FIMessage", "int", "java.lang.String"})
})
public class CSPMMLFMessageControlBean_Request_Interceptor implements AroundInterceptor {
    private final PLogger logger = PLoggerFactory.getLogger(CSPMMLFMessageControlBean_Request_Interceptor.class.getName());
    private final TraceContext traceContext;
    private final MethodDescriptor descriptor;

    public CSPMMLFMessageControlBean_Request_Interceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
    }

    @Override
    public void before(Object target, Object[] args) {
        FIMessage message = (FIMessage) args[0];

        String transactionId = message.getValue(UepPluginConstants.META_TRANSACTION_ID);
        long spanID = NumberUtils.parseLong(message.getValue(UepPluginConstants.META_SPAN_ID), SpanId.NULL);
        long parentSpanID = NumberUtils.parseLong(message.getValue(UepPluginConstants.META_PARENT_SPAN_ID), SpanId.NULL);
        short parentApplicationType = NumberUtils.parseShort(message.getValue(UepPluginConstants.META_PARENT_APPLICATION_TYPE), ServiceType.UNDEFINED.getCode());
        String parentApplicationName = message.getValue(UepPluginConstants.META_PARENT_APPLICATION_NAME);
        short flags = NumberUtils.parseShort(message.getValue(UepPluginConstants.META_FLAGS), (short) 0);
        String serverName = message.getValue(UepPluginConstants.META_F_SERVERNAME);
        String clientName = message.getValue(UepPluginConstants.META_F_CLIENTNAME);

        if (transactionId == null || transactionId.isEmpty()) {
            return;
        }

        TraceId traceId = traceContext.createTraceId(transactionId, parentSpanID, spanID, flags);
        Trace trace = traceContext.continueTraceObject(traceId);

        SpanRecorder recorder = trace.getSpanRecorder();
        recorder.recordServiceType(UepPluginConstants.UEP_SERVER);
        recorder.recordApi(descriptor, args);
        recorder.recordRpcName(FIMessageUtil.getCommandCode(message));
        recorder.recordEndPoint(serverName);
        recorder.recordAcceptorHost(serverName);
        recorder.recordRemoteAddress(clientName);
        if (!recorder.isRoot()) {
            recorder.recordParentApplication(parentApplicationName, parentApplicationType);
        }
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            logger.info("after(): trace==null");
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
