package platform.szxyzxx.web.im.client.vo;

public class AppAccountVo {
	private static final long serialVersionUID = 1L;
	
	private String imType;
	
	private String appId;
	
	private String ownerName;
	
	private String accountName;

	public String getImType() {
		return imType;
	}

	public void setImType(String imType) {
		this.imType = imType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
}
