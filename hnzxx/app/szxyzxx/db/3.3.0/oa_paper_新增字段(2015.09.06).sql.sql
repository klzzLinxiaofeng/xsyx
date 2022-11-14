ALTER TABLE oa_paper  
ADD COLUMN `remark` varchar(300) DEFAULT NULL COMMENT '摘要';

ALTER TABLE oa_paper
ADD COLUMN   `publish_date` datetime DEFAULT NULL COMMENT '发布日期';
