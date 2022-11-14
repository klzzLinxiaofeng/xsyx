-- ----------------------------
-- Table structure for `oa_summary_read_user`
-- ----------------------------
DROP TABLE IF EXISTS `oa_summary_read_user`;
CREATE TABLE `oa_summary_read_user` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `summary_id` int(11) DEFAULT NULL  COMMENT '纪要id',  
  `user_id` int(10) DEFAULT NULL COMMENT '已经读人id',
  `user_image` varchar(100) DEFAULT NULL COMMENT '已经读人头像',
  `user_name` varchar(32) DEFAULT NULL COMMENT '已经读人名字',
   `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

