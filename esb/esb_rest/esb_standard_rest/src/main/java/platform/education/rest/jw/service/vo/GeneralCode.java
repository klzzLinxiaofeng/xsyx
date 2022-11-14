package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class GeneralCode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//代码名称
	private String name;
	//代码值
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
