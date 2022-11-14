package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 【事件|课程】根据用户查询指定日期范围的考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListRecordsByUserDuringDateRequest extends OpenApiRequest<AttendanceServiceListRecordsByUserDuringDateParam, AttendanceServiceListRecordsByUserDuringDateResult> {

    public AttendanceServiceListRecordsByUserDuringDateRequest(AttendanceServiceListRecordsByUserDuringDateParam param) {
        this();
        setBizModel(param);
    }

    public AttendanceServiceListRecordsByUserDuringDateRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/attendance-service/list-records-by-user-during-date");
        setHttpMethod("POST");
    }

    public Class<AttendanceServiceListRecordsByUserDuringDateResult> getResponseClass() {
        return AttendanceServiceListRecordsByUserDuringDateResult.class;
    }

    public Class<AttendanceServiceListRecordsByUserDuringDateParam> getDomainClass() {
        return AttendanceServiceListRecordsByUserDuringDateParam.class;
    }
}

