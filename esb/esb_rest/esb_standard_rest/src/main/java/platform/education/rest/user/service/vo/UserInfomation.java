package platform.education.rest.user.service.vo;

import java.io.Serializable;

public class UserInfomation implements Serializable {

	private static final long serialVersionUID = -6590727392859705924L;

	private String username; // 用户名
	private Integer userId;
	private String name; // 姓名
	private String sex; // 性别
	private String role; // 角色
	private String gradeCode; // 年级编号
	private String mobile; // 手机号码
	private String studentUsername; // 学生用户的登录账号
	private String iconUrl; //用户头像
	private Integer teamId;
	
	private String teamName;	
	
	private String gradeName;
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getStudentUsername() {
		return studentUsername;
	}
	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
