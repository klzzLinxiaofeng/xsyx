package platform.szxyzxx.dto.seewo.pojo;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 保存场地课表（新）
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class TimetableServiceSaveRoomTimetableRequest extends OpenApiRequest<TimetableServiceSaveRoomTimetableParam, TimetableServiceSaveRoomTimetableResult> {

    public TimetableServiceSaveRoomTimetableRequest(TimetableServiceSaveRoomTimetableParam param) {
        this();
        setBizModel(param);
    }

    public TimetableServiceSaveRoomTimetableRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/timetable-service/save-room-timetable");
        setHttpMethod("POST");
    }

    public Class<TimetableServiceSaveRoomTimetableResult> getResponseClass() {
        return TimetableServiceSaveRoomTimetableResult.class;
    }

    public Class<TimetableServiceSaveRoomTimetableParam> getDomainClass() {
        return TimetableServiceSaveRoomTimetableParam.class;
    }
}

