package com.xunyunedu.team.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.CommonDao;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.teacher.dao.TeacherHomeDao;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.team.dao.ExamTeamDao;
import com.xunyunedu.team.dao.TeamsDao;
import com.xunyunedu.team.pojo.ExamTeamPojo;
import com.xunyunedu.team.pojo.ParamPojo;
import com.xunyunedu.team.pojo.TeamStuScorePojo;
import com.xunyunedu.team.service.ExamTeamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: yhc
 * @Date: 2020/12/21 16:37
 * @Description:
 */
@Service
public class ExamTeamServiceImpl implements ExamTeamService {

    @Autowired
    private ExamTeamDao examTeamDao;

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private TeacherHomeDao teacherHomeDao;

    @Autowired
    private TeamsDao teamsDao;
    @Autowired
    private BasicSQLService basicSQLService;

    /**
     * 获取当前教师教的班级考试成绩--当前学年
     * <p>
     * 班主任可以查看
     *
     * @param pojo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ExamTeamPojo> getExamTeamList(ParamPojo pojo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ExamTeamPojo> list = examTeamDao.read(pojo);
        // 平时考试不需要学校排名 1：统考 0：平时
        String type = pojo.getType();

        if (StringUtils.isNotEmpty(type) && type.equals("1") && CollUtil.isNotEmpty(list)) {
            for (ExamTeamPojo examTeamPojo : list) {
                    List<Map<String,Object>> teamOrderList=basicSQLService.find("SELECT t.id as team_id, avg(ss.score) score FROM pj_student_score ss INNER JOIN pj_exam_team_subject pets ON pets.id = ss.exam_team_subject_id INNER JOIN pj_team t ON t.id = ss.team_id WHERE pets.uuid = '"+examTeamPojo.getUuid()+"' AND ss.school_id = 215 group by t.id ");
                    examTeamPojo.setTeamOrderSize(teamOrderList.size()+"");
                    examTeamPojo.setTeamOrder(findRank("team_id",pojo.getTeamId(),teamOrderList));
//                    Integer order = examTeamDao.getTeamOrder(examTeamPojo.getId(), pojo.getSchoolId(), examTeamPojo.getUuid());
//                    if (order != null) {
//                        examTeamPojo.setTeamOrder(order);
//                    }
            }
        }

        if (CollUtil.isNotEmpty(list)) {
            return new PageInfo<>(list);
        }
        return null;
    }

    /**
     * 获取考试课程下的班级学生成绩列表
     *
     * @param id
     * @param schoolId
     * @return
     */
    @Override
    public List<TeamStuScorePojo> getStudentScoreByTeamId(Integer id, Integer schoolId) {
        return examTeamDao.readStudentScore(id, schoolId);
    }

    /**
     * 获取学生的考试历史记录及其班级排名 学校排名
     *
     * @param paramPojo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getStudentScoreAnalysis(ParamPojo paramPojo, Integer pageNum, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>(2);

        //PageHelper.startPage(pageNum, pageSize);

        if(StringUtils.isEmpty(paramPojo.getTermCode())) {
            paramPojo.setTermCode(basicSQLService.findUnique("select school_term_code from pj_school_term_current where school_id=215").toString());
        }
        List<ExamTeamPojo> list = examTeamDao.readStuScoreInfo(paramPojo);

        // 获取是否需要展示学生的排名-班级排名，学校排名
        Boolean bool = commonDao.getJobControlStatus("STU_SCORE_ORDER");
        map.put("isStuScore", bool);
        map.put("list", new PageInfo<>(list));
        if (bool == null || bool == false) {
            for (ExamTeamPojo examTeamPojo : list) {
                examTeamPojo.setOrder("暂不排名");
                examTeamPojo.setStuSchoolOrder("暂不排名");
            }
            return map;
        }
        if (CollUtil.isNotEmpty(list)) {
            for (ExamTeamPojo pojo : list) {
                String score=pojo.getScore();
                boolean isEmptyScore=StringUtils.isEmpty(score);
                if(isEmptyScore){
                    pojo.setScore("暂无成绩");
                }
                ;
                //班级中的排名
                List<Map<String,Object>> teamRankList=queryRankInfo(pojo.getId(),null);
                String teamRank=findRank("student_id",paramPojo.getStuId(),teamRankList);
                pojo.setOrderSize(teamRankList.size()+"");
                pojo.setOrder(isEmptyScore ? "0":teamRank);
                //年级中的排名
                List<Map<String,Object>> gradeRankList=queryRankInfo(null,pojo.getUuid());
                String gradeRank=findRank("student_id",paramPojo.getStuId(),gradeRankList);
                pojo.setStuSchoolOrder(isEmptyScore ? "0":gradeRank);
                pojo.setStuSchoolOrderSize(gradeRankList.size()+"");
            }
        }
        return map;
    }






    /**
     * 获取当前学生的所有学期参与过考试的学期
     *
     * @param stuId
     * @param schoolId
     * @return
     */
    @Override
    public List<Map<String, String>> getStuTerm(Integer stuId, Integer schoolId) {
        return examTeamDao.getStuTerm(stuId, schoolId);
    }

    /**
     * 获取当前老师教的班级信息
     *
     * @param teacherId
     * @param schoolId
     * @return
     */
    @Override
    public List<Map<Integer, String>> getTeacherClass(Integer teacherId, Integer schoolId) {
        TeacherPojo teacherPojo = new TeacherPojo();
        teacherPojo.setIsClassTeacher(1);
        teacherPojo.setId(teacherId);
        teacherPojo.setSchoolId(schoolId);
        return teacherHomeDao.getTeacherClass(teacherPojo);
    }

    /**
     * 获取当前班级下的科目
     *
     * @param teamId
     * @param schoolId
     * @param type
     * @return
     */
    @Override
    public List<Map<Integer, String>> getTeacherSubject(Integer teamId, Integer schoolId, Integer type) {
        return teamsDao.getTeacherSubject(teamId, schoolId, type);
    }

    /**
     * 获取当前学生参与的考试科目
     *
     * @param stuId
     * @param schoolId
     * @param type
     * @return
     */
    @Override
    public List<Map<Integer, String>> getStuSubject(Integer stuId, Integer schoolId, Integer type) {
        List<Map<Integer, String>> list=teamsDao.getStuSubject(stuId, schoolId, type);
//        if(type==1) {
//            Map map = new HashMap<>();
//            map.put("code", -1);
//            map.put("name", "总分");
//            list.add(map);
//        }
        return list;
    }

    private List<Map<String,Object>> queryRankInfo(Integer etsId,String petsUuid){
        String sql=null;
        if(petsUuid==null){
            sql="SELECT ss.student_id,ss.score FROM pj_student_score ss where ss.exam_team_subject_id="+etsId+" and ss.school_id=215 order by ss.score desc";
        }else{
            //根据数据设计，一场同年级的考试，被拆分为多个班级的考试，每个考试拥有相同的uuid
            sql="select ss.student_id,ss.score from pj_student_score ss inner join pj_exam_team_subject pets on pets.id=ss.exam_team_subject_id where pets.uuid='"+petsUuid+"' and ss.school_id=215 ";
        }
        return basicSQLService.find(sql);
    }


    private String findRank(String key,Object eqVal,List<Map<String,Object>> list){
        BigDecimal score=findScore(key, eqVal, list);
        if(score==null || score.compareTo(new BigDecimal("0"))==0){
            return "0";
        }
        int rank=1;
        for (Map<String, Object> map : list) {
            BigDecimal compareScore=(BigDecimal)map.get("score");
            if(compareScore!=null && compareScore.compareTo(score)>0){
                rank++;
            }
        }
        return rank+"";
    }


    private BigDecimal findScore(String key, Object eqVal, List<Map<String,Object>> list){
        String strVal=eqVal.toString();
        for (Map<String, Object> map : list) {
            if(map.get(key).toString().equals(strVal)){
                return (BigDecimal) map.get("score");
            }
        }
        return null;
    }

}
