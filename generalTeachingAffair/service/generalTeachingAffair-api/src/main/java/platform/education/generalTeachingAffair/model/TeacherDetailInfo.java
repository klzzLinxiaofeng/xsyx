package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

public class TeacherDetailInfo implements Model<Integer>{

	private static final long serialVersionUID = 1L;
	/*
	*教育云卡号
	*/
	private  String empCard;
	/*
	 *食堂卡号
	 */
	private String shiTangCard;
	/**
	 * 所在学校
	 */
	private Integer schoolId;
	/**
	 * 对应的人
	 */
	private Integer persionId;
	/**
	 * 教师ID
	 */
	private Integer teacherId;
	
	/**
	 * 图片ID
	 */
	private String entityId;

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
	 * 英文名
	 */
	private String englishName;
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
	private java.util.Date enrollDate;
	/**
	 * 离职时间
	 */
	private java.util.Date leaveDate;
	/**
	 * 参加工作时间
	 */
	private java.util.Date workBeginDate;
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
	 * 证件类型
	 */
	private String certificateType;
	/**
	 * 证件号码
	 */
	private String certificateNum;
	/**
	 * 国籍
	 */
	private String nationality;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 籍贯
	 */
	private String nativePlace;
	/**
	 * 出生地
	 */
	private String birthPlace;
	/**
	 * 婚姻状况
	 */
	private String marriage;
	
	/**
	 * 政治面貌
	 */
	private String political;
	/**
	 * 特长
	 */
	private String specialty;
	/***
	 * 宗教信仰
	 */
	private String religiousBelief;
	/**
	 * 户口性质
	 */
	private String register;
	/**
	 * 户口所在地
	 */
	private String registerPlace;
	/**
	 * 现地址
	 */
	private String nowAddress;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 居住地址
	 */
	private String liveAddress;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 删除标志
	 */
	private String isDelete;
	/**
	 *  出生日期
	 */
	private java.util.Date birthDate;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	/***
	 * 学生角色
	 */
	private String role;
	
	
	/**
	 * 教师所属部门
	 */
	private String departmentName;
	
	//返回错误信息
	private String message;
	
	/**
	 * 别名
	 * 添加于2015-11-18
	 */
	private String alias;
	/**
	 * 部门ID，多个部门以逗号分隔
	 * 添加于2015-11-18
	 */
	private String departmentIds;
	
	/**
	 * 是否外聘
	 * 添加于2015-11-19
	 */
	private Boolean isExternal;
	
	/**
	 * 档案头像id
	 * 添加于2016-08-08
	 */
	private String photoUuid;
	
	/**
	 * 用户类型
	 */
	private String userType;
	
	/**
	 * 档案头像地址
	 */
	private String photoUrl;
	
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPhotoUuid() {
		return photoUuid;
	}

	public void setPhotoUuid(String photoUuid) {
		this.photoUuid = photoUuid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
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


	public String getEnglishName() {
		return englishName;
	}


	public void setEnglishName(String englishName) {
		this.englishName = englishName;
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


	public java.util.Date getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(java.util.Date enrollDate) {
		this.enrollDate = enrollDate;
	}


	public java.util.Date getLeaveDate() {
		return leaveDate;
	}


	public void setLeaveDate(java.util.Date leaveDate) {
		this.leaveDate = leaveDate;
	}


	public java.util.Date getWorkBeginDate() {
		return workBeginDate;
	}


	public void setWorkBeginDate(java.util.Date workBeginDate) {
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


	public String getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}


	public java.util.Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}


	public java.util.Date getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public java.util.Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(java.util.Date birthDate) {
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


	public String getMarriage() {
		return marriage;
	}


	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getPolitical() {
		return political;
	}


	public void setPolitical(String political) {
		this.political = political;
	}


	public String getSpecialty() {
		return specialty;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}


	public String getReligiousBelief() {
		return religiousBelief;
	}


	public void setReligiousBelief(String religiousBelief) {
		this.religiousBelief = religiousBelief;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLiveAddress() {
		return liveAddress;
	}


	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	public String getEntityId() {
		return entityId;
	}


	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public Boolean getIsExternal() {
		return isExternal;
	}

	public void setIsExternal(Boolean isExternal) {
		this.isExternal = isExternal;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getEmpCard() {
		return empCard;
	}

	public void setEmpCard(String empCard) {
		this.empCard = empCard;
	}

	public String getShiTangCard() {
		return shiTangCard;
	}

	public void setShiTangCard(String shiTangCard) {
		this.shiTangCard = shiTangCard;
	}

	public Boolean getExternal() {
		return isExternal;
	}

	public void setExternal(Boolean external) {
		isExternal = external;
	}

	@Override
	public Integer getKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
