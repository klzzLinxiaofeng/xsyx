/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-24 15:49:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_apply_card
-- ----------------------------
DROP TABLE IF EXISTS `oa_apply_card`;
CREATE TABLE `oa_apply_card` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) DEFAULT NULL COMMENT '申请标题',
  `card_id` int(10) DEFAULT NULL COMMENT '所申请车辆',
  `proposer` int(10) DEFAULT NULL COMMENT '申请人',
  `phone` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `begin_date` datetime DEFAULT NULL COMMENT '使用开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '使用结束时间',
  `release_date` datetime DEFAULT NULL COMMENT '发布时间',
  `audit_user` int(10) DEFAULT NULL COMMENT '审批人',
  `audit_status` varchar(10) DEFAULT NULL COMMENT '审批状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `school_id` int(10) NOT NULL COMMENT '学校ID',
  `department_id` int(10) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
