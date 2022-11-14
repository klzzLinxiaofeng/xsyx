package platform.szxyzxx.web.bbx.client.vo;

/**
 * 屏幕权限 客户端实体
 * 2015-12-25
 * @author huangyanchun
 *
 */
public class ScreenPermissionClientVo {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 操作权限方式
	 */
	private String accessType;
	
	/**
	 * 功能操作密码
	 */
	private String accessCode;
	


	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}


	
	

}
