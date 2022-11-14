package platform.szxyzxx.web.performanceAnalysis.controller;


import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.resultsStatistical.service.GradeSubjectScoresService;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grade/subject")
public class GradeSubjectScoresController {

    @Autowired
    private GradeSubjectScoresService service;

    @Autowired
    private BasicSQLService basicSQLService;


    @RequestMapping(value = "/getalls")
    public ModelAndView getalls(){
        ModelAndView mov = new ModelAndView("performanceAnalysis/gradeSubjectScores/gradeSubjectScores");
        return mov;
    }

    @RequestMapping(value = "/getall")
    @ResponseBody
    public StuVo getall(String xn,String xq,String examName, String examUuid, Page page){
        StuVo stuVo=new StuVo();
        if(StringUtils.isEmpty(examUuid)){
            page.setTotalRows(0);
            stuVo.setList(new ArrayList(0));
            stuVo.setPage(page);
            return stuVo;
        }

        List<Map<String,Object>> list=basicSQLService.findByPaging(page," SELECT t.id as teamId, t.`name` as teamName, e.exam_type as examType, e.subject_code as subjectCode, sum( sc.score) zf, count( IF( sc.score != 0, 1, NULL )) ckrs, count( IF ( sc.score != 0 AND sc.score >= 60, 1, NULL )) hgrs, count( IF ( sc.score != 0 AND sc.score < 60, 1, NULL )) bhgrs, count( IF ( sc.score != 0 AND sc.score >= 90, 1, NULL )) jsys, count( IF ( sc.score != 0 AND sc.score >= 80, 1, NULL )) bsys FROM pj_exam_team_subject e left join pj_team t on t.id=e.team_id left JOIN pj_student_score sc ON sc.exam_team_subject_id = e.id and sc.is_delete=0 left join pj_team_teacher tt on tt.team_id=t.id and tt.subject_code=e.subject_code and tt.is_delete=0 WHERE e.uuid='"+examUuid+"' GROUP BY e.id,t.id,t.`name`,e.exam_type,e.subject_code ORDER BY t.team_number asc ");
        List<Map<String,Object>> townScoreList=null;
        if(list.size()>0){
            String examTypeName="期末考试";
            if(list.get(0).get("examType").equals("1")){
                examTypeName="期中考试";
            }
            townScoreList=basicSQLService.find("select * from performance_analysis where xn='"+xn+"' and xq='"+xq+"' and testName='"+examTypeName+"'");
        }

        for (Map<String, Object> map : list) {
            long teamSize=basicSQLService.findUniqueLong("select count(*) from pj_team_student where team_id="+map.get("teamId")+" and is_delete=0");
            String teacherName=(String) basicSQLService.findUnique("select `name` from pj_team_teacher where team_id="+map.get("teamId")+" and subject_code='"+map.get("subjectCode")+"' and is_delete=0");
            map.put("teamSize",teamSize);
            map.put("teacher",teacherName);
            putTownScoreInfo(map,townScoreList);
        }


        stuVo.setPage(page);
        stuVo.setList(list);
        return stuVo;
    }


    @RequestMapping("queryExamName")
    @ResponseBody
    public List queryExamName(String xn,String xq,Integer nj){
        return basicSQLService.find("SELECT DISTINCT exam_name as examName FROM `pj_exam_team_subject` e inner join pj_team t on t.id=e.team_id where e.school_year='"+xn+"' and e.term_code='"+xq+"' and t.grade_id="+nj+" and e.is_delete=0 and e.exam_type in ('1','2')");
    }

    @RequestMapping("queryExamSubject")
    @ResponseBody
    public List queryExamSubject(String xn,String xq,Integer nj,String examName){
        return basicSQLService.find("SELECT e.uuid, s.`name`,s.code FROM `pj_exam_team_subject` e INNER JOIN pj_subject s ON s.`code` = e.subject_code inner join pj_team t on t.id=e.team_id WHERE e.school_year = '"+xn+"' AND e.term_code = '"+xq+"' AND t.grade_id = "+nj+" and e.exam_name='"+examName+"' AND e.is_delete =0 group by s.name ");
    }


    private void putTownScoreInfo(Map<String,Object> scoreInfo,List<Map<String,Object>> townScoreaList){
        String teamName=((String)scoreInfo.get("teamName")).substring(0,3);
        for (Map<String, Object> townScore : townScoreaList) {
            if(teamName.equals(townScore.get("grade"))){
                scoreInfo.put("qzpjf",townScore.get("average"));
                scoreInfo.put("qzhgl",townScore.get("percentPass"));
                scoreInfo.put("qzysl",townScore.get("proficiency"));
                break;
            }
        }
    }


}
