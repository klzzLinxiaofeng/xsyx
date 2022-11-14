package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据学号查询学生课表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TimetableServiceListByStudentCodesRequest extends OpenApiRequest<TimetableServiceListByStudentCodesParam, TimetableServiceListByStudentCodesResult> {

    public TimetableServiceListByStudentCodesRequest(TimetableServiceListByStudentCodesParam param) {
        this();
        setBizModel(param);
    }

    public TimetableServiceListByStudentCodesRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/timetable-service/list-by-student-codes");
        setHttpMethod("POST");
    }

    public Class<TimetableServiceListByStudentCodesResult> getResponseClass() {
        return TimetableServiceListByStudentCodesResult.class;
    }

    public Class<TimetableServiceListByStudentCodesParam> getDomainClass() {
        return TimetableServiceListByStudentCodesParam.class;
    }
}

