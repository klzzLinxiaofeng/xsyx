package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件|课程】分页查询学校考勤规则信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceRuleListSchoolAttendRuleRequest extends OpenApiRequest<AttendanceRuleListSchoolAttendRuleParam, AttendanceRuleListSchoolAttendRuleResult> {

    public AttendanceRuleListSchoolAttendRuleRequest(AttendanceRuleListSchoolAttendRuleParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceRuleListSchoolAttendRuleRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-rule/list-school-attend-rule");
        setHttpMethod("POST");
    }

    public Class<AttendanceRuleListSchoolAttendRuleResult> getResponseClass() {
        return AttendanceRuleListSchoolAttendRuleResult.class;
    }

    public Class<AttendanceRuleListSchoolAttendRuleParam> getDomainClass() {
        return AttendanceRuleListSchoolAttendRuleParam.class;
    }
}

