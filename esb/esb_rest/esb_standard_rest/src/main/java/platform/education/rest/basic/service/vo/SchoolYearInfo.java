package platform.education.rest.basic.service.vo;

import java.io.Serializable;

public class SchoolYearInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//学年
	private String schoolYear;
	//学年名称
	private String name;
	//是否当前学年
	private String isCurrent;
	
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsCurrent() {
		return isCurrent;
	}
	public void setIsCurrent(String isCurrent) {
		this.isCurrent = isCurrent;
	}
	
}
