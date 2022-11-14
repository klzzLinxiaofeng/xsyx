package platform.education.rest.basic.service.vo;

import java.io.Serializable;

public class Parents implements Serializable {
	private static final long serialVersionUID = -3556583966307294486L;
	private Integer id;
	private String name;
	private String mobile;
	private String relation;
	private String rank;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
