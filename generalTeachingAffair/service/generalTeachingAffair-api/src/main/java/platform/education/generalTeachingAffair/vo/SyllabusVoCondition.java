package platform.education.generalTeachingAffair.vo;

/**
 * Syllabus
 */
public class SyllabusVoCondition {

	/**
	 * pj_syllabus.id
	 */
	private Integer syllabusId;

	/**
	 * pj_school_year.year
	 */
	private String schoolYear;

	/**
	 * pj_school_term.code
	 */
	private String termCode;

	/**
	 * 相关学校pj_school.id
	 */
	private Integer schoolId;

	/**
	 * pj_team.id
	 */
	private Integer teamId;

	/**
	 * pj_syllabusLesson.id
	 */
	private Integer lessonId;

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

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getSyllabusId() {
		return syllabusId;
	}

	public void setSyllabusId(Integer syllabusId) {
		this.syllabusId = syllabusId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

}