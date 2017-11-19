package org.ds.msgboard.controller;

import org.ds.msgboard.dto.MsgPage;
import org.ds.msgboard.dto.MsgResult;
import org.ds.msgboard.entity.Msg;
import org.ds.msgboard.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/msgboard")
public class MsgController {

    @Autowired
    MsgService msgService;

    /**
     * 留言
     * @param sender
     * @param content
     * @return
     */
    @PostMapping("/msglist")
    public MsgResult addNewMsg(@RequestParam("sender") String sender,
                               @RequestParam("content") String content){
        Msg msg = new Msg(content,sender,new Timestamp(System.currentTimeMillis()));
        if(msgService.addNewMsg(msg)){
            return new MsgResult(MsgResult.BLANK_DATA);
        } else {
            return new MsgResult(MsgResult.FAIL_CODE,MsgResult.ADDMSG_FAIL_MSG);
        }
    }

    /**
     * 获取留言页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/msglist")
    public MsgResult getMsgList(@RequestParam("page") Integer page,
                                @RequestParam("size") Integer size){
        MsgPage msgPage = msgService.queryMsgList(page,size);
        return new MsgResult(msgPage);
    }

}
