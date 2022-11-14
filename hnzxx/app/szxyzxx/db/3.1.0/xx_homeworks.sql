/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2015-07-29 10:53:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `xx_homeworks`
-- ----------------------------
DROP TABLE IF EXISTS `xx_homeworks`;
CREATE TABLE `xx_homeworks` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `team_id` int(10) DEFAULT NULL,
  `subject_name` varchar(20) DEFAULT NULL,
  `poster_id` int(10) DEFAULT NULL COMMENT '发送者',
  `post_time` datetime DEFAULT NULL,
  `valid_days` int(11) DEFAULT NULL,
  `receiver_id` int(10) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  `speech` varchar(250) DEFAULT NULL,
  `document` varchar(250) DEFAULT NULL COMMENT '附件文件路径',
  `entity_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `xx_message`
-- ----------------------------
DROP TABLE IF EXISTS `xx_message`;
CREATE TABLE `xx_message` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ReceiverType',
  `school_id` int(10) DEFAULT NULL COMMENT '在所学校ID',
  `title` varchar(50) DEFAULT NULL,
  `poster_id` int(10) DEFAULT NULL COMMENT '送发者ID',
  `post_time` datetime DEFAULT NULL COMMENT '通知发送时间',
  `record_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `valid_days` int(11) DEFAULT NULL COMMENT '有效天数 ',
  `receiver_type` int(11) DEFAULT NULL COMMENT '接收者类别',
  `receiver_id` int(10) DEFAULT NULL COMMENT '接收者ID',
  `state` int(8) DEFAULT NULL COMMENT '0=发送成功 1=取消',
  `content` varchar(1000) DEFAULT NULL COMMENT '送发内容',
  `entity_id` varchar(200) DEFAULT '' COMMENT '关联res_entity表id, 多个以逗号隔开.',
  `post_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `xx_receiver`
-- ----------------------------
DROP TABLE IF EXISTS `xx_receiver`;
CREATE TABLE `xx_receiver` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) DEFAULT NULL,
  `owner_type` int(11) DEFAULT '0' COMMENT '1=通知，2=公文',
  `user_id` int(10) DEFAULT NULL COMMENT '接收者用户ID account.id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '接收者姓名 account.name',
  `receiver_id` int(10) DEFAULT NULL,
  `receiver_name` varchar(50) DEFAULT NULL,
  `receiver_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `xx_remarks`
-- ----------------------------
DROP TABLE IF EXISTS `xx_remarks`;
CREATE TABLE `xx_remarks` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `school_id` int(10) DEFAULT NULL,
  `poster_id` int(10) DEFAULT NULL,
  `poster_time` datetime DEFAULT NULL,
  `poster_name` varchar(50) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `valid_days` int(255) DEFAULT '0',
  `receiver_id` int(10) DEFAULT NULL,
  `receiver_name` varchar(1000) DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `receiver_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `yy_user_stats`
-- ----------------------------
DROP TABLE IF EXISTS `yy_user_stats`;
CREATE TABLE `yy_user_stats` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT 'accounts.id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名 accounts.user_name',
  `name` varchar(50) DEFAULT NULL COMMENT '名姓:   accounts.name',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `on_line_duration` int(11) DEFAULT '0' COMMENT '在线时长',
  `client_os` varchar(100) DEFAULT NULL COMMENT '客户端平台',
  `client_version` varchar(500) DEFAULT NULL COMMENT '客户端平台版本',
  `app_version` varchar(20) DEFAULT NULL COMMENT '使用的App 版本',
  `rank` int(8) DEFAULT '0' COMMENT '等级',
  `score` float DEFAULT '0' COMMENT '前当积分',
  `amount` float DEFAULT '0' COMMENT '号帐总额',
  `total_income` float DEFAULT '0' COMMENT '收入总',
  `total_expense` float DEFAULT '0' COMMENT '总支出',
  `is_deleted` int(11) DEFAULT '0' COMMENT '1 = 志标为删除',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  `first_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8;