ALTER TABLE `mi_micro_lesson` 
ADD COLUMN `json_entity_id` CHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL COMMENT 'main.json �ļ�entity_id' AFTER `play_length`;
ALTER TABLE `mi_micro_lesson` 
ADD COLUMN `media_entity_id` CHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL COMMENT '��Ƶ�ļ�entity_id' AFTER `json_entity_id`;
ALTER TABLE `mi_micro_lesson` 
ADD COLUMN `bgpicture_entity_id` CHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL COMMENT '����ͼƬentity_id' AFTER `media_entity_id`;
ALTER TABLE `mi_micro_lesson` 
ADD COLUMN `property_entity_id` CHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL COMMENT '�����ļ�entity_id' AFTER `bgpicture_entity_id`;