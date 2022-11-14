package com.xunyunedu.india.pojo;

import lombok.Data;

@Data
public class OaApplayIndiaDepartmentCount {

    /**
     * id
     */
    private Integer id;
    /**
     * 公文所属的单位，学校
     */
    private Integer ownerId;
    /**
     * 组的类型，1：学校
     */
    private String ownerType;
    /**
     * 所属部门id
     */
    private Integer departmentId;
    /**
     * 对应文印申请总数
     */
    private Integer number;
    /**
     * 文印处理状态
     */
    private String indiaStatus;
    /**
     * 创建时间
     */
    private java.util.Date createDate;
    /**
     * 修改时间
     */
    private java.util.Date modifyDate;
    /**
     * 删除标志
     */
    private Boolean isDeleted;

}
