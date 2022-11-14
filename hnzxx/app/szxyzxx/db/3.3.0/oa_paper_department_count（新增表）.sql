/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-28 14:30:14
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_paper_department_count`
-- ----------------------------
DROP TABLE IF EXISTS `oa_paper_department_count`;
CREATE TABLE `oa_paper_department_count` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL COMMENT '发布公文人员所属单位  学校',
  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型：1 学校',
  `department_id` int(11) DEFAULT NULL COMMENT '对应发布的部门id',
  `count` int(11) DEFAULT NULL COMMENT '对应部门的公文总数',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_paper_department_count
-- ----------------------------
INSERT INTO oa_paper_department_count VALUES ('1', '1', '1', '1', '0', '2015-08-28 14:26:32', '2015-08-28 14:26:58', '0');
INSERT INTO oa_paper_department_count VALUES ('2', '1', '1', '4', '0', '2015-08-28 14:26:32', '2015-08-28 14:26:41', '0');
