/*
Navicat MySQL Data Transfer

Source Server         : 60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-06 12:37:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sq_comment`
-- ----------------------------
DROP TABLE IF EXISTS `sq_comment`;
CREATE TABLE `sq_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `app_id` int(10) NOT NULL COMMENT '宿主APP',
  `object_id` char(36) NOT NULL COMMENT '被评论的记录ID',
  `object_type` varchar(30) NOT NULL COMMENT '评论类型',
  `parent_id` int(10) DEFAULT NULL COMMENT '回复的评论id',
  `poster_id` int(10) NOT NULL COMMENT '发表者用户id',
  `post_name` varchar(50) DEFAULT NULL COMMENT '发表人的姓名或昵称',
  `post_time` datetime NOT NULL COMMENT '发表时间',
  `agrees` int(10) DEFAULT '0' COMMENT '赞同数',
  `disagrees` int(10) DEFAULT '0' COMMENT '反对数',
  `content` varchar(1000) NOT NULL COMMENT '评论内容',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sq_comment
-- ----------------------------
