/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-28 14:29:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_applay_india`
-- ----------------------------
DROP TABLE IF EXISTS `oa_applay_india`;
CREATE TABLE `oa_applay_india` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL COMMENT '发布文印所属单位 1：学校',
  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型   1：学校',
  `proposer_id` int(10) NOT NULL COMMENT '申请人id(用户id)',
  `proposer_name` varchar(50) DEFAULT NULL COMMENT '申请人姓名(用户姓名)(冗余字段）',
  `mobile` varchar(11) DEFAULT NULL,
  `department_id` int(10) DEFAULT NULL COMMENT '申请人所属部门id',
  `india_status` varchar(2) NOT NULL COMMENT '处理状态  （0：待处理 1：未处理 2：处理中 3：已处理）',
  `title` varchar(40) NOT NULL COMMENT '标题',
  `publish_date` datetime NOT NULL COMMENT '发布日期',
  `remark` varchar(120) DEFAULT NULL COMMENT '申请说明',
  `upload_id` char(36) DEFAULT NULL COMMENT '上传的文件id',
  `delivery_method` varchar(2) DEFAULT NULL COMMENT '提货方式   (0：送货   1：自提)',
  `treat_date` datetime DEFAULT NULL COMMENT '已处理时间',
  `non_treatment_cause` varchar(140) DEFAULT NULL COMMENT '未处理事由',
  `expected_completion_time` datetime DEFAULT NULL COMMENT '预计完成时间',
  `is_deleted` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_applay_india
-- ----------------------------
