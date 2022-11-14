package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * Charge
 * @author AutoCreate
 *
 */
public class Charge implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 收费项目id
	 */
	private Integer itemId;
	/**
	 * 学校id
	 */
	private Integer schoolId;
	/**
	 * 学期
	 */
	private String termCode;
	/**
	 * 年级id
	 */
	private Integer gradeId;
	/**
	 * 班级id
	 */
	private Integer teamId;
	/**
	 * 学生id
	 */
	private Integer studentId;
	/**
	 * 是否缴费
	 */
	private Boolean isPay;
	/**
	 * 缴费金额
	 */
	private String amount;
	/**
	 * 是否删除
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
	
	public Charge() {
		
	}
	
	public Charge(Integer id) {
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
	 * 获取收费项目id
	 * @return java.lang.Integer
	 */
	public Integer getItemId() {
		return this.itemId;
	}
	
	/**
	 * 设置收费项目id
	 * @param itemId
	 * @type java.lang.Integer
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * 获取学校id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取学期
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置学期
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	/**
	 * 获取年级id
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return gradeId;
	}

	/**
	 * 设置年级id
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * 获取班级id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置班级id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取学生id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取是否缴费
	 * @return java.lang.Boolean
	 */
	public Boolean getIsPay() {
		return this.isPay;
	}
	
	/**
	 * 设置是否缴费
	 * @param isPay
	 * @type java.lang.Boolean
	 */
	public void setIsPay(Boolean isPay) {
		this.isPay = isPay;
	}
	
	/**
	 * 获取缴费金额
	 * @return java.lang.String
	 */
	public String getAmount() {
		return this.amount;
	}
	
	/**
	 * 设置缴费金额
	 * @param amount
	 * @type java.lang.String
	 */
	public void setAmount(String amount) {
		this.amount = amount;
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