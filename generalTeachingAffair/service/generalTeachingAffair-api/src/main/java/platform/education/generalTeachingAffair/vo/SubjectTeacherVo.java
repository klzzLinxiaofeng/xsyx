package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.SubjectTeacher;

/**
 * SubjectTeacher
 * 
 * @author AutoCreate
 *
 */
public class SubjectTeacherVo extends SubjectTeacher {

	/**
	 * 关联字段
	 */
	private String gradeName; // pj_grade

	private String subjectName; // pj_subject

	private String teacherName; // pj_teacher

	private static final long serialVersionUID = 1L;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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