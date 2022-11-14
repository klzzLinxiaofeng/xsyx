package platform.szxyzxx.mentalhealth.vo;

import java.util.Date;
import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 11:52
 * @Version 1.0
 */
public class MentalBehavior {
    /*
     * id
     */
    private Integer id;
    /*
     * 学生id
     */
    private Integer  studentId;
    /*
     * 学生name
     */
    private String  studentName;

    /*
     * 学生班级
     */
    private String  teamName;
    /*
     * 指标id
     */
    private Integer mentalIndicatorsId;
    /*
     * 指标名称
     */
    private String mentalIndicatorsName;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date  modiyTime;
    /*
     *
     */
    private Integer  isDelete;
    /*
     * 学年
     */
    private String schoolYear;
    /*
     * 学期
     */
    private String schoolTrem;
    /*
     * 分数
     */
    private Integer fenshu;
    /*
     * 评语
     */
    private String pingyu;
    /*
     * 点评教师
     */
    private String teacherName;
    List<MentalIndicators> lifeIndicatorsList;
    public MentalBehavior(){}

    public MentalBehavior(Integer id, Integer studentId, String studentName, String teamName, Integer mentalIndicatorsId, String mentalIndicatorsName, Date createTime, Date modiyTime, Integer isDelete, String schoolYear, String schoolTrem, Integer fenshu, String pingyu, String teacherName, List<MentalIndicators> lifeIndicatorsList) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.teamName = teamName;
        this.mentalIndicatorsId = mentalIndicatorsId;
        this.mentalIndicatorsName = mentalIndicatorsName;
        this.createTime = createTime;
        this.modiyTime = modiyTime;
        this.isDelete = isDelete;
        this.schoolYear = schoolYear;
        this.schoolTrem = schoolTrem;
        this.fenshu = fenshu;
        this.pingyu = pingyu;
        this.teacherName = teacherName;
        this.lifeIndicatorsList = lifeIndicatorsList;
    }

    public List<MentalIndicators> getLifeIndicatorsList() {
        return lifeIndicatorsList;
    }

    public void setLifeIndicatorsList(List<MentalIndicators> lifeIndicatorsList) {
        this.lifeIndicatorsList = lifeIndicatorsList;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getMentalIndicatorsId() {
        return mentalIndicatorsId;
    }

    public void setMentalIndicatorsId(Integer mentalIndicatorsId) {
        this.mentalIndicatorsId = mentalIndicatorsId;
    }

    public String getMentalIndicatorsName() {
        return mentalIndicatorsName;
    }

    public void setMentalIndicatorsName(String mentalIndicatorsName) {
        this.mentalIndicatorsName = mentalIndicatorsName;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

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

    public Integer getFenshu() {
        return fenshu;
    }

    public void setFenshu(Integer fenshu) {
        this.fenshu = fenshu;
    }

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }
}
