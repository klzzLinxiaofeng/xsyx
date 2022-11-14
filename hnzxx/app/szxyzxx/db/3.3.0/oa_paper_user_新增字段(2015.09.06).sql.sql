ALTER TABLE oa_paper_user 
ADD COLUMN `owner_id` int(11) DEFAULT NULL COMMENT '公文用户所属的单位  学校';

ALTER TABLE oa_paper_user
ADD COLUMN  `owner_type` char(6) DEFAULT NULL COMMENT '组的类型 1：学校';

ALTER TABLE oa_paper_user
ADD COLUMN  `department_id` int(10) DEFAULT NULL COMMENT '接收者部门id';


ALTER TABLE oa_paper_user 

ADD COLUMN `is_deleted` tinyint(1) DEFAULT NULL;
