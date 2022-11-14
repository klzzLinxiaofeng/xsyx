/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1
 Source Server Version : 50163
 Source Host           : 127.0.0.1
 Source Database       : edu_gzzhxy

 Target Server Version : 50163
 File Encoding         : utf-8

 Date: 06/10/2015 17:31:08 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `yh_user_region`
-- ----------------------------
DROP TABLE IF EXISTS `yh_user_region`;
CREATE TABLE `yh_user_region` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` int(10) NOT NULL COMMENT 'yh_app.id',
  `owner_id` int(10) DEFAULT NULL COMMENT '保留',
  `user_id` int(10) NOT NULL COMMENT 'yh_user.id',
  `level` char(2) NOT NULL COMMENT '区域类型',
  `region_code` char(10) NOT NULL COMMENT 'jc_region.code',
  `create_user_id` int(10) NOT NULL COMMENT 'yh_user.id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
