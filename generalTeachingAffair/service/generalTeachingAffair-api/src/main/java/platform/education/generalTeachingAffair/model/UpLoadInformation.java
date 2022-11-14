package platform.education.generalTeachingAffair.model;

import java.util.Date;

import framework.generic.dao.Model;
/***
 * EXCEL导入学生信息表
 * @author zhoujin
 *
 */
public class UpLoadInformation implements Model<Integer>{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 学校
	 */
	private Integer schoolId;
	
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 身份证号
	 */
	private String idCardNumber;
	/**
	 * 学籍叼
	 */
	private String studentNumber;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 成员类型 1：学生 2：教师
	 */
	private String userType;
	/**
	 * 状态  1：成功 2：失败
	 */
	private String state;
	/**
	 * 成功，失败信息
	 */
	private String message;
	/**
	 * 创建人
	 */
	private String creater;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 角色
	 * @return
	 */
	private String role;
	
	/**
	 * 家长手机
	 * @return
	 */
	private String mobile;
	
	/**
	 * 是否住宿
	 * @return
	 */
	private String isBoarded;
	
	/**
	 * 部门名称
	 * @return
	 */
	private String deptName;
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIsBoarded() {
		return isBoarded;
	}

	public void setIsBoarded(String isBoarded) {
		this.isBoarded = isBoarded;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

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

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	
	@Override
	public Integer getKey() {
		// TODO Auto-generated method stub
		return id;
	}
}
