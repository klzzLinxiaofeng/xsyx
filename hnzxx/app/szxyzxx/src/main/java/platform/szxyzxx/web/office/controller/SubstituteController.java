package platform.szxyzxx.web.office.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.service.service.SubstituteService;
import platform.education.service.vo.ApprovalVo;
import platform.education.service.vo.ExaminerVo;
import platform.education.service.vo.SubstituteCondition;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.office.SubstituteObj;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/office/substitute")
public class SubstituteController {

    private Importer<SubstituteObj> importer;

    private final static String viewBasePath = "office/substitute";

    private Exporter exporter;
    @Autowired
    private SubstituteService substituteService;

    public SubstituteController(){
        ExcelImportParam<SubstituteObj> param=new ExcelImportParam<>(SubstituteObj.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter(new ExcelExportParam<>(SubstituteObj.class,"代课列表"));
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(@CurrentUser UserInfo userInfo,
                              SubstituteCondition substituteCondition,
                              @ModelAttribute("page") Page page,
                              @RequestParam(value = "sub", required = false) String sub,
                              @RequestParam(value = "dm", required = false) String dm,
                              @RequestParam(value = "status") Integer status,
                              @RequestParam(value = "userName",required = false) String userName,
                              @RequestParam(value = "daikename",required = false) String daikename,
                              @RequestParam(value = "kaishiTime",required = false) String kaishiTime,
                              @RequestParam(value = "kaishiTime2",required = false) String kaishiTime2,
                              Model model) {
        substituteCondition.setStatus(status);// 审批状态
        substituteCondition.setDaiKeRen(daikename);
        substituteCondition.setKaishiTime(kaishiTime);
        substituteCondition.setKaishiTime2(kaishiTime2);
        substituteCondition.setUserName(userName);
        List<ExaminerVo> examinerVoList = this.substituteService.findExaminerVoByCondition(substituteCondition, page, Order.desc("create_date"));
        model.addAttribute("items", examinerVoList);

        List<ApprovalVo> approvalVoList = this.substituteService.findSubstituteGetStatusBySchoolId(userInfo.getSchoolId());
        model.addAttribute("approvalVoList", approvalVoList);

        model.addAttribute("dm", dm);
        String subPath = "/index";
        if ("list".equals(sub)) {
            subPath = "/list";
        }
        return new ModelAndView(viewBasePath + subPath, model.asMap());
    }


    /*
    * 代课已审批导出
    */
    @RequestMapping("/daochu")
    public void findDaoChu(@CurrentUser UserInfo userInfo,
                           @RequestParam(value = "status") Integer status,
                           @RequestParam(value = "userName",required = false) String userName,
                           @RequestParam(value = "daikename",required = false) String daikename,
                           @RequestParam(value = "kaishiTime",required = false) String kaishiTime,
                           @RequestParam(value = "kaishiTime2",required = false) String kaishiTime2,
                           HttpServletResponse response) {
        try {
            //page传null不分页
         /*   List<SubstituteObj> list = homeWokeService.findByAll(userInfo, teamId, gradeId, schoolYear,schoolTrem, subjectId,text,text2,isHome, null);
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); //格式化当前系统日期

            for(SubstituteObj aa:list){
                aa.setCreateTimeTwo( dateFm.format(aa.getCreateTime()));
            }
            Date date=new Date();
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
            Workbook wb = exporter.exportToNew(list);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("作业列表"+sdf2.format(date)+".xls", "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);*/
        }catch (Exception e){

        }/*catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
