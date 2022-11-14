package platform.education.rest.jw.service.vo;

import java.util.List;


public class IncCategory {
	
	private String category;
	
	private String name;

	private String categoryCode;
	
	private List<IncItemData> itemList;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public List<IncItemData> getItemList() {
		return itemList;
	}

	public void setItemList(List<IncItemData> itemList) {
		this.itemList = itemList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
