package com.zte.esight.plugin.zenap;

import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.trace.ServiceTypeFactory;
import com.navercorp.pinpoint.common.trace.ServiceTypeProperty;

/**
 * Created by 10116285 on 16-7-21.
 */
public class ZenapPluginConstants {

    public static final ServiceType HISTORY_FM_SERVICE = ServiceTypeFactory.of(9902, "HISTORY FM SERVICE", ServiceTypeProperty.INCLUDE_DESTINATION_ID);
    public static final ServiceType ACTIVE_FM_SERVICE = ServiceTypeFactory.of(9903, "ACTIVE FM SERVICE", ServiceTypeProperty.INCLUDE_DESTINATION_ID);;
    public static final ServiceType HISTORY_ALARM_QUERY = ServiceTypeFactory.of(1901, "HISTORY FM QUERY", ServiceTypeProperty.RECORD_STATISTICS);;
    public static final ServiceType ACTIVE_ALARM_QUERY = ServiceTypeFactory.of(1902, "ACTIVE ALARM QUERY", ServiceTypeProperty.RECORD_STATISTICS);;
}
