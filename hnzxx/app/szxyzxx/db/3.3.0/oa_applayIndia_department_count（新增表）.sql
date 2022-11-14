/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-28 14:29:50
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_applayIndia_department_count`
-- ----------------------------
DROP TABLE IF EXISTS `oa_applayIndia_department_count`;
CREATE TABLE `oa_applayIndia_department_count` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) DEFAULT NULL COMMENT '发布文印人员所属单位 学校',
  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型 1：学校',
  `department_id` int(10) DEFAULT NULL COMMENT '所属部门id',
  `number` int(10) DEFAULT NULL COMMENT '对应文印申请总数',
  `india_status` varchar(2) DEFAULT NULL COMMENT '文印处理状态',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_applayIndia_department_count
-- ----------------------------
INSERT INTO oa_applayIndia_department_count VALUES ('17', '1', '1', '4', '1', '0', '2015-08-28 13:05:19', '2015-08-28 13:05:19', '');
