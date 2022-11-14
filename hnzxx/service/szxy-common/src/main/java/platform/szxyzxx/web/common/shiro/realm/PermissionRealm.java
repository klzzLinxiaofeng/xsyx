package platform.szxyzxx.web.common.shiro.realm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.user.contants.UserContants;
import platform.education.user.model.AppUser;
import platform.education.user.model.User;
import platform.education.user.model.UserBinding;
import platform.education.user.service.AppUserService;
import platform.education.user.service.GroupUserService;
import platform.education.user.service.RoleService;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.shiro.exception.OverdueAccountException;

/**
 * 
 * <p>
 * Title:PermissionRealm.java
 * </p>
 * <p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>
 * 
 * @Function 功能描述：
 *           <p>
 *           此类是权限域的基本实现,继承自shiro安全框架的AuthorizingRealm.
 *           </p>
 *           <p>
 *           它是整个权限控制的生命周期. 执行动作如下：
 *           </p>
 *           <p>
 *           1.获取当前用户的名称或状态()-subject.
 *           </p>
 *           <p>
 *           2.通过用户名称与状态拿取角色相关的权限集.
 *           </p>
 *           <p>
 *           3.根据用户相关权限对用户的身份验证、授权、会话、访问控制进行管理.
 *           </p>
 *           <p>
 *           4.整个域的生命周期最后交付给SecurityManager来管理(SecurityManager
 *           </p>
 *           <p>
 *           相当于 Spring MVC中的DispqtcherServlet.)
 *           </p>
 * @Author 潘維良
 * @Version 1.0
 * @Date 2014年7月25日
 */
public class PermissionRealm extends AuthorizingRealm {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;

	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@Autowired
	@Qualifier("userBindingService")
	private UserBindingService userBindingService;
	
	@Autowired
	@Qualifier("appUserService")
	private AppUserService appUserService;
	
	@Autowired
	@Qualifier("groupUserService")
	private GroupUserService groupUserService;
	
	/**
	 * 授权住息控制
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		// admin
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		Set<String> roles = new HashSet<String>(userRoleService.findRoleCodesByUsername(username, SysContants.SYSTEM_APP_ID));
//		 角色
		authorizationInfo.setRoles(roles);
		// 权限
//		authorizationInfo.setStringPermissions(dmGyZhxxService.findPermissions(username));
		return authorizationInfo;
	}

	/**
	 * 身份验证控制
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户输入的用户名
		String username = (String) token.getPrincipal();
		
		//根据用户名查找绑定表
		UserBinding ub = this.userBindingService.findByBindingName(username);
		
		//如果绑定对象为null，抛出未知账号异常
		if (ub == null) {
			throw new UnknownAccountException("unknow account");
		}
		
		//判断当前判定对象是否激活的，如果未激活，抛出禁用账号异常
		if (!ub.getEnabled()) {
			throw new DisabledAccountException();
		}
		
		Integer userId = ub.getUserId();
		
		//根据用户ID查找用户账号在当前app的状态信息
		AppUser ap = appUserService.findUnique(SysContants.SYSTEM_APP_ID, userId);
		
		//如果为空,则当前账号不属于当前app
		if(ap == null) {
			throw new UnknownAccountException("unknow account");
		} 
		
		//判断当前账号在应用的状态是否被禁用
		if(UserContants.STATE_INVAILD.equals(ap.getState())) {
			throw new DisabledAccountException("The account has been disabled in the application");
		}
		
		//判断当前账号在应用的状态是否为被锁定
		if (UserContants.STATE_LOCK.equals(ap.getState())) {
			throw new LockedAccountException();
		}
		
		//根据userId，查找出原始账号
		User user = userService.findUserById(userId);
		
		//如果原始账号对象为null，抛出未知账号异常
		if (user == null) {
			throw new UnknownAccountException("unknow account");// 没找到帐号
		}
		
		//判断原始账号状态是否为被禁用
		if (UserContants.STATE_INVAILD.equals(user.getState())) {
			throw new DisabledAccountException();
		}
		
		//判断原始账号状态是否被锁定
		if (UserContants.STATE_LOCK.equals(user.getState())) {
			throw new LockedAccountException();
		}
		
		//判断当前时间是否超过原始账号的账号失效时间
		Date vaildDate = user.getValidDate();
		if (vaildDate != null) {
			Long currenTime = System.currentTimeMillis();
			if (currenTime >= vaildDate.getTime()) {
				throw new OverdueAccountException();
			}
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，
		// 如果觉得不好可以在此判断或自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUserName(), // 用户名
				user.getPassword(), // 密码
				getName() // realm name
		);
		return authenticationInfo;
	}

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	public void clearCachedAuthenticationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		super.clearCachedAuthenticationInfo(principals);
	}

	/**
	 * 用于注入token对象 public void setPermissionToken(PermissionToken
	 * permissionToken) { this.permissionToken = permissionToken; }
	 */

}
