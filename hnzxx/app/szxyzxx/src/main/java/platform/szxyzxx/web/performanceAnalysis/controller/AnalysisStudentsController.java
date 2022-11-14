package platform.szxyzxx.web.performanceAnalysis.controller;

import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.resultsStatistical.service.AnalysisStudensService;
import platform.szxyzxx.util.PageUtils;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analysis/students")
public class AnalysisStudentsController {
    @Autowired
    private AnalysisStudensService service;

    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping(value = "/getalls")
    public ModelAndView getalls(){
        ModelAndView mov = new ModelAndView("performanceAnalysis/analysisStudents/analysisStudents");
        return mov;
    }

    @RequestMapping(value = "/getall")
    public StuVo getall(Integer examSubjectId,String examName,String subjectName, String previousQueryId,String  queryId, Page page, HttpSession session) {
        StuVo stuVo=new StuVo();
        if(examSubjectId==null){
            page.setTotalRows(0);
            stuVo.setList(new ArrayList(0));
            stuVo.setPage(page);
            return stuVo;
        }

        if(StringUtils.isNotEmpty(previousQueryId)) {
            session.removeAttribute(previousQueryId);
        }
        //缓存查询结果到Session
        List cache=(List) session.getAttribute(queryId);
        if(cache==null){
            //查询上次考试id
            List<Map<String,Object>> examSubjectInfos=basicSQLService.find("select precise_start_date,team_id,subject_code from pj_exam_team_subject where id="+examSubjectId);
            if(examSubjectInfos.size()==0){
                return null;
            }
            Map<String,Object> examSubjectInfo=examSubjectInfos.get(0);
            Integer teamId=(Integer)examSubjectInfos.get(0).get("team_id");
            String start=new SimpleDateFormat("yyyy-MM-dd").format((Date) examSubjectInfo.get("precise_start_date"));
            Long preExamSubjectId=(Long) basicSQLService.findUnique("select id from pj_exam_team_subject where team_id="+teamId+" and subject_code="+examSubjectInfo.get("subject_code")+" and precise_start_date< '"+start+"' order by precise_start_date desc limit 1");
            //查询这次考试和上次考试的成绩
            List<Map<String,Object>> list=basicSQLService.find("SELECT ts.`name`, sc.score, sc2.score as preScore FROM pj_team_student ts LEFT JOIN pj_student_score sc ON sc.student_id = ts.student_id AND sc.exam_team_subject_id = "+examSubjectId+" AND sc.is_delete = 0 LEFT JOIN pj_student_score sc2 ON sc2.student_id = ts.student_id AND sc2.exam_team_subject_id = "+preExamSubjectId+" AND sc2.is_delete = 0 WHERE ts.team_id = "+teamId+" AND ts.is_delete =0");
            //计算排名，分数一样名次也一样
            setRank(list);
            setExamName(examName,subjectName,list);
            cache=list;
            session.setAttribute(queryId,cache);
        }

        return PageUtils.pagingGetStuVoByList(cache,page);
    }

    @RequestMapping("queryExamName")
    @ResponseBody
    public List queryExamName(String xn,String xq,Integer bj){
        return basicSQLService.find("SELECT DISTINCT exam_name as examName,id FROM `pj_exam_team_subject` where school_year='"+xn+"' and term_code='"+xq+"' and team_id="+bj+" and is_delete=0");
    }

    @RequestMapping("queryExamSubject")
    @ResponseBody
    public List queryExamSubject(String xn,String xq,Integer bj,String examName){
        return basicSQLService.find("SELECT e.id, s.`name`,s.code FROM `pj_exam_team_subject` e INNER JOIN pj_subject s ON s.`code` = e.subject_code WHERE e.school_year = '"+xn+"' AND e.term_code = '"+xq+"' AND e.team_id = "+bj+" and e.exam_name='"+examName+"' AND e.is_delete =0 ");
    }


    private void setRank(List<Map<String,Object>> list){
        for ( int i=0;i<list.size();i++) {
            Map<String, Object> map=list.get(i);
            map.put("rank",computeRank(list,(BigDecimal)map.get("score"),"score"));
            map.put("preRank",computeRank(list,(BigDecimal)map.get("preScore"),"preScore"));
        }
    }

    private String computeRank(List<Map<String,Object>> list, BigDecimal score, String compareKey){
        if(score==null || score.intValue()==0){
            return "-";
        }
        int rank=1;
        for(int i=0;i<list.size();i++){
            Map<String,Object> stuScore=list.get(i);
            if(((BigDecimal)stuScore.get(compareKey)).compareTo(score)>0){
                rank++;
            }
        }
       return rank+"";
    }

    private void setExamName(String examName,String subjectName,List<Map<String,Object>> list){
        for (Map<String, Object> map : list) {
            map.put("examName",examName);
            map.put("subjectName",subjectName);
        }
    }

}
