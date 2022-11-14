package platform.szxyzxx.web.hikservionDoor.controller;

import cn.hutool.core.util.StrUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.service.LibraryService;
import platform.szxyzxx.schoolbus.service.BusThirdPartyApiService;
import platform.szxyzxx.schoolbus.service.CarIdentifyService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.util.List;

/**
 * 海康连接客户端处理
 *
 * @author: yhc
 * @create: 2020-11-11 10:42
 */
@RestController
@RequestMapping("/hikvison")
public class HikvisonController {
    Logger log = LoggerFactory.getLogger(HikvisonController.class);
    /**
     * 闸机接口
     */
    private static String fileName;
    private static String hikvisionHost;
    private static String hikvisionAppKey;
    private static String hikvisionAppSecret;
    private static String hikvisionAddPersonUrl;
    private static String hikvisionBindCarUrl;
    private static String untieCarUrl;
    private static String hikvisionResourceTeamUrl;

    /**
     * 图书馆
     */
    private static String libraryACnteen;
    private static String libraryLogin;
    private static String libraryCreate;


    /**
     * 校车
     */
    private static Integer isSend;
    private static String url;
    private static String url2;

    private static String schoolBusModifyCard;
    private static String schoolBusModifyCard2;


    private static Integer schoolIds;

    static {
        fileName = "System.properties";
        //闸机
        hikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
        hikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
        hikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
        hikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");
        hikvisionBindCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.bindings");
        untieCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.deletion");
        hikvisionResourceTeamUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.resource");
        schoolIds = Integer.valueOf(PropertiesUtil.getProperty(fileName, "schoolId"));

        // 图书馆
        libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
        libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
        libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");


        // 是否发送到校车车队 是否开启推送学生信息到校车1:是 0： 否 本地测试不要开启
        isSend = Integer.valueOf(PropertiesUtil.getProperty(fileName, "schoolBus.send"));
        // 昊吉顺车队
        url = PropertiesUtil.getProperty(fileName, "schoolBus.send.url");
        // 国盛车队
        url2 = PropertiesUtil.getProperty(fileName, "schoolBus.send.url2");

        // 修改卡号地址
        schoolBusModifyCard = PropertiesUtil.getProperty(fileName, "schoolBus.send.modifyCard");
        schoolBusModifyCard2 = PropertiesUtil.getProperty(fileName, "schoolBus.send.modifyCard2");
    }

    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;

    @Autowired
    @Qualifier("busThirdPartyApiServiceImpl")
    private BusThirdPartyApiService busThirdPartyApiService;

    @Autowired
    @Qualifier("libraryService")
    private LibraryService libraryService;
    @Autowired
    private CarIdentifyService carIdentifyService;

    @Autowired
    private BasicSQLService basicSQLService;
    /**
     * 添加用户信息到海康门禁
     *
     * @param type 0：学生 1：教师
     * @return
     */
    @RequestMapping("/hikvisonTeacher")
    public String teacherHikservion(Integer type,@CurrentUser UserInfo userInfo) {
        System.out.println("type"+type);
        if(type==0){
            basicSQLService.update("update pj_student set is_send_hikdoor=0,is_hikvision_bind_card=0 where is_delete=0 and study_state='01'");
        }else{

        }

        if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey) && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
            ArtemisConfig artemisConfig = new ArtemisConfig();
            artemisConfig.setHost(hikvisionHost);
            artemisConfig.setAppKey(hikvisionAppKey);
            artemisConfig.setAppSecret(hikvisionAppSecret);
            httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, type,userInfo.getSchoolYear());
        } else {
            log.error("调用海康远程接口失败，请检查配置接口信息是否正确！");
        }
        return "ok";
    }


    @RequestMapping("/hikDoor")
    public String door(String userId, String startTime, String endTime) {
        return busThirdPartyApiService.getStudentGatePickInfo(userId, startTime, endTime).toString();
    }


    /**
     * 发送班级到海康组织
     *
     * @return
     */
    @RequestMapping("/hikTeam")
    public String hikTeam(@CurrentUser UserInfo userInfo) {
        if (StrUtil.isNotEmpty(hikvisionResourceTeamUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey) && StrUtil.isNotEmpty(hikvisionAppSecret) && schoolIds != null) {
            ArtemisConfig artemisConfig = new ArtemisConfig();
            artemisConfig.setHost(hikvisionHost);
            artemisConfig.setAppKey(hikvisionAppKey);
            artemisConfig.setAppSecret(hikvisionAppSecret);
            httpService.addTeamHik(artemisConfig, hikvisionResourceTeamUrl, schoolIds,userInfo.getSchoolYear());
        } else {
            log.error("调用海康远程接口失败，请检查配置接口信息是否正确！");
        }
        return "ok";
    }


    /**
     * * 图书馆请求
     */
    @RequestMapping("/lib")
    public String lib() {
        if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
            // 发送用户信息到图书馆（修改卡号也是同一个接口，直接新增一个用户）
            libraryService.teacherSend(libraryACnteen, libraryLogin, libraryCreate);
            libraryService.studentSend(libraryACnteen, libraryLogin, libraryCreate);
        } else {
            log.error("调用图书馆远程接口失败，请检查配置接口信息是否正确！");
        }
        return "ok";
    }


    /**
     * 1.发送学生到两家校车方
     * 2.发送修改卡号的学生
     */
    @RequestMapping("/schoolBus")
    public String schoolBus() {
        log.info("定时任务发送用户信息到校车..., 是否发送: {}", isSend);
        if (isSend == 1 && StrUtil.isNotEmpty(url) && StrUtil.isNotEmpty(url2)) {
            httpService.syncSendShoolBusStu(url, url2);
        }
        // 发送修改卡号的学生
        log.info("定时任务发送修改卡号的学生");
        // 发送学生信息到校车修改卡号接口
        if (StrUtil.isNotEmpty(schoolBusModifyCard) && StrUtil.isNotEmpty(schoolBusModifyCard2)) {
            // 查询需要修改的学生，一个学生可以对应多个校车
            httpService.modifyCardSchoolBus(schoolBusModifyCard, schoolBusModifyCard2);
        } else {
            log.error("调用校车接口失败，请检查配置接口信息是否正确！");
        }
        return "ok";
    }

    /**
     *
     */
    @RequestMapping("/test")
    public String test(Integer type, String inTime, String outTime, @RequestParam List<String> cards) {
        log.info("inTime:"+inTime+"    outTime:"+outTime+"  cards:"+cards+"  type:"+type);
        return carIdentifyService.getCardsMsg(type, inTime, outTime, cards).toString();
    }
}
