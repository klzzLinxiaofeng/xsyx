/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-29 09:33:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xw_hq_health_clinic
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_health_clinic`;
CREATE TABLE `xw_hq_health_clinic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `code` char(36) NOT NULL COMMENT '卫生室编号',
  `name` varchar(50) NOT NULL COMMENT '卫生室名称',
  `principal` varchar(50) DEFAULT NULL COMMENT '负责人',
  `floor_id` int(10) NOT NULL COMMENT '所属大楼xw_hq_floor.id',
  `storey` tinyint(4) NOT NULL COMMENT '所在楼层',
  `room` varchar(50) DEFAULT NULL COMMENT '所在房间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='校务后勤卫生管理--卫生室管理';
