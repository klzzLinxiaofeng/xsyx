package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Parent;
/**
 * Parent
 * @author AutoCreate
 *
 */
public class ParentCondition extends Parent {
	private static final long serialVersionUID = 1L;
	private Integer schoolId;
	private Integer gradeId;
	private Integer teamId;
	private String parentMobile;
	private String schoolYear;
	private boolean parentMobileLike = false;//是否使用模糊查询，默认为否
	private Integer studentId;
	private Integer studentUserId;
	private String studentName;
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getStudentUserId() {
		return studentUserId;
	}
	public void setStudentUserId(Integer studentUserId) {
		this.studentUserId = studentUserId;
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
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	public String getParentMobile() {
		return parentMobile;
	}
	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public boolean getParentMobileLike() {
		return parentMobileLike;
	}
	public void setParentMobileLike(boolean parentMobileLike) {
		this.parentMobileLike = parentMobileLike;
	}
	
}