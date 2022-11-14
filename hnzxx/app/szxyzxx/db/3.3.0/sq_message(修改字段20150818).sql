ALTER TABLE `edu_gzzhxy`.`sq_message` 
DROP COLUMN `read_url`,
CHANGE COLUMN `menu_code` `menu_code` CHAR(36) NOT NULL COMMENT '菜单code' ;
