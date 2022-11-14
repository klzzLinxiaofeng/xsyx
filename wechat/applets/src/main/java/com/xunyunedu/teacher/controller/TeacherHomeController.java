package com.xunyunedu.teacher.controller;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.notice.constant.SystemNoticeDataIdType;
import com.xunyunedu.notice.constant.SystemNoticeType;
import com.xunyunedu.notice.pojo.SystemWechatNotice;
import com.xunyunedu.notice.service.OaApplyNoticeService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.student.pojo.StudentAskingPojo;
import com.xunyunedu.student.service.StudentAskingService;
import com.xunyunedu.teacher.condition.TeacherSearchCondition;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.teacher.service.TeacherHomeService;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import com.xunyunedu.workAttendance.model.PjTeamTeacher;
import com.xunyunedu.workAttendance.service.PjTeamTeacherService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 教师端首页
 *
 * @author: yhc
 * @Date: 2020/10/30 16:36
 * @Description:
 */
@RestController
@RequestMapping("/teacherHome")
public class TeacherHomeController {
    Logger logger = LoggerFactory.getLogger(TeacherHomeController.class);

    @Autowired
    private StudentAskingService studentAskingService;

    @Autowired
    private TeacherHomeService teacherHomeService;

    @Autowired
    private PjTeamTeacherService pjTeamTeacherService;
    @Resource
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @Autowired
    private OaApplyNoticeService oaApplyNoticeService;
    /**
     * 查看学生已提交请假
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
        if (studentAskingPojo == null || studentAskingPojo.getSchoolId() == null || studentAskingPojo.getTeacherId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        return new ApiResult(ResultCode.SUCCESS, studentAskingService.getStuAskingList(studentAskingPojo, pageNum, pageSize));
    }


    /**
     * 教师端查看请假详细信息
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
     * 学生请假审批
     *
     * @param studentAskingPojo
     * @return
     */
    @PostMapping("/india")
    public ApiResult addAskingInfo(@RequestBody StudentAskingPojo studentAskingPojo) {
        if (studentAskingPojo == null || studentAskingPojo.getIndiaStatus() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        teacherHomeService.modifyIndiaSatus(studentAskingPojo);
        String result="【驳回】";
        if(studentAskingPojo.getIndiaStatus()==2){
            result="【同意】";
        }

        sendApprovedResultSystemAndWechatNotice(studentAskingPojo.getId(),result);

        return new ApiResult(ResultCode.SUCCESS);
    }





    /**
     * 修改教师头像
     *
     * @param teacherPojo
     * @return
     */
    @PostMapping("/updateTeacher")
    @Authorization
    public ApiResult updateTeacher(TeacherPojo teacherPojo) {
        if (teacherPojo == null || teacherPojo.getSchoolId() == null || teacherPojo.getId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        teacherHomeService.updateTeacherInfor(teacherPojo);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 获取教师核心信息列表（id,name,userId）
     */
    @PostMapping("/getTeacherCoreInfoList")
    public ApiResult teacherPage(@RequestBody PageCondition<TeacherSearchCondition> condition){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(teacherHomeService.pagingSelectTeacherCoreInfo(condition));
        return apiResult;
    }

    /**
     * 判断老师是否为班主任
     * @param teacherId
     * @return
     */
    @GetMapping("/getTeacherCondition")
    @Authorization
    public ApiResult getTeacherCondition(@Param("teacherId") Integer teacherId){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(pjTeamTeacherService.findTeacherByTeacherId(teacherId));
        return apiResult;

    }


    /**
     * 修改教师学历、职称
     */
    @PostMapping("/updateTeacherInfo")
    @Authorization
    public ApiResult updateTeacherInfo(@RequestBody TeacherPojo teacherPojo){
        if (teacherPojo == null || teacherPojo.getSchoolId() == null || teacherPojo.getId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        teacherHomeService.updateTeacher(teacherPojo);
        return new ApiResult(ResultCode.SUCCESS);
    }



    //发送审批结果通知
    private void sendApprovedResultSystemAndWechatNotice(Integer id,String result){
        try {
            //微信通知是发送给家长
            List<Map<String,Object>> notifyUserList=basicSQLService.find("select t.user_id stu_user_id,u.open_id as parent_open_id,te.user_id te_user_id from pj_student_asking sa inner join pj_student t on t.id=sa.student_id  left join pj_parent_student ps on ps.student_user_id=t.user_id and ps.rank=1 and ps.is_delete=0 inner join yh_user u on u.id=ps.parent_user_id  inner join pj_teacher te on te.id=sa.teacher_id where sa.id="+id);
            if(notifyUserList!=null && notifyUserList.size()>0) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("请假审批结果");
                swNotice.setContent("您的请假审批"+result);
                swNotice.setDataId(id.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
                swNotice.setDataIdType(SystemNoticeDataIdType.XSQJ);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("请假审批结果", "您的请假审批"+result));
                notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "stu_user_id", "parent_open_id");
                //更新代办
                oaApplyNoticeService.updateApplyResult(Integer.valueOf(notifyUserList.get(0).get("te_user_id").toString()),id,result.equals("【同意】")? 1 : 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
