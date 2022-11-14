/*
Navicat MySQL Data Transfer

Source Server         : 60数据库
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-01 10:30:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jc_textbook_version`
-- ----------------------------
DROP TABLE IF EXISTS `jc_textbook_version`;
CREATE TABLE `jc_textbook_version` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '名称（唯一）',
  `publisher_id` int(20) NOT NULL COMMENT '简称（唯一）',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `is_delete` bit(1) NOT NULL COMMENT '是否删除，0==不删除，1===删除',
  `description` varchar(500) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`,`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='教材出版社（教材版本）';

-- ----------------------------
-- Records of jc_textbook_version
-- ----------------------------
