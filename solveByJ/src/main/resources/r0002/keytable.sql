CREATE TABLE `testkey` (
  `key_str` varchar(36) NOT NULL COMMENT 'key内容',
  `key_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试数据key表';