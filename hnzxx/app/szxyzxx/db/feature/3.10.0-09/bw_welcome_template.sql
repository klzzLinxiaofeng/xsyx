/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50513
Source Host           : 127.0.0.1:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-04-22 17:30:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `bw_welcome_template`  
-- ----------------------------
DROP TABLE IF EXISTS `bw_welcome_template`;
CREATE TABLE `bw_welcome_template` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `school_id` int(10) unsigned DEFAULT NULL COMMENT '学校id （pj_school.id）',
  `team_id` int(10) unsigned DEFAULT NULL COMMENT '班级id（pj_team.id）',
  `file_uuid` varchar(36) DEFAULT NULL COMMENT '图片文件uuid',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '作废标记',
  `create_date` datetime DEFAULT NULL COMMENT '记录创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

