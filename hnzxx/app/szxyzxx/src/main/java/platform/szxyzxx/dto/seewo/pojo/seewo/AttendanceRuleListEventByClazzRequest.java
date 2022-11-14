package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 查询班级的考勤规则（事件+课程）列表  
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
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

