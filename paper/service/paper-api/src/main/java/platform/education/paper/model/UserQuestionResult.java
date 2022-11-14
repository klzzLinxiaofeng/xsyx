/**   
* @Title: UserQuestionResult.java
* @Package platform.education.paper.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年3月23日 下午4:06:23 
* @version V1.0   
*/
package platform.education.paper.model;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
* @ClassName: UserQuestionResult 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author pantq
* @date 2017年3月23日 下午4:06:23 
*  
*/
public class UserQuestionResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//题目UUID
	private String questionUuid;
	
	//题目类型
	private String questionType;
	
	//题目总得分
	private float score;
	
	//总应答人数
	private Integer answerCount;
	
	//不作答人数
	private Integer emptyCount;
	
	//总正答人数
	private Integer rightAnswerCount;
	
	private Integer teamId;

	//正答率
	private BigDecimal rightRate;
		
	//完成率
	private BigDecimal finishRate;
	
	private String subjectCode;
	
	private Integer knowledgeId;
	
	/**
	 * 难易度
	 */
	private Double difficulity;
	
	/**
	 * 认知度1：标记，2：理解，3：应用，4：探究，5：综合
	 */
	private String cognition;
	
	
	/**
	 * @return the questionType
	 */
	public String getQuestionType() {
		return questionType;
	}

	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	/**
	 * @return the score
	 */
	public float getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(float score) {
		this.score = score;
	}

	/**
	 * @return the answerCount
	 */
	public Integer getAnswerCount() {
		if(answerCount == null){
			answerCount = 0;
		}
		return answerCount;
	}

	/**
	 * @param answerCount the answerCount to set
	 */
	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

	/**
	 * @return the emptyCount
	 */
	public Integer getEmptyCount() {
		
		if(emptyCount == null){
			emptyCount = 0;
		}
		return emptyCount;
	}

	/**
	 * @param emptyCount the emptyCount to set
	 */
	public void setEmptyCount(Integer emptyCount) {
		this.emptyCount = emptyCount;
	}

	/**
	 * @return the rightAnswerCount
	 */
	public Integer getRightAnswerCount() {
		
		if(rightAnswerCount == null){
			rightAnswerCount = 0;
		}
		
		return rightAnswerCount;
	}

	/**
	 * @param rightAnswerCount the rightAnswerCount to set
	 */
	public void setRightAnswerCount(Integer rightAnswerCount) {
		this.rightAnswerCount = rightAnswerCount;
	}

	/**
	 * @return the questionUuid
	 */
	public String getQuestionUuid() {
		return questionUuid;
	}

	/**
	 * @param questionUuid the questionUuid to set
	 */
	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}

	/**
	 * @return the teamId
	 */
	public Integer getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return the rightRate
	 */
	public BigDecimal getRightRate() {
		return rightRate;
	}

	/**
	 * @param rightRate the rightRate to set
	 */
	public void setRightRate(BigDecimal rightRate) {
		this.rightRate = rightRate;
	}

	/**
	 * @return the finishRate
	 */
	public BigDecimal getFinishRate() {
		return finishRate;
	}

	/**
	 * @param finishRate the finishRate to set
	 */
	public void setFinishRate(BigDecimal finishRate) {
		this.finishRate = finishRate;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Integer getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public Double getDifficulity() {
		return difficulity;
	}

	public void setDifficulity(Double difficulity) {
		this.difficulity = difficulity;
	}

	public String getCognition() {
		return cognition;
	}

	public void setCognition(String cognition) {
		this.cognition = cognition;
	}

	
}
