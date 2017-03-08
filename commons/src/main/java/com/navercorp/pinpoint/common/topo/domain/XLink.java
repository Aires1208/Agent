package com.navercorp.pinpoint.common.topo.domain;

import com.navercorp.pinpoint.common.buffer.AutomaticBuffer;
import com.navercorp.pinpoint.common.buffer.Buffer;
import com.navercorp.pinpoint.common.buffer.OffsetFixedBuffer;

public class XLink {
    private String from;
    private String to;
    private long responseTime;
    private long calls;
    private long errors;
    private boolean hasError = false;


    public XLink(String from, String to ) {
        this.from = from;
        this.to = to;
    }

    public XLink(String from, String to , long responseTime, long errors, long calls) {
        this(from,to);
        this.responseTime = responseTime;
        this.errors = errors;
        this.calls = calls;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public long getCalls() {
        return calls;
    }

    public void setCalls(long calls) {
        this.calls = calls;
    }

    public long getErrors() {
        return errors;
    }

    public void setErrors(long errors) {
        this.errors = errors;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XLink xLink = (XLink) o;

        return from.equals(xLink.from) && to.equals(xLink.to);

    }

    @Override
    public int hashCode() {
        return 1;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("from = ").append(from).append(",");
        builder.append("to = ").append(to);
        return builder.toString();
    }

    public byte[] writeValue() {
        final Buffer buffer = new AutomaticBuffer();
        buffer.putVar(responseTime);
        buffer.putVar(calls);
        buffer.putVar(errors);
        return buffer.getBuffer();
    }

    public int readValue(byte[] bytes, int offset) {
        final Buffer buffer = new OffsetFixedBuffer(bytes, offset);
        this.responseTime = buffer.readVarLong();
        this.calls = buffer.readVarLong();
        this.errors = buffer.readVarLong();
        return buffer.getOffset();
    }

    public String getDisplayMetrics() {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(calls).append("calls/");
        strBuf.append(errors).append("errors/");
        strBuf.append(calls == 0 ? 0 : responseTime/calls).append("ms");

        return strBuf.toString();
    }

    public String getMetrics() {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(calls).append("calls/");
        strBuf.append(errors).append("errors/");
        strBuf.append(calls == 0 ? 0 : responseTime/calls).append("ms");

        return strBuf.toString();
    }

}
