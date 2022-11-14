package com.xunyunedu.paper.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.notice.constant.SystemNoticeDataIdType;
import com.xunyunedu.notice.constant.SystemNoticeType;
import com.xunyunedu.notice.pojo.SystemWechatNotice;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import com.xunyunedu.paper.condition.OaPaperCondition;
import com.xunyunedu.paper.param.OaPaperInsertParam;
import com.xunyunedu.paper.pojo.OaPaper;
import com.xunyunedu.paper.service.OaPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批-公文管理
 */
@RestController
@RequestMapping("/oa/paper")
public class OaPaperController {

    @Autowired
    OaPaperService service;
    @Resource
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    /**
     * 分页查询公文
     * @param condition
     * @return
     */
    @PostMapping("/page")
    @Authorization
    ApiResult<PageInfo<OaPaper>> page(@RequestBody PageCondition<OaPaperCondition> condition){

        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.page(condition));
        return apiResult;

    }

    /**
     * 获取公文详情
     * @param id 公文id
     * @param currUserId 当前用户id，用于判断公文是否已读
     * @return
     */
    @GetMapping("/detail")
    @Authorization
    ApiResult<PageInfo<OaPaper>> detail(Integer id, Integer currUserId){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.getDetailAndUpdateRedById(id,currUserId));
        return apiResult;
    }


    /**
     * 发布公文
     * @param param
     * @return
     */
    @PostMapping("/add")
    @Authorization
    public ApiResult add(@RequestBody OaPaperInsertParam param){
        OaPaper paper=service.add(param);
        //发送系统和微信通知
        sendSystemAndWechatNotice(paper);
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        return apiResult;
    }


    private void sendSystemAndWechatNotice(OaPaper paper){
        List notifyUserList=basicSQLService.find("select u.id as user_id,u.open_id from oa_paper_user_read r inner join yh_user u on r.user_id=u.id where r.paper_id="+paper.getId());
        if(notifyUserList!=null && notifyUserList.size()>0) {
            SystemWechatNotice swNotice = new SystemWechatNotice();
            swNotice.setTitle(paper.getTitle());
            swNotice.setContent(paper.getContent());
            swNotice.setDataId(paper.getId().toString());
            swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
            swNotice.setDataIdType(SystemNoticeDataIdType.GW);
            swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("公文通知", paper.getTitle()));
            notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");
        }
    }


}
