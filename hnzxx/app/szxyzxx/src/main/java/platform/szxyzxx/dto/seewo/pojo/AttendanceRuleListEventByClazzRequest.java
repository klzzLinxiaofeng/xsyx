package platform.szxyzxx.dto.seewo.pojo;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件考勤】查询班级指定日期的考勤规则列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class AttendanceRuleListEventByClazzRequest extends OpenApiRequest<AttendanceRuleListEventByClazzParam, AttendanceRuleListEventByClazzResult> {

    public AttendanceRuleListEventByClazzRequest(AttendanceRuleListEventByClazzParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceRuleListEventByClazzRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-rule/list-event-by-clazz");
        setHttpMethod("POST");
    }

    public Class<AttendanceRuleListEventByClazzResult> getResponseClass() {
        return AttendanceRuleListEventByClazzResult.class;
    }

    public Class<AttendanceRuleListEventByClazzParam> getDomainClass() {
        return AttendanceRuleListEventByClazzParam.class;
    }
}

