package platform.education.rest.oa.service.vo;

public class UserInfo {
	// 接收人的userId
	private Integer UserId;
	// 接收人姓名
	private String name;
	//接收人头像地址
	private String userIcon;

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}





}
