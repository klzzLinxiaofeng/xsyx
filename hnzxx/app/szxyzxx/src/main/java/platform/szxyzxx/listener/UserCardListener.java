package platform.szxyzxx.listener;

import cn.hutool.core.util.StrUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import platform.education.generalTeachingAffair.event.UpdateCardEvent;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.service.LibraryService;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

/**
 * @author: yhc
 * @Date: 2021/4/2 17:26
 * @Description: 用户卡号修改监听事件
 */

@Component
public class UserCardListener implements ApplicationListener<UpdateCardEvent> {


    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 图书馆接口
     */
    private static String libraryACnteen;
    private static String libraryLogin;
    private static String libraryCreate;
    private static String schoolBusModifyCard;
    private static String schoolBusModifyCard2;


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
        String fileName = "System.properties";
        // 图书馆
        libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
        libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
        libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");
        schoolBusModifyCard = PropertiesUtil.getProperty(fileName, "schoolBus.send.modifyCard");
        schoolBusModifyCard2 = PropertiesUtil.getProperty(fileName, "schoolBus.send.modifyCard2");

        //闸机
        hikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
        hikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
        hikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
        hikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");
        hikvisionBindCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.bindings");
        untieCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.deletion");
    }


    public ArtemisConfig getArtemisConfig(){
        ArtemisConfig artemisConfig = new ArtemisConfig();
        artemisConfig.setHost(hikvisionHost);
        artemisConfig.setAppKey(hikvisionAppKey);
        artemisConfig.setAppSecret(hikvisionAppSecret);
        return artemisConfig;
    }



    @Autowired
    @Qualifier("libraryService")
    private LibraryService libraryService;


    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;

    /**
     * 用户卡号修改业务
     *
     * @param updateCardEvent
     */
    @Override
    public void onApplicationEvent(UpdateCardEvent updateCardEvent) {
        Integer type = updateCardEvent.getuType();

        log.info("发送用户信息到图书馆");
        // 发送用户信息到图书馆（修改卡号也是同一个接口，直接新增一个用户）
        if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
            // 用户类型 0:学生 1老师
            if (type != null && type == 1) {
                libraryService.teacherSend(libraryACnteen, libraryLogin, libraryCreate);
            } else if (type != null && type == 0) {
                libraryService.studentSend(libraryACnteen, libraryLogin, libraryCreate);
            }
        } else {
            log.error("调用图书馆远程接口失败，请检查配置接口信息是否正确！");
        }
        log.info("发送用户信息到校车");
        // 发送用户信息到校车 用户类型 0:学生 1老师
        if (type != null && type == 0) {
            // 发送学生信息到校车修改卡号接口
            if (StrUtil.isNotEmpty(schoolBusModifyCard) && StrUtil.isNotEmpty(schoolBusModifyCard2)) {
                // 查询需要修改的学生，一个学生对应多个校车
                httpService.modifyCardSchoolBus(schoolBusModifyCard, schoolBusModifyCard2);
            } else {
                log.error("调用校车接口失败，请检查配置接口信息是否正确！");
            }
        }

//        log.info("监听任务发送用户 和修改卡号 信息到海康");
//        if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
//            ArtemisConfig artemisConfig = getArtemisConfig();
//            // type 0：学生 1：教师
//            httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, type);
//        } else {
//            log.error("调用海康远程接口失败，请检查配置接口信息是否正确！");
//        }
    }
}
