package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 保存班级荣誉
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AppApiSaveClassHonoursRequest extends OpenApiRequest<AppApiSaveClassHonoursParam, AppApiSaveClassHonoursResult> {

    public AppApiSaveClassHonoursRequest(AppApiSaveClassHonoursParam param) {
        this();
        setBizModel(param);
    }

    public AppApiSaveClassHonoursRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/app-api/save-class-honours");
        setHttpMethod("POST");
    }

    public Class<AppApiSaveClassHonoursResult> getResponseClass() {
        return AppApiSaveClassHonoursResult.class;
    }

    public Class<AppApiSaveClassHonoursParam> getDomainClass() {
        return AppApiSaveClassHonoursParam.class;
    }
}

