/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-05-23 11:42:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pj_exam_team_subject`
-- ----------------------------
DROP TABLE IF EXISTS `pj_exam_team_subject`;
CREATE TABLE `pj_exam_team_subject` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '考试日程安排表主键',
  `team_id` int(10) NOT NULL COMMENT '考试班级id,关联pj_team.id',
  `subject_code` varchar(36) NOT NULL COMMENT '考试科目代码 关联pj_subject.code',
  `school_year` varchar(15) NOT NULL COMMENT '考试学年',
  `exam_type` varchar(5) NOT NULL COMMENT '考试类型',
  `exam_name` varchar(100) NOT NULL COMMENT '考试名称',
  `precise_start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '考试开始时间',
  `precise_end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '考试结束时间',
  `is_delete` bit(1) NOT NULL COMMENT '是否删除，0==不删除，1===删除',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `term_code` varchar(50) NOT NULL COMMENT '学期代码',
  `task_type` int(1) NOT NULL COMMENT '任务类别，0为考试，1为作业',
  `task_online` int(1) NOT NULL COMMENT '任务是否是在线作业或者考试，1===是，0===否',
  `task_rate` bit(1) NOT NULL COMMENT '是否完成等分0===否，1===是',
  `rate_type` int(1) DEFAULT NULL COMMENT '评分类型，0===未定义，1===标准分 ，',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='考试日程安排表';

