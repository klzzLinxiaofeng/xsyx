package com.xunyunedu.character.pojo;


import lombok.Data;

import java.util.Date;
import java.util.List;

/*
* 品格记录
*/
@Data
public class Records {
    private Integer id;
    /*
    * 学生id
    */
    private Integer studentId;
     /*
     *  学生name
     */
    private String stuName;

    private Integer evaluationId;
    private String evName;
    private Integer teacherId;
    private String teaName;
    private  String pictureId;
    private List<Picture> pictureList;
    private  String  pictureUrl;
    private Integer score;
    private Integer isDelete;
    private String message;
    private String voice;
    private Date createTime;

}
