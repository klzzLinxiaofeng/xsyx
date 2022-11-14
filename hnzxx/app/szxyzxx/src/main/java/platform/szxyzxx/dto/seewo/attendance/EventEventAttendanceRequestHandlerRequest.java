package platform.szxyzxx.dto.seewo.attendance;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 事件考勤api
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class EventEventAttendanceRequestHandlerRequest extends OpenApiRequest<EventEventAttendanceRequestHandlerParam, EventEventAttendanceRequestHandlerResult> {

    public EventEventAttendanceRequestHandlerRequest(EventEventAttendanceRequestHandlerParam param) {
        this();
        setBizModel(param);
    }

    public EventEventAttendanceRequestHandlerRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-attendance-api/event/event-attendance-request-handler");
        setHttpMethod("POST");
    }

    public Class<EventEventAttendanceRequestHandlerResult> getResponseClass() {
        return EventEventAttendanceRequestHandlerResult.class;
    }

    public Class<EventEventAttendanceRequestHandlerParam> getDomainClass() {
        return EventEventAttendanceRequestHandlerParam.class;
    }
}

