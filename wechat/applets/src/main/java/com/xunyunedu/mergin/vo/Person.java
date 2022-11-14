package com.xunyunedu.mergin.vo;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * 个人档案
 * JwPerson
 *
 * @author AutoCreate
 */
public class Person implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 英文名字
     */
    private String englishName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 身份证
     */
    private String idCardNumber;
    /**
     * 证件类型
     */
    private String idCardType;
    /**
     * 港澳台侨
     */
    private String abroadCode;

    /**
     * 特长
     */
    private String specialSkill;

    /**
     * 是否是独生子
     */
    private Boolean isOnlyChild;
    /**
     * 是否是流动人口
     */
    private Boolean isFloatingPopulation;

    /**
     * 国籍
     */
    private String nationality;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 出生地
     */
    private String birthPlace;
    /**
     * 民族
     */
    private String race;
    /**
     * 婚姻状况
     */
    private String marriage;
    /**
     * 政治面貌
     */
    private String politicalStatus;
    /**
     * 户口性质
     */
    private String residenceType;
    /**
     * 宗教信仰
     */
    private String religion;
    /**
     * 健康状况
     */
    private String healthStatus;
    /**
     * 血型
     */
    private String bloodType;
    /**
     * 行政区代码
     */
    private String regionCode;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String district;

    /**
     * =====2016-7-21新增字段
     */

    /**
     * 拼音
     */
    private String pinyinName;

    /**
     * 曾用名
     */
    private String usedName;

    /**
     * 身份证有效日期
     */
    private Date idCardDate;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 家庭地址
     */
    private String homeAddress;

    /**
     * 所住房屋权属
     */
    private String houseAuthority;

    /**
     * 工作单位
     */
    private String workingPlace;

    /**
     * 职务
     */
    private String position;

    /**
     * 个人主页
     */
    private String homepage;

    /**
     * 社区/村
     */
    private String village;

    /**
     * 户籍备注
     */
    private String residenceRemark;

    //2018-3-14新增
    /**
     * 简历
     */
    private String resume;

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPinyinName() {
        return pinyinName;
    }

    public void setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
    }

    public String getUsedName() {
        return usedName;
    }

    public void setUsedName(String usedName) {
        this.usedName = usedName;
    }

    public Date getIdCardDate() {
        return idCardDate;
    }

    public void setIdCardDate(Date idCardDate) {
        this.idCardDate = idCardDate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHouseAuthority() {
        return houseAuthority;
    }

    public void setHouseAuthority(String houseAuthority) {
        this.houseAuthority = houseAuthority;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getResidenceRemark() {
        return residenceRemark;
    }

    public void setResidenceRemark(String residenceRemark) {
        this.residenceRemark = residenceRemark;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 街道
     */
    private String street;
    /**
     * 地址
     */
    private String address;
    /**
     * 居住地址
     */
    private String resideAddress;

    /**
     * 户口所在地
     */
    private String residenceAddress;


    /**
     * 邮编
     */
    private String zipcode;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String remark;

    /**
     * 照片的uuid
     */
    private String photoUuid;
    /**
     * 图片
     */
    private String entityId;

    /**
     * 删除标识
     */
    private boolean isDelete;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modifyDate;

    public Person() {

    }

    public Person(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    /**
     * 获取主键
     *
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置主键
     *
     * @param id
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 获取创建时间
     *
     * @return java.util.Date
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * 设置创建时间
     *
     * @param birthday
     * @type java.util.Date
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取身份证
     *
     * @return java.lang.String
     */
    public String getIdCardNumber() {
        return this.idCardNumber;
    }

    /**
     * 设置身份证
     *
     * @param idCardNumber
     * @type java.lang.String
     */
    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    /**
     * 获取国籍
     *
     * @return java.lang.String
     */
    public String getNationality() {
        return this.nationality;
    }

    /**
     * 设置国籍
     *
     * @param nationality
     * @type java.lang.String
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * 获取籍贯
     *
     * @return java.lang.String
     */
    public String getNativePlace() {
        return this.nativePlace;
    }

    /**
     * 设置籍贯
     *
     * @param nativePlace
     * @type java.lang.String
     */
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    /**
     * 获取出生地
     *
     * @return java.lang.String
     */
    public String getBirthPlace() {
        return this.birthPlace;
    }

    /**
     * 设置出生地
     *
     * @param birthdayPlace
     * @type java.lang.String
     */
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    /**
     * 获取民族
     *
     * @return java.lang.String
     */
    public String getRace() {
        return this.race;
    }

    /**
     * 设置民族
     *
     * @param race
     * @type java.lang.String
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * 获取婚姻状况
     *
     * @return java.lang.String
     */
    public String getMarriage() {
        return this.marriage;
    }

    /**
     * 设置婚姻状况
     *
     * @param marriage
     * @type java.lang.String
     */
    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    /**
     * 获取政治面貌
     *
     * @return java.lang.String
     */
    public String getPoliticalStatus() {
        return this.politicalStatus;
    }

    /**
     * 设置政治面貌
     *
     * @param politicalStatus
     * @type java.lang.String
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 获取户口性质
     *
     * @return java.lang.String
     */
    public String getResidenceType() {
        return this.residenceType;
    }

    /**
     * 设置户口性质
     *
     * @param residenceType
     * @type java.lang.String
     */
    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    /**
     * 获取宗教信仰
     *
     * @return java.lang.String
     */
    public String getReligion() {
        return this.religion;
    }

    /**
     * 设置宗教信仰
     *
     * @param religion
     * @type java.lang.String
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * 获取健康状兑
     *
     * @return java.lang.String
     */
    public String getHealthStatus() {
        return this.healthStatus;
    }

    /**
     * 设置健康状兑
     *
     * @param healthStatus
     * @type java.lang.String
     */
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    /**
     * 获取血型
     *
     * @return java.lang.String
     */
    public String getBloodType() {
        return this.bloodType;
    }

    /**
     * 设置血型
     *
     * @param bloodType
     * @type java.lang.String
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * 获取行政区代码
     *
     * @return java.lang.String
     */
    public String getRegionCode() {
        return this.regionCode;
    }

    /**
     * 设置行政区代码
     *
     * @param regionCode
     * @type java.lang.String
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * 获取省份
     *
     * @return java.lang.String
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * 设置省份
     *
     * @param province
     * @type java.lang.String
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return java.lang.String
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 设置城市
     *
     * @param city
     * @type java.lang.String
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取街道
     *
     * @return java.lang.String
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * 设置街道
     *
     * @param street
     * @type java.lang.String
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 获取地址
     *
     * @return java.lang.String
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置地址
     *
     * @param address
     * @type java.lang.String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取居住地址
     *
     * @return java.lang.String
     */
    public String getResideAddress() {
        return this.resideAddress;
    }

    /**
     * 设置居住地址
     *
     * @param resideAddress
     * @type java.lang.String
     */
    public void setResideAddress(String resideAddress) {
        this.resideAddress = resideAddress;
    }

    /**
     * 获取邮编
     *
     * @return java.lang.String
     */
    public String getZipcode() {
        return this.zipcode;
    }

    /**
     * 设置邮编
     *
     * @param zipcode
     * @type java.lang.String
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * 获取手机号
     *
     * @return java.lang.String
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile
     * @type java.lang.String
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取电话
     *
     * @return java.lang.String
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * 设置电话
     *
     * @param telephone
     * @type java.lang.String
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取邮箱
     *
     * @return java.lang.String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置邮箱
     *
     * @param email
     * @type java.lang.String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取备注
     *
     * @return java.lang.String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置备注
     *
     * @param remark
     * @type java.lang.String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }


    /**
     * 获取删除标识
     *
     * @return java.lang.Integer
     */
    public boolean getIsDelete() {
        return this.isDelete;
    }

    /**
     * 设置删除标识
     *
     * @param isDelete
     * @type java.lang.Integer
     */
    public void setIsDelete(boolean isDelete) {
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

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getAbroadCode() {
        return abroadCode;
    }

    public void setAbroadCode(String abroadCode) {
        this.abroadCode = abroadCode;
    }

    public String getSpecialSkill() {
        return specialSkill;
    }

    public void setSpecialSkill(String specialSkill) {
        this.specialSkill = specialSkill;
    }

    public Boolean isOnlyChild() {
        return isOnlyChild;
    }

    public void setOnlyChild(Boolean isOnlyChild) {
        this.isOnlyChild = isOnlyChild;
    }

    public Boolean isFloatingPopulation() {
        return isFloatingPopulation;
    }

    public void setFloatingPopulation(Boolean isFloatingPopulation) {
        this.isFloatingPopulation = isFloatingPopulation;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getPhotoUuid() {
        return photoUuid;
    }

    public void setPhotoUuid(String photoUuid) {
        this.photoUuid = photoUuid;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

}