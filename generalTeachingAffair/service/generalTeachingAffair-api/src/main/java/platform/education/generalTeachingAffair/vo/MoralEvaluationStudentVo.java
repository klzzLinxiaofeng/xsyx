package platform.education.generalTeachingAffair.vo;
import java.util.List;

import platform.education.generalTeachingAffair.model.MoralEvaluationStudent;
/**
 * MoralEvaluationStudent
 * @author AutoCreate
 *
 */
public class MoralEvaluationStudentVo extends MoralEvaluationStudent {
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
	 * pj_team_student.studentId 学生ID
	 */
//	private Integer studentId;
	
	/**
	 * pj_team_student.gradeId 年级ID
	 */
	private Integer gradeId;
	
	/**
	 * pj_grade.id 年级名称
	 */
	private String gradeName;
	
	/**
	 * pj_team_student.teamId 班级ID
	 */
//	private Integer teamId;
	
	private List<MoralEvaluationItemVo> itemVoList;
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
	
	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getSchoolYearName() {
		return schoolYearName;
	}

	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}

	public List<MoralEvaluationItemVo> getItemVoList() {
		return itemVoList;
	}

	public void setItemVoList(List<MoralEvaluationItemVo> itemVoList) {
		this.itemVoList = itemVoList;
	}
	
	
}