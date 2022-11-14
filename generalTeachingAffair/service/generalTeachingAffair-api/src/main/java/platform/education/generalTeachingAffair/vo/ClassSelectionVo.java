package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.ClassSelection;
/**
 * ClassSelection
 * @author AutoCreate
 *
 */
public class ClassSelectionVo extends ClassSelection {
	private static final long serialVersionUID = 1L;
	
	//学生名称
	private String studentName;
	
	//学生性别
	private String sex;
	
	//学生的学籍号
	private String studentNumber;
	
	//学生所在的班级名称
	private String teamName;

	/*   用于导出相应的课程信息      */
	
	//课程名称
	private String publicClassName;
	
	//课程的任课教师ID
	private Integer teacherId;
	
	//课程的任课教师名称
	private String teacherName;
	
	//课程开始上课日期
	private java.util.Date beginDate;
	
	//课程的总节数
	private Integer classNumber;
	
	//课程的人数上限
	private Integer maxMember;
	
	//课程的截止报名日期
	private java.util.Date expiryDate;

	// 上课日期
	private String classTime;

	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}

	/*   用于导出相应的课程信息        */
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPublicClassName() {
		return publicClassName;
	}

	public void setPublicClassName(String publicClassName) {
		this.publicClassName = publicClassName;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public java.util.Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}

	public Integer getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(Integer classNumber) {
		this.classNumber = classNumber;
	}

	public Integer getMaxMember() {
		return maxMember;
	}

	public void setMaxMember(Integer maxMember) {
		this.maxMember = maxMember;
	}

	public java.util.Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}