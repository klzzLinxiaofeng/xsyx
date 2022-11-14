package com.xunyunedu.syllabus.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.notice.constant.SystemNoticeDataIdType;
import com.xunyunedu.notice.constant.SystemNoticeType;
import com.xunyunedu.notice.pojo.OaApplyNotice;
import com.xunyunedu.notice.pojo.SystemWechatNotice;
import com.xunyunedu.notice.service.OaApplyNoticeService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.syllabus.param.ApprobalParam;
import com.xunyunedu.syllabus.pojo.AdjustClassPojo;
import com.xunyunedu.syllabus.service.AdjustClassService;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.teacher.service.TeacherHomeService;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 调课申请表(OaAdjustClass)表控制层
 *
 * @author sjz
 * @since 2021-03-30 14:20:30
 */
@RestController
@RequestMapping("/adjustClass")
public class AdjustClassController {
    /**
     * 服务对象
     */
    @Resource
    private AdjustClassService adjustClassService;

    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @Autowired
    private OaApplyNoticeService oaApplyNoticeService;

    @Resource
    TeacherHomeService teacherHomeService;

    /**
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public AdjustClassPojo selectOne(Integer id) {
        return this.adjustClassService.queryById(id);
    }


    /**
     * 创建调课审批申请
     */
    @PostMapping("/add")
    public ApiResult add(@RequestBody AdjustClassPojo adj) {
        String applyLesson = adj.getApplyLesson();
        Date applyDate = adj.getApplyDate();
        Integer applicantId = adj.getApplicantId();
        Integer approverId = adj.getApproverId();
        if (applicantId == null || approverId == null){
            return errorResult("申请人id或者审批人id不能为空");
        }
        JSONArray applyLessons = JSON.parseArray(applyLesson);
        //判断是否为连课调课申请如果是校验申请的节次是否符合连课调课的条件
        if (applyLessons != null && applyLessons.size() > 1){
            Integer lesson2 = Integer.parseInt(applyLessons.get(0).toString());
            Integer lesson1 = Integer.parseInt(applyLessons.get(1).toString());
            if (lesson1 - lesson2 != 1 && lesson2 - lesson1 != 1){
                return errorResult("不符合连课调课的条件");
            }
            JSONArray setLessons = JSON.parseArray(adj.getSetLesson());
            if (applyLessons.size() != setLessons.size()){
                return errorResult("调课节次数不一致，请检查");
            }
            if (setLessons.size() > 1){
                int setLesson = Integer.parseInt(setLessons.get(0).toString());
                int setLesson1 = Integer.parseInt(setLessons.get(1).toString());
                if (setLesson - setLesson1 != 1 && setLesson1 - setLesson != 1){
                    return errorResult("不符合连课调课的条件");
                }
            }
        }
        if (applyLessons != null && applyLessons.size() > 0){
            for (Object lesson : applyLessons) {
                int count = adjustClassService.selectCount(applyDate,lesson,applicantId);
                if (count > 0){
                    return errorResult("重复的调课申请");
                }
            }
        }
        adj.setTitle(adj.getApplicantName() + "的调课申请");
        adj.setCreateDate(new Date());
        adj.setStatus(0);
        adjustClassService.insert(adj);
        //根据审批人教师id查询用户id
        TeacherPojo teacher = teacherHomeService.getTeacherById(approverId);
        //发送调课审批通知
        if (teacher != null){
            sendNotApprovedSystemAndWechatNotice(adj.getId(),teacher.getUserId(),adj.getApplicantName(),adj.getReason());
        }
        String receivers = adj.getReceivers();
        if (receivers != null){
            JSONArray receiverList = JSON.parseArray(receivers);
            for (int i = 0; i < receiverList.size(); i++) {
                JSONObject receiver = receiverList.getJSONObject(i);
                Integer userId = Integer.valueOf(receiver.get("userId").toString());
                sendNotSystemAndWechatNotice(adj.getId(),userId,adj.getApplicantName());
            }
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 发送待审批通知
     *
     * @param id
     * @param notifyUserId
     * @param applyUserName
     * @param explain
     */
    private void sendNotApprovedSystemAndWechatNotice(Integer id,Integer notifyUserId,String applyUserName,String explain) {
        try {
            List notifyUserList = basicSQLService.find("select id user_id,open_id from yh_user where id=" + notifyUserId);
            if (notifyUserList != null && notifyUserList.size() > 0){
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("调课审批");
                swNotice.setContent("您有一条" + applyUserName + "的调课待审批");
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.TK);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("调课审批","你有一条" + applyUserName + "的调课待审批"));
                notifyService.sendSystemAndWechatNotice(swNotice,notifyUserList,"user_id","open_id");
                //发送代办事项
                OaApplyNotice oaApplyNotice = new OaApplyNotice();
                oaApplyNotice.setTitle(applyUserName + "的调课申请");
                oaApplyNotice.setReceiverId(notifyUserId);
                oaApplyNotice.setApplyExplain(explain);
                oaApplyNotice.setApplicantName(applyUserName);
                oaApplyNotice.setApplyResult(0);
                oaApplyNotice.setDataIdType(SystemNoticeDataIdType.TK);
                oaApplyNotice.setDataId(id);
                oaApplyNoticeService.add(oaApplyNotice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分页查询审批申请
     *
     * @param condition
     * @return
     */
    @PostMapping("/page")
    public PageInfo<AdjustClassPojo> pageSelect(@RequestBody PageCondition<AdjustClassPojo> condition) {
        return adjustClassService.selectList(condition);
    }

    /**
     * 审批调课申请
     *
     * @param approbalParam
     * @return
     */
    @PostMapping("/approval")
    public ApiResult approval(@RequestBody ApprobalParam approbalParam) {
        if (approbalParam.getStatus().equals(2) && approbalParam.getRejectionReason() == null){
            return errorResult("驳回理由不能为空");
        }
        AdjustClassPojo adjustClass = adjustClassService.approval(approbalParam);
        if (adjustClass != null){
            //根据教师id获取用户id
            TeacherPojo applyUser = teacherHomeService.getTeacherById(adjustClass.getApplicantId());
            TeacherPojo approver = teacherHomeService.getTeacherById(adjustClass.getApproverId());
            if (approbalParam.getStatus().equals(2)){
                // 驳回调课申请
                if (applyUser != null && approver != null){
                    sendApprovedResultSystemAndWechatNotice(adjustClass.getId(),"【驳回】",applyUser.getUserId(),approver.getUserId());
                    String receivers = adjustClass.getReceivers();
                    if (receivers != null){
                        JSONArray receiverList = JSON.parseArray(receivers);
                        for (int i = 0; i < receiverList.size(); i++) {
                            JSONObject receiver = receiverList.getJSONObject(i);
                            Integer userId = Integer.valueOf(receiver.get("userId").toString());
                            sendResultSystemAndWechatNotice(adjustClass.getId(),"【驳回】",userId);
                        }
                    }
                }
            } else {
                // 同意调课申请
                if (applyUser != null && approver != null){
                    sendApprovedResultSystemAndWechatNotice(adjustClass.getId(),"【同意】",applyUser.getUserId(),approver.getUserId());
                    String receivers = adjustClass.getReceivers();
                    if (receivers != null){
                        JSONArray receiverList = JSON.parseArray(receivers);
                        for (int i = 0; i < receiverList.size(); i++) {
                            JSONObject receiver = receiverList.getJSONObject(i);
                            Integer userId = Integer.valueOf(receiver.get("userId").toString());
                            sendResultSystemAndWechatNotice(adjustClass.getId(),"【同意】",userId);
                        }
                    }
                }
                // 驳回全部有关的调课申请
                Date applyDate = adjustClass.getApplyDate();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                String applyDateStr = df.format(applyDate);
                String applyLesson = adjustClass.getApplyLesson();
                JSONArray applyLessons = JSON.parseArray(applyLesson);
                for (Object lesson : applyLessons) {
                    //查询他人是否有与你有调课申请，如果有全部驳回
                    List<AdjustClassPojo> adjustClassPojoList = adjustClassService.listByLesson(lesson,applyDateStr);
                    if (adjustClassPojoList != null && adjustClassPojoList.size() > 0){
                        for (AdjustClassPojo adjustClassPojo : adjustClassPojoList) {
                            adjustClassPojo.setStatus(2);
                            adjustClassPojo.setApproveDate(new Date());
                            adjustClassPojo.setRejectionReason("不好意思，我已经和别的老师调课了");
                            adjustClassService.update(adjustClassPojo);
                            TeacherPojo apply = teacherHomeService.getTeacherById(adjustClassPojo.getApplicantId());
                            TeacherPojo Approval = teacherHomeService.getTeacherById(adjustClassPojo.getApproverId());
                            // 发送通知给申请人
                            if (apply != null && Approval != null){
                                sendApprovedResultSystemAndWechatNotice(adjustClassPojo.getId(),"【驳回】",apply.getUserId(),Approval.getUserId());
                                String receivers = adjustClassPojo.getReceivers();
                                if (receivers != null){
                                    JSONArray receiverList = JSON.parseArray(receivers);
                                    for (int i = 0; i < receiverList.size(); i++) {
                                        JSONObject receiver = receiverList.getJSONObject(i);
                                        Integer userId = Integer.valueOf(receiver.get("userId").toString());
                                        sendResultSystemAndWechatNotice(adjustClass.getId(),"【驳回】",userId);
                                    }
                                }
                            }
                        }
                    }
                }
                //查询是否有与审批人相同日期节次的调课如果有全部驳回，
                Date setDate = adjustClass.getSetDate();
                String setDateStr = df.format(setDate);
                String setLesson = adjustClass.getSetLesson();
                JSONArray setLessons = JSON.parseArray(setLesson);
                for (Object lesson : setLessons) {
                    List<AdjustClassPojo> adjustClassPojoList = adjustClassService.listBySetDateSetLesson(lesson,setDateStr);
                    if (adjustClassPojoList != null && adjustClassPojoList.size() > 0){
                        for (AdjustClassPojo classPojo : adjustClassPojoList) {
                            classPojo.setApproveDate(new Date());
                            classPojo.setStatus(2);
                            classPojo.setRejectionReason("不好意思，我已经和别的老师调课了");
                            adjustClassService.update(classPojo);
                            // 发送通知给申请人
                            TeacherPojo apply = teacherHomeService.getTeacherById(classPojo.getApplicantId());
                            TeacherPojo approval = teacherHomeService.getTeacherById(classPojo.getApproverId());
                            if (apply != null && approval != null){
                                sendApprovedResultSystemAndWechatNotice(classPojo.getId(),"【驳回】",apply.getUserId(),approval.getUserId());
                                String receivers = classPojo.getReceivers();
                                if (receivers != null){
                                    JSONArray receiverList = JSON.parseArray(receivers);
                                    for (int i = 0; i < receiverList.size(); i++) {
                                        JSONObject receiver = receiverList.getJSONObject(i);
                                        Integer userId = Integer.valueOf(receiver.get("userId").toString());
                                        sendResultSystemAndWechatNotice(adjustClass.getId(),"【驳回】",userId);
                                    }
                                }
                            }
                        }

                    }
                }
            }
            return ApiResult.of(ResultCode.SUCCESS);
        }
        return ApiResult.of(ResultCode.PARAMS_IS_INVALID);
    }

    /**
     * 发送审批完成通知
     *
     * @param id
     * @param result
     * @param applyUserId
     * @param handlerUserId
     */
    private void sendApprovedResultSystemAndWechatNotice(Integer id,String result,Integer applyUserId,Integer handlerUserId) {
        try {
            List notifyUserList = basicSQLService.find("select id user_id,open_id from yh_user where id=" + applyUserId);
            if (notifyUserList != null && notifyUserList.size() > 0){
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("调课课审批结果");
                swNotice.setContent("审批：" + result);
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.TK);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("调课审批结果","审批：" + result));
                notifyService.sendSystemAndWechatNotice(swNotice,notifyUserList,"user_id","open_id");
                //更新代办
                oaApplyNoticeService.updateApplyResult(handlerUserId,id,result.equals("【同意】") ? 1 : 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 抄送人通知
     *
     * @param id
     * @param notifyUserId
     * @param applyUserName
     */
    private void sendNotSystemAndWechatNotice(Integer id,Integer notifyUserId,String applyUserName) {
        try {
            List notifyUserList = basicSQLService.find("select id user_id,open_id from yh_user where id=" + notifyUserId);
            if (notifyUserList != null && notifyUserList.size() > 0){
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("调课审批");
                swNotice.setContent(applyUserName + "申请了调课");
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.TK);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("调课审批",applyUserName + "申请了调课"));
                notifyService.sendSystemAndWechatNotice(swNotice,notifyUserList,"user_id","open_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送审批完成通知
     *
     * @param id
     * @param result
     * @param applyUserId
     */
    private void sendResultSystemAndWechatNotice(Integer id,String result,Integer applyUserId) {
        try {
            List notifyUserList = basicSQLService.find("select id user_id,open_id from yh_user where id=" + applyUserId);
            if (notifyUserList != null && notifyUserList.size() > 0){
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("调课课审批结果");
                swNotice.setContent("审批：" + result);
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.TK);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("调课审批结果","审批：" + result));
                notifyService.sendSystemAndWechatNotice(swNotice,notifyUserList,"user_id","open_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ApiResult errorResult(String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(400);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
