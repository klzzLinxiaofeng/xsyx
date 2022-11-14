package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsMoralEvaluationItem
 * @author AutoCreate
 *
 */
public class ApsMoralEvaluationItem implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * pj_school.id
	 */
	private Integer schoolId;
	/**
	 * 评价项目
	 */
	private String name;
	/**
	 * 评价项目分类
	 */
	private String classification;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 删除标记
	 */
	private Boolean isDelete;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	/**
	 * 1:德育课程管理,2:团队活动管理,3:社团管理,4专兼职心理健康教师管理
	 */
	private Integer type;
	/**
	 * 是否专职教师
	 */
	private Integer isFullTeach;
	/**
	 * 是否心理专业
	 */
	private Integer isMind;
	/**
	 * 时间
	 */
	private java.util.Date pubishDate;
	
	public ApsMoralEvaluationItem() {
		
	}
	
	public ApsMoralEvaluationItem(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键ID
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取pj_school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置pj_school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取评价项目
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置评价项目
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取评价项目分类
	 * @return java.lang.String
	 */
	public String getClassification() {
		return this.classification;
	}
	
	/**
	 * 设置评价项目分类
	 * @param classification
	 * @type java.lang.String
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	/**
	 * 获取备注
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置备注
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 获取删除标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标记
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	/**
	 * 获取创建日期
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建日期
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改日期
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改日期
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取1:德育课程管理,2:团队活动管理,3:社团管理,4专兼职心理健康教师管理
	 * @return java.lang.Integer
	 */
	public Integer getType() {
		return this.type;
	}
	
	/**
	 * 设置1:德育课程管理,2:团队活动管理,3:社团管理,4专兼职心理健康教师管理
	 * @param type
	 * @type java.lang.Integer
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 获取是否专职教师
	 * @return java.lang.Integer
	 */
	public Integer getIsFullTeach() {
		return this.isFullTeach;
	}
	
	/**
	 * 设置是否专职教师
	 * @param isFullTeach
	 * @type java.lang.Integer
	 */
	public void setIsFullTeach(Integer isFullTeach) {
		this.isFullTeach = isFullTeach;
	}
	
	/**
	 * 获取是否心理专业
	 * @return java.lang.Integer
	 */
	public Integer getIsMind() {
		return this.isMind;
	}
	
	/**
	 * 设置是否心理专业
	 * @param isMind
	 * @type java.lang.Integer
	 */
	public void setIsMind(Integer isMind) {
		this.isMind = isMind;
	}
	
	/**
	 * 获取时间
	 * @return java.util.Date
	 */
	public java.util.Date getPubishDate() {
		return this.pubishDate;
	}
	
	/**
	 * 设置时间
	 * @param pubishDate
	 * @type java.util.Date
	 */
	public void setPubishDate(java.util.Date pubishDate) {
		this.pubishDate = pubishDate;
	}
	
}