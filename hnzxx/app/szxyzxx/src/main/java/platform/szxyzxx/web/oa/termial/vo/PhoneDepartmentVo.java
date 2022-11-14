package platform.szxyzxx.web.oa.termial.vo;

import java.util.ArrayList;
import java.util.List;

import platform.education.generalTeachingAffair.model.Department;

public class PhoneDepartmentVo {
	
	private String dep_id;
	
	private String dep_name;
	
	private List<PhoneUserInfoVo> users=new ArrayList<PhoneUserInfoVo>();
	
	public PhoneDepartmentVo(Department d,List<PhoneUserInfoVo> users){
		this.dep_id=d.getId()+"";
		this.dep_name=d.getName();
		if(!users.isEmpty()){
			this.users=users;
		}
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

	public List<PhoneUserInfoVo> getUsers() {
		return users;
	}

	public void setUsers(List<PhoneUserInfoVo> users) {
		this.users = users;
	}
	
	

}
