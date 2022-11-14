/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-29 09:33:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xw_hq_health_medicine_inspection
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_health_medicine_inspection`;
CREATE TABLE `xw_hq_health_medicine_inspection` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `clinic_id` int(10) NOT NULL COMMENT '卫生室xw_hq_clinic.id',
  `examine_date` datetime NOT NULL COMMENT '检查日期',
  `name` varchar(50) NOT NULL COMMENT '药品名称',
  `stock` int(10) NOT NULL COMMENT '库存量',
  `unit` char(4) NOT NULL COMMENT '单位',
  `state` char(4) NOT NULL COMMENT '药品状态',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='校务后勤卫生管理--药品检查情况';
