/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-03 15:46:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_returncard
-- ----------------------------
DROP TABLE IF EXISTS `oa_returncard`;
CREATE TABLE `oa_returncard` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `usecard_id` int(10) NOT NULL COMMENT '借出车ID',
  `return_date` datetime DEFAULT NULL COMMENT '还车日期',
  `return_explain` varchar(200) DEFAULT NULL COMMENT '还车说明',
  `return_status` varchar(45) DEFAULT NULL COMMENT '还车状态',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
