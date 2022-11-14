package platform.education.paper.model;

import java.io.Serializable;

/**
 * 大数据统计用户作答时间
 * @author pantq
 *
 */
public class PaperAnswerTime implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//题号
	private Integer pos;
	
	//是否正确
	private Boolean isCorrect;
	
	//知识点
	private String knowledge;
	
	//答题用时
	private Integer answerTime;
	
	//最长用时 方便画图用作临界点
	private Integer maxAnswerTime;

	//平均用时
	private Integer avgAnswerTime;

	//题目UUID
	private String questionUuid;

	public String getQuestionUuid() {
		return questionUuid;
	}

	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}

	public Integer getAvgAnswerTime() {
		return avgAnswerTime;
	}

	public void setAvgAnswerTime(Integer avgAnswerTime) {
		this.avgAnswerTime = avgAnswerTime;
	}

	public Boolean getCorrect() {
		return isCorrect;
	}

	public void setCorrect(Boolean correct) {
		isCorrect = correct;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public Integer getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Integer answerTime) {
		this.answerTime = answerTime;
	}

	public Integer getMaxAnswerTime() {
		return maxAnswerTime;
	}

	public void setMaxAnswerTime(Integer maxAnswerTime) {
		this.maxAnswerTime = maxAnswerTime;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
}
