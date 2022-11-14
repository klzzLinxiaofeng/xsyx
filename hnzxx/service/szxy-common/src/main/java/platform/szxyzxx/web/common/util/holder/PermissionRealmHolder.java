package platform.szxyzxx.web.common.util.holder;

import platform.szxyzxx.web.common.shiro.realm.PermissionRealm;
/**
 * <p>Title:PermissionRealmHolder.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年11月17日
 */
public class PermissionRealmHolder {
	
	private static PermissionRealmHolder instance;
	private PermissionRealm permissionRealm;
	
	public PermissionRealmHolder() {
		instance = this;
	}

	public static PermissionRealmHolder getInstance() {
		return instance;
	}

	public PermissionRealm getPermissionRealm() {
		return permissionRealm;
	}

	public void setPermissionRealm(PermissionRealm permissionRealm) {
		this.permissionRealm = permissionRealm;
	}
	
}
