/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-11-20 10:04:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_applay_leave_approve_user`
-- ----------------------------
DROP TABLE IF EXISTS `oa_applay_leave_approve_user`;
CREATE TABLE `oa_applay_leave_approve_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `leave_id` int(11) NOT NULL COMMENT '请假条id',
  `approve_id` int(11) DEFAULT NULL COMMENT '审批人id',
  `approve_state` tinyint(4) DEFAULT NULL COMMENT '审批状态： 0、未审批  1、已审批',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;


