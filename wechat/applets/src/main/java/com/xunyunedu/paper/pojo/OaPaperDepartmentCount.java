package com.xunyunedu.paper.pojo;

import lombok.Data;

@Data
public class OaPaperDepartmentCount {

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
     * 对应发布的部门id
     */
    private Integer departmentId;
    /**
     * 对应部门的公文数量
     */
    private Integer count;
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
