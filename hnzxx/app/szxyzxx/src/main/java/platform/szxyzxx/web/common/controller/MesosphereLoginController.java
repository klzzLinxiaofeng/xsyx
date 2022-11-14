package platform.szxyzxx.web.common.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.user.model.User;
import platform.education.user.model.UserBinding;
import platform.education.user.model.UserBindingSso;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserBindingSsoService;
import platform.education.user.service.UserService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.SimulationCasUtil;
import platform.szxyzxx.web.common.vo.UserInfo;


/**
 *
 * @功能描述: 模拟单点登录
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2017年11月30日下午7:52:41
 */
@Controller
@RequestMapping("/mesosphere/")
public class MesosphereLoginController{
	private static final Logger log = LoggerFactory.getLogger(MesosphereLoginController.class);

	@Autowired
	@Qualifier("userBindingSsoService")
	private UserBindingSsoService userBindingSsoService;

	@Autowired
	@Qualifier("userBindingService")
	private UserBindingService userBindingService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	/**
	 * 沿河三中海康监控单点登录
	 * @param uiModel
	 * @param user
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "mqtCasGenerator", method = RequestMethod.GET)
	public String loginPerformer(Model uiModel ,@CurrentUser UserInfo user) throws Exception {
		String jumpUrl = "";
		if(user != null) {
			//根据迈奇拓平台的用户ID以及平台ID 暂时写死1，以后改成读表
			UserBindingSso userBindingSso  = userBindingSsoService.findUnique(user.getId(), 1);
			if(userBindingSso != null) {
				String ticket = SimulationCasUtil.getCasTicket(userBindingSso.getBindingName(), userBindingSso.getBindingPwd(),userBindingSso.getEncryptType());
				String url = SysContants.YH_BROADCAST_URL_PATH;
				//需要调整的API
				jumpUrl = url + ticket;
			}

		}

		return "redirect:"+jumpUrl;

	}

	/**
	 * 根据绑定账号和学校ID查找 唯一用户信息(只适用于一个账号绑定多个学校情况)
	 * @param uiModel
	 * @param username
	 * @param schoolId
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "mqtGetUserByUsernameAndSchoolId", method = RequestMethod.GET)
	@ResponseBody
	public User mqtGetUserByUsernameAndSchoolId(Model uiModel ,@RequestParam(value = "username", required = true) String username,@RequestParam(value = "schoolId", required = true)Integer schoolId) throws Exception {

		Integer userId = this.userBindingService.findUserIdByBindNameAndSchoolId(username, schoolId);
		if(userId != null) {
			User user = this.userService.findUserById(userId);
			return user;
		}else {
			UserBinding userBinding = userBindingService.findByBindingName(username);
			if(userBinding != null) {
				 userBinding = userBindingService.findUserBindingByUserIdAndType(userBinding.getUserId(), 1);
				 userId = this.userBindingService.findUserIdByBindNameAndSchoolId(userBinding.getBindingName(), schoolId);
				User user = this.userService.findUserById(userId);
				return user;
			}
		}
		return null;
	}

}
