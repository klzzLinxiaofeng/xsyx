/*
Navicat MySQL Data Transfer

Source Server         : xunyun
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-10-22 17:34:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for res_operation_weekcount
-- ----------------------------
DROP TABLE IF EXISTS `res_operation_weekcount`;
CREATE TABLE `res_operation_weekcount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `resource_id` int(10) unsigned NOT NULL,
  `resource_uuid` varchar(36) NOT NULL,
  `resource_title` varchar(200) NOT NULL COMMENT '资源的名称',
  `library_uuid` varchar(36) NOT NULL COMMENT '关联的学校',
  `resource_type` int(1) NOT NULL COMMENT '1代表微课   2代表资源',
  `operation_type` int(1) NOT NULL COMMENT '0代表点击  1代表点赞 2代表收藏 3代表下载',
  `count` int(10) NOT NULL COMMENT '操作次数',
  `create_date` datetime NOT NULL,
  `modify_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1189 DEFAULT CHARSET=utf8;
