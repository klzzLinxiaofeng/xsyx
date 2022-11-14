package platform.education.paper.model;

import java.io.Serializable;

public class AnswerQuestionModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//题号
	private Integer pos ;
	
	//题目UUID
	private String questionUuid;
	

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getQuestionUuid() {
		return questionUuid;
	}

	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}
}


