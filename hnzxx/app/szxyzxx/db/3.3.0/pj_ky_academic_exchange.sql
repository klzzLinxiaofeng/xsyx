/*
Navicat MySQL Data Transfer

Source Server         : szxyzxx
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-19 16:07:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pj_ky_academic_exchange
-- ----------------------------
DROP TABLE IF EXISTS `pj_ky_academic_exchange`;
CREATE TABLE `pj_ky_academic_exchange` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `type` varchar(6) NOT NULL COMMENT '交流类型',
  `name` varchar(50) NOT NULL COMMENT '名字',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `attendees_name` varchar(5000) NOT NULL COMMENT '参与人员的名字',
  `attendees_id` varchar(5000) NOT NULL COMMENT '组织者的id',
  `file_uuid` varchar(500) NOT NULL DEFAULT '' COMMENT '多个文件的id',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
