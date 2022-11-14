package platform.szxyzxx.dto.seewo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.szxyzxx.dto.seewo.service.TeamSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeamSeewoSyncService;

import java.util.List;
import java.util.Map;

@Service
public class TeamSeewoSyncServiceImpl extends BaseSeewoSyncService<Map<String,Object>,Map<String,Object>> implements TeamSeewoSyncService {

    Logger logger= LoggerFactory.getLogger(TeamSeewoSyncServiceImpl.class);

    @Autowired
    private TeamSeewoDataOperateService tsoService;

    @Autowired
    TeamService teamService;
    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public void syncData() {
        logger.info("开始同步希沃班级");
        String nowSchoolYear=basicSQLService.getNowSchoolYear();
        List<Map<String,Object>> teamList=basicSQLService.find("SELECT t.id, t.school_year AS schoolYear, g.grade_number AS gradeNumber, t.`name` teamName, t.team_number AS teamNumber, te.mobile FROM pj_team t inner JOIN pj_grade AS g ON t.`grade_id` = g.id left join `pj_team_teacher` AS tt on tt.team_id=t.id and tt.type=1 and tt.is_delete=0 left JOIN pj_teacher AS te ON tt.user_id = te.user_id and te.is_send_canteen= 1 AND te.emp_card != '0' and te.is_delete=0 left join yh_user tu on tu.id=te.user_id and tu.`state`=0 and tu.is_deleted=0 WHERE t.is_delete = 0 AND t.school_id = 215 AND t.school_year = '"+nowSchoolYear+"'");
        logger.info("系统班级数量："+teamList.size());
        List<Map<String,Object>> seewoTeamList=tsoService.queryAll();
        logger.info("希沃系统班级数量："+seewoTeamList.size());
        List<Map<String,Object>> addList=getAddList(teamList,seewoTeamList);
        List<Map<String,Object>> deleteList=getDeleteList(teamList,seewoTeamList);
        boolean hasUpdate=false;
        if(addList.size()>0){
            logger.info("开始添加班级，数量："+addList.size());
            tsoService.addAll(addList);
            hasUpdate=true;
        }
        if(deleteList.size()>0){
            logger.info("开始删除班级，数量："+deleteList.size());
            tsoService.delete(deleteList);
            hasUpdate=true;
        }
        if(hasUpdate) {
            seewoTeamList = tsoService.queryAll();
        }
        //设置级班主任
        for (Map<String, Object> teamMap : teamList) {
            String seewoTeamId=findSeewoTeam(teamMap,seewoTeamList).get("uid").toString();
            tsoService.removeMasterRelation(seewoTeamId);
            String mobile=(String) teamMap.get("mobile");
            if(StringUtils.isNotEmpty(mobile)) {
                tsoService.setMaster(seewoTeamId, mobile);
            }
        }
        logger.info("班级同步完成");

    }


    private Map<String ,Object> findSeewoTeam(Map<String, Object> p, List<Map<String, Object>> sList){
        for (Map<String, Object> s : sList) {
            if(pkEqual(p,s)){
                return s;
            }
        }
        return null;
    }

    @Override
    boolean pkEqual(Map<String, Object> p, Map<String, Object> s) {
        return p.get("schoolYear").toString().equals(s.get("gradeYear").toString()) && p.get("gradeNumber").toString().equals(s.get("grade").toString()) && p.get("teamName").equals(s.get("nickName"));
    }

    @Override
    boolean needUpdateSeewoObj(Map<String, Object> p, Map<String, Object> s) {
        return false;
    }
}
