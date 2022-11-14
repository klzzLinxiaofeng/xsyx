ALTER TABLE `pj_textbook_manager` 
CHANGE COLUMN `teacher_name` `teacher_name` VARCHAR(45) NOT NULL COMMENT '任课教师姓名' ,
ADD COLUMN `stage_code_name` VARCHAR(45) NOT NULL AFTER `teacher_name`,
ADD COLUMN `grade_code_name` VARCHAR(45) NOT NULL AFTER `stage_code_name`,
ADD COLUMN `subject_code_name` VARCHAR(45) NOT NULL AFTER `grade_code_name`,
ADD COLUMN `volumn_name` VARCHAR(45) NOT NULL AFTER `subject_code_name`,
ADD COLUMN `version_name` VARCHAR(45) NOT NULL AFTER `volumn_name`,
ADD COLUMN `grade_id_name` VARCHAR(45) NOT NULL AFTER `version_name`,
ADD COLUMN `team_id_name` VARCHAR(45) NOT NULL AFTER `grade_id_name`;
