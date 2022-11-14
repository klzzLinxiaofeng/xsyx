package com.xunyunedu.PublishAndAcceptJob.param;


import lombok.Data;

import java.util.List;

/**
 * 老师发布内容 参数
 */
@Data
public class PublishAndAcceptJobContentInsertParam {


    /**
     * 老师id
     */
    private Integer teacherId;

    /**
     * 学科id
     */
    private Integer subjectId;
    /**
     * 年级id
     */
    private Integer gradeId;
    /**
     * 学年
     */
    private String schoolYear;
    /**
     * 学期
     */
    private String schoolTrem;
    /**
     * 班级id
     */
    private List<Integer> teamIds;

    /**
     * 图片id
     */
    private String pictureUUIDs;
    /**
     * 图片id
     */
    private Integer isHome;

    /**
     * 输入内容
     */
    private String content;
    /**
     * 输入学校id
     */
    private Integer schoolId;

}
