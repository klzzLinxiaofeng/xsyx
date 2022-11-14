package platform.szxyzxx.web.sns.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import platform.service.ucenter.client.UcenterClient;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

@RequestMapping("/sns")
public class SNSController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private UcenterClient ucenterClient;
	
	public void setUcenterClient(UcenterClient ucenterClient) {
		this.ucenterClient = ucenterClient;
	}

	@RequestMapping("/index")
	public ModelAndView index(@CurrentUser UserInfo user, HttpServletResponse response, Model model) {
		StringBuilder msg = new StringBuilder();
		String result = snsLogin(user);
		model.addAttribute("repoInfo", "error");
		model.addAttribute("msg", "SNS连接错误");
		if ("error".equals(result)) {
//			msg.append("SNS连接错误");
		} else if ("pwd_error".equals(result)) {
//			msg.append("SNS连接错误");
		} else if ("answer_error".equals(result)) {
//			msg.append("SNS连接错误");
		} else {
			// 只需要第一段script就可以完成单点登陆
			result = result.substring(0, result.lastIndexOf("<script"));
			msg.append(result);
//			msg.append("<script type=\"text/javascript\">window.open(\"" + SysContants.SNS_BASE_PATH + "\",\"_self\")</script>");
			model.addAttribute("msg", msg.toString());
			model.addAttribute("repoInfo", "success");
		}
		return new ModelAndView("/sns/index", model.asMap());
	}
	
	@RequestMapping("/logout")
	public void logout() {
		String result = this.ucenterClient.synlogout();
		System.out.println(result);
	}

	private String snsLogin(UserInfo user) {
		String msg = "error";
		String username = user.getOriginalUsername();
		String password = user.getInputPassword();
		String email = username + "@" + this.ucenterClient.getEmailDomainName();
		
		
		
		List<String> loginResult = this.ucenterClient.login(username, password);
		if (loginResult != null && loginResult.size() > 0) {
			// array (uid/status, username, password, email) 数组第一项 1 : 成功 -1 :
			// 用户不存在,或者被删除 -2 : 密码错 -3:安全提问错
			String loginStatus = loginResult.get(0);
			if ("-1".equals(loginStatus)) {
				// 用户不存在或被删除
				int regResult = Integer.parseInt(register(username, password,
						email));
				if (regResult > 0) {
					msg = snsLogin(user);
				}
			} else if ("-2".equals(loginStatus)) {
				msg = "pwd_error";
				// 密码错误
			} else if ("-3".equals(loginStatus)) {
				msg = "answer_error";
				// 安全提问错误
			} else {
				msg = ucenterClient.synlogin(loginStatus);
			}
		}
		return msg;
	}

	private String register(String username, String password, String email) {
		String registerResult = this.ucenterClient.register(username, password,
				email);
		if ("-1".equals(registerResult)) {
			log.info("{} 用户名不合法", username);
			// 用户名不合法
		} else if ("-2".equals(registerResult)) {
			log.info("{} 包含不允许注册的词语", username);
			// 包含不允许注册的词语
		} else if ("-3".equals(registerResult)) {
			log.info("{} 用户名已经存在", username);
			// 用户名已经存在
		} else if ("-4".equals(registerResult)) {
			log.info("{} email 格式有误", email);
			// email 格式有误
		} else if ("-5".equals(registerResult)) {
			log.info("{} email 不允许注册", email);
			// email 不允许注册
		} else if ("-6".equals(registerResult)) {
			log.info("{} email 已经被注册", email);
			// email 已经被注册
		} else if ("".equals(registerResult)) {
			log.info("uccenter 通信失败", email);
			// 通信失败
		} else {

		}
		return registerResult;
	}

}
