package platform.education.generalTeachingAffair.vo;

import java.io.Serializable;

public class PjUserActionTestVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Integer userId;
	
	private Double value;

	public PjUserActionTestVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
}
