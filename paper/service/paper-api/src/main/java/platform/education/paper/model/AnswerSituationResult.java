/**   
* @Title: AnswerSituationResult.java
* @Package platform.education.paper.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年3月21日 下午7:00:33 
* @version V1.0   
*/
package platform.education.paper.model;

import java.io.Serializable;

/** 
* @ClassName: AnswerSituationResult 
* @Description: 试卷作答情况实体类 
* @author pantq
* @date 2017年3月21日 下午7:00:33 
*  
*/
public class AnswerSituationResult implements Serializable{

	private static final long serialVersionUID = 1L;

	//图片文件key
	private String fileId;
	
	//图片url路径
	private String fileUrl;
	
	//题号
	private Integer pos;
	
	//作答情况 0：错误，1：正确，99：未作答
	private Integer status;
	
	//用户作答答案
	private String userAnswer;
	
	//答案是否正确
	private Boolean isCorrect;

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * @param fileUrl the fileUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the userAnswer
	 */
	public String getUserAnswer() {
		return userAnswer;
	}

	/**
	 * @param userAnswer the userAnswer to set
	 */
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
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
	
}
