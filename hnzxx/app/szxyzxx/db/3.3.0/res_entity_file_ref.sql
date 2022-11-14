/*
 Navicat MySQL Data Transfer

 Source Server         : educaction_192.168.10.60
 Source Server Version : 50625
 Source Host           : 192.168.10.60
 Source Database       : edu_gzzhxy

 Target Server Version : 50625
 File Encoding         : utf-8

 Date: 08/20/2015 16:17:46 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `res_entity_file_ref`
-- ----------------------------
DROP TABLE IF EXISTS `res_entity_file_ref`;
CREATE TABLE `res_entity_file_ref` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `entity_file_id` int(10) unsigned NOT NULL COMMENT '文件实体ID res_entity_file.id',
  `tag` char(10) NOT NULL COMMENT '模块标记，确保值唯一，可app_id或模块唯一标识',
  `count` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '同一个app模块下的文件引用次数',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22790 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
