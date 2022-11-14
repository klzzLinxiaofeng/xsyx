package platform.szxyzxx.web.sys.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.hnzxx.ui.model.Setting;
import platform.education.hnzxx.ui.service.SettingService;
import platform.education.user.model.*;
import platform.education.user.service.AppletOwnerService;
import platform.education.user.service.AppletRoleService;
import platform.education.user.service.AppletService;
import platform.education.user.service.RoleService;
import platform.education.user.vo.*;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;


@Controller
@RequestMapping("/sys/appletRole")
public class AppletRoleController extends BaseController {

    private final static String viewBasePath = "/sys/appletCenter/appletRole";

    @Autowired
    @Qualifier("appletRoleService")
    private AppletRoleService appletRoleService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("appletOwnerService")
    private AppletOwnerService appletOwnerService;

    @Autowired
    @Qualifier("appletService")
    private AppletService appletService;

    @Autowired
    @Qualifier("settingService")
    private SettingService settingService;

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "type", required = false, defaultValue = "school") String type,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") AppletRoleCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        if (!"superAdmin".equalsIgnoreCase(user.getUserName())) {
            condition.setOwnerId(user.getSchoolId());
            if (!"school".equals(type)) type = "school";
        } else {
            model.addAttribute("isSuperAdmin", true);
        }

        String appletName=condition.getAppletName();
        if(appletName!=null&&!"".equals(appletName.trim())){
            condition.setAppletName(appletName.trim());
        }else{
            condition.setAppletName(null);
        }

        String viewPath = null;
        if ("list".equals(sub)) {
            if (condition.getOwnerId() == null) condition.setOwnerId(0);
            condition.setGroupId(getGroupIdBySchoolId(condition.getOwnerId()));
            List<AppletRoleVo> appletRoles = appletRoleService.findAppletRoleVoByCondition(condition, page,null);
            if (appletRoles != null && !appletRoles.isEmpty()) {
                String[] uuids=new String[appletRoles.size()];
                for(int i=0;i<uuids.length;i++){
                    uuids[i]=appletRoles.get(i).getIcon();
                }
                Map<String, FileResult> icons = fileService.findFileByUUIDs(uuids);
                for (AppletRoleVo appletRole : appletRoles) {
                	FileResult image=icons.get(appletRole.getIcon());
                	if(image!=null){
                		appletRole.setIcon(image.getHttpUrl());
                	}
                    appletRole.setRoles(appletRole.getRolesString().split("、"));
                }
            }
            model.addAttribute("items", appletRoles);
            viewPath = structurePath("/list");
        } else {
            model.addAttribute("condition", condition);
            viewPath = structurePath("/index");
        }
        model.addAttribute("type", type);
        model.addAttribute("sub", sub);
        return new ModelAndView(viewPath, model.asMap());
    }


    @RequestMapping(value = "/editor")
    public ModelAndView editor(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "noList", required = false) String noList,
            @ModelAttribute("condition") AppletRoleCondition condition,
            Model model) {
        if (!"superAdmin".equalsIgnoreCase(user.getUserName())) {
            condition.setOwnerId(user.getSchoolId());
        }
        condition.setGroupId(getGroupIdBySchoolId(condition.getOwnerId()));
        String viewPath = structurePath("/editor");
        //找到学校所有的角色授权
        List<AppletRoleVo> appletRoles=appletRoleService.findAppletRoleBySchoolAndApplet(condition,null,null);
        model.addAttribute("items", appletRoles);
        model.addAttribute("condition", condition);
        model.addAttribute("noList", noList);
        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") AppletRoleCondition condition,
            @RequestParam(value = "roleCodes", required = false) String[] roleCodes) {
        if (!"superAdmin".equalsIgnoreCase(user.getUserName())) {
            condition.setOwnerId(user.getSchoolId());
        }
        if (condition.getOwnerId() == 0) {
            condition.setOwnerType(1);
        } else {
            condition.setOwnerType(5);
        }
        return appletRoleService.editAppletRole(condition, roleCodes) ? "success" : "fail";
    }


    @RequestMapping(value = "/roleDel")
    @ResponseBody
    public Object delRoleApplets(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") AppletRoleCondition condition,
            @RequestParam(value = "appletIds") Integer[] appletIds) {
        if (!"superAdmin".equalsIgnoreCase(user.getUserName())) {
            condition.setOwnerId(user.getSchoolId());
        }
        if (condition.getOwnerId() == null) {
            condition.setOwnerId(0);
        }
        if (appletIds != null && appletIds.length > 0) {
            AppletRoleCondition roleCondition = new AppletRoleCondition();
            roleCondition.setOwnerId(condition.getOwnerId());
            roleCondition.setRoleCode(condition.getRoleCode());
            for (int appletId : appletIds) {
                roleCondition.setAppletId(appletId);
                List<AppletRole> roles = appletRoleService.findAppletRoleByCondition(roleCondition);
                if (roles != null && !roles.isEmpty()) {
                    for (AppletRole role : roles) {
                        appletRoleService.remove(role);
                    }
                }
            }
        }
        return "success";
    }


    @RequestMapping(value = "/showApplets")
    public ModelAndView showApplets(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @ModelAttribute("condition") AppletRoleCondition condition,
            Model model) {
        if (!"superAdmin".equalsIgnoreCase(user.getUserName())) {
            condition.setOwnerId(user.getSchoolId());
        } else if (condition.getOwnerId() == null) {
            condition.setOwnerId(0);
        }

        //当前角色已有哪些应用
        List<AppletRole> appletRoles = appletRoleService.findAppletRoleByCondition(condition);
        Integer[] exsistIds = null;
        if (appletRoles != null && !appletRoles.isEmpty()) {
            exsistIds = new Integer[appletRoles.size()];
            for (int i = 0; i < exsistIds.length; i++) {
                exsistIds[i] = appletRoles.get(i).getAppletId();
            }
        }

        //学校的
        List<Applet> appletList = null;
        Map<Integer, Applet> applets = null;
        if (condition.getOwnerId() != 0) {
            AppletOwnerCondition ownerCondition = new AppletOwnerCondition();
            ownerCondition.setOwnerId(condition.getOwnerId());
            ownerCondition.setLineType(1);
            ownerCondition.setIsDeleted(false);
            appletList = appletService.findAppletByOwnerCondition(ownerCondition, null, Order.desc("id"));
            //平台的
        } else {
            AppletCondition appletCondition = new AppletCondition();
            appletCondition.setIsDeleted(false);
            appletCondition.setLineType(1);
            appletList = appletService.findAppletByCondition(appletCondition, null, Order.desc("id"));
            appletCondition.setLineType(2);
            appletList.addAll(appletService.findAppletByCondition(appletCondition, null, Order.desc("id")));
        }

        //图标处理 剔除已存在应用
        if (appletList != null && !appletList.isEmpty()) {
            applets = new LinkedHashMap<>(appletList.size());
            String[] uuids = new String[appletList.size()];
            for (int i = 0; i < uuids.length; i++) {
                uuids[i] = appletList.get(i).getIcon();
            }
            Map<String, FileResult> icons = fileService.findFileByUUIDs(uuids);
            for (Applet applet : appletList) {
            	FileResult image=icons.get(applet.getIcon());
            	if(image!=null){
            		applet.setIcon(image.getHttpUrl());
            	}
            	applets.put(applet.getId(), applet);
            }
            if (exsistIds != null) {
                for (Integer exsistId : exsistIds) {
                    applets.remove(exsistId);
                }
            }
        }
        model.addAttribute("applets", applets);
        model.addAttribute("condition", condition);
        return new ModelAndView(structurePath("/showApplets"), model.asMap());
    }

    @RequestMapping(value = "/addAppletRole")
    @ResponseBody
    public Object addAppletRole(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") AppletRoleCondition condition,
            @RequestParam(value = "appletIds") Integer[] appletIds) {
        if (!"superAdmin".equalsIgnoreCase(user.getUserName())) {
            condition.setOwnerId(user.getSchoolId());
        }
        if (condition.getOwnerId() == 0) {
            condition.setOwnerType(1);
        } else {
            condition.setOwnerType(5);
        }
        AppletRole appletRole = new AppletRole();
        appletRole.setId(null);
        appletRole.setOwnerId(condition.getOwnerId());
        appletRole.setOwnerType(condition.getOwnerType());
        appletRole.setRoleCode(condition.getRoleCode());
        for (Integer appletId : appletIds) {
            appletRole.setId(null);
            appletRole.setAppletId(appletId);
            appletRoleService.add(appletRole);
        }
        return "success";
    }


    @RequestMapping(value = "/showRole")
    public ModelAndView showRole(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "roleCode", required = false) String roleCode,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") AppletRoleCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        if (!"superAdmin".equalsIgnoreCase(user.getUserName())) {
            condition.setOwnerId(user.getSchoolId());
        } else if (condition.getOwnerId() == null) {
            condition.setOwnerId(0);
        }

        String appletName=condition.getAppletName();
        if(appletName!=null&&!"".equals(appletName.trim())){
            condition.setAppletName(appletName.trim());
        }else{
            condition.setAppletName(null);
        }

        if ("list".equals(sub)) {
            AppletRoleCondition appletRoleCondition = new AppletRoleCondition();
            appletRoleCondition.setOwnerId(condition.getOwnerId());
            appletRoleCondition.setRoleCode(roleCode);
            appletRoleCondition.setGroupId(getGroupIdBySchoolId(appletRoleCondition.getOwnerId()));
            appletRoleCondition.setAppletName(condition.getAppletName());
            List<AppletRoleVo> roleVos = appletRoleService.findAppletRoleVoByCondition(appletRoleCondition, page, null);

            if (roleVos != null && !roleVos.isEmpty()) {
                String[] uuids=new String[roleVos.size()];
                for(int i=0;i<uuids.length;i++){
                    uuids[i]=roleVos.get(i).getIcon();
                }
                Map<String, FileResult> icons = fileService.findFileByUUIDs(uuids);
                for (AppletRoleVo appletRole : roleVos) {
                	FileResult image=icons.get(appletRole.getIcon());
                	if(image!=null){
                		appletRole.setIcon(image.getHttpUrl());
                	}
                }
            }

            model.addAttribute("items", roleVos);
            viewPath = structurePath("/show_list");
        } else {
            RoleCondition roleCondition = new RoleCondition();
            //学校的话先找到groupId
            roleCondition.setGroupId(getGroupIdBySchoolId(condition.getOwnerId()));
            //找到学校所有的角色
            List<Role> roles = roleService.findRoleByCondition(roleCondition, null, Order.desc("id"));
            model.addAttribute("roles", roles);
            viewPath = structurePath("/show_index");
        }
        condition.setRoleCode(roleCode);
        model.addAttribute("condition", condition);
        model.addAttribute("sub", sub);
        return new ModelAndView(viewPath, model.asMap());
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private Integer getGroupIdBySchoolId(Integer schoolId) {
        if (schoolId != 0) {
            GroupCondition groupCondition = new GroupCondition();
            groupCondition.setOwnerId(schoolId);
            return groupService.findGroupByCondition(groupCondition, null, null).get(0).getId();
        } else {
            //0为默认角色组
            return schoolId;
        }
    }
}
