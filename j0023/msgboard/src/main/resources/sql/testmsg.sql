CREATE TABLE `testmsg` (
  `msg_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `msg_content` varchar(600) NOT NULL COMMENT '留言内容',
  `msg_sender` varchar(30) NOT NULL COMMENT '发送者',
  `msg_sendtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='留言板';