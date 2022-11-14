package platform.education.commonResource.web.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import platform.education.commonResource.web.common.contants.CasContants;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.util.SessionInfoManager;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.user.model.User;
import platform.education.user.service.UserService;

/**
 * 
 * <p>
 * Title:AutoLoginFilter.java
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
 * @Function 功能描述：用于 自动登录 的拦截器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月25日
 */
public class AutoLoginFilter implements Filter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private UserService userService;
//	private GroupUserService groupUserService;
	
	private String excludedDir;
	private boolean rememberMeEnable;
	
	private SessionInfoManager sessionInfoManager;
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.excludedDir = filterConfig.getInitParameter("excludedDir");
		try {
			this.rememberMeEnable = Boolean.parseBoolean(filterConfig
					.getInitParameter("rememberMeEnable"));
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("web.xml文件中配置AutoLoginFilter初始化参数rememberMeEnable的值应该为true|false");
			}
		}

		WebApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		this.userService = (UserService) application.getBean("userService");
		this.sessionInfoManager = (SessionInfoManager) application.getBean("sessionInfoManager");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String currentRequestURI = httpRequest.getRequestURI();
		Integer schoolId = null;
		Cookie[] cookies = httpRequest.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie:cookies) {
				if("schoolId".equals(cookie.getName())) {
					if(cookie.getValue() != null && !"".equals(cookie.getValue()) && !"null".equals(cookie.getValue())) {
						schoolId = Integer.valueOf(cookie.getValue());
					}
				}
			}
		}
		
		
		if(!currentRequestURI.contains(this.excludedDir)) {
			Subject currentUser = SecurityUtils.getSubject();
			if( !currentUser.isAuthenticated() && currentUser.isRemembered() && this.rememberMeEnable) {
				String username = (String)currentUser.getPrincipal();
				User user = this.userService.findUserByUsername(username);
				if(user != null) {
					String password = user.getPassword();
					UsernamePasswordToken token = new UsernamePasswordToken(username, password);
					token.setRememberMe(true);
					try {
						currentUser.login(token);
						sessionInfoManager.setUserInfoToSession(username, currentUser.getSession());
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(currentUser.isAuthenticated()) {
						StringBuffer requestUrl = httpRequest.getRequestURL();
						httpResponse.sendRedirect(requestUrl.toString());
						return;
					}
				}
			} else if (CasContants.CAS_ENABLE) {
				Session session = currentUser.getSession();
				if(session != null) {
					UserInfo userInfo = (UserInfo) session.getAttribute(SysContants.CURRENT_USER);
					String username = (String) currentUser.getPrincipal();
					if (currentUser.isAuthenticated() && userInfo == null) {
						try {
							sessionInfoManager.setUserInfoToSession(username, session);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (currentUser.isAuthenticated() && userInfo != null) {
						String userName = userInfo.getUsername();
						if(userName != null && !userName.equals(username)) {
							sessionInfoManager.setUserInfoToSession(username, session);
						}
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
