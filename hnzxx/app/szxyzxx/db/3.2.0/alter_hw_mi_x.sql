ALTER TABLE `hw_homework_publish` CHANGE COLUMN `real_micro_list` `real_micro_list` varchar(4000) NOT NULL COMMENT '关联作业的json' after `uuid`;
ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `published_micro_id` `published_micro_id` char(36) NOT NULL COMMENT '关联已发布的作业id' after `modify_date`;
ALTER TABLE `hw_homework_relate` CHANGE COLUMN `publish_micro_lesson_id` `publish_micro_lesson_id` char(36) NOT NULL COMMENT '关联的作业发布对象' after `relate_id`;
ALTER TABLE `hw_homework_relate` CHANGE COLUMN `real_micro_list` `real_micro_list` varchar(4000) DEFAULT NULL COMMENT '关联作业的json' after `publisher_name`;
ALTER TABLE `mi_micro_lesson` ENGINE=`MyISAM` AUTO_INCREMENT=657 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=DYNAMIC CHECKSUM=0 DELAY_KEY_WRITE=0;

