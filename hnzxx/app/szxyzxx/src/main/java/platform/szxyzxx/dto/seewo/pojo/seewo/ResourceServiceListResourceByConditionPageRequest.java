package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据条件获取资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ResourceServiceListResourceByConditionPageRequest extends OpenApiRequest<ResourceServiceListResourceByConditionPageParam, ResourceServiceListResourceByConditionPageResult> {

    public ResourceServiceListResourceByConditionPageRequest(ResourceServiceListResourceByConditionPageParam param) {
        this();
        setBizModel(param);
    }

    public ResourceServiceListResourceByConditionPageRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/resource-service/list-resource-by-condition-page");
        setHttpMethod("POST");
    }

    public Class<ResourceServiceListResourceByConditionPageResult> getResponseClass() {
        return ResourceServiceListResourceByConditionPageResult.class;
    }

    public Class<ResourceServiceListResourceByConditionPageParam> getDomainClass() {
        return ResourceServiceListResourceByConditionPageParam.class;
    }
}

