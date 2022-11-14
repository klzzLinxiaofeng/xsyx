/*
Navicat MySQL Data Transfer

Source Server         : 60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-07 15:12:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sq_message`
-- ----------------------------
DROP TABLE IF EXISTS `sq_message`;
CREATE TABLE `sq_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_id` int(10) NOT NULL COMMENT '宿主App',
  `poster_id` int(10) NOT NULL COMMENT '发表者用户ID',
  `poster_name` varchar(50) DEFAULT NULL COMMENT '发表人的姓名或昵称',
  `post_time` datetime NOT NULL COMMENT '发表时间',
  `content` varchar(500) NOT NULL COMMENT '消息内容',
  `record_status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态  0=正常  1=删除',
  `read_url` varchar(100) DEFAULT NULL COMMENT '发送消息的链接地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站内消息，短信';

-- ----------------------------
-- Records of sq_message
-- ----------------------------
