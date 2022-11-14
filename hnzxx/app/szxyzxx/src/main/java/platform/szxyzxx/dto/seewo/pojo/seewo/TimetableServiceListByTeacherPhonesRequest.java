package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据教师手机号查询教师课表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class TimetableServiceListByTeacherPhonesRequest extends OpenApiRequest<TimetableServiceListByTeacherPhonesParam, TimetableServiceListByTeacherPhonesResult> {

    public TimetableServiceListByTeacherPhonesRequest(TimetableServiceListByTeacherPhonesParam param) {
        this();
        setBizModel(param);
    }

    public TimetableServiceListByTeacherPhonesRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/timetable-service/list-by-teacher-phones");
        setHttpMethod("POST");
    }

    public Class<TimetableServiceListByTeacherPhonesResult> getResponseClass() {
        return TimetableServiceListByTeacherPhonesResult.class;
    }

    public Class<TimetableServiceListByTeacherPhonesParam> getDomainClass() {
        return TimetableServiceListByTeacherPhonesParam.class;
    }
}

