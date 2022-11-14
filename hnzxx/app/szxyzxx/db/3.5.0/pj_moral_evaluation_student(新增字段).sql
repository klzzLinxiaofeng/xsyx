ALTER TABLE `edu_gzzhxy`.`pj_moral_evaluation_student` 
CHANGE COLUMN `team_student_id` `team_id` INT(10) NOT NULL COMMENT 'pj_team.id' ,
ADD COLUMN `student_id` INT(10) NOT NULL COMMENT 'pj_student.id' AFTER `team_id`;

