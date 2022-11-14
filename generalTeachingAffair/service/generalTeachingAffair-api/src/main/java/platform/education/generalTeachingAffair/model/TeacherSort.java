package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * TeacherSort
 * @author AutoCreate
 *
 */
public class TeacherSort implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 教师id
	 */
	private Integer teacherId;
	/**
	 * 教师名称
	 */
	private String teacherName;
	/**
	 * 学校id
	 */
	private Integer schoolId;
	/**
	 * 个人id
	 */
	private Integer persionId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 部门id
	 */
	private Integer departmentId;
	/**
	 * 目标排序类型
	 */
	private String type;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 部门排序
	 */
	private String departmentSort;
	/**
	 * 删除标志
	 */
	private Boolean isDelete;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public TeacherSort() {
		
	}
	
	public TeacherSort(Integer id) {
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
	 * 获取教师id
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置教师id
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取教师名称
	 * @return java.lang.String
	 */
	public String getTeacherName() {
		return this.teacherName;
	}
	
	/**
	 * 设置教师名称
	 * @param teacherName
	 * @type java.lang.String
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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
	 * 获取个人id
	 * @return java.lang.Integer
	 */
	public Integer getPersionId() {
		return this.persionId;
	}
	
	/**
	 * 设置个人id
	 * @param persionId
	 * @type java.lang.Integer
	 */
	public void setPersionId(Integer persionId) {
		this.persionId = persionId;
	}
	
	/**
	 * 获取用户id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取部门id
	 * @return java.lang.Integer
	 */
	public Integer getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * 设置部门id
	 * @param departmentId
	 * @type java.lang.Integer
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * 获取目标排序类型
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置目标排序类型
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取排序
	 * @return java.lang.Integer
	 */
	public Integer getSort() {
		return this.sort;
	}
	
	/**
	 * 设置排序
	 * @param sort
	 * @type java.lang.Integer
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * 获取部门排序
	 * @return java.lang.String
	 */
	public String getDepartmentSort() {
		return this.departmentSort;
	}
	
	/**
	 * 设置部门排序
	 * @param departmentSort
	 * @type java.lang.String
	 */
	public void setDepartmentSort(String departmentSort) {
		this.departmentSort = departmentSort;
	}
	
	/**
	 * 获取删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标志
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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