package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ResearchProject
 * @author AutoCreate
 *
 */
public class ResearchProject implements Model<Integer> {
	
	
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
	 * 立项级别
	 */
	private String level;
	/**
	 * 进展级别
	 */
	private String progressLevel;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * masterId
	 */
	private String masterId;
	/**
	 * 负责人
	 */
	private String masterName;
	/**
	 * 参与人员的名字
	 */
	private String attendeesName;
	/**
	 * 组织者的id
	 */
	private String attendeesId;
	/**
	 * 开始时间
	 */
	private java.util.Date beginDate;
	/**
	 * 结束时间
	 */
	private java.util.Date endDate;
	/**
	 * prize
	 */
	private String prize;
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
	
	public ResearchProject() {
		
	}
	
	public ResearchProject(Integer id) {
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
	 * 获取立项级别
	 * @return java.lang.String
	 */
	public String getLevel() {
		return this.level;
	}
	
	/**
	 * 设置立项级别
	 * @param level
	 * @type java.lang.String
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * 获取进展级别
	 * @return java.lang.String
	 */
	public String getProgressLevel() {
		return this.progressLevel;
	}
	
	/**
	 * 设置进展级别
	 * @param progressLevel
	 * @type java.lang.String
	 */
	public void setProgressLevel(String progressLevel) {
		this.progressLevel = progressLevel;
	}
	
	/**
	 * 获取编号
	 * @return java.lang.String
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * 设置编号
	 * @param code
	 * @type java.lang.String
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * 获取masterId
	 * @return java.lang.String
	 */
	public String getMasterId() {
		return this.masterId;
	}
	
	/**
	 * 设置masterId
	 * @param masterId
	 * @type java.lang.String
	 */
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
	/**
	 * 获取负责人
	 * @return java.lang.String
	 */
	public String getMasterName() {
		return this.masterName;
	}
	
	/**
	 * 设置负责人
	 * @param masterName
	 * @type java.lang.String
	 */
	public void setMasterName(String masterName) {
		this.masterName = masterName;
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
	 * 获取开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getBeginDate() {
		return this.beginDate;
	}
	
	/**
	 * 设置开始时间
	 * @param beginDate
	 * @type java.util.Date
	 */
	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
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
	
	/**
	 * 获取prize
	 * @return java.lang.String
	 */
	public String getPrize() {
		return this.prize;
	}
	
	/**
	 * 设置prize
	 * @param prize
	 * @type java.lang.String
	 */
	public void setPrize(String prize) {
		this.prize = prize;
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