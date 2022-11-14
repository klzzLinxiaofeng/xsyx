package platform.education.generalTeachingAffair.vo;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * Teacher
 */
public class TeacherApiVo implements Model<Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * 主健ID
     */
    private Integer id;
    /**
     * 所在学校
     */
    private Integer schoolId;
    /**
     * 对应的人
     */
    private Integer persionId;
    /**
     * 用户帐号
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 别名
     */
    private String alias;
    /**
     * 性别
     */
    private String sex;
    /**
     * 全国统一工号
     */
    private String jobNumber;
    /**
     * 入职时间
     */
    private Date enrollDate;
    /**
     * 离职时间
     */
    private Date leaveDate;
    /**
     * 参加工作时间
     */
    private Date workBeginDate;
    /**
     * 学历
     */
    private String qualification;
    /**
     * 岗位职业码
     */
    private String occupationCode;
    /**
     * 职务
     */
    private String position;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 办公电话
     */
    private String telephone;
    private String idCardNumber; //身份证号码
    /**
     * 岗位编制 1=教学 2=行政 3=教辅 4=工勤 5= 校企 9=其它 0=无
     */
    private String postStaffing;
    /**
     * 工作状态 11=在职 01=退休 02=离休 03=死亡 05=调出 06=辞职 07=离职 08=开除 14=长假 15=因公出国16=停薪留职 18=合同终止 99=其它
     */
    private String jobState;
    /**
     * 是否外聘
     */
    private Boolean isExternal;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * modifyDate
     */
    private Date modifyDate;

    private String departmentName;

    private String departmentId;

    private String email;

    @Override
    public Integer getKey() {
        return this.id;
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

    public Integer getPersionId() {
        return persionId;
    }

    public void setPersionId(Integer persionId) {
        this.persionId = persionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Date getWorkBeginDate() {
        return workBeginDate;
    }

    public void setWorkBeginDate(Date workBeginDate) {
        this.workBeginDate = workBeginDate;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPostStaffing() {
        return postStaffing;
    }

    public void setPostStaffing(String postStaffing) {
        this.postStaffing = postStaffing;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    public Boolean getExternal() {
        return isExternal;
    }

    public void setExternal(Boolean external) {
        isExternal = external;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "TeacherApiVo{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", persionId=" + persionId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", sex='" + sex + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", enrollDate=" + enrollDate +
                ", leaveDate=" + leaveDate +
                ", workBeginDate=" + workBeginDate +
                ", qualification='" + qualification + '\'' +
                ", occupationCode='" + occupationCode + '\'' +
                ", position='" + position + '\'' +
                ", mobile='" + mobile + '\'' +
                ", telephone='" + telephone + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", postStaffing='" + postStaffing + '\'' +
                ", jobState='" + jobState + '\'' +
                ", isExternal=" + isExternal +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", departmentName='" + departmentName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}