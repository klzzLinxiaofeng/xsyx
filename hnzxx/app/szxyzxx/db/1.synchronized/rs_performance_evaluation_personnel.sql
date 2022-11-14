/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-06-24 18:24:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rs_performance_evaluation_personnel
-- ----------------------------
DROP TABLE IF EXISTS `rs_performance_evaluation_personnel`;
CREATE TABLE `rs_performance_evaluation_personnel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `task_id` int(10) NOT NULL COMMENT 'rs_performance_evaluation_task.id  考核任务ID',
  `personnel_id` int(10) NOT NULL COMMENT '教职工ID',
  `type` char(4) NOT NULL COMMENT '类型:0--考核人;1--被考核人',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
