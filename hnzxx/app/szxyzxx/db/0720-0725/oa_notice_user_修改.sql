/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-07-24 16:05:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_notice_user`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notice_user`;
CREATE TABLE `oa_notice_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receiver_id` int(10) DEFAULT NULL COMMENT '接收者ID',
  `notice_id` int(10) DEFAULT NULL COMMENT '关联的公告ID',
  `receiver_name` varchar(50) DEFAULT NULL COMMENT '接收者姓名或名称（冗余）',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice_user
-- ----------------------------
INSERT INTO `oa_notice_user` VALUES ('58', '15', '41', '教师', '2015-07-17 16:59:25', '2015-07-22 11:15:31', '0');
INSERT INTO `oa_notice_user` VALUES ('59', '41', '41', '罗盘', '2015-07-17 16:59:25', '2015-07-22 11:15:32', '0');
INSERT INTO `oa_notice_user` VALUES ('60', '41', '42', '罗盘', '2015-07-17 16:59:49', '2015-07-22 11:15:33', '0');
INSERT INTO `oa_notice_user` VALUES ('61', '15', '42', '教师', '2015-07-17 16:59:49', '2015-07-22 11:15:33', '0');
INSERT INTO `oa_notice_user` VALUES ('62', '15', '43', '教师', '2015-07-17 17:07:00', '2015-07-22 11:15:34', '0');
INSERT INTO `oa_notice_user` VALUES ('63', '41', '43', '罗盘', '2015-07-17 17:07:00', '2015-07-22 11:15:34', '0');
INSERT INTO `oa_notice_user` VALUES ('64', '61', '43', '测试', '2015-07-17 17:07:00', '2015-07-22 15:27:51', '1');
INSERT INTO `oa_notice_user` VALUES ('65', '41', '44', '罗盘', '2015-07-17 17:07:13', '2015-07-22 11:15:36', '0');
INSERT INTO `oa_notice_user` VALUES ('66', '15', '44', '教师', '2015-07-17 17:07:13', '2015-07-22 11:15:36', '0');
INSERT INTO `oa_notice_user` VALUES ('67', '61', '44', '测试', '2015-07-17 17:07:13', '2015-07-22 15:28:23', '1');
INSERT INTO `oa_notice_user` VALUES ('68', '15', '45', '教师', '2015-07-22 15:28:32', '2015-07-22 16:13:20', '1');
INSERT INTO `oa_notice_user` VALUES ('69', '41', '45', '罗盘', '2015-07-22 15:28:32', '2015-07-22 15:28:32', '0');
INSERT INTO `oa_notice_user` VALUES ('70', '61', '45', '测试', '2015-07-22 15:28:32', '2015-07-22 15:28:32', '0');
INSERT INTO `oa_notice_user` VALUES ('71', '41', '46', '罗盘', '2015-07-22 15:28:48', '2015-07-22 15:28:48', '0');
INSERT INTO `oa_notice_user` VALUES ('72', '15', '46', '教师', '2015-07-22 15:28:48', '2015-07-22 15:28:48', '0');
INSERT INTO `oa_notice_user` VALUES ('73', '61', '46', '测试', '2015-07-22 15:28:48', '2015-07-22 15:28:48', '0');
INSERT INTO `oa_notice_user` VALUES ('74', '61', '47', '测试', '2015-07-22 15:28:59', '2015-07-22 15:30:03', '1');
INSERT INTO `oa_notice_user` VALUES ('75', '15', '47', '教师', '2015-07-22 15:28:59', '2015-07-22 15:28:59', '0');
INSERT INTO `oa_notice_user` VALUES ('76', '41', '47', '罗盘', '2015-07-22 15:28:59', '2015-07-22 15:28:59', '0');
