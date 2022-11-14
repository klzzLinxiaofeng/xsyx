package platform.education.generalTeachingAffair.vo;

import framework.generic.dao.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/8 15:59
 * @Description: 学生表现
 */
public class PerformancePojo implements Model<Integer> {

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
    private String[] studentIds;

    private String students;

    /**
     * 老师id
     */
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
    private Integer performanceType;

    /**
     * 星级评定 10颗星
     */
    private Integer stars;

    /**
     * 表现标题
     */
    private String showTitle;

    /**
     * 表现内容
     */
    private String showContent;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    private String uuid;

    /**
     * 批量添加图片
     */
    private List<String> photos;

    /**
     * 图片地址
     */
    private String[] url;

    /**
     * 学生id
     */
    private Integer studentId;

    private String stuName;

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
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

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
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

    public String[] getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String[] studentIds) {
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

    @Override
    public Integer getKey() {
        return this.id;
    }
}