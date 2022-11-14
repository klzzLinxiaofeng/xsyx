package com.xunyunedu.act.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * at_substitute
 * @author 
 */
@Data
public class AtSubstitute implements Serializable {
    private Integer id;

    /**
     * 申请人userId
     */
    private Integer userId;
    /**
     * 申请人姓名
     */
    private String userName;

    /**
     * 学校ID
     */
    @JsonIgnore
    private Integer schoolId;

    /**
     * 审批人userId
     */
    private Integer receiver;

    /**
     * 审批人姓名
     */
    private String receiverName;

    /**
     * 审批状态（0：待审批，1：审批通过，2：审批拒绝）
     */
    private Integer status;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

    /**
     * 代课说明
     */
    private String description;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 附件url
     */
    private String accessory;
    /**
     * 附件名称
     */
    private String accessoryName;

    /**
     * 反馈
     */
    private String feedback;

    /**
     * 创建/申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyDate;

    /*
    * 代课教师id
    */
    private Integer daikeId;
    /*
     * 代课教师name
     */
    private String daikeName;

    private static final long serialVersionUID = 1L;
}