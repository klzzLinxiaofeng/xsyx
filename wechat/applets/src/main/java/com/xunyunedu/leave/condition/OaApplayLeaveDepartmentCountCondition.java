package com.xunyunedu.leave.condition;

import lombok.Data;

@Data
public class OaApplayLeaveDepartmentCountCondition {


    Integer departmentId;

    Integer ownerId;

    String ownerType;

    String auditStatus;

}
