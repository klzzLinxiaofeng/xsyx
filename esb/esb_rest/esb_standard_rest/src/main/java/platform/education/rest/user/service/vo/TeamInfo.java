package platform.education.rest.user.service.vo;

import java.util.List;

import platform.education.generalTeachingAffair.model.Subject;

public class TeamInfo {
	
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	private Integer teamId;
	
	private String teamName;
	
	private List<SubjectInfo> subjectList;

	public List<SubjectInfo> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectInfo> subjectList) {
		this.subjectList = subjectList;
	}

}
