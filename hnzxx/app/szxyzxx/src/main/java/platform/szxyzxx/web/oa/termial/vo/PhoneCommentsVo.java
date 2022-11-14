package platform.szxyzxx.web.oa.termial.vo;
 

import platform.education.oa.model.Comments; 
import platform.education.oa.utils.UtilDate;
 
 
public class PhoneCommentsVo {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 *会议id
	 */
	private String meetingId;
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
	/**
	 * 评论内容
	 */
	private String content ;
	 
	/**
	 * 发布时间
	 */
	private String createDate  ; 
 
	public PhoneCommentsVo(Comments m){
	 this.id=m.getId()+""; 
	 this.meetingId=m.getMeetingId()+"";
	 this.userId=m.getCreateuserId()+"";
	 this.userName=m.getCreatename();
	 this.userImage=m.getCreateuserImage();
	 this.content=m.getComment();
	 this.createDate=UtilDate.getDateFormatter(m.getCreateDate());
			
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
 
}