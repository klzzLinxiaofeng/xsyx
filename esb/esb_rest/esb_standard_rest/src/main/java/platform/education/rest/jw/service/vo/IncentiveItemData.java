package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class IncentiveItemData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//项目分组 组名
	private String category;
	//分组 标号
	private String categoryCode;
	
	private Integer itemId;
	
	private String itemName;
	
	private Integer isChoose;

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
	
}
