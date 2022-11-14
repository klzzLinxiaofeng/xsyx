/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-14 15:25:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ld_learning_design_publish`
-- ----------------------------
DROP TABLE IF EXISTS `ld_learning_design_publish`;
CREATE TABLE `ld_learning_design_publish` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  `real_micro_list` varchar(4000) NOT NULL COMMENT '关联课件的json',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `start_date` datetime DEFAULT NULL COMMENT '微课开始时间',
  `finished_date` datetime DEFAULT NULL COMMENT '微课结束时间',
  `published_flag` int(5) NOT NULL COMMENT '发布标记   1 已发布  2 未发布  3 已过期',
  `publisher_id` int(10) DEFAULT NULL COMMENT '发布者userId',
  `publisher_name` varchar(20) DEFAULT NULL COMMENT '发布者姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `ld_learning_design_published_record`
-- ----------------------------
DROP TABLE IF EXISTS `ld_learning_design_published_record`;
CREATE TABLE `ld_learning_design_published_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `published_micro_id` char(36) NOT NULL COMMENT '关联已发布的微课id',
  `reviews` varchar(550) DEFAULT NULL COMMENT '评语',
  `reward` int(11) DEFAULT NULL COMMENT '奖励  1   一星    2  二星    3  三星',
  `finished_flag` int(10) DEFAULT NULL COMMENT '完成标记   1  已完成    2  未完成   ',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `user_name` varchar(10) DEFAULT NULL COMMENT '用户名',
  `finished_date` datetime DEFAULT NULL COMMENT '完成日期时间',
  `student_number` varchar(20) DEFAULT NULL COMMENT '全国统一学籍号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `ld_learning_design_relate`
-- ----------------------------
DROP TABLE IF EXISTS `ld_learning_design_relate`;
CREATE TABLE `ld_learning_design_relate` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `relate_id` int(10) NOT NULL COMMENT '发布对象id  (可以是单个人或一个组织一个班一个机构)',
  `publish_micro_lesson_id` char(36) NOT NULL COMMENT '关联的课件发布对象',
  `relate_name` varchar(200) DEFAULT NULL COMMENT '发布对象姓名或名称  (可以是单个人或一个组织一个班一个机构)',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `start_date` datetime DEFAULT NULL COMMENT '微课开始时间',
  `finished_date` datetime DEFAULT NULL COMMENT '微课结束时间',
  `publisher_id` int(10) DEFAULT NULL COMMENT '发布者userId',
  `publisher_name` varchar(20) DEFAULT NULL COMMENT '发布者姓名',
  `real_micro_list` varchar(4000) DEFAULT NULL COMMENT '关联课件的json',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `ld_learning_design_user_record`
-- ----------------------------
DROP TABLE IF EXISTS `ld_learning_design_user_record`;
CREATE TABLE `ld_learning_design_user_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `micro_id` char(36) NOT NULL COMMENT '关联的课件id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `user_name` varchar(10) DEFAULT NULL COMMENT '用户名',
  `finished_flag` int(10) DEFAULT NULL COMMENT '完成标记   1  已完成    2  未完成   ',
  `finished_date` datetime DEFAULT NULL COMMENT '完成日期时间',
  `play_time` int(11) DEFAULT NULL COMMENT '浏览次数',
  `last_play_time` double(11,0) DEFAULT NULL COMMENT '记录最后一次的播放时间',
  `student_number` varchar(20) DEFAULT NULL COMMENT '全国统一学籍号',
  `publish_lesson_id` char(36) DEFAULT NULL COMMENT '关联已发布的课程uuid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

