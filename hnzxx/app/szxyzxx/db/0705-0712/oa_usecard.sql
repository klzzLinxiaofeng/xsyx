/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-09 17:13:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_usecard
-- ----------------------------
DROP TABLE IF EXISTS `oa_usecard`;
CREATE TABLE `oa_usecard` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `plate_number` varchar(20) NOT NULL COMMENT '车牌号',
  `card_user` varchar(2000) DEFAULT NULL COMMENT '用车人员',
  `application` varchar(200) DEFAULT NULL COMMENT '用途',
  `use_date` datetime DEFAULT NULL COMMENT '出车时间',
  `proposer` varchar(50) DEFAULT NULL COMMENT '申请人',
  `approval` varchar(50) DEFAULT NULL COMMENT '审批人',
  `status` varchar(20) DEFAULT NULL COMMENT '申请状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除标志',
  `school_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
