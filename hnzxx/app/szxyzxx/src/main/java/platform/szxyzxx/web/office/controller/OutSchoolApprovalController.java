package platform.szxyzxx.web.office.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.service.model.OutSchoolActivity;
import platform.education.service.service.OutSchoolActivityApprovalService;
import platform.education.service.service.OutSchoolActivityService;
import platform.education.service.service.OutSchoolActivitySummaryService;
import platform.service.storage.service.FileService;
import platform.szxyzxx.notice.constants.SystemNoticeDataIdType;
import platform.szxyzxx.notice.constants.SystemNoticeType;
import platform.szxyzxx.notice.pojo.SystemWechatNotice;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/office/out/school")
public class OutSchoolApprovalController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static String viewBasePath = "office/out/school";

    @Autowired
    private OutSchoolActivityService outSchoolActivityService;
    @Autowired
    private OutSchoolActivityApprovalService outSchoolActivityApprovalService;
    @Autowired
    private OutSchoolActivitySummaryService outSchoolActivitySummaryService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @Resource
    private FileService fileService;



    @RequestMapping(value = "/approval/index")
    public ModelAndView approval2index(
                                        @CurrentUser UserInfo userInfo,
                                       OutSchoolActivity act,
                                       @ModelAttribute("page") Page page,
                                       @RequestParam(value = "sub", required = false) String sub,
                                       Model model) {
        //if(act.getHandleUserId()!=null && act.getHandleUserId()==-1){
            //act.setUserId(userInfo.getId());
        log.warn("校外userId"+userInfo.getId());
        String sqls="select owner_id FROM pj_school_user where user_id='"+userInfo.getId()+"'";
        List<Map<String,Object>> list=basicSQLService.find(sqls);
        act.setHandleUserId((Integer) list.get(0).get("owner_id"));
        log.warn("校外owner_id"+list.get(0).get("owner_id"));
        //}
        List<OutSchoolActivity> outSchoolActivityAndApprovalVoList = this.outSchoolActivityService.findOutSchoolActivityAndApprovalVoByCondition(act, page, Order.desc("create_date"));
        model.addAttribute("items", outSchoolActivityAndApprovalVoList);
        model.addAttribute("state",act.getState());
        String subPath = "/approval/index";
        if ("list".equals(sub)) {
            subPath = "/approval/list";
        }
        return new ModelAndView(viewBasePath + subPath, model.asMap());
    }

    @ResponseBody
    @RequestMapping(value = "/approval/agreed/{activityId}", method = RequestMethod.POST)
    public ResponseInfomation approval2agreed2id4post(@CurrentUser UserInfo userInfo,
                                                      @PathVariable("activityId") Integer activityId) {
        try {
            String sqls="select owner_id FROM pj_school_user where user_id='"+userInfo.getId()+"'";
            List<Map<String,Object>> list=basicSQLService.find(sqls);
            ResponseInfomation r=this.outSchoolActivityApprovalService.updateOutSchoolActivityApproval(activityId,(Integer) list.get(0).get("owner_id"), 1, null) ? new ResponseInfomation(ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
            sendApprovedResultSystemAndWechatNotice(activityId,"【同意】",outSchoolActivityService.findOutSchoolActivityById(activityId));
            return r;
        } catch (Exception e) {
            this.log.debug(e.getMessage());
            return new ResponseInfomation();
        }
    }

    @RequestMapping(value = "/approval/reject/{activityId}", method = RequestMethod.GET)
    public ModelAndView approval2reject2id4get(@PathVariable("activityId") Integer activityId,
                                               Model model) {
            model.addAttribute("activityId", activityId);
        return new ModelAndView(viewBasePath + "/approval/reject", model.asMap());
    }

    @ResponseBody
    @RequestMapping(value = "/approval/reject/{activityId}", method = RequestMethod.POST)
    public Integer approval2reject2id4post(@CurrentUser UserInfo userInfo,
                                                      @PathVariable("activityId") Integer activityId,
                                           @RequestParam(value = "feedback") String feedback) {
        try {
            String sqls="select owner_id FROM pj_school_user where user_id='"+userInfo.getId()+"'";
            List<Map<String,Object>> list=basicSQLService.find(sqls);
            basicSQLService.update("update at_out_school_activity set state=2,refuse_cause='"+feedback+"'," +
                    "handle_user_id="+list.get(0).get("owner_id")+",modify_date=now() where id="+activityId);
            sendApprovedResultSystemAndWechatNotice(activityId,"【驳回】",outSchoolActivityService.findOutSchoolActivityById(activityId));
            return 0;
        } catch (Exception e) {
            e.getMessage();
            return 1;
        }
    }



    @RequestMapping(value = "/approval/examine/{activityId}", method = RequestMethod.GET)
    public ModelAndView approval2examine2id4get(@PathVariable("activityId") Integer activityId,
                                                Model model) {
        OutSchoolActivity act =  outSchoolActivityService.findOutSchoolActivityById(activityId);
        model.addAttribute("act", act);
        if(act!=null && act.getSummaryImgs()!=null) {
            model.addAttribute("prefix",fileService.getHttpPrefix()+"/"+fileService.getSpaceName());
            model.addAttribute("items", act.getSummaryImgs().split(","));
        }
        return new ModelAndView(viewBasePath + "/approval/examine", model.asMap());
    }

    //发送审批结果通知
    private void sendApprovedResultSystemAndWechatNotice(Integer id,String result,OutSchoolActivity out){
        try {
            List notifyUserList=basicSQLService.find("select id user_id,open_id from yh_user where id="+out.getUserId());
            if(notifyUserList!=null && notifyUserList.size()>0) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("校外活动审批结果");
                swNotice.setContent("校外活动【"+out.getName()+"】审批"+result);
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.ACTIVITY);
                swNotice.setDataIdType(SystemNoticeDataIdType.XWHD);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("校外活动审批结果", "校外活动【"+out.getName()+"】审批"+result));
                notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
