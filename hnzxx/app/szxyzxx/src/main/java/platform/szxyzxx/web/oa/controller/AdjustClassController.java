package platform.szxyzxx.web.oa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import platform.education.generalTeachingAffair.dao.SyllabusLessonDao;
import platform.education.generalTeachingAffair.model.SyllabusLesson;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.vo.SyllabusLessonCondition;
import platform.education.oa.model.AdjustClass;
import platform.education.oa.service.AdjustClassService;
import platform.education.oa.vo.AdjustClassCondition;
import platform.education.oa.vo.AdjustClassVo;
import platform.szxyzxx.notice.constants.SystemNoticeDataIdType;
import platform.szxyzxx.notice.constants.SystemNoticeType;
import platform.szxyzxx.notice.pojo.SystemWechatNotice;
import platform.szxyzxx.notice.service.OaApplyNoticeService;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.oa.contans.ContansOfOa;
import platform.szxyzxx.web.oa.utils.DateUtils;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ZenGx1n
 * @version 1.0
 * @date 2021-03-25 19:14
 * 调课
 */
@Controller
@RequestMapping("/oa/adjustClass")
public class AdjustClassController {

    private final static String viewBasePath = "/oa/adjustClass";

    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    private AdjustClassService adjustClassService;

    @Autowired
    private OaApplyNoticeService oaApplyNoticeService;

    @Autowired
    private SyllabusLessonDao syllabusLessonDao;

    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    private static final Logger log = LoggerFactory.getLogger(AdjustClassController.class);

    @RequestMapping(value = "/index")
    public ModelAndView index(@CurrentUser UserInfo user,
                              @ModelAttribute("condition") AdjustClassCondition condition,
                              @ModelAttribute("page") Page page,
                              @RequestParam(value = "sub", required = false) String sub,
                              @ModelAttribute("order") Order order,
                              @RequestParam(value = "auditType", required = false) String auditType,
                              @RequestParam(value = "usePage", required = false) boolean usePage,
                              Model model) {
        String viewPath;
        // 如果是学校管理员 可以看到全部的调课记录
        if (!"SCHOOL_LEADER".equals(user.getCurrentRoleCode())) {
            condition.setApproverId(user.getTeacherId());
            condition.setApplicantId(user.getTeacherId());
        }

        // 设置每页显示三条记录
        page.setPageSize(3);

        if (condition.getStatus() == null) {
            if ("approval".equals(auditType) || auditType == null || "".equals(auditType)) {
                // 将待审批条件设置
                condition.setStatus(ContansOfOa.COURSE_TRANSFER_APPROVAL);
            } else if ("approved".equals(auditType)) {
                // 将已审批条件设置
                condition.setStatus(ContansOfOa.COURSE_TRANSFER_APPROVED);
            }
        }
        log.info("查询调课列表 参数 => {}", JSONObject.toJSONString(condition));

        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        Long count = this.adjustClassService.count(condition);
        List<AdjustClassVo> adjustClassVos = this.adjustClassService.findAdjustClassVoByCondition(condition, page, order);

        for (AdjustClassVo adjustClassVo : adjustClassVos) {
            JSONArray applyLessons = JSON.parseArray(adjustClassVo.getApplyLesson());
            JSONArray setLessons = JSON.parseArray(adjustClassVo.getSetLesson());
            if (applyLessons.size() > 1) {
                adjustClassVo.setApplyLessonOne(applyLessons.get(0));
                adjustClassVo.setApplyLessonTwo(applyLessons.get(1));
                adjustClassVo.setSetLessonOne(setLessons.get(0));
                adjustClassVo.setSetLessonTwo(setLessons.get(1));
            } else {
                adjustClassVo.setApplyLessonOne(applyLessons.get(0));
                adjustClassVo.setSetLessonOne(applyLessons.get(0));
            }
            adjustClassVo.setSetWeek(DateUtils.getWeekNameOfDate(adjustClassVo.getSetDate()));
            adjustClassVo.setApplyWeek(DateUtils.getWeekNameOfDate(adjustClassVo.getApplyDate()));

            if (adjustClassVo.getStatus() == 0 && adjustClassVo.getApproverId().equals(user.getTeacherId())) {
                adjustClassVo.setApproveFlag(true);
            }
        }

        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/pending");
        }

        model.addAttribute("totalCount", count);
        model.addAttribute("items", adjustClassVos);
        model.addAttribute("currentUser", user.getTeacherId());
        model.addAttribute("curenuser", user.getId());
        model.addAttribute("auditType", auditType);
        return new ModelAndView(viewPath);
    }

    /**
     * 同意调课
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    public String agree(Integer id) {
        log.info("同意调课参数 => {}", id);
        try {
            AdjustClass adjustClass = this.adjustClassService.findById(id);
            if (adjustClass != null) {
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String approveDate = df.format(date);
                // 拷贝申请人的个人课表
                personalTimetable(adjustClass, adjustClass.getApplicantId(), true);
                // 拷贝审批人的个人课表
                personalTimetable(adjustClass, adjustClass.getApproverId(), false);
                this.basicSQLService.update("UPDATE oa_adjust_class SET status=1, approve_date='" + approveDate + "' WHERE id=" + id);
                // 发送通知给申请人
                sendApprovedResultSystemAndWechatNotice(adjustClass.getId(), "【同意】", adjustClass.getApplicantId(), adjustClass.getApproverId());

                // 驳回全部有关的调课申请
                List<AdjustClass> adjustClasses = this.adjustClassService.modifyReject(adjustClass);
                if (!adjustClasses.isEmpty()) {
                    for (AdjustClass a : adjustClasses) {
                        String rejectDate = df.format(date);
                        this.basicSQLService.update("UPDATE oa_adjust_class SET status=2, approve_date='" + rejectDate + "', rejection_reason=\"不好意思，我已经和别的老师调课了\" WHERE id=" + a.getId());
                        // 发送通知给申请人
                        sendApprovedResultSystemAndWechatNotice(adjustClass.getId(), "【驳回】", adjustClass.getApplicantId(), adjustClass.getApproverId());
                    }
                }

                // 发送通知给申请人
                String receivers = adjustClass.getReceivers();
                if (receivers != null) {
                    JSONArray receiverList = JSON.parseArray(receivers);
                    for (int i = 0; i < receiverList.size(); i++) {
                        JSONObject receiver = receiverList.getJSONObject(i);
                        Integer userId = Integer.valueOf(receiver.get("userId").toString());
                        sendResultSystemAndWechatNotice(adjustClass.getId(), "【同意】", userId);
                    }
                }
            }
            return ResponseInfomation.OPERATION_SUC;
        } catch (Exception e) {
            return ResponseInfomation.OPERATION_FAIL;
        }
    }

    /**
     * 驳回调课
     *
     * @param adjustClass
     * @return
     */
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    public String reject(AdjustClass adjustClass) {
        log.info("驳回调课参数 => {}", JSONObject.toJSONString(adjustClass));
        try {
            Date date = new Date();
            SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String approveDate = temp.format(date);
            this.basicSQLService.update("UPDATE oa_adjust_class SET status=2, approve_date='" + approveDate + "', rejection_reason='" + adjustClass.getRejectionReason() + "' WHERE id=" + adjustClass.getId());
            // 发送通知给申请人
            sendApprovedResultSystemAndWechatNotice(adjustClass.getId(), "【驳回】", adjustClass.getApplicantId(), adjustClass.getApproverId());

            // 发送通知给申请人
            String receivers = adjustClass.getReceivers();
            if (receivers != null) {
                JSONArray receiverList = JSON.parseArray(receivers);
                for (int i = 0; i < receiverList.size(); i++) {
                    JSONObject receiver = receiverList.getJSONObject(i);
                    Integer userId = Integer.valueOf(receiver.get("userId").toString());
                    sendResultSystemAndWechatNotice(adjustClass.getId(), "【驳回】", userId);
                }
            }
            return ResponseInfomation.OPERATION_SUC;
        } catch (Exception e) {
            return ResponseInfomation.OPERATION_FAIL;
        }
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    /**
     * 发送审批结果通知以及更新代办
     *
     * @param id          申请id，前端跳转页面所需
     * @param result      审批结果字符串
     * @param applicantId 申请者userId
     * @param approverId  审批者userId
     */
    private void sendApprovedResultSystemAndWechatNotice(Integer id, String result, Integer applicantId, Integer approverId) {
        try {
            List applicantList = basicSQLService.find("select user_id from pj_teacher where id=" + applicantId);
            List approverList = basicSQLService.find("select user_id from pj_teacher where id=" + approverId);
            if (applicantList.size() > 0 && approverList.size() > 0) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("调课审批结果");
                swNotice.setContent("审批：" + result);
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.TK);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("调课审批结果", "审批：" + result));
                HashMap map1 = (HashMap) applicantList.get(0);
                HashMap map2 = (HashMap) approverList.get(0);
                notifyService.sendSystemAndWechatNotice(swNotice, (Integer) map1.get("user_id"));

                //更新代办
                oaApplyNoticeService.updateApplyResult((Integer) map2.get("user_id"), id, "【同意】".equals(result) ? 1 : 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拷贝个人课表
     *
     * @param adjustClass
     * @param teacherId
     * @param flag
     */
    private void personalTimetable(AdjustClass adjustClass, Integer teacherId, boolean flag) {
        String applyWeek = DateUtils.getWeekOfDate(adjustClass.getApplyDate()).toString();
        String setWeek = DateUtils.getWeekOfDate(adjustClass.getSetDate()).toString();
        // 调课日期的周一日期
        Date applyFirstDay = DateUtils.getFirstDayOfWeek(adjustClass.getApplyDate());
        // 调课日期的周日日期
        Date applyLastDay = DateUtils.getLastDayOfWeek(adjustClass.getApplyDate());
        String[] applyLessons = adjustClass.getApplyLesson().substring(1, adjustClass.getApplyLesson().length() - 1).split(",");
        String[] setLessons = adjustClass.getSetLesson().substring(1, adjustClass.getSetLesson().length() - 1).split(",");
        int i1 = Integer.parseInt(applyLessons[0].substring(1, 2));
        int i2 = Integer.parseInt(setLessons[0].substring(1, 2));
        SyllabusLessonCondition condition = new SyllabusLessonCondition();
        condition.setTeacherId(teacherId);
        condition.setStartDate(applyFirstDay);
        condition.setDefaultFlag(1);
        List<SyllabusLesson> syllabusLessonList = syllabusLessonDao.findSyllabusLessonByCondition(condition, null, null);
        if (syllabusLessonList.size() <= 0) {
            condition.setEffectiveDate(adjustClass.getApplyDate());
            condition.setStartDate(null);
            condition.setDefaultFlag(0);
            syllabusLessonList = syllabusLessonDao.findSyllabusLessonByCondition(condition, null, null);
        } else {
            // 删除该周的个人课表
            syllabusLessonDao.deleteByCondition(condition);
        }
        if (applyLessons.length > 1) {
            // 连课调课
            int i3 = Integer.parseInt(setLessons[1].substring(1, 2));
            int i4 = Integer.parseInt(applyLessons[1].substring(1, 2));
            for (SyllabusLesson syllabusLesson : syllabusLessonList) {
                syllabusLesson.setCreateDate(new Date());
                syllabusLesson.setStartDate(applyFirstDay);
                syllabusLesson.setAdjustFlag(0);
                syllabusLesson.setDefaultFlag(1);
                syllabusLesson.setEndDate(applyLastDay);
                syllabusLesson.setId(null);
                if (flag) {
                    // 复制申请人的个人课表
                    if (syllabusLesson.getLesson().equals(i1) && syllabusLesson.getDayOfWeek().equals(applyWeek)) {
                        syllabusLesson.setLesson(i2);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(setWeek);
                    } else if (syllabusLesson.getLesson().equals(i4) && syllabusLesson.getDayOfWeek().equals(applyWeek)) {
                        syllabusLesson.setLesson(i3);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(setWeek);
                    }
                } else {
                    // 复制审批人的个人课表
                    if (syllabusLesson.getLesson().equals(i2) && syllabusLesson.getDayOfWeek().equals(setWeek)) {
                        syllabusLesson.setLesson(i1);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(applyWeek);
                    } else if (syllabusLesson.getLesson().equals(i3) && syllabusLesson.getDayOfWeek().equals(setWeek)) {
                        syllabusLesson.setLesson(i4);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(applyWeek);
                    }
                }
                this.syllabusLessonDao.create(syllabusLesson);
            }
        } else {
            // 单课调课
            for (SyllabusLesson syllabusLesson : syllabusLessonList) {
                syllabusLesson.setCreateDate(new Date());
                syllabusLesson.setId(null);
                syllabusLesson.setStartDate(applyFirstDay);
                syllabusLesson.setEndDate(applyLastDay);
                syllabusLesson.setAdjustFlag(0);
                syllabusLesson.setDefaultFlag(1);
                if (flag) {
                    // 复制申请人的个人课表
                    if (syllabusLesson.getLesson().equals(i1) && syllabusLesson.getDayOfWeek().equals(applyWeek)) {
                        syllabusLesson.setLesson(i2);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(setWeek);
                    }
                } else {
                    // 复制审批人的个人课表
                    if (syllabusLesson.getLesson().equals(i2) && syllabusLesson.getDayOfWeek().equals(setWeek)) {
                        syllabusLesson.setLesson(i1);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(applyWeek);
                    }
                }
                this.syllabusLessonDao.create(syllabusLesson);
            }
        }
    }

    /**
     * 发送审批完成通知
     *
     * @param id
     * @param result
     * @param applyUserId
     */
    private void sendResultSystemAndWechatNotice(Integer id, String result, Integer applyUserId) {
        try {
            List notifyUserList = basicSQLService.find("select id user_id,open_id from yh_user where id=" + applyUserId);
            if (notifyUserList != null && notifyUserList.size() > 0) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("调课课审批结果");
                swNotice.setContent("审批：" + result);
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.TK);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("调课审批结果", "审批：" + result));
                notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
