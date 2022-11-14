package com.xunyunedu.cas.authentication;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import platform.education.user.contants.UserContants;
import platform.education.user.model.AppUser;
import platform.education.user.model.User;
import platform.education.user.model.UserBinding;
import platform.education.user.model.UserLoginLog;
import platform.education.user.service.AppUserService;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserLoginLogService;
import platform.education.user.service.UserService;
import platform.education.user.vo.UserLoginLogCondition;

public class SzxyAuthenticationHandler extends
		AbstractJdbcUsernamePasswordAuthenticationHandler {

	private UserService userService;

	private UserBindingService userBindingService;

	private AppUserService appUserService;
	private UserLoginLogService userLoginLogService;

	private Integer appId;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected boolean authenticateUsernamePasswordInternal(UsernamePasswordCredentials credentials) throws AuthenticationException {
		final String username = getPrincipalNameTransformer().transform(credentials.getUsername());
		 String password = credentials.getPassword();
		 String encryptedPassword = credentials.getEncryptedPassword();
		
		
		// 根据用户名查找绑定表
		UserBinding ub = this.userBindingService.findByBindingName(username);

		
		
		// 如果绑定对象为null，抛出未知账号异常
		if (ub == null) {
			if(log.isDebugEnabled()) {
				log.debug("unknow account");
			}
//			throw new UnknownAccountException("unknow account");
			return false;
		}

		System.out.println("开始记录登录日志。。。。。。。。。");
		UserLoginLogCondition userLoginLogCondition = new UserLoginLogCondition();
		userLoginLogCondition.setUserId(ub.getUserId());
		List<UserLoginLog> userLoginLogList = userLoginLogService.findUserLoginLogByCondition(userLoginLogCondition);
		if(userLoginLogList != null &&userLoginLogList.size() > 0) {
			InetAddress addr;
			try {
				addr = InetAddress.getLocalHost();
				String curIp=addr.getHostAddress();
				UserLoginLog userLoginLog = userLoginLogList.get(0);
				userLoginLog.setLastIp(curIp);
				userLoginLog.setCurLoginTime(new Date());
				userLoginLogService.modify(userLoginLog);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}       
		
		}else {
			try {
				Date nowDate = new Date();
				InetAddress addr = InetAddress.getLocalHost();       
				String curIp=addr.getHostAddress();
				UserLoginLog userLoginLog = new UserLoginLog();
				userLoginLog.setUserId(ub.getUserId());
				userLoginLog.setCurLoginTime(nowDate);
				userLoginLog.setLastLoginTime(nowDate);
				userLoginLog.setCreateDate(nowDate);
				userLoginLog.setCurIp(curIp);
				userLoginLog.setLastIp(curIp);
				userLoginLog.setModifyDate(nowDate);
				userLoginLogService.add(userLoginLog);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		// 判断当前判定对象是否激活的，如果未激活，抛出禁用账号异常
		if (!ub.getEnabled()) {
			if(log.isDebugEnabled()) {
				log.debug("The account has been disabled in the application");
			}
//			throw new DisabledAccountException();
			return false;
		}

		Integer userId = ub.getUserId();

		// 根据用户ID查找用户账号在当前app的状态信息
		AppUser ap = appUserService.findUnique(appId, userId);

		// 如果为空,则当前账号不属于当前app
		if (ap == null) {
			if(log.isDebugEnabled()) {
				log.debug("unknow account");
			}
//			throw new UnknownAccountException("unknow account");
			return false;
		}

		// 判断当前账号在应用的状态是否被禁用
		if (UserContants.STATE_INVAILD.equals(ap.getState())) {
//			throw new DisabledAccountException("The account has been disabled in the application");
			if(log.isDebugEnabled()) {
				log.debug("The account has been disabled in the application");
			}
			return false;
		}

		// 判断当前账号在应用的状态是否为被锁定
		if (UserContants.STATE_LOCK.equals(ap.getState())) {
//			throw new LockedAccountException();
			if(log.isDebugEnabled()) {
				log.debug("The account has been locked in the application");
			}
			return false;
		}

		// 根据userId，查找出原始账号
		User user = userService.findUserById(userId);

		// 如果原始账号对象为null，抛出未知账号异常
		if (user == null) {
//			throw new UnknownAccountException("unknow account");// 没找到帐号
			if(log.isDebugEnabled()) {
				log.debug("unknow account");
			}
			return false;
		}

		// 判断原始账号状态是否为被禁用
		if (UserContants.STATE_INVAILD.equals(user.getState())) {
//			throw new DisabledAccountException();
			if(log.isDebugEnabled()) {
				log.debug("The account has been locked in the application");
			}
			return false;
		}

		// 判断原始账号状态是否被锁定
		if (UserContants.STATE_LOCK.equals(user.getState())) {
//			throw new LockedAccountException();
			if(log.isDebugEnabled()) {
				log.debug("The account has been locked in the application");
			}
			return false;
		}

		// 判断当前时间是否超过原始账号的账号失效时间
		Date vaildDate = user.getValidDate();
		if (vaildDate != null) {
			Long currenTime = System.currentTimeMillis();
			if (currenTime >= vaildDate.getTime()) {
//				throw new OverdueAccountException();
				if(log.isDebugEnabled()) {
					log.debug("The account has been Overdued in the application");
				}
				return false;
			}
		}

		try {
			
		
			final String dbPassword = user.getPassword();
			if(encryptedPassword != null && !"".equals(encryptedPassword)) {
				return dbPassword.equals(encryptedPassword);
			}else {
				encryptedPassword = this.getPasswordEncoder().encode(password);
				return dbPassword.equals(encryptedPassword);
			}
			
		} catch (final IncorrectResultSizeDataAccessException e) {
			// this means the username was not found.
			if(log.isDebugEnabled()) {
				log.debug("this means the username was not found.");
			}
			return false;
		}
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserBindingService(UserBindingService userBindingService) {
		this.userBindingService = userBindingService;
	}

	public void setAppUserService(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public void setUserLoginLogService(UserLoginLogService userLoginLogService) {
		this.userLoginLogService = userLoginLogService;
	}

	
}
