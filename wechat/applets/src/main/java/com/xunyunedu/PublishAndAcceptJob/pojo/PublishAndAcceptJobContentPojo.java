package com.xunyunedu.PublishAndAcceptJob.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 老师发布作业
 * 内容管理表
 * @author lee
 * @Date 2020/12/7
 */
public class PublishAndAcceptJobContentPojo implements Serializable {

    /**
     * 内容id
     */
    private Integer id;

    /**
     * 老师id
     */
    private Integer teacherId;

    /**
     * 学生id
     */
    private Integer studentId;
    /**
     * 学科id
     */
    private Integer subjectId;
    /**
     * 班级id
     */
    private String teamId;
    /**
     * 学校id
     */
    private Integer schoolId;
    /**
     * 图片id
     */
    private String pictureUUID;

    /**
     * 图片完整url
     */
    private String httpUrl;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 图片id
     */
    private List<String> pictureUUIDs;

    /**
     * 班级老师表id
     */
    private Integer tId;

    /**
     * 删除状态
     */
    private Boolean isDelete;
    /**
     * 学科名称
     */
    private String subjectName;
    /**
     * 是否收作业
     */
    private Integer isStats;
    /*
    * 是否家庭作业 0否，1是
    */
    private Integer isHome;
    /*
     * 学年
     */
    private String schoolYear;
    /*
     * 学期
     */
    private String schoolTrem;
    /*
     * 年级id
     */
    private Integer gradeId;

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSchoolTrem() {
        return schoolTrem;
    }

    public void setSchoolTrem(String schoolTrem) {
        this.schoolTrem = schoolTrem;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getIsHome() {
        return isHome;
    }

    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
    }

    public Integer getIsStats() {
        return isStats;
    }

    public void setIsStats(Integer isStats) {
        this.isStats = isStats;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 输入内容
     */
    private String content;

    private TeamPojo teamPojo;

    /**
     * 班级名称
     */
    private String teamName;


    private List<String> teamNames;
    /**
     * 班级id
     */
    private List<Integer> teamIds;

    public List<Integer> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Integer> teamIds) {
        this.teamIds = teamIds;
    }

    public List<String> getTeamNames() {
        return teamNames;
    }

    public void setTeamNames(List<String> teamNames) {
        this.teamNames = teamNames;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public TeamPojo getTeamPojo() {
        return teamPojo;
    }

    public void setTeamPojo(TeamPojo teamPojo) {
        this.teamPojo = teamPojo;
    }

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 教师名称
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * 更新时间
     */
    private Date modifyDate;

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public List<String> getPictureUUIDs() {
        return pictureUUIDs;
    }

    public void setPictureUUIDs(List<String> pictureUUIDs) {
        this.pictureUUIDs = pictureUUIDs;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getPictureUUID() {
        return pictureUUID;
    }

    public void setPictureUUID(String pictureUUID) {
        this.pictureUUID = pictureUUID;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
