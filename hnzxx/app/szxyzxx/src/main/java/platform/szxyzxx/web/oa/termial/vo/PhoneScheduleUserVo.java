package platform.szxyzxx.web.oa.termial.vo;

import platform.education.oa.model.ScheduleUser;

public class PhoneScheduleUserVo {
	
	private String receiverId;
	
	private String receiverName;
	
	public PhoneScheduleUserVo(ScheduleUser su){
		this.receiverId=su.getReceiverId()+"";
		this.receiverName=su.getReceiverName();
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
	
	

}
