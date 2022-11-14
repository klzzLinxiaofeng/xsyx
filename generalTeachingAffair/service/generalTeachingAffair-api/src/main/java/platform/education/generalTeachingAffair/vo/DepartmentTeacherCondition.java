package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.DepartmentTeacher;

public class DepartmentTeacherCondition extends DepartmentTeacher{
	private static final long serialVersionUID = 1L;
	
	private Integer teacherUserId;

	public Integer getTeacherUserId() {
		return teacherUserId;
	}

	public void setTeacherUserId(Integer teacherUserId) {
		this.teacherUserId = teacherUserId;
	}
	
}
