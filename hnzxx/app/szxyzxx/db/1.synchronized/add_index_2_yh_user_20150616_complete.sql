ALTER TABLE `edu_gzzhxy`.`yh_user` ADD INDEX `idx_yhUser_isDeleted_username	user_name, is_deleted	1	btree` USING BTREE (user_name, is_deleted);

ALTER TABLE `edu_gzzhxy`.`yh_group_user` ADD INDEX `idx_yhGroupUser_groupId_userId` USING BTREE (group_id, user_id);

ALTER TABLE `edu_gzzhxy`.`yh_app_user` ADD INDEX `idx_yhAppUser_appId_userId` USING BTREE (app_id, user_id);