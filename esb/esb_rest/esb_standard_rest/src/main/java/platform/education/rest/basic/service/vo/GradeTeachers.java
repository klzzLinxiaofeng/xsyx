package platform.education.rest.basic.service.vo;

import java.util.List;

public class GradeTeachers {
	private Integer id;
	private String name;
	private List<GradeTeachersVo> teachers;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GradeTeachersVo> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<GradeTeachersVo> teachers) {
		this.teachers = teachers;
	}
}
