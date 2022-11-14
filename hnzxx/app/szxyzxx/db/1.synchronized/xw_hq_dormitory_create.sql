/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy_test

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-06-17 14:17:30
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `xw_hq_dormitory`
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_dormitory`;
CREATE TABLE `xw_hq_dormitory` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `floor_code` char(36) NOT NULL COMMENT '宿舍楼号',
  `floor_name` varchar(50) NOT NULL COMMENT '大楼名称',
  `dormitory_code` char(36) NOT NULL COMMENT '寝室编号',
  `sex` varchar(2) NOT NULL COMMENT '入住性别',
  `capacity` int(4) NOT NULL COMMENT '可住人数',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(2) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xw_hq_dormitory
-- ----------------------------
INSERT INTO xw_hq_dormitory VALUES ('2', '1', 'A01', 'A01', '101', '1', '8', '', '2015-06-11 18:02:40', '2015-06-12 14:14:08', '0');
INSERT INTO xw_hq_dormitory VALUES ('4', '1', 'A02', 'badc', '102', '2', '8', '', '2015-06-12 11:44:01', '2015-06-12 14:19:21', '0');

-- ----------------------------
-- Table structure for `xw_hq_dormitory_attendance`
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_dormitory_attendance`;
CREATE TABLE `xw_hq_dormitory_attendance` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `floor_code` char(36) NOT NULL COMMENT '宿舍楼号',
  `dormitory_code` char(36) NOT NULL COMMENT '寝室编号',
  `school_year_id` int(10) NOT NULL COMMENT '所在学年id',
  `grade_id` int(10) NOT NULL COMMENT '所在年级id',
  `team_number` int(10) NOT NULL COMMENT '所在班级id',
  `student_id` int(10) NOT NULL COMMENT '入住学生id',
  `student_name` varchar(50) NOT NULL COMMENT '入住学生姓名',
  `student_number` varchar(20) NOT NULL COMMENT '入住学生学籍号',
  `attendance_type` varchar(2) NOT NULL COMMENT '考勤类别',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(2) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xw_hq_dormitory_attendance
-- ----------------------------

-- ----------------------------
-- Table structure for `xw_hq_dormitory_daycheck`
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_dormitory_daycheck`;
CREATE TABLE `xw_hq_dormitory_daycheck` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `floor_code` char(36) NOT NULL COMMENT '宿舍楼号',
  `dormitory_code` char(36) NOT NULL COMMENT '寝室编号',
  `school_year_id` int(10) NOT NULL COMMENT '所在学年id',
  `grade_id` int(10) DEFAULT NULL COMMENT '所在年级id',
  `team_number` int(10) DEFAULT NULL COMMENT '所在班级id',
  `check_type` varchar(2) NOT NULL COMMENT '检查类别',
  `check_result` varchar(10) NOT NULL COMMENT '检查结果',
  `remark` varchar(50) DEFAULT NULL COMMENT '检查说明',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(2) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xw_hq_dormitory_daycheck
-- ----------------------------

-- ----------------------------
-- Table structure for `xw_hq_dormitory_person`
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_dormitory_person`;
CREATE TABLE `xw_hq_dormitory_person` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所属学校',
  `floor_code` char(36) NOT NULL COMMENT '宿舍楼号',
  `dormitory_code` char(36) NOT NULL COMMENT '寝室编号',
  `sex` varchar(2) NOT NULL COMMENT '入住性别',
  `capacity` int(4) NOT NULL COMMENT '可住人数',
  `school_year_id` int(10) NOT NULL COMMENT '所在学年id',
  `grade_id` int(10) NOT NULL COMMENT '所在年级id',
  `team_number` int(10) NOT NULL COMMENT '所在班级id',
  `student_id` int(10) NOT NULL COMMENT '入住学生id',
  `student_name` varchar(50) NOT NULL COMMENT '入住学生姓名',
  `student_number` varchar(20) NOT NULL COMMENT '入住学生学籍号(冗余)',
  `bed_number` int(2) DEFAULT NULL COMMENT '床位号（冗余）',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(2) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xw_hq_dormitory_person
-- ----------------------------
INSERT INTO xw_hq_dormitory_person VALUES ('1', '1', 'A01', '101', '1', '8', '1', '1', '1', '15', '周津', 'G411381198512113914', '2', '2015-06-15 17:05:32', '2015-06-15 17:06:35', '0');
INSERT INTO xw_hq_dormitory_person VALUES ('2', '1', 'A01', '101', '1', '8', '1', '1', '1', '14', '李想', 'G123454512131245', '1', '2015-06-15 17:07:30', '2015-06-15 17:07:35', '0');
