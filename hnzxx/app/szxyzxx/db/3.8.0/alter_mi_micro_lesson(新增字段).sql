ALTER TABLE `mi_micro_lesson` 
ADD COLUMN `lesson_length` DOUBLE NULL COMMENT '资源总时长' AFTER `logo_entity_id`;
ALTER TABLE `mi_micro_lesson` 
ADD COLUMN `play_number` INT(11) NULL DEFAULT 0 COMMENT '播放次数' AFTER `lesson_length`;

