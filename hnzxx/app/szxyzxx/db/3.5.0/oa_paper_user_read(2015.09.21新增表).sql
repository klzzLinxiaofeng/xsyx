/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2015-09-22 09:12:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_paper_user_read`
-- ----------------------------
DROP TABLE IF EXISTS `oa_paper_user_read`;
CREATE TABLE `oa_paper_user_read` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL COMMENT '公文用户所属的单位  学校',
  `owner_type` char(6) NOT NULL COMMENT '组的类型 1：学校',
  `paper_id` int(11) NOT NULL COMMENT '公文id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(2) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_paper_user_read
-- ----------------------------
