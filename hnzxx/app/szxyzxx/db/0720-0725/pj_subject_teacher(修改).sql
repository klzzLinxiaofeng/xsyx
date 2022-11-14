/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-20 17:55:05
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `pj_subject_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `pj_subject_teacher`;
CREATE TABLE `pj_subject_teacher` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `stage_code` char(36) NOT NULL COMMENT '学段code',
  `teacher_id` int(10) NOT NULL COMMENT '教师ID',
  `name` varchar(50) NOT NULL COMMENT '教师姓名（冗余）',
  `subject_code` char(36) NOT NULL COMMENT '担任科目',
  `create_date` datetime NOT NULL,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


