/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-07-24 16:06:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_schedule_user`
-- ----------------------------
DROP TABLE IF EXISTS `oa_schedule_user`;
CREATE TABLE `oa_schedule_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `schedule_id` int(11) DEFAULT NULL COMMENT '相关日程ID',
  `receiver_id` int(10) DEFAULT NULL COMMENT '接收者id',
  `receiver_name` varchar(100) DEFAULT NULL COMMENT '接收者名字',
  `receiver_type` tinyint(1) DEFAULT NULL COMMENT '接收者类型',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：还没删除，1：已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_schedule_user
-- ----------------------------
