package com.xunyunedu.student.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/8 15:59
 * @Description: 学生表现
 */
public class PerformancePojo implements Serializable {

    /**
     * 动态查询数据库
     */
    private String assesName;

    /**
     * id
     */
    private Integer id;

    /**
     * 学校id
     */
//    @NotNull(message = "学校id不能为空")
//    private Integer schoolId;

    /**
     * 批量选择学生id
     */
    @NotNull(message = "学生id不能为空")
    private Integer[] studentIds;

    /**
     * 老师id
     */
    @NotNull(message = "老师id不能为空")
    private Integer teacherId;

    /**
     * 老师姓名
     */
    private String teacherName;

    /**
     * 班级id
     */
    private Integer teamId;

    /**
     * 班级名称
     */
    private String teamName;

    /**
     * 表现情况 0：表扬 1：批评
     */
    @NotNull(message = "表现情况不能为空")
    private Integer performanceType;

    /**
     * 星级评定 10颗星
     */
//    @NotNull(message = "星级评定不能为空")
    private Integer stars;

    /**
     * 表现标题
     */
    @NotNull(message = "表现标题不能为空")
    private String showTitle;

    /**
     * 表现内容
     */
    @NotNull(message = "表现内容不能为空")
    private String showContent;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    private String uuid;

    /**
     * 批量添加图片
     */
    private String[] photos;

    /**
     * 图片地址
     */
    private String[] url;

    /**
     * 学生id
     */
    private Integer studentId;

    private String stuName;

    private List<StudentDO> studentDOList;


    public String getAssesName() {
        return assesName;
    }

    public void setAssesName(String assesName) {
        this.assesName = assesName;
    }

    public List<StudentDO> getStudentDOList() {
        return studentDOList;
    }

    public void setStudentDOList(List<StudentDO> studentDOList) {
        this.studentDOList = studentDOList;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }

    private static final long serialVersionUID = 1L;

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

//    public Integer getSchoolId() {
//        return schoolId;
//    }
//
//    public void setSchoolId(Integer schoolId) {
//        this.schoolId = schoolId;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer[] getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(Integer[] studentIds) {
        this.studentIds = studentIds;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public Integer getPerformanceType() {
        return performanceType;
    }

    public void setPerformanceType(Integer performanceType) {
        this.performanceType = performanceType;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}