package platform.szxyzxx.web.ishangkelilu.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.ishangkelilu.pojo.KeTangGongShi;
import platform.szxyzxx.ishangkelilu.service.StudyHabitsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/KeTangGongShi")
public class KeTangGongShiController {
    @Autowired
    private StudyHabitsService studyHabitsService;
    @Autowired
    private BasicSQLService basicSQLService;

    private Importer<KeTangGongShi> importer;

    private Exporter<KeTangGongShi> exporter;

    public KeTangGongShiController(){
        ExcelImportParam<KeTangGongShi> param=new ExcelImportParam<>(KeTangGongShi.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter<>(new ExcelExportParam<>(KeTangGongShi.class,"课堂行为公示列表"));

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
            List<KeTangGongShi> list2 = studyHabitsService.findByKeTangGongShi(gradeId,teamId,schoolYear,schoolTrem,subjectId,startDate,endDate);
            String filename="";
            Date date=new Date();
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
            filename+="行为评价公示"+sdf2.format(date)+".xls";
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
        ModelAndView modelAndView = new ModelAndView("/ishangkelilu/xiguan/gongshidaochu");
        return modelAndView;
    }
}
