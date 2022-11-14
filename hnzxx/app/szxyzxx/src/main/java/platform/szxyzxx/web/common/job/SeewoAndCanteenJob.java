package platform.szxyzxx.web.common.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.szxyzxx.core.service.WebApplicationContextAware;
import platform.szxyzxx.dto.meal.service.MealService;
import platform.szxyzxx.dto.seewo.service.StudentSeewoSyncService;
import platform.szxyzxx.dto.seewo.service.TeacherSeewoSyncService;
import platform.szxyzxx.dto.seewo.service.TeamSeewoSyncService;

/**
 * 1、同步数据到希沃，2、拉取食堂消费明细
 */
public class SeewoAndCanteenJob {

    private static Logger logger= LoggerFactory.getLogger(SeewoAndCanteenJob.class);



    public void syncData(){
        logger.info("开始同步希沃数据");

        //因service对象是被spring mvc的IOC容器（子容器）解析，而当前bean是被spring的IOC容器解析·所以当前bean中无法注入service对象
        //本来打算把service交给spring的IOC容器扫描，但是报空指针，所以只能用这种方式了
        //此类如果直接实现ApplicationContextAware注入的ApplicationContext是spring的ioc容器，一样获取不到service对象
        if(WebApplicationContextAware.applicationContext==null){
            logger.error("ApplicationContext未初始化，无法执行同步操作");
            return;
        }
        TeamSeewoSyncService teamSeewoSyncService= WebApplicationContextAware.applicationContext.getBean(TeamSeewoSyncService.class);
        TeacherSeewoSyncService teacherSeewoSyncService= WebApplicationContextAware.applicationContext.getBean(TeacherSeewoSyncService.class);
        StudentSeewoSyncService studentSeewoSyncService= WebApplicationContextAware.applicationContext.getBean(StudentSeewoSyncService.class);

        MealService mealService=WebApplicationContextAware.applicationContext.getBean(MealService.class);
        if(teamSeewoSyncService==null || teacherSeewoSyncService==null || studentSeewoSyncService==null || mealService==null){
            logger.error("ApplicationContext中获取service对象失败，无法执行同步操作");
            return;
        }
        try {
            teamSeewoSyncService.syncData();
            teacherSeewoSyncService.syncData();
            studentSeewoSyncService.syncData();
        } catch (Exception e) {
            logger.error("同步希沃数据失败",e);
        }
        logger.info("开始拉取食堂消费明细");
        try {
            mealService.syncLastDayReportMx();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("拉取食堂消费明细失败",e);
        }

    }


}
