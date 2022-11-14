package platform.szxyzxx.web.teach.client.vo;

public class ExtSyllabusLessonVo {
	//第几节
	private Integer lesson;
	
	//教师USerId
	private Integer teacherId;
	
	//教师姓名
	private String teacherName;
	
	//学科代码
	private String subjectCode;
	
	//学科名称
	private String subjectName;

	public Integer getLesson() {
		return lesson;
	}

	public void setLesson(Integer lesson) {
		this.lesson = lesson;
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
	
}
