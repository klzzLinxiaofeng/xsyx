 
DROP TABLE IF EXISTS `oa_paper`;
CREATE TABLE `oa_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(36) DEFAULT NULL COMMENT 'UUID',
  `app_id` int(10) DEFAULT NULL COMMENT 'appid',
  `owner_id` int(11) DEFAULT NULL COMMENT '公文所属的单位，学校',
  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型，1：学校',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `author` varchar(20) DEFAULT NULL COMMENT '发文单位',
  `poster_id` int(11) DEFAULT NULL COMMENT '发布者ID',
  `poster_name` varchar(50) DEFAULT NULL COMMENT '发布者姓名',
  `document_type` varchar(5) DEFAULT NULL COMMENT '公文种类',
  `emergency_level` varchar(5) DEFAULT NULL COMMENT '公文紧急等级',
  `secret_level` varchar(5) DEFAULT NULL COMMENT '公文机密等级',
  `receiver_type` tinyint(4) DEFAULT NULL COMMENT '接收者类型',
  `receiver_count` tinyint(4) DEFAULT NULL COMMENT '接收者实际人数',
  `read_count` tinyint(4) DEFAULT NULL COMMENT '已阅人数',
  `receiver` varchar(1000) DEFAULT NULL COMMENT '接收者名字',
  `content` text COMMENT '正文内容',
  `attachment_uuid` varchar(36) DEFAULT NULL COMMENT '附件文件ID',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

 