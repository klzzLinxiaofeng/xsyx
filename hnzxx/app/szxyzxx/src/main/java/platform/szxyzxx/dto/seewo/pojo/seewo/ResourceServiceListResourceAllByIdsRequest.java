package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据Id列表带有文本信息的资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ResourceServiceListResourceAllByIdsRequest extends OpenApiRequest<ResourceServiceListResourceAllByIdsParam, ResourceServiceListResourceAllByIdsResult> {

    public ResourceServiceListResourceAllByIdsRequest(ResourceServiceListResourceAllByIdsParam param) {
        this();
        setBizModel(param);
    }

    public ResourceServiceListResourceAllByIdsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/resource-service/list-resource-all-by-ids");
        setHttpMethod("POST");
    }

    public Class<ResourceServiceListResourceAllByIdsResult> getResponseClass() {
        return ResourceServiceListResourceAllByIdsResult.class;
    }

    public Class<ResourceServiceListResourceAllByIdsParam> getDomainClass() {
        return ResourceServiceListResourceAllByIdsParam.class;
    }
}

