package platform.szxyzxx.web.im.client.vo;

public class AppAccount {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String imType;
	
	private String appKey;
	
	private Integer userId;
	
	private String ownerId;
	
	private String accountName;
	
	private String password;
	
	private String ownerName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImType() {
		return imType;
	}

	public void setImType(String imType) {
		this.imType = imType;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
}
