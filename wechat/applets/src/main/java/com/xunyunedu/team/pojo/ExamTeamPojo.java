package com.xunyunedu.team.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 班级成绩
 *
 * @author: yhc
 * @Date: 2020/12/21 16:55
 * @Description:
 */
public class ExamTeamPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 班级名称
     */
    private String teamName;

    /**
     * 考试名称
     */
    private String examName;

    /**
     * 分数
     */
    private String score;

    /**
     * 考试时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date preciseStartDate;

    /**
     * 学生班级排名
     */
    private String order;

    /**
     * 及格率
     */
    private String passingRate;

    /**
     * 班级学校排名
     */
    private String teamOrder;

    /**
     * 学生在学校中的排名
     */
    private String stuSchoolOrder;

    private String comment;

    private String stuSchoolOrderSize;

    private String teamOrderSize;

    private String orderSize;


    private Integer teamId;


    /**
     * 相同考试日程的班级标识
     */
    private String uuid;

    /**
     * 科目名称
     */
    private String name;

    private Object createDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }



    public String getPassingRate() {
        return passingRate;
    }

    public void setPassingRate(String passingRate) {
        this.passingRate = passingRate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getScore() {

        if(StringUtils.isNotEmpty(score)){
            try {
                return new BigDecimal(score).stripTrailingZeros().toPlainString();
            } catch (Exception e) { }
        }
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getPreciseStartDate() {
        return preciseStartDate;
    }

    public void setPreciseStartDate(Date preciseStartDate) {
        this.preciseStartDate = preciseStartDate;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(String teamOrder) {
        this.teamOrder = teamOrder;
    }

    public String getStuSchoolOrder() {
        return stuSchoolOrder;
    }

    public void setStuSchoolOrder(String stuSchoolOrder) {
        this.stuSchoolOrder = stuSchoolOrder;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getStuSchoolOrderSize() {
        return stuSchoolOrderSize;
    }

    public void setStuSchoolOrderSize(String stuSchoolOrderSize) {
        this.stuSchoolOrderSize = stuSchoolOrderSize;
    }

    public String getTeamOrderSize() {
        return teamOrderSize;
    }

    public void setTeamOrderSize(String teamOrderSize) {
        this.teamOrderSize = teamOrderSize;
    }

    public String getOrderSize() {
        return orderSize;
    }

    public void setOrderSize(String orderSize) {
        this.orderSize = orderSize;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }
}
