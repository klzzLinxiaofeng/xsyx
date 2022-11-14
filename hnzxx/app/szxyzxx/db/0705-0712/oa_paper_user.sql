 
DROP TABLE IF EXISTS `oa_paper_user`;
CREATE TABLE `oa_paper_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `paper_id` int(11) DEFAULT NULL COMMENT '对应的公文记录',
  `receiver_id` int(10) DEFAULT NULL COMMENT '接收者id',
  `receiver_name` varchar(100) DEFAULT NULL COMMENT '接收者名字',
  `read_status` tinyint(1) DEFAULT NULL COMMENT '是否已阅',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

 