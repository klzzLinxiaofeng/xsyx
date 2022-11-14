package platform.szxyzxx.web.aesthetic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.aesthetic.pojo.AestheticPojo;
import platform.szxyzxx.aesthetic.pojo.BaoGaoQuery;
import platform.szxyzxx.aesthetic.service.BaoGaoService;
import platform.szxyzxx.indicators.pojo.IndicatorsStudent;
import platform.szxyzxx.innovation.pojo.PracticeInnovation;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pdf/dowerd")
public class PdfController {
    Logger log = LoggerFactory.getLogger(PdfController.class);
    @Autowired
    private BaoGaoService baoGaoService;
    @RequestMapping(value = "/pdfView")
    public ModelAndView findByAesthetic(@CurrentUser UserInfo userInfo){
        ModelAndView modelAndView=new
                ModelAndView("redirect:/pdf/pdfdaochu");
        return modelAndView;
    }
    /*
    *品格养成报告
    */
 @RequestMapping(value = "/zhibiaoScore")
 public Map<String,Object> findByCharacterAll(@CurrentUser UserInfo userInfo,
                                              @RequestParam String year,
                                              @RequestParam String xq,
                                              @RequestParam Integer gradeId,
                                              @RequestParam Integer teamId,
                                              @RequestParam String studentName){
        BaoGaoQuery baoGaoQuery=zuZhuangg(year,xq,studentName,gradeId,teamId);
        Map<String,Object> map=new HashMap<>();
             map=baoGaoService.findByCharacterAll(userInfo,baoGaoQuery);
        if(map==null){
            Map<String,Object> map2=new HashMap<>();
            map2.put("stats",false);
            return map2;
        }
     map.put("stats",true);
     return map;
 }

    /*
     *学习习惯报告
     */
    @RequestMapping(value = "/studyScore")
    public List<Map<String,Object>> findByAllXueXi(@CurrentUser UserInfo userInfo,
                                                   @RequestParam String year,
                                                   @RequestParam String xq,
                                                   @RequestParam Integer gradeId,
                                                   @RequestParam Integer teamId,
                                                   @RequestParam String studentName){
        BaoGaoQuery baoGaoQuery=zuZhuangg(year,xq,studentName,gradeId,teamId);
        List<Map<String,Object>> map=baoGaoService.findByAllXueXi(userInfo,baoGaoQuery);
       if(map==null){
           List<Map<String,Object>> list=new ArrayList<>();
           Map<String,Object> map2=new HashMap<>();
           map2.put("stats",false);
           list.add(map2);
            return list;
        }
        return map;
    }

    /*
     *体测健康报告
     */
    @RequestMapping(value = "/tizhiScore")
    public IndicatorsStudent findByAllTizhi(@CurrentUser UserInfo userInfo,
                                            @RequestParam String year,
                                            @RequestParam String xq,
                                            @RequestParam Integer gradeId,
                                            @RequestParam Integer teamId,
                                            @RequestParam String studentName){
        BaoGaoQuery baoGaoQuery=zuZhuangg(year,xq,studentName,gradeId,teamId);
        IndicatorsStudent indicatorsStudent =baoGaoService.findByAllTiZhi(userInfo,baoGaoQuery);
        return indicatorsStudent;
    }

    /*
     *实践创新报告
     */
    @RequestMapping(value = "/innovationScore")
    public PracticeInnovation findByAllShijian(@CurrentUser UserInfo userInfo,
                                               @RequestParam String year,
                                               @RequestParam String xq,
                                               @RequestParam Integer gradeId,
                                               @RequestParam Integer teamId,
                                               @RequestParam String studentName){
        BaoGaoQuery baoGaoQuery=zuZhuangg(year,xq,studentName,gradeId,teamId);
        PracticeInnovation practiceInnovation =baoGaoService.findByAllShijian(userInfo,baoGaoQuery);
        return practiceInnovation;
    }
    /*
     *艺术审美报告
     */
    @RequestMapping(value = "/ArtScore")
    public AestheticPojo findByAllArt(@CurrentUser UserInfo userInfo,
                                      @RequestParam String year,
                                      @RequestParam String xq,
                                      @RequestParam Integer gradeId,
                                      @RequestParam Integer teamId,
                                      @RequestParam String studentName){
        BaoGaoQuery baoGaoQuery=zuZhuangg(year,xq,studentName,gradeId,teamId);
        AestheticPojo aestheticPojo =baoGaoService.findByAllAestheticPojo(userInfo,baoGaoQuery);
        return aestheticPojo;
    }

    /*
     *学科素养报告
     */
    @RequestMapping(value = "/subjectScore")
    public List<Map<String,Object>> findByAllSubject(@CurrentUser UserInfo userInfo,
                                                     @RequestParam String year,
                                                     @RequestParam String xq,
                                                     @RequestParam Integer gradeId,
                                                     @RequestParam Integer teamId,
                                                     @RequestParam String studentName,
                                                     @RequestParam Integer subjectId){
        BaoGaoQuery baoGaoQuery=zuZhuangg(year,xq,studentName,gradeId,teamId);
        List<Map<String,Object>> map=baoGaoService.findByAllsubject(userInfo,baoGaoQuery,subjectId);
        if(map==null){
            List<Map<String,Object>> list=new ArrayList<>();
            Map<String,Object> map2=new HashMap<>();
            map2.put("stats",false);
            list.add(map2);
            return list;
        }
        return map;
    }
    /*
    * 获取所有有指标的科目
    *
    * */
    @RequestMapping(value = "/subjectKu")
    public List<Map<String,Object>> findByAllSubjectId(@CurrentUser UserInfo userInfo,
                                                     @RequestParam String year,
                                                     @RequestParam String xq,
                                                     @RequestParam Integer gradeId,
                                                     @RequestParam Integer teamId,
                                                     @RequestParam String studentName){
        BaoGaoQuery baoGaoQuery=zuZhuangg(year,xq,studentName,gradeId,teamId);
        List<Map<String,Object>> map=baoGaoService.findByAllsubjectId(userInfo,baoGaoQuery);
        return map;
    }


public BaoGaoQuery zuZhuangg(String year, String xq, String studentName,Integer gradeId,Integer teamId){
    BaoGaoQuery baoGaoQuery=new BaoGaoQuery();
    baoGaoQuery.setGradeId(gradeId);
    baoGaoQuery.setTeamId(teamId);
    baoGaoQuery.setStudentName(studentName);
    baoGaoQuery.setXn(xq);
    baoGaoQuery.setYear(year);
    return baoGaoQuery;
}
    /*
     * 获取学生年级
     *
     * */
    @RequestMapping(value = "/chnegjifenxi")
    public List<Map<String,Object>> findBychnegjifenxi(@CurrentUser UserInfo userInfo,
                                                       @RequestParam String year,
                                                       @RequestParam String xq,
                                                       @RequestParam Integer gradeId,
                                                       @RequestParam Integer teamId,
                                                       @RequestParam(value = "studentName",required = false) String studentName,
                                                       @RequestParam Integer studentId){
        BaoGaoQuery baoGaoQuery=zuZhuangg(year,xq,studentName,gradeId,teamId);
        List<Map<String,Object>> map=baoGaoService.findByAllstudentIdChengJi(userInfo,baoGaoQuery,studentId);
        return map;
    }

}
