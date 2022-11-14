/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-07-24 16:05:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_notice`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notice`;
CREATE TABLE `oa_notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '公告标题',
  `content` text COMMENT '公告描述',
  `type` varchar(50) DEFAULT NULL COMMENT '公告类型',
  `poster_id` int(10) DEFAULT NULL COMMENT '发布者ID',
  `owner_id` int(10) DEFAULT NULL COMMENT '所属的单位,学校ID',
  `owner_type` char(2) DEFAULT NULL COMMENT '所属的单位或学校类型',
  `poster_name` varchar(50) DEFAULT NULL COMMENT '发布者用户名',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` varchar(36) DEFAULT NULL COMMENT 'UUID',
  `receiver_type` int(4) DEFAULT NULL,
  `app_id` int(11) DEFAULT NULL COMMENT 'appid',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除，0：还没删除，1：已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice
-- ----------------------------
INSERT INTO `oa_notice` VALUES ('41', '测试发送全部通知,发送类型为：2，测试接收者有：41', '测试发送全部通知--主内容，测试接收者有：41', '学生处', '15', '1', '1', '潘建安', '2015-07-17 16:59:25', '2015-07-22 11:13:45', null, '2', '1', '0');
INSERT INTO `oa_notice` VALUES ('42', '测试发送全部通知,发送类型为：2，测试接收者有：15', '测试发送全部通知--主内容，测试接收者有：15', '学生处', '41', '1', '1', '张天海', '2015-07-17 16:59:49', '2015-07-22 11:13:46', null, '2', '1', '0');
INSERT INTO `oa_notice` VALUES ('43', '测试发送全部通知,发送类型为：2，测试接收者有：41,61', '测试发送全部通知--主内容，测试接收者有：41,61', '学生处', '15', '1', '1', '潘建安', '2015-07-17 17:07:00', '2015-07-22 11:13:47', null, '2', '1', '0');
INSERT INTO `oa_notice` VALUES ('44', '测试发送全部通知,发送类型为：2，测试接收者有：15,61', '测试发送全部通知--主内容，测试接收者有：15,61', '学生处', '41', '1', '1', '张天海', '2015-07-17 17:07:13', '2015-07-22 11:13:49', null, '2', '1', '0');
INSERT INTO `oa_notice` VALUES ('45', '测试删除1', '测试删除1,接收人有41,61', '开发部', '15', '1', '1', '潘建安', '2015-07-22 15:28:31', '2015-07-22 15:28:31', null, '2', '1', '0');
INSERT INTO `oa_notice` VALUES ('46', '测试删除2', '测试删除2,接收人有15,61', '开发部', '41', '1', '1', '张天海', '2015-07-22 15:28:48', '2015-07-22 15:28:48', null, '2', '1', '0');
INSERT INTO `oa_notice` VALUES ('47', '测试删除3', '测试删除3,接收人有15,41', '开发部', '61', '1', '1', '小明', '2015-07-22 15:28:59', '2015-07-22 15:28:59', null, '2', '1', '0');
