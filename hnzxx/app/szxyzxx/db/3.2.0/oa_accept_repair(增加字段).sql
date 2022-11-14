ALTER TABLE oa_accept_repari RENAME oa_accept_repair
ALTER TABLE `oa_accept_repair` 
ADD COLUMN `school_id` VARCHAR(45) NOT NULL AFTER `is_delete`;
