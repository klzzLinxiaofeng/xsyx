package com.xunyunedu.homeWorkZy.pojo;

import java.io.Serializable;
import java.util.Date;

public class HomeWokePojo implements Serializable {
    private Integer id;
    private Integer schoolId;
    private Integer teamId;
    private Integer gradeId;

    private Integer teacherId;

    private String teachName;


    private String teamName;
    /*
    * 科目id
    */
    private Integer subjectId;
    /**/

    private String subjectName;
    private Date createTime;

    private Date modieTime;
    private Integer isDelete;
    /*
    * 内容
    */

    private String text;
    /*
    *图片uuid
     */
    private  String pictureUuid;
    /*
     *图片url
     */
    private  String picturUrl;
    /*
    * 是否收过作业 0 没有 1收过
    */
    private Integer isStats;
    /*
    * 状态
    */
    private Integer zhuangtai;

    public Integer getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public Integer getIsStats() {
        return isStats;
    }

    public void setIsStats(Integer isStats) {
        this.isStats = isStats;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    @Override
    public String toString() {
        return "HomeWokePojo{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", teamId=" + teamId +
                ", gradeId=" + gradeId +
                ", teacherId=" + teacherId +
                ", teachName='" + teachName + '\'' +
                ", subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", createTime=" + createTime +
                ", modieTime=" + modieTime +
                ", isDelete=" + isDelete +
                ", text='" + text + '\'' +
                ", pictureUuid='" + pictureUuid + '\'' +
                ", picturUrl='" + picturUrl + '\'' +
                '}';
    }

    public String getPictureUuid() {
        return pictureUuid;
    }

    public void setPictureUuid(String pictureUuid) {
        this.pictureUuid = pictureUuid;
    }

    public String getPicturUrl() {
        return picturUrl;
    }

    public void setPicturUrl(String picturUrl) {
        this.picturUrl = picturUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModieTime() {
        return modieTime;
    }

    public void setModieTime(Date modieTime) {
        this.modieTime = modieTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
