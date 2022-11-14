CREATE TABLE `pj_textbook_manager` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stage_code` VARCHAR(2) NOT NULL COMMENT '使用学段',
  `grade_code` VARCHAR(5) NOT NULL COMMENT '年级',
  `subject_code` VARCHAR(10) NOT NULL COMMENT '适用学科',
  `volumn` VARCHAR(2) NOT NULL COMMENT '册次，卷',
  `version` VARCHAR(20) NOT NULL COMMENT '版本',
  `name` VARCHAR(100) NOT NULL COMMENT '名称',
  `publisher_id` INT(11) NULL COMMENT '版本类型，如人教版',
  `isbn` VARCHAR(20) NULL COMMENT 'isbn 国际标准书号（International Standard Book Number），简称ISBN，是专门为识别图书等文献而设计的国际编号',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `price` VARCHAR(10) NULL COMMENT '价格',
  `type` VARCHAR(3) NULL COMMENT '书籍类型',
  `is_delete` BIT(1) NOT NULL COMMENT '是否删除，0==不删除，1===删除',
  `count` VARCHAR(12) NOT NULL DEFAULT 0 COMMENT '数量',
  `count_type` VARCHAR(3) NULL COMMENT '数量类型-手工填写1，还是自动从班级获取0',
  `add_type` VARCHAR(3) NOT NULL DEFAULT 0 COMMENT '添加类型，默认为从基础教材表获取 0 ，现在不存在手动添加 1',
  `school_id` INT(11) NOT NULL COMMENT '学校',
  `school_year` VARCHAR(45) NOT NULL COMMENT '学年',
  `term_code` VARCHAR(50) NOT NULL COMMENT '学期',
  PRIMARY KEY (`id`))
COMMENT = '学校教材管理';

ALTER TABLE `pj_textbook_manager` 
ADD COLUMN `teacher_id` VARCHAR(45) NULL AFTER `name`,
ADD COLUMN `grade_id` VARCHAR(45) NOT NULL AFTER `term_code`,
ADD COLUMN `team_id` VARCHAR(45) NOT NULL AFTER `grade_id`,
ADD COLUMN `teacher_name` VARCHAR(45) NULL AFTER `team_id`;

ALTER TABLE `pj_textbook_manager` 
CHANGE COLUMN `teacher_id` `teacher_id` VARCHAR(45) NULL DEFAULT NULL COMMENT '任课教师id' ,
CHANGE COLUMN `grade_id` `grade_id` VARCHAR(45) NOT NULL COMMENT '年级id' ,
CHANGE COLUMN `team_id` `team_id` VARCHAR(45) NOT NULL COMMENT '班级id' ,
CHANGE COLUMN `teacher_name` `teacher_name` VARCHAR(45) NULL COMMENT '任课教师姓名' ;

