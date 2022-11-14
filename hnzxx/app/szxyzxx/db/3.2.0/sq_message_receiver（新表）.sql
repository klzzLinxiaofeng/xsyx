/*
Navicat MySQL Data Transfer

Source Server         : 60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-07 14:55:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sq_message_receiver`
-- ----------------------------
DROP TABLE IF EXISTS `sq_message_receiver`;
CREATE TABLE `sq_message_receiver` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `message_id` int(10) NOT NULL COMMENT '对应的消息ID',
  `receiver_id` int(10) NOT NULL COMMENT '接收者ID',
  `receive_name` varchar(50) DEFAULT NULL COMMENT '接收者姓名',
  `read_status` varchar(2) NOT NULL DEFAULT '0' COMMENT '阅读状态',
  `record_status` varchar(2) NOT NULL DEFAULT '0' COMMENT '记录状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息接收者';

-- ----------------------------
-- Records of sq_message_receiver
-- ----------------------------
