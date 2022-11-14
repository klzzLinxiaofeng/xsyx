/*
Navicat MySQL Data Transfer

Source Server         : szxyzxx
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-05 18:24:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xw_hq_canteen_sign_order
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_canteen_sign_order`;
CREATE TABLE `xw_hq_canteen_sign_order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `supply_id` int(10) NOT NULL COMMENT '供应商id xw_hq_canteen_supply.id',
  `canteen_code` char(36) NOT NULL COMMENT '餐厅编号',
  `supply_name` varchar(50) NOT NULL COMMENT '提供商名称',
  `sign_person` varchar(20) NOT NULL COMMENT '签收人',
  `sign_date` datetime NOT NULL COMMENT '签收日期',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
