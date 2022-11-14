ALTER TABLE `pj_upLoadInformation_temp`
ADD COLUMN `mobile`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�ҳ��绰' AFTER `role`;

ALTER TABLE `pj_upLoadInformation_temp`
ADD COLUMN `isBoarded`  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�Ƿ�ס��' AFTER `role`;

ALTER TABLE `pj_upLoadInformation_temp`
ADD COLUMN `deptName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称' AFTER `isBoarded`;

