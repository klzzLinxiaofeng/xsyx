package platform.education.generalTeachingAffair.vo;

import java.io.Serializable;

public class TeacherArchiveVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Integer userId;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 头像
	 */
	private String userIcon;
	
	/**
	 * 头像UUID
	 */
	private String IconUUID;
	
	/**
	 * 工号
	 */
	private String number;

	public String getIconUUID() {
		return IconUUID;
	}

	public void setIconUUID(String iconUUID) {
		IconUUID = iconUUID;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}
