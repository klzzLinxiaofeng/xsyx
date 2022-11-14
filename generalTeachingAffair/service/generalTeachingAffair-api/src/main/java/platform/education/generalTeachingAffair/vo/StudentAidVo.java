package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.StudentAid;

/**
 * 学生资助前台页面显示Vo
 * @author AutoCreate
 *
 */
public class StudentAidVo extends StudentAid {
	private static final long serialVersionUID = 1L;

	private String schoolName;//学校名称
	private String schoolYear;//学年
	private String gradeName;//年级
	private String teamName;//班级
	private String studentName;//学生姓名
	private Integer schoolId;//学校id
	private Integer gradeId;//年级id
	private String schoolYearName;//学年


	public String getSchoolYearName() {
		return schoolYearName;
	}
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}
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
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


}