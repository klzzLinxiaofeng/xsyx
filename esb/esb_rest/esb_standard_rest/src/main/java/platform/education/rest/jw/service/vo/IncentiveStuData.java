package platform.education.rest.jw.service.vo;

import java.io.Serializable;
import java.util.List;

public class IncentiveStuData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer studentId;
	
	private String studentName;

	private List<IncCategory> categoryList;
	
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<IncCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<IncCategory> categoryList) {
		this.categoryList = categoryList;
	}
	
	
}
