package platform.education.commonResource.web.common.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import platform.education.commonResource.web.common.contants.CasContants;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.util.MultiDomainResolver;
import platform.education.commonResource.web.common.util.SessionInfoManager;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {
	
	@Autowired
	@Qualifier("sessionInfoManager")
	private SessionInfoManager sessionInfoManager;
	
	@RequestMapping(value = "/login")
	public void loginer(@RequestParam("un") String username) {
		Subject currentUser = SecurityUtils.getSubject();
		sessionInfoManager.setUserInfoToSession(username, currentUser.getSession());
	}

	@RequestMapping(value = "/cas/login/transfer")
	public String loginTransfer(HttpServletRequest request) {
		String basePath = MultiDomainResolver.resolver(request.getServerName(), SysContants.SZXY_BASE_PATH);
		String casBasePath = MultiDomainResolver.resolver(request.getServerName(), CasContants.CASE_BASE_PATH);
		return "redirect:" + casBasePath + "/login?service=" + basePath + request.getContextPath() + "/sso";
	}

}
