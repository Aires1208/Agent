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
import com.navercorp.pinpoint.bootstrap.context.SpanRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethods;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.zte.apm.plugin.uep.EMessageUtil;
import com.zte.apm.plugin.uep.UepPluginConstants;
import com.zte.ums.api.common.mml.ppu.entity.common.CmdInputData;
import com.zte.ums.api.common.mml.ppu.service.MmlPPUClisClientService;
import com.zte.ums.api.common.mml.ppu.service.MmlPPUMessageService;
import com.zte.ums.api.common.mml.ppu.service.MmlPPUServerService;
import com.zte.ums.mml.common.tool.MMLLoadToolService;
import com.zte.ums.uep.api.pfl.emb.EMessage;
import com.zte.ums.uep.api.pfl.embmml.EMBMmlService;
import com.zte.ums.uep.api.pfl.finterface.FClientService;

/**
 * com.zte.ums.common.mml.wsf.clis.service.DefaultClisFClientService
 *
 * @author 10116285
 */
@TargetMethods({
        @TargetMethod(name = "execute", paramTypes = {"com.zte.ums.api.common.mml.ppu.entity.common.CmdInputData"}),
        @TargetMethod(name = "execute", paramTypes = {"com.zte.ums.api.common.mml.ppu.entity.common.CmdInputData", "java.awt.Component"}),
})
public class DefaultClisFClientService_Execute_Interceptor implements AroundInterceptor {
    private final PLogger logger = PLoggerFactory.getLogger(DefaultClisFClientService_Execute_Interceptor.class);
    private final MethodDescriptor descriptor;
    private final TraceContext traceContext;

    MmlPPUServerService serverService;
    MmlPPUClisClientService s;
    MmlPPUMessageService m;
    EMBMmlService e;

    public DefaultClisFClientService_Execute_Interceptor(TraceContext traceContext, MethodDescriptor descriptor) {
        this.traceContext = traceContext;
        this.descriptor = descriptor;
    }

    @Override
    public void before(Object target, Object[] args) {
        CmdInputData cmdInputData = (CmdInputData) args[0];

        try {
            FClientService fClient = MMLLoadToolService.getFClientService();
            String serverName = fClient.getSvrIP();
            String clientName = fClient.getClientIP();

            Trace trace = traceContext.newTraceObject();

            SpanRecorder spanRecorder = trace.getSpanRecorder();
            spanRecorder.recordApi(descriptor);
            spanRecorder.recordServiceType(UepPluginConstants.UEP_CLIENT);
            spanRecorder.recordEndPoint(serverName);
            spanRecorder.recordRemoteAddress(clientName);
            spanRecorder.recordAcceptorHost(serverName);
            spanRecorder.recordRpcName("4405");// mml固定CommandCode
        } catch (Throwable th) {
            logger.warn("BEFORE. Caused:{}", th.getMessage(), th);
        }
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        EMessage eMessage = (EMessage) args[0];
        if (EMessageUtil.hasValidPath(eMessage)) {
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
