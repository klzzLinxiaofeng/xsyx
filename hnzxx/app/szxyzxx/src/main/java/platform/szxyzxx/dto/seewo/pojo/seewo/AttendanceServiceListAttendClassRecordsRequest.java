package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 获取事件（课程+事件）下班级考勤详细记录 
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
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

