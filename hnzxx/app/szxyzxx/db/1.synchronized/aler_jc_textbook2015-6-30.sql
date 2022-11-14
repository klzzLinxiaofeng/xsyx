ALTER TABLE `jc_textbook` 
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL COMMENT '主键' ,
CHANGE COLUMN `name` `name` VARCHAR(100) NOT NULL COMMENT '名称' ,
CHANGE COLUMN `publisher_id` `publisher_id` INT(11) NOT NULL COMMENT '版本类型，如人教版' ,
ADD COLUMN `writer_id` INT(10) NULL COMMENT '书的作者和译者，关联 jc_writer.id' AFTER `is_delete`,
ADD COLUMN `old_name` VARCHAR(100) NULL COMMENT '原书名' AFTER `writer_id`,
ADD COLUMN `subtitle` VARCHAR(50) NULL COMMENT '副标题' AFTER `old_name`,
ADD COLUMN `price` VARCHAR(10) NULL COMMENT '价格' AFTER `subtitle`,
ADD COLUMN `press_name` VARCHAR(45) NULL COMMENT '出版社' AFTER `price`,
ADD COLUMN `type` VARCHAR(3) NULL COMMENT '书籍类型' AFTER `press_name`,
ADD COLUMN `binding` VARCHAR(3) NULL COMMENT '装帧' AFTER `type`,
ADD COLUMN `pages` VARCHAR(5) NULL COMMENT '页数' AFTER `binding`,
ADD COLUMN `word_count` VARCHAR(15) NULL COMMENT '字数' AFTER `pages`;
ALTER TABLE `jc_textbook` 
ADD COLUMN `is_hidden` BIT(1) NULL COMMENT '是否隐藏' AFTER `word_count`;

ALTER TABLE `jc_textbook` 
CHANGE COLUMN `is_hidden` `is_hidden` BIT(1) NOT NULL COMMENT '是否隐藏' ;
ALTER TABLE `jc_textbook` 
CHANGE COLUMN `binding` `binding` VARCHAR(15) NULL DEFAULT NULL COMMENT '装帧' ;

ALTER TABLE `jc_textbook` 
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ;

ALTER TABLE `jc_textbook` 
CHANGE COLUMN `domain` `domain` INT(11) NULL COMMENT '应用领域（保留）' ,
CHANGE COLUMN `publisher_id` `publisher_id` INT(11) NULL COMMENT '版本类型，如人教版' ;





