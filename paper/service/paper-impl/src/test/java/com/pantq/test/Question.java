package com.pantq.test;

import java.io.Serializable;

public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String questionUuid;
	
	private Integer pos;

	public String getQuestionUuid() {
		return questionUuid;
	}

	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}
}
