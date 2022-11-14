package platform.szxyzxx.web.common.job;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

/**
 * 校车系统
 *
 * @author: yhc
 * @Date: 2021/3/8 15:05
 * @Description:
 */
public class SchoolBusJob {
    private static final Logger log = LoggerFactory.getLogger(SchoolBusJob.class);

    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;

    private static Integer isSend;
    private static String url;
    private static String url2;

    private static String schoolBusModifyCard;
    private static String schoolBusModifyCard2;

    static {
        String fileName = "System.properties";
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

    /**
     * 1.发送学生到两家校车方
     * 2.发送修改卡号的学生
     */
    public void jobMethod() {
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
    }
}
