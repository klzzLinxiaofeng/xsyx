/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-10-16 16:39:12
*/

-- ----------------------------
-- Table structure for jc_stage
-- ----------------------------
ALTER TABLE `edu_gzzhxy`.`oa_device` 
ADD COLUMN `total_number` INT(10) NULL COMMENT '设备数量' AFTER `modify_date`;
