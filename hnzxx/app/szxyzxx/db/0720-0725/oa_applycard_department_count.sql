/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-24 15:49:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_applycard_department_count
-- ----------------------------
DROP TABLE IF EXISTS `oa_applycard_department_count`;
CREATE TABLE `oa_applycard_department_count` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `school_id` int(10) DEFAULT NULL COMMENT '学校ID',
  `department_id` int(10) DEFAULT NULL COMMENT '部门ID',
  `number` int(10) DEFAULT NULL COMMENT '人数',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `isdelete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
