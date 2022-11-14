 
 
CREATE TABLE `oa_notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '公告标题',
  `content` text COMMENT '公告描述',
  `type` varchar(50) DEFAULT NULL COMMENT '公告类型',
  `poster_id` int(10) DEFAULT NULL COMMENT '发布者ID',
  `owner_id` int(10) DEFAULT NULL COMMENT '所属的单位,学校ID',
  `owner_type` char(2) DEFAULT NULL COMMENT '所属的单位或学校类型',
  `poster_name` varchar(50) DEFAULT NULL COMMENT '发布者用户名',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` varchar(36) DEFAULT NULL COMMENT 'UUID',
  `app_id` int(11) DEFAULT NULL COMMENT 'appid',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：还没删除，1：已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
 