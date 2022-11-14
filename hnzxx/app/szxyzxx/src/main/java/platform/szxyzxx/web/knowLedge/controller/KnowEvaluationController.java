package platform.szxyzxx.web.knowLedge.controller;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.knowledge.service.KnowEvaluationSercice;
import platform.szxyzxx.knowledge.service.KnowLedgeService;
import platform.szxyzxx.knowledge.vo.KnowEvaluation;
import platform.szxyzxx.knowledge.vo.KnowLedge;
import platform.szxyzxx.knowledge.vo.PdfVo;
import platform.szxyzxx.knowledge.vo.StudentVo;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/KnowEvaluation")
public class KnowEvaluationController {
    @Autowired
    private KnowEvaluationSercice knowEvaluationSercice;
    @Autowired
    private KnowLedgeService knowLedgeService;
    @Autowired
    private BasicSQLService basicSQLService;


    /*
    * 知识点评价学生列表
    */
    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@RequestParam String sub,
                                        @RequestParam(value = "schoolYear",required = false) String schoolYear,
                                        @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                        @RequestParam(value = "teamId",required = false) Integer teamId,
                                        Model model,
                                        @ModelAttribute("page") Page page){
        List<StudentVo> studentList=knowEvaluationSercice.findByAll(schoolYear,gradeId,teamId,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/zhishidian/knowevaluation/list";
        }else{
            batUrl="/zhishidian/knowevaluation/index";
        }
        model.addAttribute("student",studentList);
        return new ModelAndView(batUrl,model.asMap());
    }
    /*
    * 知识点评价课本列表
    */
    @RequestMapping("/findByAllBook")
    public ModelAndView findByAllBook(@RequestParam String sub,
                                      @RequestParam Integer gradeId,
                                      @RequestParam(value = "subjectId",required = false) Integer subjectId,
                                      @RequestParam Integer studentId,
                                      Model model,
                                      @ModelAttribute("page") Page page){
        List<KnowLedge> list=knowLedgeService.findByAll(null,gradeId,subjectId,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/zhishidian/knowevaluation/booklist";
        }else{
            batUrl="/zhishidian/knowevaluation/bookindex";
        }
        model.addAttribute("list",list);
        model.addAttribute("studentId",studentId);
        model.addAttribute("gradeId",gradeId);
        return new ModelAndView(batUrl,model.asMap());
    }
    /*
    * 知识点评价列表
    */
    @RequestMapping("/findByAllPinjia")
    public ModelAndView findByAllPinjia(@RequestParam String sub,
                                        @RequestParam Integer knowId,
                                        @RequestParam Integer studentId,
                                        Model model,
                                        @ModelAttribute("page") Page page){
        List<KnowEvaluation> studentList=knowEvaluationSercice.findByAllMenu(knowId,studentId,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/zhishidian/knowevaluation/pingjialist";
        }else{
            batUrl="/zhishidian/knowevaluation/pingjiaindex";
        }
        model.addAttribute("studentId",studentId);
        model.addAttribute("student",studentList);
        return new ModelAndView(batUrl,model.asMap());
    }

    /*
    * 评价
    */
    @RequestMapping("/createOrUpdate")
    public String createOrUpdate(KnowEvaluation knowEvaluation){
       Integer num=knowEvaluationSercice.createOrUpdate(knowEvaluation);
       if(num>0){
           return "success";
       }else{
           return "error";
       }
    }

    /*
     *评价跳转
     */
    @RequestMapping("/createView")
    public ModelAndView createView(@RequestParam(value = "id",required = false) Integer id,
                                        @RequestParam Integer knowMenuId,
                                        @RequestParam Integer studentId,
                                        Model model){
        if(id!=null){
            KnowEvaluation knowEvaluation=knowEvaluationSercice.findById(id);
            model.addAttribute("knowEvaluation",knowEvaluation);
        }
        model.addAttribute("studentId",studentId);
        model.addAttribute("knowMenuId",knowMenuId);
        String batUrl="/zhishidian/knowevaluation/pingjia";

        return new ModelAndView(batUrl,model.asMap());
    }


    @RequestMapping("/findByZhiShiPdf")
    List<PdfVo> findByPdf(@RequestParam Integer gradeId,
                          @RequestParam Integer studentId,
                          @RequestParam Integer subjectId){
        List<PdfVo> list=knowEvaluationSercice.findByPdf(gradeId,studentId,subjectId);
        return  list;
    }


    @RequestMapping("/findByStudent")
    List<Map<String,Object>> findByPdf(@RequestParam Integer gradeId,
                                       @RequestParam Integer teamId,
                                       @RequestParam String schoolYear){
        String sql="select ps.* from pj_student ps inner join pj_team_student pts on pts.student_id=ps.id inner join pj_grade pg on pg.id=pts.grade_id " +
                "  where  ps.is_delete=0 and  pts.team_id="+teamId+" and pg.id="+gradeId+" and pg.school_year="+schoolYear+" group by ps.id";
        List<Map<String,Object>> list=basicSQLService.find(sql);
        return  list;
    }
}
