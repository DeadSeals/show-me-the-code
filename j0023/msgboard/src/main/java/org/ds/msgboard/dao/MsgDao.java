package org.ds.msgboard.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ds.msgboard.entity.Msg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgDao {

    @Insert("INSERT INTO testmsg (msg_content,msg_sender,msg_sendtime) " +
            "values (#{msgContent},#{msgSender},#{msgSendtime})")
    /**
     * 新增留言
     */
    int insertMsg(Msg msg);

    @Select("SELECT * FROM testmsg ORDER BY msg_sendtime DESC limit #{offset},#{limit}")
    /**
     * 查询留言列表
     */
    List<Msg> queryMsgList(@Param("offset") int offset,@Param("limit") int limit);

    @Select("SELECT * FROM testmsg")
    /**
     * 查询全部留言
     */
    List<Msg> queryAllMsg();

    @Select("SELECT COUNT(*) FROM testmsg")
    /**
     * 查询留言数量
     */
    int queryMsgListCount();
}
