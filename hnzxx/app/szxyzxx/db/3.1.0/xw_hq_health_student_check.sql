/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-29 09:33:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xw_hq_health_student_check
-- ----------------------------
DROP TABLE IF EXISTS `xw_hq_health_student_check`;
CREATE TABLE `xw_hq_health_student_check` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校',
  `team_id` int(10) NOT NULL COMMENT '学生所在班级pj_team.id',
  `student_id` int(10) NOT NULL COMMENT '学生pj_student.id',
  `check_date` datetime NOT NULL COMMENT '晨检日期',
  `symptom` varchar(50) NOT NULL COMMENT '主要症状',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '作废标记',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='校务后勤卫生管理--学生晨检症状';
