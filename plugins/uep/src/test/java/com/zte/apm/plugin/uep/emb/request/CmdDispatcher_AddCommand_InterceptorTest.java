package com.zte.apm.plugin.uep.emb.request;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.TraceId;
import com.zte.apm.plugin.mock.ApmMethodDescriptor;
import com.zte.apm.plugin.mock.ApmTrace;
import com.zte.apm.plugin.mock.ApmTraceContext;
import com.zte.apm.plugin.mock.TestUtil;
import com.zte.apm.plugin.uep.UepPluginConstants;
import com.zte.ums.uep.api.pfl.emb.EMessage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CmdDispatcher_AddCommand_InterceptorTest {

    @Test
    public void should_annotation_right() throws Exception {
        Class<CmdDispatcher_AddCommand_Interceptor> aClass = CmdDispatcher_AddCommand_Interceptor.class;
        TestUtil.assertScope(aClass, UepPluginConstants.SCOPE_UEP, null);
        TestUtil.assertTargetMethod(aClass, "addCommand", "com.zte.ums.uep.pfl.emb.service.InnerEMessage");
    }

    @Test
    public void should_not_trace_when_eMessage_not_contains_transactionId() throws Exception {
        ApmTraceContext traceContext = new ApmTraceContext(0);
        MethodDescriptor descriptor = new ApmMethodDescriptor("EmbServiceImpl", "asyncRequest", "EMessage");
        CmdDispatcher_AddCommand_Interceptor interceptor = new CmdDispatcher_AddCommand_Interceptor(traceContext, descriptor);
        EMessage eMessage = TestUtil.makeEMessage(0, "sourcePath", "destPath");

        interceptor.before(new Object(), new Object[]{eMessage});

        assertNull(traceContext.currentTraceObject());
    }

    @Test
    public void should_trace_when_eMessage_contains_transactionId() throws Exception {
        ApmTraceContext traceContext = new ApmTraceContext(0);
        MethodDescriptor descriptor = new ApmMethodDescriptor("EmbServiceImpl", "asyncRequest", "EMessage");
        CmdDispatcher_AddCommand_Interceptor interceptor = new CmdDispatcher_AddCommand_Interceptor(traceContext, descriptor);
        EMessage eMessage = TestUtil.makeEMessage(0, "sourcePath", "destPath");
        eMessage.setMsgHeadProperty(UepPluginConstants.META_TRANSACTION_ID, String.valueOf(1));

        interceptor.before(new Object(), new Object[]{eMessage});

        ApmTrace apmTrace = traceContext.currentTraceObject();
        TraceId traceId = apmTrace.getTraceId();
        assertEquals(1, traceId.getTransactionSequence());
    }
}