package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.StudentPunish;
/**
 * StudentPunish
 * @author AutoCreate
 *
 */
public class StudentPunishCondition extends StudentPunish {
	private static final long serialVersionUID = 1L;
	
	private Integer schoolId;//学校
	private Integer gradeId;//年级
	private String schoolYear;//学年
	

	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
}