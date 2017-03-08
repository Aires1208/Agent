package com.navercorp.pinpoint.bootstrap.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Created by 10137845 on 2017/2/21.
 */
public class ServerTypeUtils {
    public static boolean isDocker() {
        boolean isDocker = false;
        Process pro;
        Runtime r = Runtime.getRuntime();
        try {
            String[] command = {"/bin/sh", "-c", "cat /proc/1/sched | head -n 1"};
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                String pid = line.substring(line.indexOf("(") + 1, line.indexOf(",")).trim();
                if (!"1".equals(pid)) {
                    isDocker = true;
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            Logger.getLogger(ServerTypeUtils.class.getClass().getName()).warning(e.getMessage());
        }
        return isDocker;
    }
}
