package org.ds.msgboard.dao;

import org.ds.msgboard.entity.Msg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgDaoTest {

    @Autowired
    MsgDao msgDao;

    @Test
    public void insertMsg() throws Exception {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Msg msg = new Msg("你好","wang",time);
        int flag = msgDao.insertMsg(msg);
        if(flag > 0){
            System.out.println("------------insert success");
        }
    }

    @Test
    public void queryMsgList() throws Exception {
        List<Msg> msgList = msgDao.queryMsgList(0,1);
        System.out.println("---------------"+msgList);
    }


    @Test
    public void queryAllMsg() throws Exception {
        List<Msg> msgList = msgDao.queryAllMsg();
        System.out.println("---------------"+msgList);
    }

    @Test
    public void queryMsgListCount() throws Exception {
        int count = msgDao.queryMsgListCount();
        System.out.println("------------count:"+count);
    }

}