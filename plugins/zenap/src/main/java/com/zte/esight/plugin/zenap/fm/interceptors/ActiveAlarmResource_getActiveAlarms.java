package com.zte.esight.plugin.zenap.fm.interceptors;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.zte.esight.plugin.zenap.ZenapPluginConstants;

/**
 * Created by 10116285 on 16-7-21.
 */
@TargetMethod(name = "getActiveAlarms", paramTypes = {"java.lang.String", "javax.servlet.http.HttpServletRequest"})
public class ActiveAlarmResource_getActiveAlarms extends SpanEventGenerator {

    public ActiveAlarmResource_getActiveAlarms(TraceContext traceContext, MethodDescriptor descriptor) {
        super(traceContext, descriptor);
    }

    @Override
    protected ServiceType getMethodServiceType() {
        return ZenapPluginConstants.ACTIVE_ALARM_QUERY;
    }
}
