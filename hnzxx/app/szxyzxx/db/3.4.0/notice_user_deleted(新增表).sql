DROP TABLE IF EXISTS `notice_user_deleted`;
CREATE TABLE `edu_gzzhxy`.`notice_user_deleted` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '����',
  `notice_id` INT(10) NOT NULL COMMENT '����ID',
  `user_id` INT(10) NOT NULL COMMENT '�û�ID',
  `create_date` DATETIME NULL COMMENT '����ʱ��',
  `modify_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�޸�ʱ��',
  `is_delete` BIT(1) NOT NULL DEFAULT 0 COMMENT '�Ƿ�ɾ��',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));