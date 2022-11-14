package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件|课程】根据规则指定日期与班级查询考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListAttendClassRecordsRequest extends OpenApiRequest<AttendanceServiceListAttendClassRecordsParam, AttendanceServiceListAttendClassRecordsResult> {

    public AttendanceServiceListAttendClassRecordsRequest(AttendanceServiceListAttendClassRecordsParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListAttendClassRecordsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-attend-class-records");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListAttendClassRecordsResult> getResponseClass() {
        return AttendanceServiceListAttendClassRecordsResult.class;
    }

    public Class<AttendanceServiceListAttendClassRecordsParam> getDomainClass() {
        return AttendanceServiceListAttendClassRecordsParam.class;
    }
}

