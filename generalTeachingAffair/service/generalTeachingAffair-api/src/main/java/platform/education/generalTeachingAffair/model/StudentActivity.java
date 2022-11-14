package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * StudentActivity
 * @author AutoCreate
 *
 */
public class StudentActivity implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 学校id
	 */
	private Integer schoolId;
	/**
	 * 学年
	 */
	private String schoolYear;
	/**
	 * 学期
	 */
	private String termCode;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 实践时间
	 */
	private java.util.Date practiceDate;
	/**
	 * position
	 */
	private String position;
	/**
	 * 组织者名字
	 */
	private String masterName;
	/**
	 * 组织者的id
	 */
	private String masterId;
	/**
	 * 参与者名字
	 */
	private String teachName;
	/**
	 * 参与者的id
	 */
	private String teachId;
	/**
	 * 参与者学生的名字
	 */
	private String studentName;
	/**
	 * 参与者的id
	 */
	private String studentId;
	/**
	 * file.id
	 */
	private String fileUuid;
	/**
	 * 是否删除
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
	
	public StudentActivity() {
		
	}
	
	public StudentActivity(Integer id) {
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
	 * 获取学年
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
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
	 * 获取名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取实践时间
	 * @return java.util.Date
	 */
	public java.util.Date getPracticeDate() {
		return this.practiceDate;
	}
	
	/**
	 * 设置实践时间
	 * @param practiceDate
	 * @type java.util.Date
	 */
	public void setPracticeDate(java.util.Date practiceDate) {
		this.practiceDate = practiceDate;
	}
	
	/**
	 * 获取position
	 * @return java.lang.String
	 */
	public String getPosition() {
		return this.position;
	}
	
	/**
	 * 设置position
	 * @param position
	 * @type java.lang.String
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 获取组织者名字
	 * @return java.lang.String
	 */
	public String getMasterName() {
		return this.masterName;
	}
	
	/**
	 * 设置组织者名字
	 * @param masterName
	 * @type java.lang.String
	 */
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	
	/**
	 * 获取组织者的id
	 * @return java.lang.String
	 */
	public String getMasterId() {
		return this.masterId;
	}
	
	/**
	 * 设置组织者的id
	 * @param masterId
	 * @type java.lang.String
	 */
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
	/**
	 * 获取参与者名字
	 * @return java.lang.String
	 */
	public String getTeachName() {
		return this.teachName;
	}
	
	/**
	 * 设置参与者名字
	 * @param teachName
	 * @type java.lang.String
	 */
	public void setTeachName(String teachName) {
		this.teachName = teachName;
	}
	
	/**
	 * 获取参与者的id
	 * @return java.lang.String
	 */
	public String getTeachId() {
		return this.teachId;
	}
	
	/**
	 * 设置参与者的id
	 * @param teachId
	 * @type java.lang.String
	 */
	public void setTeachId(String teachId) {
		this.teachId = teachId;
	}
	
	/**
	 * 获取参与者学生的名字
	 * @return java.lang.String
	 */
	public String getStudentName() {
		return this.studentName;
	}
	
	/**
	 * 设置参与者学生的名字
	 * @param studentName
	 * @type java.lang.String
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 获取参与者的id
	 * @return java.lang.String
	 */
	public String getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置参与者的id
	 * @param studentId
	 * @type java.lang.String
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取file.id
	 * @return java.lang.String
	 */
	public String getFileUuid() {
		return this.fileUuid;
	}
	
	/**
	 * 设置file.id
	 * @param fileUuid
	 * @type java.lang.String
	 */
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
	
	/**
	 * 获取是否删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置是否删除
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