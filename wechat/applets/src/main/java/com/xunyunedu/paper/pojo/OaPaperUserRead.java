package com.xunyunedu.paper.pojo;

import lombok.Data;

@Data
public class OaPaperUserRead {

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
     * 公文id
     */
    private Integer paperId;
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 是否已阅
     */
    private Boolean readStatus;

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
    private Integer isDeleted;
}
