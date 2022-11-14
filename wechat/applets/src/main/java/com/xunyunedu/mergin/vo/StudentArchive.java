package com.xunyunedu.mergin.vo;

import framework.generic.dao.Model;

public class StudentArchive implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * pk
     */
    private Integer id;
    /**
     * 学生ID
     */
    private Integer studentId;
    /**
     * 个人记录ID
     */
    private Integer personId;
    /**
     * 入学方式
     */
    private String enrollType;
    /**
     * 入学总分
     */
    private Integer enrollMark;
    /**
     * 义教卡号
     */
    private String ceNumber;
    /**
     * 学生来源
     */
    private String studentSource;
    /**
     * 原毕业学校
     */
    private String graduationSchool;
    /**
     * 是否住宿 1：是 0：否
     */
    private Boolean isBoarded;
    /**
     * 是否受过学前教育
     */
    private Boolean isPreeducated;
    /**
     * 是否由政府购买学位
     */
    private Boolean isBuyedDegree;
    /**
     * 是否烈士或优抚子女
     */
    private Boolean isMartyrChild;
    /**
     * 是否是孤儿
     */
    private Boolean isOrphan;
    /**
     * 是否需要申请资助
     */
    private Boolean isSponsored;
    /**
     * 是否享受一保
     */
    private Boolean isFirstRecommended;
    /**
     * 是否享受营养套餐
     */
    private Boolean isSupportNourishment;
    /**
     * 残疾类型
     */
    private String disabilityType;
    /**
     * 是否随班就读
     */
    private String followStudy;
    /**
     * needSpecialCare
     */
    private Boolean needSpecialCare;
    /**
     * 是否农村留守儿童
     */
    private String isUnattendedChild;
    /**
     * 是否随适子女
     */
    private Boolean isMigratedChild;
    /**
     * 进城务工随迁子女
     */
    private Boolean isCityLabourChild;
    /**
     * 就读方式
     */
    private String attendSchoolType;
    /**
     * 是否乘坐交通方式
     */
    private Boolean bySchoolBus;
    /**
     * 上下学交通方式
     */
    private String trafficType;
    /**
     * 上下学距离(公里)
     */
    private String schoolDistance;

    public StudentArchive() {

    }

    public StudentArchive(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    /**
     * 获取pk
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置pk
     * @param id
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学生ID
     * @return java.lang.Integer
     */
    public Integer getStudentId() {
        return this.studentId;
    }

    /**
     * 设置学生ID
     * @param studentId
     * @type java.lang.Integer
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取个人记录ID
     * @return java.lang.Integer
     */
    public Integer getPersonId() {
        return this.personId;
    }

    /**
     * 设置个人记录ID
     * @param personId
     * @type java.lang.Integer
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    /**
     * 获取入学方式
     * @return java.lang.String
     */
    public String getEnrollType() {
        return this.enrollType;
    }

    /**
     * 设置入学方式
     * @param enrollType
     * @type java.lang.String
     */
    public void setEnrollType(String enrollType) {
        this.enrollType = enrollType;
    }

    /**
     * 获取入学总分
     * @return java.lang.Integer
     */
    public Integer getEnrollMark() {
        return this.enrollMark;
    }

    /**
     * 设置入学总分
     * @param enrollMark
     * @type java.lang.Integer
     */
    public void setEnrollMark(Integer enrollMark) {
        this.enrollMark = enrollMark;
    }

    /**
     * 获取义教卡号
     * @return java.lang.String
     */
    public String getCeNumber() {
        return this.ceNumber;
    }

    /**
     * 设置义教卡号
     * @param ceNumber
     * @type java.lang.String
     */
    public void setCeNumber(String ceNumber) {
        this.ceNumber = ceNumber;
    }

    /**
     * 获取学生来源
     * @return java.lang.String
     */
    public String getStudentSource() {
        return this.studentSource;
    }

    /**
     * 设置学生来源
     * @param studentSource
     * @type java.lang.String
     */
    public void setStudentSource(String studentSource) {
        this.studentSource = studentSource;
    }

    /**
     * 获取原毕业学校
     * @return java.lang.String
     */
    public String getGraduationSchool() {
        return this.graduationSchool;
    }

    /**
     * 设置原毕业学校
     * @param graduationSchool
     * @type java.lang.String
     */
    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    /**
     * 获取是否住宿 1：是 0：否
     * @return java.lang.Boolean
     */
    public Boolean getIsBoarded() {
        return this.isBoarded;
    }

    /**
     * 设置是否住宿 1：是 0：否
     * @param isBoarded
     * @type java.lang.Boolean
     */
    public void setIsBoarded(Boolean isBoarded) {
        this.isBoarded = isBoarded;
    }

    /**
     * 获取是否受过学前教育
     * @return java.lang.Boolean
     */
    public Boolean getIsPreeducated() {
        return this.isPreeducated;
    }

    /**
     * 设置是否受过学前教育
     * @param isPreeducated
     * @type java.lang.Boolean
     */
    public void setIsPreeducated(Boolean isPreeducated) {
        this.isPreeducated = isPreeducated;
    }

    /**
     * 获取是否由政府购买学位
     * @return java.lang.Boolean
     */
    public Boolean getIsBuyedDegree() {
        return this.isBuyedDegree;
    }

    /**
     * 设置是否由政府购买学位
     * @param isBuyedDegree
     * @type java.lang.Boolean
     */
    public void setIsBuyedDegree(Boolean isBuyedDegree) {
        this.isBuyedDegree = isBuyedDegree;
    }

    /**
     * 获取是否烈士或优抚子女
     * @return java.lang.Boolean
     */
    public Boolean getIsMartyrChild() {
        return this.isMartyrChild;
    }

    /**
     * 设置是否烈士或优抚子女
     * @param isMartyrChild
     * @type java.lang.Boolean
     */
    public void setIsMartyrChild(Boolean isMartyrChild) {
        this.isMartyrChild = isMartyrChild;
    }

    /**
     * 获取是否是孤儿
     * @return java.lang.Boolean
     */
    public Boolean getIsOrphan() {
        return this.isOrphan;
    }

    /**
     * 设置是否是孤儿
     * @param isOrphan
     * @type java.lang.Boolean
     */
    public void setIsOrphan(Boolean isOrphan) {
        this.isOrphan = isOrphan;
    }

    /**
     * 获取是否需要申请资助
     * @return java.lang.Boolean
     */
    public Boolean getIsSponsored() {
        return this.isSponsored;
    }

    /**
     * 设置是否需要申请资助
     * @param isSponsored
     * @type java.lang.Boolean
     */
    public void setIsSponsored(Boolean isSponsored) {
        this.isSponsored = isSponsored;
    }

    /**
     * 获取是否享受一保
     * @return java.lang.Boolean
     */
    public Boolean getIsFirstRecommended() {
        return this.isFirstRecommended;
    }

    /**
     * 设置是否享受一保
     * @param isFirstRecommended
     * @type java.lang.Boolean
     */
    public void setIsFirstRecommended(Boolean isFirstRecommended) {
        this.isFirstRecommended = isFirstRecommended;
    }

    /**
     * 获取是否享受营养套餐
     * @return java.lang.Boolean
     */
    public Boolean getIsSupportNourishment() {
        return this.isSupportNourishment;
    }

    /**
     * 设置是否享受营养套餐
     * @param isSupportNourishment
     * @type java.lang.Boolean
     */
    public void setIsSupportNourishment(Boolean isSupportNourishment) {
        this.isSupportNourishment = isSupportNourishment;
    }

    /**
     * 获取残疾类型
     * @return java.lang.String
     */
    public String getDisabilityType() {
        return this.disabilityType;
    }

    /**
     * 设置残疾类型
     * @param disabilityType
     * @type java.lang.String
     */
    public void setDisabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
    }

    /**
     * 获取是否随班就读
     * @return java.lang.String
     */
    public String getFollowStudy() {
        return this.followStudy;
    }

    /**
     * 设置是否随班就读
     * @param followStudy
     * @type java.lang.String
     */
    public void setFollowStudy(String followStudy) {
        this.followStudy = followStudy;
    }

    /**
     * 获取needSpecialCare
     * @return java.lang.Boolean
     */
    public Boolean getNeedSpecialCare() {
        return this.needSpecialCare;
    }

    /**
     * 设置needSpecialCare
     * @param needSpecialCare
     * @type java.lang.Boolean
     */
    public void setNeedSpecialCare(Boolean needSpecialCare) {
        this.needSpecialCare = needSpecialCare;
    }

    /**
     * 获取是否农村留守儿童
     * @return java.lang.Boolean
     */
    public String getIsUnattendedChild() {
        return this.isUnattendedChild;
    }

    /**
     * 设置是否农村留守儿童
     * @param isUnattendedChild
     * @type java.lang.Boolean
     */
    public void setIsUnattendedChild(String isUnattendedChild) {
        this.isUnattendedChild = isUnattendedChild;
    }

    /**
     * 获取是否随适子女
     * @return java.lang.Boolean
     */
    public Boolean getIsMigratedChild() {
        return this.isMigratedChild;
    }

    /**
     * 设置是否随适子女
     * @param isMigratedChild
     * @type java.lang.Boolean
     */
    public void setIsMigratedChild(Boolean isMigratedChild) {
        this.isMigratedChild = isMigratedChild;
    }

    /**
     * 获取进城务工随迁子女
     * @return java.lang.Boolean
     */
    public Boolean getIsCityLabourChild() {
        return this.isCityLabourChild;
    }

    /**
     * 设置进城务工随迁子女
     * @param isCityLabourChild
     * @type java.lang.Boolean
     */
    public void setIsCityLabourChild(Boolean isCityLabourChild) {
        this.isCityLabourChild = isCityLabourChild;
    }

    /**
     * 获取就读方式
     * @return java.lang.String
     */
    public String getAttendSchoolType() {
        return this.attendSchoolType;
    }

    /**
     * 设置就读方式
     * @param attendSchoolType
     * @type java.lang.String
     */
    public void setAttendSchoolType(String attendSchoolType) {
        this.attendSchoolType = attendSchoolType;
    }

    /**
     * 获取是否乘坐交通方式
     * @return java.lang.Boolean
     */
    public Boolean getBySchoolBus() {
        return this.bySchoolBus;
    }

    /**
     * 设置是否乘坐交通方式
     * @param bySchoolBus
     * @type java.lang.Boolean
     */
    public void setBySchoolBus(Boolean bySchoolBus) {
        this.bySchoolBus = bySchoolBus;
    }

    /**
     * 获取上下学交通方式
     * @return java.lang.String
     */
    public String getTrafficType() {
        return this.trafficType;
    }

    /**
     * 设置上下学交通方式
     * @param trafficType
     * @type java.lang.String
     */
    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    /**
     * 获取上下学距离(公里)
     * @return java.lang.String
     */
    public String getSchoolDistance() {
        return this.schoolDistance;
    }

    /**
     * 设置上下学距离(公里)
     * @param schoolDistance
     * @type java.lang.String
     */
    public void setSchoolDistance(String schoolDistance) {
        this.schoolDistance = schoolDistance;
    }

}
