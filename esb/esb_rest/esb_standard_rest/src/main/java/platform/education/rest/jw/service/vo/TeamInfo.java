package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class TeamInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer teamId;
	
	private String teamName;
	
	private String name;

	private Integer teamNumber;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}
	
}
