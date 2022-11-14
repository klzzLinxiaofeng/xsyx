//package com.xunyunedu.workAttendance.Controller;
//
//import com.xunyunedu.exception.ResultCode;
//import com.xunyunedu.util.seewo.pojo.AttendanceServiceListEventClassRecordsResult;
//import com.xunyunedu.util.seewo.pojo.AttendanceServiceListLessonRecordsConditionalResult;
//import com.xunyunedu.workAttendance.model.HikvisionDoorReq;
//import com.xunyunedu.workAttendance.model.HikvisionDoorRes;
//import com.xunyunedu.workAttendance.model.SeewoEventWorkAttendance;
//import com.xunyunedu.workAttendance.model.SeewoLessonWorkAttendance;
//import com.xunyunedu.workAttendance.service.SeeWoWorkAttendanceControllerService;
//import com.xunyunedu.exception.ApiResult;
//import com.xunyunedu.interceptor.Authorization;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
///**
//  * 考勤控制控制层
//  * @author: cmb
//  * @create: 2020-11-05 09:44
// **/
//@RestController
//@RequestMapping("/accessControl")
//public class WorkAttendanceController {
//    @Autowired
//    private SeeWoWorkAttendanceControllerService seeWoWorkAttendanceControllerService;
//    /**
//     * 条件查询课程考勤记录详情
//     * @param sw
//     * @return: com.xunyunedu.exception.ApiResult
//     * @Author: cmb
//     * @Date: 2020/11/10
//     */
//    @PostMapping("/seewobp/lesson/details")
//    @Authorization
//
//    private ApiResult lessonDetails(@RequestBody SeewoLessonWorkAttendance sw){
//        Map all = seeWoWorkAttendanceControllerService.findDetails(sw);
//        if (all!=null){
//            return new ApiResult(ResultCode.FIND_SUCCESS,all);
//        }
//            return new ApiResult(ResultCode.FIND_FAIL);
//    }
//    /**
//     * 事件考勤
//     * @param se
//     * @return: com.xunyunedu.exception.ApiResult
//     * @Author: cmb
//     * @Date: 2020/11/10
//     */
//
//    @PostMapping("/seewo/event")
//    @Authorization
//
//    private ApiResult lessonDetails(@RequestBody SeewoEventWorkAttendance se){
//        AttendanceServiceListEventClassRecordsResult as = seeWoWorkAttendanceControllerService.getEventWorkAttendance(se);
//        if (as!=null){
//            return new ApiResult(ResultCode.FIND_SUCCESS,as);
//        }
//        return new ApiResult(ResultCode.FIND_FAIL);
//    }
//    /**
//     * 通过学校id，时间查询门禁考勤
//     * @param req
//     * @return: com.xunyunedu.exception.ApiResult
//     * @Author: cmb
//     * @Date: 2020/11/10
//     */
//    @PostMapping("/hikvision/door")
//    @Authorization
//    private ApiResult doorWorkAttendance(@RequestBody HikvisionDoorReq req){
//        Map<String, Object> doorWorkAttendance = seeWoWorkAttendanceControllerService.getDoorWorkAttendance(req);
//        if (!doorWorkAttendance.isEmpty()){
//            return new ApiResult(ResultCode.FIND_SUCCESS,doorWorkAttendance);
//        }
//        return new ApiResult(ResultCode.FIND_FAIL);
//
//    }
//
//
//
//}
