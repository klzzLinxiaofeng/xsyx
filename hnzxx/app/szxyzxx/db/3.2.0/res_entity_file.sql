/*
 Navicat MySQL Data Transfer

 Source Server         : educaction_192.168.10.60
 Source Server Version : 50625
 Source Host           : 192.168.10.60
 Source Database       : edu_gzzhxy_test

 Target Server Version : 50625
 File Encoding         : utf-8

 Date: 08/14/2015 16:47:39 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `res_entity_file`
-- ----------------------------
DROP TABLE IF EXISTS `res_entity_file`;
CREATE TABLE `res_entity_file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `size` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '文件大小',
  `md5` char(32) NOT NULL COMMENT '文件的唯一md5码',
  `uuid` char(32) NOT NULL COMMENT '文件唯一标识符 用于关联',
  `extension` varchar(6) NOT NULL COMMENT '文件扩展名不包括点(.)',
  `content_type` varchar(45) NOT NULL COMMENT '文件头类型 ',
  `file_name` varchar(255) NOT NULL COMMENT '真实文件名',
  `disk_file_name` varchar(255) NOT NULL COMMENT '系统磁盘上的文件名称',
  `thumbnail_url` varchar(1000) NOT NULL COMMENT '文件对应缩略图的相对地址',
  `relative_path` varchar(1000) NOT NULL COMMENT '文件的相对路径',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
