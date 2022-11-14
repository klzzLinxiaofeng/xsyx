package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 删除班级荣誉
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AppApiRemoveClassHonourRequest extends OpenApiRequest<AppApiRemoveClassHonourParam, AppApiRemoveClassHonourResult> {

    public AppApiRemoveClassHonourRequest(AppApiRemoveClassHonourParam param) {
        this();
        setBizModel(param);
    }

    public AppApiRemoveClassHonourRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/app-api/remove-class-honour");
        setHttpMethod("POST");
    }

    public Class<AppApiRemoveClassHonourResult> getResponseClass() {
        return AppApiRemoveClassHonourResult.class;
    }

    public Class<AppApiRemoveClassHonourParam> getDomainClass() {
        return AppApiRemoveClassHonourParam.class;
    }
}

