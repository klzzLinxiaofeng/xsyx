/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-06-24 18:24:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rs_performance_evaluation_item
-- ----------------------------
DROP TABLE IF EXISTS `rs_performance_evaluation_item`;
CREATE TABLE `rs_performance_evaluation_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `name` varchar(50) NOT NULL COMMENT '考核项目名称',
  `start_score` varchar(4) NOT NULL COMMENT '起始分值',
  `end_score` varchar(4) NOT NULL COMMENT '结束分值',
  `description` varchar(200) DEFAULT NULL COMMENT '分值描述',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
