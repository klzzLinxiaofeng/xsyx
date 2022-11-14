package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;
import java.util.List;
/**
 * 用户详细信息
 * @author admin
 *
 */
public class UserDetailInfo implements Model<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer personId;
	
	private Integer userId;
	
	private Integer studentId;

	private Integer schoolId;
	//姓名
	private String name;
	//英文名
	private String englishName;
	//用户名
	private String username;
	//性别
	private String sex;
	//学生类别
	private String studentType;
	//学籍号
	private String studentNum;
	//学籍辅助号
	private String studentNum2;
	//是否住宿
	private String isBoarded;
	//职务
	private String position;
	//入学时间
	private Date enterDate;
	//入学时间--给客户端用
	private String enterDateTemp;
	//离校时间
	private Date endDate;
	//离校时间--给客户端用
	private String endDateTemp;
	//在读状态
	private String state;
	//出生日期
	private Date birthDate;
	//出生日期--给客户端用
	private String birthDateTemp;

	//证件类型
	private String certificateType;
	//证件号码
	private String certificateNum;
	//国籍
	private String nationality;
	//民族
	private String nation;
	//籍贯
	private String nativePlace;
	//出生地
	private String birthPlace;
	//户口性质
	private String register;
	//户口所在地
	private String registerPlace;
	//现地址
	private String nowAddress;
	//居住地址
	private String liveAddress;
	//特长
	private String specialty;
	//政治面貌
	private String political;
	//宗教信仰
	private String religiousBelief;
	//是否独生子女
	private boolean isOnlyChild;
	//电话
	private String telephone;
	//手机
	private String cellPhone;
	//家长手机
	private String parentCellPhone;
	
	//邮件
	private String email;
	//备注
	private String remark;
	//婚烟状况
	private String marriage;
	//健康状况
	private String healthStatus;
	//港澳台侨外码
	private String abroadCode;
	//血型
	private String bloodType;
	//省
	private String province;
	//市
	private String city;
	//区
	private String district;
	//街
	private String street;
	//是否是流动人口
	private String isFloatingPopulation;
	
	//社区、村
	private String village;
	//工作单位
	private String workingPlace;
	
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getWorkingPlace() {
		return workingPlace;
	}
	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}


	/***
	 * 学生角色
	 */
	private String role;
	private Integer ordergradeId;

	public Integer getOrdergradeId() {
		return ordergradeId;
	}

	public void setOrdergradeId(Integer ordergradeId) {
		this.ordergradeId = ordergradeId;
	}

	//图片
	private String entityId;
	
	//返回错误信息
	private String message;
	
	//别名
	private String alias;
	
	//学生的班级的ID   2016-2-26客户端导入时用到
	private Integer studentTeamId;
	
	//班级学生在班中的学号（顺序编号）（pj_team_student.number）2016-02-29
	private Integer number;
	
	
	
	//2016-08-05
	//新增学生档案字段
	//头像
	private String photoUuid;
	//姓名拼音
	private String pinyinName;
	//曾用名
	private String usedName;
	//身份证有效期
	private Date idCardDate;
	//户口所在地
	private String residenceAddress;
	//户口类别
	private String residenceType;
	//特长
	//private String specialSkill;
	
	//学生类别
	//private String studentType;
	//入学方式
	private String enrollType;
	//就读方式
	private String attendSchoolType;
	//学生来源
	private String studentSource;
	//学籍号
	//private String studentNumber;
	//班内学号
	//private String number;
	//年级
	private String gradeId;
	//班级
	private String teamId;
	//入学时间
	//private Date enrollDate;
	//离校时间
	//private Date leaveDate;
	
	//现住地址（身份证地址）
	private String address;
	//家庭地址
	private String homeAddress;
	//通信地址（居住地址）
	private String resideAddress;
	//手机
	private String mobile;
	//电话
	//private String telephone;
	//邮政编码
	private String zipCode;
	//电子信箱
	//private String email;
	//主页地址
	private String homepage;
	
	//是否独生子女
	//private Boolean isOnlyChild;
	//是否受过学前教育
	private Boolean isPreeducated;
	//是否留守儿童
	private String isUnattendedChild;
	//是否进城务工人员随迁子女
	private Boolean isCityLabourChild;
	//是否孤儿
	private Boolean isOrphan;
	//是否烈士或优抚子女
	private Boolean isMartyrChild;
	//随班就读
	private String followStudy;
	//残疾类型
	private String disabilityType;
	//是否由政府购买学位
	private Boolean isBuyedDegree;
	//是否需要申请资助
	private Boolean isSponsored;
	//是否享受一补
	private Boolean isFirstRecommended;
	//所住房屋权属
	private String houseAuthority;
	//是否因身体原因需要学校特别照顾
	private Boolean needSpecialCare;
	
	//上下学距离（公里）
	private String schoolDistance;
	//上下学交通方式
	private String trafficType;
	//是否需要乘坐校车
	private Boolean bySchoolBus;


	// 2020-11-04 发送给视同需要使用
	// 班级名称
	private String newTeamName;
	// 班级code
	private String newCode;

	public String getNewTeamName() {
		return newTeamName;
	}

	public void setNewTeamName(String newTeamName) {
		this.newTeamName = newTeamName;
	}

	public String getNewCode() {
		return newCode;
	}

	public void setNewCode(String newCode) {
		this.newCode = newCode;
	}

	public String getPhotoUuid() {
		return photoUuid;
	}
	public void setPhotoUuid(String photoUuid) {
		this.photoUuid = photoUuid;
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

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public String getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}

	public String getEnrollType() {
		return enrollType;
	}

	public void setEnrollType(String enrollType) {
		this.enrollType = enrollType;
	}

	public String getAttendSchoolType() {
		return attendSchoolType;
	}

	public void setAttendSchoolType(String attendSchoolType) {
		this.attendSchoolType = attendSchoolType;
	}

	public String getStudentSource() {
		return studentSource;
	}

	public void setStudentSource(String studentSource) {
		this.studentSource = studentSource;
	}

	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getResideAddress() {
		return resideAddress;
	}

	public void setResideAddress(String resideAddress) {
		this.resideAddress = resideAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public Boolean getIsPreeducated() {
		return isPreeducated;
	}

	public void setIsPreeducated(Boolean isPreeducated) {
		this.isPreeducated = isPreeducated;
	}

	public String getIsUnattendedChild() {
		return isUnattendedChild;
	}

	public void setIsUnattendedChild(String isUnattendedChild) {
		this.isUnattendedChild = isUnattendedChild;
	}

	public Boolean getIsCityLabourChild() {
		return isCityLabourChild;
	}

	public void setIsCityLabourChild(Boolean isCityLabourChild) {
		this.isCityLabourChild = isCityLabourChild;
	}

	public Boolean getIsOrphan() {
		return isOrphan;
	}

	public void setIsOrphan(Boolean isOrphan) {
		this.isOrphan = isOrphan;
	}

	public Boolean getIsMartyrChild() {
		return isMartyrChild;
	}

	public void setIsMartyrChild(Boolean isMartyrChild) {
		this.isMartyrChild = isMartyrChild;
	}

	public String getFollowStudy() {
		return followStudy;
	}

	public void setFollowStudy(String followStudy) {
		this.followStudy = followStudy;
	}

	public String getDisabilityType() {
		return disabilityType;
	}

	public void setDisabilityType(String disabilityType) {
		this.disabilityType = disabilityType;
	}

	public Boolean getIsBuyedDegree() {
		return isBuyedDegree;
	}

	public void setIsBuyedDegree(Boolean isBuyedDegree) {
		this.isBuyedDegree = isBuyedDegree;
	}

	public Boolean getIsSponsored() {
		return isSponsored;
	}

	public void setIsSponsored(Boolean isSponsored) {
		this.isSponsored = isSponsored;
	}

	public Boolean getIsFirstRecommended() {
		return isFirstRecommended;
	}

	public void setIsFirstRecommended(Boolean isFirstRecommended) {
		this.isFirstRecommended = isFirstRecommended;
	}

	public String getHouseAuthority() {
		return houseAuthority;
	}

	public void setHouseAuthority(String houseAuthority) {
		this.houseAuthority = houseAuthority;
	}

	public Boolean getNeedSpecialCare() {
		return needSpecialCare;
	}

	public void setNeedSpecialCare(Boolean needSpecialCare) {
		this.needSpecialCare = needSpecialCare;
	}

	public String getSchoolDistance() {
		return schoolDistance;
	}

	public void setSchoolDistance(String schoolDistance) {
		this.schoolDistance = schoolDistance;
	}

	public String getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}

	public Boolean getBySchoolBus() {
		return bySchoolBus;
	}

	public void setBySchoolBus(Boolean bySchoolBus) {
		this.bySchoolBus = bySchoolBus;
	}
	
	//新增结束
	
	
	public Integer getStudentTeamId() {
		return studentTeamId;
	}

	public void setStudentTeamId(Integer studentTeamId) {
		this.studentTeamId = studentTeamId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private List<Parent> parentList;
	
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getAbroadCode() {
		return abroadCode;
	}

	public void setAbroadCode(String abroadCode) {
		this.abroadCode = abroadCode;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getIsFloatingPopulation() {
		return isFloatingPopulation;
	}

	public void setIsFloatingPopulation(String isFloatingPopulation) {
		this.isFloatingPopulation = isFloatingPopulation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
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

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getRegisterPlace() {
		return registerPlace;
	}

	public void setRegisterPlace(String registerPlace) {
		this.registerPlace = registerPlace;
	}

	public String getNowAddress() {
		return nowAddress;
	}

	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}

	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getReligiousBelief() {
		return religiousBelief;
	}

	public void setReligiousBelief(String religiousBelief) {
		this.religiousBelief = religiousBelief;
	}

	public boolean getIsOnlyChild() {
		return isOnlyChild;
	}

	public void setIsOnlyChild(boolean isOnlyChild) {
		this.isOnlyChild = isOnlyChild;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setOnlyChild(boolean isOnlyChild) {
		this.isOnlyChild = isOnlyChild;
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

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Parent> getParentList() {
		return parentList;
	}

	public void setParentList(List<Parent> parentList) {
		this.parentList = parentList;
	}
	
	public String getEnterDateTemp() {
		return enterDateTemp;
	}

	public void setEnterDateTemp(String enterDateTemp) {
		this.enterDateTemp = enterDateTemp;
	}

	public String getEndDateTemp() {
		return endDateTemp;
	}

	public void setEndDateTemp(String endDateTemp) {
		this.endDateTemp = endDateTemp;
	}

	public String getBirthDateTemp() {
		return birthDateTemp;
	}

	public void setBirthDateTemp(String birthDateTemp) {
		this.birthDateTemp = birthDateTemp;
	}
	public String getStudentNum2() {
		return studentNum2;
	}

	public void setStudentNum2(String studentNum2) {
		this.studentNum2 = studentNum2;
	}
	
	public String getIsBoarded() {
		return isBoarded;
	}

	public void setIsBoarded(String isBoarded) {
		this.isBoarded = isBoarded;
	}
	
	public String getParentCellPhone() {
		return parentCellPhone;
	}

	public void setParentCellPhone(String parentCellPhone) {
		this.parentCellPhone = parentCellPhone;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public Integer getKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
