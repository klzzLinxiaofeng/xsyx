package platform.szxyzxx.dto.seewo.attendance;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 课程考勤api
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class LessonAttendanceLessonRequestHandlerRequest extends OpenApiRequest<LessonAttendanceLessonRequestHandlerParam, LessonAttendanceLessonRequestHandlerResult> {

    public LessonAttendanceLessonRequestHandlerRequest(LessonAttendanceLessonRequestHandlerParam param) {
        this();
        setBizModel(param);
    }

    public LessonAttendanceLessonRequestHandlerRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-attendance-api/lesson/attendance-lesson-request-handler");
        setHttpMethod("POST");
    }

    public Class<LessonAttendanceLessonRequestHandlerResult> getResponseClass() {
        return LessonAttendanceLessonRequestHandlerResult.class;
    }

    public Class<LessonAttendanceLessonRequestHandlerParam> getDomainClass() {
        return LessonAttendanceLessonRequestHandlerParam.class;
    }
}

