package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * StudentEmployment
 * @author AutoCreate
 *
 */
public class StudentEmployment implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 学生ID，根据学生id可以找到学生信息。关联表pj_student.id
	 */
	private Integer studentId;
	/**
	 * 学生姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 全国统一学籍号
	 */
	private String studentNumber;
	/**
	 * 联系方式
	 */
	private String mobile;
	/**
	 * 单位名称
	 */
	private String company;
	/**
	 * 就业地点
	 */
	private String employAddress;
	/**
	 * 就业时间
	 */
	private String employDate;
	/**
	 * 入学时间
	 */
	private String enrollDate;
	/**
	 * 删除标记
	 */
	private Boolean isDeleted;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	public StudentEmployment() {
		
	}
	
	public StudentEmployment(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取学生ID，根据学生id可以找到学生信息。关联表pj_student.id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生ID，根据学生id可以找到学生信息。关联表pj_student.id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取学生姓名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置学生姓名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取性别
	 * @return java.lang.String
	 */
	public String getSex() {
		return this.sex;
	}
	
	/**
	 * 设置性别
	 * @param sex
	 * @type java.lang.String
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * 获取全国统一学籍号
	 * @return java.lang.String
	 */
	public String getStudentNumber() {
		return this.studentNumber;
	}
	
	/**
	 * 设置全国统一学籍号
	 * @param studentNumber
	 * @type java.lang.String
	 */
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	/**
	 * 获取联系方式
	 * @return java.lang.String
	 */
	public String getMobile() {
		return this.mobile;
	}
	
	/**
	 * 设置联系方式
	 * @param mobile
	 * @type java.lang.String
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 获取单位名称
	 * @return java.lang.String
	 */
	public String getCompany() {
		return this.company;
	}
	
	/**
	 * 设置单位名称
	 * @param company
	 * @type java.lang.String
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * 获取就业地点
	 * @return java.lang.String
	 */
	public String getEmployAddress() {
		return this.employAddress;
	}
	
	/**
	 * 设置就业地点
	 * @param employAddress
	 * @type java.lang.String
	 */
	public void setEmployAddress(String employAddress) {
		this.employAddress = employAddress;
	}
	
	/**
	 * 获取就业时间
	 * @return java.util.Date
	 */
	public String getEmployDate() {
		return this.employDate;
	}
	
	/**
	 * 设置就业时间
	 * @param employDate
	 * @type java.util.Date
	 */
	public void setEmployDate(String employDate) {
		this.employDate = employDate;
	}
	
	/**
	 * 获取入学时间
	 * @return java.util.Date
	 */
	public String getEnrollDate() {
		return this.enrollDate;
	}
	
	/**
	 * 设置入学时间
	 * @param enrollDate
	 * @type java.util.Date
	 */
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	/**
	 * 获取删除标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标记
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	/**
	 * 获取createDate
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置createDate
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取modifyDate
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置modifyDate
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}