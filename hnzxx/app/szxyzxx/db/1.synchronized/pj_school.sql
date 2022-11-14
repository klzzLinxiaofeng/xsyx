ALTER TABLE `pj_school`
DROP COLUMN `uuid`,
ADD COLUMN `uuid`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'UUID' AFTER `id`;