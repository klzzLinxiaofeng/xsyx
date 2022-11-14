/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-21 10:27:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pj_class_selection
-- ----------------------------
DROP TABLE IF EXISTS `pj_class_selection`;
CREATE TABLE `pj_class_selection` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '学生选课ID',
  `school_id` int(10) NOT NULL COMMENT '所在学校 pj_school.id',
  `public_class_id` int(10) NOT NULL COMMENT '课程ID pj_public_class.id',
  `team_id` int(10) DEFAULT NULL COMMENT '所在班级 pj_team.id',
  `student_id` int(10) NOT NULL COMMENT '学生ID pj_student.id',
  `is_delete` bit(1) NOT NULL COMMENT '作废标记',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='开课选课管理--选课管理';
