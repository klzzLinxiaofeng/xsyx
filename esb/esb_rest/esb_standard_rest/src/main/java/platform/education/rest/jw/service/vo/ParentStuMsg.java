package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class ParentStuMsg extends ParentMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer studentId;
	
	private String termCode;
	
	private String termName;
	
	private String beginDate;
	
	private String finishDate;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
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
	
	
}
