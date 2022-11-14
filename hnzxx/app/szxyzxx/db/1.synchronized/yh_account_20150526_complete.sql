/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1
 Source Server Version : 50163
 Source Host           : 127.0.0.1
 Source Database       : edu_gzzhxy

 Target Server Version : 50163
 File Encoding         : utf-8

 Date: 05/26/2015 17:16:13 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `yh_account`
-- ----------------------------
DROP TABLE IF EXISTS `yh_account`;
CREATE TABLE `yh_account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(36) NOT NULL COMMENT '用户名',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '账号库使用状态 0=未分配；1=已经分配；2=保留',
  `for_app_id` int(10) DEFAULT NULL COMMENT '预分配给哪个app yh_app.id',
  `to_app_id` int(10) DEFAULT NULL COMMENT '已经被哪个app分配',
  `apply_date` datetime DEFAULT NULL COMMENT '被使用的时间',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
