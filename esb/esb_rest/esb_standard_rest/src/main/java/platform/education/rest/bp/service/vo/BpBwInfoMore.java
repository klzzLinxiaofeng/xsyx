package platform.education.rest.bp.service.vo;

import framework.generic.dao.Model;

/**
 * BwInfoMore
 * @author AutoCreate
 *
 */
public class BpBwInfoMore implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 班级:team.id
	 */
	private Integer teamId;
	/**
	 * 类型:1.电子黑板报 2寻物启示
	 */
	private Integer type;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 文件:file.uuid
	 */
	private String file;
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
	private Boolean isDeleted;
	
	public BpBwInfoMore() {
		
	}
	
	public BpBwInfoMore(Integer id) {
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
	 * 获取班级:team.id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置班级:team.id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取类型:1.电子黑板报 2寻物启示
	 * @return java.lang.Integer
	 */
	public Integer getType() {
		return this.type;
	}
	
	/**
	 * 设置类型:1.电子黑板报 2寻物启示
	 * @param type
	 * @type java.lang.Integer
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 获取内容
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置内容
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取文件:file.uuid
	 * @return java.lang.String
	 */
	public String getFile() {
		return this.file;
	}
	
	/**
	 * 设置文件:file.uuid
	 * @param file
	 * @type java.lang.String
	 */
	public void setFile(String file) {
		this.file = file;
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
	
	/**
	 * 获取是否删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置是否删除
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}