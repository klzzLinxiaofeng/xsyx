/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-28 14:30:03
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_paper`
-- ----------------------------
DROP TABLE IF EXISTS `oa_paper`;
CREATE TABLE `oa_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(36) DEFAULT NULL COMMENT 'UUID',
  `app_id` int(10) DEFAULT NULL COMMENT 'appid',
  `owner_id` int(11) DEFAULT NULL COMMENT '公文所属的单位，学校',
  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型，1：学校',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `author` varchar(20) DEFAULT NULL COMMENT '发文单位',
  `poster_id` int(11) DEFAULT NULL COMMENT '发布者ID',
  `poster_name` varchar(50) DEFAULT NULL COMMENT '发布者姓名',
  `document_type` varchar(5) DEFAULT NULL COMMENT '公文种类',
  `emergency_level` varchar(5) DEFAULT NULL COMMENT '公文紧急等级',
  `secret_level` varchar(5) DEFAULT NULL COMMENT '公文机密等级',
  `receiver_type` tinyint(4) DEFAULT NULL COMMENT '接收者类型',
  `receiver_count` tinyint(4) DEFAULT NULL COMMENT '接收者实际人数',
  `read_count` tinyint(4) DEFAULT NULL COMMENT '已阅人数',
  `receiver` varchar(1000) DEFAULT NULL COMMENT '接收者名字',
  `content` text COMMENT '正文内容',
  `attachment_uuid` varchar(36) DEFAULT NULL COMMENT '附件文件ID',
  `remark` varchar(300) DEFAULT NULL COMMENT '摘要',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `publish_date` datetime DEFAULT NULL COMMENT '发布日期',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_paper
-- ----------------------------
INSERT INTO oa_paper VALUES ('47', '7e065803f2224018b210e2348cac8312', '1', '1', '1', '教师评论结果', '学生处', '15', '教师', null, null, null, '2', '1', '0', null, '放大法房产税的', '', '打的是发生地方v', '0', '2015-08-28 00:00:00', '2015-08-28 14:26:32', '2015-08-28 14:27:12');
