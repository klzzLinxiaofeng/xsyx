package com.xunyunedu.india.condition;

import lombok.Data;

@Data
public class OaApplayIndiaDepartmentCountCondition {


    Integer departmentId;

    Integer ownerId;

    String ownerType;

    /**
     * 文印处理状态
     */
    String indiaStatus;

    Boolean isDeleted;

}
