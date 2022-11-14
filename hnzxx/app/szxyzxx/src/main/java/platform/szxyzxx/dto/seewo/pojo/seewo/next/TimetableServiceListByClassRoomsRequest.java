package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据场地id查询场地课表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-9-7
 */
public class TimetableServiceListByClassRoomsRequest extends OpenApiRequest<TimetableServiceListByClassRoomsParam, TimetableServiceListByClassRoomsResult> {

    public TimetableServiceListByClassRoomsRequest(TimetableServiceListByClassRoomsParam param) {
        this();
        setBizModel(param);
    }

    public TimetableServiceListByClassRoomsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/timetable-service/list-by-class-rooms");
        setHttpMethod("POST");
    }

    public Class<TimetableServiceListByClassRoomsResult> getResponseClass() {
        return TimetableServiceListByClassRoomsResult.class;
    }

    public Class<TimetableServiceListByClassRoomsParam> getDomainClass() {
        return TimetableServiceListByClassRoomsParam.class;
    }
}

