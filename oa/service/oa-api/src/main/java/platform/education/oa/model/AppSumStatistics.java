package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * AppSumStatistics
 * @author AutoCreate
 *
 */
public class AppSumStatistics implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 功能类型
	 */
	private String type;
	/**
	 * 统计
	 */
	private Integer statistics;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public AppSumStatistics() {
		
	}
	
	public AppSumStatistics(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取功能类型
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置功能类型
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取统计
	 * @return java.lang.Integer
	 */
	public Integer getStatistics() {
		return this.statistics;
	}
	
	/**
	 * 设置统计
	 * @param statistics
	 * @type java.lang.Integer
	 */
	public void setStatistics(Integer statistics) {
		this.statistics = statistics;
	}
	
	/**
	 * 获取创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}