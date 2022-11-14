/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-05-29 18:22:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pj_student_score`
-- ----------------------------
DROP TABLE IF EXISTS `pj_student_score`;
CREATE TABLE `pj_student_score` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `exam_team_subject_id` int(10) NOT NULL COMMENT '考试日程安排表主键 pj_exam_team_subject.id',
  `student_id` int(10) NOT NULL COMMENT '学生id，关联学生表主键 pj_student.id',
  `subject_code` varchar(36) NOT NULL COMMENT '科目代码',
  `score` varchar(5) NOT NULL COMMENT '成绩',
  `team_rank` varchar(6) DEFAULT NULL COMMENT '班级排名',
  `grade_rank` varchar(6) DEFAULT NULL COMMENT '年级排名',
  `is_delete` bit(1) NOT NULL COMMENT '是否删除，0==不删除，1===删除',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `school_year` varchar(15) NOT NULL COMMENT '学年',
  `term_code` varchar(50) NOT NULL COMMENT '学期',
  `team_id` int(10) NOT NULL COMMENT '班级',
  `grade_id` int(10) NOT NULL COMMENT '年级',
  `exam_type` varchar(5) NOT NULL COMMENT '考试类型',
  `exam_name` varchar(100) NOT NULL COMMENT '考试名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='学生成绩';

-- ----------------------------
-- Records of pj_student_score
