ALTER TABLE `jc_textbook_catalog` 
CHANGE COLUMN `name` `name` VARCHAR(200) NOT NULL COMMENT '名称' ;

ALTER TABLE `jc_textbook_catalog` 
CHANGE COLUMN `level` `level` INT(11) NOT NULL COMMENT '树节点的层次深度' ;
