package platform.szxyzxx.web.bbx.client.vo;

public class CommonSyllabus {
	private Integer syllabusId;
	/**
	 * 节次，第几节
	 */
	private Integer lesson;
	/**
	 * 星期几 XY-JY-XQ 0=星期日 1=星期一
	 */
	private String dayOfWeek;
	/**
	 * pj_teacher.id
	 */
	private Integer teacherId;
	/**
	 * pj_subject.code
	 */
	private String subjectCode;
	
	private String subjectName;

	private String teacherName;

	public Integer getSyllabusId() {
		return syllabusId;
	}

	public void setSyllabusId(Integer syllabusId) {
		this.syllabusId = syllabusId;
	}

	public Integer getLesson() {
		return lesson;
	}

	public void setLesson(Integer lesson) {
		this.lesson = lesson;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}
