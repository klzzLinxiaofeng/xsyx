package com.xunyunedu.act.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 校外活动信息
 *
 * @author AutoCreate
 */
@Data
public class OutSchoolActivity  {


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
     * 申请人姓名
     */
    private String userName;

    /**
     * 学校ID
     */
    private Integer schoolId;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 活动地点
     */
    private String location;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private java.util.Date startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private java.util.Date endTime;
    /**
     * 附件
     */
    private String accessory;
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
     * 审批状态（0：待审批，1：已通过，2：待总结，3：已总结、4：已驳回）
     */
    private Integer state;
    /**
     * 活动总结
     */
    private String summary;
    /**
     * 活动总结图片url，多个用英文逗号分割
     */
    private String summaryImgs;

    /**
     * 审批人userId
     */
    private Integer handleUserId;

    private String handleUserName;

    private String refuseCause;


    @JsonIgnore
    private java.util.Date modifyDate;
    /**
     * 活动总结图片url
     */
    private List<String> imgs;


    private Object param;

}