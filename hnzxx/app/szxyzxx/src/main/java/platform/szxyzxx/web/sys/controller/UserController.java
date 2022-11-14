package platform.szxyzxx.web.sys.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.im.model.ImAccount;
import platform.education.im.service.IMService;
import platform.education.user.contants.GroupContants;
import platform.education.user.model.*;
import platform.education.user.service.UserWebService;
import platform.education.user.vo.*;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.contants.ServiceContants;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:AccountController.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 *
 * @Function 功能描述：账号管理控制器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年8月7日
 */

@RequestMapping(value = "/sys/user")
public class UserController extends BaseController {

    private final static String viewBasePath = "/sys/user";

    @Autowired
    @Qualifier("imService")
    private IMService imService;

    @Resource(name = "userWebService")
    private UserWebService userWebService;

    @Autowired
    @Qualifier("schoolUserService")
    private SchoolUserService schoolUserService;

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "stuName", required = false) String stuName,
            @ModelAttribute("condition") UserCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        if(state==null){
            state=0;
        }
        String viewPath;
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        conditionFilter(user, condition);
        if (type == null || type == 0) {
            //2022 ，11，10 新增用户状态查询，修改之前的查询方法
            List<TeacherVo> voList=teacherService.findByUser(name,state,condition.getUserName(),condition.getMobile(),page);
/*
            TeacherCondition teacherCondition = new TeacherCondition();
            teacherConditionFilter(user, teacherCondition);
            if (!StringUtils.isEmpty(condition.getUserName())) {
                teacherCondition.setUserName(condition.getUserName());
                teacherCondition.setUserNameLike(true);
            }
            if (name != null && !"".equals(name)) {
                teacherCondition.setName(name);
                teacherCondition.setNameLike(true);
            }
            //2016-11-9添加号码查询
            if (condition != null && condition.getMobile() != null) {
                teacherCondition.setMobile(condition.getMobile());
            }
            List<Teacher> accounts = teacherService.findTeacherByCondition(teacherCondition, page, order);
            List<TeacherVo> voList = new ArrayList<TeacherVo>();
            if (accounts.size() > 0) {
                for (Teacher teacher : accounts) {
                    TeacherVo vo = new TeacherVo();
                    School school = schoolService.findSchoolById(teacher.getSchoolId());
                    if (school != null) {
                        vo.setSchoolName(school.getName());
                    } else {
                        vo.setSchoolName("找不到对应的学校");
                    }
                    BeanUtils.copyProperties(teacher, vo);

                    User u = userService.findUserById(teacher.getUserId());
                    if (u != null) {
                        vo.setUserState(u.getState());
                        //2022，11月10日 新增用户状态查询,由于源码无法更改，加此判断
                        if(u.getState().equals(state)){
                            voList.add(vo);
                        }
                    }
                }
            }
            page.setTotalRows(voList.size());*/
            model.addAttribute("accounts", voList);
        } else if (type == 1) {
            StudentCondition studentCondition = new StudentCondition();
            studentConditionFilter(user, studentCondition);
            if (!StringUtils.isEmpty(condition.getUserName())) {
                studentCondition.setUserName(condition.getUserName());
                studentCondition.setUserNameLike(true);
            }
            if (name != null && !"".equals(name)) {
                studentCondition.setName(name);
                studentCondition.setNameLike(true);
            }
            studentCondition.setStudyState("01");
            List<Student> accounts = studentService.findStudentByOnlyCondition(studentCondition, page, order);
            List<StudentVo> voList = new ArrayList<StudentVo>();
            if (accounts.size() > 0) {
                for (Student student : accounts) {
                    StudentVo vo = new StudentVo();
                    School school = schoolService.findSchoolById(student.getSchoolId());
                    if (school != null) {
                        vo.setSchoolName(school.getName());
                    } else {
                        vo.setSchoolName("找不到对应的学校");
                    }
                    BeanUtils.copyProperties(student, vo);
                    User u = userService.findUserById(student.getUserId());
                    if (u != null) {
                        vo.setUserState(u.getState());
                        //2022，11月10日 新增用户状态查询,由于源码无法更改，加此判断
                        if(u.getState().equals(state)){
                            vo.setUserState(u.getState());
                        }
                    }
                    voList.add(vo);
                }
            }
            model.addAttribute("accounts", voList);
        } else if (type == 2) {
            List<ParentStudent> voList=parentProxyService.findByUser(stuName,name,state,condition.getUserName(),condition.getMobile(),page);
/*
            ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
            parentConditionFilter(user, parentStudentCondition);
            if (!StringUtils.isEmpty(condition.getUserName())) {
                parentStudentCondition.setUserName(condition.getUserName());
            }
            if (name != null && !"".equals(name)) {
                parentStudentCondition.setName(name);
            }

            if (stuName != null && !"".equals(stuName)) {
                parentStudentCondition.setStuName(stuName);
            }
            if (condition != null && condition.getMobile() != null) {
                parentStudentCondition.setMobile(condition.getMobile());
            }
            List<ParentStudent> list = parentProxyService.findParentByGroupCondition(parentStudentCondition, page);
            List<ParentStudent> voList = new ArrayList<ParentStudent>();
            if (list.size() > 0) {
                for (ParentStudent parent : list) {
                    ParentVo vo = new ParentVo();
//                    BeanUtils.copyProperties(parent, vo);
                    User u = userService.findUserById(parent.getUserId());
                    if (u != null) {
                        parent.setUserState(u.getState());
                    }
                    voList.add(parent);
                }
            }*/
            System.out.println(voList.size());
            model.addAttribute("accounts", voList);
        }
        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        //model.addAttribute("accounts", accounts);
        model.addAttribute("roleType", type == null ? "0" : type);
        return new ModelAndView(viewPath, model.asMap());
    }


    @RequestMapping(value = "/indexStu")
    public ModelAndView indexStu(
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") ParentStudentCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath;

        model.addAttribute("accounts", parentProxyService.findParentAccountAndStuInfo(condition,page));

        if ("list".equals(sub)) {
            viewPath = structurePath("/listStu");
        } else {
            viewPath = structurePath("/indexStu");
        }
        return new ModelAndView(viewPath, model.asMap());
    }


    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator() {
        return new ModelAndView(structurePath("/input"));
    }


    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(UserVo userVo, @CurrentUser UserInfo userInfo) {
        User user = null;
        UserResult result = null;
        try {
            Integer schoolId = userVo.getGroupOwnerId();
            if (schoolId == null) {
                userVo.setGroupOwnerId(userInfo.getSchoolId());
            }
            userVo.setAppId(SysContants.SYSTEM_APP_ID);
            result = this.userService.addUser(userVo);
            user = result.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != null && UserResult.STATUS_SUCCESS.equals(result.getStatusCode()) ? new ResponseInfomation(user.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation(result.getStatusCode());
    }

    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(@RequestParam(value = "id", required = true) Integer id,
                               @RequestParam(value = "isCK", required = false) String isCK, Model model) {
        User account = this.userService.findUserById(id);
        if (isCK.equals("disable")) {
            model.addAttribute("isCK", isCK);
        }
        model.addAttribute("account", account);
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    @RequestMapping(value = "/bindingnames", method = RequestMethod.GET)
    public ModelAndView bingingnames(@RequestParam(value = "id", required = true) Integer id, Model model) {
        UserBindingCondition condition = new UserBindingCondition();
        condition.setUserId(id);
        List<UserBinding> ubs = this.userBindingService.findUserBindingByCondition(condition, null, null);
        model.addAttribute("items", ubs);
        return new ModelAndView(structurePath("/ub_index"), model.asMap());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, User user) {
        if (user != null) {
            user.setId(id);
        }
        String result = this.userService.abandon(user);
        deleteUserInfo(result, user.getId());
        return result;
    }

    /**
     * 删除用户角色、绑定账号等信息
     */
    private void deleteUserInfo(String result, Integer userId) {
        if (ResponseInfomation.OPERATION_SUC.equals(result)) {
            List<UserRole> userRoleList = userRoleService.findByUserId(userId);
            for (UserRole userRole : userRoleList) {
                userRole.setIsDeleted(true);
                userRole.setModifyDate(new Date());
                userRoleService.modify(userRole);
            }
            List<UserBinding> userBindingList = userBindingService.findByUserId(userId);
            for (UserBinding binding : userBindingList) {
                binding.setIsDeleted(true);
                binding.setModifyDate(new Date());
                userBindingService.modify(binding);
            }
            List<SchoolUser> schoolUserList = schoolUserService.findSchoolUserListByUserId(userId);
            for (SchoolUser schoolUser : schoolUserList) {
                schoolUser.setIsDeleted(true);
                schoolUser.setModifyDate(new Date());
                schoolUserService.modify(schoolUser);
            }
            List<Teacher> teacherList = teacherService.findTeacherByUserId(userId);
            for (Teacher teacher : teacherList) {
                teacherService.deleteTeacherAllInfoById(teacher.getId(), SysContants.USER_TYPE_TEACHER);
            }
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(@PathVariable(value = "id") Integer id, User user) {
        user.setId(id);
        user = this.userService.modify(user);
        return user != null ? new ResponseInfomation(ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    @RequestMapping(value = "/editor/pwd", method = RequestMethod.GET)
    @ResponseBody
    public String resetPwd(@RequestParam("ids[]") Integer[] ids) {
        return this.userService.modifyUsersPassword(ids, SysContants.DEFAULT_PASSWORD);
    }

    @RequestMapping(value = "checker", method = RequestMethod.GET)
    @ResponseBody
    public boolean checker(
            @RequestParam(value = "dxlx", required = false) String dxlx,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "id", required = false) Integer id) {
        boolean isExist = false;
        if ("userName".equals(dxlx)) {
            UserBinding ub = this.userBindingService.findByBindingName(username);
//			User user = this.userService.findUserByUsername(username);
            if (ub != null) {
                Integer currentId = ub.getUserId();
                if (currentId != null && currentId.equals(id)) {
                    isExist = true;
                } else {
                    isExist = false;
                }
            } else {
                isExist = true;
            }
        }
        return isExist;
    }

    @RequestMapping(value = "/push/register")
    @ResponseBody
    public List<ResponseInfomation> registerUser(@RequestParam("usernames[]") String[] usernames) {
        List<ResponseInfomation> repos = new ArrayList<ResponseInfomation>();
        for (String username : usernames) {
            try {
                User user = this.userService.findUserByUsername(username);
                if (user != null) {
                    ImAccount result = this.imService.createIMAccountForUser(user.getId(), "");
                    repos.add(new ResponseInfomation(username, result.getImAccountStatus()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return repos;
    }

    @RequestMapping(value = "/toBatchReg")
    public ModelAndView toBatchReg(
            @RequestParam(value = "dm", required = false) String dm,
            Model model) {
//		SchoolCondition schoolCondition = new SchoolCondition();
        List<School> schools = this.schoolService.findSchoolByCondition(null, null, null);
        model.addAttribute("schools", schools);
        return new ModelAndView(structurePath("/batch_reg"), model.asMap());
    }

    @RequestMapping(value = "/batchReg")
    @ResponseBody
    public List<ResponseInfomation> batchReg(
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "schoolId", required = false) Integer schoolId,
            @ModelAttribute("condition") UserCondition condition,
            @ModelAttribute("order") Order order,
            Model model) {
        String defFailResult = "{'error':'duplicate_unique_property_exists','timestamp':0,'duration':0,'exception':'','error_description':'','statusCode':400}";
        String faleResult = "duplicate_unique_property_exists";
        List<ResponseInfomation> repos = new ArrayList<ResponseInfomation>();
        UserWebCondition userWebCondition = new UserWebCondition();
        userWebCondition.setAccountType(ServiceContants.AC_TYPE_YONG_HU);
        userWebCondition.setOpenResult(ServiceContants.OPEN_SUCCESS);
        userWebCondition.setServiceType(ServiceContants.SERVICE_TYPE_HUAN_XIN);
        if (type == null || type == 0) {
            TeacherCondition teacherCondition = new TeacherCondition();
            if (schoolId != null) {
                teacherCondition.setSchoolId(schoolId);
            }
            if (!StringUtils.isEmpty(condition.getUserName())) {
                teacherCondition.setUserName(condition.getUserName());
                teacherCondition.setUserNameLike(true);
            }
            if (name != null && !"".equals(name)) {
                teacherCondition.setName(name);
                teacherCondition.setNameLike(true);
            }
            List<Teacher> tAccounts = teacherService.findTeacherByCondition(teacherCondition, null, order);
            if (tAccounts.size() > 0) {
                for (Teacher teacher : tAccounts) {
                    userWebCondition.setAccount(teacher.getUserName());
                    List<UserWeb> list = userWebService.findUserWebByCondition(userWebCondition);
                    if (list.size() == 0) {
                        try {
                            ImAccount result = this.imService.createIMAccountForUser(teacher.getUserId(), teacher.getName());
                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode jsonNode = objectMapper.readTree(result.getImAccountStatus());
                            int statusCode = jsonNode.findValue("statusCode").asInt();
                            ;
                            String errMsg = jsonNode.findValue("error").asText();
                            if (statusCode == 200) {
                                userWebCondition.setOpenResult(ServiceContants.OPEN_FAIL);
                                list = userWebService.findUserWebByCondition(userWebCondition);
                                if (list.size() > 0) {
                                    UserWeb uw = list.get(0);
                                    uw.setOpenResult(ServiceContants.OPEN_SUCCESS);
                                    userWebService.modify(uw);
                                } else {
                                    UserWeb uw = new UserWeb();
                                    uw.setAccount(teacher.getUserName());
                                    uw.setAccountType(ServiceContants.AC_TYPE_YONG_HU);
                                    uw.setOpenResult(ServiceContants.OPEN_SUCCESS);
                                    uw.setSchoolId(teacher.getSchoolId());
                                    uw.setServiceType(ServiceContants.SERVICE_TYPE_HUAN_XIN);
                                    userWebService.add(uw);
                                }
                            } else if (statusCode == 400 && faleResult.equals(errMsg)) {
                                UserWeb uw = new UserWeb();
                                uw.setAccount(teacher.getUserName());
                                uw.setAccountType(ServiceContants.AC_TYPE_YONG_HU);
                                uw.setOpenResult(ServiceContants.OPEN_SUCCESS);
                                uw.setSchoolId(teacher.getSchoolId());
                                uw.setServiceType(ServiceContants.SERVICE_TYPE_HUAN_XIN);
                                userWebService.add(uw);
                            }
                            repos.add(new ResponseInfomation(teacher.getUserName() + "(教师)", result.getImAccountStatus()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        repos.add(new ResponseInfomation(teacher.getUserName() + "(教师)", defFailResult));
                    }
                }
            }
        }
        if (type == null || type == 1) {
            StudentCondition studentCondition = new StudentCondition();
            if (schoolId != null) {
                studentCondition.setSchoolId(schoolId);
            }
            if (!StringUtils.isEmpty(condition.getUserName())) {
                studentCondition.setUserName(condition.getUserName());
                studentCondition.setUserNameLike(true);
            }
            if (name != null && !"".equals(name)) {
                studentCondition.setName(name);
                studentCondition.setNameLike(true);
            }
            List<Student> sAccounts = studentService.findStudentByOnlyCondition(studentCondition, null, order);
            if (sAccounts.size() > 0) {
                for (Student student : sAccounts) {
                    userWebCondition.setAccount(student.getUserName());
                    List<UserWeb> list = userWebService.findUserWebByCondition(userWebCondition);
                    if (list.size() == 0) {
                        try {
                            ImAccount result = this.imService.createIMAccountForUser(student.getUserId(), student.getName());
                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode jsonNode = objectMapper.readTree(result.getImAccountStatus());
                            int statusCode = jsonNode.findValue("statusCode").asInt();
                            ;
                            String errMsg = jsonNode.findValue("error").asText();
                            if (statusCode == 200) {
                                userWebCondition.setOpenResult(ServiceContants.OPEN_FAIL);
                                list = userWebService.findUserWebByCondition(userWebCondition);
                                if (list.size() > 0) {
                                    UserWeb uw = list.get(0);
                                    uw.setOpenResult(ServiceContants.OPEN_SUCCESS);
                                    userWebService.modify(uw);
                                } else {
                                    UserWeb uw = new UserWeb();
                                    uw.setAccount(student.getUserName());
                                    uw.setAccountType(ServiceContants.AC_TYPE_YONG_HU);
                                    uw.setOpenResult(ServiceContants.OPEN_SUCCESS);
                                    uw.setSchoolId(student.getSchoolId());
                                    uw.setServiceType(ServiceContants.SERVICE_TYPE_HUAN_XIN);
                                    userWebService.add(uw);
                                }
                            } else if (statusCode == 400 && faleResult.equals(errMsg)) {
                                UserWeb uw = new UserWeb();
                                uw.setAccount(student.getUserName());
                                uw.setAccountType(ServiceContants.AC_TYPE_YONG_HU);
                                uw.setOpenResult(ServiceContants.OPEN_SUCCESS);
                                uw.setSchoolId(student.getSchoolId());
                                uw.setServiceType(ServiceContants.SERVICE_TYPE_HUAN_XIN);
                                userWebService.add(uw);
                            }
                            repos.add(new ResponseInfomation(student.getUserName() + "(学生)", result.getImAccountStatus()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    repos.add(new ResponseInfomation(student.getUserName() + "(学生)", defFailResult));
                }
            }
        }
        if (type == null || type == 2) {
            ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
            if (schoolId != null) {
                parentStudentCondition.setSchoolId(schoolId);
            }
            if (!StringUtils.isEmpty(condition.getUserName())) {
                parentStudentCondition.setUserName(condition.getUserName());
            }
            if (name != null && !"".equals(name)) {
                parentStudentCondition.setName(name);
            }
            List<Parent> list = parentProxyService.findParentByGroupCondition(parentStudentCondition);
            if (list.size() > 0) {
                for (Parent parent : list) {
                    userWebCondition.setAccount(parent.getUserName());
                    List<UserWeb> uwList = userWebService.findUserWebByCondition(userWebCondition);
                    if (uwList.size() == 0) {
                        try {
                            ImAccount result = this.imService.createIMAccountForUser(parent.getUserId(), parent.getName());
                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode jsonNode = objectMapper.readTree(result.getImAccountStatus());
                            int statusCode = jsonNode.findValue("statusCode").asInt();
                            ;
                            String errMsg = jsonNode.findValue("error").asText();
                            if (statusCode == 200) {
                                userWebCondition.setOpenResult(ServiceContants.OPEN_FAIL);
                                uwList = userWebService.findUserWebByCondition(userWebCondition);
                                if (list.size() > 0) {
                                    UserWeb uw = uwList.get(0);
                                    uw.setOpenResult(ServiceContants.OPEN_SUCCESS);
                                    userWebService.modify(uw);
                                } else {
                                    UserWeb uw = new UserWeb();
                                    uw.setAccount(parent.getUserName());
                                    uw.setAccountType(ServiceContants.AC_TYPE_YONG_HU);
                                    uw.setOpenResult(ServiceContants.OPEN_SUCCESS);
                                    uw.setServiceType(ServiceContants.SERVICE_TYPE_HUAN_XIN);
                                    userWebService.add(uw);
                                }
                            } else if (statusCode == 400 && faleResult.equals(errMsg)) {
                                UserWeb uw = new UserWeb();
                                uw.setAccount(parent.getUserName());
                                uw.setAccountType(ServiceContants.AC_TYPE_YONG_HU);
                                uw.setOpenResult(ServiceContants.OPEN_SUCCESS);
                                uw.setServiceType(ServiceContants.SERVICE_TYPE_HUAN_XIN);
                                userWebService.add(uw);
                            }
                            repos.add(new ResponseInfomation(parent.getUserName() + "(家长)", result.getImAccountStatus()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    repos.add(new ResponseInfomation(parent.getUserName() + "(家长)", defFailResult));
                }
            }
        }
        return repos;
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private void conditionFilter(UserInfo user, UserCondition condition) {
        Integer groupId = condition.getGroupId();
        Integer appId = condition.getAppId();
        if (user != null) {
            if (groupId == null) {
                condition.setGroupId(user.getGroupId());
            }

            if (appId == null) {
                condition.setAppId(SysContants.SYSTEM_APP_ID);
            }
        }
        condition.setIsDeleted(false);
    }

    private void teacherConditionFilter(UserInfo user, TeacherCondition condition) {
        Integer schoolId = condition.getSchoolId();
        if (user != null) {
            if (schoolId == null) {
                condition.setSchoolId(user.getSchoolId());
            }
        }
        condition.setIsDelete(false);
    }

    private void studentConditionFilter(UserInfo user, StudentCondition condition) {
        Integer schoolId = condition.getSchoolId();
        if (user != null) {
            if (schoolId == null) {
                condition.setSchoolId(user.getSchoolId());
            }
        }
        condition.setIsDelete(false);
    }

    private void parentConditionFilter(UserInfo user, ParentStudentCondition condition) {
        Integer schoolId = condition.getSchoolId();
        if (user != null) {
            if (schoolId == null) {
                condition.setSchoolId(user.getSchoolId());
            }
        }
        condition.setIsDelete(false);
    }

    /**
     * @function 系统缺省角色和菜单同步
     * 如果该用户有学校，那么只能操作自己学校的数据，如果是没有学校（超级管理员）那么可以操作所有学校
     * @date 2016年12月28号
     */
    @RequestMapping(value = "/roleandmenusync")
    public ModelAndView toSyncDefRoleAndMenu(@CurrentUser UserInfo userInfo, Model model) {
        if (userInfo != null && userInfo.getSchoolId() != null) {
            Group group = groupService.findUnique(userInfo.getSchoolId(), GroupContants.TYPE_SCHOOL);
            model.addAttribute("group", group);
        } else {
            SchoolCondition schoolCondition = new SchoolCondition();
            GroupCondition groupCondition = new GroupCondition();
            groupCondition.setAppId(SysContants.SYSTEM_APP_ID);
            groupCondition.setType(GroupContants.TYPE_SCHOOL);
            schoolCondition.setDelete(false);
            List<Group> groupList = schoolService.findGroupBySchoolCondition(groupCondition, schoolCondition, null, null);
            model.addAttribute("groupList", groupList);
        }
        return new ModelAndView(structurePath("/roleandmenusync"), model.asMap());
    }

    /**
     * @param
     * @throws Exception
     * @function 系统缺省角色和菜单同步
     * @date 2016年12月28号
     */
    @RequestMapping(value = "/syncDefRoleAndMenu")
    @ResponseBody
    public List<School> syncDefRoleAndMenu(
            @RequestParam(value = "groupIds", required = false) String groupIds) throws Exception {
        List<School> schoolList = new ArrayList<School>();
        if (groupIds != null && !"".equals(groupIds)) {
            Group group = null;
            String[] groupIdArr = groupIds.split(",");
            try {
                for (String groupId : groupIdArr) {
                    group = groupService.findGroupById(Integer.parseInt(groupId));
                    if (group != null) {
                        Boolean success = roleService.syncDefaultRolesAndMenu(group.getId(), SysContants.SYSTEM_APP_ID);
                        //批量的时候，将同步失败的学校返回前段提示
                        if (!success) {
                            schoolList.add(schoolService.findSchoolById(group.getOwnerId()));
                        }
                    }
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return schoolList;
    }

}
