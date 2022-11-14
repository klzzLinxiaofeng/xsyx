package platform.szxyzxx.web.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.user.model.Profile;
import platform.education.user.model.User;
import platform.education.user.service.UserService;
import platform.service.ucenter.client.UcenterClient;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.annotation.Resource;

/**
 * <p>
 * Title:UserCenterController.java
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
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年5月29日
 */
@Controller
@RequestMapping("/user/center")
public class UserCenterController extends BaseController {

	private final static String viewBasePath = "/sys/user/center";
	
	private final static Logger log = LoggerFactory.getLogger(UserCenterController.class);
	
	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService;
	
	@Resource
	private UcenterClient ucenterClient;

	@RequestMapping("/index")
	public String getIndex(
			@RequestParam(value = "module", required = false) String message,
			@RequestParam(value = "newPage", required = false) String newPage,
			@RequestParam(value = "teaMes", required = false) String teaMes,
			Model model) {
		return structurePath("/index");
	}

	@RequestMapping(value = "/profile/json", method = RequestMethod.GET)
	@ResponseBody
	public Profile getProfileJson(@RequestParam("userId") Integer userId) {
		return this.profileService.findByUserId(userId);
	}

	@RequestMapping(value = "/profile/editor", method = RequestMethod.GET)
	public ModelAndView editor(@CurrentUser UserInfo user, Model model) {
		Profile profile = this.profileService.findByUserId(user.getId());
		model.addAttribute("profile", profile);
		return new ModelAndView(structurePath("/profile_editor"), model.asMap());
	}

	@RequestMapping(value = "/password/editor", method = RequestMethod.GET)
	public ModelAndView passwordEditor(@CurrentUser UserInfo user, Model model) {
		User perUser = this.userService.findUserById(user.getId());
		model.addAttribute("user", perUser);
		return new ModelAndView(structurePath("/password_editor"),
				model.asMap());
	}

	@RequestMapping(value = "/profile/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "userId", required = true) Integer userId,
			Model model) {
		Profile profile = this.profileService.findByUserId(userId);
		model.addAttribute("profile", profile);
		return new ModelAndView(structurePath("/profile_editor"), model.asMap());
	}

	@RequestMapping(value = "/profile/{id}", method = RequestMethod.DELETE)
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

	@RequestMapping(value = "/profile/editor", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation edit(Profile profile, @CurrentUser UserInfo user) {
		profile = this.profileService.modify(profile);
		if (profile != null) {
			if(profile.getName()!=null){
				user.setRealName(profile.getName());
			}
			if(profile.getEmail()!=null){
				user.setEmail(profile.getEmail());
			}
		}
//		List<SchoolUser> schoolUserList = this.schoolUserService.findSchoolUserOfTeacher(user.getSchoolId());
//		for(SchoolUser schoolUser:schoolUserList){
//			if(profile.getName().equals(schoolUser.getName())){
//				return new ResponseInfomation(ResponseInfomation.DATA_REPEAT);
//			}
//		}
//		profile = this.profileService.modify(profile);
//		if (profile != null) {
//			user.setRealName(profile.getName());
//			user.setEmail(profile.getEmail());
//			String mobile = profile.getMobile();
//			String name = profile.getName();
//			//修改school_user表
//			SchoolUserCondition schoolUsercondition = new SchoolUserCondition();
//			schoolUsercondition.setUserId(user.getId());
//			schoolUsercondition.setUserName(user.getUserName());
//			SchoolUser schoolUser = this.schoolUserService.findSchoolUserByCondition(schoolUsercondition, null, null).get(0);
//			if(!schoolUser.getUserType().equals("1") && !schoolUser.getUserType().equals("2")){
//				return new ResponseInfomation(ResponseInfomation.NO_DELETE);
//			}
//			schoolUser.setName(name);
//			schoolUser.setAlias(name);
//			schoolUser = schoolUserService.modify(schoolUser);
//			schoolUser.getPersonId();
//			//修改teacher表
//			TeacherCondition teacherCondition = new TeacherCondition();
//			teacherCondition.setUserId(user.getId());
//			teacherCondition.setUserName(profile.getUserName());
//			Teacher teacher = this.teacherService.findTeacherByCondition(teacherCondition, null, null).get(0);
//			teacher.setName(name);
//			teacher.setAlias(name);
//			teacher.setMobile(mobile);
//			this.teacherService.modify(teacher);
//			//team_teacher表
//			TeamTeacherCondition teamTeacherCondtion = new TeamTeacherCondition();
//			teamTeacherCondtion.setUserId(user.getId());
//			List<TeamTeacher> teamTeacherList = this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondtion, null, null);
//			for(TeamTeacher teamTeacher :teamTeacherList){
//				teamTeacher.setName(name);
//				this.teamTeacherService.modify(teamTeacher);
//			}
//			//person表
//			Person person = this.personService.findPersonById(schoolUser.getPersonId());
//			person.setName(name);
//			person.setMobile(mobile);
//			person.setPhotoUuid(profile.getIcon());
//			this.personService.modify(person);
//			//binding表
//			UserBindingCondition userBindingCondition = new UserBindingCondition();
//			userBindingCondition.setUserId(user.getId());
//			userBindingCondition.setBindingType(1);
//			List<UserBinding> userBindingList = this.userBindingService.findUserBindingByCondition(userBindingCondition, null, null);
//			if(userBindingList.size()>0){
//				UserBinding userBinding  = userBindingList.get(0);
//				UserBinding newUserBinding = new UserBinding();
//				try {
//					PropertyUtils.copyProperties(newUserBinding,userBinding);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				newUserBinding.setBindingName(mobile);
//				newUserBinding.setId(null);
//				this.userBindingService.remove(userBinding);
//				this.userBindingService.add(newUserBinding);
//			}
//		}
		return profile != null ? new ResponseInfomation(profile.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/password/editor", method = RequestMethod.POST)
	@ResponseBody
	public String editPassword(@RequestParam("newPwd") String newPwd,
			@RequestParam("oldPwd") String oldPwd,
			@CurrentUser UserInfo currentUser) {
		String result = this.userService.modifyUserPassword(currentUser.getId(), newPwd, oldPwd);
		if (UserService.OPERATE_SUCCESS.equals(result)) {
			currentUser.setInputPassword(newPwd);
			if (SysContants.SNS_ENABLE) {
				String username = currentUser.getUserName();
				String email = currentUser.getEmail();
				email = email == null || "".equals(email) ? username + "@" + this.ucenterClient.getEmailDomainName() : email;
				String status = ucenterClient.editUser(username, oldPwd, newPwd, email, 1, "", "");
//				1 : 修改成功 0 : 没有任何修改 -1 : 旧密码不正确 -4 : email 格式有误 -5 : email 不允许注册 -6 : 该 email 已经被注册 -7 : 没有做任何修改 -8 : 受保护的用户，没有权限修改
				if ("1".equals(status) || "0".equals(status) || "-7".equals(status)) {
					
				} else if ("-4".equals(status)) {
					if (log.isDebugEnabled()) {
						log.debug("sns --> email 格式有误");
					}
					result = UserService.OPERATE_FAIL;
				} else if ("-5".endsWith(status)) {
					if (log.isDebugEnabled()) {
						log.debug("sns -->  email 不允许注册");
					}
					result = UserService.OPERATE_FAIL;
				}
			}
		}
		return result;
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

}
