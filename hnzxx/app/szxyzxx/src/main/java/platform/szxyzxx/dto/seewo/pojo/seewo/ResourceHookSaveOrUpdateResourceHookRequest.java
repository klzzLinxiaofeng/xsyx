package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 新增或更新资源回调URL
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ResourceHookSaveOrUpdateResourceHookRequest extends OpenApiRequest<ResourceHookSaveOrUpdateResourceHookParam, ResourceHookSaveOrUpdateResourceHookResult> {

    public ResourceHookSaveOrUpdateResourceHookRequest(ResourceHookSaveOrUpdateResourceHookParam param) {
        this();
        setBizModel(param);
    }

    public ResourceHookSaveOrUpdateResourceHookRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/resource-hook/save-or-update-resource-hook");
        setHttpMethod("POST");
    }

    public Class<ResourceHookSaveOrUpdateResourceHookResult> getResponseClass() {
        return ResourceHookSaveOrUpdateResourceHookResult.class;
    }

    public Class<ResourceHookSaveOrUpdateResourceHookParam> getDomainClass() {
        return ResourceHookSaveOrUpdateResourceHookParam.class;
    }
}

