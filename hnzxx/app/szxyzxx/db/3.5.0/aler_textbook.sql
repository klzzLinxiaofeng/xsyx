ALTER TABLE `jc_textbook` 
CHANGE COLUMN `volumn` `volumn` VARCHAR(36) NOT NULL COMMENT '册次，卷' ;

ALTER TABLE `jc_textbook` 
CHANGE COLUMN `code` `code` INT(10) NULL DEFAULT '0' COMMENT '教材代码' ;
