ALTER TABLE `pj_school` ADD COLUMN `code2` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统内部给学校的统一编码' AFTER `code`;
