package platform.szxyzxx.web.screen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.ExamTeamSubjectService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 总务大数据大屏，教务模块展示
 *
 * @author: zy
 * @Date: 2022/9/26 10:06
 * @Description:
 */
@RestController
@RequestMapping("/JiaoWu")
public class JiaoWuController {

    private static final Integer schoolIds;
    static {
        schoolIds = Integer.valueOf(PropertiesUtil.getProperty("System.properties", "schoolId"));
        String fileName = "System.properties";
    }
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private GradeService gradeService;
    /**
     * 考试日程安排
     */
    @Autowired
    @Qualifier("examTeamSubjectService")
    protected ExamTeamSubjectService examTeamSubjectService;

    @RequestMapping("/teamSubjectAvg")
   public ResponseInfomation findByTeanmObject(@RequestParam(value = "schoolYear") String schoolYear,
                                               @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
                                               @RequestParam(value = "type" ,required = false) String type
                                               ){
        //查询所有年级
        List<Grade> gradeList=gradeService.findGradeBySchoolYear(schoolIds,schoolYear);
        List<Map<String,Object>> listrest=new ArrayList<>();
            for(int a=0;a<gradeList.size();a++){
                Map<String,Object> objectMap=new HashMap<>();
                List<String> list=new ArrayList();
                Integer gradeId=gradeList.get(a).getId();
                //获取所有有考试的科目
                String sql="SELECT pets.*,ps.name as subject_name FROM pj_exam_team_subject pets " +
                        " INNER JOIN pj_team pt on pets.team_id = pt.id" +
                        " inner join pj_subject ps on ps.code=pets.subject_code WHERE 1=1 " +
                        " AND pets.is_delete=0 AND pt.is_delete=0  AND pets.school_year='"+schoolYear+"'"+
                        " AND pets.exam_type ='"+type+"' AND pets.term_code ='"+schoolTrem+"'"+
                        " AND pets.school_id ="+schoolIds+" AND pt.grade_id ="+gradeId+
                        "  group by pets.subject_code";
                List<Map<String,Object>> examTeamSubjectList=basicSQLService.find(sql);
                //对应年级所有班级
                List<Map<String,Object>> teamId=basicSQLService.find("select name, 100 as max from pj_team where is_delete=0 and grade_id="+gradeId+" order by team_number asc");
                List<Map<String, Object>> subjectScore=new ArrayList();
                for(Map<String,Object> aa:examTeamSubjectList){
                    Map<String,Object> avgScore=new HashMap<>();
                    avgScore.put("name",aa.get("subject_name"));
                    list.add(aa.get("subject_name").toString());
                    List<Double> avgScoreTeam=examTeamSubjectService.findAvgScoreTeam(schoolIds,schoolYear,schoolTrem,gradeId,aa.get("subject_code").toString(),type);
                    avgScore.put("value",avgScoreTeam);
                    subjectScore.add(avgScore);
                }
                //科目
                objectMap.put("legend",list);
                //班级
                objectMap.put("indicator",teamId);
                //平均分
                objectMap.put("dateValue",subjectScore);
                listrest.add(objectMap);
            }
            return  new ResponseInfomation(listrest, ResponseInfomation.OPERATION_SUC);
   }


   /*
   * 年级成绩趋势
   */
   @RequestMapping("/GradeSubjectAvg")
   public ResponseInfomation findByGradeObject(@RequestParam(value = "schoolYear") String schoolYear,
                                               @RequestParam(value = "schoolTrem") String schoolTrem){
       System.out.println(schoolYear+"++"+schoolTrem+"schoolId"+schoolIds);
       List<Grade> gradeList=gradeService.findGradeBySchoolYear(schoolIds,schoolYear);
       List<Map<String,Object>> listrest=new ArrayList<>();
       System.out.println("ninaji大小"+gradeList.size());
       for(int a=0;a<gradeList.size();a++){
           Map<String,Object> objectMap=new HashMap<>();
           List<String> list=new ArrayList();
           Integer gradeId=gradeList.get(a).getId();
           //获取所有有考试的科目
           String sql="SELECT pets.*,ps.name as subject_name FROM pj_exam_team_subject pets " +
                   " INNER JOIN pj_team pt on pets.team_id = pt.id" +
                   " inner join pj_subject ps on ps.code=pets.subject_code WHERE 1=1 " +
                   " AND pets.is_delete=0 AND pt.is_delete=0  AND pets.school_year='"+schoolYear+"'"+
                   " AND pets.exam_type in (1,2) AND pets.term_code ='"+schoolTrem+"'"+
                   " AND pets.school_id ="+schoolIds+" AND pt.grade_id ="+gradeId+
                   "  group by pets.subject_code";
           List<Map<String,Object>> examTeamSubjectList=basicSQLService.find(sql);
           System.out.println("年级id"+gradeId);
           System.out.println("所有科目"+examTeamSubjectList.size());
           //存放平均分
           List subjectScore=new ArrayList();
           for(Map<String,Object> aa:examTeamSubjectList){
               list.add(aa.get("subject_name").toString());
               List<Double> avgScoreTeam=examTeamSubjectService.findAvgScoreGrade(schoolIds,schoolYear,schoolTrem,gradeId,aa.get("subject_code").toString());
               subjectScore.add(avgScoreTeam);
           }
           //科目
           objectMap.put("legend",list);
           //平均分
           objectMap.put("dateValue",subjectScore);
           listrest.add(objectMap);
       }
       System.out.println("最终大小"+listrest.size());
       return  new ResponseInfomation(listrest, ResponseInfomation.OPERATION_SUC);
       // return new ResponseInfomation(listrest, ResponseInfomation.OPERATION_FAIL);
   }


}
