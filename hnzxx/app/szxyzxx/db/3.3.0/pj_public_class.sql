/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-21 10:27:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pj_public_class
-- ----------------------------
DROP TABLE IF EXISTS `pj_public_class`;
CREATE TABLE `pj_public_class` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校pj_shool.id',
  `name` varchar(50) NOT NULL COMMENT '课程名称',
  `teacher_id` int(10) NOT NULL COMMENT '上课老师 pj_teacher.id',
  `begin_date` datetime NOT NULL COMMENT '开始上课日期',
  `class_number` int(3) NOT NULL COMMENT '课程总节数',
  `max_member` int(3) NOT NULL COMMENT '人数上限',
  `enroll_number` int(3) DEFAULT '0' COMMENT '已报名人数',
  `expiry_date` datetime NOT NULL COMMENT '报名截止日期',
  `enroll_desc` varchar(200) DEFAULT NULL COMMENT '报名详情',
  `class_desc` varchar(200) DEFAULT NULL COMMENT '课程详情',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='开课选课管理--开课管理';
