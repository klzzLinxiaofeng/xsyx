package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: （已废弃）获取事件下班级考勤详细记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListEventClassRecordsRequest extends OpenApiRequest<AttendanceServiceListEventClassRecordsParam, AttendanceServiceListEventClassRecordsResult> {

    public AttendanceServiceListEventClassRecordsRequest(AttendanceServiceListEventClassRecordsParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListEventClassRecordsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-event-class-records");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListEventClassRecordsResult> getResponseClass() {
        return AttendanceServiceListEventClassRecordsResult.class;
    }

    public Class<AttendanceServiceListEventClassRecordsParam> getDomainClass() {
        return AttendanceServiceListEventClassRecordsParam.class;
    }
}

