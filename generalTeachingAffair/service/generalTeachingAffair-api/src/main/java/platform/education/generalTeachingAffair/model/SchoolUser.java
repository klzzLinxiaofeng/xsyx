package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * SchoolUser
 * @author AutoCreate
 *
 */
public class SchoolUser implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 所在学校
	 */
	private Integer schoolId;
	/**
	 * 用户帐号
	 */
	private Integer userId;
	/**
	 * 档案记录ID
	 */
	private Integer ownerId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 用户类型：1=教师，2=学生，3=家长
	 */
	private String userType;
	
	/*  添加于2015-11-16   */
	/**
	 * 个人档案记录ID
	 */
	private Integer personId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 姓名或别名
	 */
	private String alias;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 在校用户标识
	 */
	private Boolean inSchool;
	/**
	 * 作废标识
	 */
	private Boolean isDeleted;
	/*  添加于2015-11-16   */
	
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	public SchoolUser() {
		
	}
	
	public SchoolUser(Integer id) {
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
	 * 获取所在学校
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所在学校
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取用户帐号
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户帐号
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取档案记录ID
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置档案记录ID
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取姓名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置姓名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取用户类型：1=教师，2=学生，3=家长
	 * @return java.lang.Integer
	 */
	public String getUserType() {
		return this.userType;
	}
	
	/**
	 * 设置用户类型：1=教职工，2=管理员，3=家长,4=学生
	 * @param userType
	 * @type java.lang.Integer
	 */
	public void setUserType(String userType) {
		this.userType = userType;
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

	/*  添加于2015-11-16   */
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Boolean getInSchool() {
		return inSchool;
	}

	public void setInSchool(Boolean inSchool) {
		this.inSchool = inSchool;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/*  添加于2015-11-16   */
	
}