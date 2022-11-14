package platform.education.generalTeachingAffair.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import framework.generic.dao.Page;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import platform.education.generalTeachingAffair.dao.*;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.service.ParentProxyService;
import platform.education.generalTeachingAffair.utils.DateUtil;
import platform.education.generalTeachingAffair.utils.hikvision.HikvisionClient;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpEntityType;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.EmployeeList;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.user.model.Role;
import platform.education.user.service.RoleService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yhc cmb
 * @Date: 2020/9/30 11:42
 * @Description: 用于http请求实现
 */
public class HttpServiceImpl implements HttpService {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 食堂线程池
     */
    private static ArrayBlockingQueue<Runnable> canteenArrayBlockingQueue = new ArrayBlockingQueue(100);
    private static ThreadFactory canteenThreadFactory = new CanteenNameTreadFactory();
    private static ThreadPoolExecutor canteenThreadPoolExecutor =
            new ThreadPoolExecutor(1, 2, 5, TimeUnit.MINUTES, canteenArrayBlockingQueue, canteenThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());


    /**
     * 海康线程池
     */
    private static ArrayBlockingQueue<Runnable> hikvisionArrayBlockingQueue = new ArrayBlockingQueue(100);
    private static ThreadFactory hikvisionThreadFactory = new HikvisionTreadFactory();
    // 改为单线程线程池，避免 查询数据发送重复
    private static ThreadPoolExecutor hikvisionThreadPoolExecutor =
            new ThreadPoolExecutor(1, 1, 5, TimeUnit.MINUTES, hikvisionArrayBlockingQueue, hikvisionThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());


    /**
     * 图书馆线程池 使用LinkedBlockingQueue 无界队列储存
     */
//    private static BlockingQueue<Runnable> libraryArrayBlockingQueue = new ArrayBlockingQueue(100);
    private static BlockingQueue<Runnable> libraryArrayBlockingQueue = new LinkedBlockingQueue<>();

    private static ThreadFactory libraryThreadFactory = new LibraryNameTreadFactory();
    private static ThreadPoolExecutor libraryThreadPoolExecutor =
            new ThreadPoolExecutor(1, 2, 5, TimeUnit.MINUTES, libraryArrayBlockingQueue, libraryThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 校车线程池
     */
    private static ArrayBlockingQueue<Runnable> schoolBusArrayBlockingQueue = new ArrayBlockingQueue(20);
    private static ThreadFactory schoolBusThreadFactory = new SchoolBusNameTreadFactory();
    private static ThreadPoolExecutor schoolBusExecutorService =
            new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, schoolBusArrayBlockingQueue, schoolBusThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    // 采用单线程线程池
//    private static ExecutorService schoolBusExecutorService = Executors.newSingleThreadExecutor();

    /**
     * 校车修改卡号
     */
    private static ExecutorService schoolBusModifyCardExecutorService = Executors.newSingleThreadExecutor();


    /**
     * 角色
     */
    private RoleService roleService;

    private DepartmentDao departmentDao;

    private StringRedisTemplate stringRedisTemplate;

    private StudentDao studentDao;

    private TeacherDao teacherDao;

    private TeamDao teamDao;

//    @Resource
//    private ThredService thredService;

    @Autowired
    @Qualifier("parentProxyService")
    protected ParentProxyService parentProxyService;

    @Autowired
    private BasicSQLService basicSQLService;


    private ParentStudentDao parentStudentDao;

    private SchoolBusDao schoolBusDao;


    public void setSchoolBusDao(SchoolBusDao schoolBusDao) {
        this.schoolBusDao = schoolBusDao;
    }

    public void setParentStudentDao(ParentStudentDao parentStudentDao) {
        this.parentStudentDao = parentStudentDao;
    }

    public void setParentProxyService(ParentProxyService parentProxyService) {
        this.parentProxyService = parentProxyService;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    /**
     * 广播所有设备id
     * @param bobaoHost      服务器地址
     * @param devicesIdUrl   查询所有id接口地址
     * @param devicesTtsUrl  tts转换
     */
    @Override
    public String [] findByids(String bobaoHost, String devicesIdUrl, String devicesTtsUrl) {
        int [] ids=new int []{};
        HttpRequestResult httpRequestResult=null;
        String  idss = HttpClientUtils.get(bobaoHost+devicesIdUrl);
        log.info("广播接口返回"+idss.split(","));
        return idss.split(",");
    }
    /*
    * 请求广播tts接口
    */
    @Override
    public String findByTts(int[] ids, String text, Integer times, String bobaoHost, String devicesIdUrl, String devicesTtsUrl) {
        JSONObject param = new JSONObject();
        String iss="[10636,10586,10635,10637]";
        param.put("deviceIDArray", iss);
        param.put("text", text);
        param.put("times", 1);
        try{
            String  idss = HttpClientUtils.postJson(bobaoHost+devicesTtsUrl,param.toJSONString());
            log.info("tts播报"+idss);
            return idss;
        }catch (Exception e){
            log.info("tts播报错误"+e);
        }
        return null;
    }
     //人员发送
    @Override
    public String createShitan(Integer type,String url) {
        //学生 0 ，老师 1
        if (type == 0) {
            while (true) {
                List<Student> listLists = studentDao.findByStudentShitang();
                System.out.println("学生推送"+listLists.size());

                if (listLists.size() <= 0) {
                    return "success";
                } else {
                    List<EmployeeList> list = new ArrayList<>();
                    for (Student aa : listLists) {
                        EmployeeList employeeList = new EmployeeList();
                        employeeList.setEmp_name(aa.getName());
                        // 入学日期
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        employeeList.setEmp_workdate(simpleDateFormat.format(new Date()));
                        employeeList.setEmp_code(aa.getEmpCode());
                        employeeList.setEmp_pycode(String.valueOf(aa.getUserId()));
                        employeeList.setEmp_card(aa.getEmpCard());
                        //employeeList.setDept_code("000002");
                      String teamName= banjimingchengzhuanhuan(aa.getTeamName());
                        System.out.println(teamName);
                        employeeList.setDept_name(teamName);
                        employeeList.setEmp_tel(aa.getMobile());
                        employeeList.setEmp_idcard(aa.getUserName());
                        list.add(employeeList);
                    }
                    Object object = JSONObject.toJSON(list);
                    JSONObject param = new JSONObject();
                    param.put("sign_name", "kksss");
                    param.put("tran_code", "emp_add");
                    param.put("employeeList", object);

                    HttpRequestResult httpRequestResult = null;
                    HttpRequestConfig config = HttpRequestConfig.create().url(url)
                            .addHeader("content-type", "application/json")
                            .httpEntityType(HttpEntityType.ENTITY_STRING);
                    config.json(param.toString());
                    try {
                        httpRequestResult = HttpClientUtils.post(config);
                        System.out.println("返回"+httpRequestResult);
                        if(httpRequestResult==null){
                            System.out.println("失败");
                            return "失败";
                        }
                        String str=result(httpRequestResult,0);
                        if(str==null){
                            System.out.println("tuisongshibai");
                            return "推送失败";
                        }
                        System.out.println("食堂接口--食堂添加接口添加状态{}，  返回信息: {}----" + httpRequestResult);
                    } catch (IOException e) {
                        System.out.println("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}" + e);
                    }
                }
            }
        }else{
            while (true) {
                List<Teacher> listLists = teacherDao.findByTeacherShitang();
                if (listLists.size() <= 0) {
                    return null;
                } else {
                    List<EmployeeList> list = new ArrayList<>();
                    for (Teacher aa : listLists) {
                        EmployeeList employeeList = new EmployeeList();
                        employeeList.setEmp_name(aa.getName());
                        // 入学日期
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        employeeList.setEmp_workdate(simpleDateFormat.format(new Date()));
                        employeeList.setEmp_code(aa.getEmpCode());
                        employeeList.setEmp_pycode(String.valueOf(aa.getUserId()));
                        employeeList.setEmp_card(aa.getEmpCard());
                        //employeeList.setDept_code("000002");
                        //employeeList.setDept_name(department.getTeamName());
                        //list.add(new DetailVo("dept_name", team.getName(), "String"));
                        Department department=departmentDao.findDepartmentByteacherId(aa.getSchoolId(),aa.getId());
                        if(department==null){
                            employeeList.setDept_name("教师");
                        }else if(department.getName() !="" && department.getName()!=null){
                            log.info("部门"+department.getName());
                            employeeList.setDept_name(department.getName());
                        }else{
                            employeeList.setDept_name("教师");
                        }
                        employeeList.setEmp_tel(aa.getMobile());
                        employeeList.setEmp_idcard(aa.getUserName());
                        list.add(employeeList);
                    }
                    Object object = JSONObject.toJSON(list);
                    JSONObject param = new JSONObject();
                    param.put("sign_name", "kksss");
                    //param.put("tran_code","emp_add");
                    param.put("tran_code", "emp_add");
                    param.put("employeeList", object);

                    //canteenThreadPoolExecutor.submit
                    HttpRequestResult httpRequestResult = null;
                    HttpRequestConfig config = HttpRequestConfig.create().url(url)
                            .addHeader("content-type", "application/json")
                            .httpEntityType(HttpEntityType.ENTITY_STRING);
                    //config.json(shiTangDate.toString());
                    config.json(param.toString());
                    try {
                        httpRequestResult = HttpClientUtils.post(config);
                        String str=result(httpRequestResult,1);
                        if(str==null){
                            return "推送食堂失败";
                        }
                          } catch (IOException e) {
                        System.out.println("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}" + e);
                    }
                }
            }
        }
    }
    //班级格式转换
    private String banjimingchengzhuanhuan(String teamName){
        if(teamName==null && teamName.equals("")){
            return null;
        }else {
            if (teamName.equals("一年级(1)班")) {
                String str = "一年级一班";
                return str;
            }
            if (teamName.equals("一年级(2)班")) {
                String str = "一年级二班";
                return str;
            }
            if (teamName.equals("一年级(3)班")) {
                String str = "一年级三班";
                return str;
            }
            if (teamName.equals("一年级(4)班")) {
                String str = "一年级四班";
                return str;
            }
            if (teamName.equals("一年级(5)班")) {
                String str = "一年级五班";
                return str;
            }
            if (teamName.equals("一年级(6)班")) {
                String str = "一年级六班";
                return str;
            }
            if (teamName.equals("一年级(7)班")) {
                String str = "一年级七班";
                return str;
            }
            if (teamName.equals("一年级(8)班")) {
                String str = "一年级八班";
                return str;
            }
            if (teamName.equals("一年级(9)班")) {
                String str = "一年级九班";
                return str;
            }
            if (teamName.equals("一年级(10)班")) {
                String str = "一年级十班";
                return str;
            }
            if (teamName.equals("一年级(11)班")) {
                String str = "一年级十一班";
                return str;
            }
            if (teamName.equals("二年级(2)班")) {
                String str = "二年级二班";
                return str;
            }
            if (teamName.equals("二年级(3)班")) {
                String str = "二年级三班";
                return str;
            }
            if (teamName.equals("二年级(4)班")) {
                String str = "二年级四班";
                return str;
            }
            if (teamName.equals("二年级(5)班")) {
                String str = "二年级五班";
                return str;
            }if (teamName.equals("二年级(6)班")) {
                String str = "二年级六班";
                return str;
            }if (teamName.equals("二年级(7)班")) {
                String str = "二年级七班";
                return str;
            }if (teamName.equals("二年级(8)班")) {
                String str = "二年级八班";
                return str;
            }
            if (teamName.equals("二年级(9)班")) {
                String str = "二年级九班";
                return str;
            }
            if (teamName.equals("二年级(10)班")) {
                String str = "二年级十班";
                return str;
            }if (teamName.equals("二年级(11)班")) {
                String str = "二年级十一班";
                return str;
            }if (teamName.equals("二年级(1)班")) {
                String str = "二年级一班";
                return str;
            }if (teamName.equals("三年级(1)班")) {
                String str = "三年级一班";
                return str;
            }
            if (teamName.equals("三年级(2)班")) {
                String str = "三年级二班";
                return str;
            }
            if (teamName.equals("三年级(3)班")) {
                String str = "三年级三班";
                return str;
            }
            if (teamName.equals("三年级(4)班")) {
                String str = "三年级四班";
                return str;
            }
            if (teamName.equals("三年级(5)班")) {
                String str = "三年级五班";
                return str;
            }
            if (teamName.equals("三年级(6)班")) {
                String str = "三年级六班";
                return str;
            }
            if (teamName.equals("三年级(7)班")) {
                String str = "三年级七班";
                return str;
            }
            if (teamName.equals("三年级(8)班")) {
                String str = "三年级八班";
                return str;
            }
            if (teamName.equals("三年级(9)班")) {
                String str = "三年级九班";
                return str;
            }
            if (teamName.equals("三年级(10)班")) {
                String str = "三年级十班";
                return str;
            }
            if (teamName.equals("三年级(11)班")) {
                String str = "三年级十一班";
                return str;
            }if (teamName.equals("四年级(1)班")) {
                String str = "四年级一班";
                return str;
            }
            if (teamName.equals("四年级(2)班")) {
                String str = "四年级二班";
                return str;
            }
            if (teamName.equals("四年级(3)班")) {
                String str = "四年级三班";
                return str;
            }
            if (teamName.equals("四年级(4)班")) {
                String str = "四年级四班";
                return str;
            }
            if (teamName.equals("四年级(5)班")) {
                String str = "四年级五班";
                return str;
            }
            if (teamName.equals("四年级(6)班")) {
                String str = "四年级六班";
                return str;
            }
            if (teamName.equals("四年级(7)班")) {
                String str = "四年级七班";
                return str;
            }
            if (teamName.equals("四年级(8)班")) {
                String str = "四年级八班";
                return str;
            }
            if (teamName.equals("四年级(9)班")) {
                String str = "四年级九班";
                return str;
            }
            if (teamName.equals("四年级(10)班")) {
                String str = "四年级十班";
                return str;
            }
            if (teamName.equals("四年级(11)班")) {
                String str = "四年级十一班";
                return str;
            }if (teamName.equals("五年级(1)班")) {
                String str = "五年级一班";
                return str;
            }
            if (teamName.equals("五年级(2)班")) {
                String str = "五年级二班";
                return str;
            }
            if (teamName.equals("五年级(3)班")) {
                String str = "五年级三班";
                return str;
            }
            if (teamName.equals("五年级(4)班")) {
                String str = "五年级四班";
                return str;
            }
            if (teamName.equals("五年级(5)班")) {
                String str = "五年级五班";
                return str;
            }
            if (teamName.equals("五年级(6)班")) {
                String str = "五年级六班";
                return str;
            }
            if (teamName.equals("五年级(7)班")) {
                String str = "五年级七班";
                return str;
            }
            if (teamName.equals("五年级(8)班")) {
                String str = "五年级八班";
                return str;
            }
            if (teamName.equals("五年级(9)班")) {
                String str = "五年级九班";
                return str;
            }
            if (teamName.equals("五年级(10)班")) {
                String str = "五年级十班";
                return str;
            }
            if (teamName.equals("五年级(11)班")) {
                String str = "五年级十一班";
                return str;
            }if (teamName.equals("六年级(1)班")) {
                String str = "六年级一班";
                return str;
            }
            if (teamName.equals("六年级(2)班")) {
                String str = "六年级二班";
                return str;
            }
            if (teamName.equals("六年级(3)班")) {
                String str = "六年级三班";
                return str;
            }
            if (teamName.equals("六年级(4)班")) {
                String str = "六年级四班";
                return str;
            }
            if (teamName.equals("六年级(5)班")) {
                String str = "六年级五班";
                return str;
            }
            if (teamName.equals("六年级(6)班")) {
                String str = "六年级六班";
                return str;
            }
            if (teamName.equals("六年级(7)班")) {
                String str = "六年级七班";
                return str;
            }
            if (teamName.equals("六年级(8)班")) {
                String str = "六年级八班";
                return str;
            }
            if (teamName.equals("六年级(9)班")) {
                String str = "六年级九班";
                return str;
            }
            if (teamName.equals("六年级(10)班")) {
                String str = "六年级十班";
                return str;
            }
            if (teamName.equals("六年级(11)班")) {
                String str = "六年级十一班";
                return str;
            }
        }
        return null;
    }


    /**
     * 食堂数据
     *
     * @param tdi
     * @param url      服务器地址
     * @param status   接口地址 0: 单条数据 1：批量 2：单条（已获取数据）
     * @param schoolId
     * @param type     数据类型 0：老师 1：学生
     * @param udi      学生数据
     * @Description: 食堂调用远程接口发送数据
     * 已弃用 2021，11，16 食堂更新接口，此方法已弃用
     */
    @Override
    public void addEmploye(final TeacherDetailInfo tdi, final String url, final Integer schoolId,
                           final Integer status, final List<List<DetailVo>> postData, final Integer type, final UserDetailInfo udi) {
        try {
          //  canteenThreadPoolExecutor.submit(new CanteenData(tdi, url, schoolId, status, postData, type, udi));
        } catch (Exception e) {
            log.error("食堂数据线程池异常: {}", e);
        }
    }

    /**
     * 发送用户数据到食堂，后面再通过定时，获取没有发卡的用户，请求食堂接口获取卡号，有就绑定，没有就下次继续
     */
    //11,食堂更新接口，所有数据从我们系统推给食堂
  /*  class CanteenData implements Runnable {
        private TeacherDetailInfo tdi;
        private String url;
        private Integer schoolId;
        private Integer status;
        private List<List<DetailVo>> postData;
        private Integer type;
        private UserDetailInfo udi;
        private Runnable callback;

        private String empCode;
        private String card;

        // status   接口地址 0: 单条数据 1：批量 2：单条（已获取数据）
        public CanteenData(TeacherDetailInfo tdi, String url, Integer schoolId, Integer status, List<List<DetailVo>> postData, Integer type, UserDetailInfo udi) {
            this.tdi = tdi;
            this.url = url;
            this.schoolId = schoolId;
            this.status = status;
            this.postData = postData;
            this.type = type;
            this.udi = udi;
        }

        @Override
        public void run() {
            if (status != null) {
                HttpRequestConfig config = HttpRequestConfig.create().url(url)
                        .addHeader("Content-Type", "application/json")
                        .httpEntityType(HttpEntityType.ENTITY_STRING);
                HttpRequestResult httpRequestResult = null;
                JSONObject param = new JSONObject();
                param.put("table_name", "employee");
                param.put("table_where", "emp_id=0");
                String userName = "";
                if (status == 0) {
                    List<DetailVo> list = new ArrayList<>(6);
                    // 获取用户信息
                    userName = getCanteenData(tdi, udi, schoolId, type, list);
                    Object object = JSONObject.toJSON(list);
                    param.put("detailList", object);
                    config.json(param.toString());
                } else if (status == 1) {
                    // 食堂对接要求：都是单条发送，没有批量接口
                    for (List<DetailVo> postDatum : postData) {
                        userName = getUserName(postDatum);
                        Object object = JSONObject.toJSON(postDatum);
                        param.put("detailList", object);
                        config.json(param.toString());
                        try {
                            httpRequestResult = HttpClientUtils.post(config);
                            log.info("食堂接口--食堂添加接口添加状态{}，  返回信息: {}", status, httpRequestResult);
                            result(httpRequestResult, type, userName);
                        } catch (IOException e) {
                            log.error("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}", status, e);
                        }
                    }
                    return;
                } else if (status == 2) {
                    Object object = JSONObject.toJSON(postData.get(0));
                    param.put("detailList", object);
                    config.json(param.toString());
                    userName = getUserName(postData.get(0));
                }
                try {
                    httpRequestResult = HttpClientUtils.post(config);
                    log.info("食堂接口--食堂添加接口添加状态{}，  返回信息: {}", status, httpRequestResult);
                    result(httpRequestResult, type, userName);
                } catch (IOException e) {
                    log.error("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}", status, e);
                }
                if (callback != null) {
                    callback.run();
                }
            }
        }
    }*/

    public String getUserName(List<DetailVo> postDatum) {
        for (DetailVo detailVo : postDatum) {
            String name = detailVo.getName();
            if (name != null && ("emp_code").equals(name)) {
                return detailVo.getValue();
            }
        }
        return "";
    }

    /**
     * 判断返回信息
     *
     * @param httpRequestResult
     * @param type              数据类型 1：老师 0：学生
     */
    public String result(HttpRequestResult httpRequestResult, Integer type) {
        if(type==0){
        if (httpRequestResult == null) {
            return null;
        }
        if (200 == httpRequestResult.getCode()) {
            String responseText = httpRequestResult.getResponseText();
            if (("").equals(responseText) || responseText == null) {
                return  null;
            }
            JSONObject jsonObject = JSONObject.parseObject(responseText);
            String statusCode = jsonObject.getString("statusCode");
            String result = jsonObject.getString("result");
            if (("").equals(statusCode) || statusCode == null && ("").equals(result) || result == null) {
                return null;
            }
            if ("200".equals(statusCode) && "true".equals(result)) {
             List<Map<String,Object>> list= (List<Map<String, Object>>) jsonObject.get("data");
             if(list.size()<=0){
                 return null;
             }
             /*食堂返回信息
             * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"测试11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
             */
             for(Map<String,Object> aa:list){
                 String str= (String) aa.get("deal_msg");
                 if(str.equals("success")){
                     log.info("食堂接口--添加用户信息到远程接口--食堂接口成功");
                     String asd = (String) aa.get("emp_code");
                     basicSQLService.update("update pj_student set is_send_canteen =1 where emp_code ='"+asd+"'");
                 }else{

                     System.out.println("添加失败"+aa.get("deal_msg"));
                     return null;
                 }
             }
            } else {
                log.info("食堂接口--添加用户信息到远程接口失败--食堂接口成功 错误信息 {}", httpRequestResult);
            }
        }else {
            log.error("食堂接口--添加用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
        }
        } else{
            //教师推送到食堂
            if (httpRequestResult == null) {
                return null;
            }
            if (200 == httpRequestResult.getCode()) {
                String responseText = httpRequestResult.getResponseText();
                if (("").equals(responseText) || responseText == null) {
                    return  null;
                }
                JSONObject jsonObject = JSONObject.parseObject(responseText);
                String statusCode = jsonObject.getString("statusCode");
                System.out.println("返回数据"+jsonObject);
                String result = jsonObject.getString("result");
                if (("").equals(statusCode) || statusCode == null && ("").equals(result) || result == null) {
                    return null;
                }
                if ("200".equals(statusCode) && "true".equals(result)) {
                    List<Map<String,Object>> list= (List<Map<String, Object>>) jsonObject.get("data");
                    if(list.size()<=0){
                        return null;
                    }
                    /*食堂返回信息
                     * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"测试11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
                     */
                    for(Map<String,Object> aa:list){
                        String str= (String) aa.get("deal_msg");
                        if(str.equals("success")){
                            log.info("食堂接口--添加用户信息到远程接口--食堂接口成功");
                            /*Teacher teacher = new Teacher();
                            teacher.setEmpCode((String) aa.get("emp_code"));*/
                            String empCode= (String) aa.get("emp_code");
                            //teacher.setIsSendCanteen(1);
                            basicSQLService.update("update pj_teacher set is_send_canteen =1 where emp_code ='"+empCode+"'");
                        }else{
                            log.info("添加失败:"+aa.get("deal_msg"));
                            return null;
                        }
                    }
                } else {
                    log.info("食堂接口--添加用户信息到远程接口失败--食堂接口成功 错误信息 {}", httpRequestResult);
                    return null;
                }
            }else {
                log.error("食堂接口--添加用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
                return null;
            }
        }
        return "success";
    }

    /**
     * 获取用户数据到食堂
     *
     * @param type 数据类型 0：老师 1：学生
     * @param tdi
     * @param udi  学生数据
     * @param list 用户数据
     */
    public String getCanteenData(final TeacherDetailInfo tdi, final UserDetailInfo udi, Integer schoolId, Integer type, List<DetailVo> list) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String userName = "";
        if (type != null && type == 0) {
            // 教师数据，初始化时只会有一个部门，后面初始化完成后，可以在界面中指定多个部门（所以不考虑）
            String departmentIds = tdi.getDepartmentIds();
            if (departmentIds != null && !departmentIds.equals("")) {
                List<String> deparId = new ArrayList<>();
                DepartmentCondition departmentCondition = new DepartmentCondition();
                deparId = Arrays.asList(departmentIds.split(","));
                departmentCondition.setListId(deparId);
                departmentCondition.setSchoolId(schoolId);
                // 根据部门id查询部门名称，食堂那边只接收一个部门
                List<Department> listDepartment = departmentDao.findDepartmentByCondition(departmentCondition, null, null);
                if (listDepartment != null && listDepartment.size() > 0) {
                    list.add(new DetailVo("dept_name", listDepartment.get(0).getId().toString(), "String"));
                } else {
                    // 为空，默认写死
                    list.add(new DetailVo("dept_name", "香市一小", "String"));
                }
            } else {
                // 为空，默认写死
                list.add(new DetailVo("dept_name", "香市一小", "String"));
            }
            list.add(new DetailVo("dept_code", "000001", "String"));
            list.add(new DetailVo("emp_name", tdi.getName(), "String"));
            list.add(new DetailVo("emp_workdate", simpleDateFormat.format(new Date()), "String"));
            // 工号 只需要传递唯一值, 调用的时候可以查询出对应的用户数据
            userName = tdi.getUserName();
            list.add(new DetailVo("emp_code", userName, "String"));
            // 食堂卡号 要求10位可以字母数字，目前以登录账号，必须要传，但是食堂那边不会保存这个卡号，他们自己发卡，所以查询食堂的卡号，根据emp_code查询
            list.add(new DetailVo("emp_card", userName, "String"));
        } else if (type != null && type == 1) {
            // 学生数据
            list.add(new DetailVo("emp_name", udi.getName(), "String"));
            list.add(new DetailVo("emp_workdate", simpleDateFormat.format(new Date()), "String"));
            // 登录的账号
            userName = udi.getUsername();
            list.add(new DetailVo("emp_code", userName, "String"));
            // 食堂卡号 要求10位可以字母数字，目前以登录账号，必须要传，但是食堂那边不会保存这个卡号，他们自己发卡，所以查询食堂的卡号，根据emp_code查询
            list.add(new DetailVo("emp_card", userName, "String"));
            list.add(new DetailVo("dept_code", "000002", "String"));
            // 获取学生的班级信息
            String teamName = udi.getNewTeamName();
            if (teamName != null && !("").equals(teamName)) {
                list.add(new DetailVo("dept_name", teamName, "String"));
            } else {
                list.add(new DetailVo("dept_name", "香市小学班级", "String"));
            }
        }
        return userName;
    }

    /**
     * 食堂自定义线程工厂
     */
    static class CanteenNameTreadFactory implements ThreadFactory {
        // 原子类实现自定义线程编号
        private final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            // 重命名线程
            Thread t = new Thread(r, "canteen-thread-" + atomicInteger.getAndIncrement());
            return t;
        }
    }

    /**
     * 海康接口
     *
     * @param artemisConfig
     * @param addPersonUrl  添加人员接口
     * @param bindCarUrl    绑卡接口
     * @param untieCarUrl   解绑接口
     * @param type          0：学生 1：教师
     */
    @Override
    public void addHikvisionPerson(final ArtemisConfig artemisConfig, final String addPersonUrl, final String bindCarUrl, final String untieCarUrl, final Integer type, final String schoolYear) {
        try {
            hikvisionThreadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    if (artemisConfig == null || StringUtils.isEmpty(addPersonUrl) || StringUtils.isEmpty(bindCarUrl) || type == null) {
                        log.error("海康接口获取参数为空!");
                        return;
                    }
                    long a = System.currentTimeMillis();
                    log.info("海康线程池启动，发送用户到海康，本次发送用户为：{}", type == 0 ? "学生" : "教师");
                    //发送用户信息到海康 教师和学生
                    sendHikvisionUser(artemisConfig, addPersonUrl, type,schoolYear);
                    //untieHikvisionCards(artemisConfig, addPersonUrl, type);
                    // 解绑卡号 is_hikvision_bind_card= 2 需要解绑卡号 ，解绑完成后is_hikvision_bind_card改为0 再进行绑卡操作，由于海康没有提供修改卡号功能，所以需要分为两个步骤，1.先解绑原来的卡号2.绑定新卡号
                    // 海康绑卡状态 is_hikvision_bind_card 1：已绑卡，0：没有2：需要解绑
                    //untieHikvisionCards(artemisConfig, untieCarUrl, type);
                    // 绑定卡号/修改卡号 ，到海康 教师和学生
                   bindHikvisionCards(artemisConfig, bindCarUrl, type);
                    log.info("海康线程任务结束，耗时 {}ms", (System.currentTimeMillis() - a));
                }
            });
        } catch (Exception e) {
            log.error("海康数据线程池异常: {}", e);
        }
    }


    /**
     * 发送用户信息到海康
     *
     * @param artemisConfig
     * @param addPersonUrl
     * @param type          0：学生 1：教师
     */
    public void sendHikvisionUser(ArtemisConfig artemisConfig, String addPersonUrl, Integer type,String schoolYear) {
        try {
            System.out.println("海康添加用户--开始查询");
            int currentPage = 1;
            /*for (int i1 = 1; i1 <= currentPage; i1++) {*/
                // 每次最多批量发送500条
                Page page = new Page();
                page.setCurrentPage(1);
                page.setPageSize(500);
                while (true){
                    List<HikvisionUserPo> userList = new ArrayList<>();

                    if (type == 0) {
                        //学生
                        userList = studentDao.findStudentSendHik(schoolYear,page, null);
                    } else {
                        //老师
                        userList = teacherDao.findTeacherSendHik(page, null);
                    }
                    if (userList == null || userList.size() == 0) {
                        log.info("海康添加--未获取到需要新增的用户");
                        return ;
                    }
                    log.info("海康添加--海康发送用户数据 {} 条", userList.size());
                    //int totalPages = page.getTotalPages();
                    /*if (totalPages != 0) {
                        currentPage = totalPages;
                    }*/
                    String json = JSONObject.toJSONString(userList);
                    String resultData = HikvisionClient.hikvisionPost(artemisConfig, addPersonUrl, json);
                    // 添加用户成功，修改状态
                    if (StringUtils.isEmpty(resultData)) {
                        return ;
                    }
                    HikvisionResponseVo responseVo = JSON.parseObject(resultData, new TypeReference<HikvisionResponseVo>() {
                    });
                    if (responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")) {
                        // 获取success数据
                        List<HikvisionResponseVo.Data.Successes> successesList = responseVo.getData().getSuccesses();
                        log.info("海康添加--新增成功用户为：{}条", !CollectionUtils.isEmpty(successesList) ? successesList.size() : 0);
                        if (CollectionUtils.isEmpty(successesList)) {
                            log.info("海康添加--新增用户返回信息为===={}", responseVo.toString());
                            return;
                        }
                        Integer [] array = new Integer[successesList.size()];
                        /* log.info("userid"+successesList.get(1).getPersonId());*/
                        String [] arrs =new String[successesList.size()];
                        for (int b = 0; b < successesList.size();b++) {
                            HikvisionResponseVo.Data.Successes successesVO = successesList.get(b);
                            Integer clientid = successesVO.getClientId();
                            String personId=successesVO.getPersonId();
                            if (type == 1) {
                                // 截取t
                                array[b] = clientid;
                                arrs[b]=personId;
                            } else {
                                log.info("ClientId"+successesList.get(b).getPersonId());
                                arrs[b]=personId;
                                array[b] =clientid;
                            }
                        }
                        if (array.length > 0) {
                            log.info("海康添加--添加用户成功，需要修改：{}条", array.length);
                            if (type == 0) {
                                log.info("修改");
                                for(int ss=0;ss< array.length;ss++){
                                    studentDao.updateHikvisionUserInfoPersonAdd( array[ss],1, 0,  arrs[ss]);
                                }
                            } else {
                                for(int ss=0;ss< array.length;ss++) {
                                    teacherDao.updateHikvisionUserInfoPersonAdd(array[ss], 1, 2,arrs[ss]);
                                }
                            }
                            // 修改之后分页重新查询
                            //i1 = 0;
                        }
                        log.info("海康添加用户--结束");
                    } else {
                        log.error("海康添加--用户失败！{}", responseVo.getMsg());
                        return;
                    }
                }
        } catch (Exception e) {
            log.error("海康添加--发送用户信息到海康失败, {}", e);
        }
    }


    /**
     * 解绑
     * 只有之前绑定海康成功的卡需要执行
     * 正常流程
     * 1. 已经绑定完成卡号的：流程走完
     * 2. 用户信息已经绑定，卡号没有绑定 ：绑卡 不用解绑再执行绑卡
     *
     * @param artemisConfig
     * @param untieCarUrl
     * @param type          0：学生 1：教师
     */
   public void untieHikvisionCards(ArtemisConfig artemisConfig, String untieCarUrl, Integer type) {
        // 修改完成用户状态进行解绑
        try {
            log.info("开始解除绑卡--海康-------------");
            List<UntieCardList> untieCardList = null;
            // 查询需要解除绑卡的用户
            if (type == 0) {
                untieCardList = studentDao.findUntieHikvisionBindCarStu();
            } else {
                untieCardList = teacherDao.findUntieHikvisionBindCarTeacher();
            }
            if (untieCardList == null || untieCardList.size() == 0) {
                log.info("海康解除绑卡--为获取到需要解绑的卡号");
                return ;
            }
            log.info("海康解除绑卡---用户数据 {} 条", untieCardList.size());
            for (UntieCardList card : untieCardList) {
                // 解析返回数据
                String json = JSONObject.toJSONString(card);
                String cardResultData = HikvisionClient.hikvisionPost(artemisConfig, untieCarUrl, json);
                // 绑卡完成，修改数据库状态
                try {
                    if (StringUtils.isEmpty(cardResultData)) {
                        return;
                    }
                    JSONObject jsonObject = JSON.parseObject(cardResultData);
                    String code = jsonObject.getString("code");
                    String msg = jsonObject.getString("msg");
                    // code 0 成功 0x04a12023 卡号不存在也是同样成功
                    if (code != null && msg != null && ((code.equals("0") && msg.equals("success")) || code.equals("0x04a12023"))) {
                        // 修改状态
                        if (type == 0) {
                            String [] arrayBindCard = {card.getPersonId()};
                            studentDao.updateHikvisionUserInfoPerson(arrayBindCard, null, 0);
                        } else {
                            int personId = Integer.parseInt(card.getPersonId().substring(0, card.getPersonId().length() - 1));
                            Integer[] arrayBindCard = {personId};
                            //teacherDao.updateHikvisionUserInfoPerson(arrayBindCard, null, 0);
                        }
                        log.info("海康解除绑卡---成功， 卡号信息：{}， 返回数据 {}", card.toString(), jsonObject.toJSONString());
                    } else {
                        log.error("海康解除绑卡---失败！{}", jsonObject.toString());
                    }
                } catch (Exception e) {
                    log.error("海康解除绑卡---修改绑卡失败！{}", e);
                }
            }


        } catch (Exception e) {
            log.error("海康解绑绑卡---绑卡失败！{}", e);
        }
    }

    /**
     * 绑定海康卡号/修改卡号也是同一个逻辑
     *
     * @param artemisConfig
     * @param bindCarUrl
     * @param type          0：学生 1：教师
     */
    public void bindHikvisionCards(ArtemisConfig artemisConfig, String bindCarUrl, Integer type) {
        // 修改完成用户状态进行绑卡
        try {
            System.out.println("开始绑卡--海康-------------");
            HikvisionBindCarPojo hikvisionBindCarPojo = new HikvisionBindCarPojo();

            hikvisionBindCarPojo.setStartDate(DateUtil.dateToStr(new Date()));
            //海康系统接口限制，时间必须效益2037-12-31
            hikvisionBindCarPojo.setEndDate("2037-12-30");
            Page page = new Page();
            page.setCurrentPage(1);
            page.setPageSize(50);
            while (true){
                List<CardList> cardList = null;
                if (type == 0) {
                    cardList = studentDao.findHikvisionBindCarStu(page, null);
                } else {
                    cardList = teacherDao.findHikvisionBindCarTeacher(page, null);
                }

                /*for (int j = 1; j <= currentPage; j++) {*/
                // 获取开卡数据发送海康，开卡列表最多支持50条，但是在50条中只要有一条有问题，50条就全部失败，所以可以改成一条
                if (cardList == null || cardList.size() == 0) {
                    System.out.println("海康绑卡---未获取到需要绑定的卡号");
                    return;
                }
                System.out.println("海康绑卡---用户数据 {} 条"+ cardList.size());
                // 解析返回数据
                hikvisionBindCarPojo.setCardList(cardList);
                String cardJson = JSONObject.toJSONString(hikvisionBindCarPojo);
                String cardResultData = HikvisionClient.hikvisionPost(artemisConfig, bindCarUrl, cardJson);
                try {
                    if (StringUtils.isEmpty(cardResultData)) {
                        return;
                    }
                    HikvisonCardBindPojo hikvisonCardBindPojo = JSON.parseObject(cardResultData, new TypeReference<HikvisonCardBindPojo>() {
                    });
                    // HikvisonCardBindPojo{code='0x04a12022', msg='cardNo [0000000001] is exists', data=null} 绑卡失败的 用户卡号已经绑定过
                    if (hikvisonCardBindPojo != null && hikvisonCardBindPojo.getCode().equals("0") && hikvisonCardBindPojo.getMsg().equals("success") && !hikvisonCardBindPojo.getData().equals("")) {
                        List<HikvisonCardBindPojo.Data> dataList = hikvisonCardBindPojo.getData();
                        log.error("海康绑卡---成功用户为：{}条", !CollectionUtils.isEmpty(dataList) ? dataList.size() : 0);
                        if (CollectionUtils.isEmpty(dataList)) {
                            return;
                        }
                        String [] arrayBindCard = new String[dataList.size()];
                        for (int i = 0; i < dataList.size(); i++) {
                            String personId = dataList.get(i).getPersonId();
                            // 截取t
                            if (type == 1) {
                                arrayBindCard[i] = personId;
                                //arrayBindCard[i] = personId.substring(0, personId.length() - 1);
                            } else {
                                arrayBindCard[i] = personId;
                            }
                        }
                        if (arrayBindCard.length > 0) {
                            System.out.println("海康绑卡---成功，需要修改：{}条"+ arrayBindCard.length);
                            // 修改状态
                            if (type == 0) {
                                studentDao.updateHikvisionUserInfoPerson(arrayBindCard, null, 1);
                            } else {
                                teacherDao.updateHikvisionUserInfoPerson(arrayBindCard, null, 1);
                            }
                            // 修改之后分页重新查询
                            // j = 0;
                            System.out.println("海康绑卡---绑卡成功数据 {} 条"+ dataList.size());
                        }
                    } else {
                        log.error("海康绑卡---失败！{}", hikvisonCardBindPojo.toString());
                        return;
                    }
                } catch (Exception e) {
                    log.error("海康绑卡---修改绑卡失败！{}", e);
                }
            }
                   // 绑卡完成，修改数据库状态
        } catch (Exception e) {
            log.error("海康绑卡---绑卡失败！{}", e);
        }
    }


    /**
     * 海康自定义线程工厂
     */
    static class HikvisionTreadFactory implements ThreadFactory {

        // 原子类实现自定义线程编号
        private final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            // 重命名线程
            Thread t = new Thread(r, "hikvision-thread-" + atomicInteger.getAndIncrement());
            return t;
        }
    }


    /**
     * /**
     * 发送数据到图书馆接口
     *
     * @param postLibraryData
     * @param libraryCnteen   ip
     * @param libraryLogin    登录接口
     * @param libraryCreate   创建用户接口
     * @param tdi             单条数据
     * @param udi             学生详细信息
     * @param type            数据类型 0：老师 1：学生
     */
    @Override
    public void addLibraryData(final LibraryVo postLibraryData, final String libraryCnteen, final String libraryLogin,
                               final String libraryCreate, final TeacherDetailInfo tdi, final UserDetailInfo udi, final Integer type) {
        try {
            libraryThreadPoolExecutor.submit(new LibraryData(postLibraryData, libraryCnteen, libraryLogin, libraryCreate, tdi, udi, type));
        } catch (Exception e) {
            log.error("图书馆线程池异常: {}", e);
        }
    }


    /**
     * 图书馆线程池
     */
    class LibraryData implements Runnable {
        private LibraryVo postLibraryData;
        private String libraryCnteen;
        private String libraryLogin;
        private String libraryCreate;

        private Integer status;
        private TeacherDetailInfo tdi;
        private UserDetailInfo udi;
        private Integer type;

        public LibraryData(LibraryVo postLibraryData, String libraryCnteen, String libraryLogin, String libraryCreate, TeacherDetailInfo tdi, UserDetailInfo udi, Integer type) {
            this.postLibraryData = postLibraryData;
            this.libraryCnteen = libraryCnteen;
            this.libraryLogin = libraryLogin;
            this.libraryCreate = libraryCreate;
            this.tdi = tdi;
            this.udi = udi;
            this.type = type;
        }

        @Override
        public void run() {
            try {
                // 图书馆接口单条发送
                // 获取redis中保存的library:Authorization:token
                String token = stringRedisTemplate.opsForValue().get("library:Authorization:token");
                if (token == null || ("").equals(token)) {
                    token = getLibraryToken(libraryCnteen + libraryLogin);
                }
                // 创建用户和修改用户采用同一个接口 no carNo不同即可
                HttpRequestConfig postConfig = HttpRequestConfig.create().url(libraryCnteen + libraryCreate)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", token)
                        .httpEntityType(HttpEntityType.ENTITY_STRING);
                Object object = JSONObject.toJSON(postLibraryData);
                postConfig.json(object.toString());
                HttpRequestResult result = HttpClientUtils.post(postConfig);
                log.debug("图书馆---请求图书馆远程接口返回信息：{}", result);
                JSONObject jsonObject = JSONObject.parseObject(result.getResponseText());
                if (jsonObject == null) {
                    return;
                }
                String status = jsonObject.getString("status");
                String success = jsonObject.getString("success");
                if (status != null && success != null && status.equals("200") && success.equals("true")) {
                    Integer id = postLibraryData.getId();
                    if (id == null) {
                        return;
                    }
                    log.info("图书馆---发送用户信息--图书馆接口成功:{}", postLibraryData.getCardNo());
                    if (type == 1) {
                        // 修改数据库的状态
                        Student student = new Student();
                        student.setId(id);
                        student.setIsSendLibrary(1);
                        studentDao.update(student);
                    } else {
                        Teacher teacher = new Teacher();
                        teacher.setId(id);
                        teacher.setIsSendLibrary(1);
                        teacherDao.update(teacher);
                    }
                } else {
                    log.error("图书馆---发送用户信息--图书馆接口失败: {}, 卡号: {}", result.toString(), postLibraryData.getCardNo());
                }
            } catch (IOException e) {
                log.error("图书馆---请求图书馆远程接口失败：{}", e);
            }
        }
    }

    /**
     * 图书馆自定义线程工厂
     */
    static class LibraryNameTreadFactory implements ThreadFactory {

        // 原子类实现自定义线程编号
        private final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            // 重命名线程
            Thread t = new Thread(r, "library-thread-" + atomicInteger.getAndIncrement());
            return t;
        }
    }


    /**
     * 获取图书馆数据
     *
     * @param tdi
     * @param udi
     * @param type
     * @param libraryVo
     */
    public void getLiaryData(TeacherDetailInfo tdi, UserDetailInfo udi, Integer type, LibraryVo libraryVo) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String roleId = null;
        Date birth = null;
        if (type != null && type == 0) {
            // 老师数据
            libraryVo.setNo(String.valueOf(tdi.getUserId()));
            libraryVo.setName(tdi.getName());
            libraryVo.setIdentityNo(tdi.getCertificateNum());
            libraryVo.setSex(tdi.getSex());
            birth = tdi.getBirthDate();
            libraryVo.setEmail(tdi.getEmail());
            libraryVo.setPhone(tdi.getMobile());
            libraryVo.setAddr(tdi.getLiveAddress());
            libraryVo.setDescription(tdi.getRemark());
            roleId = tdi.getRole();
            libraryVo.setPhotoUrl(tdi.getPhotoUuid());
        } else if (type != null && type == 1) {
            // 学生数据
            libraryVo.setNo(String.valueOf(udi.getUserId()));
            libraryVo.setName(udi.getName());
            libraryVo.setIdentityNo(udi.getCertificateNum());
            libraryVo.setSex(udi.getSex());
            birth = udi.getBirthDate();
            libraryVo.setEmail(udi.getEmail());
            libraryVo.setPhone(udi.getMobile());
            libraryVo.setAddr(udi.getLiveAddress());
            libraryVo.setDescription(udi.getRemark());
            roleId = udi.getRole();
            libraryVo.setPhotoUrl(udi.getPhotoUuid());
        }
        libraryVo.setEnabled("true");
        // 根据id查询角色名称
        if (roleId != null && !("").equals(roleId)) {
            libraryVo.setRoleId(roleId);
            Role role = roleService.findRoleById(Integer.parseInt(roleId));
            libraryVo.setRoleName(role.getName());
        }
        // 生日信息
        String date = format.format(birth);
        libraryVo.setBirth(date);
    }

    /**
     * 获取图书馆token值
     * 登录接口获取token，有效期为15天，通过判断redis中是否有token进行请求,没有就获取保存到redis中设置过期时间
     *
     * @param url
     * @return
     */
    public String getLibraryToken(String url) {
        String tokenValue = null;
        // 调用登录接口获取token
        try {
            log.info("图书馆---图书馆token值为空，重新获取.");
            HttpRequestConfig config = HttpRequestConfig.create().url(url + "?name=admin&pass=123456")
                    .addHeader("Content-Type", "application/json");
            HttpRequestResult result = null;
            result = HttpClientUtils.get(config);
            JSONObject jsonObject = JSONObject.parseObject(result.getResponseText());
            JSONObject response = JSONObject.parseObject(jsonObject.getString("response"));

            String success = response.getString("success");
            String token = response.getString("token");

            if (success != null && ("true").equals(success) && token != null && !("").equals(token)) {
                tokenValue = "Bearer " + token;
                // 获取token 设置过期时间
                stringRedisTemplate.opsForValue().set("library:Authorization:token", tokenValue, 14, TimeUnit.DAYS);
            }
        } catch (IOException e) {
            log.error("图书馆---获取图书馆登录接口token失败：{}", e);
        }
        return tokenValue;
    }


    @Override
    public String postData(String url) {
        try {
            HttpRequestConfig config = HttpRequestConfig.create().url(url)
                    .addHeader("Content-Type", "application/json");
            HttpRequestResult httpRequestResult = HttpClientUtils.get(config);
            return canteenResult(httpRequestResult);
        } catch (Exception e) {
            log.error("请求接口失败,url: {}, 失败{}", url, e.toString());
            return null;
        }
    }

    /**
     * 解析食堂返回信息
     *
     * @param httpRequestResult
     */
    public String canteenResult(HttpRequestResult httpRequestResult) {
        try {
            if (httpRequestResult == null) {
                return null;
            }
            if (200 == httpRequestResult.getCode() && !("").equals(httpRequestResult.getResponseText()) && httpRequestResult.getResponseText() != null) {
                String responseText = httpRequestResult.getResponseText();
                JSONObject jsonObject = JSONObject.parseObject(responseText);
                String statusCode = jsonObject.getString("statusCode");
                String result = jsonObject.getString("result");
                if (statusCode != null && !("").equals(statusCode) && "200".equals(statusCode) && result != null && !("").equals(result) && "true".equals(result) && jsonObject.get("data") != null && !("").equals(jsonObject.get("data"))) {
                    Object data = jsonObject.get("data");
                    JSONArray jsonArray = (JSONArray) data;
                    if (jsonArray != null && jsonArray.size() > 0) {
                        Object emp_card = jsonArray.getJSONObject(0).get("emp_card");
                        return (String) emp_card;
                    }
                }
            } else {
                log.error("获取用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }


    @Override
    public String sendData(String url) {
        try {
            HttpRequestConfig config = HttpRequestConfig.create().url(url)
                    .addHeader("Content-Type", "application/json");
            HttpRequestResult httpRequestResult = HttpClientUtils.get(config);
            if (httpRequestResult != null) {
                return httpRequestResult.getResponseText();
            }
        } catch (Exception e) {
            log.error("请求接口失败,url: {}, 失败{}", url, e.toString());
            return null;
        }
        return null;
    }


    /**
     * 校车自定义线程工厂
     */
    static class SchoolBusNameTreadFactory implements ThreadFactory {

        // 原子类实现自定义线程编号
        private final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            // 重命名线程
            Thread t = new Thread(r, "schoolBus-thread-" + atomicInteger.getAndIncrement());
            return t;
        }
    }

    /**
     * 发送学生信息到校车系统 --改为只有昊吉顺车队
     *
     * @param url  昊吉顺车队
     * @param url2 国盛车队
     */
    @Override
    public void syncSendShoolBusStu(final String url, final String url2) {
        try {
            schoolBusExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
                    parentStudentCondition.setIsSendSchoolBusHjs(0);
                    log.info("校车系统--发送用户信息到校车地址：{}", url);
                    // 获取需要发送到校车系统的用户数据 一条数据需要发送到两个校车系统
                    // 分页
                    int i = 1;
                    for (int i1 = 1; i1 <= i; i1++) {
                        // 每次最多批量发送500条
                        Page page = new Page();
                        page.setCurrentPage(i1);
                        page.setPageSize(500);
                        // 查询：添加到需要发送到校车的学生
                        List<ParentStudentBus> studentBusList = parentProxyService.findParentSutBusByGroupCondition(parentStudentCondition, page);
                        if (CollectionUtils.isEmpty(studentBusList)) {
                            log.error("校车系统--未获取到需要发送的学生信息到校车");
                            return;
                        }
                        log.info("校车系统--发送到校车: {} ，本次发送{} 条", url, studentBusList.size());
                        int totalPages = page.getTotalPages();
                        if (totalPages != 0) {
                            i = totalPages;
                        }
                        HttpRequestConfig config = HttpRequestConfig.create().addHeader("Content-Type", "application/json")
                                .httpEntityType(HttpEntityType.ENTITY_STRING)
                                .url(url);
                        try {
                            config.json(JSONObject.toJSON(studentBusList).toString());
                            HttpRequestResult httpRequestResult = HttpClientUtils.post(config);
                            log.debug("校车系统--修改校车返回信息：{}", httpRequestResult.toString());
                            if (httpRequestResult != null && 200 == httpRequestResult.getCode() && !("").equals(httpRequestResult.getResponseText()) && httpRequestResult.getResponseText() != null) {
                                String responseText = httpRequestResult.getResponseText();
                                JSONObject jsonObject = JSONObject.parseObject(responseText);
                                String statusCode = jsonObject.getString("code");
                                String stuId = jsonObject.getString("stuId");
                                if (statusCode != null && "200".equals(statusCode) && stuId != null && !"".equals(stuId)) {
                                    // 修改完成发送的学生状态
                                    updateShoolBusStu(0, stuId.split(","));
                                    // 修改完成后重新分页重新
                                    i1 = 0;
                                } else {
                                    log.error("校车系统--校车--发送数据失败: {}", statusCode);
                                }
                            }
                        } catch (IOException e) {
                            log.error("校车系统--校车--发送数据失败: {}", e);
                        }
                    }
                }
            });
        } catch (Exception e) {
            log.error("校车系统--添加学生校车线程池异常: {}", e);
        }
    }

    /**
     * 修改学生卡号到对应的校车车队 卡号--改为只有昊吉顺车队
     *
     * @param url  昊吉顺车队
     * @param url2 国盛车队
     */
    @Override
    public void modifyCardSchoolBus(final String url, final String url2) {
        try {
            schoolBusModifyCardExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    // 昊吉顺车队
                    ModifyShoolBusCardStu shoolBusCardStu = new ModifyShoolBusCardStu();
                    shoolBusCardStu.setIsSendSchoolBusHjs(2);
                    // 查询需要修改卡号的学生发送到校车方
                    log.info("校车修改卡号--发送修改卡号到校车地址：{}", url);
                    List<ModifyShoolBusCardStu> list = studentDao.findModifySchoolBusCardStus(shoolBusCardStu);
                    if (CollectionUtils.isEmpty(list)) {
                        log.error("校车修改卡号--未获取到需要修改的卡号的学生信息到校车");
                        return;
                    }
                    for (ModifyShoolBusCardStu pojo : list) {
                        try {
                            HttpRequestConfig config = HttpRequestConfig.create().addHeader("Content-Type", "application/json")
                                    .httpEntityType(HttpEntityType.ENTITY_STRING)
                                    .url(url);
                            config.json(JSONObject.toJSON(pojo).toString());
                            HttpRequestResult httpRequestResult = HttpClientUtils.post(config);
                            log.debug("校车修改卡号--修改校车卡号返回信息：{}", httpRequestResult.toString());
                            if (httpRequestResult != null && 200 == httpRequestResult.getCode() && !("").equals(httpRequestResult.getResponseText()) && httpRequestResult.getResponseText() != null) {
                                String responseText = httpRequestResult.getResponseText();
                                JSONObject jsonObject = JSONObject.parseObject(responseText);
                                String statusCode = jsonObject.getString("code");
                                if (statusCode != null && "200".equals(statusCode)) {
                                    // 修改状态
                                    String[] split = {String.valueOf(pojo.getId())};
                                    updateShoolBusStu(0, split);
                                    log.info("校车修改卡号--校车--发送修改卡号数据成功: {}", pojo.getEmpCard());
                                } else {
                                    log.error("校车修改卡号--校车--发送修改卡号数据失败: {}"+pojo.getEmpCard()+"学生id"+pojo.getId(), jsonObject);
                                }
                            }
                        } catch (IOException e) {
                            log.error("校车修改卡号--校车--发送修改卡号数据失败: {}", e);
                        }
                    }
                }
            });
        } catch (Exception e) {
            log.error("校车修改卡号--修改校车线程池异常: {}", e);
        }
    }

    /**
     * 发送完成修改信息
     *
     * @param type
     * @param split
     */
    public void updateShoolBusStu(Integer type, String[] split) {
        SchoolBusMangerVo schoolBusMangerVo = new SchoolBusMangerVo();
        schoolBusMangerVo.setModifyDate(new Date());
        if (type == 0) {
            schoolBusMangerVo.setIsSendSchoolBusHjs(1);
        }
//        else if (type == 1) {
//            schoolBusMangerVo.setIsSendSchoolBusGs(1);
//        }
        schoolBusDao.updateStuBusManger(schoolBusMangerVo, split);
    }


    /**
     * 发送班级组织信息到海康
     *
     * @param artemisConfig
     * @param hikvisionResourceTeamUrl
     * @param schoolIds
     */
    @Override
    public void addTeamHik(ArtemisConfig artemisConfig, String hikvisionResourceTeamUrl, Integer schoolIds,String schoolYear) {
        List<HikTeamRequestVo> hikTeamVoList = teamDao.findNotSendHik(schoolIds,schoolYear);
        System.out.println("海康组织--发送组织信息到海康：{}条！"+ hikTeamVoList.size());
        if (CollectionUtils.isEmpty(hikTeamVoList)) {
            return;
        }
        String json = JSONObject.toJSONString(hikTeamVoList);
        String resultData = HikvisionClient.hikvisionPost(artemisConfig, hikvisionResourceTeamUrl, json);
        // 添加班级成功，修改状态
        if (StringUtils.isEmpty(resultData)) {
            return;
        }
        HikTeamResponseVo responseVo = JSON.parseObject(resultData, new TypeReference<HikTeamResponseVo>() {
        });

        log.info("添海康加组织响应：" + resultData);

        if (responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")) {
            List<HikTeamResponseVo.DataBean.SuccessesBean> successesBeanList = responseVo.getData().getSuccesses();
            if (CollectionUtils.isEmpty(successesBeanList)) {
                return;
            }
            Team team = new Team();
            for (HikTeamResponseVo.DataBean.SuccessesBean bean : successesBeanList) {
                Integer clientId = bean.getClientId();
                if (clientId != null) {
                    team.setId(clientId);
                    team.setIsSendHikdoor(1);
                    teamDao.update(team);
                }
            }
        } else {
            log.error("海康组织--海康添加组织失败！{}", responseVo.getMsg());
        }
    }

    @Override
    public String addAndUpdateHkPersonById(ArtemisConfig artemisConfig, Integer id, boolean isStu) {
        List<Map<String, Object>> userList = null;
        if (isStu) {
            userList = basicSQLService.find("SELECT ps.NAME 'personName', ps.user_id 'clientId', ps.user_name 'certificateNo', pt.CODE orgIndexCode FROM pj_student ps inner JOIN pj_team pt ON pt.id = ps.team_id AND pt.is_delete = 0 WHERE ps.is_delete = 0 AND ps.id =" + id);
        } else {
            userList = basicSQLService.find("select name 'personName', concat(user_id, 't') as'personId' , user_name 'certificateNo','d216add0-c940-482e-b8fb-a7207a75d7e7' orgIndexCode from pj_teacher where is_delete = 0  and id = " + id);
        }

        if (userList.size() == 0) {
            return "查询不到用户信息，请确认用户是否存在";
        }
        //HikvisionResponseVo response = JSON.parseObject(HikvisionClient.hikvisionPost(artemisConfig, "/api/resource/v1/person/batch/add", requestBody), HikvisionResponse.class);
        String json = JSONObject.toJSONString(userList);
        String resultData = HikvisionClient.hikvisionPost(artemisConfig, "/api/resource/v1/person/batch/add", json);
        // 添加用户成功，修改状态
        if (StringUtils.isEmpty(resultData)) {
            return "失败";
        }
        HikvisionResponseVo responseVo = JSON.parseObject(resultData, new TypeReference<HikvisionResponseVo>() {
        });
        if (responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")) {
            // 获取success数据
            List<HikvisionResponseVo.Data.Successes> successesList = responseVo.getData().getSuccesses();
            log.info("海康添加--新增成功用户为：{}条", !CollectionUtils.isEmpty(successesList) ? successesList.size() : 0);
            if (CollectionUtils.isEmpty(successesList)) {
                log.info("海康添加--新增用户返回信息为===={}", responseVo.toString());
                return "失败2";
            }
            Integer[] array = new Integer[successesList.size()];
            /* log.info("userid"+successesList.get(1).getPersonId());*/
            String[] arrs = new String[successesList.size()];
            for (int b = 0; b < successesList.size(); b++) {
                HikvisionResponseVo.Data.Successes successesVO = successesList.get(b);
                Integer clientid = successesVO.getClientId();
                String personId = successesVO.getPersonId();
                if (isStu) {
                    log.info("ClientId" + successesList.get(b).getPersonId());
                    arrs[b] = personId;
                    array[b] = clientid;
                } else {
                    // 截取t
                    array[b] = clientid;
                    arrs[b] = personId;
                }
            }
            if (array.length > 0) {
                log.info("海康添加--添加用户成功，需要修改：{}条", array.length);
                if (isStu) {
                    log.info("修改");
                    for (int ss = 0; ss < array.length; ss++) {
                        studentDao.updateHikvisionUserInfoPersonAdd(array[ss], 1, 0, arrs[ss]);
                    }
                } else {
                    for (int ss = 0; ss < array.length; ss++) {
                        teacherDao.updateHikvisionUserInfoPersonAdd(array[ss], 1, 2, arrs[ss]);
                    }
                }

            }
        }
        return null;
    }


    @Override
    public String unbindHkCar(ArtemisConfig artemisConfig, String personId, String carNo) {
        UntieCardList car = new UntieCardList();
        car.setPersonId(personId);
        car.setCardNumber(carNo);
        String body = JSON.toJSONString(car);

        HikvisionResponse response = JSON.parseObject(HikvisionClient.hikvisionPost(artemisConfig, "/api/cis/v1/card/deletion", body), HikvisionResponse.class);
        if (!response.getCode().equals("0")) {
            log.error("解绑请求体：" + body);
            log.error("解绑响应体：" + JSON.toJSONString(response));
            return response.getMsg();
        }
        return null;
    }

    @Override
    public String bindHkCar(ArtemisConfig artemisConfig, String personId, String carId) {
        HikvisionBindCarPojo hikvisionBindCarPojo = new HikvisionBindCarPojo();
        hikvisionBindCarPojo.setEndDate("2037-12-30");
        hikvisionBindCarPojo.setStartDate(DateUtil.dateToStr(new Date()));
        CardList car = new CardList();
        car.setCardNo(carId);
        car.setPersonId(personId);
        List<CardList> carList = new ArrayList<>(1);
        carList.add(car);
        hikvisionBindCarPojo.setCardList(carList);
        String body = JSON.toJSONString(hikvisionBindCarPojo);
        HikvisionResponse response = JSON.parseObject(HikvisionClient.hikvisionPost(artemisConfig, "/api/cis/v1/card/bindings", body), HikvisionResponse.class);
        //if(!response.getCode().equals("0")){
        log.info("海康绑卡请求体：" + body);
        log.info("海康绑卡响应体：" + JSON.toJSONString(response));
        //return response.getMsg();
        //}
        return null;
    }

    @Override
    public HikvisionResponse queryCarInfoByCarNo(ArtemisConfig artemisConfig, String carNo) {
        String body = "{\"cardNo\":\"" + carNo + "\"}";
        HikvisionResponse response = JSON.parseObject(HikvisionClient.hikvisionPost(artemisConfig, "/api/irds/v1/card/cardInfo", body), HikvisionResponse.class);
        if (!response.getCode().equals("0")) {
            log.error("查询卡号信息绑卡请求体：" + body);
            log.error("查询卡号信息响应体：" + JSON.toJSONString(response));

        }
        return response;
    }

    @Override
    public HikvisionResponse queryPersonInfoById(ArtemisConfig artemisConfig, String personId) {
        String body = "{\"personId\":\"" + personId + "\"}";
        HikvisionResponse response = JSON.parseObject(HikvisionClient.hikvisionPost(artemisConfig, "/api/resource/v1/person/personId/personInfo", body), HikvisionResponse.class);
        if (!response.getCode().equals("0")) {
            log.error("查询用户信息请求体：" + body);
            log.error("查询用户信息绑卡响应体：" + JSON.toJSONString(response));
        }
        return response;

    }


    @Override
    public String addUserToHk(boolean isStu, String empCode, String card, ArtemisConfig artemisConfig) {
        try {
            //海康添加
            List<Map<String, Object>> userList = null;
            if (isStu) {
                //orgIndexCode在海康系统不存在所以添加学生失败,所以这里应该先查询，并判断是否需要添加组织
                //根据学生班级名称，去海康系统查询出对应的组织的orgIndexCode（应该查询这个“默认分组》文昌小学》学生”组织下的子级，怎么查得看海康api文档），然后设置为学生的orgIndexCode（同一个parent下组织名不可重复，现在海康门禁已经又1-6年级所有班级了，只能根据班级名字判断是否要添加组织）
                //如果根据班级名字查询不到组织，这时才应该添加，添加成功后再设置学生的orgIndexCode
                //cc6924c5-97f4-4862-a4b0-027f8cc5a141
                userList = basicSQLService.find("SELECT ps.team_id, ps.NAME 'personName',ps.team_name 'orgIndexCode', ps.user_id 'personId', ps.user_name 'certificateNo', pt.CODE orgIndexCode FROM pj_student ps inner JOIN pj_team pt ON pt.id = ps.team_id AND pt.is_delete = 0 WHERE ps.is_delete = 0 AND ps.emp_code ='" + empCode + "'");
                String orgName = (String) userList.get(0).get("orgIndexCode");
                String orgIndex = queryOrgIndexByName(orgName,artemisConfig);
                if (orgIndex == null) {
                    //添加组织
                    Map map=addOrg(userList.get(0).get("team_id").toString(),artemisConfig);
                    if((int)map.get("code")!=1){
                        log.error((String)map.get("message"));
                        return (String)map.get("message");
                    }
                    orgIndex=(String) map.get("orgIndexCode");
                }
                System.out.println("orgIndex"+orgIndex);
                userList.get(0).remove("team_id");
                userList.get(0).put("orgIndexCode", orgIndex);
            } else {
                userList = basicSQLService.find("select name 'personName', concat(user_id, 't') as'personId' , user_name 'certificateNo','d216add0-c940-482e-b8fb-a7207a75d7e7' orgIndexCode from pj_teacher where is_delete = 0  and emp_code ='" + empCode + "' ");
            }
            if (userList.size() == 0) {
                return "查询不到用户信息，请确认用户是否存在";
            }
            String requestBody = JSON.toJSONString(userList);

            String json = JSONObject.toJSONString(userList);
            String resultData = HikvisionClient.hikvisionPost(artemisConfig, "/api/resource/v1/person/batch/add", json);
            System.out.println("海康返回数据"+resultData);
            // 添加用户成功，修改状态
            if (StringUtils.isEmpty(resultData)) {
                return "";
            }
            //添加用户到海康
            //HikvisionResponse response = JSON.parseObject(HikvisionClient.hikvisionPost(artemisConfig, "/api/resource/v1/person/batch/add", requestBody), HikvisionResponse.class);
            log.info("海康添加用户请求体：" + requestBody);
            //log.info("海康添加用户响应体：" + JSON.toJSONString(response));
            HikvisionResponseVo responseVo = JSON.parseObject(resultData, new TypeReference<HikvisionResponseVo>() {
            });
            String personId=null;
            if (responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")) {
                // 获取success数据
                List<HikvisionResponseVo.Data.Successes> successesList = responseVo.getData().getSuccesses();
                log.info("海康添加--新增成功用户为：{}条", !CollectionUtils.isEmpty(successesList) ? successesList.size() : 0);
                if (CollectionUtils.isEmpty(successesList)) {
                    System.out.println("海康添加--新增用户返回信息为===={}"+ responseVo.toString());
                    return "";
                }
                HikvisionResponseVo.Data.Successes successesVO = successesList.get(0);
                Integer clientid = successesVO.getClientId();
                 personId=successesVO.getPersonId();
                if (isStu) {
                    basicSQLService.update("update pj_student set persionId="+personId+" ,is_send_hikdoor=1 where emp_code='" + empCode + "'");
                } else {
                    basicSQLService.update("update pj_teacher set is_send_hikdoor=1 where emp_code='" + empCode + "'");
                }
            }
            return bindHkCar(artemisConfig, personId, card);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    private String queryOrgIndexByName(String orgName,ArtemisConfig artemisConfig) {

        Map<String, Object> requestParam = new HashMap<String, Object>();
        requestParam.put("orgName", orgName);
        requestParam.put("pageNo", 1);
        requestParam.put("pageSize", 500);
        //cc6924c5-97f4-4862-a4b0-027f8cc5a141
        Map<String,Object> responses = JSON.parseObject(HikvisionClient.hikvisionPost(artemisConfig, "/api/resource/v1/org/advance/orgList", JSON.toJSONString(requestParam)), Map.class);
        log.info("查询组织响应体：" + responses);
        Map<String,Object> data=(Map<String, Object>) responses.get("data");
        if (data != null) {
            List<Map<String,Object>> list=(List<Map<String,Object>>)data.get("list");
            //todo 判断组织父级为cc6924c5-97f4-4862-a4b0-027f8cc5a141在获取orgIndexCode
            for(Map a:list){
                if(a.get("parentOrgIndexCode").equals("cc6924c5-97f4-4862-a4b0-027f8cc5a141")){
                    return (String)a.get("orgIndexCode");//list.get(0).get("orgIndexCode");
                }
            }
        }
        return null;
    }

    //返回直应该设至状态，明确是哪里返回的
    private Map addOrg(String teamId,ArtemisConfig artemisConfig){
        Map map=new HashMap<>();
        List<Map<String,Object>> list=basicSQLService.find(" SELECT id 'clientId', code 'orgIndexCode', name as 'orgName', 'cc6924c5-97f4-4862-a4b0-027f8cc5a141' as parentIndexCode FROM `pj_team` where id="+teamId);
        String json = JSONObject.toJSONString(list);
        String resultData = HikvisionClient.hikvisionPost(artemisConfig, "/api/resource/v1/org/batch/add", json);
        log.info("添加组织海康请求体："+json);
        log.info("添加组织海康响应体："+resultData);
        // 添加用户成功，修改状态
        if (StringUtils.isEmpty(resultData)) {
            map.put("code",0);
            map.put("message","添加组织失败");
            return map;
        }
        HikTeamResponseVo responseVo = JSON.parseObject(resultData, new TypeReference<HikTeamResponseVo>() {});
        if(responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")){
            basicSQLService.update("update pj_team set is_send_hikdoor=1 where id="+teamId);
            map.put("code",1);
            map.put("orgIndexCode",(String) list.get(0).get("orgIndexCode"));
            return map;
        }
        map.put("code",0);
        map.put("message","修改状态失败");
        return map;
    }

}