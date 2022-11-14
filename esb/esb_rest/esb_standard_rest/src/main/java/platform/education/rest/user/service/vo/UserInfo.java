package platform.education.rest.user.service.vo;

import java.io.Serializable;

/**
 * 登录成功返回的信息
 * 
 * @author hmzhang 2016/07/25
 *
 */
public class UserInfo implements Serializable {
	/**
     *
     */
	private static final long serialVersionUID = -5179686936016731378L;

	/**
	 * 用户ID
	 */
	private Integer id;

	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 头像url
	 */
	private String iconUrl;
	
	private Boolean isTeacher =false;

	
	
	public Boolean getIsTeacher() {
		return isTeacher;
	}

	public void setIsTeacher(Boolean isTeacher) {
		this.isTeacher = isTeacher;
	}

	public UserInfo(){
		
	}
	
	public UserInfo(Integer id, String name, String iconUrl) {
		super();
		this.id = id;
		this.name = name;
		this.iconUrl = iconUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
}
