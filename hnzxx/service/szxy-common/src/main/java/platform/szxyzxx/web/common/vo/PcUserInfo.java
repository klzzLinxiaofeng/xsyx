package platform.szxyzxx.web.common.vo;

/**
 * <p>Title:PcUserInfo.java</p>
 * <p>Description:教育基础管理平台</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：Pc端用户信息
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年8月3日
 */
public class PcUserInfo extends UserInfo {

	private static final long serialVersionUID = 8668164769088637297L;

	/**
	 * 登录状态
	 */
	public String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
