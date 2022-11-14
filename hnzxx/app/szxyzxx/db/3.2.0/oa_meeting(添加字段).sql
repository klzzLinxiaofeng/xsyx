alter table 'oa_meeting' ADD COLUMN 'fanwei' int(11) DEFAULT 0 COMMENT '可见范围';
alter table 'oa_meeting' ADD COLUMN 'meeting_num' int(11) DEFAULT 0 COMMENT '参会人数';

ALTER TABLE `oa_meeting` CHANGE COLUMN `fanwei` `fanwei` int(11) DEFAULT '0' COMMENT '可见范围' after `modify_date`
ALTER TABLE `oa_meeting` CHANGE COLUMN `meeting_num` `meeting_num` int(11) DEFAULT '0' COMMENT '参会人数' after `fanwei`
