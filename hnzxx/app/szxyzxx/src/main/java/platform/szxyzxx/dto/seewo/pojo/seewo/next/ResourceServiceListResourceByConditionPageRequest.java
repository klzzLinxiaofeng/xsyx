package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据条件获取资源（已废弃）
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
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

