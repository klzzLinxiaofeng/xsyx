CREATE TABLE `res_resource_library` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(36) NOT NULL,
  `app_id` INT NOT NULL,
  `ower_id` INT NOT NULL COMMENT '所属学校机构和单位',
  `region_code` INT NULL COMMENT '如果有地区属性，此为行政区划码',
  `name` VARCHAR(50) NOT NULL COMMENT '资源库名称',
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  `modify_date` TIMESTAMP NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`))
COMMENT = '单位资源库';


ALTER TABLE `res_resource_library` 
CHANGE COLUMN `region_code` `region_code` VARCHAR(50) NULL COMMENT '如果有地区属性，此为行政区划码' ;
