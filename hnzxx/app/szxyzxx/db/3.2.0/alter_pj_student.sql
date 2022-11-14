ALTER TABLE `pj_student`
ADD COLUMN `student_number2`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学籍辅助号' AFTER `student_number`;

ALTER TABLE `pj_student`
ADD COLUMN `is_boarded`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否住宿' AFTER `team_name`;