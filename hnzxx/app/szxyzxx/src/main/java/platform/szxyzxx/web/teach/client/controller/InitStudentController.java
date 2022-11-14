package platform.szxyzxx.web.teach.client.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpEntityType;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.generalcode.model.Stage;
import platform.education.generalcode.service.StageService;
import platform.education.user.model.UserBinding;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserService;
import platform.education.generalTeachingAffair.vo.EmployeeList;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.job.HikvisonJob;
import platform.szxyzxx.web.common.job.UserCardJob;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtils;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/student/init")
public class InitStudentController {
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 食堂接口
     */
    private static String fileName;

    private static String address;
    //新增添加接口
    private static String canteen;
    //新增修改接口
    private  static String canupdate;
    /**
     * 图书馆接口
     */
    private static String libraryACnteen;
    private static String libraryLogin;
    private static String libraryCreate;


    /**
     * 海康接口
     */
    private static String hikvisionHost;
    private static String hikvisionAppKey;
    private static String hikvisionAppSecret;
    private static String hikvisionAddPersonUrl;
    private static String hikvisionBindCarUrl;
    private static String untieCarUrl;
    static {
        fileName = "System.properties";
        // 食堂
        address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");
        canupdate=PropertiesUtil.getProperty(fileName,"canteen.server.update.url");
        // 图书馆
        libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
        libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
        libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");

        //闸机
        hikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
        hikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
        hikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
        hikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");
        hikvisionBindCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.bindings");
        untieCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.deletion");
    }

    @Autowired
    @Qualifier("teamStudentService")
    private TeamStudentService teamStudentService;
    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;
    @Autowired
    @Qualifier("teamService")
    private TeamService teamService;
    @Autowired
    @Qualifier("parentStudentService")
    private ParentStudentService parentStudentService;
    @Autowired
    @Qualifier("parentService")
    private ParentService parentService;
    @Autowired
    @Qualifier("parentProxyService")
    private ParentProxyService parentProxyService;
    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;
    @Autowired
    @Qualifier("baseRedisCache")
    private BaseRedisCache<Object> baseRedisCache;
    @Autowired
    @Qualifier("userBindingService")
    private UserBindingService userBindingService;
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("schoolInitService")
    private SchoolInitService schoolInitService;
    @Autowired
    @Qualifier("schoolUserService")
    private SchoolUserService schoolUserService;

    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("libraryService")
    private LibraryService libraryService;

    //注入当个定时任务用于获取新增用户的食堂卡号和推送至海康门禁系统
    @Autowired
    private UserCardJob userCardJob;
    @Autowired
    private HikvisonJob hikvisonJob;


    private static final String DIR = "schoolInit/student";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Object indexPage() {
        ModelAndView model = new ModelAndView();

        model.setViewName(DIR + "/student_index");

        return model;
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public Object index(@CurrentUser UserInfo userInfo) {
        ModelAndView model = new ModelAndView();

        String key = "student_tmp_" + userInfo.getId();
        Object value = baseRedisCache.getCacheObject(key);

        model.setViewName(DIR + "/student_import");
        model.addObject("value", value);
        model.addObject("userid", userInfo.getId());
        model.addObject("schoolid", userInfo.getSchoolId());
        model.addObject("schoolYear", userInfo.getSchoolYear());

        return model;
    }

    /**
     * 获取学生列表
     *
     * @param user
     * @param condition
     * @param page
     * @param index
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public Object list(@CurrentUser UserInfo user, @ModelAttribute("condition") TeamStudentCondition condition,
                       @ModelAttribute("page") Page page, @RequestParam(value = "index", defaultValue = "index") String index)
            throws Exception {

        condition.setSchoolId(user.getSchoolId());
        condition.setSchoolYear(user.getSchoolYear());

        Order order = Order.desc("id");
        List<Object> result = this.findTeamStudentAndParentByCondition(condition, page, order);

        ModelAndView model = new ModelAndView();
        if ("list".equals(index)) {
            model.setViewName(DIR + "/student_manage_list");
        } else {
            model.setViewName(DIR + "/student_manage");
        }
        model.addObject("result", result);

        return model;
    }

    private List<Object> findTeamStudentAndParentByCondition(TeamStudentCondition condition, Page page, Order order) {
        Integer gradeId = -1;
        Integer teamId = -1;
        String gradeName = null;
        String teamName = null;

        condition.setPattern(true);
        //order.setUseDerived(false);
        List<TeamStudentVo> studentList = teamStudentService.findTeamStudentByConditionStudent(condition, page,
                order);
        List<Object> result = new ArrayList<Object>(studentList.size());

        for (TeamStudentVo teamStudent : studentList) {
            Map<String, Object> entity = new HashMap<String, Object>();
            entity.put("id", teamStudent.getStudentId());
            entity.put("num", teamStudent.getEmpCode());
            entity.put("name", teamStudent.getName());
            entity.put("empCard",teamStudent.getEmpCard());
            Long diff = DateUtils.getDateBetween(new Date(), teamStudent.getCreateDate());
            if (diff - 15 >= 0) {
                entity.put("delete", false);
            } else {
                entity.put("delete", true);
            }

            /** 查找年级名称 */
            Integer gid = teamStudent.getGradeId();
            /** 如果这次和上次是同一个年级，就不用重复拿年级名称了 */
            if (gradeId - gid != 0) {
                Grade grade = gradeService.findGradeById(gid);
                gradeName = grade.getName();
                gradeId = gid;
            }
            entity.put("gradeName", gradeName);

            /** 查询班级名称，原因同上 */
            Integer tid = teamStudent.getTeamId();
            if (teamId - tid != 0) {
                Team team = teamService.findTeamById(tid);
                teamName = team.getName();
                teamId = tid;
            }
            entity.put("teamName", teamName);

            Integer userid = teamStudent.getUserId();
            if (userid != null) {
                List<ParentVo> parents = parentService.findParentsByStudentUserId(teamStudent.getUserId());
                entity.put("parents", parents);
            }

            result.add(entity);
        }
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Object get() {
        ModelAndView model = new ModelAndView();
        model.setViewName(DIR + "/student_add");
        return model;
    }

    /**
     * 添加学生
     *
     * @param userInfo 当前用户
     * @param data     json格式对象
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@CurrentUser UserInfo userInfo, @RequestParam(value = "data", required = true) String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        String grade = jsonObject.getString("grade");

        String team = jsonObject.getString("team");
        String num = jsonObject.getString("num");
        String name = jsonObject.getString("name");
        String guardianPhone = jsonObject.getString("guardianPhone");
        String guardian = jsonObject.getString("guardian");
        String kh=jsonObject.getString("kh");
        Integer schoolId = userInfo.getSchoolId();
        String schoolYear = userInfo.getSchoolYear();
        Integer appid = SysContants.SYSTEM_APP_ID;
        String studentType = SysContants.USER_TYPE_STUDENT;
        String parentType = SysContants.USER_TYPE_PARENT;
        List<EmployeeList> list = new ArrayList<>();
        Map<String, Object> result = studentService.addStudentFromExcelImport(kh,grade, team, num, name, guardianPhone,
                guardian, schoolId, schoolYear, appid, studentType, parentType, list);
        StudentTmp tmp = JSONObject.parseObject(JSON.toJSONString(result), StudentTmp.class);
        Integer status = tmp.getStatus();

        if (status - 0 == 0) {

            //推送到海康门禁
            httpService.addUserToHk(true, num, kh, HikvisonJob.getHkConfig());

            // 食堂接口
            if (list != null && list.size() > 0) {
                // 判断学生信息是否添加成功 调用远程接口发送数据
                if (address != null && !address.equals("") && canteen != null && !canteen.equals("") && list.size()>0) {
                      // List<List<DetailVo>> postData = new ArrayList<>(1);
                     // postData.add(list);
                     // httpService.addEmploye(null, address + canteen, null, 2, postData, 1, null);
                    // 2021,11月份将原来的与食堂数据的推送进行修改，将卡号与学号由我们系统统一推送到食堂
                    Object object = JSONObject.toJSON(list);
                    JSONObject param = new JSONObject();
                    param.put("sign_name","kksss");
                    //param.put("tran_code","emp_add");
                    param.put("tran_code","emp_add");
                    param.put("employeeList", object);
                    //canteenThreadPoolExecutor.submit
                    HttpRequestResult httpRequestResult=null;
                    HttpRequestConfig config = HttpRequestConfig.create().url(address + canteen)
                            .addHeader("content-type", "application/json")
                            .httpEntityType(HttpEntityType.ENTITY_STRING);
                    //config.json(shiTangDate.toString());
                    config.json(param.toString());
                    try {
                        httpRequestResult = HttpClientUtils.post(config);
                        if (httpRequestResult == null) {
                            log.info("空数据");
                        }
                        if (200 == httpRequestResult.getCode()) {
                            String responseText = httpRequestResult.getResponseText();
                            if (("").equals(responseText) || responseText == null) {
                                log.info("空数据2");
                            }
                            JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                            String statusCode = jsonObject2.getString("statusCode");
                            String result2 = jsonObject2.getString("result");
                            if (("").equals(statusCode) || statusCode == null && ("").equals(result2) || result2 == null) {
                                log.info("返回数据3");
                            }
                            if ("200".equals(statusCode) && "true".equals(result2)) {
                                List<Map<String,Object>> list2= (List<Map<String, Object>>) jsonObject2.get("data");
                                if(list2.size()<=0){
                                    log.info("返回空数据4");
                                }
                                /*食堂返回信息
                                 * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"测试11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
                                 */
                                for(Map<String,Object> aa:list2){
                                    if(aa.get("deal_msg")=="success"){
                                        log.info("食堂接口--添加用户信息到远程接口--食堂接口成功");
                                        basicSQLService.update("update pj_student set is_send_canteen=1 where is_delete=0 and  school_id="+userInfo.getSchoolId()+" and  user_id="+list.get(0).getEmp_pycode());
                                    }else{
                                        log.info("添加失败"+aa.get("deal_msg"));
                                    }
                                }
                            } else {
                                log.info("食堂接口--添加用户信息到远程接口失败--食堂接口成功 错误信息 {}", httpRequestResult);
                            }
                        }else {
                            log.error("食堂接口--添加用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
                        }
                        log.info("食堂接口--食堂添加接口添加状态{}"," 返回信息: {}----"+httpRequestResult);
                    } catch (IOException e) {
                        log.info("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}"+ e);
                    }
                } else {
                    log.error("调用远程接口失败，请检查配置接口信息是否正确！");
                }
                // 发送用户信息到图书馆
                if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
                    // 发送用户信息到图书馆
                    libraryService.studentSend(libraryACnteen, libraryLogin, libraryCreate);
                } else {
                    log.error("调用图书馆远程接口失败，请检查配置接口信息是否正确！");
                }
               // basicSQLService.update("update pj_student set is_send_canteen=1 where is_delete=0 and  school_id="+userInfo.getSchoolId()+" and  user_id="+list.get(0).getEmp_pycode());

                // 定时任务发送用户信息到海康
//                if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
//                    ArtemisConfig artemisConfig = new ArtemisConfig();
//                    artemisConfig.setHost(hikvisionHost);
//                    artemisConfig.setAppKey(hikvisionAppKey);
//                    artemisConfig.setAppSecret(hikvisionAppSecret);
//                    // 0：学生 1：教师
//                    httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 0);
//                } else {
//                    log.error("调用海康远程接口失败，请检查配置接口信息是否正确！");
//                }
            }
            //图书馆添加

        }
        return result;
    }

    /**
     * 更新学生信息
     *
     * @param
     * @return SUCCESS更新成功 DATA_NO_FIND找不到记录
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@CurrentUser UserInfo user,
                         @RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name,
                         @RequestParam(value = "oldMobile") String oldMobile,
                         @RequestParam(value = "newMobile") String newMobile) {
        if (oldMobile == null || "".equals(oldMobile)) {
            return "FAIL";
        }
        Integer schoolId = user.getSchoolId();

        List<UserBinding> userBindingList = userBindingService.findUserListByBindingName(newMobile);
        for (UserBinding userBinding : userBindingList) {
            SchoolUser schoolUser = schoolUserService.findSchoolUserByUserId(userBinding.getUserId());
            if (schoolUser != null && schoolUser.getSchoolId() - schoolId == 0) {
                return "PHONE_REPEAT";
            }
        }

        Parent parent = parentService.findParentByMobileAndSchoolId(oldMobile, schoolId);

        /** 原电话号码和新电话号码不一样，需要修改到userbing */
        if (!oldMobile.equals(newMobile)) {
            Parent nparent = parentService.findParentByMobileAndSchoolId(newMobile, schoolId);
            if (nparent != null) {
                return "PHONE_REPEAT";
            }
            parent.setMobile(newMobile);

            UserBinding userBinding = userBindingService.findUnique(parent.getUserId(), oldMobile);
            if (userBinding != null) {
                userBinding.setBindingName(newMobile);
                userBinding.setModifyDate(new Date());
                userBindingService.modify(userBinding);
            }
        }
        parent.setName(name);
        parent.setModifyDate(new Date());
        parentService.modify(parent);
        String sql="SELECT * FROM pj_student where id="+id;
        List<Map<String,Object>> student=basicSQLService.find(sql);
        //推送到食堂进行修改
        EmployeeList employeeList=new EmployeeList();
        //设置参数
        employeeList.setEmp_pycode((String) student.get(0).get("user_id"));
        employeeList.setEmp_tel(newMobile);
        Object object = JSONObject.toJSON(employeeList);
        JSONObject param = new JSONObject();
        param.put("sign_name","kksss");
        param.put("tran_code","emp_update");
        param.put("employeeList", object);
        HttpRequestResult httpRequestResult=null;
        HttpRequestConfig config = HttpRequestConfig.create().url(address + canupdate)
                .addHeader("content-type", "application/json")
                .httpEntityType(HttpEntityType.ENTITY_STRING);
        //config.json(shiTangDate.toString());
        config.json(param.toString());
        try {
            httpRequestResult = HttpClientUtils.post(config);
            log.info("食堂接口--{}"," 返回信息: {}----"+httpRequestResult);
        } catch (IOException e) {
            log.info("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}"+ e);
        }

        return "SUCCESS";
    }

    /**
     * 删除教师信息
     *
     * @param userInfo 当前用户
     * @param
     * @return
     */
    @RequestMapping(value = "batchDelete")
    @ResponseBody
    public Object delete(@CurrentUser UserInfo userInfo, @RequestParam(value = "ids", required = true) String ids) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();
        List<Integer> errorIds = new ArrayList<Integer>();

        Integer[] studentIds = null;

        studentIds = JSON.parseObject(ids, Integer[].class);

        if (studentIds == null) {

            result.put("status", "DATA_ERROR");
        } else if (studentIds.length == 0) {
            result.put("status", "DATA_NO_FIND");
        } else {
            String studentType = SysContants.USER_TYPE_STUDENT;
            String parentType = SysContants.USER_TYPE_PARENT;
            log.debug("this is studenList==============>" + studentIds.length);
            for (Integer studentId : studentIds) {
                Student student = studentService.findStudentById(studentId);
                log.debug(student.getName());
                if (student == null) {
                    continue;
                }
                Long diff = DateUtils.getDateBetween(new Date(), student.getCreateDate());
                if (diff - 15 >= 0) {
                    errorIds.add(studentId);
                } else {
                    studentService.removeAllStudentInfoById(studentId, studentType, parentType);
                    log.debug("studentService is ok=============>remove");
                    schoolInitService.deleteStudentTmpByStudentId(studentId);
                    log.debug("schoolInitService is ok====================>deleteStudentTmpByStudentId");
                }
            }
        }
        if (errorIds.size() > 0) {
            result.put("status", "ERROR");
            result.put("message", "Id=" + errorIds.toString() + "的记录不在删除时间内，无法删除这些记录");
        } else {
            result.put("status", "SUCCESS");
        }
        return result;
    }

    @Autowired
    @Qualifier("jcStageService")
    private StageService stageService;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public Object export(@CurrentUser UserInfo userInfo) {
        ModelAndView model = new ModelAndView();
        String[] stages = userInfo.getStageCodes();

        List<Object> result = new ArrayList<Object>(stages == null ? 0 : stages.length);

        for (String stage : stages) {
            Map<String, String> map = new HashMap<String, String>();
            Stage tmp = stageService.findStageByCode(stage);
            map.put("code", stage);
            map.put("name", tmp.getName());
            result.add(map);
        }

        model.addObject("result", result);
        model.addObject("userid", userInfo.getId());
        model.setViewName(DIR + "/student_export");

        return model;
    }

    @RequestMapping(value = "/grade/list", method = RequestMethod.GET)
    public Object gradeList(@CurrentUser UserInfo userInfo,
                            @RequestParam(value = "code", required = true) String code) {
        ModelAndView model = new ModelAndView();

        Integer schoolId = userInfo.getSchoolId();
        String schoolYear = userInfo.getSchoolYear();
        List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);

        List<Object> result = new ArrayList<Object>();

        for (Grade grade : gradeList) {
            if (!code.equals(grade.getStageCode())) {
                continue;
            }

            Map<String, Object> entity = new HashMap<String, Object>();

            entity.put("grade", grade);

            TeamCondition codition = new TeamCondition();
            codition.setSchoolId(schoolId);
            codition.setSchoolYear(schoolYear);
            codition.setGradeId(grade.getId());
            List<Team> teamList = teamService.findTeamByCondition(codition, null, Order.asc("team_number"));

            entity.put("teamList", teamList);
            result.add(entity);
        }

        model.addObject("result", result);
        model.setViewName(DIR + "/grade_list");

        return model;
    }
    /*
    *修改学生信息
    * */
    @RequestMapping("/updateStudent")
    @ResponseBody
    public Map<String,Object> xiugaiStudent(@RequestBody Student student) {
        log.warn("getId"+student.getId()+"---------getTeamId"+student.getTeamId());
        Map<String,Object> map= studentService.updateasdasdas(student);
        log.warn("map"+map.get("message"));
        return  map;
    }
}
