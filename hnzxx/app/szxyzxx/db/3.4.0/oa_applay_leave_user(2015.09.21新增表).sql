/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2015-09-21 16:53:24
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_applay_leave_user`
-- ----------------------------
DROP TABLE IF EXISTS `oa_applay_leave_user`;
CREATE TABLE `oa_applay_leave_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `leave_id` int(11) NOT NULL COMMENT '请假条id',
  `daike_id` int(11) DEFAULT NULL COMMENT '代课教师id',
  `daike_name` varchar(36) DEFAULT NULL COMMENT '代课教师姓名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_applay_leave_user
-- ----------------------------
