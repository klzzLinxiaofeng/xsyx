package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsToolLibrary
 * @author AutoCreate
 *
 */
public class LadsToolLibrary implements Model<String> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 启用或失效
	 */
	private String validFlag;
	/**
	 * 工具名字
	 */
	private String toolName;
	/**
	 * 图标
	 */
	private String image;
	
	public LadsToolLibrary() {
		
	}
	
	public LadsToolLibrary(String id) {
		this.id = id;
	}
	
	public String getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * @return java.lang.String
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * 设置主键
	 * @param id
	 * @type java.lang.String
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取启用或失效
	 * @return java.lang.String
	 */
	public String getValidFlag() {
		return this.validFlag;
	}
	
	/**
	 * 设置启用或失效
	 * @param validFlag
	 * @type java.lang.String
	 */
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	
	/**
	 * 获取工具名字
	 * @return java.lang.String
	 */
	public String getToolName() {
		return this.toolName;
	}
	
	/**
	 * 设置工具名字
	 * @param toolName
	 * @type java.lang.String
	 */
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	
	/**
	 * 获取图标
	 * @return java.lang.String
	 */
	public String getImage() {
		return this.image;
	}
	
	/**
	 * 设置图标
	 * @param image
	 * @type java.lang.String
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
}