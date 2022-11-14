/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-07 16:26:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ui_setting
-- ----------------------------
DROP TABLE IF EXISTS `ui_setting`;
CREATE TABLE `ui_setting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `wallpaper_path` varchar(250) NOT NULL COMMENT '壁纸的绝对路径',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户首页壁纸';
