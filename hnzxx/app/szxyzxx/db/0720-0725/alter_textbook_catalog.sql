ALTER TABLE `jc_textbook_catalog` 
ADD COLUMN `code` INT(10) NOT NULL DEFAULT 0 COMMENT '代码' AFTER `modify_time`;
ALTER TABLE `jc_textbook_catalog` 
ADD COLUMN `list_order` INT(10) NULL DEFAULT 0 COMMENT '排序问题' AFTER `code`;

UPDATE jc_textbook_catalog a set a.`code`=a.id;