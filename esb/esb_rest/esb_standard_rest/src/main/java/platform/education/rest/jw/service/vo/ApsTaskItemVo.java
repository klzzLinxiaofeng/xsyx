package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class ApsTaskItemVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer itemId;
	
	private String itemName;
	
	private String category;
	
	private String checkType;
	
	private String code;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}	
