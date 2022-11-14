package platform.education.rest.user.service.vo;

import java.io.Serializable;
import java.util.List;

public class MesosphereTeamInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer teamId;
	
	private String teamName;
	
	private List<MesosphereSubjectInfo> subjectList;

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

	public List<MesosphereSubjectInfo> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<MesosphereSubjectInfo> subjectList) {
		this.subjectList = subjectList;
	}

}
