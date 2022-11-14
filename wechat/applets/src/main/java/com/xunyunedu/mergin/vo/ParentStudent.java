package com.xunyunedu.mergin.vo;

import java.util.Date;

public class ParentStudent {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;

    /**
     * 家长用户帐号ID yh_user.id
     */
    private Integer userId;
    /**
     * 用户姓名(冗余) yh_user.username
     */
    private String userName;
    /**
     * 家长档案记录 pj_person.id
     */
    private Integer personId;
    /**
     * 家长姓名
     */
    private String name;
    /**
     * 家长手机号
     */
    private String mobile;






    /**
     * 学生用户账号id，yh_user.id
     */
    private Integer studentUserId;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 家长用户账号id，yh_user.id
     */
    private Integer parentUserId;
    /**
     * 学生与家长关系（家庭关系）GB-JTGX
     */
    private String parentRelation;
    /**
     * 家长关系类别，0==普通  ，1==主监护人  XY-JY-JZLB
     */
    private String rank;
    /**
     * 是否删除，0为不删除，1为删除
     */
    private Boolean isDelete;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modifyDate;

    //2016-7-21新增字段
    private String relationRemarks;

    private String email;

    /**
     * 学生名称
     */
    private String stuName;
    /**
     * 班级名称
     */
    private String teamName;

    private String schoolName;

    /**
     * 用户状态
     */
    public String userState;

    /**
     * 学生卡号
     */
    private String empCard;

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
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

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getRelationRemarks() {
        return relationRemarks;
    }

    public void setRelationRemarks(String relationRemarks) {
        this.relationRemarks = relationRemarks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ParentStudent() {

    }

    public ParentStudent(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    /**
     * 获取id
     *
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     *
     * @param id
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学生用户账号id，yh_user.id
     *
     * @return java.lang.Integer
     */
    public Integer getStudentUserId() {
        return this.studentUserId;
    }

    /**
     * 设置学生用户账号id，yh_user.id
     *
     * @param studentUserId
     * @type java.lang.Integer
     */
    public void setStudentUserId(Integer studentUserId) {
        this.studentUserId = studentUserId;
    }

    /**
     * 获取学生姓名
     *
     * @return java.lang.String
     */
    public String getStudentName() {
        return this.studentName;
    }

    /**
     * 设置学生姓名
     *
     * @param studentName
     * @type java.lang.String
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * 获取家长用户账号id，yh_user.id
     *
     * @return java.lang.Integer
     */
    public Integer getParentUserId() {
        return this.parentUserId;
    }

    /**
     * 设置家长用户账号id，yh_user.id
     *
     * @param parentUserId
     * @type java.lang.Integer
     */
    public void setParentUserId(Integer parentUserId) {
        this.parentUserId = parentUserId;
    }

    /**
     * 获取学生与家长关系（家庭关系）GB-JTGX
     *
     * @return java.lang.String
     */
    public String getParentRelation() {
        return this.parentRelation;
    }

    /**
     * 设置学生与家长关系（家庭关系）GB-JTGX
     *
     * @param parentRelation
     * @type java.lang.String
     */
    public void setParentRelation(String parentRelation) {
        this.parentRelation = parentRelation;
    }

    /**
     * 获取家长关系类别，0==普通  ，1==主监护人  XY-JY-JZLB
     *
     * @return java.lang.String
     */
    public String getRank() {
        return this.rank;
    }

    /**
     * 设置家长关系类别，0==普通  ，1==主监护人  XY-JY-JZLB
     *
     * @param rank
     * @type java.lang.String
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 获取是否删除，0为不删除，1为删除
     *
     * @return java.lang.Boolean
     */
    public Boolean isIsDelete() {
        return this.isDelete;
    }

    /**
     * 设置是否删除，0为不删除，1为删除
     *
     * @param isDelete
     * @type java.lang.Boolean
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
     * 获取修改时间
     *
     * @return java.util.Date
     */
    public Date getModifyDate() {
        return this.modifyDate;
    }

    /**
     * 设置修改时间
     *
     * @param modifyDate
     * @type java.util.Date
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}
