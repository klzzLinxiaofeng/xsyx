package platform.education.rest.basic.service.vo;

import java.io.Serializable;
import java.util.List;

public class SchoolYearVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//学年
	private String schoolYear;
	//学年名称
	private String schoolYearName;
	//
	private String yearName;
	//学期列表
	private List<SchoolTermInfo> schoolTermlist;
	
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public String getSchoolYearName() {
		return schoolYearName;
	}
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}
	public List<SchoolTermInfo> getSchoolTermlist() {
		return schoolTermlist;
	}
	public void setSchoolTermlist(List<SchoolTermInfo> schoolTermlist) {
		this.schoolTermlist = schoolTermlist;
	}
	public String getYearName() {
		return yearName;
	}
	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
}
