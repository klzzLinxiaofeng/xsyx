package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Subject;
/**
 * Subject
 * @author AutoCreate
 *
 */
public class SubjectVo extends Subject {
	private static final long serialVersionUID = 1L;
	
	public String stageNames;  //学段名称
	
	private Integer teacherNumber;//该科目下有多少个教师

	public Integer getTeacherNumber() {
		return teacherNumber;
	}

	public void setTeacherNumber(Integer teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	public String getStageNames() {
		return stageNames;
	}

	public void setStageNames(String stageNames) {
		this.stageNames = stageNames;
	}

}