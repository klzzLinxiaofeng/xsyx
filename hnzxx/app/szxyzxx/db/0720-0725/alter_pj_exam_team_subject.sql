ALTER TABLE `pj_exam_team_subject` 
ADD COLUMN `code` VARCHAR(50) NOT NULL COMMENT '考试日程代码' AFTER `school_id`;
