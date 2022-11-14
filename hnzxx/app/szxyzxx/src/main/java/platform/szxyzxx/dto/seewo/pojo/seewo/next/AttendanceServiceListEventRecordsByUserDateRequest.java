package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件+课程】根据用户查询指定日期的考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListEventRecordsByUserDateRequest extends OpenApiRequest<AttendanceServiceListEventRecordsByUserDateParam, AttendanceServiceListEventRecordsByUserDateResult> {

    public AttendanceServiceListEventRecordsByUserDateRequest(AttendanceServiceListEventRecordsByUserDateParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListEventRecordsByUserDateRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-event-records-by-user-date");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListEventRecordsByUserDateResult> getResponseClass() {
        return AttendanceServiceListEventRecordsByUserDateResult.class;
    }

    public Class<AttendanceServiceListEventRecordsByUserDateParam> getDomainClass() {
        return AttendanceServiceListEventRecordsByUserDateParam.class;
    }
}

