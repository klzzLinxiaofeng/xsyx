ALTER TABLE `pj_student_score` 
ADD COLUMN `school_id` INT(10) NOT NULL COMMENT '所属学校' AFTER `exam_name`;
