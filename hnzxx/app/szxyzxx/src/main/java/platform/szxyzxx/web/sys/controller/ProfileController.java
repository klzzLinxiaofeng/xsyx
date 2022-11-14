package platform.szxyzxx.web.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
/**
 * <p>Title:ProfileController.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：用户个人信息
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年5月28日
 */
@Controller
@RequestMapping("/sys/user/profile")
public class ProfileController extends BaseController { 
	
	private final static String viewBasePath = "/sys/user";
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	@ResponseBody
	public Profile getProfileJson(
			@RequestParam("userId") Integer userId) {
		return this.profileService.findByUserId(userId);
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "userId", required = true) Integer userId, Model model) {
		Profile profile = this.profileService.findByUserId(userId);
		model.addAttribute("profile", profile);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(@RequestParam(value = "userId", required = true) Integer userId, Model model) {
		Profile profile = this.profileService.findByUserId(userId);
		model.addAttribute("profile", profile);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Profile profile) {
		if (profile != null) {
			profile.setId(id);
		}
		try {
			this.profileService.remove(profile);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Profile profile) {
		profile.setId(id);
		profile = this.profileService.modify(profile);
		return profile != null ? new ResponseInfomation(profile.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
}
