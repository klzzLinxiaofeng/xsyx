package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【课程考勤】根据老师查询指定日期的课程考勤统计数据
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListLessonStatisByTeacherRequest extends OpenApiRequest<AttendanceServiceListLessonStatisByTeacherParam, AttendanceServiceListLessonStatisByTeacherResult> {

    public AttendanceServiceListLessonStatisByTeacherRequest(AttendanceServiceListLessonStatisByTeacherParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListLessonStatisByTeacherRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-lesson-statis-by-teacher");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListLessonStatisByTeacherResult> getResponseClass() {
        return AttendanceServiceListLessonStatisByTeacherResult.class;
    }

    public Class<AttendanceServiceListLessonStatisByTeacherParam> getDomainClass() {
        return AttendanceServiceListLessonStatisByTeacherParam.class;
    }
}

