package com.zte.apm.plugin.uep.emb.request;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.zte.apm.plugin.mock.ApmMethodDescriptor;
import com.zte.apm.plugin.mock.ApmTraceContext;
import com.zte.apm.plugin.mock.TestUtil;
import com.zte.apm.plugin.uep.UepPluginConstants;
import com.zte.ums.uep.api.pfl.emb.EMessage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmbServiceImpl_SyncRequest_InterceptorTest {

    @Test
    public void should_annotation_right() throws Exception {
        Class<EmbServiceImpl_SyncRequest_Interceptor> aClass = EmbServiceImpl_SyncRequest_Interceptor.class;
        TestUtil.assertScope(aClass, UepPluginConstants.SCOPE_UEP, null);
        TestUtil.assertTargetMethod(aClass, "syncRequest", "com.zte.ums.uep.api.pfl.emb.EMessage");
    }

    @Test
    public void should_not_set_header_when_eMessage_is_null() throws Exception {
        TraceContext traceContext = new ApmTraceContext(0);
        MethodDescriptor descriptor = new ApmMethodDescriptor("EmbServiceImpl", "syncRequest", "EMessage");

        EmbServiceImpl_SyncRequest_Interceptor interceptor = new EmbServiceImpl_SyncRequest_Interceptor(traceContext, descriptor);
        interceptor.before(new Object(), new Object[]{null});
    }

    @Test
    public void should_not_set_header_when_eMessage_sourcePath_equals_destPath() throws Exception {
        TraceContext traceContext = new ApmTraceContext(0);
        MethodDescriptor descriptor = new ApmMethodDescriptor("EmbServiceImpl", "syncRequest", "EMessage");

        EMessage eMessage = TestUtil.makeEMessage(0, "", "");
        EmbServiceImpl_SyncRequest_Interceptor interceptor = new EmbServiceImpl_SyncRequest_Interceptor(traceContext, descriptor);
        interceptor.before(new Object(), new Object[]{eMessage});

        assertEquals(null, eMessage.getMsgHeadProperty(UepPluginConstants.META_TRANSACTION_ID));
    }

    @Test
    public void should_set_header_when_eMessage_is_valid() throws Exception {
        TraceContext traceContext = new ApmTraceContext(0);
        MethodDescriptor descriptor = new ApmMethodDescriptor("EmbServiceImpl", "syncRequest", "EMessage");

        EMessage eMessage = TestUtil.makeEMessage(0, "sourcePath", "destPath");
        EmbServiceImpl_SyncRequest_Interceptor interceptor = new EmbServiceImpl_SyncRequest_Interceptor(traceContext, descriptor);
        interceptor.before(new Object(), new Object[]{eMessage});

        assertNotNull(eMessage.getMsgHeadProperty(UepPluginConstants.META_TRANSACTION_ID));
        assertNotNull(eMessage.getMsgHeadProperty(UepPluginConstants.META_SPAN_ID));
        assertNotNull(eMessage.getMsgHeadProperty(UepPluginConstants.META_PARENT_SPAN_ID));
        assertNotNull(eMessage.getMsgHeadProperty(UepPluginConstants.META_PARENT_APPLICATION_TYPE));
        assertNotNull(eMessage.getMsgHeadProperty(UepPluginConstants.META_PARENT_APPLICATION_NAME));
    }
}