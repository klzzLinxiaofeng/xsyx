/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy_test

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-07-02 14:43:18
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `oa_india`
-- ----------------------------
DROP TABLE IF EXISTS `oa_india`;
CREATE TABLE `oa_india` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `school_id` int(10) NOT NULL COMMENT '所属学校id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户姓名(冗余)',
  `department_id` int(10) NOT NULL COMMENT '所属部门id',
  `title` varchar(50) NOT NULL COMMENT '文印标题',
  `upload_file` varchar(100) NOT NULL COMMENT '上传的文件路径',
  `india_status` varchar(2) NOT NULL COMMENT '打印状态 0：待处理； 1：进行中；2：已完成',
  `remark` varchar(100) DEFAULT NULL COMMENT '说明(附注)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_india
-- ----------------------------
