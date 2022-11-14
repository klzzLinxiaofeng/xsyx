ALTER TABLE `pj_textbook_manager` 
CHANGE COLUMN `grade_code` `grade_code` VARCHAR(5) NULL COMMENT '年级' ;

ALTER TABLE `pj_textbook_manager` 
CHANGE COLUMN `volumn` `volumn` VARCHAR(36) NOT NULL COMMENT '册次，卷' ;

ALTER TABLE `pj_textbook_manager` 
CHANGE COLUMN `grade_code_name` `grade_code_name` VARCHAR(45) NULL ;


