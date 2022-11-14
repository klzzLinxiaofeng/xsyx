package platform.szxyzxx.web.oa.termial.vo;
import platform.education.oa.model.Meeting;
import platform.education.oa.utils.UtilDate;
 
 
public class PhoneMeetingVo {
	
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 会议名称
	 */
	private String meetingName;
	/**
	 * 开始时间 
	 */
	private String startTime ;
	/**
	 * 结束时间 格式：2011-11-11 11:11
	 */
	private String finishTime ;
	 
	/**
	 * 会议地点
	 */
	private String address ;
	/**
	 * 会议说明
	 */
	private String meetingContent ;
	/**
	 * 发布者名字
	 */
	private String posterId ;
	/**
	 * 发布者名字
	 */
	private String posterName ;
	
	private String posterImg="";
	/**
	 * 发布时间
	 */
	private String createDate  ;
	/**
	 * 人数
	 */
	private String meeting_num  ;
 
	public PhoneMeetingVo(Meeting m){
	 this.id=m.getId()+"";
	 this.meetingName=m.getMeetingName();
	 this.startTime=m.getStarttime();
	 this.finishTime=m.getEndtime();
	 this.address=m.getAddress();
	 this.meetingContent=m.getMeetingContent();
	 this.posterId=m.getCreateuserId()+"";
	 this.posterName=m.getCreatename();
	 this.posterImg=m.getUserimage();
	 this.createDate=UtilDate.getDateFormatter(m.getCreateDate());
	 this.meeting_num=m.getMeetingNum()+"";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMeetingContent() {
		return meetingContent;
	}

	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}
	
	
	

	public String getPosterId() {
		return posterId;
	}

	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	
	
	

	public String getPosterImg() {
		return posterImg;
	}

	public void setPosterImg(String posterImg) {
		this.posterImg = posterImg;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getMeeting_num() {
		return meeting_num;
	}

	public void setMeeting_num(String meeting_num) {
		this.meeting_num = meeting_num;
	}

 
	
}