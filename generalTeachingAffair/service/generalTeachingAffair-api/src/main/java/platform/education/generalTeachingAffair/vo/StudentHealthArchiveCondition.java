package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.StudentHealthArchive;
/**
 * StudentHealthArchive
 * @author AutoCreate
 *
 */
public class StudentHealthArchiveCondition extends StudentHealthArchive {
	private static final long serialVersionUID = 1L;
	
	//搜索条件
	private String schoolYear;
	
	private Integer gradeId;

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
}