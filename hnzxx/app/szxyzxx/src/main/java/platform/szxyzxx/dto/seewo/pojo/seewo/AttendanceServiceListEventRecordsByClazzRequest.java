package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据班级查询指定日期考勤结果
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
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

