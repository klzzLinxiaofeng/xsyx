package com.xunyunedu.syllabus.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SyllabusLessonVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * pj_syllabus.id
     */
    private Integer syllabusId;
    /**
     * 节次，第几节
     */
    private Integer lesson;
    /**
     * 星期几 XY-JY-XQ 0=星期日 1=星期一
     */
    private String dayOfWeek;
    /**
     * 老师id
     */
    private Integer teacherId;
    /**
     * 科目码
     */
    private String subjectCode;

    /**
     * 科目名称
     */
    private String subjectName;
    /**
     * 教室id
     */
    private Integer roomId;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    /**
     * 0 正常 1删除
     */
    private Integer isDeleted;

    /**
     * 老师名字
     */
    private String teacherName;

    /**
     * 教室名字
     */
    private String roomName;
    /**
     * 节次开始时间
     */
    private String startTime;
    /**
     * 节次结束时间
     */
    private String endTime;

    /**
     * 是否是默认课程 0 是 1 否
     */
    private Integer defaultFlag;
    /**
     * 是否是调课后的课程 0 否 1 是
     */

    private Integer adjustFlag;

    /**
     * 课程生效开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 课程结束时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 班级id
     */
    private Integer teamId;
    /**
     * 班级名字
     */
    private String teamName;
    /**
     * 星期对应的日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date weekOfDay;

}
