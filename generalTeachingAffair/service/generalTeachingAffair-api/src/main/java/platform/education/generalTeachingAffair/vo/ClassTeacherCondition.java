package platform.education.generalTeachingAffair.vo;

/***
 * 班主任
 */
public class ClassTeacherCondition extends ClassTeacherVo{
	private static final long serialVersionUID = 1L;
	
	private Integer schoolId;
	
	private String schoolYear;

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
}
