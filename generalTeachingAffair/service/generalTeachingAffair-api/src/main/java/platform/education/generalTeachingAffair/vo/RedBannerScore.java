package platform.education.generalTeachingAffair.vo;

public class RedBannerScore {
	
	private Integer gradeId;
	
	private String  gradeName;
	
	private float score;
	
	private Integer ReachCount;
	
	private String getWay;
	
	public Integer getReachCount() {
		return ReachCount;
	}

	public void setReachCount(Integer reachCount) {
		ReachCount = reachCount;
	}

	public String getGetWay() {
		return getWay;
	}

	public void setGetWay(String getWay) {
		this.getWay = getWay;
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

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
}
