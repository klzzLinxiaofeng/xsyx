package platform.szxyzxx.web.teach.vo;

import java.util.List;

public class Resourse {
	
	private String name;//名称
	private Integer level;//层次
	private String path;//路径
	private boolean isLeaf;
	private List<Resourse> resourseList;//子类资源集合
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Resourse> getResourseList() {
		return resourseList;
	}

	public void setResourseList(List<Resourse> resourseList) {
		this.resourseList = resourseList;
	}
	
	

}
