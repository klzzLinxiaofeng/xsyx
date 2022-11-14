/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2015-09-21 16:53:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_applay_leave`
-- ----------------------------
DROP TABLE IF EXISTS `oa_applay_leave`;
CREATE TABLE `oa_applay_leave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(36) DEFAULT NULL COMMENT 'UUID',
  `app_id` int(10) DEFAULT NULL COMMENT 'appid',
  `owner_id` int(11) DEFAULT NULL COMMENT '公文所属的单位，学校',
  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型 ， 1：学校',
  `propser_id` int(10) DEFAULT NULL COMMENT '申请人id',
  `propser_name` varchar(36) DEFAULT NULL COMMENT '申请人姓名',
  `propser_department_id` int(10) DEFAULT NULL COMMENT '申请人所属部门',
  `title` varchar(40) DEFAULT NULL COMMENT '标题',
  `start_date` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `end_date` varchar(50) DEFAULT NULL COMMENT '结束时间',
  `mobile` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `detail` varchar(120) DEFAULT NULL COMMENT '详情',
  `is_daike` tinyint(4) DEFAULT NULL COMMENT '有无代课教师  0：有 1：无',
  `audit_status` varchar(10) DEFAULT NULL COMMENT '审批状态  0：审批  1：未审批',
  `approve_state` varchar(10) DEFAULT NULL COMMENT '审批通过状态  0：审批通过 1：审批未通过',
  `approve_id` int(10) DEFAULT NULL COMMENT '审批人id',
  `approve_name` varchar(36) DEFAULT NULL COMMENT '审批人姓名',
  `publish_date` datetime DEFAULT NULL COMMENT '发布日期',
  `approve_remark` varchar(120) DEFAULT NULL COMMENT '审批备注',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '删除标志',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_applay_leave
-- ----------------------------
