CREATE TABLE `jc_writer` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_date` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `is_delete` BIT(1) NOT NULL COMMENT '是否删除，0==不删除，1===删除',
  `type` VARCHAR(5) NOT NULL COMMENT '人员类型，作者===1 译者===2 ',
  `summary` VARCHAR(200) NULL COMMENT '人员简介',
  `name` VARCHAR(20) NOT NULL COMMENT '人员名称',
  PRIMARY KEY (`id`))
COMMENT = '书的作者和译者';

ALTER TABLE `jc_writer` 
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL ,
ADD COLUMN `text_book_id` INT(10) NOT NULL COMMENT '对应的书的 jc_textbook.id' AFTER `name`,
ADD COLUMN `list_order` VARCHAR(3) NOT NULL AFTER `text_book_id`;

ALTER TABLE `jc_writer` 
CHANGE COLUMN `list_order` `list_order` VARCHAR(3) NOT NULL COMMENT '排序' ;

ALTER TABLE `jc_writer` 
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ;


