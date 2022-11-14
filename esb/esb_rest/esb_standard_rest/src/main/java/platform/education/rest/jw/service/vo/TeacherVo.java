package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class TeacherVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	
	private String userName;
	
	private String name;
	
	private String userIcon;
	
	private String mobile;
	/**
	 * 环信账号
	 */
	private String imAccount;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getImAccount() {
		return imAccount;
	}

	public void setImAccount(String imAccount) {
		this.imAccount = imAccount;
	}

}
