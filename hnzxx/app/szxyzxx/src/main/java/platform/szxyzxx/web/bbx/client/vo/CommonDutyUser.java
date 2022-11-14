package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;

public class CommonDutyUser {
	private String dutyDate;
	
	private String modifyDate;
	
	private List<DutyUser> students;

	public CommonDutyUser(){}
	
	public CommonDutyUser(String dutyDate, List<DutyUser> students) {
		this.dutyDate = dutyDate;
		this.students = students;
	}
	
	public CommonDutyUser(String dutyDate,String modifyDate, List<DutyUser> students) {
		this.modifyDate = modifyDate;
		this.dutyDate = dutyDate;
		this.students = students;
	}

	public String getDutyDate() {
		return dutyDate;
	}

	public void setDutyDate(String dutyDate) {
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
