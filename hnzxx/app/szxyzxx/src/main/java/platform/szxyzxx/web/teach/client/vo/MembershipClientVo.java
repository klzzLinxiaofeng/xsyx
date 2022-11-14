package platform.szxyzxx.web.teach.client.vo;

import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;

public class MembershipClientVo {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String owner_type;
	private String owner_id;
	private List<T>children;
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
	public String getOwner_type() {
		return owner_type;
	}
	public void setOwner_type(String owner_type) {
		this.owner_type = owner_type;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public List<T> getChildren() {
		return children;
	}
	public void setChildren(List<T> children) {
		this.children = children;
	}
	
	

}
