package platform.education.hnzxx.ui.model;

import framework.generic.dao.Model;
/**
 * PjPermission
 * @author AutoCreate
 *
 */
public class PjPermission implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * pk
	 */
	private Integer id;
	/**
	 * schoolId
	 */
	private Integer schoolId;
	/**
	 * 此权限所属app
	 */
	private Integer appId;
	/**
	 * 父权限资源
	 */
	private String parentCode;
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 资源入口url
	 */
	private String accessUrl;
	/**
	 * 树层次
	 */
	private Integer level;
	/**
	 * 排序号
	 */
	private Integer listOrder;
	/**
	 * 说明
	 */
	private String description;
	/**
	 * 转态：0：启用；1：禁用
	 */
	private String state;
	/**
	 * 资源权限对应图标标识
	 */
	private String icon;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	/**
	 * type
	 */
	private String type;
	
	public PjPermission() {
		
	}
	
	public PjPermission(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取pk
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置pk
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * 获取此权限所属app
	 * @return java.lang.Integer
	 */
	public Integer getAppId() {
		return this.appId;
	}
	
	/**
	 * 设置此权限所属app
	 * @param appId
	 * @type java.lang.Integer
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	/**
	 * 获取父权限资源
	 * @return java.lang.String
	 */
	public String getParentCode() {
		return this.parentCode;
	}
	
	/**
	 * 设置父权限资源
	 * @param parentCode
	 * @type java.lang.String
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	/**
	 * 获取代码
	 * @return java.lang.String
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * 设置代码
	 * @param code
	 * @type java.lang.String
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * 获取资源入口url
	 * @return java.lang.String
	 */
	public String getAccessUrl() {
		return this.accessUrl;
	}
	
	/**
	 * 设置资源入口url
	 * @param accessUrl
	 * @type java.lang.String
	 */
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
	
	/**
	 * 获取树层次
	 * @return java.lang.Integer
	 */
	public Integer getLevel() {
		return this.level;
	}
	
	/**
	 * 设置树层次
	 * @param level
	 * @type java.lang.Integer
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	/**
	 * 获取排序号
	 * @return java.lang.Integer
	 */
	public Integer getListOrder() {
		return this.listOrder;
	}
	
	/**
	 * 设置排序号
	 * @param listOrder
	 * @type java.lang.Integer
	 */
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	
	/**
	 * 获取说明
	 * @return java.lang.String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 设置说明
	 * @param description
	 * @type java.lang.String
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取转态：0：启用；1：禁用
	 * @return java.lang.String
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * 设置转态：0：启用；1：禁用
	 * @param state
	 * @type java.lang.String
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 获取资源权限对应图标标识
	 * @return java.lang.String
	 */
	public String getIcon() {
		return this.icon;
	}
	
	/**
	 * 设置资源权限对应图标标识
	 * @param icon
	 * @type java.lang.String
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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
	
}