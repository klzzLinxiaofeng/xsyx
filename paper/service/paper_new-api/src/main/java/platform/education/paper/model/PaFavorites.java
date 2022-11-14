package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaFavorites
 * @author AutoCreate
 *
 */
public class PaFavorites implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * userId
	 */
	private Integer posterId;
	/**
	 * objectId
	 */
	private Integer objectId;
	/**
	 * 0 为试卷 1为试题
	 */
	private Integer objectType;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	public PaFavorites() {
		
	}
	
	public PaFavorites(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	/**
	 * 获取objectId
	 * @return java.lang.Integer
	 */
	public Integer getObjectId() {
		return this.objectId;
	}
	
	/**
	 * 设置objectId
	 * @param objectId
	 * @type java.lang.Integer
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	
	public Integer getPosterId() {
		return posterId;
	}

	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}

	/**
	 * 获取0 为试卷 1为试题
	 * @return java.lang.Integer
	 */
	public Integer getObjectType() {
		return this.objectType;
	}
	
	/**
	 * 设置0 为试卷 1为试题
	 * @param objectType
	 * @type java.lang.Integer
	 */
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	
	
	/**
	 * 获取createDate
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置createDate
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取modifyDate
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置modifyDate
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}