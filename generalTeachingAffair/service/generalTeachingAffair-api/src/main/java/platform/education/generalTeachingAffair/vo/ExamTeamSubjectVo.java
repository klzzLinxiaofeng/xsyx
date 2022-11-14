package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.ExamTeamSubject;
/**
 * ExamTeamSubject
 * @author AutoCreate
 *
 */
public class ExamTeamSubjectVo extends ExamTeamSubject {
	
	private static final long serialVersionUID = 1L;
	private String teamName;//考试班级
	private String subjectName;//科目名称
/*	private Integer gradeId;//年级id*/
	private String gradeName;//考试年级
	private String teamIdCollection;//班级id集合
	private Integer TeamSum;//班级人数
	private String taskOnlineName;//是否在线
	private String schoolYearName;//学年
	private String termCodeName;//学期
	public String getTermCodeName() {
		return termCodeName;
	}
	public void setTermCodeName(String termCodeName) {
		this.termCodeName = termCodeName;
	}
	public String getSchoolYearName() {
		return schoolYearName;
	}
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}
	public String getTaskOnlineName() {
		return taskOnlineName;
	}
	public void setTaskOnlineName(String taskOnlineName) {
		this.taskOnlineName = taskOnlineName;
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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/*public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}*/
	public String getTeamIdCollection() {
		return teamIdCollection;
	}
	public void setTeamIdCollection(String teamIdCollection) {
		this.teamIdCollection = teamIdCollection;
	}


	public Integer getTeamSum() {
		return TeamSum;
	}
	public void setTeamSum(Integer teamSum) {
		TeamSum = teamSum;
	}
	

}