ALTER TABLE `pj_student_aid` 
CHANGE COLUMN `one_income` `one_income` VARCHAR(11) NOT NULL COMMENT '家庭收入/人口' ,
CHANGE COLUMN `aid_amount` `aid_amount` VARCHAR(11) NOT NULL COMMENT '资助金额' ;
