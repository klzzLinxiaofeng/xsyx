/*
Navicat MySQL Data Transfer

Source Server         : szxyzxx
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-05 18:24:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xw_hq_canteen_sign_item
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_canteen_sign_item`;
CREATE TABLE `xw_hq_canteen_sign_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '签收明细单id',
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `order_id` int(10) NOT NULL COMMENT 'xw_hq_canteen_sign_order.id 签收单ID',
  `goods_code` char(36) NOT NULL COMMENT '商品编号',
  `goods_name` varchar(50) NOT NULL COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL COMMENT '商品单价',
  `total_count` int(10) NOT NULL COMMENT '商品总数量',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
