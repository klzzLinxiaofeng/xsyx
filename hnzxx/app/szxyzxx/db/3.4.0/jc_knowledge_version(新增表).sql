/*
Navicat MySQL Data Transfer

Source Server         : 60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-09-10 09:53:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jc_knowledge_version`
-- ----------------------------
DROP TABLE IF EXISTS `jc_knowledge_version`;
CREATE TABLE `jc_knowledge_version` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `code` char(20) NOT NULL COMMENT '唯一标识code',
  `subject_code` char(20) NOT NULL COMMENT '学科',
  `subject_name` varchar(50) NOT NULL COMMENT '科目名称',
  `stage_code` char(20) NOT NULL COMMENT '学段',
  `stage_name` varchar(50) NOT NULL COMMENT '学段名',
  `version_id` int(10) NOT NULL,
  `version_name` varchar(50) NOT NULL COMMENT '版本名',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `description` varchar(500) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='知识点版本';

