package platform.szxyzxx.web.common.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.user.model.*;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.AppReleaseService;
import platform.education.user.vo.AppReleaseCondition;
import platform.education.user.vo.AppReleaseVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>Title:HomeController.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：管理系统入口
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月27日
 */
@Controller
public class HomeController extends BaseController {

	@Resource
	private AppReleaseService appReleaseService;

	@Resource
	private AppEditionService appEditionService;

//	@Value("${system.isUseNewHome}")
	private Boolean isUseNewHome=SysContants.isUseNewHome;

	@RequestMapping(value={"/", "/home", "/index"})
	public ModelAndView home(@CurrentUser UserInfo user, Model model, HttpServletRequest request) {

//		if(isUseNewHome&&!"superadmin".equalsIgnoreCase(user.getUserName())) return new ModelAndView("forward:/new_home");

		//需要根据当前登录用户，查询能够有读取权限的模块列表
		if(user != null){
			List<Permission> headModules = aclService.findPermissions(user.getId(), "0", SysContants.SYSTEM_APP_ID);
			model.addAttribute("headModules", headModules);
			List<Permission> modules = aclService.findPermissions(user.getId(), SysContants.SYSTEM_APP_ID);
			for (Permission module : modules) {
				if(module.getName().equals("考勤管理")){
					module.setAccessUrl("http://campus.seewo.com/mis-banpai-data-sync/api/sso/v1/dgxsyx/login?redirectUrl=https%3A%2F%2Fcampus.seewo.com%2F%23%2Fmanage%2Festhesia&token="+getShiroSessionCookie(request));
				}
			}
			model.addAttribute("modules", modules);
		}
		return new ModelAndView("/main", model.asMap());
	}


	private String getShiroSessionCookie(HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("szxyzxx.clouds")){
				return cookie.getValue();
			}
		}
		return null;
	}

	@RequestMapping(value= "/index/mainPage", method = RequestMethod.GET)
	public ModelAndView toMainPage(@CurrentUser UserInfo user, Model model) {
		String depOrteamName = "";
		String roleName = "";
		if(user != null) {
			String type = user.getUserTypes();
			//获得角色名称
			List<UserRole> userRoleList = this.userRoleService.findByUserId(user.getId());
			if(userRoleList.size() > 0) {
				for(UserRole userRole : userRoleList) {
					if(userRole.getRole() != null) {
						if("".equals(roleName)) {
							roleName = userRole.getRole().getName();
						}else {
							roleName = roleName + ";" + userRole.getRole().getName();
						}
					}
				}
			}

			if("1".equals(type)) { //1--教职工
				Integer teacherId = user.getTeacherId();
				if(teacherId != null) {
					DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
					dtCondition.setIsDeleted(false);
					dtCondition.setTeacherId(teacherId);
					List<DepartmentTeacher> list = this.departmentTeacherService.findDepartmentTeacherByCondition(dtCondition, null, null);
					if(list.size() > 0) {
						depOrteamName = list.get(0).getDepartmentName();
					}
				}
			}else if("2".equals(type)) { // 2--管理员
				Integer teacherId = user.getTeacherId();
				if(teacherId != null) {
					DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
					dtCondition.setIsDeleted(false);
					dtCondition.setTeacherId(teacherId);
					List<DepartmentTeacher> list = this.departmentTeacherService.findDepartmentTeacherByCondition(dtCondition, null, null);
					if(list.size() > 0) {
						depOrteamName = list.get(0).getDepartmentName();
					}
				}
			}else if("3".equals(type)) { // 3--家长
				Integer parentUserId = user.getId();
				if(parentUserId != null){
					Parent parent = parentService.findUniqueByUserId(parentUserId);
					if(parent != null){
						user.setRealName(parent.getName());
					}
				}
			}else if("4".equals(type)) { //4--学生
				Integer studentId = user.getStudentId();
				if(studentId != null) {
					Student student = this.studentService.findStudentById(studentId);
					if(student != null) {
						//SchoolTermCurrent schoolTermCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
						//Team team = this.teamService.findCurrentTeamOfStudent(student.getUserId(), schoolTermCurrent.getSchoolYear());
						//depOrteamName = team.getName();
						//Integer teamId = team.getId();
						//model.addAttribute("teamId", teamId);

						depOrteamName = student.getName();
						Integer teamId = student.getId();
						model.addAttribute("teamId", teamId);
					}
				}
			}else {

			}
		}
//		List<AppAndAppReleaseVo> appReleases = appReleaseService.findCurAppReleases(null);
		AppReleaseCondition appReleaseCondition = new AppReleaseCondition();
		appReleaseCondition.setIsDeleted(false);
		appReleaseCondition.setIsCurrent(true);
		List<AppRelease> appReleases = appReleaseService.findAppReleaseByCondition(appReleaseCondition);
		App app = null;
		AppReleaseVo appReleaseVo = null;
		AppEdition appEdition = null;
		List<AppReleaseVo> appReleaseVoList = new ArrayList<AppReleaseVo>();
		for (AppRelease appRelease : appReleases) {
			app = appService.findAppById(appRelease.getAppId());
			if (app != null) {
				appEdition = appEditionService.findByAppKey(appRelease.getAppKey());
				if (appEdition != null) {
					appReleaseVo = new AppReleaseVo();
					BeanUtils.copyProperties(appRelease, appReleaseVo);
					appReleaseVo.setName(appEdition.getName());
					appReleaseVo.setTrademark(fileService.relativePath2HttpUrlByUUID(app.getTrademark()));
					appReleaseVoList.add(appReleaseVo);
				}
			}
		}
		model.addAttribute("appReleaseVoList", appReleaseVoList);
		model.addAttribute("roleName", roleName);
		model.addAttribute("depOrteamName", depOrteamName);
		return new ModelAndView("/common/embedded/teacher_index", model.asMap());
	}

	@RequestMapping(value= "/second/index", method = RequestMethod.GET)
	public ModelAndView toSecondMainPage(@CurrentUser UserInfo user,
										 @RequestParam(value = "dm", required = false) String dm,
										 Model model) {
		model.addAttribute("dm", dm);
		return new ModelAndView("/common/embedded/second_index", model.asMap());
	}

	@RequestMapping(value={"/third/index"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView toThirdMainPage(
			@CurrentUser UserInfo user,
			@RequestParam(value="dm", required=false) String dm,
			@RequestParam(value="type", required=false, defaultValue="out") String type,
			Model model) {
		model.addAttribute("dm", dm);
		model.addAttribute("type", type);
		return new ModelAndView("/common/embedded/third_index", model.asMap());
	}

	@RequestMapping(value={"/self/index"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView toThirdMainPage(
			@CurrentUser UserInfo user,
			@RequestParam(value="dm", required=false) String dm,
			@RequestParam(value="url", required=false) String url,
			@RequestParam(value="level", required=false) String level,
			@RequestParam(value="type", required=false, defaultValue="out") String type,
			Model model) {
		model.addAttribute("dm", dm);
		model.addAttribute("url", url);
		model.addAttribute("level", level);
		model.addAttribute("type", type);
		return new ModelAndView("/common/embedded/self_index", model.asMap());
	}

	
	
	/**
	 * 汉教云H5主页
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bp/h5/home")
	public ModelAndView bpHome(@CurrentUser UserInfo user, Model model) {
		//需要根据当前登录用户，查询能够有读取权限的模块列表
		if(user == null){
			return new ModelAndView("redirect:/bp/login", model.asMap());
			/*List<Permission> headModules = aclService.findPermissions(user.getId(), "0", SysContants.SYSTEM_APP_ID);
			model.addAttribute("headModules", headModules);
			List<Permission> modules = aclService.findPermissions(user.getId(), SysContants.SYSTEM_APP_ID);
			model.addAttribute("modules", modules);*/
			/*model.addAttribute("userName", user.getUserName());
			model.addAttribute("type", user.getUserTypes());*/
		}
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("type", user.getUserTypes());
		return new ModelAndView("/bpH5/main", model.asMap());
	}

}

