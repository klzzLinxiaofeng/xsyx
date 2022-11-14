package platform.szxyzxx.dto.seewo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.dto.seewo.service.TeacherSeewoSyncService;

import java.util.List;
import java.util.Map;

@Service
public class TeacherSeewoSyncServiceImpl extends BaseSeewoSyncService<Map<String,Object>,Map<String,Object>> implements TeacherSeewoSyncService {

    private static final Logger logger= LoggerFactory.getLogger(TeacherSeewoSyncServiceImpl.class);

    @Autowired
    private TeacherSeewoDataOperateServiceImpl tsoService;

    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public void syncData() {
        logger.info("开始与希沃同步老师数据");
        List<Map<String,Object>> plist=basicSQLService.find("SELECT t.mobile,t.`name`,t.emp_card FROM `pj_teacher` t inner join yh_user u on u.id=t.user_id where t.is_delete=0 and u.is_deleted=0 and u.`state`=0 and t.mobile is not null and t.mobile!=''  and t.school_id=215");
        logger.info("系统老师数量:"+plist.size());
        List<Map<String,Object>> slist=tsoService.queryAll();
        logger.info("希沃老师数量："+slist.size());
        List<Map<String,Object>> addList=getAddList(plist,slist);
        logger.info("希沃需添加老师数量："+addList.size());
        List<Map<String,Object>> delList=getDeleteList(plist,slist);
        logger.info("希沃需删除老师数量："+delList.size());
        if(addList.size()>0) {
            tsoService.addAll(addList);
        }
        if(delList.size()>0) {
            tsoService.delete(delList);
        }
        logger.info("开始更新老师卡号");
        tsoService.updateAllCarNo(plist);
        logger.info("老师数据同步完成");
    }

    @Override
    boolean pkEqual(Map<String,Object> platformObj, Map<String, Object> seewoObj) {
        return platformObj.get("mobile").toString().equals(seewoObj.get("phone").toString());
    }

    @Override
    boolean needUpdateSeewoObj(Map<String,Object> platformObj, Map<String, Object> seewoObj) {
        return false;
    }
}
