package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.StudentPunish;
/**
 * StudentPunish
 * @author AutoCreate
 *
 */
public class StudentPunishVo extends StudentPunish {
	
	private static final long serialVersionUID = 1L;
	
	private String studentName;//学生姓名
	private String schoolYear;//学年
	private String teamName;//班级名称
	private Integer schoolId;//学生id
	private Integer gradeId;//学年id
	private String gradeName;//学年名称
	private String schoolYearName;//学年
	
	
	public String getSchoolYearName() {
		return schoolYearName;
	}
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
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
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	
}