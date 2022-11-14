package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件|课程】根据班级查询指定日期的考勤统计数据
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListEventRecordsByClazzRequest extends OpenApiRequest<AttendanceServiceListEventRecordsByClazzParam, AttendanceServiceListEventRecordsByClazzResult> {

    public AttendanceServiceListEventRecordsByClazzRequest(AttendanceServiceListEventRecordsByClazzParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListEventRecordsByClazzRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-event-records-by-clazz");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListEventRecordsByClazzResult> getResponseClass() {
        return AttendanceServiceListEventRecordsByClazzResult.class;
    }

    public Class<AttendanceServiceListEventRecordsByClazzParam> getDomainClass() {
        return AttendanceServiceListEventRecordsByClazzParam.class;
    }
}

