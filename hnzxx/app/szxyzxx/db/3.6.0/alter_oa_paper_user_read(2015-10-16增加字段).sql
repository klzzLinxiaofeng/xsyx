ALTER TABLE oa_paper_user_read 
ADD COLUMN `read_status` tinyint(1) DEFAULT NULL COMMENT '阅读状态 0:未阅读 1：已阅读';