ALTER TABLE `oa_apply_card` 
ADD COLUMN `proposer_name` VARCHAR(45) NULL AFTER `department_id`,
ADD COLUMN `audit_name` VARCHAR(45) NULL AFTER `proposer_name`;
