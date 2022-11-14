/*
Navicat MySQL Data Transfer

Source Server         : szxyzxx
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-05 18:25:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xw_hq_canteen_supply
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_canteen_supply`;
CREATE TABLE `xw_hq_canteen_supply` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `name` varchar(50) NOT NULL COMMENT '供货商名称',
  `telephone` varchar(20) NOT NULL COMMENT '联系方式',
  `address` varchar(100) NOT NULL COMMENT '联系地址',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
