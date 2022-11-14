package platform.szxyzxx.web.teach.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.education.clazz.model.BwUserInfo;
import platform.education.clazz.service.BwUserInfoService;
import platform.education.generalTeachingAffair.dao.DepartmentDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.oa.utils.UtilDate;
import platform.education.user.model.Group;
import platform.education.user.model.Profile;
import platform.education.user.model.User;
import platform.education.user.model.UserRole;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.PropertiesUtil;
import platform.szxyzxx.web.teach.vo.ErroMessageVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/teach/teacher")
public class TeacherController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);
    /**
     * 食堂接口
     */
    private static String fileName;
    private static String address;
    private static String canteen;
    /**
     * 闸机接口
     */
    private static String HikvisionHost;
    private static String HikvisionAppKey;
    private static String HikvisionAppSecret;
    private static String HikvisionAddPersonUrl;
    /**
     * 图书馆接口
     */
    private static String libraryACnteen;
    private static String libraryLogin;
    private static String libraryCreate;

    static {
        fileName = "System.properties";
        // 食堂
        address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");
        //闸机
        HikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
        HikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
        HikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
        HikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");

        // 图书馆
        libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
        libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
        libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");
    }


    @Autowired
    @Qualifier("pjTeacherSalaryService")
    private PjTeacherSalaryService salaryService;

    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    @Qualifier("pjTeacherSalaryFieldService")
    private PjTeacherSalaryFieldService salaryFieldService;

    @Autowired
    @Qualifier("bwUserInfoService")
    private BwUserInfoService bwUserInfoService;

    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;
    @Autowired
    private  DepartmentDao departmentDao;
    @Autowired
    @Qualifier("schoolInitService")
    private SchoolInitService schoolInitService;
    /**
     * 教师列表
     *
     * @return
     */
    @RequestMapping("/teacherList")
    public ModelAndView getTeacherList(
            @CurrentUser UserInfo user,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "dm", required = false) String dm,
            @ModelAttribute("teacherCondition") TeacherCondition teacherCondition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        ModelAndView mav = null;
        String viewPath = "";
        try {
            mav = new ModelAndView();
            teacherCondition.setSchoolId(user.getSchoolId());
            teacherCondition.setIsDelete(false);
            teacherCondition.setNameLike(true);
            List<platform.szxyzxx.web.bbx.vo.TeacherDetailInfoVo> list
                    = new ArrayList<platform.szxyzxx.web.bbx.vo.TeacherDetailInfoVo>();
            List<Teacher> teacherList = teacherService.findTeacherByCondition(teacherCondition, page, Order.desc("create_date"));
            for (Teacher teacher : teacherList) {
                TeacherDetailInfoVo teacherDetailInfo = new TeacherDetailInfoVo();
                teacherDetailInfo = teacherService.findTeacherDetailInfoById(teacher.getId());
                teacherDetailInfo.setJobNumber(teacher.getEmpCode());
                platform.szxyzxx.web.bbx.vo.TeacherDetailInfoVo t = new platform.szxyzxx.web.bbx.vo.TeacherDetailInfoVo();
                try {
                    HttpRequestConfig config = HttpRequestConfig.create().url("http://10.170.76.29:8090/api/mobile/VipUser/list?emp_code="+teacher.getEmpCode())
                            .addHeader("Content-Type", "application/json");
                    HttpRequestResult httpRequestResult = HttpClientUtils.get(config);
                    String responseText = httpRequestResult.getResponseText();
                    if (!StrUtil.hasEmpty(responseText)) {
                        JSONObject jsonObject = JSONObject.parseObject(responseText);
                        List<Map<String, Object>> list2 = (List<Map<String, Object>>) jsonObject.get("data");
                        if(list2.size()==1){
                            teacherDetailInfo.setShiTangCard(list2.get(0).get("emp_card").toString());
                        }else{
                            log.error("请求食堂接口,url: {}","未获取到数据");
                        }
                    }
                } catch (Exception e) {
                    log.error("请求食堂接口失败,url: {}","获取卡号");
                }
                org.springframework.beans.BeanUtils.copyProperties(teacherDetailInfo, t);
                BwUserInfo userInfo = this.bwUserInfoService.findBwUserInfoByUserId(teacherDetailInfo.getUserId());
                if (userInfo != null) {
                    t.setCharacteristic(userInfo.getCharacteristic());
                }
                list.add(t);
            }
            if ("list".equals(sub)) {
                viewPath = "/teach/teacher/list";

            } else {
                viewPath = "/teach/teacher/teacherList";
            }
//			mav.addObject("teacherList", teacherList);
            mav.addObject("teacherList", list);
            mav.setViewName(viewPath);
        } catch (Exception e) {
            log.info("查询教师列表异常...");
        }
        return mav;
    }

    /**
     * 修改教师
     *
     * @param id
     * @return
     */
    @RequestMapping("/modifyTeacher")
    public ModelAndView modifyTeacher(
            @RequestParam(value = "id", required = true) String id,
            @CurrentUser UserInfo user
    ) {
        ModelAndView mav = new ModelAndView();
        TeacherDetailInfoVo teacherDetailInfo = teacherService.findTeacherDetailInfoById(Integer.parseInt(id));
        if (teacherDetailInfo != null && teacherDetailInfo.getUserId() != null) {
            TeacherAssistCondition teacherAssistCondition = new TeacherAssistCondition();
            teacherAssistCondition.setUserId(teacherDetailInfo.getUserId());
            teacherAssistCondition.setSchoolId(teacherDetailInfo.getSchoolId());
            List<TeacherAssist> t = teacherAssistService.findTeacherAssistByCondition(teacherAssistCondition);
            if (t != null && t.size() > 0) {
                TeacherAssist teacherAssist = t.get(0);
                mav.addObject("teacherAssist", teacherAssist);
            }

            //2017-2-16判断当前教师不是编辑的教师，不能查看薪资信息
            //判断角色是否为校长 管理员 领导
            List<UserRole> userRoleList = userRoleService.findByUserId(user.getId());
            Boolean canShow = false;
            if (userRoleList != null && userRoleList.size() > 0) {
                for (UserRole ur : userRoleList) {
                    if (ur != null && ur.getRole() != null) {
                        if (ur.getRole().getCode().equals("SCHOOL_MASTER") || ur.getRole().getCode().equals("SCHOOL_MANAGER") || ur.getRole().getCode().equals("SCHOOL_LEADER")) {
                            canShow = true;
                            break;
                        }
                    }
                }
            }
            //判断用户类型是否为校级管理员
            if (user != null) {
                if (user.getUserTypes() != null) {
                    String[] types = user.getUserTypes().split(",");
                    if (types != null && types.length > 0) {
                        for (String type : types) {
                            if (type != null && type.trim().equals(SysContants.USER_TYPE_ADMIN)) {
                                canShow = true;
                                break;
                            }
                        }
                    }
                }

                if (user.getTeacherId() != null && user.getTeacherId().toString().equals(id.toString())) {
                    canShow = true;
                }
            }
            if (canShow) {
                setSalaryDetail(teacherDetailInfo);
                mav.addObject("canShow", "canShow");
            }
            List<TeamTeacher> teamTeachers=teamTeacherService.getMyTeamTeacher(Integer.parseInt(id),user.getSchoolYear());
            mav.addObject("teamTeachers", teamTeachers);
          /*  TeamTeacherTmpController teamTeacherTmpController= new TeamTeacherTmpController();
            teamTeacherTmpController.deleteTmp(user,1,1);*/
        }
        mav.addObject("teacherDetailInfo", teacherDetailInfo);
        mav.setViewName("/teach/teacher/modifyTeacher");
        return mav;
    }
    /*
    * 删除任课班级
    */
    @RequestMapping(value="/delete")
    @ResponseBody
    public String deleteTmp(
            @CurrentUser UserInfo user,
            @RequestParam(value="id", required = false) Integer id,
            @RequestParam(value="teamTeacherId", required = false) Integer teamTeacherId){
        Integer schoolId = user.getSchoolId();
        Group group = groupService.findUnique(schoolId, "1");
        TeamTeacher teamTeacher = teamTeacherService.findTeamTeacherById(teamTeacherId);

        String status = ResponseInfomation.OPERATION_SUC;
        if (teamTeacher != null) {
            Integer type = teamTeacher.getType();
            if (type.intValue() == 1) {
                status = teamTeacherService.removeMasterFromTeam(teamTeacher, 1, group.getId(), schoolId);
            } else if (type.intValue() == 2) {
                status = teamTeacherService.removeClassTeacherFromTeam(teamTeacher, 1, group.getId(), schoolId);
            }
        }
        if (id != null) {
            schoolInitService.deleteSubjectTeacherTmpById(id);
        } else {
            if (teamTeacher != null) {
                SubjectTeacherTmpCondition condition = new SubjectTeacherTmpCondition();
                condition.setGradeId(teamTeacher.getGradeId());
                condition.setTeamId(teamTeacher.getTeamId());
                condition.setSubjectTeacherId(teamTeacherId);
                List<SubjectTeacherTmp> tmpList = schoolInitService.findSubjectTeacherTmpByCondition(condition, null, null);
                for (SubjectTeacherTmp tmp : tmpList) {
                    schoolInitService.deleteSubjectTeacherTmpById(tmp.getId());
                }
            }
        }

        return status;
    }

    //添加教师课程
    @RequestMapping("/inputview")
    public ModelAndView modifyTeacher(
            @RequestParam(value = "id") Integer id,
            @CurrentUser UserInfo user, Model model) {
        model.addAttribute("id",id);
        return new ModelAndView("/teach/teacher/input",model.asMap());
    }

/****************************************************新增修改任课教师开始************************************************/
    /**
     * 添加任课教师
     * @param teamTeacher
     * @return
     */
    @RequestMapping("/addOrModifyClassRoomTeacher")
    @ResponseBody
    public String addOrModifyClassRoomTeacher(TeamTeacher teamTeacher,
                                              @CurrentUser UserInfo user){
        //添加任课教师 具备四个条件   必须有年级  必须有班级  必须有科目  必须有教师
        boolean isNotNUllOfGrade = teamTeacher.getGradeId() !=null && !"".equals(teamTeacher.getGradeId());
        boolean isNotNUllOfTeam = teamTeacher.getTeamId() != null && !"".equals(teamTeacher.getTeamId());
        boolean isNotNUllOfSubject = teamTeacher.getSubjectCode() != null && !"".equals(teamTeacher.getSubjectCode());
        boolean isNotNUllOfTeacher = teamTeacher.getTeacherId() != null && !"".equals(teamTeacher.getTeacherId());

        try{
            if(isNotNUllOfGrade && isNotNUllOfTeam && isNotNUllOfSubject && isNotNUllOfTeacher){
                String mess = this.teamTeacherService.addOrModifyClassRoomTeacherOfTeam(SysContants.SYSTEM_APP_ID,user.getGroupId(),teamTeacher);
                if(mess.equals(ResponseInfomation.OPERATION_ERROR)){
                    return ResponseInfomation.OPERATION_FAIL;
                }
            }else{
                log.info("添加任课教师参数不全...");
                return ResponseInfomation.OPERATION_FAIL;
            }
        }catch(Exception e){
            log.info("添加任课教师异常...");
            e.printStackTrace();
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    /****************************************************新增修改任课教师结束************************************************/

    /**
     * 获取修改教师头像页面信息
     *
     * @param id
     * @param user
     * @return
     */
    @RequestMapping("/modifyTeacherPhoto")
    public ModelAndView modifyTeacherPhoto(
            @RequestParam(value = "id", required = true) String id,
            @CurrentUser UserInfo user
    ) {
        ModelAndView mav = new ModelAndView();
        TeacherDetailInfoVo teacherDetailInfo = teacherService.findTeacherDetailInfoById(Integer.parseInt(id));
        mav.addObject("teacherDetailInfo", teacherDetailInfo);
        mav.setViewName("/teach/teacher/modifyTeacherPhoto");
        return mav;
    }

    /**
     * 更新教师头像
     *
     * @param teacherDetailInfo
     * @return
     */
    @RequestMapping("/updateTeacherPhoto")
    @ResponseBody
    public ResponseInfomation updateTeacherPhoto(@ModelAttribute("teacherData") TeacherDetailInfo teacherDetailInfo) {
        try {
            Teacher teacher = teacherService.findTeacherById(teacherDetailInfo.getTeacherId());
            if (teacher == null) {
                return new ResponseInfomation("teacher:" + teacherDetailInfo.getTeacherId() + "不存在");
            }
            Person person = personService.findPersonById(teacher.getPersionId());
            if (person == null) {
//				return new ResponseInfomation("personId:"+teacher.getPersionId()+"不存在");
                person = new Person();
                person.setPhotoUuid(teacherDetailInfo.getPhotoUuid());
                person.setName(teacher.getName());
                Date date = new Date();
                person.setCreateDate(date);
                person.setModifyDate(date);
                person.setIsDelete(false);
                person = this.personService.add(person);
                if (person != null) {
                    teacher.setPersionId(person.getId());
                    this.teacherService.modify(teacher);
                    User user = this.userService.findUserById(teacher.getUserId());
                    user.setPersonId(person.getId());
                    this.userService.modify(user);
                }
            } else {
                person.setPhotoUuid(teacherDetailInfo.getPhotoUuid());
                personService.modify(person);
            }
            //
            Profile profile = profileService.findByUserId(teacher.getUserId());
            if (profile != null) {
                profile.setModifyDate(new Date());
                profile.setIcon(person.getPhotoUuid());
                profileService.modify(profile);
            } else {
                createProfileByTeacher(teacher, person);
            }
        } catch (Exception e) {
            log.info("修改教师头像信息异常");

            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }
        return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
    }

    //验证姓名
    @RequestMapping(value = "/checker", method = RequestMethod.POST)
    @ResponseBody
    public boolean checker(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dxlx", required = false) String dxlx,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "id") Integer id) {
        boolean isExist = false;
        TeacherCondition condition = new TeacherCondition();
        condition.setSchoolId(user.getSchoolId());
        condition.setName(name);
        condition.setIsDelete(false);
        if ("name".equals(dxlx)) {
            List<Teacher> list = teacherService.findTeacherByCondition(condition, null, null);
            if (list != null && list.size() > 0) {
                Integer currentId;
                for (Teacher temp : list) {
                    currentId = temp.getId();
                    if (currentId != null && currentId.equals(id)) {
                        isExist = true;
                    } else {
                        isExist = false;
                    }
                }
            } else {
                isExist = true;
            }
        }
        return isExist;
    }


    @RequestMapping(value = "/mobileChecker", method = RequestMethod.GET)
    @ResponseBody
    public boolean mobileChecker(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dxlx", required = false) String dxlx,
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "id") Integer id) {
        boolean isExist = false;
        TeacherCondition condition = new TeacherCondition();
        condition.setSchoolId(user.getSchoolId());
        condition.setMobile(mobile);
        condition.setIsDelete(false);
        if ("mobile".equals(dxlx)) {
            List<Teacher> list = teacherService.findTeacherByCondition(condition, null, null);
            if (list != null && list.size() > 0) {
                Integer currentId;
                for (Teacher temp : list) {
                    currentId = temp.getId();
                    if (currentId != null && currentId.equals(id)) {
                        isExist = true;
                    } else {
                        isExist = false;
                    }
                }
            } else {
                isExist = true;
            }
        }
        return isExist;
    }


    private void createProfileByTeacher(Teacher teacher, Person person) {
        Profile profile;
        profile = new Profile();
        if (person.getAddress() != null) {
            profile.setAddress(person.getAddress());
        }
        if (person.getEmail() != null) {
            profile.setEmail(person.getEmail());
        }
        if (teacher.getMobile() != null) {
            profile.setMobile(teacher.getMobile());
        }
        if (person.getBirthday() != null) {
            profile.setBirthday(person.getBirthday());
        }
        if (person.getPhotoUuid() != null) {
            profile.setIcon(person.getPhotoUuid());
        }
        profile.setCreateDate(new Date());
        profile.setIsDeleted(false);
        profile.setModifyDate(new Date());
        if (teacher.getName() != null) {
            profile.setName(teacher.getName());
        }
        if (teacher.getSex() != null) {
            profile.setSex(teacher.getSex());
        }
        if (teacher.getUserName() != null) {
            profile.setUserName(teacher.getUserName());
        }
        profile.setUserId(teacher.getUserId());
        profileService.add(profile);
    }

    /**
     * 设置薪资信息
     *
     * @param teacherDetailInfo
     * @author Ken
     * @date 2017年1月22日 上午9:49:56
     */
    private void setSalaryDetail(TeacherDetailInfoVo teacherDetailInfo) {
        // 薪资信息
        List<SalaryFieldnameValue> salaryDetail = new ArrayList<SalaryFieldnameValue>();
        PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition = new PjTeacherSalaryFieldCondition();
        pjTeacherSalaryFieldCondition.setSchoolId(teacherDetailInfo.getSchoolId());
        // 薪资表头信息
        List<PjTeacherSalaryField> fieldList = salaryFieldService.findPjTeacherSalaryFieldByCondition(pjTeacherSalaryFieldCondition);
        if (fieldList != null && fieldList.size() > 0) {
            PjTeacherSalaryCondition pjTeacherSalaryCondition = new PjTeacherSalaryCondition();
            pjTeacherSalaryCondition.setUserId(teacherDetailInfo.getUserId());
            framework.generic.dao.Page page = new framework.generic.dao.Page();
            page.setCurrentPage(0);
            page.setPageSize(1);
            List<PjTeacherSalary> salaryValueList = salaryService.findPjTeacherSalaryByCondition(pjTeacherSalaryCondition, page);
            if (salaryValueList != null && salaryValueList.size() > 0) {
                PjTeacherSalary salaryValue = salaryValueList.get(0);
                for (int i = 0; i < fieldList.size(); i = i + 2) {
                    SalaryFieldnameValue value = new SalaryFieldnameValue();
                    salaryDetail.add(value);
                    // 为attrName1和value1赋值
                    PjTeacherSalaryField pjTeacherSalaryField = fieldList.get(i);
                    value.setAttrName1(pjTeacherSalaryField.getAttrName());
                    try {
                        Field declaredField = salaryValue.getClass().getDeclaredField(pjTeacherSalaryField.getFieldName());
                        declaredField.setAccessible(true);
                        Float float1 = (Float) declaredField.get(salaryValue);
                        if (float1 != null) {
                            value.setValue1(float1);
                        }
                    } catch (Exception e) {
                    }
                    if (i < (fieldList.size() - 1)) {
                        // 为attrName2和value2赋值
                        pjTeacherSalaryField = fieldList.get((i + 1));
                        value.setAttrName2(pjTeacherSalaryField.getAttrName());
                        try {
                            Field declaredField2 = salaryValue.getClass().getDeclaredField(pjTeacherSalaryField.getFieldName());
                            declaredField2.setAccessible(true);
                            Float float1 = (Float) declaredField2.get(salaryValue);
                            if (float1 != null) {
                                value.setValue2(float1);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                // 设置总工资项
//				SalaryFieldnameValue salaryFieldnameValue = salaryDetail.get((salaryDetail.size()-1));
//				if ( salaryFieldnameValue.getAttrName2() == null ) {
//					salaryFieldnameValue.setAttrName2("实发工资总额");
//					salaryFieldnameValue.setValue2(salaryValue.getSalaryTotal());
//				} else {
//					SalaryFieldnameValue salaryFieldnameValue2 = new SalaryFieldnameValue();
//					salaryFieldnameValue2.setAttrName1("实发工资总额");
//					salaryFieldnameValue2.setValue1(salaryValue.getSalaryTotal());
//					salaryDetail.add(salaryFieldnameValue2);
//				}
                String salaryDate = salaryValue.getPayYear() + "年" + salaryValue.getPayMonth() + "月";
                teacherDetailInfo.setSalaryDate(salaryDate);
            }
        }
        teacherDetailInfo.setSalaryDetail(salaryDetail);
    }

    /**
     * 删除教师
     */
    @RequestMapping("/deleteTeacher")
    @ResponseBody
    public String deleteTeacher(
            @RequestParam(value = "id", required = true) String id) {
        try {
            teacherService.updateTeacherDetailInforById(Integer.parseInt(id));
        } catch (Exception e) {
            log.info("删除教师异常");
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    /**
     * 功能描述：修改教师信息
     * 2015-11-20
     *
     * @param teacherDetailInfo
     * @return
     */
    @RequestMapping("/updateTeacher")
    @ResponseBody
    public ResponseInfomation updateTeacher(
            TeacherDetailInfo teacherDetailInfo,
            TeacherAssist teacherAssist
    ) {

        if(StringUtils.isEmpty(teacherDetailInfo.getJobNumber())){
            return new ResponseInfomation( "工号不可为空");
        }

        if(basicSQLService.findUniqueLong("select exists(select 1 from pj_teacher where is_delete=0 and emp_code='"+teacherDetailInfo.getJobNumber()+"' and id!="+teacherDetailInfo.getTeacherId()+" ) e")>0){
             return new ResponseInfomation( "工号重复");
        }

        TeacherDetailInfo tdi = null;
        String message = ResponseInfomation.OPERATION_SUC;
//		String errorMessage = "";
        try {
            String userType = SysContants.USER_TYPE_TEACHER;
            teacherDetailInfo.setUserType(userType);
            tdi = teacherService.modifyInfoForTeacher(teacherDetailInfo);

            //2016-8-18 判断教师头像是否存在，不存在的将档案头像作为用户头像
            changeTeacherIcon(tdi.getTeacherId(), tdi.getPhotoUuid());

            if (tdi != null) {
                if (tdi.getMessage() != null && !"".equals(tdi.getMessage().trim())) {
                    message = tdi.getMessage();
                } else {
//					2016-12-8添加教师辅助信息的修改
                    if (teacherAssist != null && teacherDetailInfo.getUserId() != null && teacherDetailInfo.getSchoolId() != null) {
                        teacherAssistService.updateTeacherAssist(teacherDetailInfo.getSchoolId(),
                                teacherDetailInfo.getUserId(), teacherAssist);
                    }
                }
            }

        } catch (Exception e) {
//			errorMessage = "修改教师信息错误：" + this.errorMessage(e.getMessage());
//			log.error(errorMessage);
            log.info("修改教师信息异常");

            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }
//		ResponseInfomation responseInfomation = teacher != null ? new ResponseInfomation(teacher.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation(errorMessage);
//		return responseInfomation;
        return new ResponseInfomation(tdi, message);
    }
   //前端未启用
   /* *//**
     * 新增教师页面
     *
     * @return
     *//*
    @RequestMapping("/addTeacherPage")
    public ModelAndView addTeacherPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/teach/teacher/addTeacherPage");
        return mav;
    }*/

    /**
     * 功能描述：修改教师
     * 2015-11-20
     *
     * @param teacherDetailInfo
     * @param departmentIds
     * @param user
     * @return
     * 2021 11，16 前端未启用，如需使用注意第三方食堂推送是否能用
     */
   /* @RequestMapping("/addTeacher")
    @ResponseBody
    public ResponseInfomation addTeacher(
            TeacherDetailInfo teacherDetailInfo,
            TeacherAssist teacherAssist,
            @RequestParam(value = "departmentIds", required = false) String departmentIds,
            @CurrentUser UserInfo user) {
        TeacherDetailInfo tdi = null;
        String message = ResponseInfomation.OPERATION_SUC;
        try {
            teacherDetailInfo.setSchoolId(user.getSchoolId());
            teacherDetailInfo.setDepartmentIds(departmentIds);
            ExtImportTeacherVo extImTeaVo = new ExtImportTeacherVo();
            ExtImportTeacherVo.Assist assist = extImTeaVo.new Assist();
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BeanUtils.copyProperties(assist, teacherAssist);
            tdi = this.teacherService.addInfoForTeacher(teacherDetailInfo, assist, SysContants.DEFAULT_PASSWORD, SysContants.SYSTEM_APP_ID, SysContants.USER_TYPE_TEACHER);
            //2016-8-18 判断教师头像是否存在，不存在的将档案头像作为用户头像
            changeTeacherIcon(tdi.getTeacherId(), tdi.getPhotoUuid());
            //删除教职工默认角色
            List<UserRole> userRoleList = userRoleService.findByUserId(tdi.getUserId());
            for (UserRole u : userRoleList) {
                userRoleService.remove(u);
            }
            if (tdi != null) {
                if (tdi.getMessage() != null && !"".equals(tdi.getMessage().trim())) {
                    message = tdi.getMessage();
                } else {
                    // 食堂数据接口
                    if (address != null && !address.equals("") && canteen != null && !canteen.equals("")) {
                        // 判断教师信息是否添加成功 调用远程接口发送数据
                        List<EmployeeList> list=new ArrayList<>();
                        Teacher teacher = teacherService.findTeacherById(tdi.getTeacherId());
                        if(teacher==null){
                            log.info("保存教师信息异常");
                            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
                        }else {
                            EmployeeList employeeList = new EmployeeList();
                            employeeList.setEmp_name(tdi.getName());
                            //list.add(new DetailVo("emp_name", name, "String"));
                            // 入职日期
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            employeeList.setEmp_workdate(simpleDateFormat.format(new Date()));
                            //list.add(new DetailVo("emp_workdate", simpleDateFormat.format(new Date()), "String"));
//		// 工号
                            employeeList.setEmp_code(teacher.getEmpCode());
                            //list.add(new DetailVo("emp_code", gh, "String"));
                            // 食堂卡号
                            employeeList.setEmp_card(teacher.getEmpCard());
                            // list.add(new DetailVo("emp_card", kh, "String"));
                            //食堂唯一标识 userId
                            employeeList.setEmp_pycode(String.valueOf(teacher.getUserId()));
                            //生份证号码 userName
                            employeeList.setEmp_idcard(teacher.getUserName());
                            //部门名称
                            Department department=departmentDao.findDepartmentByteacherId(teacher.getSchoolId(),teacher.getId());
                            if(department==null){
                                employeeList.setDept_code("000001");
                                employeeList.setDept_name("香市一小");
                            }else{
                                employeeList.setDept_code("000001");
                                employeeList.setDept_name(department.getName());
                            }
                            list.add(employeeList);

                            Object object = JSONObject.toJSON(list);
                            JSONObject param = new JSONObject();
                            param.put("sign_name", "kksss");
                            //param.put("tran_code","emp_add");
                            param.put("tran_code", "emp_add");
                            param.put("employeeList", object);
                            //canteenThreadPoolExecutor.submit
                            HttpRequestResult httpRequestResult = null;
                            HttpRequestConfig config = HttpRequestConfig.create().url(address + canteen)
                                    .addHeader("content-type", "application/json")
                                    .httpEntityType(HttpEntityType.ENTITY_STRING);
                            //config.json(shiTangDate.toString());
                            config.json(param.toString());
                            try {
                                httpRequestResult = HttpClientUtils.post(config);
                                if (httpRequestResult == null) {
                                    return null;
                                }
                                if (200 == httpRequestResult.getCode()) {
                                    String responseText = httpRequestResult.getResponseText();
                                    if (("").equals(responseText) || responseText != null) {
                                        return null;
                                    }
                                    JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                                    String statusCode = jsonObject2.getString("statusCode");
                                    String result2 = jsonObject2.getString("result");
                                    if (("").equals(statusCode) || statusCode == null && ("").equals(result2) || result2 == null) {
                                        return null;
                                    }
                                    if ("200".equals(statusCode) && "true".equals(result2)) {
                                        List<Map<String, Object>> list2 = (List<Map<String, Object>>) jsonObject2.get("data");
                                        if (list.size() < 0) {
                                            return null;
                                        }
                                        *//*食堂返回信息
                                         * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"测试11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
                                         *//*
                                        for (Map<String, Object> aa : list2) {
                                            if (aa.get("deal_msg") == "success") {
                                                log.info("食堂接口--添加用户信息到远程接口--食堂接口成功");
                                                Teacher teacher2 = new Teacher();
                                                teacher2.setEmpCode((String) aa.get("emp_code"));
                                                teacher2.setIsSendCanteen(1);
                                                teacherService.updateTeacherSendCanteen(teacher2);
                                            } else {
                                                log.info("添加失败" + aa.get("deal_msg"));
                                            }
                                        }
                                    } else {
                                        log.info("食堂接口--添加用户信息到远程接口失败--食堂接口成功 错误信息 {}", httpRequestResult);
                                    }
                                } else {
                                    log.error("食堂接口--添加用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
                                }
                                log.info("食堂接口--食堂添加接口添加状态{}", " 返回信息: {}----" + httpRequestResult);
                            } catch (IOException e) {
                                log.info("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}" + e);
                            }
                        }
                    } else {
                        log.error("调用远程接口失败，请检查配置接口信息是否正确！");
                    }
                        //httpService.addEmploye(tdi, address + canteen, user.getSchoolId(), 0, null, 0, null);
                    // 调用闸机接口
//					if (HikvisionAddPersonUrl2 != null && !HikvisionAddPersonUrl2.equals("") ){
//						ArtemisConfig artemisConfig=new ArtemisConfig();
//						artemisConfig.setHost(HikvisionHost);
//						artemisConfig.setAppKey(HikvisionAppKey);
//						artemisConfig.setAppSecret(HikvisionAppSecret);
//						HikvisionPerson hik=new HikvisionPerson();
//						if (tdi.getName()!=null && tdi.getName().equals("") && tdi.getAlias()!=null && tdi.getAlias().equals("")){
//							hik.setPersonName(tdi.getName()+tdi.getAlias());
//						}
//						else {
//							hik.setPersonName(tdi.getName());
//						}
//						hik.setOrgIndexCode(tdi.getSchoolId()+","+tdi.getDepartmentName());
//						hik.setJobNo(tdi.getJobNumber());
//						hik.setGenderStr(tdi.getSex());
//						hik.setPhoneNo(tdi.getMobile());
//						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//						hik.setBirthday(format.format(tdi.getBirthDate()));
//						BASE64Encoder base64Encoder = new BASE64Encoder();
//						hik.setFaceData(base64Encoder.encode(hik.getPersonName().getBytes()));
//						// 判断教师信息是否添加成功 调用远程接口发送数据
//						httpService.addHikvisionPerson(artemisConfig,HikvisionAddPersonUrl2,2,hik,null);
//					} else {
//						log.error("调用闸机接口失败，请检查配置接口信息是否正确！");
//					}
//					// 调用图书馆接口
//					if (libraryACnteen != null && !("").equals(libraryACnteen) && libraryLogin != null && !("").equals(libraryLogin) && libraryCreate != null && !("").equals(libraryCreate)){
//							// status: 0: 单条数据 1：批量 2：单条（已获取数据）
//						httpService.addLibraryData(null, libraryACnteen, libraryLogin, libraryCreate, 0, tdi, null , 0);
//					} else {
//						log.error("调用图书馆远程接口失败，请检查配置接口信息是否正确！");
//					}
                }
            }
        } catch (Exception e) {
            log.info("保存教师信息异常");
            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }
        return new ResponseInfomation(tdi, message);
    }*/

    /**
     * 检查教师名字是否重复
     */
    @RequestMapping(value = "checkerName", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkName(
            @RequestParam(value = "dxlx", required = false) String dxlx,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "id", required = false) Integer id) {
        boolean isExist = false;
        if ("name".equals(dxlx)) {
            Teacher teacher = this.teacherService.findTeacherByName(name);
            if (teacher != null) {
                Integer currentId = teacher.getId();
                if (currentId != null && currentId == id) {
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


    /**
     * 导入教师信息页面
     */
    @RequestMapping("/upLoadTeacherInfoPage")
    public ModelAndView upLoadTeacherInfoPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/teach/teacher/upLoadTeacherInfoPage");
        return mav;
    }

    /**
     * 检验要改的EXCEL是否合法
     *
     * @param
     * @param userInfo
     * @return
     */
    @SuppressWarnings("unused")
    @RequestMapping("/checkFileUpload")
    @ResponseBody
    public String checkFileUpload(
            @RequestParam("checkFileUpload") MultipartFile checkFileUpload,
            @CurrentUser UserInfo userInfo, HttpServletResponse response) throws IOException {
        String isSuccess = "";
        InputStream is = checkFileUpload.getInputStream();
        POIFSFileSystem fs = new POIFSFileSystem(is);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheet(wb.getSheetName(0));
        List<TeacherDetailInfoVo> successMeaageVoList = new ArrayList<TeacherDetailInfoVo>();
        try {
            TeacherCondition teacherCondition = new TeacherCondition();
            TeacherDetailInfoVo teacherDetailInfoVo = null;
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
                HSSFRow row = sheet.getRow(i);
                HSSFCell cell3 = row.getCell(3);//手机号
                HSSFCell cell1 = row.getCell(0);//姓名
                String name = row.getCell(0) == null ? "" : row.getCell(0).toString();
                String sexTemp = row.getCell(1) == null ? "" : row.getCell(1).toString();
                String certificateNum = row.getCell(2) == null ? "" : row.getCell(2).toString();
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                String mobile = row.getCell(3) == null ? "" : row.getCell(3).toString();

                if (cell1 != null) {
                    Integer schoolId = userInfo.getSchoolId();
                    List<Teacher> teacherList = this.teacherService.findTeacherByNameAndSchool(name, schoolId);
                    if (teacherList.isEmpty()) {
                        isSuccess = "合格";
                    } else {
                        isSuccess = "姓名已存在";
                    }
                } else {
                    isSuccess = "姓名不能为空";
                }

                if (cell3 != null) {
                    teacherCondition.setSchoolId(userInfo.getSchoolId());
                    teacherCondition.setMobile(mobile);
                    List<Teacher> teacherList0 = this.teacherService.findTeacherByCondition(teacherCondition, null, null);
                    if (teacherList0.isEmpty()) {
                        isSuccess = "合格";
                    } else {
                        isSuccess = "手机号已存在";
                    }
                } else {
                    isSuccess = "手机号不能为空";
                }

                teacherDetailInfoVo = new TeacherDetailInfoVo();
                teacherDetailInfoVo.setSchoolId(userInfo.getSchoolId());
                teacherDetailInfoVo.setName(name);
                teacherDetailInfoVo.setSex(sexTemp);
                teacherDetailInfoVo.setMobile(mobile);//手机号
                teacherDetailInfoVo.setCertificateNum(certificateNum);//身份证号
                teacherDetailInfoVo.setMessage(isSuccess);
                successMeaageVoList.add(teacherDetailInfoVo);
            }
            writeMessageHSSFSheetResult(successMeaageVoList, response);
        } catch (Exception e) {
            //
            log.info("checkFileUpload 检验要改的EXCEL是否合法");
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    @SuppressWarnings("deprecation")
    public void writeMessageHSSFSheetResult(List<TeacherDetailInfoVo> successMeaageVoList, HttpServletResponse response) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet_ = wb.createSheet("错误信息表");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet_.createRow((int) 0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("姓名");
            cell.setCellStyle(style);
            cell = row.createCell((short) 1);
            cell.setCellValue("性别");
            cell.setCellStyle(style);
            cell = row.createCell((short) 2);
            cell.setCellValue("身份证号");
            cell.setCellStyle(style);
            cell = row.createCell((short) 3);
            cell.setCellValue("手机号");
            cell.setCellStyle(style);

            cell = row.createCell((short) 4);
            cell.setCellValue("错误信息");
            cell.setCellStyle(style);

            for (int i = 0; i < successMeaageVoList.size(); i++) {
                TeacherDetailInfoVo emv = successMeaageVoList.get(i);
                row = sheet_.createRow((int) i + 1);
                // 第四步，创建单元格，并设置值
                row.createCell((short) 0).setCellValue(emv.getName());
                row.createCell((short) 1).setCellValue(emv.getSex());
                row.createCell((short) 2).setCellValue(emv.getCertificateNum());
                row.createCell((short) 3).setCellValue(emv.getMobile());
                cell = row.createCell((short) 4);
                cell.setCellValue(emv.getMessage());
            }
            FileOutputStream fout = new FileOutputStream("E:/teacher_error.xls");
            wb.write(fout);
            fout.close();

            String path = "E:/teacher_error.xls";
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {

        }

    }

    @RequestMapping("/upLoadTeacherInfo")
    @ResponseBody
    public String upLoadTeacherInfo(
            @RequestParam("fileUpload") MultipartFile fileUpload,
            @RequestParam(value = "role", required = true) String role,
            @CurrentUser UserInfo userInfo,
            HttpServletResponse response,
            HttpServletRequest request,
            @ModelAttribute("upLoadInformationCondition") UpLoadInformationCondition upLoadInformationCondition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) throws IOException {
        InputStream is = null;
        try {
            is = fileUpload.getInputStream();
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheet(wb.getSheetName(0));
            /***
             * 先清空临时表中的数据
             */
            upLoadInformationCondition.setSchoolId(userInfo.getSchoolId());
            upLoadInformationCondition.setCreater(userInfo.getId().toString());
            upLoadInformationCondition.setUserType("2");//1:学生 2：教师
            List<UpLoadInformation> upLoadInformationList = this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
            if (!upLoadInformationList.isEmpty()) {
                for (UpLoadInformation u : upLoadInformationList) {
                    //System.out.println(u.getId());;
                    upLoadInformationService.remove(u);
                }
            }
            UpLoadInformation upLoadInformation = null;
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
                HSSFRow row = sheet.getRow(i);
                String name = row.getCell(0) == null ? "" : row.getCell(0).toString();//姓名
                String sexTemp = row.getCell(1) == null ? "" : row.getCell(1).toString();//性别
                String certificateNum = row.getCell(2) == null ? "" : row.getCell(2).toString();//身份证号
                String deptName = row.getCell(4) == null ? "" : row.getCell(4).toString();//部门名称

                HSSFCell cell3 = row.getCell(3);//手机号
                String mobile = "";
                if (row.getCell(3) != null) {
                    cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mobile = row.getCell(3).toString();
                }
                String isSuccess = "合格";
                String isState = "1";

                if (name == "" || "".equals(name)) {
                    isSuccess = "姓名不能为空";
                    isState = "2";
                }
                if (certificateNum == "" || "".equals(certificateNum)) {
                    isSuccess = "证件号不能为空";
                    isState = "2";
                }
                if (mobile == "" || "".equals(mobile)) {
                    isSuccess = "手机号码不能为空";
                    isState = "2";
                }
                if ((name != "" || !"".equals(name)) && (certificateNum != "" || !"".equals(certificateNum)) && (mobile != "" || !"".equals(mobile))) {
                    upLoadInformation = new UpLoadInformation();
                    upLoadInformation.setCreateDate(new Date());
                    upLoadInformation.setCreater(userInfo.getId().toString());
                    upLoadInformation.setName(name);
                    upLoadInformation.setSex(sexTemp);
                    upLoadInformation.setSchoolId(userInfo.getSchoolId());
                    upLoadInformation.setIdCardNumber(certificateNum);
                    upLoadInformation.setTelephone(mobile);
                    upLoadInformation.setUserType("2");
                    upLoadInformation.setRole(role);
                    upLoadInformation.setDeptName(deptName);
                    upLoadInformation.setState(isState);
                    upLoadInformation.setMessage(isSuccess);
                    this.upLoadInformationService.add(upLoadInformation);
                }

            }
        } catch (Exception e) {
            //
            return ResponseInfomation.OPERATION_FAIL;
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return ResponseInfomation.OPERATION_SUC;
    }


    /***
     * 异步加载导入教师信息
     * @param userInfo
     * @param response
     * @param upLoadInformationCondition
     * @param page
     * @param order
     * @return
     */
    @RequestMapping(value = "/getUploadInformation", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List<UpLoadInformation>> getUploadInformation(
            @CurrentUser UserInfo userInfo,
            HttpServletResponse response,
            @ModelAttribute("upLoadInformationCondition") UpLoadInformationCondition upLoadInformationCondition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        Map<String, List<UpLoadInformation>> map = new HashMap<String, List<UpLoadInformation>>();
        try {
            upLoadInformationCondition.setSchoolId(userInfo.getSchoolId());
            upLoadInformationCondition.setCreater(userInfo.getId().toString());
            upLoadInformationCondition.setUserType("2");//1:学生 2：教师
            upLoadInformationCondition.setState("1");//1:成功
            List<UpLoadInformation> upLoadInformationListSuccess = this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);

            upLoadInformationCondition.setState("2");//2:失败
            List<UpLoadInformation> upLoadInformationListFail = this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
            //System.out.println("upLoadInformationListSuccess:"+upLoadInformationListSuccess.size()+"=upLoadInformationListFail="+upLoadInformationListFail.size());
            map.put("upLoadInformationListSuccess", upLoadInformationListSuccess);
            map.put("upLoadInformationListFail", upLoadInformationListFail);
        } catch (Exception e) {
            //
            log.info("异步加载导入教师异常...");
            return null;
        }
        return map;
    }

    @RequestMapping(value = "/saveUploadTeacherInformation", method = RequestMethod.POST)
    @ResponseBody
    public String saveUploadTeacherInformation(
            @CurrentUser UserInfo userInfo,
            HttpServletResponse response,
            @ModelAttribute("upLoadInformationCondition") UpLoadInformationCondition upLoadInformationCondition) {
        String message = ResponseInfomation.OPERATION_SUC;
        try {
            upLoadInformationCondition.setSchoolId(userInfo.getSchoolId());
            upLoadInformationCondition.setCreater(userInfo.getId().toString());
            upLoadInformationCondition.setUserType("2");//1:学生 2：教师
            upLoadInformationCondition.setState("1");//1:成功
            upLoadInformationCondition.setCreater(userInfo.getId().toString());
            List<UpLoadInformation> upLoadInformationListSuccess = this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
            TeacherDetailInfo teacherDetailInfo = null;
            for (UpLoadInformation upLoadInformation : upLoadInformationListSuccess) {
                teacherDetailInfo = new TeacherDetailInfo();
                teacherDetailInfo.setSchoolId(userInfo.getSchoolId());
                teacherDetailInfo.setName(upLoadInformation.getName());
                teacherDetailInfo.setCertificateType("1");//证件类型
                teacherDetailInfo.setCertificateNum(upLoadInformation.getIdCardNumber());
                teacherDetailInfo.setSex(getSexValue(upLoadInformation.getSex()));
                teacherDetailInfo.setMobile(upLoadInformation.getTelephone());
                teacherDetailInfo.setRole(upLoadInformation.getRole());
                teacherDetailInfo.setDepartmentName(upLoadInformation.getDeptName());
                TeacherDetailInfo teacherDetail = this.teacherService.addTeacherInfo(teacherDetailInfo, SysContants.SYSTEM_APP_ID, SysContants.DEFAULT_PASSWORD, SysContants.USER_TYPE_TEACHER);
                if (teacherDetail != null && teacherDetail.getMessage() != null && !"".equals(teacherDetail.getMessage())) {
                    message = teacherDetail.getMessage();
                }

                //将已同步到学生表中的数据后，将state状态设为3
                upLoadInformation.setState("3");
                this.upLoadInformationService.modify(upLoadInformation);
            }
        } catch (Exception e) {
            //
            log.info("saveUploadTeacherInformation 保存导入学生异常");
            return ResponseInfomation.OPERATION_FAIL;
        }
        return message;
    }

    @SuppressWarnings("deprecation")
    public void writeErrorTeacherMessageHSSFSheetResult(List<ErroMessageVo> errorMeaageVoList, HttpServletResponse response) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet_ = wb.createSheet("错误信息表");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet_.createRow((int) 0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("姓名");
            cell.setCellStyle(style);
            cell = row.createCell((short) 1);
            cell.setCellValue("英文名");
            cell.setCellStyle(style);
            cell = row.createCell((short) 2);
            cell.setCellValue("用户名");
            cell.setCellStyle(style);
            cell = row.createCell((short) 3);
            cell.setCellValue("错误信息");
            cell.setCellStyle(style);

            for (int i = 0; i < errorMeaageVoList.size(); i++) {
                ErroMessageVo emv = errorMeaageVoList.get(i);
                row = sheet_.createRow((int) i + 1);
                // 第四步，创建单元格，并设置值
                row.createCell((short) 0).setCellValue(emv.getName());
                row.createCell((short) 1).setCellValue(emv.getEnglistName());
                row.createCell((short) 2).setCellValue(emv.getUsername());
                cell = row.createCell((short) 3);
                cell.setCellValue(emv.getErrorMessage());
            }

            FileOutputStream fout = new FileOutputStream("E:/teacher_error.xls");
            wb.write(fout);
            fout.close();

            String path = "E:/teacher_error.xls";
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {

        }
    }

    public Map<String, Object> getTeacherHSSFSheetResult(HSSFSheet sheet, UserInfo userInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ErroMessageVo> errorMeaageVoList = new ArrayList<ErroMessageVo>();
        List<TeacherDetailInfoVo> successMeaageVoList = new ArrayList<TeacherDetailInfoVo>();

        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            HSSFRow row = sheet.getRow(i);

            TeacherDetailInfoVo teacherDetailInfoVo = null;
            ErroMessageVo erroMessageVo = null;
            boolean flag = false;

            HSSFCell cell2 = row.getCell(2);//用户名
            String name = row.getCell(0) == null ? "" : row.getCell(0).toString();
            String englistName = row.getCell(1) == null ? "" : row.getCell(1).toString();
            String username = row.getCell(2) == null ? "" : row.getCell(2).toString();
            if (cell2 != null) {
                User user = this.userService.findUserByUsername(username);
                if (user == null) {
                    flag = true;
                } else {
                    flag = false;
                    erroMessageVo = getErrorTeacherMessage(i, name, englistName, username, "用户名不能重复");
                }
            } else {
                flag = false;
                erroMessageVo = getErrorTeacherMessage(i, name, englistName, username, "用户名不能为空");
            }

            //符合条件
            if (flag) {
                teacherDetailInfoVo = getTeacherHssfCell(row);
                teacherDetailInfoVo.setSchoolId(userInfo.getSchoolId());
                successMeaageVoList.add(teacherDetailInfoVo);
            } else {
                errorMeaageVoList.add(erroMessageVo);
            }

        }
        map.put("ERROR", errorMeaageVoList);
        map.put("SUCCESS", successMeaageVoList);

        return map;
    }

    public ErroMessageVo getErrorTeacherMessage(Integer rowId, String name, String englistName, String username, String errorMessage) {
        ErroMessageVo erroMessageVo = new ErroMessageVo();
        erroMessageVo.setRowId(rowId);
        erroMessageVo.setErrorMessage(errorMessage);
        erroMessageVo.setName(name);
        erroMessageVo.setEnglistName(englistName);
        erroMessageVo.setUsername(username);
        return erroMessageVo;
    }

    public TeacherDetailInfoVo getTeacherHssfCell(HSSFRow row) {
        TeacherDetailInfoVo tdv = new TeacherDetailInfoVo();
        try {

            HSSFCell cell0 = row.getCell(0);//姓名
            System.out.println("姓名:" + cell0.toString() + "====");
            tdv.setName(cell0 == null ? "" : cell0.toString());
            HSSFCell cell1 = row.getCell(1);//英文名
            tdv.setEnglishName(cell1 == null ? "" : cell1.toString());
            HSSFCell cell2 = row.getCell(2);//用户名
            tdv.setUserName(cell2 == null ? "" : cell2.toString());
            HSSFCell cell3 = row.getCell(3);//性别
            tdv.setSex(cell3 == null ? "" : getSexValue(cell3.toString().trim()));
            HSSFCell cell4 = row.getCell(4);//民族
            tdv.setNation(cell3 == null ? "" : getNationValue(cell4.toString().trim()));
            HSSFCell cell5 = row.getCell(5);//出生日期
            tdv.setBirthDate(cell5 == null ? new Date() : getDate(cell5.toString().trim()));
            HSSFCell cell6 = row.getCell(6);//工号
            tdv.setJobNumber(cell6 == null ? "" : cell6.toString());
            HSSFCell cell7 = row.getCell(7);//证件类型
            tdv.setCertificateType(cell7 == null ? "1" : getCertificateTypeValue(cell7.toString().trim()));
            HSSFCell cell8 = row.getCell(8);//证件号码
            tdv.setCertificateNum(cell8 == null ? "" : cell8.toString());
            HSSFCell cell9 = row.getCell(9);//手机号码
            tdv.setMobile(cell9 == null ? "" : cell9.toString());
            HSSFCell cell10 = row.getCell(10);//参加工作时间
            tdv.setWorkBeginDate(cell10 == null ? new Date() : getDate(cell10.toString().trim()));

            HSSFCell cell11 = row.getCell(11);//国籍
            tdv.setNationality(cell11 == null ? "" : getNationlityTypeValue(cell11.toString().trim()));
            HSSFCell cell12 = row.getCell(12);//入职时间
            tdv.setEnrollDate(cell12 == null ? new Date() : getDate(cell12.toString().trim()));
            HSSFCell cell13 = row.getCell(13);//离职时间
            tdv.setLeaveDate(cell13 == null ? new Date() : getDate(cell13.toString().trim()));
            HSSFCell cell14 = row.getCell(14);//籍贯
            tdv.setNativePlace(cell14 == null ? "" : cell14.toString());
            HSSFCell cell15 = row.getCell(15);//职务
            tdv.setPosition(cell15 == null ? "" : cell15.toString());
            HSSFCell cell16 = row.getCell(16);//出生地
            tdv.setBirthPlace(cell16 == null ? "" : cell16.toString());
            HSSFCell cell17 = row.getCell(17);//岗位职业
            tdv.setOccupationCode(cell17 == null ? "" : getOccupationCodeValue(cell17.toString().trim()));
            HSSFCell cell18 = row.getCell(18);//婚姻状况
            tdv.setMarriage(cell18 == null ? "" : getMarrage(cell18.toString()));
            HSSFCell cell19 = row.getCell(19);//学历
            tdv.setQualification(cell19 == null ? "" : getQualification(cell19.toString()));
            HSSFCell cell20 = row.getCell(20);//政治面貌
            tdv.setPolitical(cell20 == null ? "" : getPoliticalValue(cell20.toString().trim()));


            HSSFCell cell21 = row.getCell(21);//特长
            tdv.setSpecialty(cell21 == null ? "" : cell21.toString());
            HSSFCell cell22 = row.getCell(22);//宗教信仰
            tdv.setReligiousBelief(cell22 == null ? "" : getReligiousBelief(cell22.toString().trim()));
            HSSFCell cell23 = row.getCell(23);//岗位编制
            tdv.setPostStaffing(cell23 == null ? "" : getPostStaffing(cell23.toString().trim()));
            HSSFCell cell24 = row.getCell(24);//户口性质
            tdv.setRegister(cell24 == null ? "" : getRegist(cell24.toString()));
            HSSFCell cell25 = row.getCell(25);//在职状态
            tdv.setJobState(cell25 == null ? "" : getJobState(cell25.toString().trim()));
            HSSFCell cell26 = row.getCell(26);//户口所在地
            tdv.setRegisterPlace(cell26 == null ? "" : cell26.toString());
            HSSFCell cell27 = row.getCell(27);//办公室电话
            tdv.setTelephone(cell27 == null ? "" : cell27.toString());
            HSSFCell cell28 = row.getCell(28);//现地址
            tdv.setNowAddress(cell28 == null ? "" : cell28.toString());
            HSSFCell cell29 = row.getCell(29);//邮件
            tdv.setEmail(cell29 == null ? "" : cell29.toString());
            HSSFCell cell30 = row.getCell(30);//居住地址
            tdv.setLiveAddress(cell30 == null ? "" : cell30.toString());

            HSSFCell cell31 = row.getCell(31);//备注
            tdv.setRemark(cell31 == null ? "" : cell31.toString());

        } catch (ParseException e) {
            // TODO Auto-generated catch block

        }
        return tdv;
    }

    public Date getDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(date);
        return d;
    }

    /***
     * 导出教师信息
     */
    @RequestMapping("/exportTeacherInfoPage")
    public ModelAndView exportTeacherInfoPage(
            @RequestParam(value = "name", required = false) String name
    ) {
        ModelAndView mav = new ModelAndView();
		/* try {
			System.out.println("before name:"+name);
			if(name!=null&&!"".equals(name) ){
			    name=new String((name).getBytes("iso-8859-1"),"utf-8");
			 }
			System.out.println("end name:"+name);
		} catch (UnsupportedEncodingException e) {
			
		}*/
        mav.addObject("name", name);
        mav.setViewName("/teach/teacher/exportTeacherInfoPage");
        return mav;
    }


    /***
     * 导出教师信息
     */
    @RequestMapping("/exportTeacherInfo")
    public void exportTeacherInfo(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @ModelAttribute("teacherCondition") TeacherCondition condition,
                                  //@RequestParam(value="name",required=false) String name,
                                  @CurrentUser UserInfo user) {
        try {


            String name = condition.getName();
            System.out.println("exportTeacherInfo name:" + name);
            String ids = condition.getIds();
            //List<TeacherDetailInfo> tdiList = this.teachService.findAllTeacherDetailInfo(user.getSchoolId());
            TeacherCondition teacherCondition = new TeacherCondition();
            teacherCondition.setSchoolId(user.getSchoolId());
            teacherCondition.setName(name);
            List<TeacherDetailInfo> tdiList = this.teacherService.findTeacherDetailInfoByCondition(teacherCondition);
            List<String> title_list = new ArrayList<String>();
            List<String> colname_list = new ArrayList<String>();
            //前台传过来的选择项的参数
            //String str_options=request.getParameter("ids");
            String str_options = ids;
            String[] szoptions = str_options.split(",");
            if (szoptions != null && szoptions.length > 0) {
                for (int i = 0; i < szoptions.length; i++) {
                    String option = szoptions[i];
                    colname_list.add(option.split(":")[0]);
                    title_list.add(option.split(":")[1]);
                }
            }
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", "name");
            map.put("sex", "sex");
            map.put("englishName", "englishName");
            map.put("userName", "userName");
            map.put("birthDate", "birthDate");
            map.put("jobNumber", "jobNumber");
            map.put("certificateType", "certificateType");
            map.put("mobile", "mobile");
            map.put("certificateNum", "certificateNum");
            map.put("workBeginDate", "workBeginDate");
            map.put("nationality", "nationality");
            map.put("enrollDate", "enrollDate");
            map.put("nation", "nation");
            map.put("leaveDate", "leaveDate");
            map.put("nativePlace", "nativePlace");
            map.put("position", "position");
            map.put("birthPlace", "birthPlace");
            map.put("occupationCode", "occupationCode");
            map.put("marriage", "marriage");
            map.put("qualification", "qualification");
            map.put("political", "political");
            map.put("specialty", "specialty");
            map.put("religiousBelief", "religiousBelief");
            map.put("postStaffing", "postStaffing");
            map.put("register", "register");
            map.put("jobState", "jobState");
            map.put("registerPlace", "registerPlace");
            map.put("telephone", "telephone");
            map.put("nowAddress", "nowAddress");
            map.put("email", "email");
            map.put("liveAddress", "liveAddress");
            map.put("remark", "remark");

            String[] title = (String[]) title_list.toArray(new String[title_list.size()]);
            String[] colname = (String[]) colname_list.toArray(new String[colname_list.size()]);

            //清空response
            response.reset();
            //设置response的Header
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            response.setHeader("Content-Disposition", "attachment;filename=\"teacherInfo.xls" + "\"");
            HSSFWorkbook hssfWorkbook = buildExcel("教师基本信息", title, colname, tdiList, map);
            OutputStream os = response.getOutputStream();
            hssfWorkbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            log.info("教师导出数据异常");
        }

    }

    @SuppressWarnings("deprecation")
    public HSSFWorkbook buildExcel(String sheetname, String[] title, String[] colname, List<TeacherDetailInfo> tdiList, HashMap<String, String> mapVal) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetname);
        HSSFRow row = null;
        try {
            //HSSFCell cell = null;
            // 写表头
            row = sheet.createRow(0);
            //产生标题列
            for (int i = 0; i < title.length; i++) {
                row.createCell((short) i).setCellValue(title[i]);
            }

            //填充数据
            if (tdiList != null && tdiList.size() > 0) {
                TeacherDetailInfo tdi = null;
                //Iterator lit = null;
                for (int i = 0; i < tdiList.size(); i++) {
                    tdi = tdiList.get(i);
                    row = sheet.createRow(i + 1);
                    for (int j = 0; j < colname.length; j++) {
                        if (mapVal != null && mapVal.get(colname[j]) != null) {
                            if ("name".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getName());
                            } else if ("sex".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("GB-XB", tdi.getSex()));
                            } else if ("englishName".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getEnglishName());
                            } else if ("userName".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getUserName());
                            } else if ("birthDate".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getBirthDate() == null ? "" : UtilDate.getDateFormatter(tdi.getBirthDate(), "yyyy-MM-dd"));
                            } else if ("jobNumber".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getJobNumber());
                            } else if ("certificateType".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("JY-SFZJLX", tdi.getCertificateType()));
                            } else if ("mobile".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getMobile());
                            } else if ("certificateNum".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getCertificateNum());
                            } else if ("workBeginDate".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getWorkBeginDate() == null ? "" : UtilDate.getDateFormatter(tdi.getWorkBeginDate(), "yyyy-MM-dd"));
                            } else if ("nationality".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("GB-GJ", tdi.getNationality()));
                            } else if ("enrollDate".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getEnrollDate() == null ? "" : UtilDate.getDateFormatter(tdi.getEnrollDate(), "yyyy-MM-dd"));
                            } else if ("nation".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("GB-MZ", tdi.getNation()));
                            } else if ("leaveDate".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getLeaveDate() == null ? "" : UtilDate.getDateFormatter(tdi.getLeaveDate(), "yyyy-MM-dd"));
                            } else if ("nativePlace".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getNativePlace());
                            } else if ("position".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getPosition());
                            } else if ("birthPlace".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getBirthPlace());
                            } else if ("occupationCode".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("JY-GWZY", tdi.getOccupationCode()));
                            } else if ("political".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("GB-ZZMM", tdi.getPolitical()));
                            } else if ("specialty".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getSpecialty());
                            } else if ("religiousBelief".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("GB-ZJXY", tdi.getReligiousBelief()));
                            } else if ("postStaffing".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getPostStaffing());
                            } else if ("register".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("GB-HKLB", tdi.getRegister()));
                            } else if ("jobState".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("JY-JZGDQZT", tdi.getJobState()));
                            } else if ("registerPlace".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getRegisterPlace());
                            } else if ("telephone".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getTelephone());
                            } else if ("nowAddress".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getNowAddress());
                            } else if ("email".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getEmail());
                            } else if ("liveAddress".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getLiveAddress());
                            } else if ("religiousBelief".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("GB-ZJXY", tdi.getReligiousBelief()));
                            } else if ("remark".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(tdi.getRemark());
                            } else if ("marriage".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("GB-HYZK", tdi.getMarriage()));
                            } else if ("qualification".equals(colname[j])) {
                                row.createCell((short) j).setCellValue(getDictNameByValue("JY-XL", tdi.getQualification()));
                            }

                        }

                    }
                }
            }
        } catch (Exception e) {
            log.info("导出学生创建表格异常");
        }
        return wb;
    }

    /**
     * 报错提示问题
     * 2015-11-20
     *
     * @param message
     * @return
     */
    public String errorMessage(String message) {
        if (message == null) {
            message = ResponseInfomation.OPERATION_ERROR;
        }
        String errorMessage = message.length() > 100 ? message.substring(0, 100) : message;
        return errorMessage;
    }

    public String getDictNameByValue(String tableCode, String val) {
        if (val != "" || !"".equals(val)) {
            Object obj = this.jcGcCacheService.getNameByValue(tableCode, val);
            return obj == null ? "" : obj.toString();
        }
        return "";
    }

    //获取民族值
    public String getNationValue(String nation) {
        switch (nation) {
            case "汉族":
                return "01";
            case "蒙古族":
                return "02";
            case "回族":
                return "03";
            case "藏族":
                return "04";
            case "维吾尔族":
                return "05";
            case "苗族":
                return "06";
            case "彝族":
                return "07";
            case "壮族":
                return "08";
            case "布依族":
                return "09";
            case "朝鲜族":
                return "10";
            case "满族":
                return "11";
            case "侗族":
                return "12";
            case "瑶族":
                return "13";
            case "白族":
                return "14";
            case "土家族":
                return "15";
            case "哈尼族":
                return "16";
            case "哈萨克族":
                return "17";
            case "傣族":
                return "18";
            case "黎族":
                return "19";
            case "傈僳族":
                return "20";
            case "佤族":
                return "21";
            case "畲族":
                return "22";
            case "高山族":
                return "23";
            case "拉祜族":
                return "24";
            case "水族":
                return "25";
            case "东乡族":
                return "26";
            case "纳西族":
                return "27";
            case "景颇族":
                return "28";
            case "柯尔克孜族":
                return "29";
            case "土族":
                return "30";
            case "达斡尔族":
                return "31";
            case "仫佬族":
                return "32";
            case "羌族":
                return "33";
            case "布朗族":
                return "34";
            case "撒拉族":
                return "35";
            case "毛难族":
                return "36";
            case "仡佬族":
                return "37";
            case "锡伯族":
                return "38";
            case "阿昌族":
                return "39";
            case "普米族":
                return "40";
            case "塔吉克族":
                return "41";
            case "怒族":
                return "42";
            case "乌孜别克族":
                return "43";
            case "俄罗斯族":
                return "44";
            case "鄂温克族":
                return "45";
            case "德昂族":
                return "46";
            case "保安族":
                return "47";
            case "裕固族":
                return "48";
            case "京族":
                return "49";
            case "塔塔尔族":
                return "50";
            case "独龙族":
                return "51";
            case "赫哲族":
                return "52";
            case "门巴族":
                return "53";
            case "珞巴族":
                return "54";
            case "基诺族":
                return "55";
            case "穿青人族":
                return "56";
            case "其他":
                return "57";
            case "外国血统中国籍人士":
                return "58";
        }
        return "01";
    }

    //婚姻状况
    public String getMarrage(String marrage) {
        switch (marrage) {
            case "未婚":
                return "10";
            case "已婚":
                return "20";
            case "初婚":
                return "21";
            case "再婚":
                return "22";
            case "复婚":
                return "23";
            case "丧偶":
                return "24";
            case "离婚":
                return "40";
            case "未说明的婚姻状况":
                return "90";
        }
        return "90";
    }

    //学历
    public String getQualification(String qualification) {
        switch (qualification) {
            case "博士研究生毕业":
                return "11";
            case "博士研究生结业":
                return "12";
            case "博士研究生肄业":
                return "13";
            case "硕士研究生毕业":
                return "14";
            case "硕士研究生结业":
                return "15";
            case "硕士研究生肄业":
                return "16";
            case "研究生班毕业":
                return "17";
            case "研究生班结业":
                return "18";
            case "研究生班肄业":
                return "19";
            case "大学本科毕业":
                return "21";
            case "大学本科结业":
                return "22";
            case "大学本科肄业":
                return "23";
            case "大学普通班毕业":
                return "24";
            case "大学专科毕业":
                return "31";
            case "大学专科结业":
                return "32";
            case "大学专科肄业":
                return "33";
            case "中等专科毕业":
                return "41";
            case "中等专科结业":
                return "42";
            case "中等专科肄业":
                return "43";
            case "职业高中毕业":
                return "44";
            case "职业高中结业":
                return "45";
            case "职业高中肄业":
                return "46";
            case "技工学校毕业":
                return "47";
            case "技工学校结业":
                return "48";
            case "技工学校肄业":
                return "49";
            case "普通高中毕业":
                return "61";
            case "普通高中结业":
                return "62";
            case "普通高中肄业":
                return "63";
            case "初中毕业":
                return "71";
            case "初中肄业":
                return "72";
            case "小学毕业":
                return "81";
            case "小学肄业":
                return "83";
            case "其他":
                return "90";
        }
        return "90";
    }

    //岗位编制
    public String getPostStaffing(String postStaffing) {
        switch (postStaffing) {
            case "无":
                return "0";
            case "有":
                return "1";
        }
        return "1";
    }

    //性别
    public String getSexValue(String sex) {
        switch (sex) {
            case "未知":
                return "0";
            case "男":
                return "1";
            case "女":
                return "2";
            case "未说明":
                return "9";
        }
        return "9";
    }

    //岗位职业
    public String getOccupationCodeValue(String occupationCode) {
        switch (occupationCode) {
            case "国家公务员":
                return "11";
            case "专业技术人员":
                return "13";
            case "职员":
                return "17";
            case "企业管理人员":
                return "21";
            case "工人":
                return "24";
            case "农民":
                return "27";
            case "学生":
                return "31";
            case "现役军人":
                return "37";
            case "自由职业者":
                return "51";
            case "个体经营者":
                return "54";
            case "无业人员":
                return "70";
            case "退（离）休人员":
                return "80";
            case "其他":
                return "90";
        }
        return "90";
    }

    //证书类型
    public String getCertificateTypeValue(String certificateType) {
        switch (certificateType) {
            case "身份证":
                return "0";
            case "一卡通":
                return "1";
        }
        return "9";
    }

    //国籍
    public String getNationlityTypeValue(String nationlity) {
        switch (nationlity) {
            case "中国":
                return "156";
            case "其它":
                return "0";
        }
        return "156";
    }

    //户口性质
    public String getRegist(String regist) {
        switch (regist) {
            case "未落常住户口":
                return "0";
            case "非农业家庭户口":
                return "1";
            case "农业家庭户口":
                return "2";
            case "非农业集体户口":
                return "3";
            case "农业集体户口":
                return "4";
            case "自理口粮户口":
                return "5";
            case "寄住人口":
                return "6";
            case "暂住人口":
                return "7";
            case "其他户口":
                return "8";
        }
        return "8";
    }

    //是否是流动人口
    public String getFolatPeople(String floatPeople) {
        switch (floatPeople) {
            case "是":
                return "1";
            case "否":
                return "0";
        }
        return "1";
    }

    //政治面貌
    public String getPoliticalValue(String political) {
        switch (political) {
            case "中国共产党党员":
                return "01";
            case "中国共产党预备党员":
                return "02";
            case "中国共产主义青年团团员":
                return "03";
            case "中国国民党革命委员会会员":
                return "04";
            case "中国民主同盟盟员":
                return "05";
            case "中国民主建国会会员":
                return "06";
            case "中国民主促进会会员":
                return "07";
            case "中国农工民主党党员":
                return "08";
            case "中国致公党党员":
                return "09";
            case "九三学社社员":
                return "10";
            case "台湾民主自治同盟盟员":
                return "11";
            case "无党派民主人士":
                return "12";
            case "群众":
                return "13";
        }
        return "13";
    }

    //宗教信仰
    public String getReligiousBelief(String religiousBelief) {
        switch (religiousBelief) {
            case "无宗教信仰":
                return "00";
            case "佛教":
                return "10";
            case "喇嘛教":
                return "20";
            case "道教":
                return "30";
            case "天主教":
                return "40";
            case "基督教":
                return "50";
            case "东正教":
                return "60";
            case "伊斯兰教":
                return "70";
            case "其他":
                return "99";
        }
        return "99";
    }

    //在职状诚
    public String getJobState(String jobState) {
        switch (jobState) {
            case "退休":
                return "01";
            case "离休":
                return "02";
            case "死亡":
                return "03";
            case "返聘":
                return "04";
            case "调出":
                return "05";
            case "辞职":
                return "06";
            case "离职":
                return "07";
            case "开除":
                return "08";
            case "下落不明":
                return "09";
            case "在职":
                return "11";
            case "延聘":
                return "12";
            case "待退休":
                return "13";
            case "长病假":
                return "14";
            case "因公出国":
                return "15";
            case "停薪留职":
                return "16";
            case "待岗":
                return "17";
            case "其他":
                return "99";
        }
        return "99";
    }

    public void changeTeacherIcon(Integer teacherId, String iconUuid) {
        Teacher teacher = teacherService.findTeacherById(teacherId);
        if (teacher != null) {
            Person person = personService.findPersonById(teacher.getPersionId());
            if (person != null) {
                Profile profile = profileService.findByUserId(teacher.getUserId());
                if (profile == null) {
                    createProfileByTeacher(teacher, person);
                } else {
                    if (profile.getIcon() == null || "".equals(profile.getIcon()) || !iconUuid.equals(profile.getIcon())) {
                        profile.setAddress(person.getAddress());
                        profile.setEmail(person.getEmail());
                        profile.setMobile(teacher.getMobile());
                        profile.setBirthday(person.getBirthday());
                        profile.setCreateDate(new Date());
                        profile.setIcon(person.getPhotoUuid());
                        profile.setIsDeleted(false);
                        profile.setModifyDate(new Date());
                        profile.setName(teacher.getName());
                        profile.setSex(teacher.getSex());
                        profile.setUserName(teacher.getUserName());
                        profile.setUserId(teacher.getUserId());
                        profileService.modify(profile);
                    }
                }
            }
        }
    }


    /**
     * 导入教师到希沃
     *
     * @return
     */
//	@RequestMapping(method = RequestMethod.GET, value = "/import/teacher")
//	public boolean importTeacher() {
//
//		MySeewoClient client =
//				MySeewoClient.request(TeacherApiSaveOrUpdateTeachersRequest.class);
//
//		TeacherApiSaveOrUpdateTeachersParam param = new TeacherApiSaveOrUpdateTeachersParam();
//		//请求体，MimeType为 application/json
//		TeacherApiSaveOrUpdateTeachersParam.JSONRequestBody requestBody = TeacherApiSaveOrUpdateTeachersParam.JSONRequestBody.builder()
//				.build();
//		param.setRequestBody(requestBody);
//
//		TeacherApiSaveOrUpdateTeachersParam.MisThirdTeacherQueryDto query = TeacherApiSaveOrUpdateTeachersParam.MisThirdTeacherQueryDto.builder()
//				.schoolUid(SCHOOL_UID)
//				.appId(APPID)
//				.build();
//		requestBody.setQuery(query);
//
//
//		query.setTeachers(teacherConversion(teacherService.findNotSendSeewo()));
//		param.setRequestBody(requestBody);
//
//		client.param(param);
//		TeacherApiSaveOrUpdateTeachersResult result = client.invoke();
//		if (result.getStatusCode() == 200) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	private List<TeacherApiSaveOrUpdateTeachersParam.MisThirdTeacherDto> teacherConversion(List<Teacher> teachers) {
//
//		List<TeacherApiSaveOrUpdateTeachersParam.MisThirdTeacherDto> misThirdTeacherDtos = new ArrayList<>(teachers.size());
//
//		for (Teacher teacher : teachers) {
//
//			TeacherApiSaveOrUpdateTeachersParam.MisThirdTeacherDto t
//					= TeacherApiSaveOrUpdateTeachersParam.MisThirdTeacherDto.builder()
//					.phone(teacher.getTelephone())
//					.name(teacher.getName())
//					.build();
//
//			//教师uid
//			TeacherApiSaveOrUpdateTeachersParam.Uid uid = TeacherApiSaveOrUpdateTeachersParam.Uid.builder()
//					.build();
//			t.setUid(uid);
//
//			misThirdTeacherDtos.add(t);
//
//		}
//		return misThirdTeacherDtos;
//
//	}
}
