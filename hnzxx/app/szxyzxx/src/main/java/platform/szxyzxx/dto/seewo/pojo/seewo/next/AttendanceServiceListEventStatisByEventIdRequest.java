package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件|课程】根据事件查询指定日期的考勤统计数据
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListEventStatisByEventIdRequest extends OpenApiRequest<AttendanceServiceListEventStatisByEventIdParam, AttendanceServiceListEventStatisByEventIdResult> {

    public AttendanceServiceListEventStatisByEventIdRequest(AttendanceServiceListEventStatisByEventIdParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListEventStatisByEventIdRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-event-statis-by-event-id");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListEventStatisByEventIdResult> getResponseClass() {
        return AttendanceServiceListEventStatisByEventIdResult.class;
    }

    public Class<AttendanceServiceListEventStatisByEventIdParam> getDomainClass() {
        return AttendanceServiceListEventStatisByEventIdParam.class;
    }
}

