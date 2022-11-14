ALTER TABLE `pj_exam_team_subject` 
ADD COLUMN `school_id` INT(10) NOT NULL COMMENT '所属学校' AFTER `rate_type`;
