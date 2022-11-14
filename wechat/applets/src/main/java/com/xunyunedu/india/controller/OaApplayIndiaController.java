package com.xunyunedu.india.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.india.condition.OaApplayIndiaCondition;
import com.xunyunedu.india.param.OaApplayIndiaApprovalParam;
import com.xunyunedu.india.param.OaApplayIndiaInsertParam;
import com.xunyunedu.india.pojo.OaApplayIndia;
import com.xunyunedu.india.service.OaApplayIndiaService;
import com.xunyunedu.notice.constant.SystemNoticeDataIdType;
import com.xunyunedu.notice.constant.SystemNoticeType;
import com.xunyunedu.notice.pojo.OaApplyNotice;
import com.xunyunedu.notice.pojo.SystemWechatNotice;
import com.xunyunedu.notice.service.OaApplyNoticeService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批-用章（文印）管理
 * @author edison
 */
@RestController
@RequestMapping("/oa/applay/india")
public class OaApplayIndiaController {

    @Autowired
    OaApplayIndiaService service;
    @Resource
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @Autowired
    private OaApplyNoticeService oaApplyNoticeService;

    /**
     * 分页查询用章列表
     * @param condition
     * @return
     */
    @PostMapping("/page")
    ApiResult<PageInfo<OaApplayIndia>> page(@RequestBody PageCondition<OaApplayIndiaCondition> condition){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.page(condition));
        return apiResult;
    }


    /**
     * 添加用章申請
     * @param param
     * @return
     */
    @PostMapping("/add")
    ApiResult add(@RequestBody OaApplayIndiaInsertParam param){
        OaApplayIndia applayIndia= service.add(param);
        if(applayIndia.getId()!=null) {
            //发送通知
            sendNotApprovedSystemAndWechatNotice(applayIndia.getId(), applayIndia.getApproverId(), applayIndia.getProposerName(), param.getRemark());
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }


    /**
     * 查询用章详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    ApiResult detail(Integer id){
        ApiResult result= ApiResult.of(ResultCode.SUCCESS);
        result.setData(service.getDetailById(id));
        return result;
    }

    /**
     * 审批用章
     * @param param
     * @return
     */
    @PostMapping("/approval")
    ApiResult approval(@RequestBody OaApplayIndiaApprovalParam param){
        String sql="update oa_applay_india set india_status='"+param.getIndiaStatus()+"'";
        if(StringUtils.isNotEmpty(param.getNonTreatmentCause())){
            sql+=",non_treatment_cause='"+param.getNonTreatmentCause()+"'";
        }
        sql+=" where id="+param.getId();
        basicSQLService.update(sql);

        String result="【同意】";
        if(param.getIndiaStatus().equals("1")){
            result="【驳回】";
        }
        sendApprovedResultSystemAndWechatNotice(param.getId(),result,param.getProposerId(),param.getUserId());
        return ApiResult.of(ResultCode.SUCCESS);
    }




    //发送待审批通知
    private void sendNotApprovedSystemAndWechatNotice(Integer id, Integer notifyUserId,String applyUserName,String explain){
        try {
            List notifyUserList=basicSQLService.find("select id user_id,open_id from yh_user where id="+notifyUserId);
            if(notifyUserList!=null && notifyUserList.size()>0) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("用章审批");
                swNotice.setContent("您有一条"+applyUserName+"的用章待审批");
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.YZ);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("用章审批", "您有一条"+applyUserName+"的用章待审批"));
                notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");
                //发送代办事项
                OaApplyNotice oaApplyNotice=new OaApplyNotice();
                oaApplyNotice.setTitle(applyUserName+"的用章申请");
                oaApplyNotice.setReceiverId(notifyUserId);
                oaApplyNotice.setApplicantName(applyUserName);
                oaApplyNotice.setApplyExplain(explain);
                oaApplyNotice.setApplyResult(0);
                oaApplyNotice.setDataIdType(SystemNoticeDataIdType.YZ);
                oaApplyNotice.setDataId(id);
                oaApplyNoticeService.add(oaApplyNotice);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //发送审批结果通知
    private void sendApprovedResultSystemAndWechatNotice(Integer id,String result,Integer applyUserId,Integer handlerUserId){
        try {
            List notifyUserList=basicSQLService.find("select id user_id,open_id from yh_user where id="+applyUserId);
            if(notifyUserList!=null && notifyUserList.size()>0) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("用章审批结果");
                swNotice.setContent("审批："+result);
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.YZ);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("用章审批结果", "审批："+result));
                notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");

                //更新代办
                oaApplyNoticeService.updateApplyResult(handlerUserId,id,result.equals("【同意】")? 1 : 2);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
