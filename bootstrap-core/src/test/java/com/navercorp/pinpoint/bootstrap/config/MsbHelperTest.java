package com.navercorp.pinpoint.bootstrap.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MsbHelperTest {
    @Test
    public void should_return_expectPort_when_publishPort_NotNull_then_call_getServicePublishPort() throws Exception {
        MsbHelper msbHelper = new MsbHelper();
        int actualValue = msbHelper.getServicePublishPort("smartsight-logstash-tcp",29993);
        int expectValue = 40001;
        assertEquals(expectValue,actualValue);
    }


    @Test
    public void should_return_defaultPort_when_publishPort_isNull_then_call_getServicePublishPortOri() throws Exception {
        MsbHelper msbHelper = new MsbHelper();
        int actualValue = msbHelper.getServicePublishPortOri("IUI_access",29993);
        int expectValue = 29993;
        assertEquals(expectValue,actualValue);

    }

    @Test
    public void should_return_defaultPort_when_publishPort_isNull_then_call_getServicePublishPort() throws Exception {
        MsbHelper msbHelper = new MsbHelper();
        int actualValue = msbHelper.getServicePublishPort("smartsight-helloworld-ms",29993);
        int expectValue = 29993;
        assertEquals(expectValue,actualValue);
    }

    @Test
    public void should_defaultPort_when_service_notexist_then_call_getServicePublishPort() throws Exception {
        MsbHelper msbHelper = new MsbHelper();
        int actualValue = msbHelper.getServicePublishPort("notExsitService",29993);
        int expectValue = 29993;
        assertEquals(expectValue,actualValue);

    }

    @Test
    public void getMsbAddsAndPort() throws Exception {

    }


    @Test
    public void should_return_defaultValue_when_systemGetenv_isNull_then_call_getMsbAddsAndPort() throws Exception {
        MsbHelper msbHelper = new MsbHelper();
        String actualValue = msbHelper.getMsbAddsAndPort();
        String expectValue = "10.62.100.184:10081";
        assertEquals(expectValue,actualValue);
    }

    @Test
    public void getMsbAdds() throws Exception {

    }

    @Test
    public void should_return_defaultValue_when_systemGetenv_isNull_then_call_getMsbAdds() throws Exception {
        MsbHelper msbHelper = new MsbHelper();
        String actualValue = msbHelper.getMsbAdds();
        String expectValue = "10.62.100.184";
        assertEquals(expectValue,actualValue);
    }

}