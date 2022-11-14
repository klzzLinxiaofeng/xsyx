package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 获取学校下的班级荣誉列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AppApiListClassHonourRequest extends OpenApiRequest<AppApiListClassHonourParam, AppApiListClassHonourResult> {

    public AppApiListClassHonourRequest(AppApiListClassHonourParam param) {
        this();
        setBizModel(param);
    }

    public AppApiListClassHonourRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/app-api/list-class-honour");
        setHttpMethod("POST");
    }

    public Class<AppApiListClassHonourResult> getResponseClass() {
        return AppApiListClassHonourResult.class;
    }

    public Class<AppApiListClassHonourParam> getDomainClass() {
        return AppApiListClassHonourParam.class;
    }
}

