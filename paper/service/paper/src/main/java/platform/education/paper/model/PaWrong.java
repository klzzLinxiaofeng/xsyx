package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaWrong
 * @author AutoCreate
 *
 */
public class PaWrong implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private String userId;
	/**
	 * 关联题目或paper的id
	 */
	private String relateId;
	/**
	 * 错误类型, 是错的题目 或者 错的试卷 或者....
	 */
	private Integer wrongType;
	/**
	 * 重做并正确的标记
	 */
	private String reDoSuccess;
	/**
	 * 做关联的uuid
	 */
	private String uuid;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public PaWrong() {
		
	}
	
	public PaWrong(Integer id) {
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
	 * 获取这个userid指的是使用者的id或者有使用权的id
	 * @return java.lang.String
	 */
	public String getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置这个userid指的是使用者的id或者有使用权的id
	 * @param userId
	 * @type java.lang.String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取关联题目或paper的id
	 * @return java.lang.String
	 */
	public String getRelateId() {
		return this.relateId;
	}
	
	/**
	 * 设置关联题目或paper的id
	 * @param relateId
	 * @type java.lang.String
	 */
	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}
	
	/**
	 * 获取错误类型, 是错的题目 或者 错的试卷 或者....
	 * @return java.lang.Integer
	 */
	public Integer getWrongType() {
		return this.wrongType;
	}
	
	/**
	 * 设置错误类型, 是错的题目 或者 错的试卷 或者....
	 * @param wrongType
	 * @type java.lang.Integer
	 */
	public void setWrongType(Integer wrongType) {
		this.wrongType = wrongType;
	}
	
	/**
	 * 获取重做并正确的标记
	 * @return java.lang.String
	 */
	public String getReDoSuccess() {
		return this.reDoSuccess;
	}
	
	/**
	 * 设置重做并正确的标记
	 * @param reDoSuccess
	 * @type java.lang.String
	 */
	public void setReDoSuccess(String reDoSuccess) {
		this.reDoSuccess = reDoSuccess;
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