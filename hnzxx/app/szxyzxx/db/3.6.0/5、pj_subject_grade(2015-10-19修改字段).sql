ALTER TABLE `edu_gzzhxy`.`pj_subject_grade` 
CHANGE COLUMN `grade_id` `grade_code` VARCHAR(50) NULL DEFAULT NULL COMMENT '通用年级码，jc_grade.code' ,
CHANGE COLUMN `subject_name` `subject_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '科目名称' ,
CHANGE COLUMN `subject_code` `subject_code` VARCHAR(50) NULL DEFAULT NULL COMMENT '科目编码' ,
CHANGE COLUMN `stage_code` `stage_code` VARCHAR(50) NULL DEFAULT NULL COMMENT '学段' ;
