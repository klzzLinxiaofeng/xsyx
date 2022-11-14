/**   
* @Title: UserRank.java
* @Package platform.education.paper.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月28日 下午1:53:40 
* @version V1.0   
*/
package platform.education.paper.model;

import java.io.Serializable;
import java.util.Date;

/** 
* @ClassName: UserRank 
* @Description: 统计一份试卷每个人的得分排名
* @author pantq
* @date 2017年2月28日 下午1:53:40 
*  
*/
public class UserRank implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Integer userId;
	
	/**
	 * 用户排名
	 */
	private Integer rank;
	
	/**
	 * 完成时间
	 */
	private Date finishedTime;
	
	/**
	 * 学号
	 */
	private String studentNum;
	
	/**
	 * 姓名
	 */
	private String studentName;
	
	/**
	 * 卷面总得分
	 */
	private Double score;

	/**
	 * 题号
	 */
	private Integer pos;
	
	/**
	 * 题型
	 */
	private String questionType;
	
	/**
	 * 正确率
	 */
	private String correctRate;
	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		if(rank == null){
			rank = 1;
		}
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * @return the finishedTime
	 */
	public Date getFinishedTime() {
		return finishedTime;
	}

	/**
	 * @param finishedTime the finishedTime to set
	 */
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}

	/**
	 * @return the studentNum
	 */
	public String getStudentNum() {
		return studentNum;
	}

	/**
	 * @param studentNum the studentNum to set
	 */
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	 * @return the pos
	 */
	public Integer getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Integer pos) {
		this.pos = pos;
	}

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
	 * @return the correctRate
	 */
	public String getCorrectRate() {
		return correctRate;
	}

	/**
	 * @param correctRate the correctRate to set
	 */
	public void setCorrectRate(String correctRate) {
		this.correctRate = correctRate;
	}

}
