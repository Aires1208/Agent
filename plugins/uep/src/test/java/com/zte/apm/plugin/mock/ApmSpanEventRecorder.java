package com.zte.apm.plugin.mock;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.ParsingResult;
import com.navercorp.pinpoint.bootstrap.context.SpanEventRecorder;
import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.trace.ServiceType;

/**
 * Created by 10116285 on 16-6-21.
 */
public class ApmSpanEventRecorder implements SpanEventRecorder {
    @Override
    public void recordTime(boolean time) {

    }

    @Override
    public void recordException(Throwable throwable) {

    }

    @Override
    public void recordException(boolean markError, Throwable throwable) {

    }

    @Override
    public void recordApiId(int apiId) {

    }

    @Override
    public void recordApi(MethodDescriptor methodDescriptor) {

    }

    @Override
    public void recordApi(MethodDescriptor methodDescriptor, Object[] args) {

    }

    @Override
    public void recordApi(MethodDescriptor methodDescriptor, Object args, int index) {

    }

    @Override
    public void recordApi(MethodDescriptor methodDescriptor, Object[] args, int start, int end) {

    }

    @Override
    public void recordApiCachedString(MethodDescriptor methodDescriptor, String args, int index) {

    }

    @Override
    public ParsingResult recordSqlInfo(String sql) {
        return null;
    }

    @Override
    public void recordSqlParsingResult(ParsingResult parsingResult) {

    }

    @Override
    public void recordSqlParsingResult(ParsingResult parsingResult, String bindValue) {

    }

    @Override
    public void recordAttribute(AnnotationKey key, String value) {

    }

    @Override
    public void recordAttribute(AnnotationKey key, int value) {

    }

    @Override
    public void recordAttribute(AnnotationKey key, Object value) {

    }

    @Override
    public void recordServiceType(ServiceType serviceType) {

    }

    @Override
    public void recordRpcName(String rpc) {

    }

    @Override
    public void recordDestinationId(String destinationId) {

    }

    @Override
    public void recordEndPoint(String endPoint) {

    }

    @Override
    public void recordNextSpanId(long spanId) {

    }

    @Override
    public void recordAsyncId(int asyncId) {

    }

    @Override
    public void recordNextAsyncId(int asyncId) {

    }

    @Override
    public void recordAsyncSequence(short sequence) {

    }

    @Override
    public Object attachFrameObject(Object frameObject) {
        return null;
    }

    @Override
    public Object getFrameObject() {
        return null;
    }

    @Override
    public Object detachFrameObject() {
        return null;
    }
}
