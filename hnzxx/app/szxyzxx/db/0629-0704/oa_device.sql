/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-03 15:47:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_device
-- ----------------------------
DROP TABLE IF EXISTS `oa_device`;
CREATE TABLE `oa_device` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` char(36) NOT NULL COMMENT 'UUID',
  `school_id` int(11) NOT NULL COMMENT '所在学校',
  `code` varchar(20) DEFAULT NULL COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `english_name` varchar(100) DEFAULT NULL COMMENT '名称',
  `property_right` varchar(10) DEFAULT NULL COMMENT '产权码',
  `service_condition` varchar(50) DEFAULT NULL COMMENT '使用状况码',
  `category` varchar(50) DEFAULT NULL COMMENT '分类',
  `model` varchar(50) DEFAULT NULL COMMENT '型号',
  `ex_factory_date` datetime DEFAULT NULL COMMENT '出厂时间',
  `purchase_date` datetime DEFAULT NULL COMMENT '购置日期',
  `manufacturer` varchar(100) DEFAULT NULL COMMENT '生产厂家',
  `source_type` varchar(2) DEFAULT NULL COMMENT '设备来源',
  `document_number` varchar(50) DEFAULT NULL COMMENT '单据号',
  `place` varchar(50) DEFAULT NULL COMMENT '仪器地点',
  `bliding_id` int(11) NOT NULL COMMENT '所在建筑物',
  `room_id` int(11) NOT NULL COMMENT '所在房间',
  `price` varchar(45) DEFAULT NULL COMMENT '价格',
  `warranty_exp_date` datetime DEFAULT NULL COMMENT '保修期截止日期',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除状态',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
