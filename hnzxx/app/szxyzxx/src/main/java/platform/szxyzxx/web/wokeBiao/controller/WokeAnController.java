package platform.szxyzxx.web.wokeBiao.controller;


import framework.generic.facility.poi.excel.config.ParseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.service.storage.service.FileService;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wokeBiao.pojo.DaoChuPojo;
import platform.szxyzxx.wokeBiao.pojo.WokeXingQing;
import platform.szxyzxx.wokeBiao.pojo.ZhouQi;
import platform.szxyzxx.wokeBiao.service.ZhouQiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/woke")
public class WokeAnController {
    @Autowired
    private ZhouQiService zhouQiService;
    @Autowired
    private BasicSQLService basicSQLService;
    private Importer<DaoChuPojo> importer;

    private Exporter<DaoChuPojo> exporter;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;
    public WokeAnController(){
        ExcelImportParam<DaoChuPojo> param=new ExcelImportParam<>(DaoChuPojo.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter<>(new ExcelExportParam<>(DaoChuPojo.class,"工作"));

    }

    @RequestMapping("/viewWoke")
    public ModelAndView findByAll(){
        ModelAndView modelAndView=new ModelAndView("/woke/wokeBiao");
        return  modelAndView;
    }
    @RequestMapping("/wokeBiaoAll")
    public List<Map<String,Object>> findByAll(@RequestParam Integer zhouShu,
                                              @RequestParam  String schoolYear,
                                              @RequestParam String schoolTerm,
                                              @CurrentUser UserInfo userInfo){
       ZhouQi zhouQi=zhouQiService.findById(zhouShu,schoolYear,schoolTerm);
        List<Map<String,Object>> list=new ArrayList<>();
        if(zhouQi!=null) {
            if (zhouQi.getId() != null) {
                list = zhouQiService.findByAll(zhouShu, schoolYear, schoolTerm);
            } else {
                ZhouQi zhouQi1 = new ZhouQi();
                zhouQi1.setSchoolYear(schoolYear);
                zhouQi1.setXueqi(schoolTerm);
                zhouQi1.setZhoushu(zhouShu);
                Integer num = zhouQiService.craete(zhouQi1);
                if (num > 0) {
                    list = zhouQiService.findByAll(zhouShu, schoolYear, schoolTerm);
                }
            }
        }else {
            ZhouQi zhouQi1 = new ZhouQi();
            zhouQi1.setSchoolYear(schoolYear);
            zhouQi1.setXueqi(schoolTerm);
            zhouQi1.setZhoushu(zhouShu);
            Integer num = zhouQiService.craete(zhouQi1);
            if (num > 0) {
                list = zhouQiService.findByAll(zhouShu, schoolYear, schoolTerm);
            }
        }
       return list;
    }
    @RequestMapping("/wokeBiaoObject")
    public WokeXingQing findByObject(@RequestParam Integer zhouShu,
                                     @RequestParam  Integer jieshu,
                                     @RequestParam Integer id){
        WokeXingQing wokeXingQing=zhouQiService.findByObject(id,jieshu,zhouShu);
        ZhouQi zhouQi=zhouQiService.findByAllZhouQi(id);
        wokeXingQing.setIsStats(zhouQi.getIsStats());
        return wokeXingQing;
    }

    /*
    * 保存
    */
    @RequestMapping("/updateById")
    public String updateById(WokeXingQing wokeXingQing){
        Integer num=zhouQiService.updateZhouQiXianQing(wokeXingQing);
        if(num>0){
            return "success";
        }
        return "error";
    }

    /*
    * 确认日程
    */
    @RequestMapping("/updateByOk")
    public String updateRiCheng(ZhouQi zhouQi){
        Integer num=zhouQiService.updateZhouQi(zhouQi);
        if(num>0){
            return "success";
        }
        return "error";
    }

    /*
     * 导出
     */
    @RequestMapping("/findByDaochu")
    public String findByDaoChu(@RequestParam String schoolYear,
                               @RequestParam String xueqi,
                               @RequestParam Integer zhoushu,
                               HttpServletResponse response, HttpServletRequest request){
        ZhouQi zhouQi=new ZhouQi();
        zhouQi.setZhoushu(zhoushu);
        zhouQi.setXueqi(xueqi);
        zhouQi.setSchoolYear(schoolYear);
        ZhouQi zhouQi1=zhouQiService.findById(zhouQi.getZhoushu(),zhouQi.getSchoolYear(),zhouQi.getXueqi());
        if(zhouQi1!=null){
            if(zhouQi1.getIsStats()==1){
                List<Object> list = new ArrayList<Object>();
                //查找数据
                List<DaoChuPojo> list2 = zhouQiService.findByDaoChu(zhouQi1.getId());
                ParseConfig config = SzxyExcelTookit.getConfig("ZhouQi");

                StringBuffer excelName = new StringBuffer();
                excelName.append("第"+zhoushu+"周工作表.xls");
                String filename = excelName.toString();
                try {
                    for (DaoChuPojo studentScoreVo : list2) {
                        list.add(studentScoreVo);
                    }
                    SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "success";
            }else{
                return "你输入的周期还未由管理人员确认，请重新选择";
            }
        }else{
            return "你输入的周期还未创建，请重新选择";

        }
    }


    /**
     *  确认是否有权限
     */
    @RequestMapping("/findByRold")
    public String findByRold(@CurrentUser UserInfo userInfo){
        List<Map<String,Object>> mapList2=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id   where 1=1  and yur.user_id="+userInfo.getId());
        for(Map<String,Object> bb:mapList2){
            if(bb.get("name").toString().equals("人事部") || bb.get("code").toString().equals("REN_SHI_BU")){
                return "success";
            }
        }
        return "error";
    }
}
