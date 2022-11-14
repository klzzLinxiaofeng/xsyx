package platform.education.rest.jw.service.vo;

import java.io.Serializable;
import java.util.List;

public class GradeInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer gradeId;
	
	private String gradeName;
	
	private List<TeamInfo> teamList;
	
	private String gradeCode;

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public List<TeamInfo> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<TeamInfo> teamList) {
		this.teamList = teamList;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	
}
