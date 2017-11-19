package org.ds.msgboard.dto;

import org.ds.msgboard.entity.Msg;

import java.util.List;

public class MsgPage {

    private List<Msg> msgList;

    private Integer pageNum;

    private Integer totalPageNum;

    public MsgPage() {
    }

    public MsgPage(List<Msg> msgList, Integer pageNum, Integer totalPageNum) {
        this.msgList = msgList;
        this.pageNum = pageNum;
        this.totalPageNum = totalPageNum;
    }

    public List<Msg> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Msg> msgList) {
        this.msgList = msgList;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    @Override
    public String toString() {
        return "MsgPage{" +
                "msgList=" + msgList +
                ", pageNum=" + pageNum +
                ", totalPageNum=" + totalPageNum +
                '}';
    }
}
