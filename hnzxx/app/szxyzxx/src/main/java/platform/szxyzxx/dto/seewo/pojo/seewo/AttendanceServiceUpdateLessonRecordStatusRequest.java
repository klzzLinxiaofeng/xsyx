package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 更新课程考勤用户考勤状态
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AttendanceServiceUpdateLessonRecordStatusRequest extends OpenApiRequest<AttendanceServiceUpdateLessonRecordStatusParam, AttendanceServiceUpdateLessonRecordStatusResult> {

    public AttendanceServiceUpdateLessonRecordStatusRequest(AttendanceServiceUpdateLessonRecordStatusParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceUpdateLessonRecordStatusRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/update-lesson-record-status");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceUpdateLessonRecordStatusResult> getResponseClass() {
        return AttendanceServiceUpdateLessonRecordStatusResult.class;
    }

    public Class<AttendanceServiceUpdateLessonRecordStatusParam> getDomainClass() {
        return AttendanceServiceUpdateLessonRecordStatusParam.class;
    }
}

