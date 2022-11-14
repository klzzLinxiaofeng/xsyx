package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.ExamTeamSubject;
/**
 * ExamTeamSubject
 * @author AutoCreate
 *
 */
public class ExamTeamSubjectCondition extends ExamTeamSubject {
	private static final long serialVersionUID = 1L;
	
	public static Integer TASKTYPE_TEST = 1;//作业
	public static Integer TASKTYPE_EXAM = 0;//考试
	
	public static Integer TASKONLINE_YES = 1;//在线任务，作业或者考试
	public static Integer TASKONLINE_NO = 0;//不是在线任务，作业或者考试
	private String schoolYearName;//学年
	private Integer gradeId;//年级id
	private String teamIdCollection;//班级id集合
	

	public String getSchoolYearName() {
		return schoolYearName;
	}
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getTeamIdCollection() {
		return teamIdCollection;
	}
	public void setTeamIdCollection(String teamIdCollection) {
		this.teamIdCollection = teamIdCollection;
	}
	
	
}