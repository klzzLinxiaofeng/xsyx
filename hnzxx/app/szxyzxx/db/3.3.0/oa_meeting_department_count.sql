/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-09-07 15:15:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_meeting_department_count
-- ----------------------------
DROP TABLE IF EXISTS `oa_meeting_department_count`;
CREATE TABLE `oa_meeting_department_count` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `department_id` int(10) NOT NULL COMMENT '部门ID',
  `meeting_count` int(10) NOT NULL COMMENT '该部门下的会议数',
  `owner_id` int(10) NOT NULL COMMENT '单位ID',
  `owner_type` int(10) NOT NULL COMMENT '单位类型',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
