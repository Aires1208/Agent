package com.zte.apm.plugin.mock;

import com.zte.ums.api.usf.bsf.system.Path;
import com.zte.ums.uep.api.pfl.emb.EMBUrl;
import com.zte.ums.uep.api.pfl.emb.EMessage;
import com.zte.ums.uep.api.pfl.emb.EMessageObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by 10116285 on 16-6-22.
*/
public class ApmEMessage implements EMessage {
    private int code;
    private Path sourcePath;
    private Path destPath;
    private Map<String, String> header = new HashMap<String, String>();

    public ApmEMessage(int code) {
        this.code = code;
    }

    @Override
    public int getMessageCode() {
        return code;
    }

    @Override
    public String getAppVersion() {
        return null;
    }

    @Override
    public void setAppVersion(String s) {

    }

    @Override
    public EMBUrl getDestinationURL() {
        return null;
    }

    @Override
    public EMBUrl getSourceURL() {
        return null;
    }

    @Override
    public String getSequenceID() {
        return null;
    }

    @Override
    public void addMessageObject(EMessageObject eMessageObject) {

    }

    @Override
    public void addMessageObjects(EMessageObject[] eMessageObjects) {

    }

    @Override
    public List getAllMessageObject() {
        return null;
    }

    @Override
    public String getHandler() {
        return null;
    }

    @Override
    public void setHandler(String s) {

    }

    @Override
    public Path getDestinationPath() {
        return destPath;
    }

    @Override
    public Path getSourcePath() {
        return sourcePath;
    }

    @Override
    public void setDestinationPath(Path path) {
        this.destPath = path;
    }

    @Override
    public void setSourcePath(Path path) {
        this.sourcePath = path;
    }

    @Override
    public void setMML(String s) {

    }

    @Override
    public String getMML() {
        return null;
    }

    @Override
    public void setMsgVersion(int i) {

    }

    @Override
    public int getMsgVersion() {
        return 0;
    }

    @Override
    public void setMarker(String s) {

    }

    @Override
    public String getMarker() {
        return null;
    }

    @Override
    public boolean isBinaryMsg() {
        return false;
    }

    @Override
    public void setBinaryMsg(boolean b) {

    }

    @Override
    public int getTimeout() {
        return 0;
    }

    @Override
    public void setTimeout(int i) {

    }

    @Override
    public void setLimitSize(int i) {

    }

    @Override
    public int getLimitSize() {
        return 0;
    }

    @Override
    public byte[] getBinMsg() {
        return new byte[0];
    }

    @Override
    public void setMsgHeadProperty(String key, String value) {
        this.header.put(key, value);
    }

    @Override
    public String getMsgHeadProperty(String key) {
        return header.get(key);
    }

    @Override
    public void setAsyncResponseCommand(boolean b) {

    }

    @Override
    public void setReceiveMml(boolean b) {

    }

    @Override
    public void setAsynResponseTimeout(int i) {

    }

    @Override
    public int getAsynResoneTimeout() {
        return 0;
    }
}
