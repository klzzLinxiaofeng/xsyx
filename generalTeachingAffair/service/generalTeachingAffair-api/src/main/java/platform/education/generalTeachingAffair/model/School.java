package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * 学校
 * School
 * @author zhoujin
 *
 */
public class School implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * UUID
	 */
	private String uuid;
	/**
	 * 学校名称
	 */
	private String name;
	/**
	 * 学校英文名称
	 */
	private String englishName;
	/**
	 * 学校代码
	 */
	private String code;
	
	/***
	 * 系统内部给学校的统一代码（10位，前六位是学校所在行政区划代码，后四位是随机数字）
	 */
	private String code2;
	
	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	/**
	 * 学校教学范围(如小学+初中)
	 */
	private String stageScope;
	/**
	 * 学制 国际学制为代码方式，只有3-6学年，分开学段。本学段使用年代替国际代码，以学校为单位，允许9年制
	 */
	private String schoolSystem;
	

	/**
	 * 学校类别
	 */
	private String schoolType;
	/**
	 * 办学类别
	 */
	private String runningType;
	/**
	 * 建校年月
	 */
	private String buildDate;
	/**
	 * 校庆日
	 */
	private java.util.Date decorationDay;
	/**
	 * 所在省
	 */
	private String province;
	/**
	 * 所在市
	 */
	private String city;
	/**
	 * 所在区
	 */
	private String district;
	/**
	 * 行政区划代码
	 */
	private String regionCode;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮编
	 */
	private String zipcode;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 学校主页
	 */
	private String websit;
	/**
	 * 介绍
	 */
	private String remark;
	/**
	 * 校徽url
	 */
	private String entityId_badge;
	/**
	 * 学校图片url
	 */
	private String entityId_image;
	
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 所在街道
	 */
	private String street;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 招生范围
	 */
	private String enrollScope;
	/**
	 * 所在城乡类型
	 */
	private String cityType;
	/**
	 * 所在地经济属性
	 */
	private String econonyType;
	/**
	 * 组织机构码
	 */
	private String institutionCode;
	/**
	 * 学校主管部门
	 */
	private String authority;
	/**
	 * 法定代表人号
	 */
	private String corporation;
	/**
	 * 法人证号
	 */
	private String certificate;
	/**
	 * 校长姓名
	 */
	private String schoolMaster;
	/**
	 * 党委负责人
	 */
	private String partyCommittee;
	/**
	 * 主教学语言
	 */
	private String mainLanguage;
	/**
	 * 归属平台
	 */
	private Integer attributePlatform;
	
	public Integer getAttributePlatform() {
		return attributePlatform;
	}

	public void setAttributePlatform(Integer attributePlatform) {
		this.attributePlatform = attributePlatform;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	private boolean isDelete;

	/**
	 * 获取id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取学校名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置学校名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取学校英文名称
	 * @return java.lang.String
	 */
	public String getEnglishName() {
		return this.englishName;
	}
	
	/**
	 * 设置学校英文名称
	 * @param englishName
	 * @type java.lang.String
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	/**
	 * 获取学校代码
	 * @return java.lang.String
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * 设置学校代码
	 * @param code
	 * @type java.lang.String
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 获取学校教学范围(如小学+初中)
	 * @return java.lang.String
	 */
	public String getStageScope() {
		return this.stageScope;
	}
	
	/**
	 * 设置学校教学范围(如小学+初中)
	 * @param stageScope
	 * @type java.lang.String
	 */
	public void setStageScope(String stageScope) {
		this.stageScope = stageScope;
	}
	
	/**
	 * 获取学制 国际学制为代码方式，只有3-6学年，分开学段。本学段使用年代替国际代码，以学校为单位，允许9年制
	 * @return java.lang.String
	 */
	
	
	public String getSchoolSystem() {
		return schoolSystem;
	}
	
	/**
	 * 设置学制 国际学制为代码方式，只有3-6学年，分开学段。本学段使用年代替国际代码，以学校为单位，允许9年制
	 * @param 
	 * @type java.lang.String
	 */
	public void setSchoolSystem(String schoolSystem) {
		this.schoolSystem = schoolSystem;
	}
	
	/**
	 * 获取学校类别
	 * @return java.lang.String
	 */
	public String getSchoolType() {
		return this.schoolType;
	}
	
	/**
	 * 设置学校类别
	 * @param schoolType
	 * @type java.lang.String
	 */
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	
	/**
	 * 获取办学类别
	 * @return java.lang.String
	 */
	public String getRunningType() {
		return this.runningType;
	}
	
	/**
	 * 设置办学类别
	 * @param runningType
	 * @type java.lang.String
	 */
	public void setRunningType(String runningType) {
		this.runningType = runningType;
	}
	
	/**
	 * 获取建校年月
	 * @return java.lang.String
	 */
	public String getBuildDate() {
		return this.buildDate;
	}
	
	/**
	 * 设置建校年月
	 * @param buildDate
	 * @type java.lang.String
	 */
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	
	/**
	 * 获取校庆日
	 * @return java.util.Date
	 */
	public java.util.Date getDecorationDay() {
		return this.decorationDay;
	}
	
	/**
	 * 设置校庆日
	 * @param decorationDay
	 * @type java.util.Date
	 */
	public void setDecorationDay(java.util.Date decorationDay) {
		this.decorationDay = decorationDay;
	}
	
	/**
	 * 获取所在省
	 * @return java.lang.String
	 */
	public String getProvince() {
		return this.province;
	}
	
	/**
	 * 设置所在省
	 * @param province
	 * @type java.lang.String
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 获取所在市
	 * @return java.lang.String
	 */
	public String getCity() {
		return this.city;
	}
	
	/**
	 * 设置所在市
	 * @param city
	 * @type java.lang.String
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * 获取所在区
	 * @return java.lang.String
	 */
	public String getDistrict() {
		return this.district;
	}
	
	/**
	 * 设置所在区
	 * @param district
	 * @type java.lang.String
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	
	/**
	 * 获取行政区划代码
	 * @return java.lang.String
	 */
	public String getRegionCode() {
		return this.regionCode;
	}
	
	/**
	 * 设置行政区划代码
	 * @param regionCode
	 * @type java.lang.String
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	/**
	 * 获取地址
	 * @return java.lang.String
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * 设置地址
	 * @param address
	 * @type java.lang.String
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取邮编
	 * @return java.lang.String
	 */
	public String getZipcode() {
		return this.zipcode;
	}
	
	/**
	 * 设置邮编
	 * @param zipcode
	 * @type java.lang.String
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	/**
	 * 获取电话
	 * @return java.lang.String
	 */
	public String getTelephone() {
		return this.telephone;
	}
	
	/**
	 * 设置电话
	 * @param telephone
	 * @type java.lang.String
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * 获取学校主页
	 * @return java.lang.String
	 */
	public String getWebsit() {
		return this.websit;
	}
	
	/**
	 * 设置学校主页
	 * @param websit
	 * @type java.lang.String
	 */
	public void setWebsit(String websit) {
		this.websit = websit;
	}
	
	/**
	 * 获取介绍
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置介绍
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getEntityId_badge() {
		return entityId_badge;
	}

	public void setEntityId_badge(String entityId_badge) {
		this.entityId_badge = entityId_badge;
	}

	public String getEntityId_image() {
		return entityId_image;
	}

	public void setEntityId_image(String entityId_image) {
		this.entityId_image = entityId_image;
	}

	
	/**
	 * 获取创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEnrollScope() {
		return enrollScope;
	}

	public void setEnrollScope(String enrollScope) {
		this.enrollScope = enrollScope;
	}

	public String getCityType() {
		return cityType;
	}

	public void setCityType(String cityType) {
		this.cityType = cityType;
	}

	public String getEcononyType() {
		return econonyType;
	}

	public void setEcononyType(String econonyType) {
		this.econonyType = econonyType;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getSchoolMaster() {
		return schoolMaster;
	}

	public void setSchoolMaster(String schoolMaster) {
		this.schoolMaster = schoolMaster;
	}

	public String getPartyCommittee() {
		return partyCommittee;
	}

	public void setPartyCommittee(String partyCommittee) {
		this.partyCommittee = partyCommittee;
	}

	public String getMainLanguage() {
		return mainLanguage;
	}

	public void setMainLanguage(String mainLanguage) {
		this.mainLanguage = mainLanguage;
	}
	
	public School() {
		
	}
	
	public School(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}