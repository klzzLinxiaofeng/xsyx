/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-03 15:46:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `oa_vehicle`;
CREATE TABLE `oa_vehicle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` char(36) DEFAULT NULL COMMENT 'UUID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `name` varchar(100) DEFAULT NULL COMMENT '房间名称',
  `plate_number` varchar(20) DEFAULT NULL COMMENT '车牌号码',
  `model` varchar(10) DEFAULT NULL COMMENT '汽车类型',
  `frame_number` varchar(5) DEFAULT NULL COMMENT '车架号',
  `engine_number` varchar(45) DEFAULT NULL COMMENT '发动机号',
  `purchase_date` datetime DEFAULT NULL COMMENT '购置日期',
  `vehicle_type` varchar(2) DEFAULT NULL COMMENT '车辆类别',
  `service_condition` varchar(50) DEFAULT NULL COMMENT '使用状况码',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
