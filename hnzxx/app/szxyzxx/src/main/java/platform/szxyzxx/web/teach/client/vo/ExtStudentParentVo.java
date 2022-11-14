package platform.szxyzxx.web.teach.client.vo;

import java.util.List;

/**
 * @function 学生信息VO  包含该学生下的家长信息
 * @date 2016-2-22
 */
public class ExtStudentParentVo {
	//学生的userId
	private Integer id;
	
	//学生的姓名
	private String name;
	
	//学生的所有家长  集合类型
	private List<ExtParentVo> parents;

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

	public List<ExtParentVo> getParents() {
		return parents;
	}

	public void setParents(List<ExtParentVo> parents) {
		this.parents = parents;
	}
	
}
