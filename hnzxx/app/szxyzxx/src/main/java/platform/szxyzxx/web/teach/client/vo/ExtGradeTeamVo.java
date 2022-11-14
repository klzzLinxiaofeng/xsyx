package platform.szxyzxx.web.teach.client.vo;

import java.util.List;

/**
 * @function 年级下的班级信息GradeTeamVo
 * @author Administrator
 *
 */
public class ExtGradeTeamVo {
	//班级ID
	private Integer teamId;
	
	//班级名称
	private String teamName;
	
	//班级下的所有教师
	private List<ExtTeamTeacherVo> teachers;

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

	public List<ExtTeamTeacherVo> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<ExtTeamTeacherVo> teachers) {
		this.teachers = teachers;
	}
	
}
