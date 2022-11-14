package platform.szxyzxx.web.wareHousing.controller;


import framework.generic.dao.Page;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.warehousing.service.WareHousingService;
import platform.szxyzxx.warehousing.vo.ShenHe;
import platform.szxyzxx.warehousing.vo.WareHousing;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;
import platform.szxyzxx.wechat.template.WechatMessageTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wareHousing")
public class WareHousingController {
    Logger log = LoggerFactory.getLogger(WareHousingController.class);
    @Autowired
    private WareHousingService wareHousingService;
    @Autowired
    private BasicSQLService basicSQLService;
    private Importer<WareHousing> importer;

    private Exporter<WareHousing> exporter;

    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    public WareHousingController() {
        ExcelImportParam<WareHousing> param = new ExcelImportParam<>(WareHousing.class);
        param.setStartRowIndex(1);
        importer = new DefaultImporter<>(param);
        exporter = new DefaultExporter<>(new ExcelExportParam<>(WareHousing.class, "仓储管理列表"));

    }

    /*
     * 主页
     */
    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@CurrentUser UserInfo userInfo,
                                  @RequestParam(value = "shenqingrenName", required = false) String shenqingrenName,
                                  @RequestParam(value = "type", required = false) Integer type,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "zhuangtai", required = false) Integer zhuangtai,
                                  @RequestParam(value = "startTime", required = false) String startTime,
                                  @RequestParam(value = "endTime", required = false) String endTime,
                                  @ModelAttribute("page") Page page,
                                  Model model,
                                  @RequestParam String sub) {
        List<WareHousing> wareHousingList = wareHousingService.findByAll(shenqingrenName, type, name, zhuangtai, startTime, endTime, page);
        String batUrl = "";
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("wareHousingList", wareHousingList);
        if (sub.equals("list")) {
            batUrl = "/WareHousing/list";
        } else {
            batUrl = "/WareHousing/index";
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
            batUrl="/WareHousing/bianji";
            WareHousing wareHousing = wareHousingService.findById(id);
            model.addAttribute("wareHousing", wareHousing);
        } else {
            batUrl="/WareHousing/input";
            model.addAttribute("userInfo", userInfo);
        }
        return new ModelAndView(batUrl, model.asMap());
    }

    /*
     * 新增
     */
    @RequestMapping("/create")
    public String create(WareHousing wareHousing) {
        int num = wareHousingService.create(wareHousing);
        if (num > 0) {
            sendWechatNotice2(wareHousing.getShenheId());
            return "success";
        } else {
            return "error";

        }
    }

    /*
     * 修改
     */
    @RequestMapping("/update")
    public String update(WareHousing wareHousing) {
        int num = wareHousingService.update(wareHousing);
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
        WareHousing wareHousing = new WareHousing();
        wareHousing.setId(id);
        wareHousing.setIsDelete(1);
        int num = wareHousingService.update(wareHousing);
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
            WareHousing wareHousing = wareHousingService.findById(id);
            model.addAttribute("wareHousing", wareHousing);
        } else {
            model.addAttribute("userInfo", userInfo);
        }
        return new ModelAndView("/WareHousing/xiangqing", model.asMap());
    }

    /*
     * 审批
     */
    @RequestMapping("/updateShenpi")
    public String updateShenpi(@RequestParam Integer id,
                               @RequestParam Integer zhuantai,
                               @RequestParam(value = "str",required = false) String str,
                               @RequestParam(value = "liyou", required = false) String liyou,
                               @RequestParam(value = "guihuanLiyou", required = false) String guihuanLiyou) {
        WareHousing wareHousing = new WareHousing();
        wareHousing.setId(id);
        wareHousing.setZhuangtai(zhuantai);
        wareHousing.setLiyou(liyou);
        wareHousing.setGuiHuanliyou(guihuanLiyou);
        int num = wareHousingService.update(wareHousing);
        WareHousing wareHousing1=wareHousingService.findById(id);
        if(zhuantai==1){
            sendWechatNotice3(wareHousing1.getShenqingren(),str);
        }if(zhuantai==2){
            sendWechatNotice3(wareHousing1.getShenqingren(),str);
        }if(zhuantai==3){
            ShenHe shenHe=new ShenHe();
            shenHe.setTitle(wareHousing1.getShenqingName()+"的物资归还");
            shenHe.setReceiverId(wareHousing1.getFuzeren());
            shenHe.setApplicantName(wareHousing1.getShenqingName());
            shenHe.setApplyExplain(wareHousing1.getBeizhu());
            shenHe.setDataId(wareHousing1.getId());
            shenHe.setDataIdType("wzgh");
            wareHousingService.createShenhe(shenHe);
            sendWechatNotice3(wareHousing1.getFuzeren(),str);
        } if(zhuantai==4){
            sendWechatNotice3(wareHousing1.getShenqingren(),str);
        }if(zhuantai==5){
            sendWechatNotice3(wareHousing1.getShenqingren(),str);
        }


        if (num > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    /*
     * 仓储管理员列表
     */
    @RequestMapping("/findByCangChuYuan")
    public List<Map<String, Object>> findByCangChuYuan(@RequestParam(value = "name", required = false) String name) {
        String sql = "select pt.* from pj_teacher pt inner join  yh_user_role yur on yur.user_id=pt.user_id inner join yh_role yr on yr.id=yur.role_id where yr.code='CANGCHU_GUANLIYUAN'";
        if (name != null) {
            sql += "  and pt.name like '%" + name + "%'  order by pt.id";
        }
        List<Map<String, Object>> map = basicSQLService.find(sql);
        return map;
    }


    /*
     * 仓储审批员列表
     */
    @RequestMapping("/findByShenpiYuan")
    public List<Map<String, Object>> findByShenpiYuan(@RequestParam(value = "name", required = false) String name) {
        String sql = "select pt.* from pj_teacher pt inner join  yh_user_role yur on yur.user_id=pt.user_id inner join yh_role yr on yr.id=yur.role_id where yr.code='CANGCHU_SHENGPIYUAN'";
        if (name != null) {
            sql += "  and pt.name like '%" + name + "%'  order by pt.id";
        }
        List<Map<String, Object>> map = basicSQLService.find(sql);
        return map;
    }


    /*
     * 导出仓储
     */
    @RequestMapping("/findByWareHousingExport")
    public void findByWareHousingExport(@RequestParam(value = "shenqingrenName", required = false) String shenqingrenName,
                                        @RequestParam(value = "type", required = false) Integer type,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "zhuangtai", required = false) Integer zhuangtai,
                                        @RequestParam(value = "startTime", required = false) String startTime,
                                        @RequestParam(value = "endTime", required = false) String endTime,
                                        @RequestParam String url,
                                     HttpServletResponse response) {
        List<WareHousing> mapList = wareHousingService.findByDaoAll(shenqingrenName,type,name,zhuangtai,startTime,endTime);
        Date date = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
           String filename = "仓储记录" + sdf2.format(date) + ".xls";

        Workbook wb = exporter.exportToNew(mapList);
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

    private void sendWechatNotice3(Integer userId,String str){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+userId+" and open_id is not null");
        WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核通知",str);
        notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
    }
    private void sendWechatNotice2(Integer id){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+id+" and open_id is not null");
        WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核通知","您有一个物资申请待审核");
        notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
    }
}
