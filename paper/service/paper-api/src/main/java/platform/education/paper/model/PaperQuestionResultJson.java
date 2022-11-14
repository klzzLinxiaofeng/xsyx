/**   
* @Title: PaperQuestionResult.java
* @Package platform.education.paper.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年3月22日 下午4:35:46 
* @version V1.0   
*/
package platform.education.paper.model;

import java.io.Serializable;

/** 
* @ClassName: PaperQuestionResult 
* @Description: 一份试卷的所有题目实体类
* @author pantq
* @date 2017年3月22日 下午4:35:46 
*  
*/
public class PaperQuestionResultJson implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String questionUuid;
	
	//是否复合题
	private Boolean isComplex = false;
		
	//复合题标题
	private String complexTitle;
	
	//分组ID
	private String groupId;
	
	//题目序号
	private Integer pos;
	
	//题目标题
	private String paperTitle;
	
	//题干
	private String content;
	
	//题目选项
	private String[] questionAnswer;
	
	//标准答案
	private String[] correctAnswer;
	
	//答案解释
	private String explanation;

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


	public String[] getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String[] questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String[] getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String[] correctAnswer) {
		this.correctAnswer = correctAnswer;
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
	 * @return the isComplex
	 */
	public Boolean getIsComplex() {
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
}
