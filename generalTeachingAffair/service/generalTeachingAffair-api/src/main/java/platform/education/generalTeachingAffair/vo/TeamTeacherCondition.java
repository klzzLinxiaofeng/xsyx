package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.TeamTeacher;
/**
 * TeamTeacher
 * @author AutoCreate
 *
 */
public class TeamTeacherCondition extends TeamTeacher {
	private static final long serialVersionUID = 1L;
	private Integer schoolId;
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	private boolean nameIsLike=false;

	public boolean isNameIsLike() {
		return nameIsLike;
	}

	public void setNameIsLike(boolean nameIsLike) {
		this.nameIsLike = nameIsLike;
	}
}