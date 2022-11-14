package platform.education.rest.oa.service.vo;

import java.util.List;

public class UserRead {
	private List<UserInfo> readed;
	
	private List<UserInfo> unread;

	public List<UserInfo> getReaded() {
		return readed;
	}

	public void setReaded(List<UserInfo> readed) {
		this.readed = readed;
	}

	public List<UserInfo> getUnread() {
		return unread;
	}

	public void setUnread(List<UserInfo> unread) {
		this.unread = unread;
	}
	
	
}
