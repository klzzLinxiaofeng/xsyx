package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.TeamAdmin;
/**
 * TeamAdmin
 * @author AutoCreate
 *
 */
public class TeamAdminVo extends TeamAdmin {
	private static final long serialVersionUID = 1L;

	private String teamName;

	private String gradeName;

	private Integer teamNumber;

	private String teamCode;

	private String gradeCode;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
}