package platform.education.generalTeachingAffair.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import platform.education.generalTeachingAffair.dao.CanteenCardServiceDao;
import platform.education.generalTeachingAffair.dao.SchoolBusDao;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.event.UpdateCardEvent;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.CanteenCardService;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpEntityType;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;
import platform.education.generalTeachingAffair.vo.EmployeeList;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 补卡管理
 *
 * @author: yhc
 * @Date: 2020/12/3 17:21
 * @Description:
 */
public class CanteenCardServiceImpl implements CanteenCardService {
    private Logger log = LoggerFactory.getLogger(getClass());


    private CanteenCardServiceDao canteenCardServiceDao;

    private StudentDao studentDao;

    private TeacherDao teacherDao;

    private SchoolBusDao schoolBusDao;

    Logger logger= LoggerFactory.getLogger(CanteenCardServiceImpl.class);

    @Autowired
    private HttpService httpService;
    @Autowired
    BasicSQLService basicSQLService;

    public void setSchoolBusDao(SchoolBusDao schoolBusDao) {
        this.schoolBusDao = schoolBusDao;
    }

    @Autowired
    private ApplicationContext applicationContext;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setCanteenCardServiceDao(CanteenCardServiceDao canteenCardServiceDao) {
        this.canteenCardServiceDao = canteenCardServiceDao;
    }


    @Override
    public List<CanteenCardPojo> findCanteenByCondition(CanteenCardPojo condition, Page page, Order order) {
        return this.canteenCardServiceDao.findCanteenByCondition(condition, page, order);
    }

    /**
     * 补卡管理 & 发布修改卡号事件
     *
     * @param canteen
     * @return
     */
    @Override
    public String modify(CanteenCardPojo canteen, ArtemisConfig artemisConfig,String shitangUrl) {
        if (canteen == null) {
            return null;
        }

        if(canteen.getUserType()==0){
            if(basicSQLService.findUniqueLong("select exists(select 1 from pj_student where is_delete=0 and emp_card='"+canteen.getNewIcNumber()+"')")>0){
                return "补卡卡号重复，请输入其他卡号";
            }
        }else{
            if(basicSQLService.findUniqueLong("select exists(select 1 from pj_teacher where is_delete=0 and emp_card='"+canteen.getNewIcNumber()+"')")>0){
                return "补卡卡号重复，请输入其他卡号";
            }
        }
        //修改食堂
        String shitang= sendCarNoToST(canteen,shitangUrl);
        if(shitang!=null){
            return shitang;
        }
        String hkResult=sendCarNoToHk(canteen,artemisConfig);
        if(hkResult!=null){
            //海康维护暂时不用，2021，12，23
            return hkResult;
        }

        Date modify = new Date();
        canteen.setIsSend(1);
        canteen.setModifyDate(modify);
        CanteenCardPojo canteenCardPojo = this.canteenCardServiceDao.update(canteen);

        if (canteenCardPojo != null && canteen.getUserType() != null) {
            Integer userType = canteen.getUserType();
            Integer userId = canteen.getUserId();
            // 校车 海康，都是只有当用户信息发送完成后才能修改卡号，图书馆不用（新增用户和修改卡号都是同一个接口）
            // 学生
            if (userType == 0) {
                Student student = new Student();
                student.setId(userId);
                student.setEmpCard(canteenCardPojo.getNewIcNumber());
                student.setModifyDate(modify);
                // 修改图书馆发送状态为0 作为修改用户使用 // 创建用户和修改用户采用同一个接口 no carNo不同即可 所以直接修改为0
                student.setIsSendLibrary(0);
                // 海康绑卡状态 1：已绑卡，0：没有 ， 2：需要解绑卡号    由于海康没有提供修改卡号功能，所以需要分为两个步骤，1.先解绑原来的卡号2.绑定新卡号     & 需要修改，新增的用户状态为0
                //student.setI-sHikvisionBindCard(2);
                studentDao.update(student);
                // 修改校车学生绑定关系表 状态为2 pj_school_bus_manger is_send_school_bus 单条修改
                String[] split = {String.valueOf(userId)};
                // 修改完成发送的学生状态
                SchoolBusMangerVo schoolBusMangerVo = new SchoolBusMangerVo();
                schoolBusMangerVo.setModifyDate(new Date());
                // 学生信息是否发送到对应的校车，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)，2：修改卡号的学生，需要将新卡号发送到校车
                schoolBusMangerVo.setIsSendSchoolBusGs(2);
                schoolBusDao.updateListenerStuBusManger(schoolBusMangerVo, split);

                schoolBusMangerVo.setIsSendSchoolBusGs(null);
                schoolBusMangerVo.setIsSendSchoolBusHjs(2);
                schoolBusDao.updateListenerStuBusManger(schoolBusMangerVo, split);

            } else if (userType == 1) {
                Teacher teacher = new Teacher();
                teacher.setId(userId);
                teacher.setModifyDate(modify);
                teacher.setEmpCard(canteenCardPojo.getNewIcNumber());
                // // 创建用户和修改用户采用同一个接口 no carNo不同即可
                teacher.setIsSendLibrary(0);
                // 海康绑卡状态 1：已绑卡，0：没有 ， 2：需要解绑卡号    由于海康没有提供修改卡号功能，所以需要分为两个步骤，1.先解绑原来的卡号2.绑定新卡号     & 需要修改，新增的用户状态为0
                //teacher.setIsHikvisionBindCard(2);
                teacherDao.update(teacher);
            }
            // 修改校车打卡信息
            this.canteenCardServiceDao.updateStudentSign(canteen.getNewIcNumber(), canteen.getOldIcNumber());
            UpdateCardEvent event = new UpdateCardEvent(this, userId, userType);
            // 发布事件
            applicationContext.publishEvent(event);
        }

        return "success";

    }

    private String sendCarNoToST(CanteenCardPojo canteen,String shitangUrl){
        if(canteen.getUserType()==1){
            //老师补卡
            EmployeeList employeeList=new EmployeeList();
            Teacher teacher=teacherDao.findById(canteen.getUserId());
            employeeList.setEmp_pycode(String.valueOf(teacher.getUserId()));
            employeeList.setEmp_card(canteen.getNewIcNumber());
            //employeeList.setEmp_card(canteen.getNewIcNumber());
            //employeeList.setEmp_idcard(student.getUserName());
            Object object = JSONObject.toJSON(employeeList);
            JSONObject param = new JSONObject();
            param.put("sign_name","kksss");
            //param.put("tran_code","emp_add");
            param.put("tran_code","emp_update_card");
            param.put("employeeList", object);
            //canteenThreadPoolExecutor.submit
            HttpRequestResult httpRequestResult=null;
            HttpRequestConfig config = HttpRequestConfig.create().url(shitangUrl)
                    .addHeader("content-type", "application/json")
                    .httpEntityType(HttpEntityType.ENTITY_STRING);
            //config.json(shiTangDate.toString());
            config.json(param.toString());
            try {
                httpRequestResult = HttpClientUtils.post(config);
                //判断食堂修改接口返回信息
                log.info("httpRequestResult"+httpRequestResult);
                if (httpRequestResult == null) {
                    return "食堂无返回信息";
                }
                if (200 == httpRequestResult.getCode()) {
                    String responseText = httpRequestResult.getResponseText();
                    log.info("食堂返回信息"+responseText);
                    if (("").equals(responseText) || responseText == null) {
                        return "食堂返回信息为空";
                    }
                    JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                    String statusCode = jsonObject2.getString("statusCode");
                    String result2 = jsonObject2.getString("result");
                    String error=jsonObject2.getString("error");
                    String  date=jsonObject2.getString("data");
                    if (("").equals(statusCode) || statusCode == null && ("").equals(result2) || result2 == null) {
                        return "食堂接口调用有误";
                    }
                    if ("200".equals(statusCode) && "true".equals(result2)) {
                        if("".equals(error) || error==null){
                            if(date.equals("success")){
                                log.info("修改食堂成功"+date);
                                return null;
                            }else{
                                return "食堂修改失败";
                            }
                            /*食堂返回信息
                             * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"测试11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
                             */
                        }
                    }else{
                        return error;
                    }
                }else {
                    log.error("食堂接口--添加用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
                }
                log.info("食堂接口--食堂添加接口添加状态{}"," 返回信息: {}----"+httpRequestResult);
            }catch (IOException e) {
                log.info("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}"+ e);
            }
        }else{
            //学生补卡
            //2021,11,16食堂补卡接口作出修改，补卡由我们系统进行补卡，并修改食堂卡号
            EmployeeList employeeList=new EmployeeList();
            Student student=studentDao.findById(canteen.getUserId());
            employeeList.setEmp_pycode(String.valueOf(student.getUserId()));
            employeeList.setEmp_card(canteen.getNewIcNumber());
            //employeeList.setEmp_card(canteen.getNewIcNumber());
            //employeeList.setEmp_idcard(student.getUserName());
            Object object = JSONObject.toJSON(employeeList);
            JSONObject param = new JSONObject();
            param.put("sign_name","kksss");
            //param.put("tran_code","emp_add");
            param.put("tran_code","emp_update_card");
            param.put("employeeList", object);
            //canteenThreadPoolExecutor.submit
            HttpRequestResult httpRequestResult=null;
            HttpRequestConfig config = HttpRequestConfig.create().url(shitangUrl)
                    .addHeader("content-type", "application/json")
                    .httpEntityType(HttpEntityType.ENTITY_STRING);
            //config.json(shiTangDate.toString());
            config.json(param.toString());
            try {
                httpRequestResult = HttpClientUtils.post(config);
                //判断食堂修改接口返回信息
                log.info("httpRequestResult"+httpRequestResult);
                if (httpRequestResult == null) {
                    return "食堂无返回信息";
                }
                if (200 == httpRequestResult.getCode()) {
                    String responseText = httpRequestResult.getResponseText();
                    log.info("食堂返回信息"+responseText);
                    if (("").equals(responseText) || responseText == null) {
                        return "食堂返回信息为空";
                    }
                    JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                    String statusCode = jsonObject2.getString("statusCode");
                    String result2 = jsonObject2.getString("result");
                    String error=jsonObject2.getString("error");
                    String  date=jsonObject2.getString("data");
                    if (("").equals(statusCode) || statusCode == null && ("").equals(result2) || result2 == null) {
                        return "食堂接口调用有误";
                    }
                    if ("200".equals(statusCode) && "true".equals(result2)) {
                        if("".equals(error) || error==null){
                            if(date.equals("success")){
                                log.info("修改食堂成功"+date);
                                return null;
                            }else{
                                return "食堂修改失败";
                            }
                            /*食堂返回信息
                             * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"测试11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
                             */
                        }
                    }else{
                        return error;
                    }
                }else {
                    log.error("食堂接口--添加用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
                }
                log.info("食堂接口--食堂添加接口添加状态{}"," 返回信息: {}----"+httpRequestResult);
            }catch (IOException e) {
                log.info("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}"+ e);
            }
        }

            return null;
    }


    private String sendCarNoToHk(CanteenCardPojo canteen, ArtemisConfig artemisConfig) {
        try {
            String personId=getPersonId(canteen.getUserId(),canteen.getUserType());
            logger.info("查询海康用户信息,id:"+personId);
            //根据personId查询海康用户信息
            HikvisionResponse userInfoRes= httpService.queryPersonInfoById(artemisConfig,personId);
            if(!userInfoRes.getCode().equals("0")){
                return "查询海康用户信息失败："+userInfoRes.getMsg();
            }
            JSONObject userInfo=(JSONObject)userInfoRes.getData();
            boolean needUnbind=true;
            //海康系统没有该用户
            if(userInfo==null){
                logger.info("添加用户到海康");
                //添加用户
                String addRes=httpService.addAndUpdateHkPersonById(artemisConfig,canteen.getUserId(),canteen.getUserType()==0);
                if(addRes!=null){
                    return "添加用户到海康失败："+addRes;
                }
                needUnbind=false;
            }
            logger.info("查询海康补卡卡号信息");
            HikvisionResponse carInfoRes=httpService.queryCarInfoByCarNo(artemisConfig,canteen.getNewIcNumber());
            if(!carInfoRes.getCode().equals("0")){
                return "查询海康补卡卡号信息失败："+carInfoRes.getMsg();
            }
            JSONObject r=(JSONObject)carInfoRes.getData();
            if(r!=null && r.get("personId")!=null) {
                String hkPersonId = r.get("personId").toString();
                String hkPersonName = r.get("personName").toString();
                if (!hkPersonId.equals(personId)) {
                    return "要补的卡号【" + canteen.getNewIcNumber() + "】在海康系统中已被【" + hkPersonName + "】绑定，请在海康系统修改其卡号，或则修改补卡卡号，或则在海康系统中将其删除";
                }
                return null;
            }
            if(needUnbind){
                logger.info("海康卡号解绑");
                String s=httpService.unbindHkCar(artemisConfig,personId,canteen.getOldIcNumber());
                if(s!=null){
                    //return "解绑旧卡号【"+canteen.getOldIcNumber()+"】失败："+s;
                }
            }
            //绑卡
            String s=httpService.bindHkCar(artemisConfig,personId,canteen.getNewIcNumber());
            if(s!=null){
                return "海康绑卡失败："+s;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "发送卡号到海康失败";
    }

    private String getPersonId(Integer id,Integer type){
        if(type==0){
            return basicSQLService.findUnique("select persionId from pj_student where id="+id).toString();
        }else{
            return basicSQLService.findUnique("select user_id from pj_teacher where id="+id).toString()+"t";
        }
    }


}
