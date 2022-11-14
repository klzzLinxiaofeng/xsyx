package com.xunyunedu.huiyi.controller;


import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.huiyi.pojo.HuiYi;
import com.xunyunedu.huiyi.service.HuiYiService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.util.ftp.FtpUtils;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import com.xunyunedu.wechat.template.WechatMessageTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/huiyi")
@Api(value = "/huiyi", description = "会议发布")
public class HuiYiController {
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private HuiYiService huiYiService;
    @Autowired
    private FtpUtils ftpUtils;

    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @PostMapping("/add")
    @ApiOperation(value = "会务发布", httpMethod = "POST")
    public ApiResult addRepairContent(@RequestBody HuiYi huiYi) {
        String num=huiYiService.create(huiYi);
        huiYi.setSchoolYear(huiYi.getApplicantName()+"的会议申请");
        if(num.equals("success")){
            huiYiService.createshenhe(huiYi);
            //发送微信通知
            sendWechatNotice(huiYi.getReviewer());
            return new ApiResult(ResultCode.SUCCESS);
        }else{
            return new ApiResult(ResultCode.CREATE_FAIL,num);
        }

    }

    /*
     * 教师列表
     */
    @RequestMapping("/findByTeacher")
    @ApiOperation(value = "教师列表", httpMethod = "GET")
    public List<Map<String,Object>> findByXiangqing(@RequestParam(value = "text",required = false) String text){
        String sql ="select user_id,name from pj_teacher  where  is_delete=0";
        if(text!=null){
            sql+="  and name like '%"+text+"%'  order by id";
        }
        List<Map<String,Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }
    /*
    * 会务列表
    */
    @RequestMapping("/findByHuiWuLieBiao")
    @ApiOperation(value = "会务列表", httpMethod = "GET")
    public List<HuiYi> findByHuiWuLieBiao(@RequestParam(value = "id",required = false) Integer id,
                                                       @RequestParam(value = "name",required = false) String  name){
        List<HuiYi> list=huiYiService.findByAll(id,name);

        for(HuiYi aa:list){
            List<Map<String ,String>> mapList=new ArrayList<>();
            if(aa.getFujianId()!=null){
                String [] str=aa.getFujianId().split(",");
                for(int a=0;a<str.length;a++){
                    Map<String ,String> map=new HashMap<>();
                    EntityFile file = uploaderDao.findFileByUUID(str[a]);
                    if (file != null) {
                        map.put("fileName",file.getFileName());
                        map.put("url",ftpUtils.relativePath2HttpUrl(file));
                    }
                    mapList.add(map);
                }
            }
            aa.setFujian(mapList);
        }
        return list;
    }

    /*
     * 审核详情
     */
    @RequestMapping("/findByHuiYiId")
    @ApiOperation(value = "会议详情", httpMethod = "GET")
    public HuiYi findByChangdi(@RequestParam  Integer id){
        HuiYi  huiYi=huiYiService.findById(id);
        if(huiYi.getFujianId()!=null){
            String [] str=huiYi.getFujianId().split(",");
            List<Map<String ,String>> mapList=new ArrayList<>();
            for(int a=0;a<str.length;a++){
                Map<String ,String> map=new HashMap<>();
                EntityFile file = uploaderDao.findFileByUUID(str[a]);
                if (file != null) {
                    map.put("fileName",file.getFileName());
                    map.put("url",ftpUtils.relativePath2HttpUrl(file));
                }
                mapList.add(map);
            }
            huiYi.setFujian(mapList);
        }
        return huiYi;
    }

    /*
     * 会议审核
     */
    @RequestMapping("/shenhehuiyi")
    @ApiOperation(value = "会议审核", httpMethod = "GET")
    public ApiResult updateShenHe(@RequestParam Integer id,
                               @RequestParam Integer zhuangtai,
                               @RequestParam(value = "text",required = false) String text){
        HuiYi huiYi =huiYiService.findById(id);
       Integer num=huiYiService.updateShenHe(id,zhuangtai,text);
       if(zhuangtai==1){
         if(num>0) {
            //成功发送微信通知
            sendWechatNotice2(huiYi.getApplicant());
            //发送微信通知 会务负责人
            sendWechatNotice3(huiYi.getHuiwufuzeId());
            if (huiYi.getStaff() != null) {
                String[] canhui = huiYi.getStaff().split(",");
                for (int a = 0; a < canhui.length; a++) {
                    List<Map<String, Object>> mapList = basicSQLService.find("select user_id from pj_teacher where is_delete=0 and name='" + canhui[a] + "'");
                    if (mapList.size() > 0) {
                        sendWechatNotice4(Integer.parseInt(mapList.get(0).get("user_id").toString()));
                    }
                }
            }
            return new ApiResult(ResultCode.SUCCESS);
         }else {
            return new ApiResult(ResultCode.CREATE_FAIL);
         }
       }else if(zhuangtai==2){
            //不通过发送微信通知
            sendWechatNotice2(huiYi.getApplicant());
            return new ApiResult(ResultCode.SUCCESS);
        }else{
           return new ApiResult(ResultCode.SUCCESS);
       }
    }


    /*
     * 场地列表
     */
    @RequestMapping("/findByChangdi")
    @ApiOperation(value = "场地列表", httpMethod = "GET")
    public List<Map<String,Object>> findByChangdi(){
        String sql ="select  *  from zy_changdi  where  is_delete=0 ";
        List<Map<String,Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }
    private void sendWechatNotice(Integer id){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+id+"  and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核通知","您有新的会务申请待审核");
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }
    }
    private void sendWechatNotice2(Integer id){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+id+"  and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("申请结果通知","您有申请的会务结果待查看");
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }
    }

    private void sendWechatNotice3(Integer id){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+id+"  and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("会务通知","您有会务场所待布置");
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }
    }

    private void sendWechatNotice4(Integer id){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+id+"  and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("会务通知","您有会务待参加");
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }
    }
    @RequestMapping("/depentAll")
    public List<Map<String,Object>> depentAll(@CurrentUser UserInfo userInfo){
        return basicSQLService.find("select * from pj_department where school_id= 215 and is_delete=0");
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
