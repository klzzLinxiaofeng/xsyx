/*
Navicat MySQL Data Transfer

Source Server         : szxyzxx
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-21 14:39:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pj_ky_research_project
-- ----------------------------
DROP TABLE IF EXISTS `pj_ky_research_project`;
CREATE TABLE `pj_ky_research_project` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `level` varchar(6) NOT NULL COMMENT '立项级别',
  `progress_level` varchar(6) NOT NULL COMMENT '进展级别',
  `code` varchar(36) NOT NULL COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '名字',
  `master_id` varchar(3000) NOT NULL,
  `master_name` varchar(3000) NOT NULL COMMENT '负责人',
  `attendees_name` varchar(3000) NOT NULL COMMENT '参与人员的名字',
  `attendees_id` varchar(3000) NOT NULL COMMENT '组织者的id',
  `begin_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `prize` varchar(50) NOT NULL,
  `file_uuid` varchar(500) NOT NULL DEFAULT '' COMMENT '多个文件的id',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
