ALTER TABLE `res_resource` 
ADD COLUMN `library_id` INT NULL COMMENT '所在资源库' ;

ALTER TABLE `res_resource` 
ADD COLUMN `verify` BIT(1) NOT NULL DEFAULT 0 ;

ALTER TABLE `res_resource` 
CHANGE COLUMN `verify` `verify` CHAR(3) NOT NULL DEFAULT 0 ;

ALTER TABLE `res_resource` 
CHANGE COLUMN `verify` `verify` CHAR(3) NOT NULL DEFAULT '0' COMMENT '审核资源结果 //审核 0为通过 1 为审核中 9为不通过' ;

ALTER TABLE `res_resource` 
CHANGE COLUMN `library_id` `library_id` VARCHAR(36) NULL DEFAULT NULL COMMENT '所在资源库' ;

ALTER TABLE `res_resource` 
ADD COLUMN `user_id` INT(10) NULL COMMENT '用户id' ,
ADD COLUMN `user_name` VARCHAR(45) NULL COMMENT '用户名称' ;


ALTER TABLE `res_resource` 
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ;









