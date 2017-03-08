package com.navercorp.pinpoint.profiler.monitor.server.filesystem;

import com.navercorp.pinpoint.thrift.dto.TFileSystemInfo;
import com.navercorp.pinpoint.thrift.dto.TFileSystems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class OriFileSystemsCollector implements FileSystemsCollector {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TFileSystems collect() {
        Process pro;
        Runtime r = Runtime.getRuntime();
        TFileSystems tFileSystems = new TFileSystems();

        try {
            String command = "df -m";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            List<TFileSystemInfo> fileSystemList = new ArrayList<TFileSystemInfo>();
            while ((line = in.readLine()) != null) {
                String[] fileSystemInfo = line.split("\\s+");
                if(fileSystemInfo.length == 6 ){
                    if(fileSystemInfo[5].startsWith("/")){
                        TFileSystemInfo tFileSystemInfo = new TFileSystemInfo();
                        tFileSystemInfo.setFileSystem(fileSystemInfo[0]);
                        tFileSystemInfo.setMountedOn(fileSystemInfo[5]);
                        tFileSystemInfo.setTotal(Long.parseLong(fileSystemInfo[1]));
                        tFileSystemInfo.setUsed(Long.parseLong(fileSystemInfo[2]));
                        tFileSystemInfo.setFree(Long.parseLong(fileSystemInfo[3]));
                        fileSystemList.add(tFileSystemInfo);
                    }
                }else {
                    logger.warn("get fileSystemInfo error "+ fileSystemInfo.length + "!=6");
                }
            }

            tFileSystems.setTFileSystemInfos(fileSystemList);

            in.close();
            pro.destroy();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return tFileSystems;
    }
}
