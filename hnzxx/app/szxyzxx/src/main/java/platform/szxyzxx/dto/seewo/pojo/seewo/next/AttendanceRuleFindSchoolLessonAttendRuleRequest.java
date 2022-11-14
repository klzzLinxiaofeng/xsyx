package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【课程考勤】查询学校课程考勤规则
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceRuleFindSchoolLessonAttendRuleRequest extends OpenApiRequest<AttendanceRuleFindSchoolLessonAttendRuleParam, AttendanceRuleFindSchoolLessonAttendRuleResult> {

    public AttendanceRuleFindSchoolLessonAttendRuleRequest(AttendanceRuleFindSchoolLessonAttendRuleParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceRuleFindSchoolLessonAttendRuleRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-rule/find-school-lesson-attend-rule");
        setHttpMethod("POST");
    }

    public Class<AttendanceRuleFindSchoolLessonAttendRuleResult> getResponseClass() {
        return AttendanceRuleFindSchoolLessonAttendRuleResult.class;
    }

    public Class<AttendanceRuleFindSchoolLessonAttendRuleParam> getDomainClass() {
        return AttendanceRuleFindSchoolLessonAttendRuleParam.class;
    }
}

