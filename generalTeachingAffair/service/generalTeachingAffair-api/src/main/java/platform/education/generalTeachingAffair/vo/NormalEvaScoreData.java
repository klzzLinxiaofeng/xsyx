package platform.education.generalTeachingAffair.vo;

import java.util.Date;

public class NormalEvaScoreData {
	private Integer number;
	
	private Integer studentId;
	
	private String studentName;

	private Float count;
	
	private Date checkDate;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

}
