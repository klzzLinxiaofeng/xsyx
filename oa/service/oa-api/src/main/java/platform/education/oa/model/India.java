package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * India
 * @author AutoCreate
 *
 */
public class India implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 所属学校id
	 */
	private Integer schoolId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 用户姓名(冗余)
	 */
	private String userName;
	/**
	 * 所属部门id
	 */
	private Integer departmentId;
	/**
	 * 文印标题
	 */
	private String title;
	/**
	 * 上传的文件路径
	 */
	private String uploadFile;
	/**
	 * 打印状态 0：待处理； 1：进行中；2：已完成
	 */
	private String indiaStatus;
	/**
	 * 说明(附注)
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public India() {
		
	}
	
	public India(Integer id) {
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
	 * 获取所属学校id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所属学校id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取用户id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取用户姓名(冗余)
	 * @return java.lang.String
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * 设置用户姓名(冗余)
	 * @param userName
	 * @type java.lang.String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取所属部门id
	 * @return java.lang.Integer
	 */
	public Integer getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * 设置所属部门id
	 * @param departmentId
	 * @type java.lang.Integer
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * 获取文印标题
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置文印标题
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取上传的文件路径
	 * @return java.lang.String
	 */
	public String getUploadFile() {
		return this.uploadFile;
	}
	
	/**
	 * 设置上传的文件路径
	 * @param uploadFile
	 * @type java.lang.String
	 */
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	/**
	 * 获取打印状态 0：待处理； 1：进行中；2：已完成
	 * @return java.lang.String
	 */
	public String getIndiaStatus() {
		return this.indiaStatus;
	}
	
	/**
	 * 设置打印状态 0：待处理； 1：进行中；2：已完成
	 * @param indiaStatus
	 * @type java.lang.String
	 */
	public void setIndiaStatus(String indiaStatus) {
		this.indiaStatus = indiaStatus;
	}
	
	/**
	 * 获取说明(附注)
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置说明(附注)
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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