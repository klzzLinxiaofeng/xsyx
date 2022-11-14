package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Team;
/**
 * Team
 * @author AutoCreate
 *
 */
public class TeamVo extends Team {
	private static final long serialVersionUID = 1L;
	
	private Long hasStudentPhoto;
	
	private Long noStudentPhoto;

	/**
	 * 年级名称
	 * 2017-11-21
	 */
	private String gradeName;
	/**
	 * 通用年级码 pj_grade.uni_grade_code
	 * 2017-11-21
	 */
	private String uniGradeCode;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getUniGradeCode() {
		return uniGradeCode;
	}

	public void setUniGradeCode(String uniGradeCode) {
		this.uniGradeCode = uniGradeCode;
	}

	public Long getHasStudentPhoto() {
		return hasStudentPhoto;
	}

	public void setHasStudentPhoto(Long hasStudentPhoto) {
		this.hasStudentPhoto = hasStudentPhoto;
	}

	public Long getNoStudentPhoto() {
		return noStudentPhoto;
	}

	public void setNoStudentPhoto(Long noStudentPhoto) {
		this.noStudentPhoto = noStudentPhoto;
	}
	
}