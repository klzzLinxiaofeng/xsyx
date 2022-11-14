package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * AcademicExchange
 * @author AutoCreate
 *
 */
public class AcademicExchange implements Model<Integer> {
	
	
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
	 * 交流类型
	 */
	private String type;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 开始时间
	 */
	private java.util.Date startDate;
	/**
	 * 结束时间
	 */
	private java.util.Date endDate;
	/**
	 * 校外参与人员
	 */
	private String outsiders;
	/**
	 * 参与人员的名字
	 */
	private String attendeesName;
	/**
	 * 组织者的id
	 */
	private String attendeesId;
	/**
	 * 多个文件的id
	 */
	private String fileUuid;
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
	
	public AcademicExchange() {
		
	}
	
	public AcademicExchange(Integer id) {
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
	 * 获取交流类型
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置交流类型
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取名字
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置名字
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * 设置开始时间
	 * @param startDate
	 * @type java.util.Date
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 获取结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * 设置结束时间
	 * @param endDate
	 * @type java.util.Date
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	public String getOutsiders() {
		return outsiders;
	}

	public void setOutsiders(String outsiders) {
		this.outsiders = outsiders;
	}

	/**
	 * 获取参与人员的名字
	 * @return java.lang.String
	 */
	public String getAttendeesName() {
		return this.attendeesName;
	}
	
	/**
	 * 设置参与人员的名字
	 * @param attendeesName
	 * @type java.lang.String
	 */
	public void setAttendeesName(String attendeesName) {
		this.attendeesName = attendeesName;
	}
	
	/**
	 * 获取组织者的id
	 * @return java.lang.String
	 */
	public String getAttendeesId() {
		return this.attendeesId;
	}
	
	/**
	 * 设置组织者的id
	 * @param attendeesId
	 * @type java.lang.String
	 */
	public void setAttendeesId(String attendeesId) {
		this.attendeesId = attendeesId;
	}
	
	/**
	 * 获取多个文件的id
	 * @return java.lang.String
	 */
	public String getFileUuid() {
		return this.fileUuid;
	}
	
	/**
	 * 设置多个文件的id
	 * @param fileUuid
	 * @type java.lang.String
	 */
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
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