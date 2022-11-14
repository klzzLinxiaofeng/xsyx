package platform.szxyzxx.web.common.job;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.data.redis.core.StringRedisTemplate;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.core.service.WebApplicationContextAware;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.schoolbus.pojo.GatePickInfo;
import platform.szxyzxx.schoolbus.service.BusThirdPartyApiService;
import platform.szxyzxx.schoolbus.util.SchoolBusTimeUtil;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *查询门禁打卡然后通知家长
 */
public class GatePickNotifyJob {

    private static Logger logger= LoggerFactory.getLogger(GatePickNotifyJob.class);

    private final static String LAST_TIME_KEY="GatePickNotifyJob_LastTime";

    public void sendGageNotice(){
        try {

            logger.debug("发送门禁打卡通知");

            if(WebApplicationContextAware.applicationContext==null){
                logger.error("ApplicationContext未初始化，无法执行同步操作");
                return;
            }
            BasicSQLService basicSQLService= WebApplicationContextAware.applicationContext.getBean(BasicSQLService.class);
            BusThirdPartyApiService thirtyPartyApiService=WebApplicationContextAware.applicationContext.getBean("busThirdPartyApiServiceImpl",BusThirdPartyApiService.class);
            SystemWechatNotifyService notifyService=WebApplicationContextAware.applicationContext.getBean("asyncWechatNoticeService",SystemWechatNotifyService.class);
            StringRedisTemplate redisTemplate= WebApplicationContextAware.applicationContext.getBean(StringRedisTemplate.class);
            if(basicSQLService==null || thirtyPartyApiService==null || notifyService==null || redisTemplate==null){
                logger.error("ApplicationContext中获取service对象失败，无法执行同步操作");
                return;
            }
            //不同学期开始学年不一样
            String nowSchoolYear=basicSQLService.findUnique("select school_year from pj_school_term_current where school_id=215").toString();
            //当前学期所有家长有open_id的学生和家长信息
            String sql="select s.user_id, s.`name`,pu.open_id from pj_student s left join pj_parent_student ps on s.user_id=ps.student_user_id and ps.rank=1 LEFT JOIN yh_user pu ON pu.id = ps.parent_user_id left join pj_team t on t.id=s.team_id where t.school_year='"+nowSchoolYear+"' and s.is_delete = 0 and ps.is_delete=0 AND pu.open_id IS NOT NULL";
            List<Map<String,Object>> stuList=basicSQLService.find(sql);
            if(stuList==null || stuList.size()==0){
                return;
            }
            List<String> stuUserIds=new ArrayList<>(stuList.size());
            for (Map<String,Object> stu : stuList) {
                stuUserIds.add(stu.get("user_id").toString());
            }

            Calendar now=getNowCalendar();
            String nowGmt=getGMT(now);
            //考虑到时间延迟，以及临界点数据，查询截至时间比当前时间晚一秒
            now.add(Calendar.SECOND,-1);
            String nowLastSecondGmt= getGMT(now);


            String lastTime=getLastQueryTime(redisTemplate);
            logger.debug("beginTime:"+lastTime+"    endTime:"+nowLastSecondGmt);
            List<GatePickInfo> gatePickInfoList=thirtyPartyApiService.getListStudentGatePickInfo(stuUserIds,lastTime,nowLastSecondGmt);
            setLastQueryTime(redisTemplate,nowGmt);

            if(gatePickInfoList==null || gatePickInfoList.size()==0){
                logger.debug("当前没有学生门禁打卡记录");
                return;
            }

            for (GatePickInfo gatePickInfo : gatePickInfoList) {
                Map<String,Object> stu=findByUserId(gatePickInfo.getPersonId(),stuList);
                if(stu!=null){
                    notifyService.sendWechatNotice(new ApprovalWechatMessageTemplate("学生打卡通知","您的小孩【"+stu.get("name")+"】在"+SchoolBusTimeUtil.formatGmtEventTimeToSecond(gatePickInfo.getEventTime())+"打卡"+(gatePickInfo.getInAndOutType()==1 ? "进校":"出校")),stu.get("open_id").toString(),null);
                }
            }

            logger.info("门禁打卡通知推送人数："+gatePickInfoList.size());

        } catch (BeansException e) {
            e.printStackTrace();
            logger.error("门禁打卡通知发送失败");
        }


    }


    private String getLastQueryTime(StringRedisTemplate redisTemplate){
        String lastTime=redisTemplate.opsForValue().get(LAST_TIME_KEY);
        String nowDate=SchoolBusTimeUtil.getNowDateStr();
        if(StringUtils.isEmpty(lastTime) || !lastTime.startsWith(nowDate)){
            lastTime=SchoolBusTimeUtil.getUpBeginGmtDateTimeStr(nowDate);
        }
        return lastTime;
    }

    private void setLastQueryTime(StringRedisTemplate redisTemplate,String lastTime){
        redisTemplate.opsForValue().set(LAST_TIME_KEY,lastTime);
    }


    private Calendar getNowCalendar(){

        Calendar nowCalendar=Calendar.getInstance();
        nowCalendar.setTime(new Date());
        nowCalendar.add(Calendar.SECOND,-1);
        return nowCalendar;
    }

    private String getGMT(Calendar calendar){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(calendar.getTime())+".000+08:00";
    }


    private Map<String,Object> findByUserId(String userId,List<Map<String,Object>> list){
        for (Map<String,Object> stu : list) {
            if(stu.get("user_id").toString().equals(userId)){
                return stu;
            }
        }
        return null;
    }














}
