package com.xunyunedu.act.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.act.pojo.AtSubstitute;
import com.xunyunedu.act.service.AtSubstituteService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.notice.constant.SystemNoticeDataIdType;
import com.xunyunedu.notice.constant.SystemNoticeType;
import com.xunyunedu.notice.pojo.OaApplyNotice;
import com.xunyunedu.notice.pojo.SystemWechatNotice;
import com.xunyunedu.notice.service.OaApplyNoticeService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.util.ftp.FtpUtils;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 代课
 */
@RestController
@RequestMapping("/act/dk/")
public class AtSubstituteController {

    @Autowired
    private AtSubstituteService service;
    @Autowired
    private FtpUtils ftpUtils;


    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @Autowired
    private OaApplyNoticeService oaApplyNoticeService;

    /**
     * 创建代课申请
     * @param act 代课信息
     * @return 创建结果
     */
    @PostMapping("/add")
    public ApiResult add(@RequestBody AtSubstitute act){
        act.setCreateDate(new Date());
        act.setStatus(0);
        service.create(act);

        sendNotApprovedSystemAndWechatNotice(act.getId(),act.getReceiver(),act.getUserName(),act.getDescription());

        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 分页查询代课
     * @param condition 查询参数
     * @return 代课列表
     */
    @PostMapping("/page")
    public PageInfo<AtSubstitute> pageSelect(@RequestBody PageCondition<AtSubstitute> condition){
        return service.selectList(condition);
    }



    /**
     *  审批代课
     * @param act 代课信息
     * @return 审批结果
     */
    @PostMapping("/update")
    public ApiResult update(@RequestBody AtSubstitute act){
        AtSubstitute at=service.selectByPrimaryKey(act.getId());
        if(at==null){
            ApiResult a=new ApiResult();
            a.setStatus(400);
            a.setMsg("id不存在");
        }
        act.setModifyDate(new Date());
        service.updateByPrimaryKeySelective(act);
        sendApprovedResultSystemAndWechatNotice(act.getId(),act.getStatus()==1 ? "【同意】":"【驳回】",at.getUserId(),at.getReceiver());
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 代课详情
     * @param id
     * @return 代课信息
     */
    @GetMapping("/detail")
    public ApiResult detail(Integer id){
        AtSubstitute at= service.selectByPrimaryKey(id);
        if(at!=null && StringUtils.isNotEmpty(at.getAccessory())){
            at.setAccessory(ftpUtils.getPrefix()+at.getAccessory());
        }
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(at);
        return apiResult;
    }



    /**
     * 发送待审批通知
     * @param id 申请id，前端跳转页面所需
     * @param handleUserId 审批者id
     * @param applyUserName 申请者名字
     * @param explain 申请理由
     */
    private void sendNotApprovedSystemAndWechatNotice(Integer id, Integer handleUserId,String applyUserName,String explain){
        try {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("代课审批");
                swNotice.setContent("您有一条"+applyUserName+"的代课待审批");
                swNotice.setDataId(id.toString());
                swNotice.setDataIdType(SystemNoticeDataIdType.DK);
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("代课审批", "您有一条"+applyUserName+"的代课待审批"));
                notifyService.sendSystemAndWechatNotice(swNotice, handleUserId);
                //发送代办事项
                OaApplyNotice oaApplyNotice=new OaApplyNotice();
                oaApplyNotice.setTitle(applyUserName+"的代课申请");
                oaApplyNotice.setReceiverId(handleUserId);
                oaApplyNotice.setApplicantName(applyUserName);
                oaApplyNotice.setApplyExplain(explain);
                oaApplyNotice.setApplyResult(0);
                oaApplyNotice.setDataIdType(SystemNoticeDataIdType.DK);
                oaApplyNotice.setDataId(id);
                oaApplyNoticeService.add(oaApplyNotice);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送审批结果通知以及更新代办
     * @param id 申请id，前端跳转页面所需
     * @param result 审批结果字符串
     * @param applyUserId 申请者userId
     * @param handlerUserId 审批者userId
     */
    private void sendApprovedResultSystemAndWechatNotice(Integer id,String result,Integer applyUserId,Integer handlerUserId){
        try {
            SystemWechatNotice swNotice = new SystemWechatNotice();
            swNotice.setTitle("代课审批结果");
            swNotice.setContent("审批："+result);
            swNotice.setDataId(id.toString());
            swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
            swNotice.setDataIdType(SystemNoticeDataIdType.DK);
            swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("代课审批结果", "审批："+result));
            notifyService.sendSystemAndWechatNotice(swNotice, applyUserId);

            //更新代办
            oaApplyNoticeService.updateApplyResult(handlerUserId,id,result.equals("【同意】")? 1 : 2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
