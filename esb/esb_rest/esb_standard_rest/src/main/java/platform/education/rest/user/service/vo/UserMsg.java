package platform.education.rest.user.service.vo;

import java.io.Serializable;

public class UserMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer schoolId;
	private String schoolName;
	private String role;
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
