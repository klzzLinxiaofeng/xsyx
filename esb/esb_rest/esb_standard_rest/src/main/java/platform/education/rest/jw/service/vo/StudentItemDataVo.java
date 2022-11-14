package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class StudentItemDataVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer studentId;
	
	private String studentName;
	
	private String sex;
	
	private Integer itemId;
	
	private String itemName;
	
	private Integer isChoose;
	

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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getIsChoose() {
		return isChoose;
	}

	public void setIsChoose(Integer isChoose) {
		this.isChoose = isChoose;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
