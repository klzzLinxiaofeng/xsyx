package platform.szxyzxx.web.huojiang.controller;


import framework.generic.dao.Page;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.huojiang.service.ClassLunwenService;
import platform.szxyzxx.huojiang.vo.ClassLunwen;
import platform.szxyzxx.huojiang.vo.DaochuPojo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/huojiangFenXin")
public class ClassLunwenController {
        @Autowired
        private ClassLunwenService classLunwenService;
    @Autowired
    @Qualifier("fileService")
    private FileService fileService;
    @Autowired
    private BasicSQLService basicSQLService;

    private Importer<DaochuPojo> importer;

    private Exporter<DaochuPojo> exporter;

    public ClassLunwenController(){
        ExcelImportParam<DaochuPojo> param=new ExcelImportParam<>(DaochuPojo.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter<>(new ExcelExportParam<>(DaochuPojo.class,"获奖记录列表"));

    }
    /*
    * 查询所有得分
    */
    @RequestMapping("/findByHuoJiangAll")
    public ModelAndView findByHuoJiangAll(@RequestParam(value = "startTime",required = false) String startTime,
                                          @RequestParam(value = "endTime",required = false) String endTime,
                                          @RequestParam(value = "type",required = false) String type,
                                          @RequestParam(value = "teacherName",required = false) String teacherName,
                                          @RequestParam(value = "theme",required = false) String theme,
                                          @RequestParam Integer leixing,
                                          @RequestParam String sub,
                                          @ModelAttribute("page")Page page, Model model){
        List<ClassLunwen> mapList=classLunwenService.findByAll(startTime,endTime,type,teacherName,leixing,theme,page);
        model.addAttribute("list",mapList);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/jiangxiang/classLunwen/list";
        }else{
            batUrl="/jiangxiang/classLunwen/index";
        }
        return new ModelAndView(batUrl,model.asMap());
    }
    /*
     * 查询单个记录详情
     */
    @RequestMapping("/findByhuojiangId")
    public ModelAndView findByhuojiangId(@RequestParam Integer id,
                                @RequestParam Integer teacherId,Model model){
        ClassLunwen mapList=classLunwenService.findById(id,teacherId);
        List<Map<String,Object>> listw=new ArrayList<>();
        if(mapList.getPictureId()!=null){
            String [] fujianarr=mapList.getPictureId().split(",");
            for(int i=0;i<fujianarr.length;i++){
                Map<String,Object> map =new HashMap<>();
                FileResult file = fileService.findFileByUUID(fujianarr[i]);
                if (file != null) {
                    map.put("url",file.getHttpUrl());
                    map.put("uuid",fujianarr[i]);
                }
                listw.add(map);
            }
        }
        mapList.setPictureList(listw);
        model.addAttribute("type",1);
        model.addAttribute("classLunWen",mapList);
        return new ModelAndView("/jiangxiang/classLunwen/xiangqing",model.asMap());
    }
    /*
     * 查询教赛所有得分
     */
    @RequestMapping("/findByJiaoSaiAll")
    public ModelAndView findByJiaoSaiAll(@RequestParam(value = "startTime",required = false) String startTime,
                                         @RequestParam(value = "endTime",required = false) String endTime,
                                         @RequestParam(value = "type",required = false) String type,
                                         @RequestParam(value = "teacherName",required = false) String teacherName,
                                         @RequestParam Integer leixing,
                                         @RequestParam String sub,
                                         @RequestParam(value = "theme",required = false) String theme,
                                         @ModelAttribute("page")Page page, Model model){
        List<ClassLunwen> mapList=classLunwenService.findByAll(startTime,endTime,type,teacherName,leixing,theme,page);
        model.addAttribute("list",mapList);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/jiangxiang/classLunwen/jiaosai/list";
        }else{
            batUrl="/jiangxiang/classLunwen/jiaosai/jiaosai";
        }
        return new ModelAndView(batUrl,model.asMap());
    }
    /*
     * 查询教赛单个记录详情
     */
    @RequestMapping("/findByJiaoSaiId")
    public ModelAndView findByJiaoSaiId(@RequestParam Integer id,
                                 @RequestParam Integer teacherId,Model model){
        ClassLunwen mapList=classLunwenService.findById(id,teacherId);
        List<Map<String,Object>> listw=new ArrayList<>();
        if(mapList.getPictureId()!=null){
            String [] fujianarr=mapList.getPictureId().split(",");
            for(int i=0;i<fujianarr.length;i++){
                Map<String,Object> map =new HashMap<>();
                FileResult file = fileService.findFileByUUID(fujianarr[i]);
                if (file != null) {
                    map.put("url",file.getHttpUrl());
                    map.put("uuid",fujianarr[i]);
                }
                listw.add(map);
            }
        }
        mapList.setPictureList(listw);
        model.addAttribute("classLunWen",mapList);
        model.addAttribute("type",2);
        return new ModelAndView("/jiangxiang/classLunwen/xiangqing",model.asMap());
    }


    /*
     * 导出
     */
    @RequestMapping("/findByHuoJiangExport")
    public void findByHuoJiangExport(@RequestParam(value = "startTime",required = false) String startTime,
                                     @RequestParam(value = "endTime",required = false) String endTime,
                                     @RequestParam(value = "type",required = false) String type,
                                     @RequestParam(value = "teacherName",required = false) String teacherName,
                                     @RequestParam(value = "theme",required = false) String theme,
                                     @RequestParam Integer leixing,
                                     HttpServletResponse response){
        List<DaochuPojo> mapList=classLunwenService.findByDaoAll(startTime,endTime,type,teacherName,leixing,theme);
        String filename="";
        Date date=new Date();
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
        if(leixing==1){
            filename+="获奖记录"+sdf2.format(date)+".xls";
        }else{
            filename+="教赛记录"+sdf2.format(date)+".xls";
        }

        Workbook wb = exporter.exportToNew(mapList);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    /*
    *统计
    */
    @RequestMapping("/findByTongJi")
    public ModelAndView findByTongJi( @RequestParam String sub,
                                      @ModelAttribute("page")Page page,
                                      @CurrentUser UserInfo userInfo,
                                      @RequestParam(value ="teacherName" ,required = false) String teacherName,Model model){
        List<ClassLunwen> list=classLunwenService.findByTongJi(teacherName,userInfo,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/jiangxiang/classLunwen/tongjiList";
        }else{
            batUrl="/jiangxiang/classLunwen/tongji";
        }
        model.addAttribute("tongji",list);
        return new ModelAndView(batUrl,model.asMap());
    }

    /*
    *
    * 查看详情
    */
    @RequestMapping("/findByhuojiangJiLu")
    public List<ClassLunwen> findByhuojiangJiLu(@RequestParam(value ="teacherId" ,required = false) Integer teacherId,Model model){
            List<ClassLunwen> list=classLunwenService.findByhuojiangJiLu(teacherId);
            return list;

    }

    /*
    * 数据大屏接口
    */
    @RequestMapping("/findByNumBigPing")
    public List<ClassLunwen> findByNumBigPing(@CurrentUser UserInfo userInfo){
        List<ClassLunwen> list=classLunwenService.findByTongJi(null,userInfo,null);
        return list.subList(0, 10);

    }
}