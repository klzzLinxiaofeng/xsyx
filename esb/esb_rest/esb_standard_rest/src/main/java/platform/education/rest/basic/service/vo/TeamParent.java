package platform.education.rest.basic.service.vo;

import java.io.Serializable;
import java.util.List;

public class TeamParent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private List<Parents> parents;
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
	public List<Parents> getParents() {
		return parents;
	}
	public void setParents(List<Parents> parents) {
		this.parents = parents;
	}
	
}
