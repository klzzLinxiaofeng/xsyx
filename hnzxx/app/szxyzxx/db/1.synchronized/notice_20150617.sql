 
 DROP TABLE IF EXISTS `oa_notice`;
CREATE TABLE `oa_notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT  NULL  COMMENT '公告标题',
  `content` text COMMENT '公告描述',
  `type` varchar(50)  DEFAULT NULL COMMENT '公告类型',
  `user_id` int(10) DEFAULT  NULL COMMENT '发布者ID',
  `user_name` varchar(50) DEFAULT  NULL COMMENT '发布者用户名',  
   `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间', 
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oa_notice_user`;
CREATE TABLE `oa_notice_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键', 
  `user_id` int(10) DEFAULT  NULL COMMENT '用户ID',
  `notice_id` int(10) DEFAULT  NULL COMMENT '关联的公告ID',  
   `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间', 
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 




	 

 

