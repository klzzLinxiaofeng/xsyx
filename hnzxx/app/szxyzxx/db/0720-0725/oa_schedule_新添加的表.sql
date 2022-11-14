/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-07-24 16:05:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_schedule`
-- ----------------------------
DROP TABLE IF EXISTS `oa_schedule`;
CREATE TABLE `oa_schedule` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(36) DEFAULT NULL COMMENT 'uuid',
  `app_id` int(10) DEFAULT NULL COMMENT 'app.id',
  `owner_id` int(11) DEFAULT NULL COMMENT '所属的单位，学校',
  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型，1：学校',
  `poster_id` int(11) DEFAULT NULL COMMENT '发布者ID',
  `poster_name` varchar(50) DEFAULT NULL COMMENT '发布者姓名',
  `plan_start_time` varchar(25) DEFAULT NULL COMMENT '日程安排开始时间',
  `plan_finish_time` varchar(25) DEFAULT NULL COMMENT '日程安排结束时间',
  `share_to` varchar(2) DEFAULT NULL COMMENT '共享对象范围',
  `rank` int(10) DEFAULT NULL COMMENT '优先等级,0=普通，1=重要',
  `content` varchar(500) DEFAULT NULL COMMENT '正文内容',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：还没删除，1：已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_schedule
-- ----------------------------
