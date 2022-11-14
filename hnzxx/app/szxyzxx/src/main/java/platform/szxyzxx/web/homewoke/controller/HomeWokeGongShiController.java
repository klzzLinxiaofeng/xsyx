package platform.szxyzxx.web.homewoke.controller;


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
import platform.szxyzxx.homewoke.pojo.HomeWokeGongShi;
import platform.szxyzxx.homewoke.service.HomeWokeService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/*
* 作业公示导出
**/
@RestController
@RequestMapping("/home/wokeGongShi")
public class HomeWokeGongShiController {
    @Resource
    private BasicSQLService basicSQLService;
    @Autowired
    private HomeWokeService homeWokeService;
    private Importer<HomeWokeGongShi> importer;

    private Exporter exporter;
    public HomeWokeGongShiController(){
        ExcelImportParam<HomeWokeGongShi> param=new ExcelImportParam<>(HomeWokeGongShi.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter(new ExcelExportParam<>(HomeWokeGongShi.class,"作业公示列表"));

    }

    /*
     * 作业公示导出
     */
    @RequestMapping("/findByHomeWoke")
    public void findByHomeWoke(@RequestParam(value = "teamId",required = false) Integer teamId,
                               @RequestParam(value = "gradeId",required = false) Integer gradeId,
                               @RequestParam String schoolYear,
                               @CurrentUser UserInfo userInfo,
                               @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
                               @RequestParam(value = "subjectId",required = false) Integer subjectId,
                               @RequestParam(value = "startTime",required = false) String startTime,
                               @RequestParam(value = "endTime",required = false) String endTime,
                               @RequestParam(value = "isHome",required = false)  Integer isHome,
                               HttpServletResponse response) {
        try {
            //page传null不分页
            List<HomeWokeGongShi> list = homeWokeService.findByGongshi(userInfo.getSchoolId(),schoolYear,schoolTrem, gradeId,teamId, subjectId,startTime,endTime,isHome);
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); //格式化当前系统日期

        /*    for(HomeWokeGongShi aa:list){
                aa.setCreateTimeTwo( dateFm.format(aa.getCreateTime()));
            }*/
            Date date=new Date();
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
            Workbook wb = exporter.exportToNew(list);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("作业公示列表"+sdf2.format(date)+".xls", "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/homeWokeGongshi")
    public ModelAndView findByHomeAll() {
        String bathUrl = "/homeWoke/gongshidaovhu";
        ModelAndView modelAndView = new ModelAndView(bathUrl);
        return modelAndView;
    }
}
