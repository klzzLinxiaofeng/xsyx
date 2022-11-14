/*
Navicat MySQL Data Transfer

Source Server         : 60数据库
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-09 10:16:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jc_textbook_volumn`
-- ----------------------------
DROP TABLE IF EXISTS `jc_textbook_volumn`;
CREATE TABLE `jc_textbook_volumn` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `code` varchar(2) NOT NULL COMMENT '代码',
  `stage_code` varchar(20) DEFAULT NULL COMMENT '适用学段，可空，可多个',
  `sort_code` int(11) DEFAULT NULL COMMENT '排序',
  `is_delete` bit(1) NOT NULL COMMENT '是否删除，0==不删除，1===删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教材的册次问题';

-- ----------------------------
-- Records of jc_textbook_volumn
-- ----------------------------
