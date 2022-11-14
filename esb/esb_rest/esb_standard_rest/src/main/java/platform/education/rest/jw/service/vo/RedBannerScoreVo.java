package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class RedBannerScoreVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer gradeId;
	
	private String gradeName;
	
	private float score;
	
	private Integer count;
	
	private String criterion;

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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCriterion() {
		return criterion;
	}

	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}
	
}
