package com.zte.esight.plugin.zenap;

import com.navercorp.pinpoint.common.trace.TraceMetadataProvider;
import com.navercorp.pinpoint.common.trace.TraceMetadataSetupContext;

/**
 * Created by 10116285 on 16-7-21.
 */
public class ZenapMetadataProvider implements TraceMetadataProvider {

    @Override
    public void setup(TraceMetadataSetupContext context) {
        context.addServiceType(ZenapPluginConstants.HISTORY_FM_SERVICE);
        context.addServiceType(ZenapPluginConstants.ACTIVE_FM_SERVICE);
        context.addServiceType(ZenapPluginConstants.HISTORY_ALARM_QUERY);
        context.addServiceType(ZenapPluginConstants.ACTIVE_ALARM_QUERY);
    }

}
