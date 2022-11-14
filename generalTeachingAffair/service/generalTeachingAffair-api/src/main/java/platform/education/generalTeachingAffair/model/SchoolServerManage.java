package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * SchoolServerManage
 * @author AutoCreate
 *
 */
public class SchoolServerManage implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * name
	 */
	private String name;
	/**
	 * schoolId
	 */
	private Integer schoolId;
	/**
	 * schoolName
	 */
	private String schoolName;
	/**
	 * 服务器地址 json格式 { code : 001,   //学校编码   name : 迅云，//学校名称  api_server : https://www.studyo.cn , //服务器地址 edu_sever:  https://www.studyo.cn}
	 */
	private String serverAddress;
	/**
	 * 删除标识
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public SchoolServerManage() {
		
	}
	
	public SchoolServerManage(Integer id) {
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
	 * 获取name
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置name
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 获取schoolName
	 * @return java.lang.String
	 */
	public String getSchoolName() {
		return this.schoolName;
	}
	
	/**
	 * 设置schoolName
	 * @param schoolName
	 * @type java.lang.String
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	/**
	 * 获取服务器地址 json格式 { code : 001,   //学校编码   name : 迅云，//学校名称  api_server : https://www.studyo.cn , //服务器地址 edu_sever:  https://www.studyo.cn}
	 * @return java.lang.String
	 */
	public String getServerAddress() {
		return this.serverAddress;
	}
	
	/**
	 * 设置服务器地址 json格式 { code : 001,   //学校编码   name : 迅云，//学校名称  api_server : https://www.studyo.cn , //服务器地址 edu_sever:  https://www.studyo.cn}
	 * @param serverAddress
	 * @type java.lang.String
	 */
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	
	/**
	 * 获取删除标识
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标识
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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