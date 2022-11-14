package platform.education.rest.bp.service.vo;

import platform.education.generalTeachingAffair.model.Team;

import java.io.Serializable;
import java.util.List;

public class BpBandingPageVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<Team> teamList;
	
	private Integer schoolId;
	
	private String schoolName;

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}
	
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
	
	

}
