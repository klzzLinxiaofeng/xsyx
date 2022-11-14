package platform.education.rest.bp.service.vo;

import java.io.Serializable;

public class BpSyllabusLessonDetailVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer syllabusLessonId;
	
	private Integer lesson;
	
	private String subjectCode;
	
	private String subjectName;
	
	private Integer teacherUserId;
	
	private String teacherName;
	
	private String teacherPostion;
	
	private String teacherHeadUrl;
	
	private String teacherFeature;
	
	private Integer type;
	
	public Integer getSyllabusLessonId() {
		return syllabusLessonId;
	}

	public void setSyllabusLessonId(Integer syllabusLessonId) {
		this.syllabusLessonId = syllabusLessonId;
	}

	public Integer getLesson() {
		return lesson;
	}

	public void setLesson(Integer lesson) {
		this.lesson = lesson;
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

	public Integer getTeacherUserId() {
		return teacherUserId;
	}

	public void setTeacherUserId(Integer teacherUserId) {
		this.teacherUserId = teacherUserId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherPostion() {
		return teacherPostion;
	}

	public void setTeacherPostion(String teacherPostion) {
		this.teacherPostion = teacherPostion;
	}

	public String getTeacherHeadUrl() {
		return teacherHeadUrl;
	}

	public void setTeacherHeadUrl(String teacherHeadUrl) {
		this.teacherHeadUrl = teacherHeadUrl;
	}

	public String getTeacherFeature() {
		return teacherFeature;
	}

	public void setTeacherFeature(String teacherFeature) {
		this.teacherFeature = teacherFeature;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
