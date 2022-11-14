ALTER TABLE `jc_textbook` 
CHANGE COLUMN `binding` `binding` VARCHAR(20) NULL DEFAULT NULL COMMENT '装帧' ;
ALTER TABLE `jc_textbook` 
ADD COLUMN `code` INT(10) NOT NULL DEFAULT 0 COMMENT '教材代码';

update jc_textbook a set a.`code` = a.id;

