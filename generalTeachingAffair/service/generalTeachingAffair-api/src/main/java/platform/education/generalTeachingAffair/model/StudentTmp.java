package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjStudentTmp
 * @author AutoCreate
 *
 */
public class StudentTmp implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * num
	 */
	private String num;
	/**
	 * name
	 */
	private String name;
	/**
	 * grade
	 */
	private String grade;
	/**
	 * team
	 */
	private String team;
	/**
	 * guardian
	 */
	private String guardian;
	/**
	 * guardianPhone
	 */
	private String guardianPhone;
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
	private Integer studentId;
	/**
	 * code
	 */
	private String code;
	
	private boolean delete;
	
	public StudentTmp() {
		
	}
	
	public StudentTmp(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

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
	 * 获取num
	 * @return java.lang.String
	 */
	public String getNum() {
		return this.num;
	}
	
	/**
	 * 设置num
	 * @param num
	 * @type java.lang.String
	 */
	public void setNum(String num) {
		this.num = num;
	}
	
	/**
	 * 获取name
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置name
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取grade
	 * @return java.lang.String
	 */
	public String getGrade() {
		return this.grade;
	}
	
	/**
	 * 设置grade
	 * @param grade
	 * @type java.lang.String
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	/**
	 * 获取team
	 * @return java.lang.String
	 */
	public String getTeam() {
		return this.team;
	}
	
	/**
	 * 设置team
	 * @param team
	 * @type java.lang.String
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	
	/**
	 * 获取guardian
	 * @return java.lang.String
	 */
	public String getGuardian() {
		return this.guardian;
	}
	
	/**
	 * 设置guardian
	 * @param guardian
	 * @type java.lang.String
	 */
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	
	/**
	 * 获取guardianPhone
	 * @return java.lang.String
	 */
	public String getGuardianPhone() {
		return this.guardianPhone;
	}
	
	/**
	 * 设置guardianPhone
	 * @param guardianPhone
	 * @type java.lang.String
	 */
	public void setGuardianPhone(String guardianPhone) {
		this.guardianPhone = guardianPhone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorFiled() {
		return errorFiled;
	}

	public void setErrorFiled(String errorFiled) {
		this.errorFiled = errorFiled;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	
}