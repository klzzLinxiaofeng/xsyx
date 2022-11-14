/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-29 09:33:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xw_hq_health_disease_report
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_health_disease_report`;
CREATE TABLE `xw_hq_health_disease_report` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `report_date` datetime NOT NULL COMMENT '报告时间',
  `report_unit` varchar(50) NOT NULL COMMENT '上报单位',
  `report_phone` varchar(50) DEFAULT NULL COMMENT '上报电话',
  `report_person` varchar(50) DEFAULT NULL COMMENT '接报人',
  `disease` varchar(50) NOT NULL COMMENT '病名',
  `attack_site` varchar(50) DEFAULT NULL COMMENT '发病地点',
  `attack_number` int(5) DEFAULT NULL COMMENT '发病人数',
  `begin_date` datetime NOT NULL COMMENT '首例时间',
  `symptom` varchar(50) DEFAULT NULL COMMENT '主要症状',
  `handle` varchar(50) DEFAULT NULL COMMENT '疫情处理',
  `reporter` varchar(50) DEFAULT NULL COMMENT '报告人',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='校务后勤卫生管理--传染病疫情报告登记';
