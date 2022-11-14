package platform.szxyzxx.web.screening.controller;


import framework.generic.dao.Page;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.screening.service.ScreeningService;
import platform.szxyzxx.screening.vo.Screening;
import platform.szxyzxx.screening.vo.ScreeningDaochu;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/Screening")
public class ScreeningController extends BaseController {
    @Autowired
    private ScreeningService screeningService;
    @Autowired
    private BasicSQLService basicSQLService;

    private Importer<ScreeningDaochu> importer;

    private Exporter<ScreeningDaochu> exporter;
    public ScreeningController() {
        ExcelImportParam<ScreeningDaochu> param = new ExcelImportParam<>(ScreeningDaochu.class);
        param.setStartRowIndex(1);
        importer = new DefaultImporter<>(param);
        exporter = new DefaultExporter<>(new ExcelExportParam<>(ScreeningDaochu.class, "资产排查列表"));

    }
    /*
     * 主页
     */
    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@CurrentUser UserInfo userInfo,
                                  @RequestParam(value = "paichaName", required = false) String paichaName,
                                  @RequestParam(value = "screeningArea", required = false) String screeningArea,
                                  @RequestParam(value = "startTime", required = false) String startTime,
                                  @RequestParam(value = "endTime", required = false) String endTime,
                                  @ModelAttribute("page") Page page,
                                  Model model,
                                  @RequestParam String sub) {
        List<Screening> screeningList  = screeningService.findByAll(paichaName, screeningArea, startTime, endTime, page);
        String batUrl = "";
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("wareHousingList",screeningList);
        List<Map<String,Object>> maps=basicSQLService.find("select * from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id where yur.user_id="+userInfo.getId());
        Boolean flag=false;
        for(Map<String,Object> aa:maps){
            if(aa.get("code").toString().equals("ZICHAN_PAICHAYUAN")){
                flag=true;
            }
        }
        model.addAttribute("flag",flag);
        if (sub.equals("list")) {
            batUrl = "/screening/list";
        } else {
            batUrl = "/screening/index";
        }
        return new ModelAndView(batUrl, model.asMap());
    }

    /*
     * 新增或修改跳转
     */
    @RequestMapping("/createOrUpdate")
    public ModelAndView createOrUpdate(@CurrentUser UserInfo userInfo, Model model,
                                       @RequestParam(value = "id", required = false) Integer id) {
        String batUrl="";
        if (id != null) {
            batUrl="/screening/bianji";
            Screening screening = screeningService.findById(id);
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            screening.setPaichaTime(format.format(screening.getPaichaTime2()));
            List<Map<String,Object>> listw=new ArrayList<>();
            Map<String,Object> map =new HashMap<>();
            if(screening.getFujianUuid()!=null && !screening.getFujianUuid().equals("")){
                String [] fujianarr=screening.getFujianUuid().split(",");
                for(int i=0;i<fujianarr.length;i++){
                    FileResult file = fileService.findFileByUUID(fujianarr[i]);
                    if (file != null) {
                        map.put("url",file.getHttpUrl());
                        map.put("fileName",file.getEntityFile().getFileName());
                        map.put("uuid",fujianarr[i]);
                    }
                    listw.add(map);
                }
                screening.setList(listw);
            }
            model.addAttribute("screening", screening);
        } else {
            batUrl="/screening/input";
            model.addAttribute("userInfo", userInfo);
        }
        return new ModelAndView(batUrl, model.asMap());
    }

    /*
     * 新增
     */
    @RequestMapping("/create")
    public String create(Screening screening) {
        int num = screeningService.create(screening);
        if (num > 0) {
            return "success";
        } else {
            return "error";

        }
    }

    /*
     * 修改
     */
    @RequestMapping("/update")
    public String update(Screening screening) {
        int num = screeningService.update(screening);
        if (num > 0) {
            return "success";
        } else {
            return "error";

        }
    }

    /*
     * 删除
     */
    @RequestMapping("/delete")
    public String updateDelete(@RequestParam Integer id) {
        Screening screening = new Screening();
        screening.setId(id);
        screening.setIsDelete(1);
        int num = screeningService.update(screening);
        if (num > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    /*
     * 详情跳转
     */
    @RequestMapping("/fingById")
    public ModelAndView fingById(@CurrentUser UserInfo userInfo, Model model,
                                 @RequestParam Integer id) {
        if (id != null) {
            Screening screening = screeningService.findById(id);
                List<Map<String,Object>> listw=new ArrayList<>();
                Map<String,Object> map =new HashMap<>();
                if(screening.getFujianUuid()!=null && !screening.getFujianUuid().equals("")){
                    String [] fujianarr=screening.getFujianUuid().split(",");
                    for(int i=0;i<fujianarr.length;i++){
                        FileResult file = fileService.findFileByUUID(fujianarr[i]);
                        if (file != null) {
                            map.put("url",file.getHttpUrl());
                            map.put("fileName",file.getEntityFile().getFileName());
                            map.put("uuid",fujianarr[i]);
                        }
                        listw.add(map);
                    }
                    screening.setList(listw);
                }
            model.addAttribute("screening", screening);
        } else {
            model.addAttribute("userInfo", userInfo);
        }
        return new ModelAndView("/screening/xiangqing", model.asMap());
    }

    /*
     * 导出排查记录
     */
    @RequestMapping("/findByWareHousingExport")
    public void findByWareHousingExport(@RequestParam(value = "paichaName", required = false) String paichaName,
                                        @RequestParam(value = "screeningArea", required = false) String screeningArea,
                                        @RequestParam(value = "startTime", required = false) String startTime,
                                        @RequestParam(value = "endTime", required = false) String endTime,
                                        @RequestParam String url,
                                        HttpServletResponse response) {
        List<Screening> mapList = screeningService.findByDaoAll(paichaName,screeningArea,startTime,endTime);
        List<ScreeningDaochu> screeningDaoList=new ArrayList<>();
        for(Screening aa:mapList){
            ScreeningDaochu screeningDao=new ScreeningDaochu();
            screeningDao.setPaichaUserName(aa.getPaichaUserName());
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            screeningDao.setPaichaTime(format.format(aa.getPaichaTime2()));
            screeningDao.setScreeningArea(aa.getScreeningArea());
            //水电
            if(aa.getWaterElectricity()==0){
                screeningDao.setWaterElectricity("正常");
            }else{
                screeningDao.setWaterElectricity("不正常");
            }
            //安全隐患
            if(aa.getTrouble()==0){
                screeningDao.setTrouble("正常");
            }else{
                screeningDao.setTrouble("不正常");
            }
            //建筑质量
            if(aa.getConstruction()==0){
                screeningDao.setConstruction("正常");
            }else{
                screeningDao.setConstruction("不正常");
            }
            //建筑质量
            if(aa.getFacilities()==0){
                screeningDao.setFacilities("正常");
            }else{
                screeningDao.setFacilities("不正常");
            }
           screeningDao.setBeizhu(aa.getBeizhu());
            screeningDaoList.add(screeningDao);
        }
        Date date = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
        String filename = "资产排查记录" + sdf2.format(date) + ".xls";

        Workbook wb = exporter.exportToNew(screeningDaoList);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    * 排查人员列表
    */
    @RequestMapping("/paiChaRenYuan")
    public List<Map<String,Object>> findByTeacher(@RequestParam(value = "",required = false) String name){
        String sql="select * from pj_teacher pt inner join yh_user_role yur on pt.user_id=yur.user_id inner join yh_role yr on yr.id=yur.role_id where yr.code='ZICHAN_PAICHAYUAN' and pt.is_delete=0 ";
        if(name!=null){
            sql+=" and pt.name like '%"+name+"%'";
        }
        List<Map<String,Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }
}
