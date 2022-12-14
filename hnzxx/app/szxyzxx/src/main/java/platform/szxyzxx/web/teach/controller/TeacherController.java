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
     * ????????????
     */
    private static String fileName;
    private static String address;
    private static String canteen;
    /**
     * ????????????
     */
    private static String HikvisionHost;
    private static String HikvisionAppKey;
    private static String HikvisionAppSecret;
    private static String HikvisionAddPersonUrl;
    /**
     * ???????????????
     */
    private static String libraryACnteen;
    private static String libraryLogin;
    private static String libraryCreate;

    static {
        fileName = "System.properties";
        // ??????
        address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");
        //??????
        HikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
        HikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
        HikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
        HikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");

        // ?????????
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
     * ????????????
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
                            log.error("??????????????????,url: {}","??????????????????");
                        }
                    }
                } catch (Exception e) {
                    log.error("????????????????????????,url: {}","????????????");
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
            log.info("????????????????????????...");
        }
        return mav;
    }

    /**
     * ????????????
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

            //2017-2-16??????????????????????????????????????????????????????????????????
            //??????????????????????????? ????????? ??????
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
            //??????????????????????????????????????????
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
    * ??????????????????
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

    //??????????????????
    @RequestMapping("/inputview")
    public ModelAndView modifyTeacher(
            @RequestParam(value = "id") Integer id,
            @CurrentUser UserInfo user, Model model) {
        model.addAttribute("id",id);
        return new ModelAndView("/teach/teacher/input",model.asMap());
    }

/****************************************************??????????????????????????????************************************************/
    /**
     * ??????????????????
     * @param teamTeacher
     * @return
     */
    @RequestMapping("/addOrModifyClassRoomTeacher")
    @ResponseBody
    public String addOrModifyClassRoomTeacher(TeamTeacher teamTeacher,
                                              @CurrentUser UserInfo user){
        //?????????????????? ??????????????????   ???????????????  ???????????????  ???????????????  ???????????????
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
                log.info("??????????????????????????????...");
                return ResponseInfomation.OPERATION_FAIL;
            }
        }catch(Exception e){
            log.info("????????????????????????...");
            e.printStackTrace();
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    /****************************************************??????????????????????????????************************************************/

    /**
     * ????????????????????????????????????
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
     * ??????????????????
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
                return new ResponseInfomation("teacher:" + teacherDetailInfo.getTeacherId() + "?????????");
            }
            Person person = personService.findPersonById(teacher.getPersionId());
            if (person == null) {
//				return new ResponseInfomation("personId:"+teacher.getPersionId()+"?????????");
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
            log.info("??????????????????????????????");

            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }
        return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
    }

    //????????????
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
     * ??????????????????
     *
     * @param teacherDetailInfo
     * @author Ken
     * @date 2017???1???22??? ??????9:49:56
     */
    private void setSalaryDetail(TeacherDetailInfoVo teacherDetailInfo) {
        // ????????????
        List<SalaryFieldnameValue> salaryDetail = new ArrayList<SalaryFieldnameValue>();
        PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition = new PjTeacherSalaryFieldCondition();
        pjTeacherSalaryFieldCondition.setSchoolId(teacherDetailInfo.getSchoolId());
        // ??????????????????
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
                    // ???attrName1???value1??????
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
                        // ???attrName2???value2??????
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
                // ??????????????????
//				SalaryFieldnameValue salaryFieldnameValue = salaryDetail.get((salaryDetail.size()-1));
//				if ( salaryFieldnameValue.getAttrName2() == null ) {
//					salaryFieldnameValue.setAttrName2("??????????????????");
//					salaryFieldnameValue.setValue2(salaryValue.getSalaryTotal());
//				} else {
//					SalaryFieldnameValue salaryFieldnameValue2 = new SalaryFieldnameValue();
//					salaryFieldnameValue2.setAttrName1("??????????????????");
//					salaryFieldnameValue2.setValue1(salaryValue.getSalaryTotal());
//					salaryDetail.add(salaryFieldnameValue2);
//				}
                String salaryDate = salaryValue.getPayYear() + "???" + salaryValue.getPayMonth() + "???";
                teacherDetailInfo.setSalaryDate(salaryDate);
            }
        }
        teacherDetailInfo.setSalaryDetail(salaryDetail);
    }

    /**
     * ????????????
     */
    @RequestMapping("/deleteTeacher")
    @ResponseBody
    public String deleteTeacher(
            @RequestParam(value = "id", required = true) String id) {
        try {
            teacherService.updateTeacherDetailInforById(Integer.parseInt(id));
        } catch (Exception e) {
            log.info("??????????????????");
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    /**
     * ?????????????????????????????????
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
            return new ResponseInfomation( "??????????????????");
        }

        if(basicSQLService.findUniqueLong("select exists(select 1 from pj_teacher where is_delete=0 and emp_code='"+teacherDetailInfo.getJobNumber()+"' and id!="+teacherDetailInfo.getTeacherId()+" ) e")>0){
             return new ResponseInfomation( "????????????");
        }

        TeacherDetailInfo tdi = null;
        String message = ResponseInfomation.OPERATION_SUC;
//		String errorMessage = "";
        try {
            String userType = SysContants.USER_TYPE_TEACHER;
            teacherDetailInfo.setUserType(userType);
            tdi = teacherService.modifyInfoForTeacher(teacherDetailInfo);

            //2016-8-18 ??????????????????????????????????????????????????????????????????????????????
            changeTeacherIcon(tdi.getTeacherId(), tdi.getPhotoUuid());

            if (tdi != null) {
                if (tdi.getMessage() != null && !"".equals(tdi.getMessage().trim())) {
                    message = tdi.getMessage();
                } else {
//					2016-12-8?????????????????????????????????
                    if (teacherAssist != null && teacherDetailInfo.getUserId() != null && teacherDetailInfo.getSchoolId() != null) {
                        teacherAssistService.updateTeacherAssist(teacherDetailInfo.getSchoolId(),
                                teacherDetailInfo.getUserId(), teacherAssist);
                    }
                }
            }

        } catch (Exception e) {
//			errorMessage = "???????????????????????????" + this.errorMessage(e.getMessage());
//			log.error(errorMessage);
            log.info("????????????????????????");

            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }
//		ResponseInfomation responseInfomation = teacher != null ? new ResponseInfomation(teacher.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation(errorMessage);
//		return responseInfomation;
        return new ResponseInfomation(tdi, message);
    }
   //???????????????
   /* *//**
     * ??????????????????
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
     * ???????????????????????????
     * 2015-11-20
     *
     * @param teacherDetailInfo
     * @param departmentIds
     * @param user
     * @return
     * 2021 11???16 ?????????????????????????????????????????????????????????????????????
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
            //2016-8-18 ??????????????????????????????????????????????????????????????????????????????
            changeTeacherIcon(tdi.getTeacherId(), tdi.getPhotoUuid());
            //???????????????????????????
            List<UserRole> userRoleList = userRoleService.findByUserId(tdi.getUserId());
            for (UserRole u : userRoleList) {
                userRoleService.remove(u);
            }
            if (tdi != null) {
                if (tdi.getMessage() != null && !"".equals(tdi.getMessage().trim())) {
                    message = tdi.getMessage();
                } else {
                    // ??????????????????
                    if (address != null && !address.equals("") && canteen != null && !canteen.equals("")) {
                        // ???????????????????????????????????? ??????????????????????????????
                        List<EmployeeList> list=new ArrayList<>();
                        Teacher teacher = teacherService.findTeacherById(tdi.getTeacherId());
                        if(teacher==null){
                            log.info("????????????????????????");
                            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
                        }else {
                            EmployeeList employeeList = new EmployeeList();
                            employeeList.setEmp_name(tdi.getName());
                            //list.add(new DetailVo("emp_name", name, "String"));
                            // ????????????
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            employeeList.setEmp_workdate(simpleDateFormat.format(new Date()));
                            //list.add(new DetailVo("emp_workdate", simpleDateFormat.format(new Date()), "String"));
//		// ??????
                            employeeList.setEmp_code(teacher.getEmpCode());
                            //list.add(new DetailVo("emp_code", gh, "String"));
                            // ????????????
                            employeeList.setEmp_card(teacher.getEmpCard());
                            // list.add(new DetailVo("emp_card", kh, "String"));
                            //?????????????????? userId
                            employeeList.setEmp_pycode(String.valueOf(teacher.getUserId()));
                            //??????????????? userName
                            employeeList.setEmp_idcard(teacher.getUserName());
                            //????????????
                            Department department=departmentDao.findDepartmentByteacherId(teacher.getSchoolId(),teacher.getId());
                            if(department==null){
                                employeeList.setDept_code("000001");
                                employeeList.setDept_name("????????????");
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
                                        *//*??????????????????
                                         * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"??????11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
                                         *//*
                                        for (Map<String, Object> aa : list2) {
                                            if (aa.get("deal_msg") == "success") {
                                                log.info("????????????--?????????????????????????????????--??????????????????");
                                                Teacher teacher2 = new Teacher();
                                                teacher2.setEmpCode((String) aa.get("emp_code"));
                                                teacher2.setIsSendCanteen(1);
                                                teacherService.updateTeacherSendCanteen(teacher2);
                                            } else {
                                                log.info("????????????" + aa.get("deal_msg"));
                                            }
                                        }
                                    } else {
                                        log.info("????????????--???????????????????????????????????????--?????????????????? ???????????? {}", httpRequestResult);
                                    }
                                } else {
                                    log.error("????????????--???????????????????????????????????????, ???????????? {}", httpRequestResult);
                                }
                                log.info("????????????--??????????????????????????????{}", " ????????????: {}----" + httpRequestResult);
                            } catch (IOException e) {
                                log.info("????????????--??????????????????????????????{}??? ???????????????????????????{}" + e);
                            }
                        }
                    } else {
                        log.error("?????????????????????????????????????????????????????????????????????");
                    }
                        //httpService.addEmploye(tdi, address + canteen, user.getSchoolId(), 0, null, 0, null);
                    // ??????????????????
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
//						// ???????????????????????????????????? ??????????????????????????????
//						httpService.addHikvisionPerson(artemisConfig,HikvisionAddPersonUrl2,2,hik,null);
//					} else {
//						log.error("?????????????????????????????????????????????????????????????????????");
//					}
//					// ?????????????????????
//					if (libraryACnteen != null && !("").equals(libraryACnteen) && libraryLogin != null && !("").equals(libraryLogin) && libraryCreate != null && !("").equals(libraryCreate)){
//							// status: 0: ???????????? 1????????? 2??????????????????????????????
//						httpService.addLibraryData(null, libraryACnteen, libraryLogin, libraryCreate, 0, tdi, null , 0);
//					} else {
//						log.error("??????????????????????????????????????????????????????????????????????????????");
//					}
                }
            }
        } catch (Exception e) {
            log.info("????????????????????????");
            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }
        return new ResponseInfomation(tdi, message);
    }*/

    /**
     * ??????????????????????????????
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
     * ????????????????????????
     */
    @RequestMapping("/upLoadTeacherInfoPage")
    public ModelAndView upLoadTeacherInfoPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/teach/teacher/upLoadTeacherInfoPage");
        return mav;
    }

    /**
     * ???????????????EXCEL????????????
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
                HSSFCell cell3 = row.getCell(3);//?????????
                HSSFCell cell1 = row.getCell(0);//??????
                String name = row.getCell(0) == null ? "" : row.getCell(0).toString();
                String sexTemp = row.getCell(1) == null ? "" : row.getCell(1).toString();
                String certificateNum = row.getCell(2) == null ? "" : row.getCell(2).toString();
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                String mobile = row.getCell(3) == null ? "" : row.getCell(3).toString();

                if (cell1 != null) {
                    Integer schoolId = userInfo.getSchoolId();
                    List<Teacher> teacherList = this.teacherService.findTeacherByNameAndSchool(name, schoolId);
                    if (teacherList.isEmpty()) {
                        isSuccess = "??????";
                    } else {
                        isSuccess = "???????????????";
                    }
                } else {
                    isSuccess = "??????????????????";
                }

                if (cell3 != null) {
                    teacherCondition.setSchoolId(userInfo.getSchoolId());
                    teacherCondition.setMobile(mobile);
                    List<Teacher> teacherList0 = this.teacherService.findTeacherByCondition(teacherCondition, null, null);
                    if (teacherList0.isEmpty()) {
                        isSuccess = "??????";
                    } else {
                        isSuccess = "??????????????????";
                    }
                } else {
                    isSuccess = "?????????????????????";
                }

                teacherDetailInfoVo = new TeacherDetailInfoVo();
                teacherDetailInfoVo.setSchoolId(userInfo.getSchoolId());
                teacherDetailInfoVo.setName(name);
                teacherDetailInfoVo.setSex(sexTemp);
                teacherDetailInfoVo.setMobile(mobile);//?????????
                teacherDetailInfoVo.setCertificateNum(certificateNum);//????????????
                teacherDetailInfoVo.setMessage(isSuccess);
                successMeaageVoList.add(teacherDetailInfoVo);
            }
            writeMessageHSSFSheetResult(successMeaageVoList, response);
        } catch (Exception e) {
            //
            log.info("checkFileUpload ???????????????EXCEL????????????");
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    @SuppressWarnings("deprecation")
    public void writeMessageHSSFSheetResult(List<TeacherDetailInfoVo> successMeaageVoList, HttpServletResponse response) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            // ???????????????webbook???????????????sheet,??????Excel????????????sheet
            HSSFSheet sheet_ = wb.createSheet("???????????????");
            // ???????????????sheet??????????????????0???,???????????????poi???Excel????????????????????????short
            HSSFRow row = sheet_.createRow((int) 0);
            // ???????????????????????????????????????????????? ??????????????????
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ????????????????????????

            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("??????");
            cell.setCellStyle(style);
            cell = row.createCell((short) 1);
            cell.setCellValue("??????");
            cell.setCellStyle(style);
            cell = row.createCell((short) 2);
            cell.setCellValue("????????????");
            cell.setCellStyle(style);
            cell = row.createCell((short) 3);
            cell.setCellValue("?????????");
            cell.setCellStyle(style);

            cell = row.createCell((short) 4);
            cell.setCellValue("????????????");
            cell.setCellStyle(style);

            for (int i = 0; i < successMeaageVoList.size(); i++) {
                TeacherDetailInfoVo emv = successMeaageVoList.get(i);
                row = sheet_.createRow((int) i + 1);
                // ??????????????????????????????????????????
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
            // path????????????????????????????????????
            File file = new File(path);
            // ??????????????????
            String filename = file.getName();
            // ??????????????????????????????
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header
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
             * ??????????????????????????????
             */
            upLoadInformationCondition.setSchoolId(userInfo.getSchoolId());
            upLoadInformationCondition.setCreater(userInfo.getId().toString());
            upLoadInformationCondition.setUserType("2");//1:?????? 2?????????
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
                String name = row.getCell(0) == null ? "" : row.getCell(0).toString();//??????
                String sexTemp = row.getCell(1) == null ? "" : row.getCell(1).toString();//??????
                String certificateNum = row.getCell(2) == null ? "" : row.getCell(2).toString();//????????????
                String deptName = row.getCell(4) == null ? "" : row.getCell(4).toString();//????????????

                HSSFCell cell3 = row.getCell(3);//?????????
                String mobile = "";
                if (row.getCell(3) != null) {
                    cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                    mobile = row.getCell(3).toString();
                }
                String isSuccess = "??????";
                String isState = "1";

                if (name == "" || "".equals(name)) {
                    isSuccess = "??????????????????";
                    isState = "2";
                }
                if (certificateNum == "" || "".equals(certificateNum)) {
                    isSuccess = "?????????????????????";
                    isState = "2";
                }
                if (mobile == "" || "".equals(mobile)) {
                    isSuccess = "????????????????????????";
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
     * ??????????????????????????????
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
            upLoadInformationCondition.setUserType("2");//1:?????? 2?????????
            upLoadInformationCondition.setState("1");//1:??????
            List<UpLoadInformation> upLoadInformationListSuccess = this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);

            upLoadInformationCondition.setState("2");//2:??????
            List<UpLoadInformation> upLoadInformationListFail = this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
            //System.out.println("upLoadInformationListSuccess:"+upLoadInformationListSuccess.size()+"=upLoadInformationListFail="+upLoadInformationListFail.size());
            map.put("upLoadInformationListSuccess", upLoadInformationListSuccess);
            map.put("upLoadInformationListFail", upLoadInformationListFail);
        } catch (Exception e) {
            //
            log.info("??????????????????????????????...");
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
            upLoadInformationCondition.setUserType("2");//1:?????? 2?????????
            upLoadInformationCondition.setState("1");//1:??????
            upLoadInformationCondition.setCreater(userInfo.getId().toString());
            List<UpLoadInformation> upLoadInformationListSuccess = this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
            TeacherDetailInfo teacherDetailInfo = null;
            for (UpLoadInformation upLoadInformation : upLoadInformationListSuccess) {
                teacherDetailInfo = new TeacherDetailInfo();
                teacherDetailInfo.setSchoolId(userInfo.getSchoolId());
                teacherDetailInfo.setName(upLoadInformation.getName());
                teacherDetailInfo.setCertificateType("1");//????????????
                teacherDetailInfo.setCertificateNum(upLoadInformation.getIdCardNumber());
                teacherDetailInfo.setSex(getSexValue(upLoadInformation.getSex()));
                teacherDetailInfo.setMobile(upLoadInformation.getTelephone());
                teacherDetailInfo.setRole(upLoadInformation.getRole());
                teacherDetailInfo.setDepartmentName(upLoadInformation.getDeptName());
                TeacherDetailInfo teacherDetail = this.teacherService.addTeacherInfo(teacherDetailInfo, SysContants.SYSTEM_APP_ID, SysContants.DEFAULT_PASSWORD, SysContants.USER_TYPE_TEACHER);
                if (teacherDetail != null && teacherDetail.getMessage() != null && !"".equals(teacherDetail.getMessage())) {
                    message = teacherDetail.getMessage();
                }

                //?????????????????????????????????????????????state????????????3
                upLoadInformation.setState("3");
                this.upLoadInformationService.modify(upLoadInformation);
            }
        } catch (Exception e) {
            //
            log.info("saveUploadTeacherInformation ????????????????????????");
            return ResponseInfomation.OPERATION_FAIL;
        }
        return message;
    }

    @SuppressWarnings("deprecation")
    public void writeErrorTeacherMessageHSSFSheetResult(List<ErroMessageVo> errorMeaageVoList, HttpServletResponse response) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            // ???????????????webbook???????????????sheet,??????Excel????????????sheet
            HSSFSheet sheet_ = wb.createSheet("???????????????");
            // ???????????????sheet??????????????????0???,???????????????poi???Excel????????????????????????short
            HSSFRow row = sheet_.createRow((int) 0);
            // ???????????????????????????????????????????????? ??????????????????
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ????????????????????????

            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("??????");
            cell.setCellStyle(style);
            cell = row.createCell((short) 1);
            cell.setCellValue("?????????");
            cell.setCellStyle(style);
            cell = row.createCell((short) 2);
            cell.setCellValue("?????????");
            cell.setCellStyle(style);
            cell = row.createCell((short) 3);
            cell.setCellValue("????????????");
            cell.setCellStyle(style);

            for (int i = 0; i < errorMeaageVoList.size(); i++) {
                ErroMessageVo emv = errorMeaageVoList.get(i);
                row = sheet_.createRow((int) i + 1);
                // ??????????????????????????????????????????
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
            // path????????????????????????????????????
            File file = new File(path);
            // ??????????????????
            String filename = file.getName();
            // ??????????????????????????????
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header
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

            HSSFCell cell2 = row.getCell(2);//?????????
            String name = row.getCell(0) == null ? "" : row.getCell(0).toString();
            String englistName = row.getCell(1) == null ? "" : row.getCell(1).toString();
            String username = row.getCell(2) == null ? "" : row.getCell(2).toString();
            if (cell2 != null) {
                User user = this.userService.findUserByUsername(username);
                if (user == null) {
                    flag = true;
                } else {
                    flag = false;
                    erroMessageVo = getErrorTeacherMessage(i, name, englistName, username, "?????????????????????");
                }
            } else {
                flag = false;
                erroMessageVo = getErrorTeacherMessage(i, name, englistName, username, "?????????????????????");
            }

            //????????????
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

            HSSFCell cell0 = row.getCell(0);//??????
            System.out.println("??????:" + cell0.toString() + "====");
            tdv.setName(cell0 == null ? "" : cell0.toString());
            HSSFCell cell1 = row.getCell(1);//?????????
            tdv.setEnglishName(cell1 == null ? "" : cell1.toString());
            HSSFCell cell2 = row.getCell(2);//?????????
            tdv.setUserName(cell2 == null ? "" : cell2.toString());
            HSSFCell cell3 = row.getCell(3);//??????
            tdv.setSex(cell3 == null ? "" : getSexValue(cell3.toString().trim()));
            HSSFCell cell4 = row.getCell(4);//??????
            tdv.setNation(cell3 == null ? "" : getNationValue(cell4.toString().trim()));
            HSSFCell cell5 = row.getCell(5);//????????????
            tdv.setBirthDate(cell5 == null ? new Date() : getDate(cell5.toString().trim()));
            HSSFCell cell6 = row.getCell(6);//??????
            tdv.setJobNumber(cell6 == null ? "" : cell6.toString());
            HSSFCell cell7 = row.getCell(7);//????????????
            tdv.setCertificateType(cell7 == null ? "1" : getCertificateTypeValue(cell7.toString().trim()));
            HSSFCell cell8 = row.getCell(8);//????????????
            tdv.setCertificateNum(cell8 == null ? "" : cell8.toString());
            HSSFCell cell9 = row.getCell(9);//????????????
            tdv.setMobile(cell9 == null ? "" : cell9.toString());
            HSSFCell cell10 = row.getCell(10);//??????????????????
            tdv.setWorkBeginDate(cell10 == null ? new Date() : getDate(cell10.toString().trim()));

            HSSFCell cell11 = row.getCell(11);//??????
            tdv.setNationality(cell11 == null ? "" : getNationlityTypeValue(cell11.toString().trim()));
            HSSFCell cell12 = row.getCell(12);//????????????
            tdv.setEnrollDate(cell12 == null ? new Date() : getDate(cell12.toString().trim()));
            HSSFCell cell13 = row.getCell(13);//????????????
            tdv.setLeaveDate(cell13 == null ? new Date() : getDate(cell13.toString().trim()));
            HSSFCell cell14 = row.getCell(14);//??????
            tdv.setNativePlace(cell14 == null ? "" : cell14.toString());
            HSSFCell cell15 = row.getCell(15);//??????
            tdv.setPosition(cell15 == null ? "" : cell15.toString());
            HSSFCell cell16 = row.getCell(16);//?????????
            tdv.setBirthPlace(cell16 == null ? "" : cell16.toString());
            HSSFCell cell17 = row.getCell(17);//????????????
            tdv.setOccupationCode(cell17 == null ? "" : getOccupationCodeValue(cell17.toString().trim()));
            HSSFCell cell18 = row.getCell(18);//????????????
            tdv.setMarriage(cell18 == null ? "" : getMarrage(cell18.toString()));
            HSSFCell cell19 = row.getCell(19);//??????
            tdv.setQualification(cell19 == null ? "" : getQualification(cell19.toString()));
            HSSFCell cell20 = row.getCell(20);//????????????
            tdv.setPolitical(cell20 == null ? "" : getPoliticalValue(cell20.toString().trim()));


            HSSFCell cell21 = row.getCell(21);//??????
            tdv.setSpecialty(cell21 == null ? "" : cell21.toString());
            HSSFCell cell22 = row.getCell(22);//????????????
            tdv.setReligiousBelief(cell22 == null ? "" : getReligiousBelief(cell22.toString().trim()));
            HSSFCell cell23 = row.getCell(23);//????????????
            tdv.setPostStaffing(cell23 == null ? "" : getPostStaffing(cell23.toString().trim()));
            HSSFCell cell24 = row.getCell(24);//????????????
            tdv.setRegister(cell24 == null ? "" : getRegist(cell24.toString()));
            HSSFCell cell25 = row.getCell(25);//????????????
            tdv.setJobState(cell25 == null ? "" : getJobState(cell25.toString().trim()));
            HSSFCell cell26 = row.getCell(26);//???????????????
            tdv.setRegisterPlace(cell26 == null ? "" : cell26.toString());
            HSSFCell cell27 = row.getCell(27);//???????????????
            tdv.setTelephone(cell27 == null ? "" : cell27.toString());
            HSSFCell cell28 = row.getCell(28);//?????????
            tdv.setNowAddress(cell28 == null ? "" : cell28.toString());
            HSSFCell cell29 = row.getCell(29);//??????
            tdv.setEmail(cell29 == null ? "" : cell29.toString());
            HSSFCell cell30 = row.getCell(30);//????????????
            tdv.setLiveAddress(cell30 == null ? "" : cell30.toString());

            HSSFCell cell31 = row.getCell(31);//??????
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
     * ??????????????????
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
     * ??????????????????
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
            //????????????????????????????????????
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

            //??????response
            response.reset();
            //??????response???Header
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            response.setHeader("Content-Disposition", "attachment;filename=\"teacherInfo.xls" + "\"");
            HSSFWorkbook hssfWorkbook = buildExcel("??????????????????", title, colname, tdiList, map);
            OutputStream os = response.getOutputStream();
            hssfWorkbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            log.info("????????????????????????");
        }

    }

    @SuppressWarnings("deprecation")
    public HSSFWorkbook buildExcel(String sheetname, String[] title, String[] colname, List<TeacherDetailInfo> tdiList, HashMap<String, String> mapVal) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetname);
        HSSFRow row = null;
        try {
            //HSSFCell cell = null;
            // ?????????
            row = sheet.createRow(0);
            //???????????????
            for (int i = 0; i < title.length; i++) {
                row.createCell((short) i).setCellValue(title[i]);
            }

            //????????????
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
            log.info("??????????????????????????????");
        }
        return wb;
    }

    /**
     * ??????????????????
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

    //???????????????
    public String getNationValue(String nation) {
        switch (nation) {
            case "??????":
                return "01";
            case "?????????":
                return "02";
            case "??????":
                return "03";
            case "??????":
                return "04";
            case "????????????":
                return "05";
            case "??????":
                return "06";
            case "??????":
                return "07";
            case "??????":
                return "08";
            case "?????????":
                return "09";
            case "?????????":
                return "10";
            case "??????":
                return "11";
            case "??????":
                return "12";
            case "??????":
                return "13";
            case "??????":
                return "14";
            case "?????????":
                return "15";
            case "?????????":
                return "16";
            case "????????????":
                return "17";
            case "??????":
                return "18";
            case "??????":
                return "19";
            case "?????????":
                return "20";
            case "??????":
                return "21";
            case "??????":
                return "22";
            case "?????????":
                return "23";
            case "?????????":
                return "24";
            case "??????":
                return "25";
            case "?????????":
                return "26";
            case "?????????":
                return "27";
            case "?????????":
                return "28";
            case "???????????????":
                return "29";
            case "??????":
                return "30";
            case "????????????":
                return "31";
            case "?????????":
                return "32";
            case "??????":
                return "33";
            case "?????????":
                return "34";
            case "?????????":
                return "35";
            case "?????????":
                return "36";
            case "?????????":
                return "37";
            case "?????????":
                return "38";
            case "?????????":
                return "39";
            case "?????????":
                return "40";
            case "????????????":
                return "41";
            case "??????":
                return "42";
            case "???????????????":
                return "43";
            case "????????????":
                return "44";
            case "????????????":
                return "45";
            case "?????????":
                return "46";
            case "?????????":
                return "47";
            case "?????????":
                return "48";
            case "??????":
                return "49";
            case "????????????":
                return "50";
            case "?????????":
                return "51";
            case "?????????":
                return "52";
            case "?????????":
                return "53";
            case "?????????":
                return "54";
            case "?????????":
                return "55";
            case "????????????":
                return "56";
            case "??????":
                return "57";
            case "???????????????????????????":
                return "58";
        }
        return "01";
    }

    //????????????
    public String getMarrage(String marrage) {
        switch (marrage) {
            case "??????":
                return "10";
            case "??????":
                return "20";
            case "??????":
                return "21";
            case "??????":
                return "22";
            case "??????":
                return "23";
            case "??????":
                return "24";
            case "??????":
                return "40";
            case "????????????????????????":
                return "90";
        }
        return "90";
    }

    //??????
    public String getQualification(String qualification) {
        switch (qualification) {
            case "?????????????????????":
                return "11";
            case "?????????????????????":
                return "12";
            case "?????????????????????":
                return "13";
            case "?????????????????????":
                return "14";
            case "?????????????????????":
                return "15";
            case "?????????????????????":
                return "16";
            case "??????????????????":
                return "17";
            case "??????????????????":
                return "18";
            case "??????????????????":
                return "19";
            case "??????????????????":
                return "21";
            case "??????????????????":
                return "22";
            case "??????????????????":
                return "23";
            case "?????????????????????":
                return "24";
            case "??????????????????":
                return "31";
            case "??????????????????":
                return "32";
            case "??????????????????":
                return "33";
            case "??????????????????":
                return "41";
            case "??????????????????":
                return "42";
            case "??????????????????":
                return "43";
            case "??????????????????":
                return "44";
            case "??????????????????":
                return "45";
            case "??????????????????":
                return "46";
            case "??????????????????":
                return "47";
            case "??????????????????":
                return "48";
            case "??????????????????":
                return "49";
            case "??????????????????":
                return "61";
            case "??????????????????":
                return "62";
            case "??????????????????":
                return "63";
            case "????????????":
                return "71";
            case "????????????":
                return "72";
            case "????????????":
                return "81";
            case "????????????":
                return "83";
            case "??????":
                return "90";
        }
        return "90";
    }

    //????????????
    public String getPostStaffing(String postStaffing) {
        switch (postStaffing) {
            case "???":
                return "0";
            case "???":
                return "1";
        }
        return "1";
    }

    //??????
    public String getSexValue(String sex) {
        switch (sex) {
            case "??????":
                return "0";
            case "???":
                return "1";
            case "???":
                return "2";
            case "?????????":
                return "9";
        }
        return "9";
    }

    //????????????
    public String getOccupationCodeValue(String occupationCode) {
        switch (occupationCode) {
            case "???????????????":
                return "11";
            case "??????????????????":
                return "13";
            case "??????":
                return "17";
            case "??????????????????":
                return "21";
            case "??????":
                return "24";
            case "??????":
                return "27";
            case "??????":
                return "31";
            case "????????????":
                return "37";
            case "???????????????":
                return "51";
            case "???????????????":
                return "54";
            case "????????????":
                return "70";
            case "?????????????????????":
                return "80";
            case "??????":
                return "90";
        }
        return "90";
    }

    //????????????
    public String getCertificateTypeValue(String certificateType) {
        switch (certificateType) {
            case "?????????":
                return "0";
            case "?????????":
                return "1";
        }
        return "9";
    }

    //??????
    public String getNationlityTypeValue(String nationlity) {
        switch (nationlity) {
            case "??????":
                return "156";
            case "??????":
                return "0";
        }
        return "156";
    }

    //????????????
    public String getRegist(String regist) {
        switch (regist) {
            case "??????????????????":
                return "0";
            case "?????????????????????":
                return "1";
            case "??????????????????":
                return "2";
            case "?????????????????????":
                return "3";
            case "??????????????????":
                return "4";
            case "??????????????????":
                return "5";
            case "????????????":
                return "6";
            case "????????????":
                return "7";
            case "????????????":
                return "8";
        }
        return "8";
    }

    //?????????????????????
    public String getFolatPeople(String floatPeople) {
        switch (floatPeople) {
            case "???":
                return "1";
            case "???":
                return "0";
        }
        return "1";
    }

    //????????????
    public String getPoliticalValue(String political) {
        switch (political) {
            case "?????????????????????":
                return "01";
            case "???????????????????????????":
                return "02";
            case "?????????????????????????????????":
                return "03";
            case "????????????????????????????????????":
                return "04";
            case "????????????????????????":
                return "05";
            case "???????????????????????????":
                return "06";
            case "???????????????????????????":
                return "07";
            case "???????????????????????????":
                return "08";
            case "?????????????????????":
                return "09";
            case "??????????????????":
                return "10";
            case "??????????????????????????????":
                return "11";
            case "?????????????????????":
                return "12";
            case "??????":
                return "13";
        }
        return "13";
    }

    //????????????
    public String getReligiousBelief(String religiousBelief) {
        switch (religiousBelief) {
            case "???????????????":
                return "00";
            case "??????":
                return "10";
            case "?????????":
                return "20";
            case "??????":
                return "30";
            case "?????????":
                return "40";
            case "?????????":
                return "50";
            case "?????????":
                return "60";
            case "????????????":
                return "70";
            case "??????":
                return "99";
        }
        return "99";
    }

    //????????????
    public String getJobState(String jobState) {
        switch (jobState) {
            case "??????":
                return "01";
            case "??????":
                return "02";
            case "??????":
                return "03";
            case "??????":
                return "04";
            case "??????":
                return "05";
            case "??????":
                return "06";
            case "??????":
                return "07";
            case "??????":
                return "08";
            case "????????????":
                return "09";
            case "??????":
                return "11";
            case "??????":
                return "12";
            case "?????????":
                return "13";
            case "?????????":
                return "14";
            case "????????????":
                return "15";
            case "????????????":
                return "16";
            case "??????":
                return "17";
            case "??????":
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
     * ?????????????????????
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
//		//????????????MimeType??? application/json
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
//			//??????uid
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
