/*
Navicat MySQL Data Transfer

Source Server         : szxyzxx
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-05 18:24:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xw_hq_canteen_goods
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_canteen_goods`;
CREATE TABLE `xw_hq_canteen_goods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `code` char(36) NOT NULL COMMENT '商品编号',
  `name` varchar(50) NOT NULL COMMENT '商品名称',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
