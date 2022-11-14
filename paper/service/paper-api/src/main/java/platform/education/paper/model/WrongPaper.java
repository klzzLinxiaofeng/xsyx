/**   
* @Title: WrongPaper.java
* @Package platform.education.paper.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年3月21日 上午10:44:10 
* @version V1.0   
*/
package platform.education.paper.model;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

/** 
* @ClassName: WrongPaper 
* @Description: 错题本实体类 
* @author pantq
* @date 2017年3月21日 上午10:44:10 
*  
*/
public class WrongPaper implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private Date createDate;
	
	//试卷标题
	private String paperTitle;
	
	//试卷UUID
	private String paperUuId;
	
	//题目UUID
	private String questionUuId;
	
	//分组ID
	private String groupId;
	
	//错题本id,用于删除操作
	private Integer userWrongId;
	
	//题型类型
	private String questionType;
	
	//科目名称
	private String subjectCode;
	
	//科目名称
	private String subjectName;
	
	//用于存复合题
	//private ComplexQuestion complexQuestion;
	
	//题目内容
	private String content;
	
	//是否复合题
	private Boolean isComplex;
	
	//复合题标题
	private String complexTitle;
	
	//题目选项
	private String dbPaperAnser;
	
	//将字符串数组转成普通数组 数据还原
	private String[] paperAnser;
	
	//正确答案
	private String dbCorrectAnswer;
	
	//将字符串数组转成普通数组 数据还原
	private String[] correctAnswer;
	
	
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
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	 * @return the subjectCode
	 */
	public String getSubjectCode() {
		return subjectCode;
	}

	/**
	 * @param subjectCode the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
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
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the paperTitle
	 */
	public String getPaperTitle() {
		return paperTitle;
	}

	/**
	 * @param paperTitle the paperTitle to set
	 */
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}

	/**
	 * @return the paperUuId
	 */
	public String getPaperUuId() {
		return paperUuId;
	}

	/**
	 * @param paperUuId the paperUuId to set
	 */
	public void setPaperUuId(String paperUuId) {
		this.paperUuId = paperUuId;
	}

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
	 * @return the isComplex
	 */
	public Boolean getIsComplex() {
		
		if(isComplex == null){
			isComplex = false;
		}
		return isComplex;
	}

	/**
	 * @param isComplex the isComplex to set
	 */
	public void setIsComplex(Boolean isComplex) {
		this.isComplex = isComplex;
	}

	/**
	 * @return the complexTitle
	 */
	public String getComplexTitle() {
		return complexTitle;
	}

	/**
	 * @param complexTitle the complexTitle to set
	 */
	public void setComplexTitle(String complexTitle) {
		this.complexTitle = complexTitle;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDbPaperAnser() {
		return dbPaperAnser;
	}

	public void setDbPaperAnser(String dbPaperAnser) {
		this.dbPaperAnser = dbPaperAnser;
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

	public String[] getPaperAnser() {
		return paperAnser;
	}

	public void setPaperAnser(String[] paperAnser) {
		this.paperAnser = paperAnser;
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


	
	
}
