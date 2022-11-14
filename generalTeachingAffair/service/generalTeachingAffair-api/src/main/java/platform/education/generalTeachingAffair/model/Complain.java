package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * Complain
 * @author AutoCreate
 *
 */
public class Complain implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 投诉人userId
	 */
	private Integer complainantId;
	/**
	 * 投诉人所在学校id
	 */
	private Integer schoolId;
	/**
	 * 投诉人所在部门id
	 */
	private Integer departId;
	/**
	 * 问题类别
	 */
	private String type;
	/**
	 * 问题描述
	 */
	private String description;
	/**
	 * 是否已处理
	 */
	private Boolean isDispose;
	/**
	 * 处理人userId
	 */
	private Integer disposeId;
	/**
	 * 处理方式
	 */
	private String disposeWay;
	/**
	 * 是否已评价
	 */
	private Boolean isEvaluate;
	/**
	 * 评价内容
	 */
	private String content;
	/**
	 * 评价星级
	 */
	private Integer level;
	/**
	 * 是否已删除
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 最后修改时间
	 */
	private java.util.Date modifyDate;
	
	public Complain() {
		
	}
	
	public Complain(Integer id) {
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
	 * 获取投诉人userId
	 * @return java.lang.Integer
	 */
	public Integer getComplainantId() {
		return this.complainantId;
	}
	
	/**
	 * 设置投诉人userId
	 * @param complainantId
	 * @type java.lang.Integer
	 */
	public void setComplainantId(Integer complainantId) {
		this.complainantId = complainantId;
	}
	
	/**
	 * 获取投诉人所在学校id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置投诉人所在学校id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取投诉人所在部门id
	 * @return java.lang.Integer
	 */
	public Integer getDepartId() {
		return this.departId;
	}
	
	/**
	 * 设置投诉人所在部门id
	 * @param departId
	 * @type java.lang.Integer
	 */
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	
	/**
	 * 获取问题类别
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置问题类别
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取问题描述
	 * @return java.lang.String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 设置问题描述
	 * @param description
	 * @type java.lang.String
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取是否已处理
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDispose() {
		return this.isDispose;
	}
	
	/**
	 * 设置是否已处理
	 * @param isDispose
	 * @type java.lang.Boolean
	 */
	public void setIsDispose(Boolean isDispose) {
		this.isDispose = isDispose;
	}
	
	/**
	 * 获取处理人userId
	 * @return java.lang.Integer
	 */
	public Integer getDisposeId() {
		return this.disposeId;
	}
	
	/**
	 * 设置处理人userId
	 * @param disposeId
	 * @type java.lang.Integer
	 */
	public void setDisposeId(Integer disposeId) {
		this.disposeId = disposeId;
	}
	
	/**
	 * 获取处理方式
	 * @return java.lang.String
	 */
	public String getDisposeWay() {
		return this.disposeWay;
	}
	
	/**
	 * 设置处理方式
	 * @param disposeWay
	 * @type java.lang.String
	 */
	public void setDisposeWay(String disposeWay) {
		this.disposeWay = disposeWay;
	}
	
	/**
	 * 获取是否已评价
	 * @return java.lang.Boolean
	 */
	public Boolean getIsEvaluate() {
		return this.isEvaluate;
	}
	
	/**
	 * 设置是否已评价
	 * @param isEvaluate
	 * @type java.lang.Boolean
	 */
	public void setIsEvaluate(Boolean isEvaluate) {
		this.isEvaluate = isEvaluate;
	}
	
	/**
	 * 获取评价内容
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置评价内容
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取评价星级
	 * @return java.lang.Integer
	 */
	public Integer getLevel() {
		return this.level;
	}
	
	/**
	 * 设置评价星级
	 * @param level
	 * @type java.lang.Integer
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	/**
	 * 获取是否已删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置是否已删除
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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
	 * 获取最后修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置最后修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}