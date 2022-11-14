ALTER TABLE `pj_school_system` 
ADD COLUMN `grade_number` INT(1) NULL COMMENT '年级在学段中的顺序，如初二是2' AFTER `grade_name`;
