package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * TestAdd
 * @author AutoCreate
 *
 */
public class TestAdd implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * teamId
	 */
	private Integer teamId;
	/**
	 * studentName
	 */
	private String studentName;
	/**
	 * fatherName
	 */
	private String fatherName;
	/**
	 * fatherMobile
	 */
	private String fatherMobile;
	/**
	 * motherName
	 */
	private String motherName;
	/**
	 * motherMobile
	 */
	private String motherMobile;
	
	public TestAdd() {
		
	}
	
	public TestAdd(Integer id) {
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
	 * 获取teamId
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置teamId
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取studentName
	 * @return java.lang.String
	 */
	public String getStudentName() {
		return this.studentName;
	}
	
	/**
	 * 设置studentName
	 * @param studentName
	 * @type java.lang.String
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 获取fatherName
	 * @return java.lang.String
	 */
	public String getFatherName() {
		return this.fatherName;
	}
	
	/**
	 * 设置fatherName
	 * @param fatherName
	 * @type java.lang.String
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	/**
	 * 获取fatherMobile
	 * @return java.lang.String
	 */
	public String getFatherMobile() {
		return this.fatherMobile;
	}
	
	/**
	 * 设置fatherMobile
	 * @param fatherMobile
	 * @type java.lang.String
	 */
	public void setFatherMobile(String fatherMobile) {
		this.fatherMobile = fatherMobile;
	}
	
	/**
	 * 获取motherName
	 * @return java.lang.String
	 */
	public String getMotherName() {
		return this.motherName;
	}
	
	/**
	 * 设置motherName
	 * @param motherName
	 * @type java.lang.String
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	/**
	 * 获取motherMobile
	 * @return java.lang.String
	 */
	public String getMotherMobile() {
		return this.motherMobile;
	}
	
	/**
	 * 设置motherMobile
	 * @param motherMobile
	 * @type java.lang.String
	 */
	public void setMotherMobile(String motherMobile) {
		this.motherMobile = motherMobile;
	}
	
}