package com.zte.apm.plugin.uep.emb.mailbox;

import org.junit.Test;

public class MailBox_DispatchNotify_InterceptorTest {

    @Test
    public void testName() throws Exception {
        System.out.println("MailBox_DispatchNotify_InterceptorTest");
//        AgentInformation agentInformation = new AgentInformation("agentId", "appName", 0L, 0, "machineName", "hostIp", UepPluginConstants.UEP_SERVER, "jvmVersion", "agentVersion");
//        TraceContext traceContext = new DefaultTraceContext(agentInformation);
//
//        String clientIp = "clientIp";
//        String serverIp = "serverIp";
//        String rpcName = "rpcName";
//        long nextSpanId = 0L;
//
//        Trace trace = traceContext.newTraceObject();
//        SpanRecorder spanRecorder = trace.getSpanRecorder();
//        MethodDescriptor methodDescriptor = new ApmMethodDescriptor("className", "methodName");
//        spanRecorder.recordApi(methodDescriptor);
//        spanRecorder.recordServiceType(UepPluginConstants.UEP_CLIENT);
//        spanRecorder.recordEndPoint(serverIp);
//        spanRecorder.recordRemoteAddress(clientIp);
//        spanRecorder.recordAcceptorHost(serverIp);
//        spanRecorder.recordRpcName(rpcName);
//
//        SpanEventRecorder spanEventRecorder = trace.traceBlockBegin();
//        spanEventRecorder.recordApi(methodDescriptor);
//        spanEventRecorder.recordServiceType(UepPluginConstants.UEP_CLIENT);
//        spanEventRecorder.recordNextSpanId(nextSpanId);
//        spanEventRecorder.recordEndPoint(serverIp);
//        spanEventRecorder.recordDestinationId(serverIp);
//        spanEventRecorder.recordRpcName(rpcName);
//
//        trace.traceBlockEnd();
//
//        traceContext.removeTraceObject();
    }
}