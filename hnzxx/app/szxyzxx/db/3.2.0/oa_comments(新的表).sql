 

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `oa_comments`;
CREATE TABLE `oa_comments` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `meeting_id` int(11) DEFAULT NULL  COMMENT '会议id', 
  `comment` varchar(255) DEFAULT NULL  COMMENT '评论内容',
  `createuser_id` int(10) DEFAULT NULL COMMENT '发布人id',
  `createuser_image` varchar(100) DEFAULT NULL COMMENT '发布人头像',
  `createname` varchar(32) DEFAULT NULL COMMENT '发布人名字',
   `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

