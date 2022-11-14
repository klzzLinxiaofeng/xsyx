package platform.szxyzxx.web.oa.termial.vo;
 

import platform.education.oa.model.Comments; 
import platform.education.oa.model.SummaryReadUser;
import platform.education.oa.utils.UtilDate;
 
 
public class PhoneSummaryReadUserVo {
	  
	/**
	 *纪要id
	 */
	private String summaryId;
	/**
	 * 用户id
	 */
	private String userId ;
	/**
	 * 用户名字
	 */
	private String userName ;
	 
	/**
	 * 用户头像
	 */
	private String userImage ;   
 
	public PhoneSummaryReadUserVo(SummaryReadUser m){
	 
	 this.summaryId=m.getSummaryId()+"";
	 this.userId=m.getUserId()+"";
	 this.userName=m.getUserName();
	 this.userImage=m.getUserImage();  
			
	}

	public String getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	 
}