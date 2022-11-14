package platform.education.generalTeachingAffair.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StudentArchiveCompleteVo extends StudentArchiveComplete{

	private static final long serialVersionUID = 1L;
	
	private Integer studentId;
	
	private Boolean isComplet;
	
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsComplet() {
		return isComplet;
	}

	public void setIsComplet(Boolean isComplet) {
		this.isComplet = isComplet;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	private String parentData;
	
	public String getParentData() {
		return parentData;
	}

	public void setParentData(String parentData) {
		this.parentData = parentData;
	}

	private String name;
	
	private String sex;
	
	private Date birthday;
	
	private String birthPlace;
	
	private String nativePlace;
	
	private String birthPlaceCode;
	
	private String nativePlaceCode;
	
	private String race;
	
	private String nationality;
	
	private String idCardType;
	
	private String idCardNumber;
	
	private String abroadCode;
	
	private String politicalStatus;
	
	private String healthStatus;
	
	private String bloodType;
	
	private String photoUuid;
	
	private String residenceAddress;
	
	private String residenceAddressCode;
	
	private String residenceType;
	
	private String specialSkill;
	
	private String pinyinName;
	
	private String usedName;

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date idCardDate;
	
	private String studentType;
	
	private String enrollType;
	
	private String attendSchoolType;
	
	private String studentSource;
	
	private String studentNumber;
	
	private String number;
	
	private Integer gradeId;
	
	private Integer teamId;
	
	private Date enrollDate;
	
	private Date leaveDate;
	
	private String address;
	
	private String homeAddress;
	
	private String resideAddress;
	
	private String mobile;
	
	private String telephone;
	
	private String zipCode;
	
	private String email;
	
	private String homepage;
	
	private Boolean isOnlyChild;
	
	private Boolean isPreeducated;
	
	private String isUnattendedChild;
	
	private Boolean isCityLabourChild;
	
	private Boolean isOrphan;
	
	private Boolean isMartyrChild;
	
	private String followStudy;
	
	private String disabilityType;
	
	private Boolean isBuyedDegree;
	
	private Boolean isSponsored;
	
	private Boolean isFirstRecommended;
	
	private String houseAuthority;
	
	private Boolean needSpecialCare;
	
	private String schoolDistance;
	
	private String trafficType;
	
	private Boolean bySchoolBus;
	
	private Integer roleId;
	private Integer ordelGradeId;
	/**
	 *性格
	 */
	private String xingge;
	/**
	 * 爱好
	 */
	private String aihao;
	/**
	 * 最喜欢的书
	 */
	private String likeBook;
	/**
	 * 最敬佩的人
	 */
	private String jingpei;
	/**
	 * 座右铭
	 */
	private String zuoyouming;
	/**
	 * 成长感言
	 */
	private String ganyan;
	/**
	 * 成长感言
	 */
	private String ganyan1;
	/**
	 * 图片uuid
	 */
	private String pictureUuid;
	/**
	 * 图片url
	 */
	private String pictureUrl;

	public String getGanyan1() {
		return ganyan1;
	}

	public void setGanyan1(String ganyan1) {
		this.ganyan1 = ganyan1;
	}

	public String getPictureUuid() {
		return pictureUuid;
	}

	public void setPictureUuid(String pictureUuid) {
		this.pictureUuid = pictureUuid;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getXingge() {
		return xingge;
	}

	public void setXingge(String xingge) {
		this.xingge = xingge;
	}

	public String getAihao() {
		return aihao;
	}

	public void setAihao(String aihao) {
		this.aihao = aihao;
	}

	public String getLikeBook() {
		return likeBook;
	}

	public void setLikeBook(String likeBook) {
		this.likeBook = likeBook;
	}

	public String getJingpei() {
		return jingpei;
	}

	public void setJingpei(String jingpei) {
		this.jingpei = jingpei;
	}

	public String getZuoyouming() {
		return zuoyouming;
	}

	public void setZuoyouming(String zuoyouming) {
		this.zuoyouming = zuoyouming;
	}

	public String getGanyan() {
		return ganyan;
	}

	public void setGanyan(String ganyan) {
		this.ganyan = ganyan;
	}

	public Integer getOrdelGradeId() {
		return ordelGradeId;
	}

	public void setOrdelGradeId(Integer ordelGradeId) {
		this.ordelGradeId = ordelGradeId;
	}

	private String studyState;

	public String getStudyState() {
		return studyState;
	}

	public void setStudyState(String studyState) {
		this.studyState = studyState;
	}

	public String getBirthPlaceCode() {
		return birthPlaceCode;
	}

	public void setBirthPlaceCode(String birthPlaceCode) {
		this.birthPlaceCode = birthPlaceCode;
	}

	public String getNativePlaceCode() {
		return nativePlaceCode;
	}

	public void setNativePlaceCode(String nativePlaceCode) {
		this.nativePlaceCode = nativePlaceCode;
	}

	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	//==============
	//多个家长(不处理)
	

	private String remark;
	
	private String resume;

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

	public String getAbroadCode() {
		return abroadCode;
	}

	public void setAbroadCode(String abroadCode) {
		this.abroadCode = abroadCode;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
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

	public String getPhotoUuid() {
		return photoUuid;
	}

	public void setPhotoUuid(String photoUuid) {
		this.photoUuid = photoUuid;
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

	public String getSpecialSkill() {
		return specialSkill;
	}

	public void setSpecialSkill(String specialSkill) {
		this.specialSkill = specialSkill;
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

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
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

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public Boolean getIsOnlyChild() {
		return isOnlyChild;
	}

	public void setIsOnlyChild(Boolean isOnlyChild) {
		this.isOnlyChild = isOnlyChild;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getResidenceAddressCode() {
		return residenceAddressCode;
	}

	public void setResidenceAddressCode(String residenceAddressCode) {
		this.residenceAddressCode = residenceAddressCode;
	}
	
}
