package com.xunyunedu.mergin.vo;

import platform.education.generalTeachingAffair.model.Parent;

public class ParentVo extends Parent {

    private static final long serialVersionUID = 1L;

    private Integer schoolId; //学校id
    private String schoolYear;//学年
    private Integer gradeId;//年级id
    private Integer teamId;//班级id
    private Integer studentId;
    private String studentNumber;//全国唯一学籍号
    private String parentRelation;//学生与家长关系（家庭关系）
    private String rank;//家长关系类别 0==普通，1==主监护人
    private String gradeName;///年级Name
    private String teamName;////班级Name
    private String studentName;//
    private String errorInfo;//添加失败的错误信息
    private String schoolName;//学校名称
    private Integer studentUserId;
    private String studentUserName;
    private Integer system_app_id;//SysContants.SYSTEM_APP_ID
    private boolean addStatus;
    private Integer roleId;
    public static String roleType = "3";//家长角色类型默认为三
    public String userState;//用户状态
    //2016-4-11  添加  错误代码（每一个错误有一个自己的编码）
    private String errorCode;
    //2016-4-20 添加详细错误
    private String detail;

    /*----------2017-11-15----------  */
    // 性别（无入口录入，大部分为null）
    private String sex;
    /**
     * 	学生信息
     * 	studentUserId,studentName,parentRelation,rank
     * 	2017-11-15
     */
    private String studentInfo;

    private String licensePlate;


    /*--------------------------------*/
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public boolean isAddStatus() {
        return addStatus;
    }
    public void setAddStatus(boolean addStatus) {
        this.addStatus = addStatus;
    }
    public Integer getSystem_app_id() {
        return system_app_id;
    }
    public void setSystem_app_id(Integer system_app_id) {
        this.system_app_id = system_app_id;
    }
    public Integer getStudentUserId() {
        return studentUserId;
    }
    public void setStudentUserId(Integer studentUserId) {
        this.studentUserId = studentUserId;
    }
    public String getStudentUserName() {
        return studentUserName;
    }
    public void setStudentUserName(String studentUserName) {
        this.studentUserName = studentUserName;
    }
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public Integer getSchoolId() {
        return schoolId;
    }
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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
    public String getSchoolYear() {
        return schoolYear;
    }
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
    public String getParentRelation() {
        return parentRelation;
    }
    public void setParentRelation(String parentRelation) {
        this.parentRelation = parentRelation;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
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
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getUserState() {
        return userState;
    }
    public void setUserState(String userState) {
        this.userState = userState;
    }
    @Override
    public String toString() {

        StringBuffer sbf = new StringBuffer();
        sbf.append("学校id:"+this.schoolId);
        sbf.append(",学年:"+this.schoolYear);
        sbf.append(",年级id:"+this.gradeId);
        sbf.append("班级id:"+this.teamId);
        sbf.append(",年级Name:"+this.gradeName);
        sbf.append(",班级Name:"+this.teamName);
        sbf.append(",全国唯一学籍号:"+this.studentNumber);
        sbf.append(",家长关系:"+this.parentRelation);
        sbf.append(",类别:"+this.rank);
        sbf.append(",姓名:"+super.getName());
        sbf.append(",手机号:"+super.getMobile());
        sbf.append(",邮箱:"+super.getEmail());

        return sbf.toString();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(String studentInfo) {
        this.studentInfo = studentInfo;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
