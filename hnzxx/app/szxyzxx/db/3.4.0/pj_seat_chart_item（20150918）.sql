/*
Navicat MySQL Data Transfer

Source Server         : xunyun
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-09-18 17:04:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pj_seat_chart_item
-- ----------------------------
DROP TABLE IF EXISTS `pj_seat_chart_item`;
CREATE TABLE `pj_seat_chart_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `school_id` int(10) unsigned NOT NULL COMMENT '所属学校',
  `seat_id` int(10) unsigned NOT NULL,
  `student_id` int(10) unsigned NOT NULL,
  `position_x` int(3) NOT NULL COMMENT '行数',
  `position_y` int(3) NOT NULL COMMENT '组数（列数）',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=717 DEFAULT CHARSET=utf8;
