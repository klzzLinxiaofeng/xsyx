package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * RepairFile
 * @author AutoCreate
 *
 */
public class RepairFile implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 维修
	 */
	private Integer repairId;
	/**
	 * 上传人
	 */
	private Integer posterId;
	/**
	 * 上传时间
	 */
	private java.util.Date postTime;
	/**
	 * 文件
	 */
	private String fileUuid;
	/**
	 * 缩略图位置
	 */
	private String thumbUrl;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 是否删除
	 */
	private Boolean isDelete;
	
	public RepairFile() {
		
	}
	
	public RepairFile(Integer id) {
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
	 * 获取维修
	 * @return java.lang.Integer
	 */
	public Integer getRepairId() {
		return this.repairId;
	}
	
	/**
	 * 设置维修
	 * @param repairId
	 * @type java.lang.Integer
	 */
	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}
	
	/**
	 * 获取上传人
	 * @return java.lang.Integer
	 */
	public Integer getPosterId() {
		return this.posterId;
	}
	
	/**
	 * 设置上传人
	 * @param posterId
	 * @type java.lang.Integer
	 */
	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}
	
	/**
	 * 获取上传时间
	 * @return java.util.Date
	 */
	public java.util.Date getPostTime() {
		return this.postTime;
	}
	
	/**
	 * 设置上传时间
	 * @param postTime
	 * @type java.util.Date
	 */
	public void setPostTime(java.util.Date postTime) {
		this.postTime = postTime;
	}
	
	/**
	 * 获取文件
	 * @return java.lang.String
	 */
	public String getFileUuid() {
		return this.fileUuid;
	}
	
	/**
	 * 设置文件
	 * @param fileUuid
	 * @type java.lang.String
	 */
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
	
	/**
	 * 获取缩略图位置
	 * @return java.lang.String
	 */
	public String getThumbUrl() {
		return this.thumbUrl;
	}
	
	/**
	 * 设置缩略图位置
	 * @param thumbUrl
	 * @type java.lang.String
	 */
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	
	
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * 获取是否删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置是否删除
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}