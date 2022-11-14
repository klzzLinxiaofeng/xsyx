package platform.szxyzxx.web.character.controller;


import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.character.pojo.GongShiDaoChu;
import platform.szxyzxx.character.service.EvaluationService;
import platform.szxyzxx.character.service.RecordsService;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/character/cultivation")
public class GongShiController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private RecordsService re;
    private Importer<GongShiDaoChu> importer;

    private Exporter<GongShiDaoChu> exporter;

    public GongShiController(){
        ExcelImportParam<GongShiDaoChu> param=new ExcelImportParam<>(GongShiDaoChu.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter<>(new ExcelExportParam<>(GongShiDaoChu.class,"课间行为公示列表"));

    }

    /*
     * 导出
     */
    @RequestMapping("/daochuWoke")
    public void daochuWoke(
            @RequestParam(value = "teamId",required = false) Integer teamId,
            @RequestParam(value = "gradeId",required = false) Integer gradeId,
            @RequestParam(value = "schoolYear",required = false) String schoolYear,
            @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
            @RequestParam(value = "subjectId",required = false) Integer subjectId,
            @RequestParam(value = "startDate",required = false) String startDate,
            @RequestParam(value = "endDate",required = false) String  endDate,
            HttpServletResponse response, HttpServletRequest request){
        try {
            //page传null不分页
            List<GongShiDaoChu> list2 = null; //studyHabitsService.findByKeTangGongShi(gradeId,teamId,schoolYear,schoolTrem,subjectId,startDate,endDate);
            String filename="";
            Date date=new Date();
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
            filename+="课件行为公示"+sdf2.format(date)+".xls";
            Workbook wb = exporter.exportToNew(list2);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/daochuview")
    public ModelAndView findByStudentZuoWei() {
        ModelAndView modelAndView = new ModelAndView("/character/gongshiDaoc hu");
        return modelAndView;
    }
}
