package platform.szxyzxx.exam.service.impl;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.ExamQuestionVoDao;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.dao.StudentScoreDao;
import platform.education.generalTeachingAffair.dao.TeamDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.exam.dao.ExamScoreDao;
import platform.szxyzxx.exam.service.ExamScoreService;
import platform.szxyzxx.exam.vo.ExamQuery;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ExamScoreServiceImpl implements ExamScoreService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentScoreDao studentScoreDao;

    @Autowired
    private ExamQuestionVoDao examQuestionVoDao;
    @Autowired
    private ExamScoreDao examScoreDao;

    @Autowired
    private TeamDao teamDao;
    @Autowired
    private BasicSQLService basicSQLService;
    @Override
    public Map<String,Object> findByGrrenScore(UserInfo user, ExamQuery examQuery, Page page) {
        Map<String,Object> objectMap=new HashMap<>();
        List<Map<String, Object>> mapList=new ArrayList<>();
        List<Student> studentList=studentDao.findByall(user.getSchoolId(),examQuery.getSchoolYear(),examQuery.getTeamId(),examQuery.getGradeId(),null,page);
        ExamQuestionVo examQuestionVo=new ExamQuestionVo();
        examQuestionVo.setSchoolYear(examQuery.getSchoolYear());
        examQuestionVo.setExamTeamSubjectId(examQuery.getExamId());
        examQuestionVo.setGradeId(examQuery.getGradeId());
        examQuestionVo.setTeamId(examQuery.getTeamId());
        examQuestionVo.setSubjectCode(examQuery.getSubjectCode());
        List<ExamQuestionVo> examQuestionVoList=examQuestionVoDao.findByTop(examQuestionVo);
        for(Student aa:studentList){
            Map<String,Object> map=new HashMap<>();
            map.put("empCode",aa.getEmpCode());
            map.put("stuName",aa.getName());
            StudentScore studentScore=studentScoreDao.findByStudentIdAndExamId(aa.getId(),examQuery.getExamId());
             //查询总分
            if(studentScore!=null){
                map.put("zongfen",studentScore.getScore());
            }else{
                map.put("zongfen",0);
            }
            //计算各题的得分率
                List<Map<String,Object>> mapList1=basicSQLService.find("select CONVERT(petss.score/IF( pq.fenzhi != 0, pq.fenzhi, 1)*100,DECIMAL(15,2)) as tmdl from pj_exam_team_subject_question pq " +
                        " inner join pj_exam_team_subject_question_score petss  on pq.id=petss.exam_queaction_id " +
                        " where  pq.is_delete=0 and pq.grade_id="+examQuery.getGradeId()+" and pq.team_id="+examQuery.getTeamId()+" " +
                        " and pq.exam_team_subject_id="+examQuery.getExamId()+" and pq.subject_code='"+examQuery.getSubjectCode()+"' " +
                        " and pq.school_year='"+examQuery.getSchoolYear()+"' and pq.school_trem='"+examQuery.getSchoolTrem()+"' and petss.student_id="+aa.getId()+" group by pq.id ");
                //ExamQuestionScoreVo examQuestionScoreVo = examScoreDao.findById(aa.getId(), examQuestionVoList.get(b).getId(), examQuery.getTeamId(), examQuery.getSchoolYear(), examQuery.getSubjectCode());
                for(int a=0;a<mapList1.size();a++){
                    map.put("data_" + a, mapList1.get(a).get("tmdl"));
                }
                String teacher= (String) basicSQLService.findUnique("select name from pj_team_teacher where subject_code="+examQuery.getSubjectCode()+" and school_year="+examQuery.getSchoolYear()+" and is_delete=0 and team_id="+examQuery.getTeamId());
                map.put("teacherName",teacher);
                mapList.add(map);
        }
        objectMap.put("list",mapList);
        objectMap.put("examQuestionVoList",examQuestionVoList);
        return objectMap;
    }
    /*
    * 个人得分分析导出
    */
    @Override
    public Map<String,Object> findByGrrenScoreDaochu(UserInfo user, ExamQuery examQuery, Page page) {
        Map<String,Object> objectMap=new HashMap<>();
        List<Map<String, Object>> mapList=new ArrayList<>();
        List<Student> studentList=studentDao.findByall(user.getSchoolId(),examQuery.getSchoolYear(),examQuery.getTeamId(),examQuery.getGradeId(),null,page);
        ExamQuestionVo examQuestionVo=new ExamQuestionVo();
        examQuestionVo.setSchoolYear(examQuery.getSchoolYear());
        examQuestionVo.setExamTeamSubjectId(examQuery.getExamId());
        examQuestionVo.setGradeId(examQuery.getGradeId());
        examQuestionVo.setTeamId(examQuery.getTeamId());
        examQuestionVo.setSubjectCode(examQuery.getSubjectCode());
        List<ExamQuestionVo> examQuestionVoList=examQuestionVoDao.findByTop(examQuestionVo);
        for(Student aa:studentList){
            Map<String,Object> map=new HashMap<>();
            map.put("empCode",aa.getEmpCode());
            map.put("stuName",aa.getName());
            StudentScore studentScore=studentScoreDao.findByStudentIdAndExamId(aa.getId(),examQuery.getExamId());
            //查询总分
            if(studentScore!=null){
                map.put("zongfen",studentScore.getScore());
            }else{
                map.put("zongfen",0);
            }
            //计算各题的得分率
            List<Map<String,Object>> mapList1=basicSQLService.find("select CONVERT(petss.score/IF( pq.fenzhi != 0, pq.fenzhi, 1)*100,DECIMAL(15,2)) as tmdl from pj_exam_team_subject_question pq " +
                    " inner join pj_exam_team_subject_question_score petss  on pq.id=petss.exam_queaction_id " +
                    " where  pq.is_delete=0 and pq.grade_id="+examQuery.getGradeId()+" and pq.team_id="+examQuery.getTeamId()+" " +
                    " and pq.exam_team_subject_id="+examQuery.getExamId()+" and pq.subject_code='"+examQuery.getSubjectCode()+"' " +
                    " and pq.school_year='"+examQuery.getSchoolYear()+"' and pq.school_trem='"+examQuery.getSchoolTrem()+"' and petss.student_id="+aa.getId()+" group by pq.id ");
            //ExamQuestionScoreVo examQuestionScoreVo = examScoreDao.findById(aa.getId(), examQuestionVoList.get(b).getId(), examQuery.getTeamId(), examQuery.getSchoolYear(), examQuery.getSubjectCode());
            for(int a=0;a<mapList1.size();a++){
                map.put("data_" + a, mapList1.get(a).get("tmdl")+"%");

            }
            String teacher= (String) basicSQLService.findUnique("select name from pj_team_teacher where subject_code="+examQuery.getSubjectCode()+" and school_year="+examQuery.getSchoolYear()+" and is_delete=0 and team_id="+examQuery.getTeamId());
            map.put("teacherName",teacher);
            mapList.add(map);
            /*//计算各题的得分率
            for(int b=0;b<examQuestionVoList.size();b++) {
                ExamQuestionScoreVo examQuestionScoreVo = examScoreDao.findById(aa.getId(), examQuestionVoList.get(b).getId(), examQuery.getTeamId(), examQuery.getSchoolYear(), examQuery.getSubjectCode());
                if (examQuestionScoreVo != null) {
                    if (examQuestionScoreVo.getScore() != null) {
                        BigDecimal percent = new BigDecimal((double) examQuestionScoreVo.getScore() * 100 / examQuestionVoList.get(b).getFenzhi()).setScale(2, BigDecimal.ROUND_HALF_UP);
                        map.put("data_" + b, percent+"%");
                        if (examQuestionVoList.size() - b == 1) {
                            map.put("teacherName", examQuestionScoreVo.getTeacherName());
                        }
                    }
                }else{
                    map.put("data_" + b, 0);
                    if (examQuestionVoList.size() - b == 1) {
                        map.put("teacherName", null);
                    }
                }
            }*/
        }
        objectMap.put("list",mapList);
        objectMap.put("examQuestionVoList",examQuestionVoList);
        return objectMap;
    }


    /*
    * 学生成绩
    */
    @Override
    public  List<Map<String, Object>> findByscoreStudentScore(UserInfo user, ExamQuery examQuery) {
        List<Map<String, Object>> mapList=new ArrayList<>();
        List<Student> studentList=studentDao.findByall(user.getSchoolId(),examQuery.getSchoolYear(),examQuery.getTeamId(),examQuery.getGradeId(),null,null);
        ExamQuestionVo examQuestionVo=new ExamQuestionVo();
        examQuestionVo.setSchoolYear(examQuery.getSchoolYear());
        examQuestionVo.setSchoolTrem(examQuery.getSchoolTrem());
        examQuestionVo.setGradeId(examQuery.getGradeId());
        examQuestionVo.setTeamId(examQuery.getTeamId());
        examQuestionVo.setSubjectCode(examQuery.getSubjectCode());
        examQuestionVo.setExamName(examQuery.getExamName());
        List<ExamQuestionVo> examQuestionVos=examQuestionVoDao.findByTop(examQuestionVo);
        Integer num=0;
        if(examQuestionVos.size()<=0){
            return  null;
        }
        for(ExamQuestionVo aa:examQuestionVos){
            num+=aa.getFenzhi();
        }
        for(Student aa:studentList){
            Map<String,Object> map=new HashMap<>();
            map.put("examName",examQuestionVos.get(0).getExamName());
            map.put("empCode",aa.getEmpCode());
            map.put("stuName",aa.getName());
            List<StudentScore> studentScore=studentScoreDao.findByYearAndTrem(aa.getId(),examQuery.getSchoolYear(),examQuery.getSchoolTrem(),examQuery.getSubjectCode(),examQuery.getExamType(),examQuery.getGradeId(),examQuery.getTeamId(),examQuery.getExamId());
            //查询总分
            if(studentScore.size()>0){
                double score=0;
                for(StudentScore bb:studentScore){
                    if(bb.getScore()!=null){
                        score+=Double.parseDouble(bb.getScore());
                    }
                }
                BigDecimal percent = new BigDecimal(score * 100 / num).setScale(2, BigDecimal.ROUND_HALF_UP);
                map.put("zongfen",score);
                map.put("baifenbi",percent);
            }else{
                map.put("zongfen",0);
                map.put("baifenbi",0);
            }
            mapList.add(map);
            }
        Collections.sort(mapList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return new Double(o2.get("zongfen").toString()).compareTo(new Double(o1.get("zongfen").toString()));
            }
        });
        return mapList;
    }

    /*
     * 班级题目分析导出
     */
    @Override
    public List<Map<String, Object>> findByTeamScoreDaoChu(UserInfo userInfo, ExamQuery examQuery) {
        String sql="select pq.ordere as tihao,pq.fenzhi," +
                " CONVERT(sum(petss.score)/(pq.fenzhi*(select count(*) from pj_student where is_delete=0 and team_id=2681))*100,DECIMAL(15,2)) as teamBaifenbi," +
                " CONVERT(count(IF(petss.score=pq.fenzhi, 1, NULL ))/(select count(*) from pj_student where is_delete=0 and team_id=2681)*100,DECIMAL(15,2)) as mfl," +
                " CONVERT(count(IF((petss.score/pq.fenzhi*100)>=85, 1, NULL))/(select count(*) from pj_student where is_delete=0 and team_id=2681)*100,DECIMAL(15,2)) as yxl," +
                " CONVERT(count(IF((petss.score/pq.fenzhi*100)>=60, 1, NULL))/(select count(*) from pj_student where is_delete=0 and team_id=2681)*100,DECIMAL(15,2)) as hgl" +
                " from pj_exam_team_subject_question pq  inner join pj_exam_team_subject_question_score petss  on pq.id=petss.exam_queaction_id " +
                " where  pq.is_delete=0 and pq.grade_id="+examQuery.getGradeId()+" and pq.team_id="+examQuery.getTeamId()+" and pq.exam_team_subject_id="+examQuery.getExamId()+
                " and pq.subject_code='"+examQuery.getSubjectCode()+"' and pq.school_year="+examQuery.getSchoolYear()+" and pq.school_trem='"+examQuery.getSchoolTrem()+"'  group by pq.ordere";

       List<Map<String, Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }

    /*
    * 班级题目分析导出
    */
    @Override
    public List<Map<String, Object>> findByTeamScore(UserInfo userInfo, ExamQuery examQuery) {
        String sql="select pq.ordere,pq.fenzhi," +
                " CONVERT(sum(petss.score)/(pq.fenzhi*(select count(*) from pj_student where is_delete=0 and team_id=2681))*100,DECIMAL(15,2)) as bjdl," +
                " CONVERT(count(IF(petss.score=pq.fenzhi, 1, NULL ))/(select count(*) from pj_student where is_delete=0 and team_id=2681)*100,DECIMAL(15,2)) as mfl," +
                " CONVERT(count(IF((petss.score/pq.fenzhi*100)>=85, 1, NULL))/(select count(*) from pj_student where is_delete=0 and team_id=2681)*100,DECIMAL(15,2)) as yxl," +
                " CONVERT(count(IF((petss.score/pq.fenzhi*100)>=60, 1, NULL))/(select count(*) from pj_student where is_delete=0 and team_id=2681)*100,DECIMAL(15,2)) as hgl" +
                " from pj_exam_team_subject_question pq  inner join pj_exam_team_subject_question_score petss  on pq.id=petss.exam_queaction_id " +
                " where  pq.is_delete=0 and pq.grade_id="+examQuery.getGradeId()+" and pq.team_id="+examQuery.getTeamId()+" and pq.exam_team_subject_id="+examQuery.getExamId()+
                " and pq.subject_code='"+examQuery.getSubjectCode()+"' and pq.school_year="+examQuery.getSchoolYear()+" and pq.school_trem='"+examQuery.getSchoolTrem()+"'  group by pq.ordere";

        List<Map<String, Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }




    @Override
    public Map<String, Object>  findByGradeScore(UserInfo userInfo, ExamQuery examQuery) {
        Map<String, Object> maps=new HashMap<>();
        List<Map<String, Object>> mapList=new ArrayList<>();
        List<ExamTeamSubject> examTeamSubjectList=examQuestionVoDao.findByExamTeam(examQuery.getGradeId(),examQuery.getSubjectCode(),examQuery.getSchoolYear(),examQuery.getSchoolTrem(),examQuery.getExamType(),userInfo.getSchoolId(),examQuery.getExamName());
            for(ExamTeamSubject aa:examTeamSubjectList){
                Map<String,Object> map=new HashMap<>();
                map.put("examName",aa.getExamName());
                map.put("teamName",aa.getTeamName());
                //班级人数
                long teamSize=basicSQLService.findUniqueLong("select count(*) from pj_student where team_id="+aa.getTeamId()+" and is_delete=0");
                //任课教师
                String teacherName=(String) basicSQLService.findUnique("select `name` from pj_team_teacher where team_id="+aa.getTeamId()+" and subject_code='"+aa.getSubjectCode()+"' and is_delete=0");
                map.put("teamSize",teamSize);
                map.put("teacher",teacherName);
                //参考人数以及总分
                List<Map<String,Object>> list=basicSQLService.find("SELECT sum(score) as zf, count( IF( score != 0, 1, NULL )) as  ckrs,team_id from pj_student_score  WHERE is_delete=0 and school_id="+userInfo.getSchoolId()+" and school_year="+aa.getSchoolYear()+" and term_code='"+aa.getTerm()+"' and subject_code="+aa.getSubjectCode()+" and team_id="+aa.getTeamId()+ " and exam_team_subject_id="+aa.getId());
                map.put("zongfen",list.get(0).get("zf"));
                map.put("cankao",list.get(0).get("ckrs"));
                //各题的分率
                ExamQuestionVo examQuestionVo=new ExamQuestionVo();
                examQuestionVo.setSchoolYear(examQuery.getSchoolYear());
                examQuestionVo.setExamTeamSubjectId(aa.getId());
                examQuestionVo.setGradeId(examQuery.getGradeId());
                examQuestionVo.setSubjectCode(examQuery.getSubjectCode());
                examQuestionVo.setSchoolTrem(examQuery.getSchoolTrem());
                List<ExamQuestionVo> examQuestionVoList=examQuestionVoDao.findByTop(examQuestionVo);

                for(int b=0;b<examQuestionVoList.size();b++) {
                    Double examQuestionScoreVo = examScoreDao.findByTeamZongfen(examQuestionVoList.get(b).getId(),aa.getTeamId(), examQuery.getSchoolYear(),examQuery.getSchoolTrem(), examQuery.getSubjectCode(),null);
                    if(examQuestionScoreVo!=0){
                        BigDecimal percent = new BigDecimal((double) examQuestionScoreVo * 100 / (examQuestionVoList.get(b).getFenzhi()*Integer.parseInt(list.get(0).get("ckrs").toString()))).setScale(2, BigDecimal.ROUND_HALF_UP);
                        map.put("data_" + b, percent);
                    }else{
                        map.put("data_" + b, 0);
                    }

                }
                mapList.add(map);
            }
        maps.put("list",mapList);
        ExamQuestionVo examQuestionVo=new ExamQuestionVo();
        examQuestionVo.setSchoolYear(examQuery.getSchoolYear());
        examQuestionVo.setExamTeamSubjectId(examTeamSubjectList.get(0).getId());
        examQuestionVo.setGradeId(examQuery.getGradeId());
        examQuestionVo.setSubjectCode(examQuery.getSubjectCode());
        examQuestionVo.setSchoolTrem(examQuery.getSchoolTrem());
        List<ExamQuestionVo> examQuestionVoList=examQuestionVoDao.findByTop(examQuestionVo);
        maps.put("ExamList",examQuestionVoList);
        return maps;
    }

    /*
    * 年级导出
    */
    @Override
    public Map<String, Object>  findByGradeScoreDaochu(UserInfo userInfo, ExamQuery examQuery) {
        Map<String, Object> maps=new HashMap<>();
        List<Map<String, Object>> mapList=new ArrayList<>();
        List<ExamTeamSubject> examTeamSubjectList=examQuestionVoDao.findByExamTeam(examQuery.getGradeId(),examQuery.getSubjectCode(),examQuery.getSchoolYear(),examQuery.getSchoolTrem(),examQuery.getExamType(),userInfo.getSchoolId(),examQuery.getExamName());
        for(ExamTeamSubject aa:examTeamSubjectList){
            Map<String,Object> map=new HashMap<>();
            map.put("examName",aa.getExamName());
            map.put("teamName",aa.getTeamName());
            //班级人数
            long teamSize=basicSQLService.findUniqueLong("select count(*) from pj_student where team_id="+aa.getTeamId()+" and is_delete=0");
            //任课教师
            String teacherName=(String) basicSQLService.findUnique("select `name` from pj_team_teacher where team_id="+aa.getTeamId()+" and subject_code='"+aa.getSubjectCode()+"' and is_delete=0");
            map.put("teamSize",teamSize);
            map.put("teacher",teacherName);
            //参考人数以及总分
            List<Map<String,Object>> list=basicSQLService.find("SELECT sum(score) as zf, count( IF( score != 0, 1, NULL )) as  ckrs,team_id from pj_student_score  WHERE is_delete=0 and school_id="+userInfo.getSchoolId()+" and school_year="+aa.getSchoolYear()+" and term_code='"+aa.getTerm()+"' and subject_code="+aa.getSubjectCode()+" and team_id="+aa.getTeamId() + " and exam_team_subject_id="+aa.getId());
            map.put("zongfen",list.get(0).get("zf"));
            map.put("cankao",list.get(0).get("ckrs"));
            //各题的分率
            ExamQuestionVo examQuestionVo=new ExamQuestionVo();
            examQuestionVo.setSchoolYear(examQuery.getSchoolYear());
            examQuestionVo.setExamTeamSubjectId(aa.getId());
            examQuestionVo.setGradeId(examQuery.getGradeId());
            examQuestionVo.setSubjectCode(examQuery.getSubjectCode());
            examQuestionVo.setSchoolTrem(examQuery.getSchoolTrem());
            List<ExamQuestionVo> examQuestionVoList=examQuestionVoDao.findByTop(examQuestionVo);

            for(int b=0;b<examQuestionVoList.size();b++) {
                Double examQuestionScoreVo = examScoreDao.findByTeamZongfen(examQuestionVoList.get(b).getId(),aa.getTeamId(), examQuery.getSchoolYear(),examQuery.getSchoolTrem(), examQuery.getSubjectCode(),null);
                if(examQuestionScoreVo!=0){
                    BigDecimal percent = new BigDecimal( examQuestionScoreVo * 100 / (examQuestionVoList.get(b).getFenzhi()*Integer.parseInt(list.get(0).get("ckrs").toString()))).setScale(2, BigDecimal.ROUND_HALF_UP);
                    map.put("data_" + b, percent+"%");
                }else{
                    map.put("data_" + b, 0);
                }

            }
            mapList.add(map);
        }
        maps.put("list",mapList);
        ExamQuestionVo examQuestionVo=new ExamQuestionVo();
        examQuestionVo.setSchoolYear(examQuery.getSchoolYear());
        examQuestionVo.setExamTeamSubjectId(examTeamSubjectList.get(0).getId());
        examQuestionVo.setGradeId(examQuery.getGradeId());
        examQuestionVo.setSubjectCode(examQuery.getSubjectCode());
        examQuestionVo.setSchoolTrem(examQuery.getSchoolTrem());
        List<ExamQuestionVo> examQuestionVoList=examQuestionVoDao.findByTop(examQuestionVo);
        maps.put("ExamList",examQuestionVoList);
        return maps;
    }


    @Override
    public List<Map<String, Object>> findByFenShuDuanFenXi(UserInfo userInfo, ExamQuery examQuery) {
        List<Map<String, Object>> mapList=new ArrayList<>();
        List<ExamTeamSubject> examTeamSubjectList=examQuestionVoDao.findByExamTeam(examQuery.getGradeId(),examQuery.getSubjectCode(),examQuery.getSchoolYear(),examQuery.getSchoolTrem(),examQuery.getExamType(),userInfo.getSchoolId(),examQuery.getExamName());
     /*   List<ExamTeamSubject> examTeamSubjectList=examQuestionVoDao.findByExamTeamSubject(userInfo.getSchoolId(),examQuery.getSchoolYear(),examQuery.getSchoolTrem(),examQuery.getExamName(),examQuery.getSubjectCode());
      */  for(ExamTeamSubject aa:examTeamSubjectList) {
            Map<String, Object> map = new HashMap<>();
            map.put("examName", aa.getExamName());
            map.put("teamName", aa.getTeamName());
            //班级人数
            long teamSize = basicSQLService.findUniqueLong("select count(*) from pj_student where team_id=" + aa.getTeamId() + " and is_delete=0");
            //任课教师
            String teacherName = (String) basicSQLService.findUnique("select `name` from pj_team_teacher where team_id=" + aa.getTeamId() + " and subject_code='" + aa.getSubjectCode() + "' and is_delete=0");
            map.put("teamSize", teamSize);

            //参考人数以及总分
            List<Map<String, Object>> list = basicSQLService.find("SELECT sum(score) as zf, count( IF( score != 0, 1, NULL )) as  ckrs,team_id from pj_student_score  WHERE is_delete=0 and school_id=" + userInfo.getSchoolId() + " and school_year=" + aa.getSchoolYear() + " and term_code='" + aa.getTerm() + "' and subject_code=" + aa.getSubjectCode() + " and team_id=" + aa.getTeamId() + " and exam_team_subject_id="+aa.getId());
            map.put("cankao", list.get(0).get("ckrs"));
            map.put("zongfen", list.get(0).get("zf"));
            //平均分
            if(list.get(0).get("zf")!=null){
                BigDecimal svg = new BigDecimal(Double.parseDouble(list.get(0).get("zf").toString())/Integer.parseInt(list.get(0).get("ckrs").toString())).setScale(2, BigDecimal.ROUND_HALF_UP);
                map.put("svg",svg);
            }else{
                map.put("svg",0);
            }
            //所有人的成绩 A++=100、100>A+≥95、95>A≥85、85>B≥75、75>C≥60、60>D（按总分比）
            //参考人数以及总分
            List<Map<String, Object>> scorelist = basicSQLService.find("SELECT score  from pj_student_score  WHERE is_delete=0 and school_id=" + userInfo.getSchoolId() + " and school_year=" + aa.getSchoolYear() + " and term_code='" + aa.getTerm() + "' and subject_code=" + aa.getSubjectCode() + " and team_id=" + aa.getTeamId() + " and exam_team_subject_id="+aa.getId());
            int A1=0;
            int A2=0;
            int A=0;
            int B=0;
            int C=0;
            int D=0;
            if(scorelist.size()>0) {
                for (Map<String, Object> bb : scorelist) {
                    double score = Double.parseDouble(bb.get("score").toString());
                    if (score == 100) {
                        A1++;
                    }
                    if (score < 100 && score >= 95) {
                        A2++;
                    }
                    if (score < 95 && score >= 85) {
                        A++;
                    }
                    if (score < 85 && score >= 75) {
                        B++;
                    }
                    if (score < 75 && score >= 60) {
                        C++;
                    }
                    if (score < 60) {
                        D++;
                    }
                }
            }
            map.put("A1",A2);
            map.put("A2",A1);
            map.put("A",A);
            map.put("B",B);
            map.put("C",C);
            map.put("D",D);
            map.put("teacher", teacherName);
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public int updateExamSubject(Integer examId, Integer zhuangtai) {
        return examQuestionVoDao.updateExamSubject(examId,zhuangtai);
    }


}
