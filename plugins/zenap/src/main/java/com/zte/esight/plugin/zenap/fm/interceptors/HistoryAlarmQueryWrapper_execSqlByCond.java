package com.zte.esight.plugin.zenap.fm.interceptors;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.zte.esight.plugin.zenap.ZenapPluginConstants;

/**
 * Created by 10116285 on 16-7-21.
 */
@TargetMethod(name = "execSqlByCond",
        paramTypes = {
                "org.skife.jdbi.v2.DBI", "java.lang.String",
                "int", "int", "java.lang.String"
        })
public class HistoryAlarmQueryWrapper_execSqlByCond extends SpanEventGenerator {

    public HistoryAlarmQueryWrapper_execSqlByCond(TraceContext traceContext, MethodDescriptor descriptor) {
        super(traceContext, descriptor);
    }

    @Override
    protected ServiceType getMethodServiceType() {
        return ZenapPluginConstants.HISTORY_ALARM_QUERY;
    }
}
