package platform.szxyzxx.dto.seewo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.bo.StudentInfo;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.szxyzxx.dto.seewo.service.NewStudentSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.StudentSeewoSyncService;
import platform.szxyzxx.dto.seewo.service.TeamSeewoDataOperateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentSeewoSyncServiceImpl extends BaseSeewoSyncService<StudentInfo,Map<String,Object>> implements StudentSeewoSyncService {

    Logger logger= LoggerFactory.getLogger(StudentSeewoSyncServiceImpl.class);

    @Autowired
    private TeamSeewoDataOperateService teamSeewoDataOperateService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private NewStudentSeewoDataOperateService newStudentSeewoDataOperateService;

    @Autowired
    StudentService studentService;

    @Override
    public void syncData() {
        logger.info("开始同步学生数据到希沃");
        String nowSchoolYear=basicSQLService.getNowSchoolYear();
        List<StudentInfo> allStudent=studentService.findSendSeewoStu(nowSchoolYear);
        List<Map<String,Object>> allSeewoTeam=teamSeewoDataOperateService.queryAll();
        for (Map<String, Object> seewoTeam : allSeewoTeam) {
            String classUid=(String) seewoTeam.get("uid");
            logger.info("同步班级学生："+seewoTeam.get("nickName"));
            List<StudentInfo> teamStuList=findStuListBy(seewoTeam.get("grade").toString(),seewoTeam.get("clazz").toString(),allStudent);
            logger.info("系统班级学生数："+teamStuList.size());
            List<Map<String,Object>> seewoTeamStuList=newStudentSeewoDataOperateService.queryByClassUid(seewoTeam.get("uid").toString());
            logger.info("希沃班级学生数："+seewoTeamStuList.size());
            List<Map<String,Object>> deleteList=super.getDeleteList(teamStuList,seewoTeamStuList);
            if(deleteList.size()>0){
                logger.info("开始删除学生，数量："+deleteList.size());
                newStudentSeewoDataOperateService.deleteAll(deleteList,classUid);
            }
            List<StudentInfo> addList=super.getAddList(teamStuList,seewoTeamStuList);
            if(addList.size()>0){
                logger.info("开始添加学生，数量："+addList.size());
                newStudentSeewoDataOperateService.addAll(addList,classUid);
            }
            List<Map<String,Object>> updateCarList=super.getUpdateList(teamStuList,seewoTeamStuList);
            if(updateCarList.size()>0){
                logger.info("开始更新卡号学生，数量："+updateCarList.size());
                newStudentSeewoDataOperateService.updateCarNo(updateCarList);
            }
        }

    }

    @Override
    boolean pkEqual(StudentInfo platformObj, Map<String, Object> seewoObj) {
        return platformObj.getStudentCode().toString().equals(seewoObj.get("studentCode"));
    }

    @Override
    boolean needUpdateSeewoObj(StudentInfo platformObj, Map<String, Object> seewoObj) {
        String empCard=platformObj.getCardCode();
        if( empCard!=null && !empCard.equals(seewoObj.get("cardId"))){
            seewoObj.put("updateCarId",empCard);
            return true;
        }
        return false;
    }


    private List<StudentInfo> findStuListBy(String gradeNum,String teamNum,List<StudentInfo> list){
        List<StudentInfo> quilfierList=new ArrayList<>(20);
        for (StudentInfo stu : list) {
            if(stu.getGradeNumber().toString().equals(gradeNum) && stu.getTeamNumber().toString().equals(teamNum)){
                quilfierList.add(stu);
            }
        }
        return quilfierList;
    }

}
