package platform.szxyzxx.web.oa.termial.vo;

import platform.education.oa.model.MeetingUser;
import platform.education.oa.model.ScheduleUser;
import platform.education.user.model.Profile;

public class PhoneMeetingUserVo {
	private String meetingId;
	private String receiverId;
	
	private String receiverName;
	
	private String receiverPicture;
	
	public PhoneMeetingUserVo(MeetingUser su, String imgurl){
		this.meetingId=su.getMeetingId()+"";
		this.receiverId=su.getUserId()+"";
		this.receiverName=su.getUserName();
		this.receiverPicture=imgurl;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getReceiverPicture() {
		return receiverPicture;
	}

	public void setReceiverPicture(String receiverPicture) {
		this.receiverPicture = receiverPicture;
	}
	
	

}
