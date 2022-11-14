package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.StudentScore;
/**
 * StudentScore
 * @author AutoCreate
 *
 */
public class StudentScoreVo extends StudentScore {
	private static final long serialVersionUID = 1L;
	private String studentNumber;//学籍号
	//private String subjectCode;//科目代码
	private String studentName;//名称
	private String errorInfo;//错误信息

	private String comment;
	
	
	
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}