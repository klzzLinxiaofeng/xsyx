package com.xunyunedu.literacy.pojo;

import java.util.Date;

public class LiteracyStudent {
    /*
    * 主键
    */
    private Integer id;
    /*
     * 学生id
     */
    private Integer studentId;
    /*
     * 学生姓名
     */
    private String stuName;
    /*
     * 班级id
     */
    private Integer teamId;
    /*
     * 班级名字
     */
    private String teamName;
    /*
     * 科目id
     */
    private Integer subjectId;
    /*
     * 科目name
     */
    private String subName;
    /*
     * 最大分值
     */
    private Integer score;
    /*
     * shanchu
     */
    private Integer isDelete;
    /*
     * 分数
     */
    private Integer fenshu;
    /*
    *创建时间
     */
    private Date  createTime;
      /*
       * 素养指标
       */
      private String literacyName;
         /*
          * 素养指标id
          */
         private Integer literacyId;
    private String  pingyu;

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Integer getLiteracyId() {
        return literacyId;
    }

    public void setLiteracyId(Integer literacyId) {
        this.literacyId = literacyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getFenshu() {
        return fenshu;
    }

    public void setFenshu(Integer fenshu) {
        this.fenshu = fenshu;
    }
}
