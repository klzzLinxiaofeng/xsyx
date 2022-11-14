package platform.education.rest.basic.service.vo;

import java.io.Serializable;
import java.util.Date;

public class SchoolTermInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//学期代码
	private String schoolTermCode;
	//学期名称
	private String schoolTermName;
	
	private String termName;
	
	private String fullName;
	//是否当前学期
	private String isCurrent;
	//学期开始时间
	private String beginDate;
	//学期结束时间
	private String finishDate;
	
	public String getSchoolTermCode() {
		return schoolTermCode;
	}
	public void setSchoolTermCode(String schoolTermCode) {
		this.schoolTermCode = schoolTermCode;
	}
	public String getSchoolTermName() {
		return schoolTermName;
	}
	public void setSchoolTermName(String schoolTermName) {
		this.schoolTermName = schoolTermName;
	}
	public String getIsCurrent() {
		return isCurrent;
	}
	public void setIsCurrent(String isCurrent) {
		this.isCurrent = isCurrent;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
