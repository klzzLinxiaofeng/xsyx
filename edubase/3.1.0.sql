	SET FOREIGN_KEY_CHECKS = 0
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `id` `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键' first
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `id`
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `uuid` `uuid` char(36) NOT NULL COMMENT '做关联的uuid' after `modify_date`
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `real_micro_list` `real_micro_list` varchar(4000) NOT NULL COMMENT '关联微课的json' after `uuid`
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `title` `title` varchar(200) DEFAULT NULL COMMENT '标题' after `real_micro_list`
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `start_date` `start_date` datetime DEFAULT NULL COMMENT '微课开始时间' after `title`
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `finished_date` `finished_date` datetime DEFAULT NULL COMMENT '微课结束时间' after `start_date`
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `published_flag` `published_flag` int(5) NOT NULL COMMENT '发布标记   1 已发布  2 未发布  3 已过期' after `finished_date`
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `publisher_id` `publisher_id` int(10) DEFAULT NULL COMMENT '发布者userId' after `published_flag`
	;ALTER TABLE `em_exam_publish` CHANGE COLUMN `publisher_name` `publisher_name` varchar(20) DEFAULT NULL COMMENT '发布者姓名' after `publisher_id`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `id` `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键' first
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `id`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `published_micro_id` `published_micro_id` char(36) NOT NULL COMMENT '关联已发布的微课id' after `modify_date`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `reviews` `reviews` varchar(550) DEFAULT NULL COMMENT '评语' after `published_micro_id`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `reward` `reward` int(11) DEFAULT NULL COMMENT '奖励  1   一星    2  二星    3  三星' after `reviews`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `finished_flag` `finished_flag` int(10) DEFAULT NULL COMMENT '完成标记   1  已完成    2  未完成   ' after `reward`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `user_id` `user_id` int(10) NOT NULL COMMENT '用户id' after `finished_flag`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `user_name` `user_name` varchar(10) DEFAULT NULL COMMENT '用户名' after `user_id`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `finished_date` `finished_date` datetime DEFAULT NULL COMMENT '完成日期时间' after `user_name`
	;ALTER TABLE `em_exam_published_record` CHANGE COLUMN `student_number` `student_number` varchar(20) DEFAULT NULL COMMENT '全国统一学籍号' after `finished_date`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `id` `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键' first
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `id`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `relate_id` `relate_id` int(10) NOT NULL COMMENT '发布对象id  (可以是单个人或一个组织一个班一个机构)' after `modify_date`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `publish_micro_lesson_id` `publish_micro_lesson_id` char(36) NOT NULL COMMENT '关联的微课发布对象' after `relate_id`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `relate_name` `relate_name` varchar(200) DEFAULT NULL COMMENT '发布对象姓名或名称  (可以是单个人或一个组织一个班一个机构)' after `publish_micro_lesson_id`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `title` `title` varchar(200) DEFAULT NULL COMMENT '标题' after `relate_name`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `start_date` `start_date` datetime DEFAULT NULL COMMENT '微课开始时间' after `title`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `finished_date` `finished_date` datetime DEFAULT NULL COMMENT '微课结束时间' after `start_date`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `publisher_id` `publisher_id` int(10) DEFAULT NULL COMMENT '发布者userId' after `finished_date`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `publisher_name` `publisher_name` varchar(20) DEFAULT NULL COMMENT '发布者姓名' after `publisher_id`
	;ALTER TABLE `em_exam_relate` CHANGE COLUMN `real_micro_list` `real_micro_list` varchar(4000) DEFAULT NULL COMMENT '关联微课的json' after `publisher_name`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `id` `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键' first
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `id`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `micro_id` `micro_id` char(36) NOT NULL COMMENT '关联的微课id' after `modify_date`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `user_id` `user_id` int(10) NOT NULL COMMENT '用户id' after `micro_id`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `user_name` `user_name` varchar(10) DEFAULT NULL COMMENT '用户名' after `user_id`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `finished_flag` `finished_flag` int(10) DEFAULT NULL COMMENT '完成标记   1  已完成    2  未完成   ' after `user_name`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `finished_date` `finished_date` datetime DEFAULT NULL COMMENT '完成日期时间' after `finished_flag`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `play_time` `play_time` int(11) DEFAULT NULL COMMENT '浏览次数' after `finished_date`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `last_play_time` `last_play_time` double(11,0) DEFAULT NULL COMMENT '记录最后一次的播放时间' after `play_time`
	;ALTER TABLE `em_exam_user_record` CHANGE COLUMN `student_number` `student_number` varchar(20) DEFAULT NULL COMMENT '全国统一学籍号' after `last_play_time`
	;ALTER TABLE `em_exam_user_record` ADD COLUMN `publish_lesson_id` char(36) DEFAULT NULL COMMENT '关联已发布的课程uuid' after `student_number`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `id` `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键' first
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `id`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `uuid` `uuid` char(36) NOT NULL COMMENT '做关联的uuid' after `modify_date`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `real_micro_list` `real_micro_list` varchar(4000) NOT NULL COMMENT '关联微课的json' after `uuid`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `title` `title` varchar(200) DEFAULT NULL COMMENT '标题' after `real_micro_list`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `start_date` `start_date` datetime DEFAULT NULL COMMENT '微课开始时间' after `title`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `finished_date` `finished_date` datetime DEFAULT NULL COMMENT '微课结束时间' after `start_date`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `published_flag` `published_flag` int(5) NOT NULL COMMENT '发布标记   1 已发布  2 未发布  3 已过期' after `finished_date`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `publisher_id` `publisher_id` int(10) DEFAULT NULL COMMENT '发布者userId' after `published_flag`
	;ALTER TABLE `hw_homework_publish` CHANGE COLUMN `publisher_name` `publisher_name` varchar(20) DEFAULT NULL COMMENT '发布者姓名' after `publisher_id`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `id` `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键' first
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `id`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `published_micro_id` `published_micro_id` char(36) NOT NULL COMMENT '关联已发布的微课id' after `modify_date`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `reviews` `reviews` varchar(550) DEFAULT NULL COMMENT '评语' after `published_micro_id`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `reward` `reward` int(11) DEFAULT NULL COMMENT '奖励  1   一星    2  二星    3  三星' after `reviews`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `finished_flag` `finished_flag` int(10) DEFAULT NULL COMMENT '完成标记   1  已完成    2  未完成   ' after `reward`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `user_id` `user_id` int(10) NOT NULL COMMENT '用户id' after `finished_flag`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `user_name` `user_name` varchar(10) DEFAULT NULL COMMENT '用户名' after `user_id`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `finished_date` `finished_date` datetime DEFAULT NULL COMMENT '完成日期时间' after `user_name`
	;ALTER TABLE `hw_homework_published_record` CHANGE COLUMN `student_number` `student_number` varchar(20) DEFAULT NULL COMMENT '全国统一学籍号' after `finished_date`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `id` `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键' first
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `id`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `relate_id` `relate_id` int(10) NOT NULL COMMENT '发布对象id  (可以是单个人或一个组织一个班一个机构)' after `modify_date`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `publish_micro_lesson_id` `publish_micro_lesson_id` char(36) NOT NULL COMMENT '关联的微课发布对象' after `relate_id`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `relate_name` `relate_name` varchar(200) DEFAULT NULL COMMENT '发布对象姓名或名称  (可以是单个人或一个组织一个班一个机构)' after `publish_micro_lesson_id`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `title` `title` varchar(200) DEFAULT NULL COMMENT '标题' after `relate_name`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `start_date` `start_date` datetime DEFAULT NULL COMMENT '微课开始时间' after `title`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `finished_date` `finished_date` datetime DEFAULT NULL COMMENT '微课结束时间' after `start_date`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `publisher_id` `publisher_id` int(10) DEFAULT NULL COMMENT '发布者userId' after `finished_date`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `publisher_name` `publisher_name` varchar(20) DEFAULT NULL COMMENT '发布者姓名' after `publisher_id`
	;ALTER TABLE `hw_homework_relate` CHANGE COLUMN `real_micro_list` `real_micro_list` varchar(4000) DEFAULT NULL COMMENT '关联微课的json' after `publisher_name`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `id` `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键' first
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `id`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `micro_id` `micro_id` char(36) NOT NULL COMMENT '关联的微课id' after `modify_date`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `user_id` `user_id` int(10) NOT NULL COMMENT '用户id' after `micro_id`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `user_name` `user_name` varchar(10) DEFAULT NULL COMMENT '用户名' after `user_id`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `finished_flag` `finished_flag` int(10) DEFAULT NULL COMMENT '完成标记   1  已完成    2  未完成   ' after `user_name`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `finished_date` `finished_date` datetime DEFAULT NULL COMMENT '完成日期时间' after `finished_flag`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `play_time` `play_time` int(11) DEFAULT NULL COMMENT '浏览次数' after `finished_date`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `last_play_time` `last_play_time` double(11,0) DEFAULT NULL COMMENT '记录最后一次的播放时间' after `play_time`
	;ALTER TABLE `hw_homework_user_record` CHANGE COLUMN `student_number` `student_number` varchar(20) DEFAULT NULL COMMENT '全国统一学籍号' after `last_play_time`
	;ALTER TABLE `hw_homework_user_record` ADD COLUMN `publish_lesson_id` char(36) DEFAULT NULL COMMENT '关联已发布的课程uuid' after `student_number`
	;ALTER TABLE `jc_textbook` ADD COLUMN `writer_description` varchar(500) DEFAULT NULL COMMENT '书的作者的简介' after `is_delete`
	;ALTER TABLE `jc_textbook` Drop COLUMN `writer_id`
	;ALTER TABLE `jc_textbook` CHANGE COLUMN `old_name` `old_name` varchar(100) DEFAULT NULL COMMENT '原书名' after `writer_description`
	;ALTER TABLE `jc_textbook` CHANGE COLUMN `subtitle` `subtitle` varchar(50) DEFAULT NULL COMMENT '副标题' after `old_name`
	;ALTER TABLE `jc_textbook` CHANGE COLUMN `price` `price` varchar(10) DEFAULT NULL COMMENT '价格' after `subtitle`
	;ALTER TABLE `jc_textbook` CHANGE COLUMN `press_name` `press_name` varchar(45) DEFAULT NULL COMMENT '出版社' after `price`
	;ALTER TABLE `jc_textbook` CHANGE COLUMN `type` `type` varchar(3) DEFAULT NULL COMMENT '书籍类型' after `press_name`
	;ALTER TABLE `jc_textbook` CHANGE COLUMN `binding` `binding` varchar(20) DEFAULT NULL COMMENT '装帧' after `type`
	;ALTER TABLE `jc_textbook` CHANGE COLUMN `pages` `pages` varchar(5) DEFAULT NULL COMMENT '页数' after `binding`
	;ALTER TABLE `jc_textbook` CHANGE COLUMN `word_count` `word_count` varchar(15) DEFAULT NULL COMMENT '字数' after `pages`
	;ALTER TABLE `jc_textbook` CHANGE COLUMN `is_hidden` `is_hidden` bit(1) NOT NULL COMMENT '是否隐藏 0===隐藏 1==显示' after `word_count`
	;ALTER TABLE `jc_textbook` ADD COLUMN `code` int(10) NOT NULL DEFAULT '0' COMMENT '教材代码' after `is_hidden`
	;ALTER TABLE `jc_textbook_catalog` ADD COLUMN `code` int(10) NOT NULL DEFAULT '0' COMMENT '代码' after `modify_time`
	;ALTER TABLE `jc_textbook_catalog` ADD COLUMN `list_order` int(10) DEFAULT '0' COMMENT '排序问题' after `code`
	;ALTER TABLE `mi_micro_lesson` ENGINE=`MyISAM` AUTO_INCREMENT=1244 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=DYNAMIC CHECKSUM=0 DELAY_KEY_WRITE=0
	;ALTER TABLE `mi_micro_lesson_publish` CHANGE COLUMN `real_micro_list` `real_micro_list` varchar(4000) NOT NULL COMMENT '关联微课的json' after `uuid`
	;ALTER TABLE `mi_micro_lesson_relate` CHANGE COLUMN `real_micro_list` `real_micro_list` varchar(4000) DEFAULT NULL COMMENT '关联微课的json' after `publisher_name`
	;ALTER TABLE `mi_micro_published_record` CHANGE COLUMN `reviews` `reviews` varchar(550) DEFAULT NULL COMMENT '评语' after `published_micro_id`
	;ALTER TABLE `mi_micro_user_record` ADD COLUMN `publish_lesson_id` char(36) DEFAULT NULL COMMENT '关联已发布的课程uuid' after `student_number`
	;CREATE TABLE `oa_accept_repari` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`repari_id` int(10) NOT NULL COMMENT '维修申请',
	`accepter_id` int(10) DEFAULT NULL COMMENT '维修人ID',
	`accepter_name` varchar(50) DEFAULT NULL COMMENT '维修人姓名',
	`phone` varchar(15) DEFAULT NULL COMMENT '维修人联系电话',
	`accept_date` datetime DEFAULT NULL COMMENT '维修时间',
	`appraise` int(2) DEFAULT NULL COMMENT '评价星级',
	`remark` varchar(200) DEFAULT NULL COMMENT '备注',
	`picture` varchar(200) DEFAULT NULL COMMENT '图片',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=10 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `oa_apply_card` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`title` varchar(100) DEFAULT NULL COMMENT '申请标题',
	`card_id` int(10) DEFAULT NULL COMMENT '所申请车辆',
	`proposer` int(10) DEFAULT NULL COMMENT '申请人',
	`phone` varchar(15) DEFAULT NULL COMMENT '联系电话',
	`begin_date` datetime DEFAULT NULL COMMENT '使用开始时间',
	`end_date` datetime DEFAULT NULL COMMENT '使用结束时间',
	`release_date` datetime DEFAULT NULL COMMENT '发布时间',
	`audit_user` int(10) DEFAULT NULL COMMENT '审批人',
	`audit_status` varchar(10) DEFAULT NULL COMMENT '审批状态',
	`remark` varchar(200) DEFAULT NULL COMMENT '备注',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
	`school_id` int(10) NOT NULL COMMENT '学校ID',
	`department_id` int(10) DEFAULT NULL COMMENT '部门ID',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=14 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `oa_apply_repair` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`shcool_id` varchar(10) NOT NULL COMMENT '学校ID',
	`proposer_id` int(10) DEFAULT NULL,
	`proposer_name` varchar(50) DEFAULT NULL,
	`title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
	`picture` varchar(200) DEFAULT NULL COMMENT '图片',
	`whole_picture` varchar(200) DEFAULT NULL COMMENT '修完后评价上传',
	`details` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '详情',
	`place` varchar(200) DEFAULT NULL COMMENT '地点',
	`contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
	`phone` varchar(15) DEFAULT NULL COMMENT '联系电话',
	`appointment_date` datetime DEFAULT NULL COMMENT '预约时间',
	`status` varchar(2) DEFAULT NULL COMMENT '维修状态',
	`remark` varchar(200) DEFAULT NULL COMMENT '备注',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=17 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `oa_applycard_department_count` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`school_id` int(10) DEFAULT NULL COMMENT '学校ID',
	`department_id` int(10) DEFAULT NULL COMMENT '部门ID',
	`number` int(10) DEFAULT NULL COMMENT '人数',
	`create_date` datetime DEFAULT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	`isdelete` bit(1) DEFAULT b'0' COMMENT '是否删除',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=4 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;ALTER TABLE `oa_meeting` ADD COLUMN `fanwei` int(11) DEFAULT NULL COMMENT '可见范围' after `modify_date`
	;ALTER TABLE `oa_meeting` ADD COLUMN `meeting_num` int(11) DEFAULT NULL COMMENT '参会人数' after `fanwei`
	;ALTER TABLE `oa_notice` ADD COLUMN `poster_id` int(10) DEFAULT NULL COMMENT '发布者ID' after `type`
	;ALTER TABLE `oa_notice` ADD COLUMN `owner_id` int(10) DEFAULT NULL COMMENT '所属的单位,学校ID' after `poster_id`
	;ALTER TABLE `oa_notice` ADD COLUMN `owner_type` char(2) DEFAULT NULL COMMENT '所属的单位或学校类型' after `owner_id`
	;ALTER TABLE `oa_notice` ADD COLUMN `poster_name` varchar(50) DEFAULT NULL COMMENT '发布者用户名' after `owner_type`
	;ALTER TABLE `oa_notice` Drop COLUMN `user_id`
	;ALTER TABLE `oa_notice` Drop COLUMN `school_id`
	;ALTER TABLE `oa_notice` Drop COLUMN `user_name`
	;ALTER TABLE `oa_notice` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `poster_name`
	;ALTER TABLE `oa_notice` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `oa_notice` ADD COLUMN `uuid` varchar(36) DEFAULT NULL COMMENT 'UUID' after `modify_date`
	;ALTER TABLE `oa_notice` ADD COLUMN `receiver_type` int(4) DEFAULT NULL after `uuid`
	;ALTER TABLE `oa_notice` ADD COLUMN `app_id` int(11) DEFAULT NULL COMMENT 'appid' after `receiver_type`
	;ALTER TABLE `oa_notice` ADD COLUMN `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：还没删除，1：已经删除' after `app_id`
	;CREATE TABLE `oa_notice_img` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`img_uuid` varchar(36) DEFAULT NULL COMMENT '图片ID',
	`img_url` varchar(150) DEFAULT NULL COMMENT '图片路径',
	`notice_id` int(10) DEFAULT NULL COMMENT '关联的公告ID',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;ALTER TABLE `oa_notice_user` ADD COLUMN `receiver_id` int(10) DEFAULT NULL COMMENT '接收者ID' after `id`
	;ALTER TABLE `oa_notice_user` Drop COLUMN `user_id`
	;ALTER TABLE `oa_notice_user` CHANGE COLUMN `notice_id` `notice_id` int(10) DEFAULT NULL COMMENT '关联的公告ID' after `receiver_id`
	;ALTER TABLE `oa_notice_user` ADD COLUMN `receiver_name` varchar(50) DEFAULT NULL COMMENT '接收者姓名或名称（冗余）' after `notice_id`
	;ALTER TABLE `oa_notice_user` Drop COLUMN `teach_name`
	;ALTER TABLE `oa_notice_user` CHANGE COLUMN `create_date` `create_date` datetime NOT NULL COMMENT '创建时间' after `receiver_name`
	;ALTER TABLE `oa_notice_user` CHANGE COLUMN `modify_date` `modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' after `create_date`
	;ALTER TABLE `oa_notice_user` ADD COLUMN `is_deleted` tinyint(1) DEFAULT NULL after `modify_date`
	;CREATE TABLE `oa_schedule` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`uuid` varchar(36) DEFAULT NULL COMMENT 'uuid',
	`app_id` int(10) DEFAULT NULL COMMENT 'app.id',
	`owner_id` int(11) DEFAULT NULL COMMENT '所属的单位，学校',
	`owner_type` char(6) DEFAULT NULL COMMENT '组的类型，1：学校',
	`poster_id` int(11) DEFAULT NULL COMMENT '发布者ID',
	`poster_name` varchar(50) DEFAULT NULL COMMENT '发布者姓名',
	`plan_start_time` varchar(25) DEFAULT NULL COMMENT '日程安排开始时间',
	`plan_finish_time` varchar(25) DEFAULT NULL COMMENT '日程安排结束时间',
	`share_to` varchar(2) DEFAULT NULL COMMENT '共享对象范围',
	`rank` int(10) DEFAULT NULL COMMENT '优先等级,0=普通，1=重要',
	`content` varchar(500) DEFAULT NULL COMMENT '正文内容',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：还没删除，1：已经删除',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `oa_schedule_remind` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`schedule_id` int(11) DEFAULT NULL COMMENT '相关日程ID',
	`poster_id` int(10) DEFAULT NULL COMMENT '创建人id',
	`enabled` tinyint(1) DEFAULT NULL COMMENT '是否启用提醒功能',
	`start_time` varchar(25) DEFAULT NULL COMMENT '开始提醒时间',
	`repeat_interval` int(4) DEFAULT NULL COMMENT '每次提醒间隔（分钟）',
	`repeat_time` int(4) DEFAULT NULL COMMENT '提醒次数',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：还没删除，1：已经删除',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `oa_schedule_user` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`schedule_id` int(11) DEFAULT NULL COMMENT '相关日程ID',
	`receiver_id` int(10) DEFAULT NULL COMMENT '接收者id',
	`receiver_name` varchar(100) DEFAULT NULL COMMENT '接收者名字',
	`receiver_type` tinyint(1) DEFAULT NULL COMMENT '接收者类型',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：还没删除，1：已经删除',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `oa_vehicle_management` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`uuid` varchar(36) DEFAULT NULL COMMENT 'UUID',
	`school_id` int(10) DEFAULT NULL COMMENT '学校ID',
	`full_load` int(10) DEFAULT NULL COMMENT '满载人数',
	`plate_number` varchar(20) DEFAULT NULL COMMENT '车牌号',
	`card_name` varchar(100) DEFAULT NULL COMMENT '车名',
	`purchase_data` datetime DEFAULT NULL COMMENT '购置日期',
	`service_condition` varchar(50) DEFAULT NULL COMMENT '使用状况',
	`cover` varchar(200) DEFAULT NULL COMMENT '封面',
	`model` varchar(10) DEFAULT NULL COMMENT '汽车类型',
	`remark` varchar(200) DEFAULT NULL COMMENT '备注',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=12 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;ALTER TABLE `pj_exam_team_subject` ADD COLUMN `code` varchar(50) NOT NULL COMMENT '考试日程代码' after `school_id`
	;ALTER TABLE `pj_student_aid` CHANGE COLUMN `aid_form` `aid_form` varchar(50) NOT NULL COMMENT '助困形式' after `poverty_causes`
	;ALTER TABLE `pj_student_punish` CHANGE COLUMN `punish_type` `punish_type` varchar(50) NOT NULL COMMENT '处分类型' after `modify_date`
	;ALTER TABLE `pj_subject_teacher` ADD COLUMN `stage_code` char(36) NOT NULL COMMENT '学段code' after `school_id`
	;ALTER TABLE `pj_subject_teacher` Drop COLUMN `grade_id`
	;ALTER TABLE `pj_team_teacher` CHANGE COLUMN `is_delete` `is_delete` bit(1) DEFAULT NULL COMMENT '删除标识' after `create_date`
	;CREATE TABLE `pj_textbook_manager` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`stage_code` varchar(2) NOT NULL COMMENT '使用学段',
	`grade_code` varchar(5) NOT NULL COMMENT '年级',
	`subject_code` varchar(10) NOT NULL COMMENT '适用学科',
	`volumn` varchar(2) NOT NULL COMMENT '册次，卷',
	`version` varchar(20) NOT NULL COMMENT '版本',
	`name` varchar(100) NOT NULL COMMENT '名称',
	`teacher_id` varchar(45) DEFAULT NULL COMMENT '任课教师id',
	`publisher_id` int(11) DEFAULT NULL COMMENT '版本类型，如人教版',
	`isbn` varchar(20) DEFAULT NULL COMMENT 'isbn 国际标准书号（International Standard Book Number），简称ISBN，是专门为识别图书等文献而设计的国际编号',
	`create_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
	`price` varchar(10) DEFAULT NULL COMMENT '价格',
	`type` varchar(3) DEFAULT NULL COMMENT '书籍类型',
	`is_delete` bit(1) NOT NULL COMMENT '是否删除，0==不删除，1===删除',
	`count` varchar(12) NOT NULL DEFAULT '0' COMMENT '数量',
	`count_type` varchar(3) DEFAULT NULL COMMENT '数量类型-手工填写1，还是自动从班级获取0',
	`add_type` varchar(3) NOT NULL DEFAULT '0' COMMENT '添加类型，默认为从基础教材表获取 0 ，现在不存在手动添加 1',
	`school_id` int(11) NOT NULL COMMENT '学校',
	`school_year` varchar(45) NOT NULL COMMENT '学年',
	`term_code` varchar(50) NOT NULL COMMENT '学期',
	`grade_id` varchar(45) NOT NULL COMMENT '年级id',
	`team_id` varchar(45) NOT NULL COMMENT '班级id',
	`teacher_name` varchar(45) DEFAULT NULL COMMENT '任课教师姓名',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=2 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT COMMENT='学校教材管理' CHECKSUM=0 DELAY_KEY_WRITE=0
	;Drop TABLE `pj_uploadinformation_temp`
	;CREATE TABLE `pj_upLoadInformation_temp` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主健ID',
	`school_id` int(10) DEFAULT NULL COMMENT '所属学校',
	`name` varchar(50) DEFAULT NULL COMMENT '姓名',
	`role` varchar(4) DEFAULT NULL COMMENT '角色',
	`sex` varchar(2) DEFAULT NULL COMMENT '性别',
	`id_card_number` varchar(22) DEFAULT NULL COMMENT '身份证号',
	`student_number` varchar(20) DEFAULT NULL COMMENT '全国统一学籍号',
	`telephone` varchar(50) DEFAULT NULL COMMENT '电话',
	`userType` varchar(10) DEFAULT NULL COMMENT '成员类型 1：学生 2：教师',
	`state` varchar(5) DEFAULT NULL COMMENT '状态 1：成功 2：失败',
	`message` varchar(100) DEFAULT NULL COMMENT '成功与否信息',
	`creater` varchar(50) DEFAULT NULL COMMENT '创建人',
	`create_date` datetime DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=3159 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;ALTER TABLE `res_resource` CHANGE COLUMN `thumbnail_url` `thumbnail_url` varchar(1000) DEFAULT NULL COMMENT '缩略图冗余' after `comment_count`
	;CREATE TABLE `xw_hq_canteen` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`school_id` int(10) NOT NULL COMMENT '所属学校',
	`code` char(36) NOT NULL COMMENT '食堂编号',
	`name` varchar(50) NOT NULL COMMENT '食堂名称',
	`leader` varchar(50) NOT NULL COMMENT '负责人',
	`run_time` varchar(50) NOT NULL COMMENT '营业时间',
	`floor_code` char(36) NOT NULL COMMENT '大楼编号',
	`floor_name` varchar(50) NOT NULL COMMENT '大楼名称',
	`layer_number` int(3) NOT NULL COMMENT '大楼楼层数',
	`remark` varchar(50) DEFAULT NULL COMMENT '备注',
	`is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=11 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_canteen_goods` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`school_id` int(10) NOT NULL COMMENT '所属学校',
	`code` char(36) NOT NULL COMMENT '商品编号',
	`name` varchar(50) NOT NULL COMMENT '商品名称',
	`enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
	`is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=13 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_canteen_sign_item` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '签收明细单id',
	`school_id` int(10) NOT NULL COMMENT '所属学校',
	`order_id` int(10) NOT NULL COMMENT 'xw_hq_canteen_sign_order.id 签收单ID',
	`goods_code` char(36) NOT NULL COMMENT '商品编号',
	`goods_name` varchar(50) NOT NULL COMMENT '商品名称',
	`price` decimal(10,2) NOT NULL COMMENT '商品单价',
	`total_count` int(10) NOT NULL COMMENT '商品总数量',
	`is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=7 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_canteen_sign_order` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`school_id` int(10) NOT NULL COMMENT '所属学校',
	`supply_id` int(10) NOT NULL COMMENT '供应商id xw_hq_canteen_supply.id',
	`canteen_code` char(36) NOT NULL COMMENT '餐厅编号',
	`supply_name` varchar(50) NOT NULL COMMENT '提供商名称',
	`sign_person` varchar(20) NOT NULL COMMENT '签收人',
	`sign_date` datetime NOT NULL COMMENT '签收日期',
	`is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=15 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_canteen_store` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`school_id` int(10) NOT NULL COMMENT '所属学校',
	`canteen_code` char(36) NOT NULL COMMENT '食堂编号',
	`goods_code` char(36) NOT NULL COMMENT '商品编号',
	`store_num` int(10) NOT NULL COMMENT '库存数量',
	`is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=8 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_canteen_supply` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`school_id` int(10) NOT NULL COMMENT '所属学校',
	`name` varchar(50) NOT NULL COMMENT '供货商名称',
	`telephone` varchar(20) NOT NULL COMMENT '联系方式',
	`address` varchar(100) NOT NULL COMMENT '联系地址',
	`remark` varchar(50) DEFAULT NULL COMMENT '备注',
	`is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=12 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_canteen_take_item` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '签收明细单id',
	`order_id` int(10) NOT NULL COMMENT 'xw_hq_canteen_take_order.id',
	`goods_code` char(36) NOT NULL COMMENT '商品编号',
	`goods_name` varchar(10) NOT NULL COMMENT '商品名称',
	`take_count` int(10) NOT NULL COMMENT '领取数量',
	`take_person` varchar(10) NOT NULL COMMENT '领用人',
	`is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_canteen_take_order` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '领用单id',
	`school_id` int(10) NOT NULL COMMENT '所属学校',
	`canteen_code` char(36) NOT NULL COMMENT '餐厅编号',
	`sign_person` varchar(20) NOT NULL COMMENT '签收人',
	`is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
	`create_date` datetime NOT NULL COMMENT '创建时间',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_health_clinic` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`school_id` int(10) NOT NULL COMMENT '所在学校',
	`code` char(36) NOT NULL COMMENT '卫生室编号',
	`name` varchar(50) NOT NULL COMMENT '卫生室名称',
	`principal` varchar(50) DEFAULT NULL COMMENT '负责人',
	`floor_id` int(10) NOT NULL COMMENT '所属大楼xw_hq_floor.id',
	`storey` tinyint(4) NOT NULL COMMENT '所在楼层',
	`room` varchar(50) DEFAULT NULL COMMENT '所在房间',
	`remark` varchar(200) DEFAULT NULL COMMENT '备注',
	`is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
	`create_date` datetime NOT NULL COMMENT '创建日期',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=4 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT COMMENT='校务后勤卫生管理--卫生室管理' CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_health_disease_report` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`school_id` int(10) NOT NULL COMMENT '所在学校',
	`report_date` datetime NOT NULL COMMENT '报告时间',
	`report_unit` varchar(50) NOT NULL COMMENT '上报单位',
	`report_phone` varchar(50) DEFAULT NULL COMMENT '上报电话',
	`report_person` varchar(50) DEFAULT NULL COMMENT '接报人',
	`disease` varchar(50) NOT NULL COMMENT '病名',
	`attack_site` varchar(50) DEFAULT NULL COMMENT '发病地点',
	`attack_number` int(5) DEFAULT NULL COMMENT '发病人数',
	`begin_date` datetime NOT NULL COMMENT '首例时间',
	`symptom` varchar(50) DEFAULT NULL COMMENT '主要症状',
	`handle` varchar(50) DEFAULT NULL COMMENT '疫情处理',
	`reporter` varchar(50) DEFAULT NULL COMMENT '报告人',
	`remark` varchar(200) DEFAULT NULL COMMENT '备注',
	`is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
	`create_date` datetime NOT NULL COMMENT '创建日期',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=3 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT COMMENT='校务后勤卫生管理--传染病疫情报告登记' CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_health_medicine_inspection` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`school_id` int(10) NOT NULL COMMENT '所在学校',
	`clinic_id` int(10) NOT NULL COMMENT '卫生室xw_hq_clinic.id',
	`examine_date` datetime NOT NULL COMMENT '检查日期',
	`name` varchar(50) NOT NULL COMMENT '药品名称',
	`stock` int(10) NOT NULL COMMENT '库存量',
	`unit` char(4) NOT NULL COMMENT '单位',
	`state` char(4) NOT NULL COMMENT '药品状态',
	`is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
	`create_date` datetime NOT NULL COMMENT '创建日期',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=3 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT COMMENT='校务后勤卫生管理--药品检查情况' CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_health_student_check` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`school_id` int(10) NOT NULL COMMENT '所在学校',
	`team_id` int(10) NOT NULL COMMENT '学生所在班级pj_team.id',
	`student_id` int(10) NOT NULL COMMENT '学生pj_student.id',
	`check_date` datetime NOT NULL COMMENT '晨检日期',
	`symptom` varchar(50) NOT NULL COMMENT '主要症状',
	`is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
	`create_date` datetime NOT NULL COMMENT '创建日期',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=7 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT COMMENT='校务后勤卫生管理--学生晨检症状' CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xw_hq_health_student_healing` (
	`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`school_id` int(10) NOT NULL COMMENT '所在学校',
	`team_id` int(10) NOT NULL COMMENT '学生所在班级pj_team.id',
	`student_id` int(10) NOT NULL COMMENT '学生pj_student.id',
	`healing_date` datetime NOT NULL COMMENT '就诊日期',
	`symptom` varchar(50) NOT NULL COMMENT '主要症状',
	`handle` varchar(50) NOT NULL COMMENT '处理方式',
	`is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
	`create_date` datetime NOT NULL COMMENT '创建日期',
	`modify_date` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=6 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT COMMENT='校务后勤卫生管理--学生就诊登记' CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xx_homeworks` (
	`id` int(10) NOT NULL AUTO_INCREMENT,
	`team_id` int(10) DEFAULT NULL,
	`subject_name` varchar(20) DEFAULT NULL,
	`poster_id` int(10) DEFAULT NULL COMMENT '发送者',
	`post_time` datetime DEFAULT NULL,
	`valid_days` int(11) DEFAULT NULL,
	`receiver_id` int(10) DEFAULT NULL,
	`content` varchar(1000) DEFAULT NULL,
	`image` varchar(250) DEFAULT NULL,
	`speech` varchar(250) DEFAULT NULL,
	`document` varchar(250) DEFAULT NULL COMMENT '附件文件路径',
	`entity_id` varchar(200) DEFAULT NULL,
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xx_message` (
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ReceiverType',
	`school_id` int(10) DEFAULT NULL COMMENT '在所学校ID',
	`title` varchar(50) DEFAULT NULL,
	`poster_id` int(10) DEFAULT NULL COMMENT '送发者ID',
	`post_time` datetime DEFAULT NULL COMMENT '通知发送时间',
	`record_time` datetime DEFAULT NULL COMMENT '记录创建时间',
	`valid_days` int(11) DEFAULT NULL COMMENT '有效天数 ',
	`receiver_type` int(11) DEFAULT NULL COMMENT '接收者类别',
	`receiver_id` int(10) DEFAULT NULL COMMENT '接收者ID',
	`state` int(8) DEFAULT NULL COMMENT '0=发送成功 1=取消',
	`content` varchar(1000) DEFAULT NULL COMMENT '送发内容',
	`entity_id` varchar(200) DEFAULT '' COMMENT '关联res_entity表id, 多个以逗号隔开.',
	`post_name` varchar(20) DEFAULT NULL,
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xx_receiver` (
	`id` int(10) NOT NULL AUTO_INCREMENT,
	`owner_id` int(10) DEFAULT NULL,
	`owner_type` int(11) DEFAULT '0' COMMENT '1=通知，2=公文',
	`user_id` int(10) DEFAULT NULL COMMENT '接收者用户ID account.id',
	`user_name` varchar(50) DEFAULT NULL COMMENT '接收者姓名 account.name',
	`receiver_id` int(10) DEFAULT NULL,
	`receiver_name` varchar(50) DEFAULT NULL,
	`receiver_type` int(11) DEFAULT NULL,
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `xx_remarks` (
	`id` int(10) NOT NULL AUTO_INCREMENT,
	`school_id` int(10) DEFAULT NULL,
	`poster_id` int(10) DEFAULT NULL,
	`poster_time` datetime DEFAULT NULL,
	`poster_name` varchar(50) DEFAULT NULL,
	`position` varchar(20) DEFAULT NULL,
	`valid_days` int(255) DEFAULT '0',
	`receiver_id` int(10) DEFAULT NULL,
	`receiver_name` varchar(1000) DEFAULT NULL,
	`content` varchar(10000) DEFAULT NULL,
	`receiver_type` int(11) DEFAULT NULL,
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0
	;CREATE TABLE `yy_user_stats` (
	`id` int(10) NOT NULL AUTO_INCREMENT,
	`user_id` int(10) DEFAULT NULL COMMENT 'accounts.id',
	`user_name` varchar(50) DEFAULT NULL COMMENT '用户名 accounts.user_name',
	`name` varchar(50) DEFAULT NULL COMMENT '名姓:   accounts.name',
	`create_date` datetime DEFAULT NULL COMMENT '创建时间',
	`last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
	`on_line_duration` int(11) DEFAULT '0' COMMENT '在线时长',
	`client_os` varchar(100) DEFAULT NULL COMMENT '客户端平台',
	`client_version` varchar(500) DEFAULT NULL COMMENT '客户端平台版本',
	`app_version` varchar(20) DEFAULT NULL COMMENT '使用的App 版本',
	`rank` int(8) DEFAULT '0' COMMENT '等级',
	`score` float DEFAULT '0' COMMENT '前当积分',
	`amount` float DEFAULT '0' COMMENT '号帐总额',
	`total_income` float DEFAULT '0' COMMENT '收入总',
	`total_expense` float DEFAULT '0' COMMENT '总支出',
	`is_deleted` int(11) DEFAULT '0' COMMENT '1 = 志标为删除',
	`login_count` int(11) DEFAULT '0' COMMENT '登录次数',
	`first_login_time` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=COMPACT CHECKSUM=0 DELAY_KEY_WRITE=0;
	SET FOREIGN_KEY_CHECKS = 1
