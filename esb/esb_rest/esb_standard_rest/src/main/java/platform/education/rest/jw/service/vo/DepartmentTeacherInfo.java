package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class DepartmentTeacherInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 用户头像路径
	 */
	private String icon;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 职务
	 */
	private String position;
	/**
	 * 办公电话
	 */
	private String telephone;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 教师ID
	 */
	private Integer teacherId;
	
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getImAccount() {
		return imAccount;
	}
	public void setImAccount(String imAccount) {
		this.imAccount = imAccount;
	}

}
