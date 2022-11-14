/**   
* @Title: Answer.java
* @Package platform.education.paper.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年3月2日 下午12:39:05 
* @version V1.0   
*/
package platform.education.paper.model;

import java.io.Serializable;

/** 
* @ClassName: Answer 
* @Description: 答案试题类
* @author pantq
* @date 2017年3月2日 下午12:39:05 
*  
*/
public class Answer implements Serializable{
	
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String[] answer;
	private Integer isCorrect;
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
	
	/**
	 * @return the isCorrect
	 */
	public Integer getIsCorrect() {
		return isCorrect;
	}
	/**
	 * @param isCorrect the isCorrect to set
	 */
	public void setIsCorrect(Integer isCorrect) {
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
	/**
	 * @return the answer
	 */
	public String[] getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
	
	
	

}
