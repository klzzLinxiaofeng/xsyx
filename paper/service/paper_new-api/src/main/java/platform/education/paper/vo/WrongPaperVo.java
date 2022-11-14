package platform.education.paper.vo;

import java.io.Serializable;
import java.util.Date;

public class WrongPaperVo implements Serializable{
private static final long serialVersionUID = 1L;

	
	//题目UUID
	private String questionUuId;
	
	//错题本id,用于删除操作
	private Integer userWrongId;
	
	//题型类型
	private String questionType;
	
	//题目内容
	private String content;
	//难度
	private String  difficulity;
	//总用时
	private  Integer totalTime;
	//平均用时
	private  Integer averageTime;
	//
	private  Float    rightAnswerRight;
	
	private Integer parentId;
	
	private boolean isComplex;
	
	private  String complexTitle;
	
	private Integer number;
	
	private String paperTitle;
	
	private Integer questionId;
	
	private float score;
	
	private String  difficulityFloat;
	public String getDifficulityFloat() {
		return difficulityFloat;
	}

	public void setDifficulityFloat(String difficulityFloat) {
		this.difficulityFloat = difficulityFloat;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	private String[] knowledges;
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String[] getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(String[] knowledges) {
		this.knowledges = knowledges;
	}

	public Integer getNumber() {
		return number;
	}

	public String getPaperTitle() {
		return paperTitle;
	}

	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public boolean isComplex() {
		return isComplex;
	}

	public void setComplex(boolean isComplex) {
		this.isComplex = isComplex;
	}

	public String getComplexTitle() {
		return complexTitle;
	}

	public void setComplexTitle(String complexTitle) {
		this.complexTitle = complexTitle;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDifficulity() {
		return difficulity;
	}

	public void setDifficulity(String difficulity) {
		this.difficulity = difficulity;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(Integer averageTime) {
		this.averageTime = averageTime;
	}

	public Float getRightAnswerRight() {
		return rightAnswerRight;
	}

	public void setRightAnswerRight(Float rightAnswerRight) {
		this.rightAnswerRight = rightAnswerRight;
	}

	private String dbPaperAnswer;
	
	//将字符串数组转成普通数组 数据还原
	private String[] paperAnswer;
	
	private String dbCorrectAnswer;
	
	//将字符串数组转成普通数组 数据还原
	private String[] correctAnswer;
	
	
	private String answer;
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	//答案解析
	private String explanation;
	
	//用户上一次答案
	private String dbUserAnswer;
	
	
	//将字符串数组转成普通数组 数据还原
	private String[] userAnswer;
	
	//错答次数
	private Integer wrongCount;
	
	//正答次数
	private Integer rightCount;
	
	//答案是否正确
	private Boolean isCorrect ;

	/**
	 * @return the userWrongId
	 */
	public Integer getUserWrongId() {
		return userWrongId;
	}

	/**
	 * @param userWrongId the userWrongId to set
	 */
	public void setUserWrongId(Integer userWrongId) {
		this.userWrongId = userWrongId;
	}



	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	

	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * @param explanation the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	

	/**
	 * @return the wrongCount
	 */
	public Integer getWrongCount() {
		if(wrongCount == null){
			wrongCount = 0;
		}
		return wrongCount;
	}

	/**
	 * @param wrongCount the wrongCount to set
	 */
	public void setWrongCount(Integer wrongCount) {
		this.wrongCount = wrongCount;
	}

	/**
	 * @return the rightCount
	 */
	public Integer getRightCount() {
		if(rightCount == null){
			rightCount = 0;
		}
		return rightCount;
	}

	/**
	 * @param rightCount the rightCount to set
	 */
	public void setRightCount(Integer rightCount) {
		this.rightCount = rightCount;
	}


	
	/**
	 * @return the isCorrect
	 */
	public Boolean getIsCorrect() {
		if(isCorrect == null){
			isCorrect = false;
		}
		return isCorrect;
	}

	/**
	 * @param isCorrect the isCorrect to set
	 */
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
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
	 * @return the complexQuestion
	 */




	/**
	 * @return the questionUuId
	 */
	public String getQuestionUuId() {
		return questionUuId;
	}

	/**
	 * @param questionUuId the questionUuId to set
	 */
	public void setQuestionUuId(String questionUuId) {
		this.questionUuId = questionUuId;
	}




	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


	

	
	public String getDbCorrectAnswer() {
		return dbCorrectAnswer;
	}

	public void setDbCorrectAnswer(String dbCorrectAnswer) {
		this.dbCorrectAnswer = dbCorrectAnswer;
	}

	

	public String getDbUserAnswer() {
		return dbUserAnswer;
	}

	public void setDbUserAnswer(String dbUserAnswer) {
		this.dbUserAnswer = dbUserAnswer;
	}

	

	public String[] getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String[] correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String[] getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String[] userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getDbPaperAnswer() {
		return dbPaperAnswer;
	}

	public void setDbPaperAnswer(String dbPaperAnswer) {
		this.dbPaperAnswer = dbPaperAnswer;
	}

	public String[] getPaperAnswer() {
		return paperAnswer;
	}

	public void setPaperAnswer(String[] paperAnswer) {
		this.paperAnswer = paperAnswer;
	}

}
