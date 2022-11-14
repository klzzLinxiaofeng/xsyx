package platform.education.generalTeachingAffair.vo;

public class TeamStructure {
	private static final long serialVersionUID = 1L;
	private Integer teamId;
	private Integer gradeId;
	private Integer schoolYearId;
	
    private String teamName;
    private String gradeName;
    private String schoolYearName;
    private Integer teamNum;
    private String   gradeNum;
	public Integer getTeamNum() {
		return teamNum;
	}
	public void setTeamNum(Integer teamNum) {
		this.teamNum = teamNum;
	}
	public String getGradeNum() {
		return gradeNum;
	}
	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public Integer getSchoolYearId() {
		return schoolYearId;
	}
	public void setSchoolYearId(Integer schoolYearId) {
		this.schoolYearId = schoolYearId;
	}
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
	public String getSchoolYearName() {
		return schoolYearName;
	}
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}
}
