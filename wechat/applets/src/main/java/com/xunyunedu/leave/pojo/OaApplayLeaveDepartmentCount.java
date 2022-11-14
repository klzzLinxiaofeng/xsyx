package com.xunyunedu.leave.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class OaApplayLeaveDepartmentCount {

    Integer id;

    Integer ownerId;

    String ownerType;

    Integer departmentId;

    String auditStatus;

    Integer count;

    Date createDate;

    Date modifyDate;


}
