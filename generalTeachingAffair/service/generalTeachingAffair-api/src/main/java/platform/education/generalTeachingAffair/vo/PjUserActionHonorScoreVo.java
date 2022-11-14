package platform.education.generalTeachingAffair.vo;

import java.io.Serializable;
import java.util.Date;

public class PjUserActionHonorScoreVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String studentName;
	
	private String subjectName;
	
	private String subjectCode;
	
	private Integer score;
	
	private Date createDate;
	
	public PjUserActionHonorScoreVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
}
