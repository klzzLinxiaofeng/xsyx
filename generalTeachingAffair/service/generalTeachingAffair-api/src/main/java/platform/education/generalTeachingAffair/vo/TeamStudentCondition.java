package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.TeamStudent;
/**
 * TeamStudent
 * @author AutoCreate
 *
 */
public class TeamStudentCondition extends TeamStudent {
	private static final long serialVersionUID = 1L;
	
	/**
	 * pj_school_year.name 学年的开始年份
	 */
	private String schoolYearName;
	
	/**
	 * pj_grade.name 年级名称
	 */
	private String gradeName;
	
	/**
	 * pj_team.schoolYear 学年的开始年份
	 */
	private String schoolYear;
	
	/**
	 * pj_team.name 班级名称
	 */
	private String teamName;

	/**
	 * pj_team_student.id 班级学生表的主键ID
	 */
	private Integer teamStudentId;
	
	/**
	 * pj_student_health_archive.type;
	 */
	private String healthType;
	
	/**
	 * pj_student.schoolId
	 */
	private Integer schoolId;
	
	private boolean pattern;
	/**
	 * 获取学年名称
	 * @return
	 */
	public String getSchoolYearName() {
		return schoolYearName;
	}

	/**
	 * 设置学年名称
	 * @param schoolYearName
	 */
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}

	/**
	 * 获取年级名称
	 * @return
	 */
	public String getGradeName() {
		return gradeName;
	}

	/**
	 * 设置年级名称
	 * @param gradeName
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * 获取学年的开始年份
	 * @return
	 */
	public String getSchoolYear() {
		return schoolYear;
	}

	/**
	 * 设置学年的开始年份
	 * @param schoolYear
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	/**
	 * 获取班级名称
	 * @return
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * 设置班级名称
	 * @param teamName
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * 获取班级学生表的主键ID
	 * @return
	 */
	public Integer getTeamStudentId() {
		return teamStudentId;
	}

	/**
	 * 设置班级学生表的主键ID
	 * @param teamStudentId
	 */
	public void setTeamStudentId(Integer teamStudentId) {
		this.teamStudentId = teamStudentId;
	}

	/**
	 * 获取健康类型
	 * @return
	 */
	public String getHealthType() {
		return healthType;
	}

	/**
	 * 设置健康类型
	 * @param healthType
	 */
	public void setHealthType(String healthType) {
		this.healthType = healthType;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public boolean isPattern() {
		return pattern;
	}

	public void setPattern(boolean pattern) {
		this.pattern = pattern;
	}
	
}