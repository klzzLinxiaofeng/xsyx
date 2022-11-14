package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件|课程】根据规则与日期条件分页查询考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListAttendRecordsRequest extends OpenApiRequest<AttendanceServiceListAttendRecordsParam, AttendanceServiceListAttendRecordsResult> {

    public AttendanceServiceListAttendRecordsRequest(AttendanceServiceListAttendRecordsParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListAttendRecordsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-attend-records");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListAttendRecordsResult> getResponseClass() {
        return AttendanceServiceListAttendRecordsResult.class;
    }

    public Class<AttendanceServiceListAttendRecordsParam> getDomainClass() {
        return AttendanceServiceListAttendRecordsParam.class;
    }
}

