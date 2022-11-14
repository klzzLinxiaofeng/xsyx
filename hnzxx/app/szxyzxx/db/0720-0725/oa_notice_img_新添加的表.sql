/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-07-24 16:07:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_notice_img`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notice_img`;
CREATE TABLE `oa_notice_img` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_uuid` varchar(36) DEFAULT NULL COMMENT '图片ID',
  `img_url` varchar(150) DEFAULT NULL COMMENT '图片路径',
  `notice_id` int(10) DEFAULT NULL COMMENT '关联的公告ID',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice_img
-- ----------------------------
