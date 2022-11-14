/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-24 15:49:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_vehicle_management
-- ----------------------------
DROP TABLE IF EXISTS `oa_vehicle_management`;
CREATE TABLE `oa_vehicle_management` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(36) DEFAULT NULL COMMENT 'UUID',
  `school_id` int(10) DEFAULT NULL COMMENT '学校ID',
  `full_load` int(10) DEFAULT NULL COMMENT '满载人数',
  `plate_number` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `card_name` varchar(100) DEFAULT NULL COMMENT '车名',
  `purchase_data` datetime DEFAULT NULL COMMENT '购置日期',
  `service_condition` varchar(50) DEFAULT NULL COMMENT '使用状况',
  `cover` varchar(200) DEFAULT NULL COMMENT '封面',
  `model` varchar(10) DEFAULT NULL COMMENT '汽车类型',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
