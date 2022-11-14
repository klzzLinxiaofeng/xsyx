package com.xunyunedu.student.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Teacher
 *
 * @author: yhc
 * @Date: 2020/10/15 9:35
 * @Description:
 */
public class TeacherPojo implements Serializable {


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
    /**
     * 岗位编制 1=教学 2=行政 3=教辅 4=工勤 5= 校企 9=其它 0=无
     */
    private String postStaffing;
    /**
     * 工作状态 11=在职 01=退休 02=离休 03=死亡 05=调出 06=辞职 07=离职 08=开除 14=长假 15=因公出国16=停薪留职 18=合同终止 99=其它
     */
    private String jobState;
    /**
     * 删除标志
     */
    private Boolean isDelete;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * modifyDate
     */
    private Date modifyDate;

    /**
     * 图片ID
     */
    private String entityId;

    /**
     * 教师类型
     */
    private String type;

    /**
     * 班级ID
     */
    private String teamId;
    /**
     * 班级名称
     */
    private String teamName;
    /**
     * 科目名称
     */
    private String subjectName;
    /**
     * 科目CODE
     *
     * @return
     */
    private String subjectCode;

    /**
     * 教师总数
     *
     * @return
     */
    private String teacherNum;

    //添加于2015-11-18
    /**
     * 别名
     */
    private String alias;
    /**
     * 是否外聘
     */
    private Boolean isExternal;

    /**
     * 图片
     */
    private String photoUuid;

    /**
     * 图片url
     */
    private String httpUrl;

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getPhotoUuid() {
        return photoUuid;
    }

    public void setPhotoUuid(String photoUuid) {
        this.photoUuid = photoUuid;
    }

    //添加于2015-11-18

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

//	public void setDelete(boolean isDelete) {
//		this.isDelete = isDelete;
//	}

    public TeacherPojo() {

    }

    public TeacherPojo(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    /**
     * 获取主健ID
     *
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置主健ID
     *
     * @param id
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取所在学校
     *
     * @return java.lang.Integer
     */
    public Integer getSchoolId() {
        return this.schoolId;
    }

    /**
     * 设置所在学校
     *
     * @param schoolId
     * @type java.lang.Integer
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 获取对应的人
     *
     * @return java.lang.Integer
     */
    public Integer getPersionId() {
        return this.persionId;
    }

    /**
     * 设置对应的人
     *
     * @param persionId
     * @type java.lang.Integer
     */
    public void setPersionId(Integer persionId) {
        this.persionId = persionId;
    }

    /**
     * 获取用户帐号
     *
     * @return java.lang.Integer
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置用户帐号
     *
     * @param userId
     * @type java.lang.Integer
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return java.lang.String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置用户名
     *
     * @param userName
     * @type java.lang.String
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取姓名
     *
     * @return java.lang.String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置姓名
     *
     * @param name
     * @type java.lang.String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     *
     * @return java.lang.String
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * 设置性别
     *
     * @param sex
     * @type java.lang.String
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取全国统一工号
     *
     * @return java.lang.String
     */
    public String getJobNumber() {
        return this.jobNumber;
    }

    /**
     * 设置全国统一工号
     *
     * @param jobNumber
     * @type java.lang.String
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * 获取入职时间
     *
     * @return java.util.Date
     */
    public Date getEnrollDate() {
        return this.enrollDate;
    }

    /**
     * 设置入职时间
     *
     * @param enrollDate
     * @type java.util.Date
     */
    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    /**
     * 获取离职时间
     *
     * @return java.util.Date
     */
    public Date getLeaveDate() {
        return this.leaveDate;
    }

    /**
     * 设置离职时间
     *
     * @param leaveDate
     * @type java.util.Date
     */
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

    /**
     * 获取职务
     *
     * @return java.lang.String
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * 设置职务
     *
     * @param position
     * @type java.lang.String
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取手机
     *
     * @return java.lang.String
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 设置手机
     *
     * @param mobile
     * @type java.lang.String
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取办公电话
     *
     * @return java.lang.String
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * 设置办公电话
     *
     * @param telephone
     * @type java.lang.String
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取岗位编制 1=教学 2=行政 3=教辅 4=工勤 5= 校企 9=其它 0=无
     *
     * @return java.lang.String
     */
    public String getPostStaffing() {
        return this.postStaffing;
    }

    /**
     * 设置岗位编制 1=教学 2=行政 3=教辅 4=工勤 5= 校企 9=其它 0=无
     *
     * @param postStaffing
     * @type java.lang.String
     */
    public void setPostStaffing(String postStaffing) {
        this.postStaffing = postStaffing;
    }

    /**
     * 获取工作状态 11=在职 01=退休 02=离休 03=死亡 05=调出 06=辞职 07=离职 08=开除 14=长假 15=因公出国16=停薪留职 18=合同终止 99=其它
     *
     * @return java.lang.String
     */
    public String getJobState() {
        return this.jobState;
    }

    /**
     * 设置工作状态 11=在职 01=退休 02=离休 03=死亡 05=调出 06=辞职 07=离职 08=开除 14=长假 15=因公出国16=停薪留职 18=合同终止 99=其它
     *
     * @param jobState
     * @type java.lang.String
     */
    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    /**
     * 获取删除标志
     *
     * @return java.lang.String
     */
    public Boolean getIsDelete() {
        return this.isDelete;
    }

    /**
     * 设置删除标志
     *
     * @param isDelete
     * @type java.lang.String
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建时间
     *
     * @return java.util.Date
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate
     * @type java.util.Date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取modifyDate
     *
     * @return java.util.Date
     */
    public Date getModifyDate() {
        return this.modifyDate;
    }

    /**
     * 设置modifyDate
     *
     * @param modifyDate
     * @type java.util.Date
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
    }

    @Override
    public String toString() {
        return "TeacherPojo{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", persionId=" + persionId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
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
                ", postStaffing='" + postStaffing + '\'' +
                ", jobState='" + jobState + '\'' +
                ", isDelete=" + isDelete +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", entityId='" + entityId + '\'' +
                ", type='" + type + '\'' +
                ", teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", teacherNum='" + teacherNum + '\'' +
                ", alias='" + alias + '\'' +
                ", isExternal=" + isExternal +
                '}';
    }
}