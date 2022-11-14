package com.xunyunedu.paper.condition;

import lombok.Data;

@Data
public class OaPaperUserCondition {

    /**
     * 公文所属的单位，学校
     */
    private Integer ownerId;
    /**
     * 组的类型，1：学校
     */
    private String ownerType;

    /**
     * 对应的公文记录
     */
    private Integer paperId;

    /**
     * 对应的部门
     */
    private Integer departmentId;

    /**
     * 接收者id
     */
    private Integer receiverId;
    /**
     * 接收者名字
     */
    private String receiverName;
    /**
     * 是否已阅
     */
    private Boolean readStatus;


    /**
     * 是否删除
     */
    private Boolean isDeleted;


}
