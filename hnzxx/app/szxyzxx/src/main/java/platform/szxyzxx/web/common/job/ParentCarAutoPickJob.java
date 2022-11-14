package platform.szxyzxx.web.common.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.core.service.WebApplicationContextAware;
import platform.szxyzxx.schoolbus.service.CarIdentifyService;
import platform.szxyzxx.schoolbus.util.SchoolBusTimeUtil;
import platform.szxyzxx.web.schoolbus.vo.CarIdentifyVo;

import java.util.*;
//2022年05，25弃用转到BroadCastController类
/**
 *放学家长车辆进校时，自动增加一条“家长接送”数据
 */
public class ParentCarAutoPickJob {

    private static Logger logger= LoggerFactory.getLogger(ParentCarAutoPickJob.class);

    public void task(){
        try {

            logger.debug("开始查询进校家长车辆");

            Calendar now=Calendar.getInstance();
            now.setTime(new Date());
//            if(now.get(Calendar.HOUR_OF_DAY)<15){
//                return;
//            }

            if(WebApplicationContextAware.applicationContext==null){
                logger.error("ApplicationContext未初始化，无法执行同步操作");
                return;
            }

            CarIdentifyService carIdentifyService=WebApplicationContextAware.applicationContext.getBean(CarIdentifyService.class);
            BasicSQLService basicSQLService= WebApplicationContextAware.applicationContext.getBean(BasicSQLService.class);
            if(basicSQLService==null || carIdentifyService==null ){
                logger.error("ApplicationContext中获取service对象失败，无法执行同步操作");
                return;
            }
            //当前学年
            String nowSchoolYear= basicSQLService.getNowSchoolYear();

            //全校有家长车牌学生以及车牌号码
            List<Map<String,Object>> stuCarNoList=basicSQLService.find("SELECT s.id as stu_id,p.license_plate AS parent_car FROM pj_parent_student ps INNER JOIN pj_parent p ON ps.parent_user_id = p.user_id AND p.is_delete = 0 INNER JOIN pj_student s ON s.user_id = ps.student_user_id AND s.is_delete = 0 inner JOIN pj_team_student pts ON s.id = pts.student_id and pts.is_delete=0 INNER JOIN pj_team t ON t.id = pts.team_id WHERE p.license_plate IS NOT NULL AND t.school_year = '"+nowSchoolYear+"' and ps.rank=1 AND ps.is_delete = 0 and ps.rank=1");

            if(stuCarNoList==null || stuCarNoList.size()==0){
                logger.debug("当前没有学生家长设置车牌");
                return;
            }
            List<String> carNoList=getAllKeyVal(stuCarNoList,"parent_car");

            String nowDate=SchoolBusTimeUtil.getNowDateStr();
            //车辆放学时段进校信息
            List<CarIdentifyVo> carList=carIdentifyService.getCardsMsg(0,nowDate+" 15:00:00",nowDate+" 19:00:00",carNoList);

            if(carList==null || carList.size()==0){
                logger.debug("当前放学时间段没有进校家长车辆");
                return;
            }
            //当日放学全部家长接送
            List<Map<String,Object>> parentPickList=basicSQLService.find("select student_id from bus_parent_pick where pick_date='"+nowDate+"' and direction=1");
            int count=0;
            for (CarIdentifyVo carIdentifyVo : carList) {
                //已家长接送则忽略
                //根据家长车牌，查询出对应的学生id，但是只在没有家长接送时才返回id
                List<String> stuIds= findPickedStuIdIfNoParent(parentPickList,stuCarNoList,carIdentifyVo.getCarno());
                if(stuIds.size()>0){
                    for (String stuId : stuIds) {
                        //插入家长接送信息
                        basicSQLService.update("insert into bus_parent_pick(student_id,pick_date,direction,place,create_type) values("+stuId+",'"+nowDate+"',1,'停车场门口',1)");
                        //当日放学全部家长接送中添加当前接送，用于下次判断是否已经接送,避免多个家长同时进校导致插入多条记录
                        Map<String,Object> picked=new HashMap<>(2,1);
                        picked.put("student_id",stuId);
                        parentPickList.add(picked);
                        count++;
                    }
                }
            }
            logger.debug("自动家长接送统计："+count);
        } catch (BeansException e) {
            e.printStackTrace();
            logger.error("自动家长接送失败");
        }

    }


    private List<String> getAllKeyVal(List<Map<String,Object>> list,String key){
        List<String> valList=new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            valList.add(map.get(key).toString());
        }
        return valList;
    }


    private List<String>  findPickedStuIdIfNoParent(List<Map<String,Object>> parentPickList, List<Map<String,Object>> stuCarNoList, String parentCarNo){
        List<String> stuIds=findStuIdByCarNo(stuCarNoList,parentCarNo);
        if(stuIds.size()>0){
            for (Map<String, Object> pickMap : parentPickList) {
                String existId=pickMap.get("student_id").toString();
                if(stuIds.contains(existId)){
                    stuIds.remove(existId);
                }
            }
        }
        return stuIds;
    }

    private List<String> findStuIdByCarNo(List<Map<String,Object>> list,String parentCarNo){
        //一个家长可能有多个学生在学校
        List<String> carStuIds=new ArrayList<>(0);
        for (Map<String, Object> map : list) {
            if(map.get("parent_car").toString().contains(parentCarNo)){
                carStuIds.add( map.get("stu_id").toString());
            }
        }
        return carStuIds;
    }


}
