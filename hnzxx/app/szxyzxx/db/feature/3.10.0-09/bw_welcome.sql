/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50513
Source Host           : 127.0.0.1:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2016-04-22 17:30:24
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `bw_welcome`  
-- ----------------------------
DROP TABLE IF EXISTS `bw_welcome`;
CREATE TABLE `bw_welcome` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `school_id` int(10) unsigned DEFAULT NULL COMMENT '学校id（pj_school.id）',
  `team_id` int(10) unsigned DEFAULT NULL COMMENT ' 班级id（pj_team.id）',
  `background_type` varchar(2) DEFAULT NULL COMMENT '播放背景类型   默认为2 type=1：模板 type=2：颜色码',
  `background_color` varchar(10) NOT NULL COMMENT '播放背景十六进制颜色码   默认为#ffffff  例如：#FF000',
  `file_uuid` varchar(36) DEFAULT NULL COMMENT '当前使用背景文件的uuid（bw_template.file_uuid）',
  `content` varchar(50) DEFAULT NULL COMMENT '欢迎词',
  `play_speed` int(2) DEFAULT NULL COMMENT '播放速度(0-10)默认为0',
  `font_size` int(3) DEFAULT NULL COMMENT '字体大小  (5-100)默认为10',
  `font_color` varchar(10) DEFAULT NULL COMMENT '字体颜色 默认为#000000',
  `font_backcolor` varchar(10) DEFAULT NULL COMMENT '字体背景颜色 默认为null',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '作废标记',
  `create_date` datetime DEFAULT NULL COMMENT '记录创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bw_welcome
-- ----------------------------
INSERT INTO bw_welcome VALUES ('1', '0', '0', '2', '#ffffff  ', null, '这是一条欢迎词', '0', '10', '#000000', null, '', '2016-04-20 09:23:00', '2016-04-20 09:23:04');
