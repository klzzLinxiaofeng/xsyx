package platform.education.rest.resource.service.vo;

import java.io.Serializable;

public class ExtMicroPublishedRecord implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -7481462195780316458L;
	
	private Integer id;
	private String createDate;
	private String modifyDate;
	private String publishedMicroId;
	private String reviews;
	private Integer reward;
	private Integer finishedFlag;
	private Integer userId;
	private String userName;
	private String finishedDate;
	private String studentNumber;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getPublishedMicroId() {
		return publishedMicroId;
	}
	public void setPublishedMicroId(String publishedMicroId) {
		this.publishedMicroId = publishedMicroId;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public Integer getReward() {
		return reward;
	}
	public void setReward(Integer reward) {
		this.reward = reward;
	}
	public Integer getFinishedFlag() {
		return finishedFlag;
	}
	public void setFinishedFlag(Integer finishedFlag) {
		this.finishedFlag = finishedFlag;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
}
