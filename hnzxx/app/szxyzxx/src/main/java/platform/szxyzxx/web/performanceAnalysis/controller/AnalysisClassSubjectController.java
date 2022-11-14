package platform.szxyzxx.web.performanceAnalysis.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.resultsStatistical.service.AnalysisClassSubjectService;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/analysis/class")
public class AnalysisClassSubjectController {
    @Autowired
    private AnalysisClassSubjectService subjectService;

    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping(value = "/getalls")
    public String getalls(){
        return "/performanceAnalysis/analysisClassSubject/analysisClassSubject";
    }

    @RequestMapping(value = "/getall")
    @ResponseBody
    public StuVo getall(String xn,String xq,Integer teamId, Integer subjectCode, Page page){
        StuVo stuVo=new StuVo();
        if(teamId==null || subjectCode==null){
            page.setTotalRows(0);
            stuVo.setList(new ArrayList(0));
            stuVo.setPage(page);
            return stuVo;
        }

        long teamSize=basicSQLService.findUniqueLong("select count(*) from pj_team_student where team_id="+teamId+" and is_delete=0");
        String teacherName=(String) basicSQLService.findUnique("select `name` from pj_team_teacher where team_id="+teamId+" and subject_code='"+subjectCode+"' and is_delete=0");

        List<Map<String,Object>> list=basicSQLService.findByPaging(page," select ets2.exam_name,ets.* from( SELECT e.id,sum(sc.score) zf ,count(if(sc.score!=0,1,null)) ckrs ,count(IF(sc.score!=0 and sc.score>=60,1,null)) hgrs ,count(IF(sc.score!=0 and sc.score<60,1,null)) bhgrs ,count(IF(sc.score!=0 and sc.score>=90,1,null)) jsys ,count(IF(sc.score!=0 and sc.score>=80 and sc.score<90,1,null)) bszjs ,count(IF(sc.score!=0 and sc.score>=70 and sc.score<80,1,null)) qszbs ,count(IF(sc.score!=0 and sc.score>=60 and sc.score<70,1,null)) lszqs ,count(IF(sc.score!=0 and sc.score>=50 and sc.score<60,1,null)) wszls FROM pj_exam_team_subject e LEFT JOIN pj_student_score sc ON sc.exam_team_subject_id = e.id and sc.is_delete=0 where e.school_year='"+xn+"' and e.term_code='"+xq+"' and e.team_id="+teamId+" and e.subject_code='"+subjectCode+"' and e.is_delete=0 group by e.id order by e.create_date desc) ets inner join pj_exam_team_subject ets2 on ets.id=ets2.id ");
        for (Map<String, Object> map : list) {
            map.put("teamSize",teamSize);
            map.put("teacher",teacherName);
        }
        stuVo.setPage(page);
        stuVo.setList(list);
        return stuVo;
    }

    @RequestMapping("/findExamSubject")
    @ResponseBody
    public List findExamSubject(String xn,String xq,Integer bj){
        return basicSQLService.find("select sj.`code`,sj.`name` from(SELECT DISTINCT subject_code FROM `pj_exam_team_subject` where school_year='"+xn+"' and term_code='"+xq+"' and team_id="+bj+" and is_delete=0) es inner join pj_subject sj on sj.`code`=es.subject_code");
    }


}
