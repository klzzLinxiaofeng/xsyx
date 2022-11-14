package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 查询已注册的URL
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ResourceHookListResourceHookRequest extends OpenApiRequest<ResourceHookListResourceHookParam, ResourceHookListResourceHookResult> {

    public ResourceHookListResourceHookRequest(ResourceHookListResourceHookParam param) {
        this();
        setBizModel(param);
    }

    public ResourceHookListResourceHookRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/resource-hook/list-resource-hook");
        setHttpMethod("POST");
    }

    public Class<ResourceHookListResourceHookResult> getResponseClass() {
        return ResourceHookListResourceHookResult.class;
    }

    public Class<ResourceHookListResourceHookParam> getDomainClass() {
        return ResourceHookListResourceHookParam.class;
    }
}

