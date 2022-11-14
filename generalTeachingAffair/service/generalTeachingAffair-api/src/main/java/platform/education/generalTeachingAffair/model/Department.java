package platform.education.generalTeachingAffair.model;

import java.util.List;

import framework.generic.dao.Model;

/**
 * 部门表 Department
 * 
 * @author zhoujin
 *
 */
public class Department implements Model<Integer> {

	private static final long serialVersionUID = 1L;
	/**
	 * 主健ID
	 */
	private Integer id;
	/**
	 * 所属学校
	 */
	private Integer schoolId;
	/**
	 * 父部门
	 */
	private Integer parentId;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 编号，排序次序
	 */
	private Integer listOrder;
	/**
	 * 部门人数
	 */
	private Integer memberCount;

	/**
	 * 是否删除 用于逻辑删除
	 */
	private Boolean isDelete;
	/**
	 * 父机构
	 */
	private Department parent;
	/**
	 * 子机构
	 */
	private List<Department> childrens;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 等级
	 */
	private Integer level;

	/**
	 * 多个部门id
	 */
	private List<String> listId;

	public List<String> getListId() {
		return listId;
	}

	public void setListId(List<String> listId) {
		this.listId = listId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Department() {

	}

	public Department(Integer id) {
		this.id = id;
	}

	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主健ID
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置主健ID
	 * 
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取所属学校
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}

	/**
	 * 设置所属学校
	 * 
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * 获取父部门
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getParentId() {
		return this.parentId;
	}

	/**
	 * @Method getName
	 * @Function 功能描述：获取部门名称
	 * @return
	 * @Date 2015年3月26日
	 */
	public String getName() {
		return name;
	}

	/**
	 * @Method setName
	 * @Function 功能描述：设置部门名称
	 * @param name
	 * @Date 2015年3月26日
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置父部门
	 * 
	 * @param parentId
	 * @type java.lang.Integer
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取编号，排序次序
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getListOrder() {
		return this.listOrder;
	}

	/**
	 * 设置编号，排序次序
	 * 
	 * @param listOrder
	 * @type java.lang.Integer
	 */
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	/**
	 * 获取部门人数
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getMemberCount() {
		return this.memberCount;
	}

	/**
	 * 设置部门人数
	 * 
	 * @param memberCount
	 * @type java.lang.Integer
	 */
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @Method getParent
	 * @Function 功能描述：获取父机构
	 * @return
	 * @Date 2015年3月26日
	 */
	public Department getParent() {
		return parent;
	}

	/**
	 * @Method setParent
	 * @Function 功能描述：设置父机构
	 * @param parent
	 * @Date 2015年3月26日
	 */
	public void setParent(Department parent) {
		this.parent = parent;
	}

	/**
	 * @Method getChildrens
	 * @Function 功能描述：获取子机构
	 * @return
	 * @Date 2015年3月26日
	 */
	public List<Department> getChildrens() {
		return childrens;
	}

	/**
	 * @Method setChildrens
	 * @Function 功能描述：设置子机构
	 * @param childrens
	 * @Date 2015年3月26日
	 */
	public void setChildrens(List<Department> childrens) {
		this.childrens = childrens;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

	/**
	 * 设置修改时间
	 * 
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", schoolId=" + schoolId
				+ ", parentId=" + parentId + ", name=" + name + ", listOrder="
				+ listOrder + ", memberCount=" + memberCount + ", parent="
				+ parent + ", childrens=" + childrens + "]";
	}

}