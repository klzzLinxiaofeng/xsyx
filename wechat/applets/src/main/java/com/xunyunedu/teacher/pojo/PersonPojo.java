package com.xunyunedu.teacher.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 个人档案
 *
 * @author: yhc
 * @Date: 2020/12/8 9:13
 * @Description:
 */
public class PersonPojo implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 英文姓名
     */
    private String englishName;

    /**
     * 姓名拼音
     */
    private String pinyinName;

    /**
     * 曾用名
     */
    private String usedName;

    /**
     * 性别 0未知 1男 2女 9未说明
     */
    private String sex;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 身份证件类别
     */
    private String idCardType;

    /**
     * 身份证
     */
    private String idCardNumber;

    /**
     * 身份证有效日期
     */
    private Date idCardDate;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 出生地（行政区划）
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
     * 宗教信仰
     */
    private String religion;

    /**
     * 港澳台侨外码
     */
    private String abroadCode;

    /**
     * 健康状况
     */
    private String healthStatus;

    /**
     * 血型
     */
    private String bloodType;

    /**
     * 行政区划代码
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
     * 街道
     */
    private String street;

    /**
     * 社区/村
     */
    private String village;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 户籍备注
     */
    private String residenceRemark;

    /**
     * 现住地址
     */
    private String address;

    /**
     * 居住地址
     */
    private String resideAddress;

    /**
     * 家庭地址
     */
    private String homeAddress;

    /**
     * 所住房屋权属
     */
    private String houseAuthority;

    /**
     * 户口性质
     */
    private String residenceType;

    /**
     * 户口所在地
     */
    private String residenceAddress;

    /**
     * 工作单位
     */
    private String workingPlace;

    /**
     * 职务
     */
    private String position;

    /**
     * 手机
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
     * 简历
     */
    private String resume;

    /**
     * 个人主页
     */
    private String homepage;

    /**
     * 特长
     */
    private String specialSkill;

    /**
     * 是否独生子女
     */
    private Boolean isOnlyChild;

    /**
     * 是否流动人口
     */
    private Boolean isFloatingPopulation;

    /**
     * 备注
     */
    private String remark;

    /**
     *
     */
    private String photoUuid;

    /**
     * res_entity.id
     */
    private String entityId;

    /**
     * 标志是否删除
     */
    private Boolean isDelete;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Date getIdCardDate() {
        return idCardDate;
    }

    public void setIdCardDate(Date idCardDate) {
        this.idCardDate = idCardDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getAbroadCode() {
        return abroadCode;
    }

    public void setAbroadCode(String abroadCode) {
        this.abroadCode = abroadCode;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getResidenceRemark() {
        return residenceRemark;
    }

    public void setResidenceRemark(String residenceRemark) {
        this.residenceRemark = residenceRemark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResideAddress() {
        return resideAddress;
    }

    public void setResideAddress(String resideAddress) {
        this.resideAddress = resideAddress;
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

    public String getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getSpecialSkill() {
        return specialSkill;
    }

    public void setSpecialSkill(String specialSkill) {
        this.specialSkill = specialSkill;
    }

    public Boolean getIsOnlyChild() {
        return isOnlyChild;
    }

    public void setIsOnlyChild(Boolean isOnlyChild) {
        this.isOnlyChild = isOnlyChild;
    }

    public Boolean getIsFloatingPopulation() {
        return isFloatingPopulation;
    }

    public void setIsFloatingPopulation(Boolean isFloatingPopulation) {
        this.isFloatingPopulation = isFloatingPopulation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhotoUuid() {
        return photoUuid;
    }

    public void setPhotoUuid(String photoUuid) {
        this.photoUuid = photoUuid;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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
}

