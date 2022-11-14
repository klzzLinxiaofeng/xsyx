package platform.education.generalTeachingAffair.vo;

import java.util.List;

public class IncreaseStudentData {

	private Integer itemId;
	
	private String itemName;
	
	private List<StudentItemData> studentDates;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public List<StudentItemData> getStudentDates() {
		return studentDates;
	}

	public void setStudentDates(List<StudentItemData> studentDates) {
		this.studentDates = studentDates;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
}
