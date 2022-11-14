package platform.szxyzxx.web.oa.termial.vo;

import java.util.ArrayList;
import java.util.List;

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.Teacher;

public class PhoneDepVo {
	private String dep_id;
	private String dep_name;
	private List<Teacher>users ;
	
	public List<Teacher> getUsers() {
		return users;
	}
	public void setUsers(List<Teacher> users) {
		this.users = users;
	}
	public String getDep_id() {
		return dep_id;
	}
	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	
	
	
	
	public PhoneDepVo(Department d){
		this.dep_id = d.getId().toString();
		this.dep_name = d.getName();
		this.users=new ArrayList<Teacher>();
	}
}
