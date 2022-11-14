package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据学校uid查询排课计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TimetableServiceListPlanRequest extends OpenApiRequest<TimetableServiceListPlanParam, TimetableServiceListPlanResult> {

    public TimetableServiceListPlanRequest(TimetableServiceListPlanParam param) {
        this();
        setBizModel(param);
    }

    public TimetableServiceListPlanRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/timetable-service/list-plan");
        setHttpMethod("POST");
    }

    public Class<TimetableServiceListPlanResult> getResponseClass() {
        return TimetableServiceListPlanResult.class;
    }

    public Class<TimetableServiceListPlanParam> getDomainClass() {
        return TimetableServiceListPlanParam.class;
    }
}

