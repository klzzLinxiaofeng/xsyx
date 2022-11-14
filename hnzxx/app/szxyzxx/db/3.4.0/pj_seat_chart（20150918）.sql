/*
Navicat MySQL Data Transfer

Source Server         : xunyun
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-09-18 17:03:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pj_seat_chart
-- ----------------------------
DROP TABLE IF EXISTS `pj_seat_chart`;
CREATE TABLE `pj_seat_chart` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `school_id` int(10) unsigned NOT NULL COMMENT '所属学校',
  `school_year` char(15) DEFAULT NULL,
  `classroom_id` int(10) unsigned DEFAULT NULL COMMENT '教室编号',
  `classroom_name` varchar(50) DEFAULT NULL COMMENT '教室名称',
  `classroom_type` char(4) DEFAULT NULL,
  `grade_id` int(10) unsigned NOT NULL,
  `team_id` int(10) unsigned NOT NULL,
  `seat_type` int(1) DEFAULT '1' COMMENT '座位类别，双人还是单人座',
  `row` int(3) NOT NULL DEFAULT '0' COMMENT '行数',
  `col` int(3) NOT NULL DEFAULT '0' COMMENT '组数（列数）',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
