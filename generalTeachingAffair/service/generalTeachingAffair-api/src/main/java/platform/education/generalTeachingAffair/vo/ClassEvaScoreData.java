package platform.education.generalTeachingAffair.vo;

import java.util.Date;

/**
 * 记录课堂优化的评价信息
 */
public class ClassEvaScoreData {
	/**
	 * 考核日期
	 */
	private Date checkDate;
	/**
	 * 学号
	 */
	private String number;
	/**
	 * 学生id
	 */
	private Integer studentId;
	/**
	 * 学生姓名
	 */
	private String name;
	/**
	 * 考核时间段--上课节数
	 */
	private String checkRange;
	/**
	 * 不良行为
	 */
	private String badBehaviours;
	/**
	 * 评价教师id
	 */
	private Integer teacherId;
	/**
	 * 科目老师（评价教师）
	 */
	private String courseTeacher;

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheckRange() {
		return checkRange;
	}

	public void setCheckRange(String checkRange) {
		this.checkRange = checkRange;
	}

	public String getBadBehaviours() {
		return badBehaviours;
	}

	public void setBadBehaviours(String badBehaviours) {
		this.badBehaviours = badBehaviours;
	}

	public String getCourseTeacher() {
		return courseTeacher;
	}

	public void setCourseTeacher(String courseTeacher) {
		this.courseTeacher = courseTeacher;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	
}	
