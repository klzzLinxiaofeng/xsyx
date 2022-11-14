//package com.xunyunedu.workAttendance.service;
//
//
//
//import com.xunyunedu.util.seewo.pojo.AttendanceServiceListEventClassRecordsResult;
//import com.xunyunedu.util.seewo.pojo.AttendanceServiceListLessonRecordsConditionalResult;
//import com.xunyunedu.workAttendance.model.HikvisionDoorReq;
//import com.xunyunedu.workAttendance.model.HikvisionDoorRes;
//import com.xunyunedu.workAttendance.model.SeewoEventWorkAttendance;
//import com.xunyunedu.workAttendance.model.SeewoLessonWorkAttendance;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @description: 希沃考勤业务层接口
// * @author: cmb
// * @create: 2020-11-05 18:17
// **/
//public interface SeeWoWorkAttendanceControllerService {
///**
//* @Description: 通过希沃考勤类当前日期所有学生信息
//* @Param: * @param slac
//* @return: java.util.List<com.xunyunedu.util.seewo.pojo.AttendanceServiceListLessonRecordsConditionalResult>
//* @Author: cmb
//* @Date: 2020/11/5
//*/
//
//Map<String,Object> findDetails(SeewoLessonWorkAttendance sw);
//    /**
//    * @Description: 事件考勤
//    * @Param: * @param se
//    * @return: com.xunyunedu.util.seewo.pojo.AttendanceServiceListLessonRecordsConditionalResult
//    * @Author: cmb
//    * @Date: 2020/11/5
//    */
//
//    AttendanceServiceListEventClassRecordsResult getEventWorkAttendance(SeewoEventWorkAttendance se);
//    /**
//    * @Description: 门禁考勤
//    * @Param: *
//    * @return: java.util.List<com.xunyunedu.workAttendance.model.HikvisionDoorRes>
//    * @Author: cmb
//    * @Date: 2020/11/6
//    */
//
//    Map<String,Object> getDoorWorkAttendance(HikvisionDoorReq req);
//}
