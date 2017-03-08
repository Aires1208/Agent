package com.zte.apm.plugin.minos.rm;

import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentMethod;
import com.navercorp.pinpoint.bootstrap.instrument.Instrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformCallback;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.ExecutionPolicy;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.InterceptorScope;

import java.security.ProtectionDomain;

/**
 * Created by 10116285 on 16-6-28.
 */
public class RmPlugin {
    private static final String SCOPE_NAME = "MINOS_RM";
    private TransformTemplate transformTemplate;

    public RmPlugin(TransformTemplate transformTemplate) {
        this.transformTemplate = transformTemplate;
    }

    public void setup() {
        transformTemplate.transform("com.zte.ums.minos.rm.emf.listener.RmNotificationListener", new AsyncInitiator());

        transformTemplate.transform("com.zte.ums.minos.rm.common.tasks.SynchronizeOmmTask", new SyncOmmWorker());
        transformTemplate.transform("com.zte.ums.minos.rm.common.tasks.CreateMoTask", new CreateMoWorker());
        transformTemplate.transform("com.zte.ums.minos.rm.common.tasks.UpdateMoTask", new UpdateMoWorker());
        transformTemplate.transform("com.zte.ums.minos.rm.common.tasks.DeleteMoTask", new DeleteMoWorker());
        transformTemplate.transform("com.zte.ums.minos.rm.common.tasks.BatchUpdateTask", new BatchUpdateWorker());
    }


    private static class AsyncInitiator implements TransformCallback {
        @Override
        public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
            InterceptorScope scope = instrumentor.getInterceptorScope(SCOPE_NAME);

            InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
            InstrumentMethod targetMethod = target.getDeclaredMethod("onMessage", "com.zte.ums.uep.api.pfl.emb.EMessage");
            targetMethod.addScopedInterceptor("com.zte.apm.plugin.minos.rm.AsyncInitiatorInterceptor", scope);

            return target.toBytecode();
        }
    }

    private static class SyncOmmWorker implements TransformCallback {

        @Override
        public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
            InterceptorScope scope = instrumentor.getInterceptorScope(SCOPE_NAME);

            InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
            target.addField("com.navercorp.pinpoint.bootstrap.async.AsyncTraceIdAccessor");

            InstrumentMethod constructor = target.getConstructor("com.zte.ums.minos.rm.common.filesync.SyncOmmAction", "java.lang.String");
            constructor.addScopedInterceptor("com.zte.apm.plugin.minos.rm.WorkerConstructorInterceptor", scope, ExecutionPolicy.INTERNAL);

            InstrumentMethod run = target.getDeclaredMethod("runTask", "com.zte.ums.minos.rm.common.agent.NeAgent");
            run.addInterceptor("com.zte.apm.plugin.minos.rm.WorkerRunInterceptor");

            return target.toBytecode();
        }
    }

    private static class CreateMoWorker implements TransformCallback {

        @Override
        public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
            InterceptorScope scope = instrumentor.getInterceptorScope(SCOPE_NAME);

            InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
            target.addField("com.navercorp.pinpoint.bootstrap.async.AsyncTraceIdAccessor");

            InstrumentMethod constructor = target.getConstructor("com.zte.ums.api.minos.common.rm.ppu.cm.MoInfo", "java.lang.String");
            constructor.addScopedInterceptor("com.zte.apm.plugin.minos.rm.WorkerConstructorInterceptor", scope, ExecutionPolicy.INTERNAL);

            InstrumentMethod run = target.getDeclaredMethod("runTask", "com.zte.ums.minos.rm.common.agent.NeAgent");
            run.addInterceptor("com.zte.apm.plugin.minos.rm.WorkerRunInterceptor");

            return target.toBytecode();
        }
    }

    private static class UpdateMoWorker implements TransformCallback {

        @Override
        public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
            InterceptorScope scope = instrumentor.getInterceptorScope(SCOPE_NAME);

            InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
            target.addField("com.navercorp.pinpoint.bootstrap.async.AsyncTraceIdAccessor");

            InstrumentMethod constructor = target.getConstructor("com.zte.ums.api.minos.common.rm.ppu.cm.MoInfo", "java.lang.String");
            constructor.addScopedInterceptor("com.zte.apm.plugin.minos.rm.WorkerConstructorInterceptor", scope, ExecutionPolicy.INTERNAL);

            InstrumentMethod run = target.getDeclaredMethod("runTask", "com.zte.ums.minos.rm.common.agent.NeAgent");
            run.addInterceptor("com.zte.apm.plugin.minos.rm.WorkerRunInterceptor");

            return target.toBytecode();
        }
    }

    private static class DeleteMoWorker implements TransformCallback {

        @Override
        public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
            InterceptorScope scope = instrumentor.getInterceptorScope(SCOPE_NAME);

            InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
            target.addField("com.navercorp.pinpoint.bootstrap.async.AsyncTraceIdAccessor");

            InstrumentMethod constructor = target.getConstructor("com.zte.ums.api.minos.common.rm.ppu.cm.MoInfo", "java.lang.String");
            constructor.addScopedInterceptor("com.zte.apm.plugin.minos.rm.WorkerConstructorInterceptor", scope, ExecutionPolicy.INTERNAL);

            InstrumentMethod run = target.getDeclaredMethod("runTask", "com.zte.ums.minos.rm.common.agent.NeAgent");
            run.addInterceptor("com.zte.apm.plugin.minos.rm.WorkerRunInterceptor");

            return target.toBytecode();
        }
    }

    private static class BatchUpdateWorker implements TransformCallback {

        @Override
        public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
            InterceptorScope scope = instrumentor.getInterceptorScope(SCOPE_NAME);

            InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
            target.addField("com.navercorp.pinpoint.bootstrap.async.AsyncTraceIdAccessor");

            InstrumentMethod constructor = target.getConstructor("com.zte.ums.api.minos.common.rm.ppu.cm.MoOperation[]",
                    "com.zte.ums.api.minos.common.rm.ppu.cm.MoInfo[]", "java.lang.String");
            constructor.addScopedInterceptor("com.zte.apm.plugin.minos.rm.WorkerConstructorInterceptor", scope, ExecutionPolicy.INTERNAL);

            InstrumentMethod run = target.getDeclaredMethod("runTask", "com.zte.ums.minos.rm.common.agent.NeAgent");
            run.addInterceptor("com.zte.apm.plugin.minos.rm.WorkerRunInterceptor");

            return target.toBytecode();
        }
    }
}
