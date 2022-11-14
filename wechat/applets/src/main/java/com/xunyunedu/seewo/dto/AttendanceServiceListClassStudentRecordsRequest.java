package com.xunyunedu.seewo.dto;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件|课程】根据班级查询学生考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-2-19
 */
public class AttendanceServiceListClassStudentRecordsRequest extends OpenApiRequest<AttendanceServiceListClassStudentRecordsParam, AttendanceServiceListClassStudentRecordsResult> {

    public AttendanceServiceListClassStudentRecordsRequest(AttendanceServiceListClassStudentRecordsParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListClassStudentRecordsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-class-student-records");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListClassStudentRecordsResult> getResponseClass() {
        return AttendanceServiceListClassStudentRecordsResult.class;
    }

    public Class<AttendanceServiceListClassStudentRecordsParam> getDomainClass() {
        return AttendanceServiceListClassStudentRecordsParam.class;
    }
}

