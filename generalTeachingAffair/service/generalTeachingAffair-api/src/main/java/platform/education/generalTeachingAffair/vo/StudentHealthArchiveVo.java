package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.StudentHealthArchive;
/**
 * StudentHealthArchive
 * @author AutoCreate
 *
 */
public class StudentHealthArchiveVo extends StudentHealthArchive {
	private static final long serialVersionUID = 1L;

	/**
	 * pj_team.name 班级名称
	 */
	private String teamName;
	
	/**
	 * pj_team.schoolYear 学年
	 */
	private String schoolYear;
	
	/**
	 * pj_schoolYear.name 学年名称
	 */
	private String schoolYearName;
	
	/**
	 * pj_team_student.name 学生姓名
	 */
	private String studentName;
	
	/**
	 * pj_team_student.gradeId 年级ID
	 */
	private Integer gradeId;
	
	/**
	 * pj_grade.id 年级名称
	 */
	private String gradeName;
	
	/**
	 * 附件名称
	 */
	private String accessoryName;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getSchoolYearName() {
		return schoolYearName;
	}

	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	
}