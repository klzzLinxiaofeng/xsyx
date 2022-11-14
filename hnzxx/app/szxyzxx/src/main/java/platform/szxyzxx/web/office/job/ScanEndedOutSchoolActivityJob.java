package platform.szxyzxx.web.office.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.core.service.WebApplicationContextAware;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;

import java.util.List;
import java.util.Map;

/**
 * 校外活动结束时，更新状态以及通知申请人
 */

public class ScanEndedOutSchoolActivityJob {

    private static Logger logger = LoggerFactory.getLogger(ScanEndedOutSchoolActivityJob.class);

    // 具体执行定时器任务逻辑的方法
    public void execute() {
        //因service对象是被spring mvc的IOC容器（子容器）解析，而当前bean是被spring的IOC容器解析·所以当前bean中无法注入service对象
        //本来打算把service交给spring的IOC容器扫描，但是报空指针，所以只能用这种方式了
        //此类如果直接实现ApplicationContextAware,注入的ApplicationContext是spring的ioc容器，一样获取不到service对象
        if (WebApplicationContextAware.applicationContext == null) {
            logger.error("ApplicationContext未初始化，无法执行同步操作");
            return;
        }
        SystemWechatNotifyService notifyService = WebApplicationContextAware.applicationContext.getBean("asyncWechatNoticeService", SystemWechatNotifyService.class);
        if (notifyService == null) {
            logger.error("asyncWechatNoticeService获取失败");
        }
        BasicSQLService basicSQLService = WebApplicationContextAware.applicationContext.getBean(BasicSQLService.class);
        if (basicSQLService == null) {
            logger.error("basicSQLService获取失败");
        }

     /*   List<Map<String, Object>> list = basicSQLService.find("select a.id,a.user_id,u.open_id from at_out_school_activity a left join yh_user u on u.id=a.user_id where a.state=1 and a.is_notified=0 and now() >= a.end_time ");
        if (list.size() > 0) {
            logger.info("开始发送总结校外活动通知");

            basicSQLService.update("update at_out_school_activity set state=2,is_notified=1 where id in (" + joinId(list) + ")");

            for (Map<String, Object> map : list) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("校外活动填写总结结果");
                swNotice.setContent("校外活动已结束，请尽快填写总结");
                swNotice.setDataId(map.get("id").toString());
                swNotice.setSystemNoticeType(SystemNoticeType.ACTIVITY);
                swNotice.setDataIdType(SystemNoticeDataIdType.XWHD);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("校外活动填写总结结果", "校外活动已结束，请尽快填写总结"));
                try {
                    notifyService.sendSystemAndWechatNotice(swNotice, map.get("user_id"), map.get("open_id"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
*/
    }


    private String joinId(List<Map<String, Object>> list) {
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            ids.append(list.get(i).get("id"));
            if (i != list.size() - 1) {
                ids.append(",");
            }
        }
        return ids.toString();

    }

}
