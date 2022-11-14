 package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class StudentParentMsg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//家长ID
	private Integer parentId;
	//用户ID
	private Integer userId;
	//家长姓名
	private String name;
	//家长手机
	private String mobile;
	//家长头像
	private String icon;
	//与子女关系
	private String relation;
	//0=普通 1=主监护人
	private String rank;
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
}
