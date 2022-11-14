package platform.education.paper.model.mobile;

import java.io.Serializable;

public class AnswersJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7323576673024998677L;
	
	private String questionUuid;
	
	//是否对错
	private Integer isCorrect;
	
	//作答时间
	private Integer answerTime;
	
	//提交答案
	private AnswerJson[] answer;
	
	private String[] picture;
	
	public Integer getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Integer isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Integer getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Integer answerTime) {
		this.answerTime = answerTime;
	}

	public String getQuestionUuid() {
		return questionUuid;
	}

	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}


	public String[] getPicture() {
		return picture;
	}

	public void setPicture(String[] picture) {
		this.picture = picture;
	}

	public AnswerJson[] getAnswer() {
		return answer;
	}

	public void setAnswer(AnswerJson[] answer) {
		this.answer = answer;
	}

}
