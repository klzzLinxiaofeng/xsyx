package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件考勤】更新用户考勤状态
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceUpdateEventRecordStatusRequest extends OpenApiRequest<AttendanceServiceUpdateEventRecordStatusParam, AttendanceServiceUpdateEventRecordStatusResult> {

    public AttendanceServiceUpdateEventRecordStatusRequest(AttendanceServiceUpdateEventRecordStatusParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceUpdateEventRecordStatusRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/update-event-record-status");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceUpdateEventRecordStatusResult> getResponseClass() {
        return AttendanceServiceUpdateEventRecordStatusResult.class;
    }

    public Class<AttendanceServiceUpdateEventRecordStatusParam> getDomainClass() {
        return AttendanceServiceUpdateEventRecordStatusParam.class;
    }
}

