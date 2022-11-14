package platform.education.generalTeachingAffair.vo;

import java.util.Date;

public class AssessmentScoreVo {
    /**
     * 学生评价的主表
     */
	private Integer judgeId;
	/**
	 * 老师这次任务的开始时间
	 */
	private Date startDate;
	/**
	 * 老师这次任务的结束时间
	 */
	private Date finishDate;
	/**
	 * 学生的评价内容
	 */
	private String description;
	/**
	 * 学生的单项评分
	 */
	private Integer score;
	/**
	 * 评价项名称
	 */
	private String itemName;
	/**
	 * 评价项id
	 */
	private Integer itemId;
	
	public Integer getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(Integer judgeId) {
		this.judgeId = judgeId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
}
