package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.PjExam;
/**
 * PjExam
 * @author AutoCreate
 *
 */
public class PjExamVo extends PjExam {
	private static final long serialVersionUID = 1L;
	private String subjectName;
	private Integer studentCount;
	
	public Integer getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}