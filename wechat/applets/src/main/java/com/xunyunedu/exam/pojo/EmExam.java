package com.xunyunedu.exam.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class EmExam {

    private Integer id;
    private Date createDate;
    private Date modifyDate;
    private String title;
    private String description;
    private String uuid;
    private String type;
    private Integer userId;
    private Integer appId;
    private String entityId;
    private Integer paperId;
    private String subjectCode;


}
