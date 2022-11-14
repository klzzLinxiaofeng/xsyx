package platform.education.commonResource.web.common.controller;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import platform.education.commonResource.web.common.util.CasConfigAccessor;
import platform.education.commonResource.web.common.util.SimulationCasUtil;

/**
 * 
 * @功能描述: 登录中间层
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2017年11月30日下午7:52:41
 */

@Controller
@RequestMapping("/mesosphere/")
public class MesosphereLoginController{
	
	/**
	 * @param username
	 * @param password
	 * @param isRememberMe
	 * @param session
	 * @return
	 * @throws Exception 
	 * @Method loginPerformer
	 * @Function 功能描述：模拟CAS登陆
	 * @Date 2014年10月27日
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPerformer(Model model,@RequestParam(value = "username", required = true) String username,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String casServer = CasConfigAccessor.getStringProp("casServer");
		String assemblyService = CasConfigAccessor.getStringProp("assemblyService");
		Map<String,String> map = SimulationCasUtil.validateFromCAS(username,"MQTCAS",casServer,assemblyService);
		if(map != null && map.size() > 0) {
			System.out.println("CASTGC"+map.get("tgt"));
			Cookie cookie = new Cookie("CASTGC",map.get("tgt"));
			cookie.setPath("/cas");
			response.addCookie(cookie);
			model.addAttribute("result", map.get("lt"));
		}
		return "demo/autoLogin";
		
	}
	
}
