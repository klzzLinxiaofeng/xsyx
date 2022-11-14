ALTER TABLE `pj_team` 
CHANGE COLUMN `team_type` `team_type` VARCHAR(2) NULL COMMENT '班级类别' ,
CHANGE COLUMN `team_property` `team_property` VARCHAR(2) NULL COMMENT '班级属性' ;
