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
-- Table structure for hw_homework_user_record
-- ----------------------------
ALTER TABLE `hw_homework_user_record` 
ADD COLUMN `entity_id` VARCHAR(500) NOT NULL COMMENT '文件id组成的字符串（”，“）';
ALTER TABLE `hw_homework_user_record` 
ADD COLUMN `content` VARCHAR(500) NOT NULL COMMENT '作业提交描述'
