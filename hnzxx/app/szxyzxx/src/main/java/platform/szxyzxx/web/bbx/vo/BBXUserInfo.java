package platform.szxyzxx.web.bbx.vo;

import java.util.List;

import platform.szxyzxx.web.common.vo.UserInfo;

public class BBXUserInfo extends UserInfo {

	private static final long serialVersionUID = -8810026069871496657L;

	/**
	 * 登录状态
	 */
	public String status;
	
	/**
	 * 角色codes
	 */
	public List<String> roleCodes;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

}
