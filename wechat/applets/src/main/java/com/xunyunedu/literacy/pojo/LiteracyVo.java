package com.xunyunedu.literacy.pojo;

import java.util.Date;

public class LiteracyVo {
    /*
    * 主键id
    */
    private  Integer id;
    /*
     * 学年
     */
    private  Integer xn;/*
     * 学期
     */
    private  Integer xq;
    /*
     * 年级id
     */
    private  Integer gradeId;
    /*
     * 年级name
     */
    private String gradeName;
    /*
     * 学科id
     */
    private  Integer subjectId;
    /*
     * 学科name
     */
    private String subjectName;
    /*
     * 学科素养指标
     */
    private  String literacyName;
    /*
     * 最大分值
     */
    private  Integer score;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private  Date moideTime;
    /*
     * 是否删除
     */
    private  Integer isDelete;
    public LiteracyVo(){};

    public LiteracyVo(Integer id, Integer xn, Integer xq, Integer gradeId, String gradeName, Integer subjectId, String subjectName, String literacyName, Integer score, Date createTime, Date moideTime, Integer isDelete) {
        this.id = id;
        this.xn = xn;
        this.xq = xq;
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.literacyName = literacyName;
        this.score = score;
        this.createTime = createTime;
        this.moideTime = moideTime;
        this.isDelete = isDelete;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }


    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getXn() {
        return xn;
    }

    public void setXn(Integer xn) {
        this.xn = xn;
    }

    public Integer getXq() {
        return xq;
    }

    public void setXq(Integer xq) {
        this.xq = xq;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }



    public String getLiteracyName() {
        return literacyName;
    }

    public void setLiteracyName(String literacyName) {
        this.literacyName = literacyName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getMoideTime() {
        return moideTime;
    }

    public void setMoideTime(Date moideTime) {
        this.moideTime = moideTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
