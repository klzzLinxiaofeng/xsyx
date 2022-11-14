package platform.szxyzxx.web.bbx.client.vo;

public class CommonTeacherTeam {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private Integer teamNumber;
	
	private String type;
	
	private String uniGradeCode;
	
	private Integer gradeId;
	
	private String gradeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
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

	public String getUniGradeCode() {
		return uniGradeCode;
	}

	public void setUniGradeCode(String uniGradeCode) {
		this.uniGradeCode = uniGradeCode;
	}

	@Override
	public String toString() {
		return "CommonTeacherTeam [id=" + id + ", name=" + name + ", teamNumber=" + teamNumber + ", type=" + type
				+ ", uniGradeCode=" + uniGradeCode + ", gradeId=" + gradeId + ", gradeName=" + gradeName + "]";
	}


}
