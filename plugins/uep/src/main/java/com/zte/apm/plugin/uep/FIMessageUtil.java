package com.zte.apm.plugin.uep;

import com.zte.ums.uep.api.pfl.finterface.FIMessage;

/**
 * Created by 10116285 on 16-6-16.
 */
public class FIMessageUtil {

    public static String getCommandCode(FIMessage message) {
        int commandCode = message.getCommandCode();
        return commandCode + "";
    }
}
