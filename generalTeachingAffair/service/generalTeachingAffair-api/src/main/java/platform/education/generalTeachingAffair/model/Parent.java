package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * Parent
 * @author AutoCreate
 *
 */
public class Parent implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主健ID
	 */
	private Integer id;
	/**
	 * 家长用户帐号ID yh_user.id
	 */
	private Integer userId;
	/**
	 * 用户姓名(冗余) yh_user.username
	 */
	private String userName;
	/**
	 * 家长档案记录 pj_person.id
	 */
	private Integer personId;
	/**
	 * 家长姓名
	 */
	private String name;
	/**
	 * 家长手机号
	 */
	private String mobile;
	/**
	 * 家长邮箱
	 */
	private String email;
	/**
	 * 是否删除，0为不删除，1为删除
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

	/**
	 * 学生名称
	 */
	private String stuName;
	/**
	 * 班级名称
	 */
	private String teamName;

	/**
	 * 家长车牌
	 */
	private String licensePlate;

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Parent() {
		
	}
	
	public Parent(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主健ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主健ID
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取家长用户帐号ID yh_user.id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置家长用户帐号ID yh_user.id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取用户姓名(冗余) yh_user.username
	 * @return java.lang.String
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * 设置用户姓名(冗余) yh_user.username
	 * @param userName
	 * @type java.lang.String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取家长档案记录 pj_person.id
	 * @return java.lang.Integer
	 */
	public Integer getPersonId() {
		return this.personId;
	}
	
	/**
	 * 设置家长档案记录 pj_person.id
	 * @param personId
	 * @type java.lang.Integer
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	/**
	 * 获取家长姓名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置家长姓名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取家长手机号
	 * @return java.lang.String
	 */
	public String getMobile() {
		return this.mobile;
	}
	
	/**
	 * 设置家长手机号
	 * @param mobile
	 * @type java.lang.String
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 获取家长邮箱
	 * @return java.lang.String
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * 设置家长邮箱
	 * @param email
	 * @type java.lang.String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 获取是否删除，0为不删除，1为删除
	 * @return java.lang.Boolean
	 */
	public Boolean isIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置是否删除，0为不删除，1为删除
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