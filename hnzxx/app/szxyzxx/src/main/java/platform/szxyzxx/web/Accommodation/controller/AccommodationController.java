package platform.szxyzxx.web.Accommodation.controller;


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
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.accommodation.pojo.Accommodation;
import platform.szxyzxx.accommodation.service.AccommodationService;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


/*
* 四、住宿管理
* autor ZY
* date 2022-04-13
*/
@RestController
@RequestMapping("/accomm/zhusu")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;
    private Importer<Accommodation> importer;

    private Exporter exporter;
    public AccommodationController(){
        ExcelImportParam<Accommodation> param=new ExcelImportParam<>(Accommodation.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter(new ExcelExportParam<>(Accommodation.class,"住房记录列表"));

    }
    /*
    * 住宿登记列表
    */
    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@RequestParam(value = "teacherName", required = false) String teacherName,
                                  @RequestParam(value = "fangshihao", required = false) String fangshihao,
                                  @RequestParam(value = "startDate", required = false) String  startDate,
                                  @RequestParam(value = "endTime", required = false) String endTime,
                                  @ModelAttribute("page") Page page,
                                  @CurrentUser UserInfo userInfo,
                                  @RequestParam(value = "sub", required = false) String sub,
                                  Model model){
        page.setPageSize(20);
        List<Accommodation> list=accommodationService.findByAll(teacherName,fangshihao,startDate,endTime,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/accommodation/list";
        }else{
            batUrl="/accommodation/index";
        }
        model.addAttribute("teacherId",userInfo.getTeacherId());
        model.addAttribute("list",list);
        ModelAndView modelAndView=new ModelAndView(batUrl,model.asMap());
        return modelAndView;
    }

    /*
     * 添加或编辑跳转页面
     */
    @RequestMapping("/createOrUpdate")
    public ModelAndView createOrUpdate(@CurrentUser UserInfo userInfo,
                                        @RequestParam(value = "id", required = false) Integer id,
                                       Model model) {
        String bathUrl;
        if (id == null) {
            bathUrl = "/accommodation/input";
            model.addAttribute("userInfo", userInfo);
        } else {
            Accommodation accommodation = accommodationService.findById(id);
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化当前系统日期
            accommodation.setShangBaoTime(dateFm.format(accommodation.getShangBaoTime2()));
            bathUrl = "/accommodation/xiu";
            List<Map<String,Object>> fujianList=new ArrayList<>();
            List<Map<String,Object>> reshuiList=new ArrayList<>();
            List<Map<String,Object>> lenshuiList=new ArrayList<>();
            List<Map<String,Object>> dianbiaoList=new ArrayList<>();
            //附件
            if(accommodation.getFujianUUID()!=null && !accommodation.getFujianUUID().equals("")){
                String [] asd=accommodation.getFujianUUID().split(",");
                for(int a=0;a<asd.length;a++){
                    Map<String,Object> map=new HashMap<>();
                    FileResult file = fileService.findFileByUUID(asd[a]);
                    if(file != null) {
                        map.put("url",file.getHttpUrl());
                        map.put("fileName",file.getEntityFile().getFileName());
                        map.put("uuid",asd[a]);
                    }
                    fujianList.add(map);
                }
            }
            //冷水
            if(accommodation.getColdPictureUUid()!=null && !accommodation.getColdPictureUUid().equals("")){
                String [] asd=accommodation.getColdPictureUUid().split(",");
                for(int a=0;a<asd.length;a++){
                    Map<String,Object> map=new HashMap<>();
                    FileResult file = fileService.findFileByUUID(asd[a]);
                    if (file != null) {
                        map.put("url",file.getHttpUrl());
                        map.put("fileName",file.getEntityFile().getFileName());
                        map.put("uuid",asd[a]);
                    }
                    lenshuiList.add(map);
                }
            }
            //热水
            if(accommodation.getHotPictureUUid()!=null && !accommodation.getHotPictureUUid().equals("")){
                String [] asd=accommodation.getHotPictureUUid().split(",");
                for(int a=0;a<asd.length;a++){
                    Map<String,Object> map=new HashMap<>();
                    FileResult file = fileService.findFileByUUID(asd[a]);
                    if (file != null) {
                        map.put("url",file.getHttpUrl());
                        map.put("fileName",file.getEntityFile().getFileName());
                        map.put("uuid",asd[a]);
                    }
                    reshuiList.add(map);
                }
            }
            //电表
            if(accommodation.getElectricityUUid()!=null && !accommodation.getElectricityUUid().equals("")){
                String [] asd=accommodation.getElectricityUUid().split(",");
                for(int a=0;a<asd.length;a++){
                    Map<String,Object> map=new HashMap<>();
                    FileResult file = fileService.findFileByUUID(asd[a]);
                    if (file != null) {
                        map.put("url",file.getHttpUrl());
                        map.put("fileName",file.getEntityFile().getFileName());
                        map.put("uuid",asd[a]);
                    }
                    dianbiaoList.add(map);
                }
            }
            accommodation.setFujianMap(fujianList);
            accommodation.setDianbiaoMap(dianbiaoList);
            accommodation.setReshuiMap(reshuiList);
            accommodation.setLenshuiMap(lenshuiList);
            model.addAttribute("accommodation", accommodation);

        }
     return    new ModelAndView(bathUrl, model.asMap());
    }

    /*
     * 详情跳转页面
     */
    @RequestMapping("/xiangqing")
    public ModelAndView findByXiangqing(@CurrentUser UserInfo userInfo,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       Model model) {
        Accommodation  accommodation= accommodationService.findById(id);
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化当前系统日期
        accommodation.setShangBaoTime(dateFm.format(accommodation.getShangBaoTime2()));
        String  bathUrl = "/accommodation/xiangqing";
        List<Map<String,Object>> fujianList=new ArrayList<>();
        List<Map<String,Object>> reshuiList=new ArrayList<>();
        List<Map<String,Object>> lenshuiList=new ArrayList<>();
        List<Map<String,Object>> dianbiaoList=new ArrayList<>();
        //附件
        if(accommodation.getFujianUUID()!=null && !accommodation.getFujianUUID().equals("")){
            String [] asd=accommodation.getFujianUUID().split(",");
            for(int a=0;a<asd.length;a++){
                Map<String,Object> map=new HashMap<>();
                FileResult file = fileService.findFileByUUID(asd[a]);
                if(file != null) {
                    map.put("url",file.getHttpUrl());
                    map.put("fileName",file.getEntityFile().getFileName());
                    map.put("uuid",asd[a]);
                }
                fujianList.add(map);
            }
        }
            //冷水
        if(accommodation.getColdPictureUUid()!=null && !accommodation.getColdPictureUUid().equals("")){
            String [] asd=accommodation.getColdPictureUUid().split(",");
            for(int a=0;a<asd.length;a++){
                Map<String,Object> map=new HashMap<>();
                FileResult file = fileService.findFileByUUID(asd[a]);
                if (file != null) {
                    map.put("url",file.getHttpUrl());
                    map.put("fileName",file.getEntityFile().getFileName());
                    map.put("uuid",asd[a]);
                }
                lenshuiList.add(map);
            }
        }
        //热水
        if(accommodation.getHotPictureUUid()!=null && !accommodation.getHotPictureUUid().equals("")){
            String [] asd=accommodation.getHotPictureUUid().split(",");
            for(int a=0;a<asd.length;a++){
                Map<String,Object> map=new HashMap<>();
                FileResult file = fileService.findFileByUUID(asd[a]);
                if (file != null) {
                    map.put("url",file.getHttpUrl());
                    map.put("fileName",file.getEntityFile().getFileName());
                    map.put("uuid",asd[a]);
                }
                reshuiList.add(map);
            }
        }
        //电表
        if(accommodation.getElectricityUUid()!=null && !accommodation.getElectricityUUid().equals("")){
            String [] asd=accommodation.getElectricityUUid().split(",");
            for(int a=0;a<asd.length;a++){
                Map<String,Object> map=new HashMap<>();
                FileResult file = fileService.findFileByUUID(asd[a]);
                if (file != null) {
                    map.put("url",file.getHttpUrl());
                    map.put("fileName",file.getEntityFile().getFileName());
                    map.put("uuid",asd[a]);
                }
                dianbiaoList.add(map);
            }
        }
        accommodation.setFujianMap(fujianList);
        accommodation.setDianbiaoMap(dianbiaoList);
        accommodation.setReshuiMap(reshuiList);
        accommodation.setLenshuiMap(lenshuiList);
        model.addAttribute("accommodation",accommodation);
        ModelAndView modelAndView = new ModelAndView(bathUrl, model.asMap());
        return modelAndView;
    }


    /*
    *添加住房记录
    */
    @RequestMapping("/addAccommdation")
    public String CreateAccommdationAll(@CurrentUser UserInfo userInfo, Accommodation accommodation) {
            Integer num=accommodationService.create(accommodation);
            if(num>0){
                return "success";
            }
        return "error";
    }
    /*
     *修改住房记录
     */
    @RequestMapping("/updateHomeWoke")
    public String UpdateAccommdationAll(@CurrentUser UserInfo userInfo, Accommodation accommodation) {
        Integer num=accommodationService.update(accommodation);
        if(num>0){
            return "success";
        }
        return "error";
    }

    /*
     *删除住房记录
     */
    @RequestMapping("/deleteAccommdation")
    public String DeteleAccommdation(@CurrentUser UserInfo userInfo,
                                     @RequestParam Integer id) {
        Integer num=accommodationService.updateId(id);
        if(num>0){
            return "success";
        }
        return "error";
    }

    /*
     *导出住宿记录列表
     */
    @RequestMapping("/daochu")
    public void fingDaoChu(@RequestParam(value = "teacherName", required = false) String teacherName,
                           @RequestParam(value = "fangshihao", required = false) String fangshihao,
                           @RequestParam(value = "startDate", required = false) String  startDate,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "sub", required = false) String sub,
                           @ModelAttribute("page") Page page,
                           @CurrentUser UserInfo userInfo,
                           HttpServletResponse response) {
        try {
            //page传null不分页
            List<Accommodation> list=accommodationService.findByAll(teacherName,fangshihao,startDate,endTime,null);
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化当前系统日期
            for(Accommodation aa:list){
                aa.setShangBaoTime(dateFm.format(aa.getShangBaoTime2()));
            }
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
            Workbook wb = exporter.exportToNew(list);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("住房记录列表"+sdf2.format(new Date())+".xls", "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
