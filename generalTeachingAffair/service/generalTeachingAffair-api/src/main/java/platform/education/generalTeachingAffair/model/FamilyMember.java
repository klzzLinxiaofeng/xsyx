package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * FamilyMember
 * @author AutoCreate
 *
 */
public class FamilyMember implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 学生ID,pj_student.id
	 */
	private Integer studentId;
	/**
	 * 家庭成员或监护人姓名
	 */
	private String name;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 学生与家长关系（家庭关系）XY-JY-XSJTGX
	 */
	private String relation;
	/**
	 * 关系说明
	 */
	private String relationRemarks;
	/**
	 * 民族
	 */
	private String race;
	/**
	 * 工作单位
	 */
	private String workingPlace;
	/**
	 * 职务
	 */
	private String position;
	/**
	 * 现住地址
	 */
	private String address;
	/**
	 * 户口所在地
	 */
	private String residenceAddress;
	/**
	 * 家长关系类别，0==普通  ，1==主监护人  XY-JY-JZLB
	 */
	private String rank;
	/**
	 * 身份证类别
	 */
	private String idCardType;
	/**
	 * 身份证
	 */
	private String idCardNumber;
	/**
	 * 标志是否删除
	 */
	private Boolean isDeleted;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	
	public FamilyMember() {
		
	}
	
	public FamilyMember(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键ID
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取学生ID,pj_student.id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生ID,pj_student.id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取家庭成员或监护人姓名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置家庭成员或监护人姓名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取手机号码
	 * @return java.lang.String
	 */
	public String getMobile() {
		return this.mobile;
	}
	
	/**
	 * 设置手机号码
	 * @param mobile
	 * @type java.lang.String
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 获取学生与家长关系（家庭关系）XY-JY-XSJTGX
	 * @return java.lang.String
	 */
	public String getRelation() {
		return this.relation;
	}
	
	/**
	 * 设置学生与家长关系（家庭关系）XY-JY-XSJTGX
	 * @param relation
	 * @type java.lang.String
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	/**
	 * 获取关系说明
	 * @return java.lang.String
	 */
	public String getRelationRemarks() {
		return this.relationRemarks;
	}
	
	/**
	 * 设置关系说明
	 * @param relationRemarks
	 * @type java.lang.String
	 */
	public void setRelationRemarks(String relationRemarks) {
		this.relationRemarks = relationRemarks;
	}
	
	/**
	 * 获取民族
	 * @return java.lang.String
	 */
	public String getRace() {
		return this.race;
	}
	
	/**
	 * 设置民族
	 * @param race
	 * @type java.lang.String
	 */
	public void setRace(String race) {
		this.race = race;
	}
	
	/**
	 * 获取工作单位
	 * @return java.lang.String
	 */
	public String getWorkingPlace() {
		return this.workingPlace;
	}
	
	/**
	 * 设置工作单位
	 * @param workingPlace
	 * @type java.lang.String
	 */
	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}
	
	/**
	 * 获取职务
	 * @return java.lang.String
	 */
	public String getPosition() {
		return this.position;
	}
	
	/**
	 * 设置职务
	 * @param position
	 * @type java.lang.String
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 获取现住地址
	 * @return java.lang.String
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * 设置现住地址
	 * @param address
	 * @type java.lang.String
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取户口所在地
	 * @return java.lang.String
	 */
	public String getResidenceAddress() {
		return this.residenceAddress;
	}
	
	/**
	 * 设置户口所在地
	 * @param residenceAddress
	 * @type java.lang.String
	 */
	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}
	
	/**
	 * 获取家长关系类别，0==普通  ，1==主监护人  XY-JY-JZLB
	 * @return java.lang.String
	 */
	public String getRank() {
		return this.rank;
	}
	
	/**
	 * 设置家长关系类别，0==普通  ，1==主监护人  XY-JY-JZLB
	 * @param rank
	 * @type java.lang.String
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	/**
	 * 获取身份证类别
	 * @return java.lang.String
	 */
	public String getIdCardType() {
		return this.idCardType;
	}
	
	/**
	 * 设置身份证类别
	 * @param idCardType
	 * @type java.lang.String
	 */
	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}
	
	/**
	 * 获取身份证
	 * @return java.lang.String
	 */
	public String getIdCardNumber() {
		return this.idCardNumber;
	}
	
	/**
	 * 设置身份证
	 * @param idCardNumber
	 * @type java.lang.String
	 */
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	
	/**
	 * 获取标志是否删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置标志是否删除
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	/**
	 * 获取创建日期
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建日期
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改日期
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改日期
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}