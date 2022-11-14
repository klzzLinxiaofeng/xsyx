/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-09 17:13:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_repair
-- ----------------------------
DROP TABLE IF EXISTS `oa_repair`;
CREATE TABLE `oa_repair` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` char(36) NOT NULL COMMENT 'UUID',
  `app_id` int(10) NOT NULL,
  `owner_id` int(10) NOT NULL COMMENT '所属的单位、学校',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `poster_id` int(10) NOT NULL COMMENT '保修人',
  `poster_name` varchar(50) DEFAULT NULL COMMENT '保修人姓名',
  `post_time` datetime DEFAULT NULL COMMENT '保修时间',
  `department_id` int(10) NOT NULL COMMENT '保修部门',
  `department_name` varchar(50) DEFAULT NULL COMMENT '保修部门（冗余）',
  `deal_status` varchar(2) DEFAULT NULL COMMENT '维修状态',
  `receiver_id` varchar(100) DEFAULT NULL COMMENT '受理人',
  `handler` varchar(50) DEFAULT NULL COMMENT '负责人、维修人',
  `handler_phone` varchar(50) DEFAULT NULL COMMENT '负责人联系电话',
  `deal_time` datetime DEFAULT NULL COMMENT '受理时间',
  `finish_time` datetime DEFAULT NULL COMMENT '维修完成时间',
  `content` varchar(500) DEFAULT NULL COMMENT '维修内容',
  `score` float DEFAULT NULL COMMENT '评价得分',
  `comment` varchar(200) DEFAULT NULL COMMENT '评价内容',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `owner_type` char(6) NOT NULL COMMENT '组的类型 1.学校',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
