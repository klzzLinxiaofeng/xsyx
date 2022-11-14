package platform.education.rest.basic.service.vo;

import java.io.Serializable;

public class GradeInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//年级ID
	private Integer id;
	
	//年级校内名称
	private String name;

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
	
}
