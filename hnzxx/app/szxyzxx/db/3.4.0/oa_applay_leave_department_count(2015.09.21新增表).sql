/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2015-09-21 16:53:16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_applay_leave_department_count`
-- ----------------------------
DROP TABLE IF EXISTS `oa_applay_leave_department_count`;
CREATE TABLE `oa_applay_leave_department_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `owner_id` int(11) DEFAULT NULL COMMENT '请假人所属的单位：学校',
  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型： 1：学校',
  `department_id` int(11) DEFAULT NULL COMMENT '部门ID ',
  `audit_status` varchar(10) DEFAULT NULL COMMENT '请假审批状态 0：待审批 1：已审批 ',
  `count` int(11) DEFAULT NULL COMMENT '请假的总数',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_applay_leave_department_count
-- ----------------------------
