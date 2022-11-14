package platform.szxyzxx.web.sys.vo;

public class ExtAppReleaseVo {
	private String name;
	private String version;
	private String releaseDate;
	private String osVersion;
	private String trademark;
	private String manufacturer;
	private String copyright;
	private String setupFile;
	private String packageFile;
	private String qrCodeFile;
	private String description;
	private Boolean isForce;
	private String packageSize;
	/**
	 * 文件精确大小
	 */
	private Integer exactSize;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getTrademark() {
		return trademark;
	}
	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getSetupFile() {
		return setupFile;
	}
	public void setSetupFile(String setupFile) {
		this.setupFile = setupFile;
	}
	public String getPackageFile() {
		return packageFile;
	}
	public void setPackageFile(String packageFile) {
		this.packageFile = packageFile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Boolean getIsForce() {
		return isForce;
	}
	public void setIsForce(Boolean isForce) {
		this.isForce = isForce;
	}
	public String getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}
	public String getQrCodeFile() {
		return qrCodeFile;
	}
	public void setQrCodeFile(String qrCodeFile) {
		this.qrCodeFile = qrCodeFile;
	}
	public Integer getExactSize() {
		return exactSize;
	}
	public void setExactSize(Integer exactSize) {
		this.exactSize = exactSize;
	}
}
