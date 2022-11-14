 

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `oa_app_statistics`;
CREATE TABLE `oa_app_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(50) DEFAULT NULL  COMMENT '功能类型', 
   `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
   `owner_id` int(11) DEFAULT NULL COMMENT '公文所属的单位，学校',
   `owner_type` char(6) DEFAULT NULL COMMENT '组的类型，1：学校',
   `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

