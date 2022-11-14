package platform.education.generalTeachingAffair.vo;

import java.util.List;
import java.util.Map;

public class TreeVo {
    
	List<TreeVo> childrens;
	
	Integer id;
	
	String name;
	
    Object sort;
	public Object getSort() {
		return sort;
	}

	public void setSort(Object sort) {
		this.sort = sort;
	}

	public List<TreeVo> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<TreeVo> childrens) {
		this.childrens = childrens;
	}

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
