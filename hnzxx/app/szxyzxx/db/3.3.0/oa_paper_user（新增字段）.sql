/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-28 14:30:21
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_paper_user`
-- ----------------------------
DROP TABLE IF EXISTS `oa_paper_user`;
CREATE TABLE `oa_paper_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `owner_id` int(11) DEFAULT NULL COMMENT '公文用户所属的单位  学校',
  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型 1：学校',
  `paper_id` int(11) DEFAULT NULL COMMENT '对应的公文记录',
  `receiver_id` int(10) DEFAULT NULL COMMENT '接收者id',
  `receiver_name` varchar(100) DEFAULT NULL COMMENT '接收者名字',
  `department_id` int(10) DEFAULT NULL COMMENT '接收者部门id',
  `read_status` tinyint(1) DEFAULT NULL COMMENT '是否已阅',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_paper_user
-- ----------------------------
INSERT INTO oa_paper_user VALUES ('5', null, null, '47', '15', '教师', null, '0', '2015-08-28 14:27:12', '2015-08-28 14:27:12', '0');
