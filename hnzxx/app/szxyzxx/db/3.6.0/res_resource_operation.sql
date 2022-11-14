/*
Navicat MySQL Data Transfer

Source Server         : xunyun
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-10-22 17:34:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for res_resource_operation
-- ----------------------------
DROP TABLE IF EXISTS `res_resource_operation`;
CREATE TABLE `res_resource_operation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `resource_id` int(10) unsigned NOT NULL,
  `resource_uuid` varchar(36) NOT NULL,
  `resource_title` varchar(200) NOT NULL COMMENT '资源的名称',
  `library_uuid` varchar(36) NOT NULL COMMENT '关联的学校',
  `resource_type` int(1) NOT NULL COMMENT '1代表微课   2代表资源',
  `operation_type` int(1) NOT NULL COMMENT '0代表点击  1代表点赞 2代表收藏 3代表下载',
  `user_id` int(10) unsigned NOT NULL COMMENT '哪个用户操作的',
  `this_week` tinyint(4) NOT NULL COMMENT '是否为本周',
  `operation_date` datetime DEFAULT NULL COMMENT '操作时间',
  `create_date` datetime DEFAULT NULL,
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
