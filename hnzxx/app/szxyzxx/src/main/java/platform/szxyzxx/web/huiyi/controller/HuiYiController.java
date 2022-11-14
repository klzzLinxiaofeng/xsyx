package platform.szxyzxx.web.huiyi.controller;

import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
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
import platform.szxyzxx.huiyi.service.HuiYiService;
import platform.szxyzxx.huiyi.vo.HuiYi;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;
import platform.szxyzxx.wechat.template.WechatMessageTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/huiyi")
public class HuiYiController {
    @Autowired
    private HuiYiService huiYiService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;
    /*
    * 全部会议
    */
    @RequestMapping("/getByAll")
    public ModelAndView getByAll(@RequestParam(value = "id") Integer id,
                                 @RequestParam(value = "theme",required = false) String theme,
                                 @RequestParam(value = "departmentId",required = false) Integer departmentId,
                                 @RequestParam(value = "applicantName",required = false) String applicantName,
                                 @RequestParam(value = "eventManagerName",required = false) String eventManagerName,
                                 @RequestParam(value = "changdiName",required = false) String changdiName,
                                 @RequestParam(value = "kaishiTime",required = false) String kaishiTime,
                                 @RequestParam(value = "kaishiTime2",required = false) String kaishiTime2,
                                 @RequestParam(value = "sub",required = false) String sub,
                                 @ModelAttribute("page") Page page, Model model,
                                 @CurrentUser UserInfo userInfo){
        model.addAttribute("userInfo",userInfo);

        //id 0与我相关，1全部会议 2 我发布的
        //realName 真实姓名
        if(id==0){
            List<HuiYi> list=huiYiService.findByAll(theme,departmentId,applicantName,eventManagerName,changdiName,kaishiTime,kaishiTime2,null,userInfo.getRealName(),page);
            for(HuiYi aa:list){
                List<Map<String,String>> listw=new ArrayList<>();
                Map<String,String> map =new HashMap<>();
                if(aa.getFujianId()!=null){
                    String [] fujianarr=aa.getFujianId().split(",");
                    for(int i=0;i<fujianarr.length;i++){
                        FileResult file = fileService.findFileByUUID(fujianarr[i]);
                        if (file != null) {
                            map.put("url",file.getHttpUrl());
                            if(file.getEntityFile()!=null){
                                if(file.getEntityFile().getFileName()!=null){
                                    map.put("fileName",file.getEntityFile().getFileName());
                                }
                            }
                            map.put("uuid",fujianarr[i]);
                        }
                        listw.add(map);
                    }
                    aa.setFujian(listw);
                }
            }
            model.addAttribute("list",list);
            model.addAttribute("id",0);
        }else if(id==1){
            List<HuiYi> list=huiYiService.findByAll(theme,departmentId,applicantName,eventManagerName,changdiName,kaishiTime,kaishiTime2,null,null,page);
            for(HuiYi aa:list){
                List<Map<String,String>> listw=new ArrayList<>();
                Map<String,String> map =new HashMap<>();
                if(aa.getFujianId()!=null){
                    String [] fujianarr=aa.getFujianId().split(",");
                    for(int i=0;i<fujianarr.length;i++){
                        FileResult file = fileService.findFileByUUID(fujianarr[i]);
                        if (file != null) {
                            map.put("url",file.getHttpUrl());
                            if(file.getEntityFile()!=null){
                                map.put("fileName",file.getEntityFile().getFileName());
                            }else {
                                map.put("fileName", null);
                            }
                            map.put("uuid",fujianarr[i]);
                        }
                        listw.add(map);
                    }
                    aa.setFujian(listw);
                }
            }
            model.addAttribute("list",list);
            model.addAttribute("id",1);
        }else {
            List<HuiYi> list=huiYiService.findByAll(theme,departmentId,null,eventManagerName,changdiName,kaishiTime,kaishiTime2,userInfo.getId(),null,page);
            for(HuiYi aa:list){
                List<Map<String,String>> listw=new ArrayList<>();
                Map<String,String> map =new HashMap<>();
                if(aa.getFujianId()!=null){
                    String [] fujianarr=aa.getFujianId().split(",");
                    for(int i=0;i<fujianarr.length;i++){
                        FileResult file = fileService.findFileByUUID(fujianarr[i]);
                        if (file != null) {
                            map.put("url",file.getHttpUrl());
                            if(file.getEntityFile()!=null){
                                map.put("fileName",file.getEntityFile().getFileName());
                            }else{
                                map.put("fileName",null);
                            }

                            map.put("uuid",fujianarr[i]);
                        }
                        listw.add(map);
                    }
                    aa.setFujian(listw);
                }
            }
            model.addAttribute("list",list);
            model.addAttribute("id",2);
        }
        String batUrl="";
        if(sub!=null){
            if(sub.equals("list")){
               batUrl="/huiyi/list";
            }else{
                batUrl="/huiyi/index";
            }
        }else{
            batUrl="/huiyi/index";
        }

        return  new ModelAndView(batUrl,model.asMap());
    }
    /*
     * 会议添加修改跳转
     */
    @RequestMapping("/createOrUpdate")
    public ModelAndView create(@CurrentUser UserInfo userInfo, @RequestParam(value = "id",required = false) Integer id, Model model){
        if(id!=null){
            HuiYi huiyi= huiYiService.findById(id);
            List<Map<String,String>> listw=new ArrayList<>();
            Map<String,String> map =new HashMap<>();
            if(huiyi.getFujianId()!=null){
                String [] fujianarr=huiyi.getFujianId().split(",");
                for(int i=0;i<fujianarr.length;i++){
                    FileResult file = fileService.findFileByUUID(fujianarr[i]);
                    if (file != null) {
                        map.put("url",file.getHttpUrl());
                        map.put("fileName",file.getEntityFile().getFileName());
                        map.put("uuid",fujianarr[i]);
                    }
                    listw.add(map);
                }
                huiyi.setFujian(listw);
            }
            model.addAttribute("huiyi",huiyi);
        }else{
            model.addAttribute("user",userInfo);
        }
        return  new ModelAndView("/huiyi/input",model.asMap());
    }

    /*
    * 申请会议
    */
    @RequestMapping("/createHuiYi")
    public String create(@CurrentUser UserInfo userInfo,HuiYi huiYi){
        huiYi.setSchoolYear(userInfo.getSchoolYear());
        huiYi.setSchoolTrem(userInfo.getSchoolTermCode());
        String asd= huiYiService.create(huiYi);
        if("success".equals(asd)){
            huiYi.setSchoolYear(huiYi.getApplicantName()+"的会议申请");
            huiYiService.createshenhe(huiYi);
        }
        return asd;
    }
    /*
     * 编辑会议
     */
    @RequestMapping("/updateHuiYi")
    public String update(HuiYi huiYi){
        return huiYiService.update(huiYi);
    }
    /*
     * 删除会议
     */
    @RequestMapping("/deleteHuiYi")
    public String updateshanchu(@RequestParam Integer id){
        return huiYiService.updateshanchu(id);
    }
    /*
     * 会议详情
     */
    @RequestMapping("/findBiXiangqing")
    public ModelAndView findByXiangqing(@RequestParam(value = "id",required = false) Integer id,Model model){
        if(id!=null){
            HuiYi huiyi= huiYiService.findById(id);
                List<Map<String,String>> listw=new ArrayList<>();

                if(huiyi.getFujianId()!=null){
                    String [] fujianarr=huiyi.getFujianId().split(",");
                    for(int i=0;i<fujianarr.length;i++){
                        Map<String,String> map =new HashMap<>();
                        FileResult file = fileService.findFileByUUID(fujianarr[i]);
                        if (file != null) {
                            map.put("url",file.getHttpUrl());
                            if(file.getEntityFile()!=null){
                                map.put("fileName",file.getEntityFile().getFileName());
                            }
                            map.put("uuid",fujianarr[i]);
                        }
                        listw.add(map);
                    }
                    huiyi.setFujian(listw);
                }

            model.addAttribute("huiyi",huiyi);
        }
        return  new ModelAndView("/huiyi/xiangqing",model.asMap());
    }

    /*
     * 教师列表
     */
    @RequestMapping("/findByTeacher")
    public List<Map<String,Object>> findByXiangqing(@RequestParam(value = "text",required = false) String text){
       String sql ="select user_id,name from pj_teacher  where  is_delete=0";
       if(text!=null){
           sql+="  and name like '%"+text+"%'  order by id";
       }
       List<Map<String,Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }

    /*
     * 会议审核
     */
    @RequestMapping("/shenhehuiyi")
    public String updateShenHe(@RequestParam Integer id,
                               @RequestParam Integer zhuangtai,
                               @RequestParam(value = "text",required = false) String text){
        String num= huiYiService.updateShenHe(id,zhuangtai,text);
        HuiYi huiYi=huiYiService.findById(id);
        if(num.equals("success")){
            if(zhuangtai==1){
                sendWechatNotice2(huiYi.getApplicant(),zhuangtai);
                sendWechatNotice3(huiYi.getHuiwufuzeId());
                sendWechatNotice3(huiYi.getEventManager());
                if(huiYi.getStaff()!=null){
                    String [] canhui=huiYi.getStaff().split(",");
                    for(int a=0;a<canhui.length;a++){
                        List<Map<String,Object>> mapList=basicSQLService.find("select user_id from pj_teacher where is_delete=0 and name='"+canhui[a]+"'");
                        if(mapList.size()>0){
                            sendWechatNotice3(Integer.parseInt(mapList.get(0).get("user_id").toString()));
                        }
                    }
                }
            }else if(zhuangtai==2){
                sendWechatNotice2(huiYi.getApplicant(),zhuangtai);
            }
            return "success";
        }else{
            return "shibai";
        }
    }

    /*
     * 场地列表
     */
    @RequestMapping("/findByChangdi")
    public List<Map<String,Object>> findByChangdi(){
        String sql ="select  *  from zy_changdi  where  is_delete=0 ";
        List<Map<String,Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }


    /*
     * 导出
     */
    @RequestMapping("/daochuHuiYi")
    public String daochuWoke(@CurrentUser UserInfo userInfo,
                             @RequestParam Integer id,
                             @RequestParam(value = "theme",required = false) String theme,
                             @RequestParam(value = "departmentId",required = false) Integer departmentId,
                             @RequestParam(value = "applicantName",required = false) String applicantName,
                             @RequestParam(value = "eventManagerName",required = false) String eventManagerName,
                             @RequestParam(value = "changdiName",required = false) String changdiName,
                             @RequestParam(value = "kaishiTime",required = false) String kaishiTime,
                             @RequestParam(value = "kaishiTime2",required = false) String kaishiTime2,
                             HttpServletResponse response, HttpServletRequest request){
        List<Object> list = new ArrayList();
        List<HuiYi> list2 =new ArrayList<>();
        //id 0与我相关，1全部会议 2 我发布的
        //realName 真实姓名
        if(id==0){
             list2=huiYiService.findByAllDaoChu(theme,departmentId,applicantName,eventManagerName,changdiName,kaishiTime,kaishiTime2,null,userInfo.getRealName());

        }else if(id==1){
             list2=huiYiService.findByAllDaoChu(theme,departmentId,applicantName,eventManagerName,changdiName,kaishiTime,kaishiTime2,null,null);

        }else {
             list2=huiYiService.findByAllDaoChu(theme,departmentId,null,eventManagerName,changdiName,kaishiTime,kaishiTime2,userInfo.getId(),null);

        }
        ParseConfig config = SzxyExcelTookit.getConfig("HuiYiDaoChu");
        StringBuffer excelName = new StringBuffer();
        Date date=new Date();
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
        excelName.append("会议列表"+sdf2.format(date)+".xls");
        String filename = excelName.toString();
        try {
            for (HuiYi huiyi : list2) {
                list.add(huiyi);
            }
            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "success";
    }
    private void sendWechatNotice2(Integer userId,Integer zhuangtai){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+userId+" and open_id is not null");
        if(zhuangtai==1){
            if(notifyUserList!=null && notifyUserList.size()>0) {
                WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核结果通知","您有一个会议申请已通过");
                notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
            }
        }else{
            if(notifyUserList!=null && notifyUserList.size()>0) {
                WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核结果通知","您有一个会议申请未通过");
                notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
            }
        }
    }
    private void sendWechatNotice3(Integer userId){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+userId+" and open_id is not null");
        WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("会议通知","您有一个会议待参加");
        notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
    }



    /*
     * 会议管理员列表
     */
    @RequestMapping("/findByHuiYiGuanLiYuan")
    public List<Map<String, Object>> findByCangChuYuan(@RequestParam(value = "name", required = false) String name) {
        String sql = "select pt.* from pj_teacher pt inner join  yh_user_role yur on yur.user_id=pt.user_id inner join yh_role yr on yr.id=yur.role_id where yr.code='HUIYI_GUANLI'";
        if (name != null) {
            sql += "  and pt.name like '%" + name + "%'  order by pt.id";
        }
        List<Map<String, Object>> map = basicSQLService.find(sql);
        return map;
    }


    /*
     * 会议审批员列表
     */
    @RequestMapping("/findByHuiYiShenHeYuan")
    public List<Map<String, Object>> findByShenpiYuan(@RequestParam(value = "name", required = false) String name) {
        String sql = "select pt.* from pj_teacher pt inner join  yh_user_role yur on yur.user_id=pt.user_id inner join yh_role yr on yr.id=yur.role_id where yr.code='HUIYI_SHENHEYUAN'";
        if (name != null) {
            sql += "  and pt.name like '%" + name + "%'  order by pt.id";
        }
        List<Map<String, Object>> map = basicSQLService.find(sql);
        return map;
    }


}
