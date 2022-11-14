package platform.szxyzxx.ishangkelilu.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.ishangkelilu.dao.StudyHabitsDao;
import platform.szxyzxx.ishangkelilu.pojo.*;
import platform.szxyzxx.ishangkelilu.service.StudyHabitsService;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class StudyHabitsServiceImpl implements StudyHabitsService {
    @Autowired
    private StudyHabitsDao studyHabitsDao;
    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public List<Student> findByStudent(UserInfo userInfo, Integer gradeId, String schoolYear, Integer teamId, Page page) {
       Integer teamIds;
        List<Student> studentList=new ArrayList<>();
        if(teamId==null){
            teamIds=studyHabitsDao.findByTeamId(userInfo.getTeacherId(),userInfo.getSchoolYear());
            studentList = studyHabitsDao.findByStudent(userInfo.getSchoolId(), teamIds, gradeId, userInfo.getSchoolYear());
                return studentList;
        }else {
             studentList = studyHabitsDao.findByStudent(userInfo.getSchoolId(), teamId, gradeId, userInfo.getSchoolYear());
        }
        return studentList;
    }

    @Override
    public String createOrupdate(StudyHabits studyHabits) {
        StudyHabits studyHabits1=studyHabitsDao.findByStudy(studyHabits);
        Date date=new Date();
        if(studyHabits1==null){
             int a=studyHabitsDao.createZanshi(studyHabits);
             if(a>0){
                 return "success";
             }
             return "shibai";
        }
        int b=studyHabitsDao.updateZanshi(studyHabits);
        if(b>0){
            return "success";
        }
        return "shibai";
    }

    @Override
    public String findByXiake(UserInfo userInfo,Integer teamId) {
        List<StudyHabits> studyHabitsList=studyHabitsDao.findByXiake(userInfo.getTeacherId(),teamId);
        if(studyHabitsList.size()<=0){
            return "success";
        }
        for(StudyHabits studyHabits:studyHabitsList){
           int b= studyHabitsDao.create(studyHabits);
            if(b>0){
                studyHabits.setScore(null);
                studyHabitsDao.updateZanshi(studyHabits);
            }
        }
        return "success";
    }

    @Override
    public List<StudentXin> findBytianjiachazhao(String stuName, UserInfo user) {
        return studyHabitsDao.findBytianjiachazhao(stuName,user.getSchoolId(),user.getSchoolYear());
    }

    @Override
    public List<ZuoWei> findByZuowei(Integer teamId) {
        return studyHabitsDao.findByZuowei(teamId);
    }

    @Override
    public Boolean createZuowei(ZuoWei zuoWei) {
        ZuoWei zuowei=studyHabitsDao.findByZuoweiaa(zuoWei);
        if(zuowei!=null){
            studyHabitsDao.updateAjsd(zuoWei);
        }
        return studyHabitsDao.createZuowei(zuoWei)>0;
    }

    @Override
    public List<StudyHabits> findByShangke(Integer teamId, Integer teacherId) {
        return studyHabitsDao.findByShangke(teamId,teacherId);
    }

    @Override
    public Boolean findBychehui(Integer id) {
        String sql="  update pj_student_zanshijiu set is_delete=1 where id="+id;
        int a=basicSQLService.update(sql);
        return a>0;
    }

    @Override
    public ZuoWeiHangLie findByzuoweiHangfLie(Integer teamId, UserInfo userInfo) {
        return studyHabitsDao.findByzuoweiHangfLie(teamId);
    }

    @Override
    public Boolean createZuoweiHangLie(Integer teamId, Integer zu, Integer hang, Integer lie) {
        Date date=new Date();
        ZuoWeiHangLie zuoWeiHangLie=new ZuoWeiHangLie();
        zuoWeiHangLie.setCreateTime(date);
        zuoWeiHangLie.setZuNumber(zu);
        zuoWeiHangLie.setHangNumber(hang);
        zuoWeiHangLie.setLieNumber(lie);
        zuoWeiHangLie.setTeamId(teamId);
      return   studyHabitsDao.createZuoweiHangLie(zuoWeiHangLie)>0;

    }

    @Override
    public Boolean updateZuoweiHangLie(Integer teamId, Integer zu, Integer hang, Integer lie) {
        Date date=new Date();
        ZuoWeiHangLie zuoWeiHangLie=new ZuoWeiHangLie();
        zuoWeiHangLie.setModelTime(date);
        zuoWeiHangLie.setZuNumber(zu);
        zuoWeiHangLie.setHangNumber(hang);
        zuoWeiHangLie.setLieNumber(lie);
        zuoWeiHangLie.setTeamId(teamId);
        return  studyHabitsDao.updateZuoweiHangLie(zuoWeiHangLie)>0;
    }

    @Override
    public List<StudyHabits> getAllStudy(Integer gradeId,Integer teamId, Integer subjectId, String studentName,String schoolYear,String schoolTrem,String startTime,String endTime,Page page) {
        return studyHabitsDao.findByAllStudy(gradeId,teamId,subjectId,studentName,schoolYear,schoolTrem,startTime,endTime,page);
    }

    @Override
    public Integer updateStudy(Integer id, String pingyu) {
        return studyHabitsDao.updateJiLu(id,pingyu);
    }

    @Override
    public StudyHabits findById(Integer id) {
        return studyHabitsDao.findById(id);
    }

    @Override
    public String updatezuowei(Integer id) {
        Integer num=  studyHabitsDao.updatezuowei(id);
        if(num>0 ){
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public List<HabitsDaoChu> findByDaoChu(Integer gradeId, Integer teamId, String schoolYear, String schoolTrem, Integer subjectId,String startDate, String endDate) {
        List<HabitsDaoChu> list=studyHabitsDao.findByDaoChu(gradeId,teamId,schoolYear,schoolTrem,subjectId,startDate,endDate);
        for(HabitsDaoChu aa:list){
            if(aa.getScore()<0){
                aa.setPingjiaJianfen(aa.getScore());
            }else{
                aa.setPingjiaJiafen(aa.getScore());
            }
        }
        return list;
    }

    @Override
    public List<HabitsDaoChu> daochuzongfen(Integer gradeId, Integer teamId, String schoolYear, String schoolTrem, Integer subjectId, String startDate, String endDate) {
        String sql="select ps.* from pj_student ps inner join pj_team_student pts on pts.student_id=ps.id  inner join pj_grade pg on pg.id=pts.grade_id where ps.is_delete=0 and pg.school_year="+schoolYear;
        if(gradeId!=null){
            sql+=" and  pts.grade_id="+gradeId;
        }
        if(teamId!=null){
            sql+="  and pts.team_id="+teamId;
        }
        sql+= " group by ps.id  order by ps.team_id,ps.emp_code asc";
        List<Map<String ,Object>> mapList=basicSQLService.find(sql);
        List<HabitsDaoChu> daoChuList=new ArrayList<>();
        for(Map<String,Object> aa:mapList){
            List<HabitsDaoChu> list=studyHabitsDao.findBydaochuzongfen(gradeId,teamId,schoolYear,schoolTrem,subjectId,startDate,endDate,Integer.parseInt(aa.get("id").toString()));
            HabitsDaoChu habitsDaoChu=new HabitsDaoChu();
            habitsDaoChu.setXueHao(aa.get("emp_code").toString());
            habitsDaoChu.setStudentName(aa.get("name").toString());
            habitsDaoChu.setTeamName(aa.get("team_name").toString());
            int jianum=0;
            int jiannum=0;
            if(subjectId!=null){
                for(HabitsDaoChu bb: list){
                    if(bb.getScore()<0){
                        jiannum+=bb.getScore();
                    }else{
                        jianum+=bb.getScore();
                    }
                    habitsDaoChu.setSubjectName(bb.getSubjectName());
                }
                habitsDaoChu.setPingjiaJianfen(jiannum);
                habitsDaoChu.setPingjiaJiafen(jianum);
                habitsDaoChu.setScore(jiannum+jianum);
                daoChuList.add(habitsDaoChu);
            }else{
                for(HabitsDaoChu bb: list){
                if(bb.getScore()<0){
                    jiannum+=bb.getScore();
                }else{
                    jianum+=bb.getScore();
                }
            }
                habitsDaoChu.setPingjiaJianfen(jiannum);
                habitsDaoChu.setPingjiaJiafen(jianum);
                habitsDaoChu.setScore(jiannum+jianum);
                habitsDaoChu.setSubjectName("全科");
                daoChuList.add(habitsDaoChu);
            }
        }
        return daoChuList;
      }

    @Override
    public List<KeTangGongShi> findByKeTangGongShi(Integer gradeId, Integer teamId, String schoolYear, String schoolTrem, Integer subjectId, String startDate, String endDate) {
        return studyHabitsDao.findByKeTangGongShi(gradeId,teamId,schoolYear,schoolTrem,subjectId,startDate,endDate);
    }

}
