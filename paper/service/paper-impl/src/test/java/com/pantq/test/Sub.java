/**   
* @Title: Sub.java
* @Package com.pantq.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月25日 下午5:46:49 
* @version V1.0   
*/
package com.pantq.test;

/** 
* @ClassName: Sub 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author pantq
* @date 2017年2月25日 下午5:46:49 
*  
*/
public class Sub {
	
	private String uuid;
	private String answer;
	private Long answerTime;
	private Boolean isCorrect;
	private Double score;
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return the answerTime
	 */
	public Long getAnswerTime() {
		return answerTime;
	}
	/**
	 * @param answerTime the answerTime to set
	 */
	public void setAnswerTime(Long answerTime) {
		this.answerTime = answerTime;
	}
	/**
	 * @return the isCorrect
	 */
	public Boolean getIsCorrect() {
		return isCorrect;
	}
	/**
	 * @param isCorrect the isCorrect to set
	 */
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	

}
