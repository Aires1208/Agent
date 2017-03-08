package com.zte.esight.plugin.zenap.fm;

import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.bootstrap.instrument.Instrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformCallback;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.plugin.ApplicationTypeDetector;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;
import com.navercorp.pinpoint.bootstrap.resolver.ConditionProvider;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.zte.esight.plugin.zenap.ZenapPluginConstants;

import java.security.ProtectionDomain;

/**
 * Created by 10116285 on 16-7-21.
 */
public class FmPlugin implements ProfilerPlugin {
    private final TransformTemplate transformTemplate;

    public FmPlugin(TransformTemplate transformTemplate) {
        this.transformTemplate = transformTemplate;
    }

    @Override
    public void setup(ProfilerPluginSetupContext context) {

        addInterceptor("com.zte.ums.zenap.fm.history.resources.HistoryAlarmQueryResources",
                "com.zte.esight.plugin.zenap.fm.interceptors.HistoryAlarmQueryResources_getPageQueryResponse");
        addInterceptor("com.zte.ums.zenap.fm.history.wrapper.HistoryAlarmQueryWrapper",
                "com.zte.esight.plugin.zenap.fm.interceptors.HistoryAlarmQueryWrapper_execSqlByCond",
                "com.zte.esight.plugin.zenap.fm.interceptors.HistoryAlarmQueryWrapper_queryHistoryAlarm");

        addInterceptor("com.zte.ums.zenap.fm.active.resources.ActiveAlarmResource",
                "com.zte.esight.plugin.zenap.fm.interceptors.ActiveAlarmResource_getActiveAlarms");
        addInterceptor("com.zte.ums.zenap.fm.active.wrapper.ActiveAlarmServiceWrapper",
                "com.zte.esight.plugin.zenap.fm.interceptors.ActiveAlarmServiceWrapper_getActiveData");
        addInterceptor("com.zte.ums.zenap.fm.active.wrapper.ActiveAlarmQueryWrapper",
                "com.zte.esight.plugin.zenap.fm.interceptors.ActiveAlarmQueryWrapper_queryByCond");

    }

    private void addInterceptor(String targetClassName, final String... interceptorClassNames) {
        transformTemplate.transform(targetClassName, new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);

                for (String interceptorClassName : interceptorClassNames) {
                    target.addInterceptor(interceptorClassName);
                }
                return target.toBytecode();
            }
        });
    }

    public void addApplicationTypeDetector(ProfilerPluginSetupContext context) {
        context.addApplicationTypeDetector(new ApplicationTypeDetector() {
            @Override
            public ServiceType getApplicationType() {
                return ZenapPluginConstants.HISTORY_FM_SERVICE;
            }

            @Override
            public boolean detect(ConditionProvider provider) {
                return provider.checkMainClass("com.zte.ums.zenap.fm.history.resources.HistoryAlarmQueryResources");
            }
        });

        context.addApplicationTypeDetector(new ApplicationTypeDetector() {
            @Override
            public ServiceType getApplicationType() {
                return ZenapPluginConstants.ACTIVE_FM_SERVICE;
            }

            @Override
            public boolean detect(ConditionProvider provider) {
                return provider.checkMainClass("com.zte.ums.zenap.fm.zctive.resources.ActiveAlarmResource");
            }
        });
    }
}
