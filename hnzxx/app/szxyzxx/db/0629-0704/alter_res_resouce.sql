ALTER TABLE `res_resource`  ADD COLUMN `icon_type` tinyint(2) COMMENT '图标类型';
ALTER TABLE `res_resource` ADD COLUMN `thumbnail_url` varchar(1000);

ALTER TABLE `res_resource` ADD COLUMN `comment_count` int(11) DEFAULT '0' COMMENT '评价次数';
