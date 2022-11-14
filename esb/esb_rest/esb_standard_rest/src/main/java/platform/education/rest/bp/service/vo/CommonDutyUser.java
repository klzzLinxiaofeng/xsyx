package platform.education.rest.bp.service.vo;

import java.util.Date;
import java.util.List;

public class CommonDutyUser {
	private Date dutyDate;
	
	private String modifyDate;
	
	private List<DutyUser> students;

	public CommonDutyUser(){}
	
	public CommonDutyUser(Date dutyDate, List<DutyUser> students) {
		this.dutyDate = dutyDate;
		this.students = students;
	}
	
	public CommonDutyUser(Date dutyDate,String modifyDate, List<DutyUser> students) {
		this.modifyDate = modifyDate;
		this.dutyDate = dutyDate;
		this.students = students;
	}

	public Date getDutyDate() {
		return dutyDate;
	}

	public void setDutyDate(Date dutyDate) {
		this.dutyDate = dutyDate;
	}
	
	public String getModifyDate() {
		return modifyDate;
	}
	
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<DutyUser> getStudents() {
		return students;
	}

	public void setStudents(List<DutyUser> students) {
		this.students = students;
	}
	
}
