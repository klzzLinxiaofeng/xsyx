package platform.szxyzxx.web.statistic.vo;

import java.io.Serializable;

public class DemoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String[] answer;
	
	private String uuid;
	
	private Integer isCorrect;
	
	private Integer answerTime;

	public String[] getAnswer() {
		return answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public Integer getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Integer answerTime) {
		this.answerTime = answerTime;
	}

	public Integer getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Integer isCorrect) {
		this.isCorrect = isCorrect;
	}

}
