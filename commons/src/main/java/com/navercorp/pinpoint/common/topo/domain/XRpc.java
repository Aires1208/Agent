package com.navercorp.pinpoint.common.topo.domain;

import com.navercorp.pinpoint.common.buffer.AutomaticBuffer;
import com.navercorp.pinpoint.common.buffer.Buffer;
import com.navercorp.pinpoint.common.buffer.OffsetFixedBuffer;

/**
 * Created by ${10183966} on 11/23/16.
 */
public class XRpc {
//    private byte version = 0;
    private String method;
    private int count;
    private int successCount;
    private long min_time;
    private long max_time;
    private long duration;
    private long avg_time;
    private String rpc;

    public long getAvg_time() {
        return avg_time;
    }

    public void setAvg_time(long avg_time) {
        this.avg_time = avg_time;
    }


    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public XRpc() {
    }

    public XRpc(String method, int count, int successCount, long min_time, long max_time, long duration, String rpc) {
        this.method = method;
        this.count = count;
        this.successCount = successCount;
        this.min_time = min_time;
        this.max_time = max_time;
        this.duration = duration;
        this.rpc = rpc;
    }

//    public byte getVersion() {
//        return version;
//    }
//
//    public void setVersion(byte version) {
//        this.version = version;
//    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public long getMin_time() {
        return min_time;
    }

    public void setMin_time(long min_time) {
        this.min_time = min_time;
    }

    public long getMax_time() {
        return max_time;
    }

    public void setMax_time(long max_time) {
        this.max_time = max_time;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }



    public byte[] writeValue() {
        final Buffer buffer = new AutomaticBuffer();
        buffer.put2PrefixedString(method);
        buffer.put(count);
        buffer.put(successCount);
        buffer.put(min_time);
        buffer.put(max_time);
        buffer.put(duration);
        buffer.put2PrefixedString(rpc);
        buffer.put(avg_time);

        return buffer.getBuffer();
    }

    public int readValue(byte[] bytes, int offset) {
        final Buffer buffer = new OffsetFixedBuffer(bytes, offset);
        this.method = buffer.read2PrefixedString();
        this.count = buffer.readInt();
        this.successCount = buffer.readInt();
        this.min_time = buffer.readLong();
        this.max_time = buffer.readLong();
        this.duration = buffer.readLong();
        this.rpc = buffer.read2PrefixedString();
        this.avg_time = buffer.readLong();
        return buffer.getOffset();
    }

    @Override
    public String toString() {
        return "XRpc{" +
                "method='" + method + '\'' +
                ", count=" + count +
                ", successCount=" + successCount +
                ", min_time=" + min_time +
                ", max_time=" + max_time +
                ", duration=" + duration +
                ", rpc='" + rpc + '\'' +
                '}';
    }
}
