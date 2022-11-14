package com.xunyunedu.student.controller;


import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.notice.constant.SystemNoticeDataIdType;
import com.xunyunedu.notice.constant.SystemNoticeType;
import com.xunyunedu.notice.pojo.OaApplyNotice;
import com.xunyunedu.notice.pojo.SystemWechatNotice;
import com.xunyunedu.notice.service.OaApplyNoticeService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.student.pojo.StudentAskingPojo;
import com.xunyunedu.student.pojo.TeacherPojo;
import com.xunyunedu.student.service.StudentAskingService;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 学生请假管理
 *
 * @author: yhc
 * @Date: 2020/10/15 9:01
 * @Description:
 */
@RestController
@RequestMapping("/stuAsking")
public class StudentAskingController {
    Logger logger = LoggerFactory.getLogger(StudentAskingController.class);

    @Autowired
    private StudentAskingService studentAskingService;
    @Resource
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @Autowired
    private OaApplyNoticeService oaApplyNoticeService;

    /**
     * 查看学生请假详细信息
     *
     * @param schoolId 学校id
     * @param id       请假信息id
     * @return
     */
    @GetMapping("/getStuAsking")
    @Authorization
    public ApiResult getStuAsking(@RequestParam Integer schoolId, @RequestParam Integer id) {
        if (schoolId == null || id == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        StudentAskingPojo studentAskingPojo = studentAskingService.getStuAskingById(schoolId, id);
        return new ApiResult(ResultCode.SUCCESS, studentAskingPojo);
    }


    /**
     * 新增学生请假
     *
     * @param studentAskingPojo 请假信息
     * @return
     */
    @PostMapping("/addAsking")
    @Authorization
    public ApiResult addAskingInfo(@RequestBody StudentAskingPojo studentAskingPojo) {
        if (studentAskingPojo == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        studentAskingService.addAskingInfo(studentAskingPojo);
        //发送通知
        String stuName = (String) basicSQLService.findUnique("select `name`  from pj_student where id=" + studentAskingPojo.getStudentId());
        sendNotApprovedSystemAndWechatNotice(studentAskingPojo.getId(), studentAskingPojo.getTeacherId(), stuName, studentAskingPojo.getRemark());
        return new ApiResult(ResultCode.SUCCESS);
    }


    //发送待审批通知
    private void sendNotApprovedSystemAndWechatNotice(Integer id, Integer teacherId, String studentName, String explain) {
        try {
            List<Map<String, Object>> notifyUserList = basicSQLService.find("select u.id user_id,u.open_id from pj_teacher t left join yh_user u on u.id=t.user_id where t.id=" + teacherId);
            if (notifyUserList != null && notifyUserList.size() > 0) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("学生请假审批");
                swNotice.setContent("你有一条" + studentName + "的请假待审批");
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.XSQJ);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("学生请假审批", "你有一条" + studentName + "的请假待审批"));
                notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");

                //发送代办事项
                logger.debug("开始发送代办事项");
                OaApplyNotice oaApplyNotice = new OaApplyNotice();
                oaApplyNotice.setTitle(studentName + "的请假申请");
                oaApplyNotice.setReceiverId(Integer.valueOf(notifyUserList.get(0).get("user_id").toString()));
                oaApplyNotice.setApplicantName(studentName);
                oaApplyNotice.setApplyExplain(explain);
                oaApplyNotice.setApplyResult(0);
                oaApplyNotice.setDataIdType(SystemNoticeDataIdType.XSQJ);
                oaApplyNotice.setDataId(id);
                oaApplyNoticeService.add(oaApplyNotice);
                logger.debug("待办事项发送成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    /**
     * 获取当前学生的班主任教师
     *
     * @param schoolId 学校id
     * @param stuId    学生id
     * @return
     */
    @GetMapping("/getTeacher")
    @Authorization
    public ApiResult getTeacher(@RequestParam Integer schoolId, @RequestParam Integer stuId) {
        if (schoolId == null || stuId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        TeacherPojo teacherPojo = studentAskingService.getTeacherById(schoolId, stuId);
        return new ApiResult(ResultCode.SUCCESS, teacherPojo);
    }

    /**
     * 查看已提交请假
     *
     * @param studentAskingPojo
     * @param pageNum           第几页
     * @param pageSize          每页记录数
     * @return
     */
    @GetMapping("/getAskingList")
    @Authorization
    public ApiResult getAskingList(StudentAskingPojo studentAskingPojo, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (studentAskingPojo == null || studentAskingPojo.getSchoolId() == null || studentAskingPojo.getStudentId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        return new ApiResult(ResultCode.SUCCESS, studentAskingService.getStuAskingList(studentAskingPojo, pageNum, pageSize));
    }
}
