package platform.szxyzxx.ishangkelilu.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.ishangkelilu.dao.TeamClassDao;
import platform.szxyzxx.ishangkelilu.pojo.TeamClassPassWord;
import platform.szxyzxx.ishangkelilu.service.TeamClassService;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
import java.util.Map;

@Service
public class TeamClassServiceImpl implements TeamClassService {
    @Autowired
    private TeamClassDao teamClassDao;
    @Autowired
    private BasicSQLService basicSQLService;
    @Override
    public List<TeamClassPassWord> findByAll(Integer gradeId, Integer teamId,UserInfo userInfo, Page page) {
        return teamClassDao.findByAll(gradeId,teamId,userInfo.getSchoolId(),userInfo.getSchoolYear(),page);
    }

    @Override
    public String UpdateOrCreate(Integer teamId,String teamName, String password, UserInfo userInfo) {
       TeamClassPassWord teamClassPassWord=teamClassDao.findById(teamId);
       if(teamClassPassWord!=null){
           teamClassDao.update(teamId,password);
       }else{
           List<Map<String,Object>> gradeList=basicSQLService.find("select pt.* ,pg.name as gradeName from pj_team pt inner join pj_grade pg on pg.id=pt.grade_id " +
                   " where pt.school_id="+userInfo.getSchoolId()+" and pt.school_year="+userInfo.getSchoolYear()+" and pt.id="+teamId);
           TeamClassPassWord teamClassPassWord1=new TeamClassPassWord();
           teamClassPassWord1.setSchoolYear(userInfo.getSchoolYear());
           teamClassPassWord1.setSchoolTrem(userInfo.getSchoolTermCode());
           teamClassPassWord1.setSchoolId(userInfo.getSchoolId());
           teamClassPassWord1.setTeamId(teamId);
           teamClassPassWord1.setTeamName(gradeList.get(0).get("name").toString());
           teamClassPassWord1.setGradeId(Integer.parseInt(gradeList.get(0).get("grade_id").toString()));
           teamClassPassWord1.setPassWord(password);
           teamClassDao.create(teamClassPassWord1);
       }
        return "success";
    }

    @Override
    public String updateOrcreateTwo(String password, UserInfo userInfo) {
        List<TeamClassPassWord> list= teamClassDao.findByAll(null,null,userInfo.getSchoolId(),userInfo.getSchoolYear(),null);
        for(TeamClassPassWord aa:list){
            TeamClassPassWord teamClassPassWord=teamClassDao.findById(aa.getId());
            if(teamClassPassWord!=null){
                teamClassDao.update(aa.getId(),password);
            }else{
                List<Map<String,Object>> gradeList=basicSQLService.find("select pt.* ,pg.name as gradeName from pj_team pt inner join pj_grade pg on pg.id=pt.grade_id " +
                        " where pt.school_id="+userInfo.getSchoolId()+" and pt.school_year="+userInfo.getSchoolYear()+" and pt.id="+aa.getId());
                TeamClassPassWord teamClassPassWord1=new TeamClassPassWord();
                teamClassPassWord1.setSchoolYear(userInfo.getSchoolYear());
                teamClassPassWord1.setSchoolTrem(userInfo.getSchoolTermCode());
                teamClassPassWord1.setSchoolId(userInfo.getSchoolId());
                teamClassPassWord1.setTeamId(aa.getId());
                teamClassPassWord1.setTeamName(gradeList.get(0).get("name").toString());
                teamClassPassWord1.setGradeId(Integer.parseInt(gradeList.get(0).get("grade_id").toString()));
                teamClassPassWord1.setPassWord(password);
                teamClassDao.create(teamClassPassWord1);
            }
        }
        return "success";
    }
}
