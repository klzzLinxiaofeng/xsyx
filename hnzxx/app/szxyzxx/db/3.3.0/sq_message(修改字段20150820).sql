ALTER TABLE `edu_gzzhxy`.`sq_message` 
CHANGE COLUMN `poster_name` `poster_name` VARCHAR(50) NOT NULL COMMENT '发表人的姓名或昵称' ,
CHANGE COLUMN `menu_code` `tag` CHAR(36) NOT NULL COMMENT '菜单code' ;
