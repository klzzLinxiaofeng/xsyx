/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-06-29 18:21:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rs_teacher_salary
-- ----------------------------
DROP TABLE IF EXISTS `rs_teacher_salary`;
CREATE TABLE `rs_teacher_salary` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `teacher_id` int(10) NOT NULL COMMENT 'pj_teacher.id',
  `salary` varchar(50) NOT NULL COMMENT '薪资金额',
  `description` varchar(50) DEFAULT NULL COMMENT '说明',
  `valid_date` datetime NOT NULL COMMENT '有效开始时间',
  `recorder` varchar(45) DEFAULT NULL COMMENT '录入人员',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='人事管理薪资管理';
