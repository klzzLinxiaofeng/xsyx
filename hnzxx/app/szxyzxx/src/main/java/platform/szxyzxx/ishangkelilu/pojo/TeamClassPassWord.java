package platform.szxyzxx.ishangkelilu.pojo;


import java.util.Date;


public class TeamClassPassWord {
    /*
    *主键id
    */
    private Integer id;
    /*
     *学校id
     */
    private Integer schoolId;
    /*
     * 班级id
     */
    private Integer teamId;
    /*
     *年级id
     */
        private Integer gradeId;
    /*
     *年级name
     */
    private String gradeName;
    /*
     *班级名称
     */
    private String teamName;
    /*
     *密码
     */
    private String passWord;
    /*
     *创建时间
     */
    private Date createTime;
    /*
     *修改时间
     */
    private Date modiyTime;

    /*
     *学年
     */
    private String schoolYear;
    /*
     *学期
     */
    private String schoolTrem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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

    public String getSchoolTrem() {
        return schoolTrem;
    }

    public void setSchoolTrem(String schoolTrem) {
        this.schoolTrem = schoolTrem;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
}
