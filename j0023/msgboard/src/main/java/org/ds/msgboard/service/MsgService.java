package org.ds.msgboard.service;

import org.ds.msgboard.dto.MsgPage;
import org.ds.msgboard.entity.Msg;
import org.ds.msgboard.exception.MsgException;

import java.util.List;

public interface MsgService {

    /**
     * 增加新留言
     * @param msg
     * @return
     */
    boolean addNewMsg(Msg msg) throws MsgException;

    /**
     * 分页查询留言
     * @param page
     * @param size
     * @return
     */
    MsgPage queryMsgList(Integer page, Integer size) throws MsgException;

    /**
     * 查询所有留言
     * @return
     */
    List<Msg> queryMsgList() throws MsgException;

}
