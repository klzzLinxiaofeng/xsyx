package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 获取学校生效的排课计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TimetablePlanFindEnablePlanInSchoolRequest extends OpenApiRequest<TimetablePlanFindEnablePlanInSchoolParam, TimetablePlanFindEnablePlanInSchoolResult> {

    public TimetablePlanFindEnablePlanInSchoolRequest(TimetablePlanFindEnablePlanInSchoolParam param) {
        this();
        setBizModel(param);
    }

    public TimetablePlanFindEnablePlanInSchoolRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/timetable-api/timetable-plan/find-enable-plan-in-school");
        setHttpMethod("POST");
    }

    public Class<TimetablePlanFindEnablePlanInSchoolResult> getResponseClass() {
        return TimetablePlanFindEnablePlanInSchoolResult.class;
    }

    public Class<TimetablePlanFindEnablePlanInSchoolParam> getDomainClass() {
        return TimetablePlanFindEnablePlanInSchoolParam.class;
    }
}

