package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【课程】获取考勤场地列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceRuleListRuleAttendRoomRequest extends OpenApiRequest<AttendanceRuleListRuleAttendRoomParam, AttendanceRuleListRuleAttendRoomResult> {

    public AttendanceRuleListRuleAttendRoomRequest(AttendanceRuleListRuleAttendRoomParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceRuleListRuleAttendRoomRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-rule/list-rule-attend-room");
        setHttpMethod("POST");
    }

    public Class<AttendanceRuleListRuleAttendRoomResult> getResponseClass() {
        return AttendanceRuleListRuleAttendRoomResult.class;
    }

    public Class<AttendanceRuleListRuleAttendRoomParam> getDomainClass() {
        return AttendanceRuleListRuleAttendRoomParam.class;
    }
}

