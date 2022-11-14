/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-01 10:58:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `zs_new_student`
-- ----------------------------
DROP TABLE IF EXISTS `zs_new_student`;
CREATE TABLE `zs_new_student` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主健ID',
  `school_id` int(10) NOT NULL COMMENT '学校',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `studentType` varchar(5) DEFAULT NULL COMMENT '学生类型',
  `studentNum` varchar(20) DEFAULT NULL COMMENT '学籍号',
  `readingState` varchar(5) DEFAULT NULL COMMENT '在读状态',
  `entrollSchoolDate` datetime DEFAULT NULL COMMENT '入学时间',
  `studentId` int(20) DEFAULT NULL COMMENT '学生ID',
  `userName` varchar(10) DEFAULT NULL COMMENT '用户名',
  `englishName` varchar(50) DEFAULT NULL COMMENT '英文名字',
  `sex` char(2) DEFAULT NULL COMMENT '性别',
  `schoolYear` varchar(20) DEFAULT '' COMMENT '学年',
  `id_card_type` char(2) DEFAULT NULL COMMENT '证件类型',
  `id_card_number` varchar(22) DEFAULT NULL COMMENT '证件号码',
  `nationality` varchar(20) DEFAULT NULL COMMENT '国籍',
  `race` char(5) DEFAULT NULL COMMENT '民族',
  `birth_date` datetime DEFAULT NULL COMMENT '出生日期',
  `native_place` varchar(20) DEFAULT NULL COMMENT '籍贯',
  `birth_place` varchar(20) DEFAULT NULL COMMENT '出生地（行政区划）',
  `residence_type` varchar(30) DEFAULT NULL COMMENT '户口性质',
  `residence_address` varchar(100) DEFAULT NULL COMMENT '户口所在地',
  `address` varchar(100) DEFAULT NULL COMMENT '现住地址',
  `reside_address` varchar(100) DEFAULT NULL COMMENT '居住地址',
  `political_status` char(5) DEFAULT NULL COMMENT '政治面貌',
  `religion` char(5) DEFAULT NULL COMMENT '宗教信仰',
  `is_only_child` char(5) DEFAULT NULL COMMENT '是否独生子',
  `telephone` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮件',
  `region_states` varchar(10) DEFAULT '0' COMMENT '注册状态',
  `enroll_person` varchar(50) DEFAULT NULL COMMENT '招生人',
  `enroll_tel` varchar(50) DEFAULT NULL COMMENT '招生电话',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '标志是否删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
