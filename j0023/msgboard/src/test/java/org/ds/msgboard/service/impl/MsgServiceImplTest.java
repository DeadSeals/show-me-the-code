package org.ds.msgboard.service.impl;

import org.ds.msgboard.dto.MsgPage;
import org.ds.msgboard.entity.Msg;
import org.ds.msgboard.service.MsgService;
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
public class MsgServiceImplTest {

    @Autowired
    MsgService msgService;

    @Test
    public void addNewMsg() throws Exception {
        Msg msg = new Msg("helloworld","Tom",new Timestamp(System.currentTimeMillis()));
        if(msgService.addNewMsg(msg)){
            System.out.println("--------------add sucess");
        } else {
            System.out.println("--------------add failed");
        }
    }

    @Test
    public void queryMsgList() throws Exception {
        MsgPage msgPage = msgService.queryMsgList(1,10);
        System.out.println("-----------msgPage:"+msgPage);
    }

    @Test
    public void queryMsgList1() throws Exception {
        List<Msg> msgList = msgService.queryMsgList();
        System.out.println("-----------msgList:"+msgList);
    }

}