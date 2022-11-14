/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-06-24 18:24:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rs_performance_evaluation_task
-- ----------------------------
DROP TABLE IF EXISTS `rs_performance_evaluation_task`;
CREATE TABLE `rs_performance_evaluation_task` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `name` varchar(50) NOT NULL COMMENT '考核任务名称',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `expiry_date` datetime NOT NULL COMMENT '终止日期',
  `year` varchar(50) NOT NULL COMMENT '年份(用于查询历史记录)',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `is_issue` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布标记，0--未发布，1--已发布',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
