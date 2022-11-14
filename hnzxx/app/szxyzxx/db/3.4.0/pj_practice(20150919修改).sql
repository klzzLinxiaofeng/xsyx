/*
Navicat MySQL Data Transfer

Source Server         : xunyun
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-09-19 11:44:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pj_practice
-- ----------------------------
DROP TABLE IF EXISTS `pj_practice`;
CREATE TABLE `pj_practice` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `school_id` int(10) NOT NULL COMMENT '学校id',
  `school_year` varchar(10) NOT NULL COMMENT '学年',
  `term_code` varchar(50) NOT NULL COMMENT '学期',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `practice_date` datetime NOT NULL COMMENT '实践时间',
  `position` varchar(50) NOT NULL,
  `master_name` varchar(500) NOT NULL COMMENT '组织者名字',
  `master_id` varchar(500) NOT NULL COMMENT '组织者的id',
  `teach_name` varchar(500) NOT NULL COMMENT '参与者名字',
  `teach_id` varchar(500) NOT NULL COMMENT '参与者的id',
  `student_name` varchar(500) NOT NULL COMMENT '参与者学生的名字',
  `student_id` varchar(500) NOT NULL COMMENT '参与者的id',
  `file_uuid` char(32) DEFAULT NULL COMMENT 'file.id',
  `is_delete` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
