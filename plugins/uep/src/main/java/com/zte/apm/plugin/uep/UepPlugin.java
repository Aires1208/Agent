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
package com.zte.apm.plugin.uep;

import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.bootstrap.instrument.Instrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformCallback;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplateAware;
import com.navercorp.pinpoint.bootstrap.plugin.ApplicationTypeDetector;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;
import com.navercorp.pinpoint.bootstrap.resolver.ConditionProvider;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.zte.apm.plugin.minos.rm.RmPlugin;

import java.security.ProtectionDomain;

/**
 * @author 10116285
 */
public class UepPlugin implements ProfilerPlugin, TransformTemplateAware {
    private TransformTemplate transformTemplate;
    
    @Override
    public void setup(ProfilerPluginSetupContext context) {
        addApplicationTypeDetector(context);
        addTransformers();
    }


    private void addTransformers() {
        transformTemplate.transform("com.zte.ums.uep.pfl.emb.service.EmbServiceImpl", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                // client：发送邮箱消息
                target.addInterceptor("com.zte.apm.plugin.uep.emb.mailbox.EmbServiceImpl_SendNotification_Interceptor");
                // client：发送同步消息
                target.addInterceptor("com.zte.apm.plugin.uep.emb.request.EmbServiceImpl_SyncRequest_Interceptor");
                // client：发送异步消息
                target.addInterceptor("com.zte.apm.plugin.uep.emb.request.EmbServiceImpl_AsyncRequest_Interceptor");
                return target.toBytecode();
            }
        });

        transformTemplate.transform("com.zte.ums.uep.pfl.emb.transport.socket.SocketTransport", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                target.addInterceptor("com.zte.apm.plugin.uep.emb.mailbox.SocketTransport_SendNotify_Interceptor");
                return target.toBytecode();
            }
        });

        transformTemplate.transform("com.zte.ums.uep.pfl.emb.transport.socket.notification.MailBox", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                target.addInterceptor("com.zte.apm.plugin.uep.emb.mailbox.MailBox_DispatchNotify_Interceptor");
                return target.toBytecode();
            }
        });

        // server：收到邮箱消息
        transformTemplate.transform("com.zte.ums.uep.pfl.emb.service.NotificationDispatcher", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                target.addInterceptor("com.zte.apm.plugin.uep.emb.mailbox.NotificationDispatcher_AddNotify_Interceptor");
                return target.toBytecode();
            }
        });

        // server：收到同步消息
        transformTemplate.transform("com.zte.ums.uep.pfl.emb.service.CmdDispatcher", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                target.addInterceptor("com.zte.apm.plugin.uep.emb.request.CmdDispatcher_AddCommand_Interceptor");
                return target.toBytecode();
            }
        });

        // client: 发送F口消息
        transformTemplate.transform("com.zte.ums.uep.pfl.finterface.wsf.FServiceProxy", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                target.addInterceptor("com.zte.apm.plugin.uep.f.FServiceProxy_RequestBatchTryBest_Interceptor");
                target.addInterceptor("com.zte.apm.plugin.uep.f.FServiceProxy_Requestx_Interceptor");
                target.addInterceptor("com.zte.apm.plugin.uep.f.FServiceProxy_RequestxAsyn_Interceptor");
                target.addInterceptor("com.zte.apm.plugin.uep.f.FServiceProxy_RequestxBatch_Interceptor");
                return target.toBytecode();
            }
        });

        // server: 分发F口消息
        transformTemplate.transform("com.zte.ums.uep.pfl.finterface.osf.FServerBrokerBean", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                target.addInterceptor("com.zte.apm.plugin.uep.f.FServerBrokerBean_RequestAsyn_Interceptor");
                target.addInterceptor("com.zte.apm.plugin.uep.f.FServerBrokerBean_RequestBatchxAtomic_Interceptor");
                target.addInterceptor("com.zte.apm.plugin.uep.f.FServerBrokerBean_RequestBatchxTryBest_Interceptor");
                target.addInterceptor("com.zte.apm.plugin.uep.f.FServerBrokerBean_Requestx_Interceptor");
                return target.toBytecode();
            }
        });

        // client：发送MML
        transformTemplate.transform("com.zte.ums.common.mml.wsf.clis.service.AbstractCfgReaderService", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                target.addInterceptor("com.zte.apm.plugin.uep.mml.AbstractCfgReaderService_Execute_Interceptor");
                return target.toBytecode();
            }
        });

        // server：处理MML
        transformTemplate.transform("com.zte.ums.common.mml.osf.clis.cln.wsf.CSPMMLFMessageControlBean", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                target.addInterceptor("com.zte.apm.plugin.uep.mml.CSPMMLFMessageControlBean_Request_Interceptor");
                return target.toBytecode();
            }
        });

        transformTemplate.transform("com.zte.ums.uep.pfl.emb.transport.socket.session.SameJVMCmdSession", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                target.addInterceptor("com.zte.apm.plugin.uep.emb.request.SameJVMCmdSession_sendCommandAndWait_Interceptor");
                return target.toBytecode();
            }
        });

        new RmPlugin(transformTemplate).setup();

    }
    
    /**
     * Pinpoint profiler agent uses this detector to find out the service type of current application.
     */
    private void addApplicationTypeDetector(ProfilerPluginSetupContext context) {
        context.addApplicationTypeDetector(new ApplicationTypeDetector() {
            @Override
            public ServiceType getApplicationType() {
                return UepPluginConstants.UEP_SERVER;
            }

            @Override
            public boolean detect(ConditionProvider provider) {
                return provider.checkMainClass("com.zte.ums.BoostMain");
            }
        });
    }

    @Override
    public void setTransformTemplate(TransformTemplate transformTemplate) {
        this.transformTemplate = transformTemplate;
    }
}
