package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【课程考勤】更新用户考勤状态
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
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

