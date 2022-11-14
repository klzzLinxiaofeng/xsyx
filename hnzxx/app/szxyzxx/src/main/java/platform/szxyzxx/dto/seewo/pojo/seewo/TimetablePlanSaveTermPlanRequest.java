package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据名称保存或更新学期排课
 * 保存或更新排课计划
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class TimetablePlanSaveTermPlanRequest extends OpenApiRequest<TimetablePlanSaveTermPlanParam, TimetablePlanSaveTermPlanResult> {

    public TimetablePlanSaveTermPlanRequest(TimetablePlanSaveTermPlanParam param) {
        this();
        setBizModel(param);
    }

    public TimetablePlanSaveTermPlanRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/timetable-api/timetable-plan/save-term-plan");
        setHttpMethod("POST");
    }

    public Class<TimetablePlanSaveTermPlanResult> getResponseClass() {
        return TimetablePlanSaveTermPlanResult.class;
    }

    public Class<TimetablePlanSaveTermPlanParam> getDomainClass() {
        return TimetablePlanSaveTermPlanParam.class;
    }
}

