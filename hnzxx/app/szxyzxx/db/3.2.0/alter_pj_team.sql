ALTER TABLE `pj_team`
ADD COLUMN `code2`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '校内编号，学校唯一' AFTER `code`;
