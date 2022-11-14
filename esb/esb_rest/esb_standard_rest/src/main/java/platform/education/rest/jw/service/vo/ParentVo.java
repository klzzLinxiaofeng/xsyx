package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class ParentVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer parentUserId;
	
	private Integer studentUserId;
	
	private String studentName;
	
	private String userName;
	
	private String parentName;
	
	private String viewName;
	
	private String parentIcon;
	
	private String mobile;
	
	private String imAccount;

	public Integer getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Integer parentUserId) {
		this.parentUserId = parentUserId;
	}

	public Integer getStudentUserId() {
		return studentUserId;
	}

	public void setStudentUserId(Integer studentUserId) {
		this.studentUserId = studentUserId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getParentIcon() {
		return parentIcon;
	}

	public void setParentIcon(String parentIcon) {
		this.parentIcon = parentIcon;
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
