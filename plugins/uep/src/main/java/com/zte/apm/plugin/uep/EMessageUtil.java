package com.zte.apm.plugin.uep;

import com.zte.ums.api.usf.bsf.system.Path;
import com.zte.ums.uep.api.pfl.emb.EMessage;

/**
 * Created by 10116285 on 16-6-14.
 */
public class EMessageUtil {

    public static boolean hasValidPath(EMessage eMessage) {
        Path sourcePath = eMessage.getSourcePath();
        Path destinationPath = eMessage.getDestinationPath();
        if (sourcePath == null || destinationPath == null) {
            return false;
        }
        return !sourcePath.toString().equals(destinationPath.toString());
    }

    public static String getCommandCode(EMessage eMessage) {
        return eMessage.getMessageCode() + "";
    }

    public static String getServerName(EMessage eMessage) {
        Path destinationPath = eMessage.getDestinationPath();
        if (destinationPath == null) {
            return "unknown server";
        }
        return destinationPath.getNodeId();
    }

    public static String getClientName(EMessage eMessage) {
        Path sourcePath = eMessage.getSourcePath();
        if (sourcePath == null) {
            return "unknown client";
        }
        return sourcePath.getNodeId();
    }

    public static boolean isFromRm(EMessage eMessage) {
        Path sourcePath = eMessage.getSourcePath();
        if (sourcePath == null) {
            return false;
        }
        String s = sourcePath.toString();
        if (s.contains("minos-rm")) {
            return true;
        }
        return false;
    }

}
