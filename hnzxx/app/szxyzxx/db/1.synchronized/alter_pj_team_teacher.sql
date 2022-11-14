ALTER TABLE `pj_team_teacher`
ADD COLUMN `is_delete`  bit(1) NULL COMMENT '删除标识' AFTER `create_date`;