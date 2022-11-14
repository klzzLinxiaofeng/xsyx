package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjUserAction
 * @author AutoCreate
 *
 */
public class PjUserAction implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * appId
	 */
	private Integer appId;
	/**
	 * schoolId
	 */
	private Integer schoolId;
	/**
	 * schoolYear
	 */
	private String schoolYear;
	/**
	 * termCode
	 */
	private String termCode;
	/**
	 * subjectCode
	 */
	private String subjectCode;
	/**
	 * teamId
	 */
	private Integer teamId;
	/**
	 * userId
	 */
	private Integer userId;
	/**
	 * objectUuid
	 */
	private Integer objectUuid;
	/**
	 * objectType
	 */
	private String objectType;
	/**
	 * tag
	 */
	private String tag;
	/**
	 * type
	 */
	private String type;
	/**
	 * value
	 */
	private String value;
	/**
	 * uuid
	 */
	private String uuid;
	/**
	 * document
	 */
	private String document;
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
	
	public PjUserAction() {
		
	}
	
	public PjUserAction(Integer id) {
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
	 * 获取appId
	 * @return java.lang.Integer
	 */
	public Integer getAppId() {
		return this.appId;
	}
	
	/**
	 * 设置appId
	 * @param appId
	 * @type java.lang.Integer
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	/**
	 * 获取schoolId
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置schoolId
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取schoolYear
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置schoolYear
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取termCode
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置termCode
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	
	/**
	 * 获取subjectCode
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置subjectCode
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取teamId
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置teamId
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取userId
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置userId
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取objectUuid
	 * @return java.lang.Integer
	 */
	public Integer getObjectUuid() {
		return this.objectUuid;
	}
	
	/**
	 * 设置objectUuid
	 * @param objectUuid
	 * @type java.lang.Integer
	 */
	public void setObjectUuid(Integer objectUuid) {
		this.objectUuid = objectUuid;
	}
	
	/**
	 * 获取objectType
	 * @return java.lang.String
	 */
	public String getObjectType() {
		return this.objectType;
	}
	
	/**
	 * 设置objectType
	 * @param objectType
	 * @type java.lang.String
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	/**
	 * 获取tag
	 * @return java.lang.String
	 */
	public String getTag() {
		return this.tag;
	}
	
	/**
	 * 设置tag
	 * @param tag
	 * @type java.lang.String
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * 获取type
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置type
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取value
	 * @return java.lang.String
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * 设置value
	 * @param value
	 * @type java.lang.String
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 获取uuid
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置uuid
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取document
	 * @return java.lang.String
	 */
	public String getDocument() {
		return this.document;
	}
	
	/**
	 * 设置document
	 * @param document
	 * @type java.lang.String
	 */
	public void setDocument(String document) {
		this.document = document;
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