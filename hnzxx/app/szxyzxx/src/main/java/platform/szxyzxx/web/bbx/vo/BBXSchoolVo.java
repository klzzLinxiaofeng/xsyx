package platform.szxyzxx.web.bbx.vo;

import platform.education.generalTeachingAffair.model.School;

public class BBXSchoolVo {
	private School school;
	
	
	/**
	 * 班级数量
	 */
	private Long teamCount;
	
	/**
	 * 学校类型
	 */
	private String schoolType;
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Long getTeamCount() {
		return teamCount;
	}
	
	public void setTeamCount(Long teamCount) {
		this.teamCount = teamCount;
	}
	
	
	public String getSchoolType() {
		return schoolType;
	}
	
	
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	
}
