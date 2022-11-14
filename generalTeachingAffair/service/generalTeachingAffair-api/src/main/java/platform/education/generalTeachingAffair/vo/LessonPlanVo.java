package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.LessonPlan;
/**
 * LessonPlan
 * @author AutoCreate
 *
 */
public class LessonPlanVo extends LessonPlan {
	private static final long serialVersionUID = 1L;
	//教师名称
	private String teacherName;
	//科目名称
	private String subjectName;
	//年级名称
	private String gradeName;
	//学年
	private String yearName;
	//学期
	private String termName;
	//统计数量
	private Integer countNumber;
	
	public Integer getCountNumber() {
		return countNumber;
	}
	public void setCountNumber(Integer countNumber) {
		this.countNumber = countNumber;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getYearName() {
		return yearName;
	}
	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	
}