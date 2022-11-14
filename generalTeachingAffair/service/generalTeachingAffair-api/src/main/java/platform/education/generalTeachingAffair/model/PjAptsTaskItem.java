package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjAptsTaskItem
 * @author AutoCreate
 *
 */
public class PjAptsTaskItem implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * aptsTaskId
	 */
	private Integer aptsTaskId;
	/**
	 * name
	 */
	private String name;
	/**
	 * listOrder
	 */
	private Integer listOrder;
	/**
	 * label
	 */
	private String label;
	/**
	 * fullScore
	 */
	private Integer fullScore;
	/**
	 * realScore
	 */
	private Integer realScore;
	/**
	 * scale
	 */
	private Integer scale;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	/**
	 * isDelete
	 */
	private Boolean isDelete;
	/**
	 * evDate
	 */
	private java.util.Date evDate;
	
	public PjAptsTaskItem() {
		
	}
	
	public PjAptsTaskItem(Integer id) {
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
	 * 获取aptsTaskId
	 * @return java.lang.Integer
	 */
	public Integer getAptsTaskId() {
		return this.aptsTaskId;
	}
	
	/**
	 * 设置aptsTaskId
	 * @param aptsTaskId
	 * @type java.lang.Integer
	 */
	public void setAptsTaskId(Integer aptsTaskId) {
		this.aptsTaskId = aptsTaskId;
	}
	
	/**
	 * 获取name
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置name
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取listOrder
	 * @return java.lang.Integer
	 */
	public Integer getListOrder() {
		return this.listOrder;
	}
	
	/**
	 * 设置listOrder
	 * @param listOrder
	 * @type java.lang.Integer
	 */
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	
	/**
	 * 获取label
	 * @return java.lang.String
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * 设置label
	 * @param label
	 * @type java.lang.String
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * 获取fullScore
	 * @return java.lang.Integer
	 */
	public Integer getFullScore() {
		return this.fullScore;
	}
	
	/**
	 * 设置fullScore
	 * @param fullScore
	 * @type java.lang.Integer
	 */
	public void setFullScore(Integer fullScore) {
		this.fullScore = fullScore;
	}
	
	/**
	 * 获取realScore
	 * @return java.lang.Integer
	 */
	public Integer getRealScore() {
		return this.realScore;
	}
	
	/**
	 * 设置realScore
	 * @param realScore
	 * @type java.lang.Integer
	 */
	public void setRealScore(Integer realScore) {
		this.realScore = realScore;
	}
	
	/**
	 * 获取scale
	 * @return java.lang.Integer
	 */
	public Integer getScale() {
		return this.scale;
	}
	
	/**
	 * 设置scale
	 * @param scale
	 * @type java.lang.Integer
	 */
	public void setScale(Integer scale) {
		this.scale = scale;
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
	
	/**
	 * 获取isDelete
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置isDelete
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	/**
	 * 获取evDate
	 * @return java.util.Date
	 */
	public java.util.Date getEvDate() {
		return this.evDate;
	}
	
	/**
	 * 设置evDate
	 * @param evDate
	 * @type java.util.Date
	 */
	public void setEvDate(java.util.Date evDate) {
		this.evDate = evDate;
	}
	
}