package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.UserDetailInfo;

public class UserDetailInfoVo extends UserDetailInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
//	private String gradeId;
//	
//	private String teamId;
	
	private Integer roleId;
	
	private String nickName;
	
	/**
	 * 是否住宿
	 */
	private String isBoarded;

	public String getIsBoarded() {
		return isBoarded;
	}

	public void setIsBoarded(String isBoarded) {
		this.isBoarded = isBoarded;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

//	public String getGradeId() {
//		return gradeId;
//	}
//
//	public void setGradeId(String gradeId) {
//		this.gradeId = gradeId;
//	}
//
//	public String getTeamId() {
//		return teamId;
//	}
//
//	public void setTeamId(String teamId) {
//		this.teamId = teamId;
//	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
