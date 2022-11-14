package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsRuleItem
 * @author AutoCreate
 *
 */
public class ApsRuleItem implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 对应的评价规则  aps_rule.id
	 */
	private Integer ruleId;
	/**
	 * 考核类型  XY-JY-JSKPLB 01=常规 02=加分 03=减分 09=其他
	 */
	private String checkType;
	/**
	 * 评价项目名称
	 */
	private String name;
	/**
	 * 项目分类
	 */
	private String category;
	/**
	 * 说明
	 */
	private String description;
	/**
	 * 排列次序
	 */
	private Integer listOrder;
	/**
	 * 标号，编号（可空）
	 */
	private String code;
	/**
	 * 本项得分（如果是扣分项用负值）
	 */
	private Float score;
	/**
	 * 本项所占权重（不需要置0）
	 */
	private Float scale;
	/**
	 * 删除标志
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
	
	public ApsRuleItem() {
		
	}
	
	public ApsRuleItem(Integer id) {
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
	 * 获取对应的评价规则  aps_rule.id
	 * @return java.lang.Integer
	 */
	public Integer getRuleId() {
		return this.ruleId;
	}
	
	/**
	 * 设置对应的评价规则  aps_rule.id
	 * @param ruleId
	 * @type java.lang.Integer
	 */
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	
	/**
	 * 获取考核类型  XY-JY-JSKPLB 01=常规 02=加分 03=减分 09=其他
	 * @return java.lang.String
	 */
	public String getCheckType() {
		return this.checkType;
	}
	
	/**
	 * 设置考核类型  XY-JY-JSKPLB 01=常规 02=加分 03=减分 09=其他
	 * @param checkType
	 * @type java.lang.String
	 */
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
	/**
	 * 获取评价项目名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置评价项目名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取项目分类
	 * @return java.lang.String
	 */
	public String getCategory() {
		return this.category;
	}
	
	/**
	 * 设置项目分类
	 * @param category
	 * @type java.lang.String
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * 获取说明
	 * @return java.lang.String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 设置说明
	 * @param description
	 * @type java.lang.String
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取排列次序
	 * @return java.lang.Integer
	 */
	public Integer getListOrder() {
		return this.listOrder;
	}
	
	/**
	 * 设置排列次序
	 * @param listOrder
	 * @type java.lang.Integer
	 */
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	
	/**
	 * 获取标号，编号（可空）
	 * @return java.lang.String
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * 设置标号，编号（可空）
	 * @param code
	 * @type java.lang.String
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 获取本项得分（如果是扣分项用负值）
	 * @return java.lang.Float
	 */
	public Float getScore() {
		return this.score;
	}
	
	/**
	 * 设置本项得分（如果是扣分项用负值）
	 * @param score
	 * @type java.lang.Float
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	
	/**
	 * 获取本项所占权重（不需要置0）
	 * @return java.lang.Float
	 */
	public Float getScale() {
		return this.scale;
	}
	
	/**
	 * 设置本项所占权重（不需要置0）
	 * @param scale
	 * @type java.lang.Float
	 */
	public void setScale(Float scale) {
		this.scale = scale;
	}
	
	/**
	 * 获取删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标志
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