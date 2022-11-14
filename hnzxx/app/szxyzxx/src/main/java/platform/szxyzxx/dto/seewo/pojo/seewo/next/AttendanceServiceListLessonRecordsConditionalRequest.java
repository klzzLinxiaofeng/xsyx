package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【课程考勤】根据条件分页查询考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListLessonRecordsConditionalRequest extends OpenApiRequest<AttendanceServiceListLessonRecordsConditionalParam, AttendanceServiceListLessonRecordsConditionalResult> {

    public AttendanceServiceListLessonRecordsConditionalRequest(AttendanceServiceListLessonRecordsConditionalParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListLessonRecordsConditionalRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-lesson-records-conditional");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListLessonRecordsConditionalResult> getResponseClass() {
        return AttendanceServiceListLessonRecordsConditionalResult.class;
    }

    public Class<AttendanceServiceListLessonRecordsConditionalParam> getDomainClass() {
        return AttendanceServiceListLessonRecordsConditionalParam.class;
    }
}

