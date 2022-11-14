package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

public class TeacherTmp implements Model<Integer>{
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * name
	 */
	private String name;
	/**
	 * alias
	 */
	private String alias;
	/**
	 * phone
	 */
	private String phone;
	/**
	 * department
	 */
	private String department;
	/**
	 * position
	 */
	private String position;
	/**
	 * job
	 */
	private String job;
	/**
	 * status
	 */
	private Integer status;
	/**
	 * errorFiled
	 */
	private String errorFiled;
	/**
	 * errorInfo
	 */
	private String errorInfo;
	/**
	 * teacherId
	 */
	private Integer teacherId;
	/**
	 * code
	 */
	private String code;
	
	private String sex;
	
	private boolean delete;

	public TeacherTmp() {
		
	}

	public TeacherTmp(Integer id) {
		this.id = id;
	}

	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取id
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置id
	 * 
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取name
	 * 
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取alias
	 * 
	 * @return java.lang.String
	 */
	public String getAlias() {
		return this.alias;
	}

	/**
	 * 设置alias
	 * 
	 * @param alias
	 * @type java.lang.String
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * 获取phone
	 * 
	 * @return java.lang.String
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 设置phone
	 * 
	 * @param phone
	 * @type java.lang.String
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取department
	 * 
	 * @return java.lang.String
	 */
	public String getDepartment() {
		return this.department;
	}

	/**
	 * 设置department
	 * 
	 * @param department
	 * @type java.lang.String
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 获取position
	 * 
	 * @return java.lang.String
	 */
	public String getPosition() {
		return this.position;
	}

	/**
	 * 设置position
	 * 
	 * @param position
	 * @type java.lang.String
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 获取job
	 * 
	 * @return java.lang.String
	 */
	public String getJob() {
		return this.job;
	}

	/**
	 * 设置job
	 * 
	 * @param job
	 * @type java.lang.String
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * 获取status
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置status
	 * 
	 * @param status
	 * @type java.lang.Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取errorFiled
	 * 
	 * @return java.lang.String
	 */
	public String getErrorFiled() {
		return this.errorFiled;
	}

	/**
	 * 设置errorFiled
	 * 
	 * @param errorFiled
	 * @type java.lang.String
	 */
	public void setErrorFiled(String errorFiled) {
		this.errorFiled = errorFiled;
	}

	/**
	 * 获取errorInfo
	 * 
	 * @return java.lang.String
	 */
	public String getErrorInfo() {
		return this.errorInfo;
	}

	/**
	 * 设置errorInfo
	 * 
	 * @param errorInfo
	 * @type java.lang.String
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * 获取teacherId
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}

	/**
	 * 设置teacherId
	 * 
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	/**
	 * 获取code
	 * 
	 * @return java.lang.String
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 设置code
	 * 
	 * @param code
	 * @type java.lang.String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}
}
