/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-07-24 16:06:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_schedule_remind`
-- ----------------------------
DROP TABLE IF EXISTS `oa_schedule_remind`;
CREATE TABLE `oa_schedule_remind` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `schedule_id` int(11) DEFAULT NULL COMMENT '相关日程ID',
  `poster_id` int(10) DEFAULT NULL COMMENT '创建人id',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否启用提醒功能',
  `start_time` varchar(25) DEFAULT NULL COMMENT '开始提醒时间',
  `repeat_interval` int(4) DEFAULT NULL COMMENT '每次提醒间隔（分钟）',
  `repeat_time` int(4) DEFAULT NULL COMMENT '提醒次数',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：还没删除，1：已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_schedule_remind
-- ----------------------------
