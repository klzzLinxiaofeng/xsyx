package com.xunyunedu.act.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *
 *校内活动信息
 */
@Data
public class InSchoolActivity {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 申请人
     */
    private Integer userId;
    /**
     * 申请人用户名
     */
    private String userName;
    /**
     * 活动室ID
     */
    private Integer roomId;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 活动描述
     */
    private String description;
    /**
     * createDate
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date createDate;
    /**
     * modifyDate
     */
    private java.util.Date modifyDate;

    /**
     * 活动状态（0：待审批，1：通过，2：驳回）
     */
    private Integer state;

    /**
     * 驳回理由
     */
    private String refuseCause;

    /**
     * 参加活动的用户id字符串，多个用,分割
     */
    private String attendUserIds;

    /**
     * 参加活动的用户姓名字符串，多个用,分割
     */
    private String attendUserNames;
    /**
     * 审批用户id
     */
    private Integer handleUserId;

    private String handleUserName;



    /**
     * 活动室名称
     */
    private String roomName;


}