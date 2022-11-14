ALTER TABLE `res_catalog_resource` 
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
CHANGE COLUMN `volumn_code` `volumn_code` VARCHAR(36) NULL COMMENT '册次，卷' ,
CHANGE COLUMN `volumn_name` `volumn_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '册次，卷' ;
