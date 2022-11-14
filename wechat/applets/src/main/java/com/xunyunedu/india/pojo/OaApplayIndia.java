package com.xunyunedu.india.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author edison
 */
@Data
public class OaApplayIndia {

    Integer id;

    Integer ownerId;

    String ownerType;

    Integer proposerId;

    String proposerName;

    String mobile;

    Integer departmentId;
    /**
     * 处理状态（0：待处理 1：未处理 2：处理中 3：已处理）
     */
    String indiaStatus;

    String title;

    Date publishDate;

    String remark;

    String uploadId;

    String deliveryMethod;

    Date treatDate;

    String nonTreatmentCause;

    Date expectedCompletionTime;

    Boolean isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    Date createDate;

    Date modifyDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    Date endDate;

    Integer approverId;

    private Object param;

}
