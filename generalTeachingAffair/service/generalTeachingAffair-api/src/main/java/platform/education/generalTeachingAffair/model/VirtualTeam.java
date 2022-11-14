package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * VirtualTeam
 * @author AutoCreate
 *
 */
public class VirtualTeam implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 所属学校
	 */
	private Integer schoolId;
	/**
	 * 学年的第一部分（冗余）
	 */
	private String schoolYear;
	/**
	 * 所属年级
	 */
	private Integer gradeId;
	/**
	 * 班级名称
	 */
	private String name;
	/**
	 * 班号：在同一年级中的顺序编号
	 */
	private Integer teamNumber;
	/**
	 * 班级成员数
	 */
	private Integer memberCount;
	/**
	 * 班级UUID
	 */
	private String uuid;
	/**
	 * 班级变更状态  null=未变更 1=已升级 2=已迁出 3=已毕业
	 */
	private Integer alteredStatus;
	/**
	 * 删除标识
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public VirtualTeam() {
		
	}
	
	public VirtualTeam(Integer id) {
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
	 * 获取所属学校
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所属学校
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取学年的第一部分（冗余）
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年的第一部分（冗余）
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取所属年级
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置所属年级
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取班级名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置班级名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取班号：在同一年级中的顺序编号
	 * @return java.lang.Integer
	 */
	public Integer getTeamNumber() {
		return this.teamNumber;
	}
	
	/**
	 * 设置班号：在同一年级中的顺序编号
	 * @param teamNumber
	 * @type java.lang.Integer
	 */
	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}
	
	/**
	 * 获取班级成员数
	 * @return Integer
	 */
	public Integer getMemberCount() {
		return this.memberCount;
	}
	
	/**
	 * 设置班级成员数
	 * @param memberCount
	 * @type Integer
	 */
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}
	
	/**
	 * 获取班级UUID
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置班级UUID
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取班级变更状态  null=未变更 1=已升级 2=已迁出 3=已毕业
	 * @return java.lang.Integer
	 */
	public Integer getAlteredStatus() {
		return this.alteredStatus;
	}
	
	/**
	 * 设置班级变更状态  null=未变更 1=已升级 2=已迁出 3=已毕业
	 * @param alteredStatus
	 * @type java.lang.Integer
	 */
	public void setAlteredStatus(Integer alteredStatus) {
		this.alteredStatus = alteredStatus;
	}
	
	/**
	 * 获取删除标识
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标识
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