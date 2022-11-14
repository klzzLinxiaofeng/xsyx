package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * PjGroup
 * @author AutoCreate
 *
 */
public class PjGroup implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 所属的学校，外键，school.id
	 */
	private Integer schoolId;
	/**
	 * 所属年级，外键，grade.id
	 */
	private Integer gradeId;
	/**
	 * 所属班级，可以为空，班级分组有值，外键，team.id
	 */
	private Integer teamId;
	/**
	 * 创建或负责的教师ID外键，teacher.id
	 */
	private Integer teacherId;
	/**
	 * 分组名称
	 */
	private String name;
	/**
	 * 分组类型 XY-JY-FZLX  1=班级分组 2=年级分组 3=自由分组
	 */
	private Integer groupType;
	/**
	 * 分组总数
	 */
	private Integer groupCount;
	/**
	 * 是否默认分组方案，默认为false
	 */
	private Boolean isDefault;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	/**
	 * isDeleted
	 */
	private Boolean isDeleted;
	
	public PjGroup() {
		
	}
	
	public PjGroup(Integer id) {
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
	 * 获取所属的学校，外键，school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所属的学校，外键，school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取所属年级，外键，grade.id
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置所属年级，外键，grade.id
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取所属班级，可以为空，班级分组有值，外键，team.id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置所属班级，可以为空，班级分组有值，外键，team.id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取创建或负责的教师ID外键，teacher.id
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置创建或负责的教师ID外键，teacher.id
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取分组名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置分组名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取分组类型 XY-JY-FZLX  1=班级分组 2=年级分组 3=自由分组
	 * @return java.lang.Integer
	 */
	public Integer getGroupType() {
		return this.groupType;
	}
	
	/**
	 * 设置分组类型 XY-JY-FZLX  1=班级分组 2=年级分组 3=自由分组
	 * @param groupType
	 * @type java.lang.Integer
	 */
	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
	
	/**
	 * 获取分组总数
	 * @return java.lang.Integer
	 */
	public Integer getGroupCount() {
		return this.groupCount;
	}
	
	/**
	 * 设置分组总数
	 * @param groupCount
	 * @type java.lang.Integer
	 */
	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}
	
	/**
	 * 获取是否默认分组方案，默认为false
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDefault() {
		return this.isDefault;
	}
	
	/**
	 * 设置是否默认分组方案，默认为false
	 * @param isDefault
	 * @type java.lang.Boolean
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
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
	 * 获取isDeleted
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置isDeleted
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}