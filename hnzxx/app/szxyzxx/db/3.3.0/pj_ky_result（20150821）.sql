/*
Navicat MySQL Data Transfer

Source Server         : szxyzxx
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-21 14:39:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pj_ky_result
-- ----------------------------
DROP TABLE IF EXISTS `pj_ky_result`;
CREATE TABLE `pj_ky_result` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `school_id` int(10) unsigned NOT NULL COMMENT '所属学校',
  `teach_name` varchar(10) NOT NULL COMMENT '教师的名字',
  `teach_id` varchar(10) NOT NULL COMMENT '教师的id',
  `name` varchar(50) NOT NULL COMMENT '名字',
  `type` char(4) NOT NULL COMMENT '成果类型',
  `level` char(4) NOT NULL COMMENT '成果级别',
  `rank` varchar(50) NOT NULL,
  `publication` varchar(50) NOT NULL COMMENT '刊物类型',
  `apply_date` datetime NOT NULL COMMENT '申请时间',
  `apply_score` decimal(10,2) NOT NULL COMMENT '申请学分',
  `check_score` decimal(10,2) NOT NULL COMMENT '核定学分',
  `person_num` int(3) NOT NULL COMMENT '部署人数',
  `Independent` tinyint(1) NOT NULL COMMENT '是否独立完成',
  `file_uuid` varchar(500) DEFAULT NULL COMMENT '多个文件的id',
  `audit` tinyint(1) DEFAULT '0' COMMENT '审核是否通过',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
