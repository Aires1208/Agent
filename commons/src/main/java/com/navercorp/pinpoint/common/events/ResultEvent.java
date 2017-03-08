package com.navercorp.pinpoint.common.events;

public class ResultEvent {
    private int eventType;
    private long startTime;
    private long endTime;
    private String objDN;
    private String detail;

    public ResultEvent() {
    }

    public ResultEvent(String dn, int type, long startTimeStamp, long endTimeStamp, String detail) {
        this.objDN = dn;
        this.eventType = type;
        this.startTime = startTimeStamp;
        this.endTime = endTimeStamp;
        this.detail = detail;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getObjDN() {
        return objDN;
    }

    public void setObjDN(String objDN) {
        this.objDN = objDN;
    }

    @Override
    public String toString() {
        return "ResultEvent{" +
                "eventType=" + eventType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", objDN='" + objDN + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultEvent event = (ResultEvent) o;

        if (eventType != event.eventType) return false;
        if (startTime != event.startTime) return false;
        return objDN.equals(event.objDN);

    }

    @Override
    public int hashCode() {
        int result = eventType;
        result = 31 * result + (int) (startTime ^ (startTime >>> 32));
        result = 31 * result + objDN.hashCode();
        return result;
    }
}
