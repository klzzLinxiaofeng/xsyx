/*
Navicat MySQL Data Transfer

Source Server         : 60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-09-10 09:53:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jc_knowledge_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `jc_knowledge_catalog`;
CREATE TABLE `jc_knowledge_catalog` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `knowledge_version_code` char(20) NOT NULL COMMENT '对应知识点版本',
  `parent_id` int(10) NOT NULL DEFAULT '0' COMMENT '自关联父节点',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `level` int(11) NOT NULL COMMENT '树节点的层次深度',
  `sort` int(11) DEFAULT '0' COMMENT '排序字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='知识点内容组织结构';

