package platform.education.commonResource.web.common.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.commonResource.web.common.vo.ResponseInfomation;

@Controller
public class LogoutController {
	
	@RequestMapping("/json/logout")
    @ResponseBody
	public String logout(
			@RequestParam(value = "useJsonp", required = true, defaultValue = "1") String useJsonp,
			@RequestParam(value = "callback", required = false) String callback) {
		Subject currentUser = SecurityUtils.getSubject();
		String result = ResponseInfomation.OPERATION_SUC;
		try {
			currentUser.logout();
		} catch (Exception e) {
			result = ResponseInfomation.OPERATION_FAIL;
		}
		if("1".equals(useJsonp)) {
			return callback + "(\"" + result + "\")";
		}
		return result;
	}
	
}
