CREATE TABLE `xx_data_version` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`user_name` varchar(32) DEFAULT NULL COMMENT '推送目标用户名',
	`object_type` varchar(2) DEFAULT NULL COMMENT '推送类型:1.通知, 2.作业, 3.评语, 4.群聊',
	`data_version` varchar(20) DEFAULT NULL COMMENT '版本号, 时间戳',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT COMMENT='信息推送的版本管理' CHECKSUM=0 DELAY_KEY_WRITE=0