ALTER TABLE `edu_gzzhxy`.`oa_notice` 
ADD COLUMN `digest` VARCHAR(200) NULL COMMENT 'ժҪ' AFTER `is_deleted`,
ADD COLUMN `upload_file` VARCHAR(45) NULL COMMENT '�ϴ��ĸ���' AFTER `digest`;
ADD COLUMN `cover` VARCHAR(250) NULL COMMENT '����' AFTER `upload_file`;