package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.MoralEvaluationStudent;
/**
 * MoralEvaluationStudent
 * @author AutoCreate
 *
 */
public class MoralEvaluationStudentCondition extends MoralEvaluationStudent {
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
	 * pj_team_student.name 学生姓名
	 */
	private String studentName;
	
	/**
	 * pj_team_student.studentId 学生ID
	 */
	private Integer studentId;
	
	/**
	 * pj_team_student.gradeId 学生ID
	 */
	private Integer gradeId;
	/**
	 * pj_team_student.teamId 学生ID
	 */
	private Integer teamId;
	
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
	 * 获取学年
	 * @return
	 */
	public String getSchoolYear() {
		return schoolYear;
	}

	/**
	 * 设置学年
	 * @param schoolYear
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	/**
	 * 获取学生姓名
	 * @return
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * 设置学生姓名
	 * @param studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * 获取学生ID
	 * @return
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * 设置学生ID
	 * @param student
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * 获取年级ID
	 * @return
	 */
	public Integer getGradeId() {
		return gradeId;
	}

	/**
	 * 设置年级ID
	 * @param gradeId
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * 获取班级ID
	 * @return
	 */
	public Integer getTeamId() {
		return teamId;
	}

	/**
	 * 设置班级ID
	 * @param teamId
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
}