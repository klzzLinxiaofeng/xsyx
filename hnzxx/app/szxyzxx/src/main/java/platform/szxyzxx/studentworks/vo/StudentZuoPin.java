package platform.szxyzxx.studentworks.vo;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/10 14:05
 * @Version 1.0
 */
public class StudentZuoPin {
    /*
    * id
    */
    private Integer id;
    /*
     * studentId
     */
    private Integer studentId;
    /*
     * student名字
     */
    private String studentName;
    /*
     * createTime
     */
    private Date createTime;
    /*
     * modiyTime
     */
    private Date modiyTime;
    /*
     * modiyTime
     */
    private Integer isDelete;
    /*
     * 作品id
     */
    private String zuopingUuid;
    /*
     * 奖状id
     */
    private String jiangzhuangUuid;
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
    /*
     * 班级id
     */
    private Integer teamId;
    /*
     * 年级名称
     */
    private String gradeName;
    /*
     * 班级名称
     */
    private String teamName;
    public StudentZuoPin(){}
    public StudentZuoPin(Integer id, Integer studentId, String studentName, Date createTime, Date modiyTime, Integer isDelete, String zuopingUuid, String jiangzhuangUuid, String schoolYear, String schoolTrem, Integer gradeId, Integer teamId, String gradeName, String teamName) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.createTime = createTime;
        this.modiyTime = modiyTime;
        this.isDelete = isDelete;
        this.zuopingUuid = zuopingUuid;
        this.jiangzhuangUuid = jiangzhuangUuid;
        this.schoolYear = schoolYear;
        this.schoolTrem = schoolTrem;
        this.gradeId = gradeId;
        this.teamId = teamId;
        this.gradeName = gradeName;
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

    public String getZuopingUuid() {
        return zuopingUuid;
    }

    public void setZuopingUuid(String zuopingUuid) {
        this.zuopingUuid = zuopingUuid;
    }

    public String getJiangzhuangUuid() {
        return jiangzhuangUuid;
    }

    public void setJiangzhuangUuid(String jiangzhuangUuid) {
        this.jiangzhuangUuid = jiangzhuangUuid;
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

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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
}
