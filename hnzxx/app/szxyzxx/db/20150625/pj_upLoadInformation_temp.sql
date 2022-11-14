/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-12 16:24:48
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `pj_upLoadInformation_temp`
-- ----------------------------
DROP TABLE IF EXISTS `pj_upLoadInformation_temp`;
CREATE TABLE `pj_upLoadInformation_temp` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主健ID',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=967 DEFAULT CHARSET=utf8;

