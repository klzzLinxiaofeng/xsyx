package platform.szxyzxx.web.logger.controller;

import framework.generic.dao.Page;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.LoggerPojo;
import platform.szxyzxx.logger.vo.Loggers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/10/31 15:06
 * @Version 1.0
 * 新增德育部分操作日志管理
 */
@RestController
@RequestMapping("/logger")
public class LoggerController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private LoggerService loggerService;

    private Importer<Loggers> importer;

    private Exporter exporter;

    public LoggerController(){
        ExcelImportParam<Loggers> param=new ExcelImportParam<>(Loggers.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter(new ExcelExportParam<>(Loggers.class,"日志列表"));

    }
    /**
     * 首页
     */
    @RequestMapping("/list")
    public ModelAndView findByAll(LoggerPojo loggers,
                                  @ModelAttribute("page") Page page,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  Model model){
        List<Loggers> list=loggerService.findByAll(loggers,page);
        String url="";
        if(sub.equals("list")){
            url="/logger/list";
        }else{
            url="/logger/index";
        }
        model.addAttribute("loggers",list);
        return new ModelAndView(url,model.asMap());
    }
        /*
        * 跳转到导出页面
        */
    @RequestMapping("/daochuView")
    public ModelAndView findDaoChuView(){

        return new ModelAndView("/logger/daochu");
    }
    /**
     * 导出，下载
     */
    @RequestMapping("/daochu")
    public void fingDaoChu(LoggerPojo loggers,
                           HttpServletResponse response) {
        try {
            //page传null不分页
            List<Loggers> list = loggerService.findByAlldaochu(loggers);
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化当前系统日期

            for(Loggers aa:list){
                aa.setCreateTime2(dateFm.format(aa.getCreateTime()));
               if(aa.getType()==1){
                   aa.setTypeName("添加");
               }else if(aa.getType()==2){
                   aa.setTypeName("修改");
               }else{
                   aa.setTypeName("删除");
               }
            }
            Date date=new Date();
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
            Workbook wb = exporter.exportToNew(list);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("日志列表"+sdf2.format(date)+".xls", "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
