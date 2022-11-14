package platform.education.netdisk.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Files
 * @author AutoCreate
 *
 */
@TableName("pan_files")
public class FilesEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 文件唯一标识符 
	 */
	private String uuid;
	/**
	 * 所属目录
	 */
	private Integer catalogId;
	/**
	 * 文件所属用户
	 */
	private Integer ownerId;
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
	
	public FilesEntity() {
		
	}
	
	public FilesEntity(Integer id) {
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
	 * 获取文件名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置文件名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取文件唯一标识符 
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置文件唯一标识符 
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取所属目录
	 * @return java.lang.Integer
	 */
	public Integer getCatalogId() {
		return this.catalogId;
	}
	
	/**
	 * 设置所属目录
	 * @param catalogId
	 * @type java.lang.Integer
	 */
	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	
	/**
	 * 获取文件所属用户
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置文件所属用户
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
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