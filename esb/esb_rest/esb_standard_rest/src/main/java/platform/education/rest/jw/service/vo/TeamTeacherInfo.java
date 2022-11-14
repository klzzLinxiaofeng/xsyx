package platform.education.rest.jw.service.vo;

import java.io.Serializable;
import java.util.List;

public class TeamTeacherInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//班级ID
	private Integer teamId;
	
	//班级名称
	private String teamName;
	
	private List<TeacherVo> teachers;

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

	public List<TeacherVo> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherVo> teachers) {
		this.teachers = teachers;
	}

}
