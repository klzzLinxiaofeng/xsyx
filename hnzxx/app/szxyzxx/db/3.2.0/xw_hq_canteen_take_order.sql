/*
Navicat MySQL Data Transfer

Source Server         : szxyzxx
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-05 18:25:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xw_hq_canteen_take_order
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_canteen_take_order`;
CREATE TABLE `xw_hq_canteen_take_order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '领用单id',
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `canteen_code` char(36) NOT NULL COMMENT '餐厅编号',
  `goods_code` char(36) NOT NULL COMMENT '商品编号',
  `take_count` int(10) NOT NULL COMMENT '领取数量',
  `sign_person` varchar(20) NOT NULL COMMENT '签收人',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除  1已经删除',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
