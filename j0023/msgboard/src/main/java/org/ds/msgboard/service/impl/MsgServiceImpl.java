package org.ds.msgboard.service.impl;

import org.ds.msgboard.dao.MsgDao;
import org.ds.msgboard.dto.MsgPage;
import org.ds.msgboard.dto.MsgResult;
import org.ds.msgboard.entity.Msg;
import org.ds.msgboard.exception.MsgException;
import org.ds.msgboard.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgServiceImpl implements MsgService{

    @Autowired
    private MsgDao msgDao;

    @Override
    public boolean addNewMsg(Msg msg) {
        try {
            int flag = msgDao.insertMsg(msg);
            if(flag > 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            throw new MsgException(MsgResult.NET_ERR_MSG);
        }
    }

    @Override
    public MsgPage queryMsgList(Integer page, Integer size) throws MsgException {
        if(page <= 0 || page == null || size <=0 || size == null){
            throw new MsgException(MsgResult.PARAM_ERR_MSG);
        }
        int offset = page*size-size;
        int limit = size;
        try{
            List<Msg> msgList = msgDao.queryMsgList(offset,limit);
            int totalMsgNum = msgDao.queryMsgListCount();
            int totalPageNum = totalMsgNum/size+1;
            if(totalMsgNum % size == 0){
                totalPageNum -= 1;
            }
            MsgPage msgPage = new MsgPage(msgList,page,totalPageNum);
            return msgPage;
        } catch (Exception e){
            throw new MsgException(MsgResult.NET_ERR_MSG);
        }
    }

    @Override
    public List<Msg> queryMsgList() throws MsgException{
        try{
            List<Msg> msgList = msgDao.queryAllMsg();
            return msgList;
        } catch (Exception e){
            throw new MsgException(MsgResult.NET_ERR_MSG);
        }
    }
}
