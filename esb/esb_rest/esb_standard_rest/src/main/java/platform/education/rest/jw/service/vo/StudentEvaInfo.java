package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class StudentEvaInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer teamId;
	
	private Integer studentId;
	
	private String studentName;
	
	private String sex;
	// 用户头像的url地址
	private String userIcon;
	
	private Integer isEvaluate;
	//是否有  课堂优化
	//private Integer isClassOptimize;
	//是否有  激励评价
	//private Integer isIncreaseEvaluate;
	//是否有  发展评价
	//private Integer isProgressEvaluate;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getIsEvaluate() {
		return isEvaluate;
	}

	public void setIsEvaluate(Integer isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	
}
