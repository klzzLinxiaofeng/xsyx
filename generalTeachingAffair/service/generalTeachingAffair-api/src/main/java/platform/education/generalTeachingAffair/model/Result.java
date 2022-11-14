package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * Result
 * @author AutoCreate
 *
 */
public class Result implements Model<Integer> {
	
	
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
	 * 教师的名字
	 */
	private String teachName;
	/**
	 * 教师的id
	 */
	private String teachId;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 成果类型
	 */
	private String type;
	/**
	 * 成果级别
	 */
	private String level;
	/**
	 * rank
	 */
	private String rank;
	/**
	 * 刊物类型
	 */
	private String publication;
	/**
	 * 申请时间
	 */
	private java.util.Date applyDate;
	/**
	 * 申请学分
	 */
	private Long applyScore;
	/**
	 * 核定学分
	 */
	private Long checkScore;
	/**
	 * 部署人数
	 */
	private Integer personNum;
	/**
	 * 是否独立完成
	 */
	private Boolean independent;
	/**
	 * 多个文件的id
	 */
	private String fileUuid;
	/**
	 * 审核是否通过
	 */
	private Integer audit;
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
	
	public Result() {
		
	}
	
	public Result(Integer id) {
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
	 * 获取教师的名字
	 * @return java.lang.String
	 */
	public String getTeachName() {
		return this.teachName;
	}
	
	/**
	 * 设置教师的名字
	 * @param teachName
	 * @type java.lang.String
	 */
	public void setTeachName(String teachName) {
		this.teachName = teachName;
	}
	
	/**
	 * 获取教师的id
	 * @return java.lang.String
	 */
	public String getTeachId() {
		return this.teachId;
	}
	
	/**
	 * 设置教师的id
	 * @param teachId
	 * @type java.lang.String
	 */
	public void setTeachId(String teachId) {
		this.teachId = teachId;
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
	 * 获取成果类型
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置成果类型
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取成果级别
	 * @return java.lang.String
	 */
	public String getLevel() {
		return this.level;
	}
	
	/**
	 * 设置成果级别
	 * @param level
	 * @type java.lang.String
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * 获取rank
	 * @return java.lang.String
	 */
	public String getRank() {
		return this.rank;
	}
	
	/**
	 * 设置rank
	 * @param rank
	 * @type java.lang.String
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	/**
	 * 获取刊物类型
	 * @return java.lang.String
	 */
	public String getPublication() {
		return this.publication;
	}
	
	/**
	 * 设置刊物类型
	 * @param publication
	 * @type java.lang.String
	 */
	public void setPublication(String publication) {
		this.publication = publication;
	}
	
	/**
	 * 获取申请时间
	 * @return java.util.Date
	 */
	public java.util.Date getApplyDate() {
		return this.applyDate;
	}
	
	/**
	 * 设置申请时间
	 * @param applyDate
	 * @type java.util.Date
	 */
	public void setApplyDate(java.util.Date applyDate) {
		this.applyDate = applyDate;
	}
	
	/**
	 * 获取申请学分
	 * @return Long
	 */
	public Long getApplyScore() {
		return this.applyScore;
	}
	
	/**
	 * 设置申请学分
	 * @param applyScore
	 * @type Long
	 */
	public void setApplyScore(Long applyScore) {
		this.applyScore = applyScore;
	}
	
	/**
	 * 获取核定学分
	 * @return Long
	 */
	public Long getCheckScore() {
		return this.checkScore;
	}
	
	/**
	 * 设置核定学分
	 * @param checkScore
	 * @type Long
	 */
	public void setCheckScore(Long checkScore) {
		this.checkScore = checkScore;
	}
	
	/**
	 * 获取部署人数
	 * @return java.lang.Integer
	 */
	public Integer getPersonNum() {
		return this.personNum;
	}
	
	/**
	 * 设置部署人数
	 * @param personNum
	 * @type java.lang.Integer
	 */
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
	
	/**
	 * 获取是否独立完成
	 * @return java.lang.Boolean
	 */
	public Boolean getIndependent() {
		return this.independent;
	}
	
	/**
	 * 设置是否独立完成
	 * @param independent
	 * @type java.lang.Boolean
	 */
	public void setIndependent(Boolean independent) {
		this.independent = independent;
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
	 * 获取审核是否通过
	 * @return java.lang.Boolean
	 */
	public Integer getAudit() {
		return this.audit;
	}
	
	/**
	 * 设置审核是否通过
	 * @param audit
	 * @type java.lang.Boolean
	 */
	public void setAudit(Integer audit) {
		this.audit = audit;
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