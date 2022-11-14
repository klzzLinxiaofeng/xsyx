package com.xunyunedu.leave.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.leave.condition.OaApplayLeaveCondition;
import com.xunyunedu.leave.param.OaApplayLeaveInsertParam;
import com.xunyunedu.leave.pojo.OaApplayLeave;
import com.xunyunedu.leave.service.OaApplayLeaveService;
import com.xunyunedu.notice.constant.SystemNoticeDataIdType;
import com.xunyunedu.notice.constant.SystemNoticeType;
import com.xunyunedu.notice.pojo.OaApplyNotice;
import com.xunyunedu.notice.pojo.SystemWechatNotice;
import com.xunyunedu.notice.service.OaApplyNoticeService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请假模块
 */
@RestController
@RequestMapping("/oa/applay/leave")
public class OaApplayLevaeController {

    @Autowired
    OaApplayLeaveService service;
    @Resource
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;
    @Autowired
    private OaApplyNoticeService oaApplyNoticeService;
    /**
     * 查询分页列表
     * @param condition
     * @return
     */
    @PostMapping("/page")
    ApiResult<PageInfo<OaApplayLeave>> page(@RequestBody PageCondition<OaApplayLeaveCondition> condition){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.page(condition));
        return apiResult;
    }

    /**
     * 获取请假详情
     * @param id 请假id
     * @return
     */
    @GetMapping("/detail")
    ApiResult detail(Integer id){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.getDetailById(id));
        return apiResult;
    }



    /**
     * 添加请假申请
     * @param param
     * @return
     */
    @PostMapping
    ApiResult add(@RequestBody OaApplayLeaveInsertParam param){
        OaApplayLeave applayLeave=service.add(param);
        sendNotApprovedSystemAndWechatNotice(applayLeave,param.getPropserName(),param.getDetail());
         return ApiResult.of(ResultCode.SUCCESS);
    }



    /**
     * 获取所有请假类型
     * @return
     */
    @GetMapping("getLeaveTypes")
    @Authorization
    public List getLeaveTypes(){
        return service.getLeaveTypes();
    }


    //发送待审批通知
    private void sendNotApprovedSystemAndWechatNotice(OaApplayLeave applayLeave,String userName,String explain){
        try {
            List notifyUserList=basicSQLService.find("select u.id user_id,u.open_id from oa_applay_leave_approve_user a left join yh_user u on u.id=a.approve_id where a.leave_id="+applayLeave.getId());
            if(notifyUserList!=null && notifyUserList.size()>0) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("老师请假审批");
                swNotice.setContent("你有一条"+userName+"的请假待审批");
                swNotice.setDataId(applayLeave.getId().toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.JSQJ);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("老师请假审批", "你有一条"+userName+"的请假待审批"));
                notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");

                //发送代办事项
                OaApplyNotice oaApplyNotice=new OaApplyNotice();
                oaApplyNotice.setTitle(userName+"的请假申请");
                oaApplyNotice.setReceiverId(applayLeave.getApproveId());
                oaApplyNotice.setApplicantName(userName);
                oaApplyNotice.setApplyExplain(explain);
                oaApplyNotice.setApplyResult(0);
                oaApplyNotice.setDataIdType(SystemNoticeDataIdType.JSQJ);
                oaApplyNotice.setDataId(applayLeave.getId());
                oaApplyNoticeService.add(oaApplyNotice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
