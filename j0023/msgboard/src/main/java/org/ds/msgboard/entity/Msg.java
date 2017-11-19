package org.ds.msgboard.entity;

import java.sql.Timestamp;

public class Msg {

    private Integer msgId;

    private String msgContent;

    private String msgSender;

    private Timestamp msgSendtime;

    public Msg() {
    }

    public Msg(String msgContent, String msgSender, Timestamp msgSendtime) {
        this.msgContent = msgContent;
        this.msgSender = msgSender;
        this.msgSendtime = msgSendtime;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    public Timestamp getMsgSendtime() {
        return msgSendtime;
    }

    public void setMsgSendtime(Timestamp msgSendtime) {
        this.msgSendtime = msgSendtime;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msgId=" + msgId +
                ", msgContent='" + msgContent + '\'' +
                ", msgSender='" + msgSender + '\'' +
                ", msgSendtime=" + msgSendtime +
                '}';
    }
}
