package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsLastStudyRecord
 * @author AutoCreate
 *
 */
public class LadsLastStudyRecord implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * lads课件的id
	 */
	private String ldid;
	/**
	 * 学习用户id
	 */
	private Integer userId;
	/**
	 * 最后学习到哪个活动的id
	 */
	private String lastToolId;
	/**
	 * 做关联的uuid
	 */
	private String uuid;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public LadsLastStudyRecord() {
		
	}
	
	public LadsLastStudyRecord(Integer id) {
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
	 * 获取lads课件的id
	 * @return java.lang.String
	 */
	public String getLdid() {
		return this.ldid;
	}
	
	/**
	 * 设置lads课件的id
	 * @param ldid
	 * @type java.lang.String
	 */
	public void setLdid(String ldid) {
		this.ldid = ldid;
	}
	
	/**
	 * 获取学习用户id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置学习用户id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取最后学习到哪个活动的id
	 * @return java.lang.String
	 */
	public String getLastToolId() {
		return this.lastToolId;
	}
	
	/**
	 * 设置最后学习到哪个活动的id
	 * @param lastToolId
	 * @type java.lang.String
	 */
	public void setLastToolId(String lastToolId) {
		this.lastToolId = lastToolId;
	}
	
	/**
	 * 获取做关联的uuid
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置做关联的uuid
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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