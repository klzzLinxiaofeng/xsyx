package platform.education.netdisk.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Catalog
 * @author AutoCreate
 *
 */
@TableName("pan_catalog")
public class CatalogEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 父级目录(顶层目录该值为0)
	 */
	private Integer parentId;
	/**
	 * 目录名称
	 */
	private String name;
	/**
	 * 所属用户ID
	 */
	private Integer ownerId;

	/**
	 * 是否公开
	 */
	private Integer published;
	/**
	 * modifyDate
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date modifyDate;
	/**
	 * createDate
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	
	public CatalogEntity() {
		
	}
	
	public CatalogEntity(Integer id) {
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
	 * 获取父级目录(顶层目录该值为0)
	 * @return java.lang.Integer
	 */
	public Integer getParentId() {
		return this.parentId;
	}
	
	/**
	 * 设置父级目录(顶层目录该值为0)
	 * @param parentId
	 * @type java.lang.Integer
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取目录名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置目录名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取所属用户ID
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置所属用户ID
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getPublished() {
		return published;
	}

	public void setPublished(Integer published) {
		this.published = published;
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
	
}