package com.xunyunedu.wareHousing.controller;


import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.wareHousing.service.WareHousingService;
import com.xunyunedu.wareHousing.vo.ShenHe;
import com.xunyunedu.wareHousing.vo.WareHousing;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import com.xunyunedu.wechat.template.WechatMessageTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/WareHousing")
@Api(value = "/WareHousing", description = "仓储发布")
public class WareHousingController {
    @Autowired
    private WareHousingService wareHousingService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @RequestMapping("/findByAll")
    @ApiOperation(value = "仓储列表", httpMethod = "GET")
    public List<WareHousing> findByAll(@RequestParam Integer userId){
        List<WareHousing> wareHousingList=wareHousingService.findByAll(userId);
        return wareHousingList;
    }

    @RequestMapping("/findById")
    @ApiOperation(value = "仓储详情", httpMethod = "GET")
    public WareHousing findById(@RequestParam Integer id) {
        WareHousing wareHousing = wareHousingService.findById(id);
        return wareHousing;
    }
    @RequestMapping("/create")
    @ApiOperation(value = "仓储申请", httpMethod = "POST")
    public ApiResult create(@RequestBody WareHousing wareHousing) {
        Integer num=wareHousingService.create(wareHousing);
        if(num>0){
            //通知
            sendWechatNotice(wareHousing.getShenheId(),"您有一个物资申请待审批");
            return new ApiResult(ResultCode.SUCCESS);
        }else{
            return new ApiResult(ResultCode.CREATE_FAIL);
        }
    }
    @RequestMapping("/updateShenHe")
    @ApiOperation(value = "仓储审批", httpMethod = "GET")
    public ApiResult update(@RequestParam Integer id,
                                      @RequestParam Integer zhuantai,
                                      @RequestParam(value = "liyou",required = false) String liyou,
                                      @RequestParam(value = "guiHuanLiYou",required = false) String guiHuanLiYou,
                                      @RequestParam String clues) {
        WareHousing wareHousing=new WareHousing();
        wareHousing.setId(id);
        wareHousing.setZhuangtai(zhuantai);
        wareHousing.setLiyou(liyou);
        wareHousing.setGuiHuanliyou(guiHuanLiYou);
        Integer num=wareHousingService.update(wareHousing);
        Map<String,String> map=new HashMap<>();
        if(num>0){
            WareHousing wareHousing1=wareHousingService.findById(id);
            if(zhuantai==1){
                //发送微信通知
                sendWechatNotice(wareHousing1.getShenqingren(),clues);
            }if(zhuantai==2){
                //发送微信通知
                sendWechatNotice(wareHousing1.getShenqingren(),clues);
            }if(zhuantai==3){
                ShenHe shenHe=new ShenHe();
                shenHe.setTitle(wareHousing1.getShenqingName()+"的物资归还");
                shenHe.setReceiverId(wareHousing1.getFuzeren());
                shenHe.setApplicantName(wareHousing1.getShenqingName());
                shenHe.setApplyExplain(wareHousing1.getBeizhu());
                shenHe.setDataId(wareHousing1.getId());
                shenHe.setDataIdType("wzgh");
                wareHousingService.createShenhe(shenHe);
                //发送微信通知
                sendWechatNotice(wareHousing1.getFuzeren(),clues);
            }if(zhuantai==4){
                //发送微信通知
                sendWechatNotice(wareHousing1.getShenqingren(),clues);
            }if(zhuantai==5){
                //发送微信通知
                sendWechatNotice(wareHousing1.getShenqingren(),clues);
            }
            return new ApiResult(ResultCode.SUCCESS);
        }else{
            return new ApiResult(ResultCode.CREATE_FAIL);
        }
    }

    private void sendWechatNotice(Integer id,String str){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+id+"  and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核通知",str);
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }
    }

    /*
     * 仓储管理员列表
     */
    @RequestMapping("/findByCangChuYuan")
    @ApiOperation(value = "仓储管理员列表", httpMethod = "GET")
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
}
