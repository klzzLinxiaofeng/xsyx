package platform.szxyzxx.web.bbx.client.vo;

public class ParentRead {
	//家长的id
	private Integer userId;
	//家长的name
	private String userName;
	//家长的头像
	private String userIcon;
	
	//家长的阅读时间 
	private String readTime;
	

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

	public String getUserIcon() {
		return userIcon;
	}
	
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	
	public String getReadTime() {
		return readTime;
	}
	
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
	
	
	
}
