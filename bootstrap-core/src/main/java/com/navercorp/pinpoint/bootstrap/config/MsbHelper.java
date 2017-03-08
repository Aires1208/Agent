package com.navercorp.pinpoint.bootstrap.config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class MsbHelper {

    private static final Logger logger = Logger.getLogger(MsbHelper.class.getName());

    public static final String MSB_ADDR = "MSB_ADDR";
    public static final String DEFAULT_ADDS = "10.62.100.169:10081";
    public static final String NAMESPACE = "OPENPALETTE_NAMESPACE";
    public static final String OPENPALETTE_MSB_IP = "OPENPALETTE_MSB_IP";
    public static final String OPENPALETTE_MSB_PORT = "OPENPALETTE_MSB_PORT";

    private String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.warning("send get request error！" + e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                logger.warning("close BufferedReader error！" + e2);
            }
        }
        return result;
    }


    public int getServicePublishPortOri(String serviceName, int defaultPort) {
        String restUrl = "http://" + getMsbAddsAndPort() + "/api/microservices/v1/services/" + serviceName + "/version/v1";

        String nameSpace = System.getenv(NAMESPACE);
        if (nameSpace != null && !"".equals(nameSpace)) {
            restUrl = restUrl + "?namespace="+nameSpace;
        }

        logger.info("nameSpace ==>"+nameSpace);
        logger.info("msb query url===>"+restUrl);
        System.out.println("nameSpace ==>"+nameSpace);
        System.out.println("msb query url===>"+restUrl);


        String returnValue = sendGet(restUrl);

        if("".equals(returnValue) || returnValue == null){
            logger.warning("getService from msb error！");
            return defaultPort;
        }

        JSONObject jObj = new JSONObject(returnValue);
        String publishPort = (String) jObj.get("publish_port");
        if ("".equals(publishPort) || publishPort == null) {
            logger.warning("getServicePublishPort error！");
            return defaultPort;
        } else {
            return Integer.parseInt(publishPort);
        }
    }


    public int getServicePublishPort(String serviceName, int defaultPort) {
        String restUrl = "http://" + getMsbAddsAndPort() + "/api/msdiscover/v1/services";
        String returnValue = sendGet(restUrl);
        if("".equals(returnValue) || returnValue == null){
            logger.warning("getServices from msb error！");
            return defaultPort;
        }

        String nameSpace = System.getenv(NAMESPACE);
        if("".equals(nameSpace) || nameSpace == null){
            logger.warning("nameSpace is null ,getServices from msb error！");
            return defaultPort;
        }


        JSONArray jsonArray = new JSONArray(returnValue);

        long version = 0;
        int returnPort = 0;

        for(Object object : jsonArray){
            JSONObject jsonObject = (JSONObject)object;
            String serviceNameFromMSB = (String)jsonObject.get("serviceName");
            String nameSpaceFromMSB = (String)jsonObject.get("namespace");
            String publishPort = (String) jsonObject.get("publish_port");

            if(serviceNameFromMSB.startsWith(serviceName) && nameSpaceFromMSB.equals(nameSpace)){
                if(serviceNameFromMSB.lastIndexOf("_v") == -1){
                    return Integer.parseInt(publishPort);
                }else{
                    String end = serviceNameFromMSB.substring(serviceNameFromMSB.lastIndexOf("_v")+2);
                    BigDecimal bigDecimal = new BigDecimal(end);
                    long tempVersion = bigDecimal.longValue();

                    if(tempVersion > version){
                        version = tempVersion;
                        returnPort = Integer.parseInt(publishPort);
                    }
                }
            }
        }
        return returnPort;
    }



    public String getMsbAddsAndPort() {
        String msbAdds = System.getenv(MSB_ADDR);
        String msbIp = System.getenv(OPENPALETTE_MSB_IP);
        String msbPort = System.getenv(OPENPALETTE_MSB_PORT);
        if (msbAdds == null || "".equals(msbAdds)) {
            if(msbIp != null && msbPort != null){
                msbAdds = msbIp+":"+msbPort;
            }else {
                msbAdds = DEFAULT_ADDS;
                logger.warning("get msbAdds error!");
            }
        }
        return msbAdds;
    }


    public String getMsbAdds() {
        String msbAddsAndPort = getMsbAddsAndPort();
        return msbAddsAndPort.substring(0, msbAddsAndPort.indexOf(':'));
    }

    public static void main(String[] args) {
        MsbHelper msbHelper = new MsbHelper();
        System.out.println(msbHelper.getServicePublishPort("IUI_access", 123));
    }

}
