/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.60
Source Server Version : 50625
Source Host           : 192.168.10.60:3306
Source Database       : edu_gzzhxy

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-11-03 12:09:47
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `lads_activity`
-- ----------------------------
DROP TABLE IF EXISTS `lads_activity`;
CREATE TABLE `lads_activity` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `learningdesign` char(36) NOT NULL COMMENT '课程',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `parent_activity` char(36) DEFAULT NULL COMMENT '父活动',
  `description` varchar(4000) DEFAULT NULL COMMENT '备注',
  `activity_type` varchar(200) NOT NULL COMMENT '活动类型',
  `tool_library` char(36) DEFAULT NULL COMMENT '工具库类型',
  `tool_id` char(36) DEFAULT NULL COMMENT '工具id',
  `score` varchar(100) DEFAULT NULL COMMENT '分数',
  `taught` varchar(100) DEFAULT NULL COMMENT '修课类型:   选修或必修',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `stop_time` datetime DEFAULT NULL COMMENT '结束时间',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=509 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for `lads_activity_transition`
-- ----------------------------
DROP TABLE IF EXISTS `lads_activity_transition`;
CREATE TABLE `lads_activity_transition` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `from_activity` char(36) NOT NULL COMMENT '前活动',
  `to_activity` char(36) NOT NULL COMMENT '后活动',
  `learningdesign` char(36) NOT NULL COMMENT '课程',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for `lads_activity_type`
-- ----------------------------
DROP TABLE IF EXISTS `lads_activity_type`;
CREATE TABLE `lads_activity_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_name` varchar(200) NOT NULL COMMENT '活动类型名称',
  `activity_description` varchar(4000) DEFAULT NULL COMMENT '活动类型描述',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_activity_type
-- ----------------------------
INSERT INTO lads_activity_type VALUES ('1', 'tool', '工具', '1', '2015-05-22 12:00:48', '2015-05-22 12:00:49');
INSERT INTO lads_activity_type VALUES ('2', 'group', '组', '2', '2015-05-22 12:00:53', '2015-05-22 12:00:53');

-- ----------------------------
-- Table structure for `lads_app_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_app_tool`;
CREATE TABLE `lads_app_tool` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` char(36) NOT NULL,
  `tool_ids` varchar(200) NOT NULL,
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for `lads_discuss_reply_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_discuss_reply_tool`;
CREATE TABLE `lads_discuss_reply_tool` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` text NOT NULL,
  `discuss` char(36) NOT NULL COMMENT '主题',
  `parent_reply` char(36) DEFAULT NULL COMMENT '回复别人的回复',
  `attachment` longtext COMMENT '上传附件的json，包含文件名，文件路径等信息',
  `user_id` int(10) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  PRIMARY KEY (`id`),
  KEY `FK_LADS_DISCUSS_REPLY_TOOL_PARENT_REPLY` (`parent_reply`),
  KEY `FK_LADS_DISCUSS_REPLY_TOOL_DISCUSS` (`discuss`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for `lads_discuss_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_discuss_tool`;
CREATE TABLE `lads_discuss_tool` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '讨论内容',
  `tool_library` char(36) NOT NULL COMMENT '工具类型',
  `allow_upload` varchar(20) DEFAULT NULL COMMENT '是否允许学员上传附件',
  `tool_id` char(36) DEFAULT NULL COMMENT '工具id',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `stop_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  PRIMARY KEY (`id`),
  KEY `FK_LADS_DISCUSS_TOOL_LIBRARY` (`tool_library`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for `lads_discuss_user_status_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_discuss_user_status_tool`;
CREATE TABLE `lads_discuss_user_status_tool` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `discuss` char(36) NOT NULL COMMENT '主题',
  `user_id` int(10) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `score` varchar(100) DEFAULT NULL COMMENT '学习成绩',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  PRIMARY KEY (`id`),
  KEY `DISCUSS` (`discuss`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for `lads_editor_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_editor_tool`;
CREATE TABLE `lads_editor_tool` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `tool_library` char(36) NOT NULL COMMENT '工具类型',
  `upload_list` varchar(4000) DEFAULT NULL COMMENT '上传资源',
  `content` longtext COMMENT '富文本编辑内容',
  `tool_id` char(36) DEFAULT NULL COMMENT '工具id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for `lads_editor_user_status_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_editor_user_status_tool`;
CREATE TABLE `lads_editor_user_status_tool` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `use_time` time DEFAULT NULL COMMENT '学习时间',
  `status` varchar(200) DEFAULT NULL COMMENT '学习状态',
  `editor_tool` char(36) NOT NULL COMMENT '联关的在线学习工具id',
  `study_time` int(10) DEFAULT NULL COMMENT '学习次数',
  `score` varchar(100) DEFAULT NULL COMMENT '学习成绩',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for `lads_faceteaching_status_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_faceteaching_status_tool`;
CREATE TABLE `lads_faceteaching_status_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `FACETEACHING_TOOL` char(36) NOT NULL,
  `USER_ID` char(36) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `STATUS` varchar(200) DEFAULT NULL COMMENT '出勤状况',
  `SCORE` varchar(100) DEFAULT NULL COMMENT '学习成绩',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_FACETEACHING_USER_STATUS_TOOL_FACETEACHING` (`FACETEACHING_TOOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_faceteaching_status_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_faceteaching_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_faceteaching_tool`;
CREATE TABLE `lads_faceteaching_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `TITLE` varchar(200) DEFAULT NULL COMMENT '标题',
  `START_TIME` datetime DEFAULT NULL COMMENT '开始时间',
  `STOP_TIME` datetime DEFAULT NULL COMMENT '结束时间',
  `DESCRIPTION` varchar(4000) DEFAULT NULL COMMENT '介绍',
  `ADDRESS` varchar(4000) DEFAULT NULL COMMENT '地址',
  `TOOL_LIBRARY` char(36) NOT NULL COMMENT '工具类型',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `TOOL_ID` char(36) DEFAULT NULL COMMENT '工具id',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_FACETEACHING_TOOL_LIBRARY` (`TOOL_LIBRARY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_faceteaching_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_faceteaching_user_status_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_faceteaching_user_status_tool`;
CREATE TABLE `lads_faceteaching_user_status_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `FACETEACHING_TOOL` char(36) NOT NULL,
  `USER_ID` char(36) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `STATUS` varchar(200) DEFAULT NULL COMMENT '出勤状况',
  `SCORE` varchar(100) DEFAULT NULL COMMENT '学习成绩',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_FACETEACHING_USER_STATUS_TOOL_FACETEACHING` (`FACETEACHING_TOOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_faceteaching_user_status_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_last_study_record`
-- ----------------------------
DROP TABLE IF EXISTS `lads_last_study_record`;
CREATE TABLE `lads_last_study_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ldid` char(36) NOT NULL COMMENT 'lads课件的id',
  `last_tool_id` char(36) NOT NULL COMMENT '最后学习到哪个活动的id',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` int(10) NOT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for `lads_learningdesign`
-- ----------------------------
DROP TABLE IF EXISTS `lads_learningdesign`;
CREATE TABLE `lads_learningdesign` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `description` varchar(4000) DEFAULT NULL COMMENT '备注',
  `user_id` char(36) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `first_activity` char(36) DEFAULT NULL COMMENT '第一个活动',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `published` varchar(10) DEFAULT NULL COMMENT '断判该课件是否已发布,1为已发布,0或null为未发布',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for `lads_media_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_media_tool`;
CREATE TABLE `lads_media_tool` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `tool_library` char(36) NOT NULL COMMENT '工具类型',
  `upload_list` varchar(4000) DEFAULT NULL COMMENT '上传资源',
  `entity_id` varchar(200) DEFAULT NULL,
  `tool_id` char(36) DEFAULT NULL COMMENT '工具id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `lads_media_user_status_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_media_user_status_tool`;
CREATE TABLE `lads_media_user_status_tool` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `status` varchar(200) DEFAULT NULL COMMENT '学习状态',
  `media_tool` char(36) NOT NULL COMMENT '关联的微课工具id',
  `study_time` int(10) DEFAULT NULL COMMENT '学习次数',
  `score` varchar(100) DEFAULT NULL COMMENT '学习成绩',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  `last_play_time` double(11,0) DEFAULT NULL COMMENT '记录最后一次的播放时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `lads_powerpoint_status_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_powerpoint_status_tool`;
CREATE TABLE `lads_powerpoint_status_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `USER_ID` char(36) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `CREATE_TIME` datetime NOT NULL COMMENT '上传时间',
  `USE_TIME` time DEFAULT NULL COMMENT '学习时间',
  `STATUS` varchar(200) DEFAULT NULL COMMENT '学习状态',
  `STUDY_TIME` int(10) DEFAULT NULL COMMENT '学习次数',
  `SCORE` varchar(100) DEFAULT NULL COMMENT '学习成绩',
  `POWER_POINT_TOOL` char(36) NOT NULL COMMENT '关联的ppt工具',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_POWERPOINT_USER_STATUS_TOOL_POWERPOINT` (`POWER_POINT_TOOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_powerpoint_status_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_powerpoint_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_powerpoint_tool`;
CREATE TABLE `lads_powerpoint_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `TITLE` varchar(200) DEFAULT NULL COMMENT '标题',
  `TOOL_LIBRARY` char(36) NOT NULL COMMENT '工具类型',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `TOOL_ID` char(36) DEFAULT NULL COMMENT '工具id',
  `FILE` char(36) DEFAULT NULL COMMENT '关联的文件系统file',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_POWERPOINT_TOOL_TOOL_LIBRARY` (`TOOL_LIBRARY`),
  KEY `FK_LADS_POWERPOINT_TOOL_FILE` (`FILE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_powerpoint_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_powerpoint_user_status_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_powerpoint_user_status_tool`;
CREATE TABLE `lads_powerpoint_user_status_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `USER_ID` char(36) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `CREATE_TIME` datetime NOT NULL COMMENT '上传时间',
  `USE_TIME` time DEFAULT NULL COMMENT '学习时间',
  `STATUS` varchar(200) DEFAULT NULL COMMENT '学习状态',
  `STUDY_TIME` int(10) DEFAULT NULL COMMENT '学习次数',
  `SCORE` varchar(100) DEFAULT NULL COMMENT '学习成绩',
  `POWER_POINT_TOOL` char(36) NOT NULL COMMENT '关联的ppt工具',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_POWERPOINT_USER_STATUS_TOOL_POWERPOINT` (`POWER_POINT_TOOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_powerpoint_user_status_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_preview_json`
-- ----------------------------
DROP TABLE IF EXISTS `lads_preview_json`;
CREATE TABLE `lads_preview_json` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `content` longtext NOT NULL COMMENT '内容',
  `uuid` char(36) NOT NULL COMMENT '做关联的uuid',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for `lads_quiz_question_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_quiz_question_tool`;
CREATE TABLE `lads_quiz_question_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `DIRECTION` text,
  `RIGHT_ANSWER_COUNT` int(10) NOT NULL,
  `WRONG_ANSWER_COUNT` int(10) NOT NULL,
  `TYPE` varchar(200) NOT NULL,
  `RIGHT_ANSWER` text,
  `XML_CONTENT` text NOT NULL,
  `QUIZ` char(36) NOT NULL,
  `QUESTION_ID` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_QUIZ_QUESTION_TOOL_QUIZ` (`QUIZ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_quiz_question_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_quiz_result_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_quiz_result_tool`;
CREATE TABLE `lads_quiz_result_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `XML_CONTENT` text NOT NULL,
  `HTTP_URL` varchar(4000) NOT NULL,
  `ABSOLUTE_PATH` varchar(4000) NOT NULL DEFAULT '文件存放绝对路径',
  `USE_TIME` time DEFAULT NULL COMMENT '使用时间',
  `CREATE_TIME` datetime NOT NULL COMMENT '上传时间',
  `SCORE` varchar(4000) NOT NULL,
  `TOTAL_SCORE` varchar(4000) DEFAULT NULL,
  `PASS_RATE` double(16,0) DEFAULT NULL,
  `TITLE` varchar(4000) NOT NULL,
  `USER_ID` char(36) DEFAULT NULL COMMENT '这个userid指的是使用者的id或者有使用权的id',
  `QUIZ` char(36) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_QUIZ_RESULT_QUIZ` (`QUIZ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_quiz_result_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_quiz_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_quiz_tool`;
CREATE TABLE `lads_quiz_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `TOOL_LIBRARY` char(36) NOT NULL COMMENT '工具类型',
  `TITLE` varchar(200) DEFAULT NULL COMMENT '标题',
  `XML_CONTENT` longtext COMMENT 'xml内容',
  `HTTP_URL` varchar(4000) DEFAULT '' COMMENT 'xml http路径',
  `ABSOLUTE_PATH` varchar(4000) DEFAULT '' COMMENT 'xml文件存放绝对路径',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `TOOL_ID` char(36) DEFAULT NULL COMMENT '工具id',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_QUIZ_TOOL_LIBRARY` (`TOOL_LIBRARY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_quiz_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_survey_answer_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_survey_answer_tool`;
CREATE TABLE `lads_survey_answer_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `QUESTION_ID` char(36) DEFAULT NULL COMMENT '关联题目',
  `USER_ID` char(36) DEFAULT NULL COMMENT '学习用户id',
  `USER_ANSWER` longtext COMMENT '用户提交的答案',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_SURVEY_ANSWER_TOOL_QUESTION_ID` (`QUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_survey_answer_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_survey_question_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_survey_question_tool`;
CREATE TABLE `lads_survey_question_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `CONTENT` longtext COMMENT '题目内容',
  `QUESTION_TYPE` varchar(10) NOT NULL COMMENT '题型',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `SURVEY_TOOL` char(36) NOT NULL COMMENT '关联调查问卷',
  `ANSWER` varchar(4000) DEFAULT NULL COMMENT '选择题答案',
  `POS` int(10) NOT NULL COMMENT '排序',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_SURVEY_QUESTION_TOOL_SURVEY` (`SURVEY_TOOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_survey_question_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_survey_tool`
-- ----------------------------
DROP TABLE IF EXISTS `lads_survey_tool`;
CREATE TABLE `lads_survey_tool` (
  `ID` char(36) NOT NULL COMMENT '主键',
  `TITLE` varchar(200) DEFAULT NULL COMMENT '标题',
  `TOOL_LIBRARY` char(36) NOT NULL COMMENT '工具类型',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `TOOL_ID` char(36) DEFAULT NULL COMMENT '工具id',
  `DESCRIPTION` longtext COMMENT '问卷描述',
  PRIMARY KEY (`ID`),
  KEY `FK_LADS_SURVEY_TOOL_TOOL_LIBRARY` (`TOOL_LIBRARY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_survey_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `lads_tool_library`
-- ----------------------------
DROP TABLE IF EXISTS `lads_tool_library`;
CREATE TABLE `lads_tool_library` (
  `id` char(36) NOT NULL COMMENT '主键',
  `valid_flag` varchar(10) NOT NULL COMMENT '启用或失效',
  `tool_name` varchar(200) NOT NULL COMMENT '工具名字',
  `image` varchar(200) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of lads_tool_library
-- ----------------------------
INSERT INTO lads_tool_library VALUES ('1', '0', 'QuizTool', '/res/images/common/lads/t_side_quiz.png');
INSERT INTO lads_tool_library VALUES ('2', '0', 'FaceTeachingTool', '/res/images/common/lads/t_side_teaching.png');
INSERT INTO lads_tool_library VALUES ('3', '1', 'EditorTool', '/res/images/common/lads/t_side_study.png');
INSERT INTO lads_tool_library VALUES ('4', '1', 'DiscussTool', '/res/images/common/lads/t_side_discuss.png');
INSERT INTO lads_tool_library VALUES ('5', '0', 'SurveyTool', '/res/images/common/lads/t_side_survey.png');
INSERT INTO lads_tool_library VALUES ('6', '0', 'PowerPointTool', '/res/images/common/lads/t_side_powerpoint.png');
INSERT INTO lads_tool_library VALUES ('7', '1', 'MediaTool', '/res/images/common/lads/t_side_media.png');
