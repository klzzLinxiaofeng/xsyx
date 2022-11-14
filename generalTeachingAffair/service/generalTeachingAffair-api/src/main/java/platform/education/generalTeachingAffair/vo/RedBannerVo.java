package platform.education.generalTeachingAffair.vo;

public class RedBannerVo {
	private Integer teamId;
	private String teamName;
	private String teamTeacherName;
	private float reduceScore;
	private float addScore;
	private float totalScore;
	private String remark;
	private Integer rank;
	private Integer isRed;
	private String date;
	private Integer isEvaluate;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamTeacherName() {
		return teamTeacherName;
	}

	public void setTeamTeacherName(String teamTeacherName) {
		this.teamTeacherName = teamTeacherName;
	}

	public float getReduceScore() {
		return reduceScore;
	}

	public void setReduceScore(float reduceScore) {
		this.reduceScore = reduceScore;
	}

	public float getAddScore() {
		return addScore;
	}

	public void setAddScore(float addScore) {
		this.addScore = addScore;
	}

	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getIsRed() {
		return isRed;
	}

	public void setIsRed(Integer isRed) {
		this.isRed = isRed;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getIsEvaluate() {
		return isEvaluate;
	}

	public void setIsEvaluate(Integer isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}


}
