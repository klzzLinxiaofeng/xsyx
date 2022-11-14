CREATE TABLE `mi_micro_lesson_praise` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `praise_type` INT(10) NOT NULL COMMENT '点赞类型（0：QQ好友、1:微信、2：新浪微博、3：QQ空间）' ,
  `praise_account` VARCHAR(200) NULL COMMENT '点赞账号',
  `lesson_id` INT(10) NOT NULL,
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  `modify_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '视频点赞数';
