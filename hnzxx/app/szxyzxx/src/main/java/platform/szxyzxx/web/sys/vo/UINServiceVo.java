package platform.szxyzxx.web.sys.vo;

public class UINServiceVo {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 开通服务类型
	 */
	private String serviceType;
	/**
	 * 账号类型
	 */
	private String accountType;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 是否开通成功(0:成功,1:失败)
	 */
	private String openResult;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 扩展字段
	 */
	private String ext;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 学校名称
	 */
	private String schoolName;
	/**
	 * 学校英文名称
	 */
	private String schoolEnglishName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOpenResult() {
		return openResult;
	}
	public void setOpenResult(String openResult) {
		this.openResult = openResult;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolEnglishName() {
		return schoolEnglishName;
	}
	public void setSchoolEnglishName(String schoolEnglishName) {
		this.schoolEnglishName = schoolEnglishName;
	}
	
}
