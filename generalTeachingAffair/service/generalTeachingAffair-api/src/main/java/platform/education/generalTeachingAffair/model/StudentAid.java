package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * 学生资助entity
 * @author AutoCreate
 *
 */
public class StudentAid implements Model<Integer> {


	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 所在班级ID
	 */
	private Integer teamId;
	/**
	 * 学生ID
	 */
	private Integer studentId;
	/**
	 * 贫困类别
	 */
	private String povertyCategory;
	/**
	 * 贫困原因
	 */
	private String povertyCauses;
	/**
	 * 助困形式
	 */
	private String aidForm;
	/**
	 * 家庭收入/人口
	 */
	private String oneIncome;
	/**
	 * 资助金额
	 */
	private String aidAmount;
	/**
	 * 资助日期
	 */
	private java.util.Date aidDay;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 删除标示
	 */
	private boolean isDelete;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;

	public StudentAid() {

	}

	public StudentAid(Integer id) {
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
	 * 获取所在班级ID
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}

	/**
	 * 设置所在班级ID
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	/**
	 * 获取学生ID
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}

	/**
	 * 设置学生ID
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * 获取贫困类别
	 * @return java.lang.String
	 */
	public String getPovertyCategory() {
		return this.povertyCategory;
	}

	/**
	 * 设置贫困类别
	 * @param povertyCategory
	 * @type java.lang.String
	 */
	public void setPovertyCategory(String povertyCategory) {
		this.povertyCategory = povertyCategory;
	}

	/**
	 * 获取贫困原因
	 * @return java.lang.String
	 */
	public String getPovertyCauses() {
		return this.povertyCauses;
	}

	/**
	 * 设置贫困原因
	 * @param povertyCauses
	 * @type java.lang.String
	 */
	public void setPovertyCauses(String povertyCauses) {
		this.povertyCauses = povertyCauses;
	}

	/**
	 * 获取助困形式
	 * @return java.lang.String
	 */
	public String getAidForm() {
		return this.aidForm;
	}

	/**
	 * 设置助困形式
	 * @param aidForm
	 * @type java.lang.String
	 */
	public void setAidForm(String aidForm) {
		this.aidForm = aidForm;
	}

	/**
	 * 获取家庭收入/人口
	 * @return Long
	 */
	public String getOneIncome() {
		return this.oneIncome;
	}

	/**
	 * 设置家庭收入/人口
	 * @param oneIncome
	 * @type Long
	 */
	public void setOneIncome(String oneIncome) {
		this.oneIncome = oneIncome;
	}

	/**
	 * 获取资助金额
	 * @return Long
	 */
	public String getAidAmount() {
		return this.aidAmount;
	}

	/**
	 * 设置资助金额
	 * @param aidAmount
	 * @type Long
	 */
	public void setAidAmount(String aidAmount) {
		this.aidAmount = aidAmount;
	}

	/**
	 * 获取资助日期
	 * @return java.util.Date
	 */
	public java.util.Date getAidDay() {
		return this.aidDay;
	}

	/**
	 * 设置资助日期
	 * @param aidDay
	 * @type java.util.Date
	 */
	public void setAidDay(java.util.Date aidDay) {
		this.aidDay = aidDay;
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
	 * 获取删除标志
	 * @return java.lang.Integer
	 */
	public boolean getIsDelete() {
		return isDelete;
	}

	/**
	 * 设置获取删除标志
	 * @param isDelete
	 * @type java.lang.Integer
	 */
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
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