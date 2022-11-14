package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.Teacher;

/**
 * Teacher
 */
public class TeacherVo extends Teacher {

	private static final long serialVersionUID = 1L;

	private String manNum;

	private String womanNum;

	private String sumNum;

	private String areaName;

	private String departmentName;

	private String departmentId;

	private String idCardNumber; //身份证号码

	private String userState;  //用户状态

	private String ZXXBZLBName;//岗位名称

	private String ZXXBZLBNumber;//岗位人数

	private String GBXLName;//学历名称

	private String GBXLNumber;//学历数量

	private String ageGroup;//年龄段名称

	private String ageNumber;//年龄段数量

	private String member;//团员人数

	private String patry;//党员人数

	private String other;//非党员、团员人数

	private String schoolName;

	private String email;

	private String subjectInfo;

	private String subjectId;

	private String listOrder;
	
	private String types;
	/**
	 * 是否能够删除
	 */
	//private boolean delete;


	/**
	 * 学生名称
	 */
	private String stuName;
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}



	public String getTypes() {
		return types;
	}
	
	private String characteristic;//上课特色
	
	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getListOrder() {
		return listOrder;
	}

	public void setListOrder(String listOrder) {
		this.listOrder = listOrder;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectInfo() {
		return subjectInfo;
	}

	public void setSubjectInfo(String subjectInfo) {
		this.subjectInfo = subjectInfo;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getPatry() {
		return patry;
	}

	public void setPatry(String patry) {
		this.patry = patry;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getAgeNumber() {
		return ageNumber;
	}

	public void setAgeNumber(String ageNumber) {
		this.ageNumber = ageNumber;
	}

	public String getGBXLname() {
		return GBXLName;
	}

	public void setGBXLname(String gBXLname) {
		GBXLName = gBXLname;
	}

	public String getGBXLNumber() {
		return GBXLNumber;
	}

	public void setGBXLNumber(String gBXLNumber) {
		GBXLNumber = gBXLNumber;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getManNum() {
		return manNum;
	}

	public void setManNum(String manNum) {
		this.manNum = manNum;
	}

	public String getWomanNum() {
		return womanNum;
	}

	public void setWomanNum(String womanNum) {
		this.womanNum = womanNum;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSumNum() {
		return sumNum;
	}

	public void setSumNum(String sumNum) {
		this.sumNum = sumNum;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getZXXBZLBName() {
		return ZXXBZLBName;
	}

	public void setZXXBZLBName(String zXXBZLBName) {
		ZXXBZLBName = zXXBZLBName;
	}

	public String getZXXBZLBNumber() {
		return ZXXBZLBNumber;
	}

	public void setZXXBZLBNumber(String zXXBZLBNumber) {
		ZXXBZLBNumber = zXXBZLBNumber;
	}

	public String getGBXLName() {
		return GBXLName;
	}

	public void setGBXLName(String gBXLName) {
		GBXLName = gBXLName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "TeacherVo [departmentName=" + departmentName
				+ ", departmentId=" + departmentId + ", getKey()=" + getKey()
				+ ", getId()=" + getId() + ", getSchoolId()=" + getSchoolId()
				+ ", getPersionId()=" + getPersionId() + ", getUserId()="
				+ getUserId() + ", getUserName()=" + getUserName()
				+ ", getName()=" + getName() + ", getSex()=" + getSex()
				+ ", getJobNumber()=" + getJobNumber() + ", getEnrollDate()="
				+ getEnrollDate() + ", getLeaveDate()=" + getLeaveDate()
				+ ", getWorkBeginDate()=" + getWorkBeginDate()
				+ ", getQualification()=" + getQualification()
				+ ", getOccupationCode()=" + getOccupationCode()
				+ ", getPosition()=" + getPosition() + ", getMobile()="
				+ getMobile() + ", getTelephone()=" + getTelephone()
				+ ", getPostStaffing()=" + getPostStaffing()
				+ ", getJobState()=" + getJobState() + ", getIsDelete()="
				+ getIsDelete() + "]";
	}

//	public boolean isDelete() {
//		return delete;
//	}
//
//	public void setDelete(boolean delete) {
//		this.delete = delete;
//	}

}