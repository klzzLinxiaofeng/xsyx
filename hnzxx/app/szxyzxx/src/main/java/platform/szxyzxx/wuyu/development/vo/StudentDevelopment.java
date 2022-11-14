package platform.szxyzxx.wuyu.development.vo;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/11 9:48
 * @Version 1.0
 */
public class StudentDevelopment {
    private Integer id;
    /*
    * 指标id
    */
    private Integer developmentId;
    /*
    * 指标名称
    */
    private String developmentName;
    /*
    * 年级Id
    */
    private Integer gradeId;
    /*
     * 年级Name
     */
    private String gradeName;
    /*
     * 班级Id
     */
    private Integer teamId;
    /*
     * 班级Name
     */
    private String teamName;
    /*
    * 评价人id
    */
    private Integer pingjiaId;
    /*
     * 评价人姓名
     */
    private String pingjiaName;

    /*
    * 学生id
    */
    private Integer studentId;
    /*
    * 学生姓名
    */
    private String studentName;
    /*
    * 评价分数
    */
    private Integer score;
    /*
    * 学年
    */
    private String schoolYear;
    /*
    * 学期
    */
    private Integer schoolTrem;

    private Date createTime;

    private Date modiyTime;

    private Integer isDelete;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }


    public Integer getPingjiaId() {
        return pingjiaId;
    }

    public void setPingjiaId(Integer pingjiaId) {
        this.pingjiaId = pingjiaId;
    }

    public String getPingjiaName() {
        return pingjiaName;
    }

    public void setPingjiaName(String pingjiaName) {
        this.pingjiaName = pingjiaName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevelopmentId() {
        return developmentId;
    }

    public void setDevelopmentId(Integer developmentId) {
        this.developmentId = developmentId;
    }

    public String getDevelopmentName() {
        return developmentName;
    }

    public void setDevelopmentName(String developmentName) {
        this.developmentName = developmentName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getSchoolTrem() {
        return schoolTrem;
    }

    public void setSchoolTrem(Integer schoolTrem) {
        this.schoolTrem = schoolTrem;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(Date modiyTime) {
        this.modiyTime = modiyTime;
    }
}
