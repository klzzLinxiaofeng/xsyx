package platform.education.generalTeachingAffair.vo;

public class EvaluationMedalData {
	
	private Integer gradeId;
	
	private String gradeName;

	private Float score;
	
	private String teamStar;
	
	private Integer teamCount;
	
	private String gradeStar;
	
	private Integer gradeCount;
	/**
	 * 按排名的人数
	 */
	private Integer count; 

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getTeamStar() {
		return teamStar;
	}

	public void setTeamStar(String teamStar) {
		this.teamStar = teamStar;
	}

	public Integer getTeamCount() {
		return teamCount;
	}

	public void setTeamCount(Integer teamCount) {
		this.teamCount = teamCount;
	}

	public String getGradeStar() {
		return gradeStar;
	}

	public void setGradeStar(String gradeStar) {
		this.gradeStar = gradeStar;
	}

	public Integer getGradeCount() {
		return gradeCount;
	}

	public void setGradeCount(Integer gradeCount) {
		this.gradeCount = gradeCount;
	}

}
