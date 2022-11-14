package com.xunyunedu.paper.condition;

import lombok.Data;

@Data
public class OaPaperDepartmentCountCondition {

    /**
     * 公文所属的单位，学校
     */
    private Integer ownerId;
    /**
     * 组的类型，1：学校
     */
    private String ownerType;

    /**
     * 对应的部门
     */
    private Integer departmentId;


    /**
     * 是否删除
     */
    private Boolean isDeleted;


}
