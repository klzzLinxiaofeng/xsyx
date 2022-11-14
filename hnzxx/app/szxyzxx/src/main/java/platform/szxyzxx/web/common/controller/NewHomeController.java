package platform.szxyzxx.web.common.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.hnzxx.ui.model.Setting;
import platform.education.hnzxx.ui.service.SettingService;
import platform.education.message.model.Message;
import platform.education.message.vo.Condition;
import platform.education.message.vo.MessageVo;
import platform.education.user.contants.AclContants;
import platform.education.user.model.*;
import platform.education.user.service.AppletRoleService;
import platform.education.user.service.AppletService;
import platform.education.user.vo.*;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.MultiDomainResolver;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.message.contans.StatusTypeContans;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


public class NewHomeController extends BaseController {

//    @Value("${common.resource.base.path}")
    private String crPath= SysContants.COMMON_RESOURCE_BASE_PATH;

    @Autowired
    @Qualifier("appletRoleService")
    private AppletRoleService appletRoleService;

    @Autowired
    @Qualifier("appletService")
    private AppletService appletService;

    @Autowired
    @Qualifier("settingService")
    private SettingService settingService;

    @RequestMapping(value = "/new_home")
    public String getIndex(@CurrentUser UserInfo user, Model model){
        Setting setting = this.settingService.findByUserId(user.getId());
        String newWallpaperPath = "new_default";
        if(setting != null) {
            newWallpaperPath = setting.getNewWallpaperPath();
        }

        List<MessageVo> unreadMessages = getUnreadMessages(user);
        List<MessageVo> teacherMessages = getTeacherMessages(user);
		model.addAttribute("unreadSysMes", unreadMessages);
		model.addAttribute("unreadTeaMes", teacherMessages);

        model.addAttribute("newWallpaperPath", newWallpaperPath);
        return "/sys/new_home/home";
    }

    @RequestMapping(value = "/new_index")
    public ModelAndView getHome(
            HttpServletRequest request,
            @CurrentUser UserInfo user, Model model,@RequestParam(value = "newWallpaperPath",required = false) String newWallpaperPath) {
        //可访问的应用桌面
        List<String> roleCodes=getRoleCodesByUserId(user.getId());
        DesktopJsonBody desktop=appletRoleService.getHome(user.getSchoolId(),roleCodes,"xunyun#educloud#web",MultiDomainResolver.resolver(request.getServerName(),crPath));
        model.addAttribute("desktop",desktop);

        //copy自原桌面代码 角色和部门班级等
        String depOrteamName = "";
        String roleName = "";
        List<UserRole> userRoleList =new ArrayList<>();
        if(user != null) {
            String type = user.getUserTypes();
            //获得角色名称
            userRoleList = this.userRoleService.findByUserId(user.getId());
            if (userRoleList.size() > 0) {
                for (UserRole userRole : userRoleList) {
                    if (userRole.getRole() != null) {
                        if ("".equals(roleName)) {
                            roleName = userRole.getRole().getName();
                        } else {
                            roleName = roleName + ";" + userRole.getRole().getName();
                        }
                    }
                }
            }
            if(type!=null){
            if (type.contains("1")) { //1--教职工
                Integer teacherId = user.getTeacherId();
                if (teacherId != null) {
                    DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
                    dtCondition.setIsDeleted(false);
                    dtCondition.setTeacherId(teacherId);
                    List<DepartmentTeacher> list = this.departmentTeacherService.findDepartmentTeacherByCondition(dtCondition, null, null);
                    if (list.size() > 0) {
                        depOrteamName = list.get(0).getDepartmentName();
                    }
                }
            } else if (type.contains("2")) { // 2--管理员
                Integer teacherId = user.getTeacherId();
                if (teacherId != null) {
                    DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
                    dtCondition.setIsDeleted(false);
                    dtCondition.setTeacherId(teacherId);
                    List<DepartmentTeacher> list = this.departmentTeacherService.findDepartmentTeacherByCondition(dtCondition, null, null);
                    if (list.size() > 0) {
                        depOrteamName = list.get(0).getDepartmentName();
                    }
                }
            } else if (type.contains("3")) { // 3--家长
                Integer parentUserId = user.getId();
                if (parentUserId != null) {
                    Parent parent = parentService.findUniqueByUserId(parentUserId);
                    if (parent != null) {
                        user.setRealName(parent.getName());
                    }
                }
            } else if (type.contains("4")) { //4--学生
                Integer studentId = user.getStudentId();
                if (studentId != null) {
                    Student student = this.studentService.findStudentById(studentId);
                    if (student != null) {
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
            } else {

            }
        }
        }

        List<MessageVo> unreadMessages = getUnreadMessages(user);
        List<MessageVo> teacherMessages = getTeacherMessages(user);
		model.addAttribute("unreadSysMes", unreadMessages);
		model.addAttribute("unreadTeaMes", teacherMessages);

        model.addAttribute("roleName", roleName);
        model.addAttribute("depOrteamName", depOrteamName);
        model.addAttribute("newWallpaperPath", newWallpaperPath);
        model.addAttribute("userRoleList", userRoleList);

        //加入系统账号登录不让切换学校逻辑，只有当时绑定账号才可以切换
        try {
	        	Subject currentUser = SecurityUtils.getSubject();
				List objs = currentUser.getPrincipals().asList();
				if(objs != null && objs.size() > 1) {
					Map<String, Object> info = (Map<String, Object>) objs.get(1);
				if(info.get("schoolId") != null && !"".equals(info.get("schoolId"))) { //切换后的
					UserBinding userBinding = userBindingService.findUserBindingByUserIdAndType(user.getId(), 1);
					List<Group> groupList = this.groupService.findGropListByBindName(userBinding.getBindingName());
					model.addAttribute("groupList", groupList);
				}else { //切换前是系统账号无切学校
					User newUser = this.userService.findUserByUsername(user.getUserName());
					if(newUser == null) { //绑定账号有切换学校
						UserBinding userBinding = userBindingService.findUserBindingByUserIdAndType(user.getId(), 1);
						List<Group> groupList = this.groupService.findGropListByBindName(userBinding.getBindingName());
						model.addAttribute("groupList", groupList);

					}
				}

				}

		} catch (Exception e) {
			e.printStackTrace();
		}

        String path="/sys/new_home/new_index";
        return new ModelAndView(path,model.asMap());
    }


    @RequestMapping(value = "/app")
    public ModelAndView getSecondIndex(
            HttpServletRequest request,
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "code",required = false) String[] code,
            @RequestParam(value = "type",required = false) Integer[] type, //type为1代表是没有子节点的
            @RequestParam(value = "targetUrl",required = false) String targetUrl,
            @RequestParam(value = "targetDm",required = false) String targetDm,
            @CurrentUser UserInfo user, Model model) {

//        判断这个用户的角色是否有权限访问此应用， applet_role表，applet_owner表是否下架
//        appletRoleService.count()


//1.正常情况 有二级菜单  2.只有一级菜单  3.消息只存了子菜单模块code 需要进入level为1的菜单并选中

        //消息进来的
        if (targetDm != null && targetDm != "" &&code==null){
            key = getAppletByPermission(permissionService.findPermissionByCode(targetDm)).getCode();
            code=new String[1];
            type=new Integer[]{0};
            code[0]=key;
//            type[0]=Integer.valueOf(0);
        }

        //应用信息
        SecondIndexJson menu=new SecondIndexJson();
        menu.setTargetDm(targetDm);
        menu.setImageUrl(targetUrl);
        AppletCondition appletCondition=new AppletCondition();
        appletCondition.setAppletKey(key);
        List<Applet> applets=appletService.findAppletByCondition(appletCondition);
        if (applets != null && !applets.isEmpty()) {
            Applet applet = applets.get(0);
            menu.setDm(applet.getAppletKey());
            menu.setImageUrl(fileService.findFileByUUID(applet.getIcon()).getHttpUrl());
            menu.setName(applet.getName());
        }

        //获取角色ids 拿到所有有权限的子菜单
        PermissionCondition rpCondition=new PermissionCondition();
        rpCondition.setState(String.valueOf(0));
        rpCondition.setRoleIds(getRoleIdsByUserId(user.getId()));
        menu.setMenu(new ArrayList<SecondIndexJsonMenu>());
        for(int i=0;i<code.length;i++){
            if(type[i]==0){
                rpCondition.setParentCode(code[i]);
                rpCondition.setCode(null);
                menu.getMenu().addAll(getMenuJson(rpCondition,request));
            } else { //没有子节点的
                rpCondition.setParentCode(null);
                rpCondition.setCode(code[i]);
                SecondIndexJsonMenu m = getSingleMenuJson(rpCondition,request);
                if (m != null) {
                    menu.getMenu().add(m);
                }
            }
        }

        model.addAttribute("secondIndex", JSONObject.toJSON(menu));

        Setting setting = this.settingService.findByUserId(user.getId());
        String newWallpaperPath = "new_default";
        if(setting != null) {
            newWallpaperPath = setting.getNewWallpaperPath();
        }

        List<MessageVo> unreadMessages = getUnreadMessages(user);
        List<MessageVo> teacherMessages = getTeacherMessages(user);
		model.addAttribute("unreadSysMes", unreadMessages);
		model.addAttribute("unreadTeaMes", teacherMessages);
		model.addAttribute("newWallpaperPath", newWallpaperPath);


        String path="/sys/new_home/new_second_home";
        return new ModelAndView(path,model.asMap());
    }

    //拿到最顶级的 现有应用中心的应用 level为1的
    private Permission getAppletByPermission(Permission permission) {
        while (permission != null && permission.getLevel() != 1)
            permission=permission.getParent();
        return permission;
    }

    //递归获得有权限的菜单  有子子节点的
    private List<SecondIndexJsonMenu> getMenuJson(PermissionCondition permissionCondition, HttpServletRequest request) {
        List<SecondIndexJsonMenu> menus = new ArrayList<>();
        List<PermissionVo> permissions = permissionService.findPermissionVoByParentCodeAndRoleIds(permissionCondition);
        if (permissions != null && !permissions.isEmpty()) {
            ListIterator<PermissionVo> menuListIterator = permissions.listIterator();
            Set<Integer> permissionIds = new HashSet<>();
            while (menuListIterator.hasNext()) {
                PermissionVo permission = menuListIterator.next();
                //去除没有读权限的
                if ((1 << AclContants.READ & permission.getOperation()) == 0) {
                    menuListIterator.remove();
                    continue;
                } else {
                    if (!permissionIds.add(permission.getId())) continue;
                    SecondIndexJsonMenu menu = new SecondIndexJsonMenu();
                    menu.setName(permission.getName());
                    menu.setDm(permission.getCode());
                    menu.setUrl(permission.getAccessUrl().replace("@(rs_url)", MultiDomainResolver.resolver(request.getServerName(), crPath)).replace("@(target=_blank)", ""));
                    permissionCondition.setParentCode(permission.getCode());
                    menu.setMenu(getMenuJson(permissionCondition, request));
                    menus.add(menu);
                }
            }
        }
        return menus;
    }

    //获取没有子节点的单个菜单
    private SecondIndexJsonMenu getSingleMenuJson(PermissionCondition permissionCondition, HttpServletRequest request) {
        List<PermissionVo> permissions = permissionService.findPermissionVoByParentCodeAndRoleIds(permissionCondition);
        if (permissions != null && !permissions.isEmpty()) {
            SecondIndexJsonMenu menu = new SecondIndexJsonMenu();
            for (PermissionVo permission : permissions) {
                //去除没有读权限的
                if ((1 << AclContants.READ & permission.getOperation()) == 0) continue;
                menu.setName(permission.getName());
                menu.setDm(permission.getCode());
                menu.setUrl(permission.getAccessUrl().replace("@(rs_url)", MultiDomainResolver.resolver(request.getServerName(), crPath)).replace("@(target=_blank)", ""));
                menu.setMenu(new ArrayList<SecondIndexJsonMenu>());
                return menu;
            }
            return null;
        } else {
            return null;
        }
    }


    private List<String> getRoleCodesByUserId(Integer userId) {
        List<String> roleCodes = new ArrayList<>();
        List<UserRole> userRoleList = this.userRoleService.findByUserId(userId);
        if (userRoleList != null && userRoleList.size() > 0) {
            for (UserRole userRole : userRoleList) {
                if (userRole.getRole() != null) {
                    roleCodes.add(userRole.getRole().getCode());
                }
            }
        }
        return roleCodes;
    }

    private List<Integer> getRoleIdsByUserId(Integer userId) {
        List<Integer> roleIds = new ArrayList<>();
        List<UserRole> userRoleList = this.userRoleService.findByUserId(userId);
        if (userRoleList != null && userRoleList.size() > 0) {
            for (UserRole userRole : userRoleList) {
                if (userRole.getRole() != null) {
                    roleIds.add(userRole.getRole().getId());
                }
            }
        }
        return roleIds;
    }

    @RequestMapping("/getCrPath")
    @ResponseBody
    public String getCrPath(HttpServletRequest request){
        return MultiDomainResolver.resolver(request.getServerName(),crPath);
    }

    private void conditionFilter(UserInfo user, Condition condition) {
		Integer appId = condition.getAppId();
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}

    @SuppressWarnings("deprecation")
	private List<MessageVo>  getMessage(List<MessageVo> volist){
		List<MessageVo> resoults = new ArrayList<MessageVo>();
		for(Message message : volist){
			MessageVo vo = new MessageVo();
			BeanUtils.copyProperties(message, vo);
			//设置多久之前
			Date nowDate = new Date();
			Date postTime =  vo.getPostTime();
			Long longAgo = nowDate.getTime() - postTime.getTime();
			int year = nowDate.getYear() - postTime.getYear();
			long day = longAgo / (24 * 60 * 60 * 1000);
		    long hour = (longAgo / (60 * 60 * 1000) - day * 24);
		    long min = ((longAgo / (60 * 1000)) - day * 24 * 60 - hour * 60);
		    long s = (longAgo / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		    String ago = null;
		    if(year == 0){
		    	if(day == 0){
		    		if(hour == 0){
		    			if(min == 0){
		    				ago = s + "秒前";
		    			}else{
				    		ago = min + "分钟前";
				    	}
			    	}else{
			    		ago = hour + "小时前";
			    	}
		    	}else{
		    		ago = day + "天前";
		    	}
		    }else{
		    	ago = year + "年前";
		    }
		    vo.setAgo(ago);
		    resoults.add(vo);
		}
		return resoults;
	}

    private List<MessageVo> getUnreadMessages(UserInfo user){
    	Condition condition = new Condition();
        conditionFilter(user, condition);
		condition.setRecordStatus(StatusTypeContans.ZERO);
		condition.setReceiverId(user.getId());
		condition.setReadStatus(StatusTypeContans.ZERO);
		condition.setTeacherMessage(false);
		List<MessageVo>  unreadList = messageService.findMessageByCondition(condition);
		List<MessageVo> unreadMessages = getMessage(unreadList);//获取未读的系统消息
		condition.setTeacherMessage(true);//true -- 表示为老师消息   false--表示为系统消息
		List<MessageVo> unreadTeacherList = messageService.findMessageByCondition(condition);
		List<MessageVo> teacherMessages = getMessage(unreadTeacherList);//获取未读的教师消息
		condition.setReadStatus(StatusTypeContans.ONE);//设置为已读
		condition.setTeacherMessage(false);
		List<MessageVo> readedList = messageService.findMessageByCondition(condition);
		List<MessageVo> readedMessages = getMessage(readedList);//获取已读的系统消息
		for (MessageVo vo1 : unreadMessages) {
			vo1.setNewPageNum(1);
		}
		for (MessageVo vo1 : teacherMessages) {
			vo1.setNewPageNum(1);
		}
		return unreadMessages;
    }

    private List<MessageVo> getTeacherMessages(UserInfo user){
    	Condition condition = new Condition();
        conditionFilter(user, condition);
		condition.setRecordStatus(StatusTypeContans.ZERO);
		condition.setReceiverId(user.getId());
		condition.setReadStatus(StatusTypeContans.ZERO);
		condition.setTeacherMessage(false);
		List<MessageVo>  unreadList = messageService.findMessageByCondition(condition);
		List<MessageVo> unreadMessages = getMessage(unreadList);//获取未读的系统消息
		condition.setTeacherMessage(true);//true -- 表示为老师消息   false--表示为系统消息
		List<MessageVo> unreadTeacherList = messageService.findMessageByCondition(condition);
		List<MessageVo> teacherMessages = getMessage(unreadTeacherList);//获取未读的教师消息
		condition.setReadStatus(StatusTypeContans.ONE);//设置为已读
		condition.setTeacherMessage(false);
		List<MessageVo> readedList = messageService.findMessageByCondition(condition);
		List<MessageVo> readedMessages = getMessage(readedList);//获取已读的系统消息
		for (MessageVo vo1 : unreadMessages) {
			vo1.setNewPageNum(1);
		}
		for (MessageVo vo1 : teacherMessages) {
			vo1.setNewPageNum(1);
		}
		return teacherMessages;
    }
}
