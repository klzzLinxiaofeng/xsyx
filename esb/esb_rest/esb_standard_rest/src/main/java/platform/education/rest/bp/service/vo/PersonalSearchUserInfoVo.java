package platform.education.rest.bp.service.vo;

import java.io.Serializable;

public class PersonalSearchUserInfoVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer userId;
	
	private Integer userType;
	
	private Integer schoolId;
	
	private String userName;
	
	private String userIcon;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
}
