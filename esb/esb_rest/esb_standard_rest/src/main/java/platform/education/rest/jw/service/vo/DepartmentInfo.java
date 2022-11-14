package platform.education.rest.jw.service.vo;

import java.io.Serializable;
import java.util.List;

public class DepartmentInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//部门ID
	private Integer departmentId;
	
	//部门名称
	private String departmentName;
	
	//部门下的教师
	private List<DepartmentTeacherInfo> teachers;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<DepartmentTeacherInfo> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<DepartmentTeacherInfo> teachers) {
		this.teachers = teachers;
	}
	
}
