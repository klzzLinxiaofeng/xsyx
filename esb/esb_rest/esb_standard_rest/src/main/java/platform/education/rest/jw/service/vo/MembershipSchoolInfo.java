package platform.education.rest.jw.service.vo;

import java.io.Serializable;
import java.util.List;

public class MembershipSchoolInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//组织关系记录ID
	private Integer id;
	
	//组织名称
	private String name;
	
	//本组织关系的类型   参见接口描述
	private String ownnerType;
	
	//本组织对应真实记录的ID
	private String ownerId;
	
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

	public String getOwnnerType() {
		return ownnerType;
	}


	public void setOwnnerType(String ownnerType) {
		this.ownnerType = ownnerType;
	}


	public String getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}
