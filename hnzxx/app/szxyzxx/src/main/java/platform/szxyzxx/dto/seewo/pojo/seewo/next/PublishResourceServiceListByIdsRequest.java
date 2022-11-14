package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据资源id批量查找资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PublishResourceServiceListByIdsRequest extends OpenApiRequest<PublishResourceServiceListByIdsParam, PublishResourceServiceListByIdsResult> {

    public PublishResourceServiceListByIdsRequest(PublishResourceServiceListByIdsParam param) {
        this();
        setBizModel(param);
    }

    public PublishResourceServiceListByIdsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/publish-resource-service/list-by-ids");
        setHttpMethod("POST");
    }

    public Class<PublishResourceServiceListByIdsResult> getResponseClass() {
        return PublishResourceServiceListByIdsResult.class;
    }

    public Class<PublishResourceServiceListByIdsParam> getDomainClass() {
        return PublishResourceServiceListByIdsParam.class;
    }
}

