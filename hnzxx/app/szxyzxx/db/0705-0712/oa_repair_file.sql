/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-09 12:45:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_repair_file
-- ----------------------------
DROP TABLE IF EXISTS `oa_repair_file`;
CREATE TABLE `oa_repair_file` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `repair_id` int(10) NOT NULL COMMENT '维修',
  `poster_id` int(10) NOT NULL COMMENT '上传人',
  `post_time` datetime DEFAULT NULL COMMENT '上传时间',
  `file_uuid` char(36) NOT NULL COMMENT '文件',
  `thumb_url` varchar(250) DEFAULT NULL COMMENT '缩略图位置',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
