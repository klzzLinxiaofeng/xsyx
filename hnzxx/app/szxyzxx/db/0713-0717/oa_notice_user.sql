 
CREATE TABLE `oa_notice_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receiver_id` int(10) DEFAULT NULL COMMENT '接收者ID',
  `notice_id` int(10) DEFAULT NULL COMMENT '关联的公告ID',
  `receiver_name` varchar(50) DEFAULT NULL COMMENT '接收者姓名或名称（冗余）',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
 