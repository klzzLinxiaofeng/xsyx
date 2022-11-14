package platform.szxyzxx.web.common.job;

import cn.hutool.core.util.StrUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

/**
 * 海康对接
 * 1.发送学生班级到海康（添加组织）
 * 2.发送学生和老师到海康
 *
 * @author: yhc
 * @Date: 2021/3/8 15:05
 * @Description:
 */
public class HikvisonJob {
    private static final Logger log = LoggerFactory.getLogger(HikvisonJob.class);

    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;

    @Autowired
    private BasicSQLService basicSQLService;


    /**
     * 海康接口
     */
    private static String fileName;
    private static String hikvisionHost;
    private static String hikvisionAppKey;
    private static String hikvisionAppSecret;
    private static String hikvisionAddPersonUrl;
    private static String hikvisionBindCarUrl;
    private static String hikvisionResourceTeamUrl;
    private static String untieCarUrl;

    private static Integer schoolIds;

    private static ArtemisConfig artemisConfig;

    static {
        fileName = "System.properties";
        //闸机
        hikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
        hikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
        hikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
        hikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");
        hikvisionBindCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.bindings");
        hikvisionResourceTeamUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.resource");
        untieCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.deletion");

        schoolIds = Integer.valueOf(PropertiesUtil.getProperty(fileName, "schoolId"));

        artemisConfig = new ArtemisConfig();
        artemisConfig.setHost(hikvisionHost);
        artemisConfig.setAppKey(hikvisionAppKey);
        artemisConfig.setAppSecret(hikvisionAppSecret);

    }


    public static ArtemisConfig getHkConfig(){
        return artemisConfig;
    }

    /**
     * 发送学生到海康
     */
    public void jobMethod() {
        //海康已经有1-6年级所有班级了，新增的组织在同一个parent下名字不可重复，而定时查询的是未推送班级，每学年都会生成，名字会和海康重复，所以不会推送成功，故注释
        //sendResourceTeamToHik();
        //有了实时发送就不需要定时发送了，这个定时发送有bug，一次绑定多张卡，一张卡有误全部失败
        //syncSendUserToHik();
    }

  /*  public void sendResourceTeamToHik(){
        log.info("定时任务发送组织信息到海康");
        if (StrUtil.isNotEmpty(hikvisionResourceTeamUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && schoolIds != null) {
            ArtemisConfig artemisConfig = new ArtemisConfig();
            artemisConfig.setHost(hikvisionHost);
            artemisConfig.setAppKey(hikvisionAppKey);
            artemisConfig.setAppSecret(hikvisionAppSecret);
            httpService.addTeamHik(artemisConfig, hikvisionResourceTeamUrl, schoolIds,);
        } else {
            log.error("调用海康远程接口失败，请检查配置接口信息是否正确！");
        }


    }
*/
    public void syncSendUserToHik(){
        log.info("定时任务发送用户信息到海康");
        if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
            ArtemisConfig artemisConfig = new ArtemisConfig();
            artemisConfig.setHost(hikvisionHost);
            artemisConfig.setAppKey(hikvisionAppKey);
            artemisConfig.setAppSecret(hikvisionAppSecret);
            // 0：学生 1：教师++++++
            //httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 0);
           // httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 1);
        } else {
            log.error("调用海康远程接口失败，请检查配置接口信息是否正确！");
        }
    }



}
