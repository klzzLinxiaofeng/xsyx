/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-03 15:47:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_auditcard
-- ----------------------------
DROP TABLE IF EXISTS `oa_auditcard`;
CREATE TABLE `oa_auditcard` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `return_or_use_id` int(10) NOT NULL COMMENT '借车或还车ID',
  `audit_user` int(10) DEFAULT NULL COMMENT '审批人',
  `audit_type` char(2) DEFAULT NULL COMMENT '审批类型',
  `audit_opinion` varchar(200) DEFAULT NULL COMMENT '审批意见',
  `audit_date` datetime DEFAULT NULL COMMENT '审批日期',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NOT NULL COMMENT '删除标记',
  `audit_result` varchar(2) DEFAULT NULL COMMENT '审核结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
