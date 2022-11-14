package platform.education.rest.resource.service.vo;

import java.io.Serializable;

public class ExtStudent implements Serializable{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 5242177195277507513L;
	
	private Integer id;
	private Integer schoolId;
	private Integer personId;
	private Integer userId;
	private Integer teamId;
	private String userName;
	private String teamName;
	private String name;
	private String sex;
	private String studentNumber;
	private String studentNumber2;
	private String isBoarded;
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
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudentNumber2() {
		return studentNumber2;
	}
	public void setStudentNumber2(String studentNumber2) {
		this.studentNumber2 = studentNumber2;
	}
	public String getIsBoarded() {
		return isBoarded;
	}
	public void setIsBoarded(String isBoarded) {
		this.isBoarded = isBoarded;
	}
	
}
