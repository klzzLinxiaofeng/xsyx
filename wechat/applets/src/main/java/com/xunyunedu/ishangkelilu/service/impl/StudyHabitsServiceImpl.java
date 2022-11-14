package com.xunyunedu.ishangkelilu.service.impl;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.ishangkelilu.dao.StudyHabitsDao;
import com.xunyunedu.ishangkelilu.pojo.StudyHabits;
import com.xunyunedu.ishangkelilu.service.StudyHabitsService;
import com.xunyunedu.login.pojo.ZuoWeiHangLie;
import com.xunyunedu.team.pojo.TeamPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudyHabitsServiceImpl implements StudyHabitsService {
    Logger logger = LoggerFactory.getLogger(StudyHabitsServiceImpl.class);
    @Autowired
    private StudyHabitsDao studyHabitsDao;
    @Autowired
    private BasicSQLService basicSQLService;
    @Override
    public List<TeamPojo> findByTeam(Integer teacherId, Integer schoolId, String schoolYear) {
        return studyHabitsDao.findByTeam(teacherId,schoolId,schoolYear);
    }

    @Override
    public Map<String, Object> findBystudentXiGuan(Integer studentId, Integer schoolId,String schoolYear) {
        Integer teamId=studyHabitsDao.findByStudentTeamId(studentId,schoolId);
        List<Integer> gradeId=studyHabitsDao.findByStudentGradeId(studentId,schoolId,schoolYear);
        Integer teamNumber=studyHabitsDao.findByteamNumber(teamId);
        Integer gradeNumber=studyHabitsDao.findByteamNumber(teamId);
        /*学生分数*/
        List<StudyHabits> studyHabitsList=new ArrayList<>();
        /*班级平均分数*/
        List<StudyHabits> teamhabitsList =new ArrayList<>();
        /*年级平均分数*/
        List<StudyHabits> gradehabitsList=new ArrayList<>();
        for(int a=0; a<6;a++){
            /*学生分数*/
            StudyHabits studentHabits=studyHabitsDao.findByStudentStudyHabits(studentId,a+1);
            StudyHabits studentHabits2=new StudyHabits();
            if(studentHabits==null){
                studentHabits2.setLeixing(a+1);
                studentHabits2.setScore(80);
                studyHabitsList.add(studentHabits2);
            }else{
                studyHabitsList.add(studentHabits);
            }
            //班级平均分
            StudyHabits teamhabits=studyHabitsDao.findByTeamStudyHabits(teamId,schoolId,a+1);
            StudyHabits teamhabits2=new StudyHabits();
            int Num=0;
            if(teamhabits!=null){
                teamhabits2.setLeixing(a+1);
                if(teamhabits.getScore()!=null){
                   int num=teamNumber*80+teamhabits.getScore();
                   Num=num/teamNumber;
                }else{
                    Num=80;
                }
                teamhabits2.setScore(Num);
                teamhabitsList.add(teamhabits2);
            }else {
                teamhabits2.setLeixing(a+1);
                teamhabits2.setScore(80);
                teamhabitsList.add(teamhabits2);
            }
            //年级平均分
            StudyHabits gradehabits=studyHabitsDao.findByGradeStudyHabits(a+1,gradeId.get(0),schoolId);
            StudyHabits gradehabits2=new StudyHabits();
            int Sum=0;
            if(gradehabits!=null){
                gradehabits2.setLeixing(a+1);
                if(gradehabits.getScore()!=null){
                    int sum=gradeNumber*80+gradehabits.getScore();
                    Sum=sum/gradeNumber;
                }else{
                    Sum=80;
                }
                gradehabits2.setScore(Sum);
                gradehabitsList.add(gradehabits2);
            }else{
                gradehabits2.setLeixing(a+1);
                gradehabits2.setScore(80);
                gradehabitsList.add(gradehabits2);
            }
        }
        Map<String,Object> map =new HashMap<>();
        map.put("studentHabits",studyHabitsList);
        map.put("teamHabits",teamhabitsList);
        map.put("gradeHabits",gradehabitsList);
        return map;
    }

    @Override
    public Boolean create(StudyHabits studyHabits) {
        Date date=new Date();
        boolean falg=true;
        StudyHabits studyHabits1=studyHabitsDao.findBysdjw(studyHabits.getTeamId(),studyHabits.getLeixing());
       if(studyHabits1!=null){
           Integer num= studyHabitsDao.update(studyHabits);
           if(num>0){
               return true;
           }else{
               return false;
           }
       }
        studyHabits.setCreateTime(date);
        studyHabits.setStats(0);
           Integer num= studyHabitsDao.create(studyHabits);
           if(num<=0){
               falg=false;
               return falg;
           }
        return falg;
    }

    @Override
    public List<StudyHabits> findByShangke(Integer teamId, Integer teacherId) {
        return studyHabitsDao.findByShangke(teamId,teacherId);
    }

    @Override
    public ZuoWeiHangLie findByzuoweiHangfLie(Integer teamId) {
        return studyHabitsDao.findByzuoweiHangfLie(teamId);
    }
    @Override
    public String createOrupdate(StudyHabits studyHabits) {
       //StudyHabits studyHabits1=studyHabitsDao.findByStudy(studyHabits);
        Date date=new Date();
            studyHabits.setCreateTime(date);
            int a=studyHabitsDao.createTwo(studyHabits);
        if(a>0){
            return "success";
        }
        return "shibai";
    }

    @Override
    public String findByXiake(Integer teacherId, Integer teamId) {
        /*List<StudyHabits> studyHabitsList=studyHabitsDao.findByXiake(teacherId,teamId);
        if(studyHabitsList.size()<=0){
            return "false";
        }
        for(StudyHabits studyHabits:studyHabitsList){
            int b= studyHabitsDao.createTwo(studyHabits);
            if(b>0){
                studyHabits.setScore(null);
                studyHabits.setStats(1);
                studyHabitsDao.updateZanshi(studyHabits);
            }
        }*/
        return "success";
    }
    @Override
    public Boolean findBychehui(Integer id) {
        String sql="  update pj_student_zanshijiu set is_delete=1 where id="+id;
        int a=basicSQLService.update(sql);
        return a>0;
    }
}
