package com.xunyunedu.workAttendance.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 希沃课程考勤类
 * @author: cmb
 * @create: 2020-11-05 18:30
 **/
@Data
public class SeewoLessonWorkAttendance implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 学生id或教师Userid
     */
    private Integer id;
    /**
     * appId true
     */
    private String appId;
    /**
     * 学校ID true
     */
    private String schoolUid;
    /**
     * 日期 yyyy-MM-dd true
     */
    private String date;
    /**
     * 场地标识 flase
     */
    private String roomNum;
    /**
     * 场地标识类型；0：第三方ID，1：希沃场地ID（默认），2：房间编号，3：行政班级ID flase
     */
    private Integer roomNumType;
    /**
     * 课程节次 1-N flase
     */
    private Integer lessonIdx;
    /**
     * 只拉取时间之后的数据 HH:mm:ss   flase
     */
    private String lastTime;
    /**
     * page true
     */
    private Integer page;
    /**
     * pageSize true
     */
    private Integer pageSize;


}
