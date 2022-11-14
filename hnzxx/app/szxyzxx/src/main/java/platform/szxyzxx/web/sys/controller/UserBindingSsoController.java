package platform.szxyzxx.web.sys.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.user.model.UserBindingSso;
import platform.education.user.service.UserBindingSsoService;
import platform.education.user.vo.UserBindingSsoCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/user/binding/sso")
public class UserBindingSsoController {

	private final static String viewBasePath = "/sys/userbinding";

	
	@Autowired
	@Qualifier("userBindingSsoService")
	private UserBindingSsoService userBindingSsoService;

	@RequestMapping(value = "/encrypt")
	public Object getRedirectUrl(@CurrentUser UserInfo user){
		UserBindingSso unique = userBindingSsoService.findUnique(user.getId(), 1);
		if (unique != null) {
			String bindingName = unique.getBindingName();
			String bindingPwd = userBindingSsoService.findDesEncryptPwd(unique);
			System.out.println("****************************************");
			System.out.println("bindingName:" + bindingName );
			System.out.println("bindingPwd:" + bindingPwd );
			String urlPath = 
					"";//SysContants.DN_BROADCAST_URL_PATH;
			String redirectUri = urlPath + "?uid=" + bindingName + "&pwd=" + bindingPwd;
			return "redirect:" + redirectUri;
		}
		return null;
	}

}
