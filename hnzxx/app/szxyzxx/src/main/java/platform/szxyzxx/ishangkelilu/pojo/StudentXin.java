package platform.szxyzxx.ishangkelilu.pojo;

public class StudentXin {
    /**
     * 主健ID
     */
    private Integer id;
    /**
     * 所属学校
     */
    private Integer schoolId;
    /**
     * 对应的人
     */
    private Integer personId;
    /**
     * 用户帐号
     */
    private Integer userId;
    /**
     * 当前最后所在班ID
     */
    private Integer teamId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 当前最后所在班级名称
     */
    private String teamName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;


    /**
     * 手机号码
     */
    private String mobile;
    /**
     * jiazhang手机号码
     */
    private String mobileparent;
    /**
     * 学生在读状态:01=在读。02=休学。03=退学。04=停学。07=毕业。08=结业。09=肄业。10=转学.11=死亡 14=开除。99=其它
     */
    private String studyState;


    public StudentXin(){}
    public StudentXin(Integer schoolId, Integer personId, Integer userId, Integer teamId, String userName, String teamName, String name, String sex, String mobile, String mobileparent, String studyState) {
        this.schoolId = schoolId;
        this.personId = personId;
        this.userId = userId;
        this.teamId = teamId;
        this.userName = userName;
        this.teamName = teamName;
        this.name = name;
        this.sex = sex;
        this.mobile = mobile;
        this.mobileparent = mobileparent;
        this.studyState = studyState;
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

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileparent() {
        return mobileparent;
    }

    public void setMobileparent(String mobileparent) {
        this.mobileparent = mobileparent;
    }

    public String getStudyState() {
        return studyState;
    }

    public void setStudyState(String studyState) {
        this.studyState = studyState;
    }
}

