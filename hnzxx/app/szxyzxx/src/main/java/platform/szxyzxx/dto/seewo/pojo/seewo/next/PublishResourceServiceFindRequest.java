package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 查询指定资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PublishResourceServiceFindRequest extends OpenApiRequest<PublishResourceServiceFindParam, PublishResourceServiceFindResult> {

    public PublishResourceServiceFindRequest(PublishResourceServiceFindParam param) {
        this();
        setBizModel(param);
    }

    public PublishResourceServiceFindRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/publish-resource-service/find");
        setHttpMethod("POST");
    }

    public Class<PublishResourceServiceFindResult> getResponseClass() {
        return PublishResourceServiceFindResult.class;
    }

    public Class<PublishResourceServiceFindParam> getDomainClass() {
        return PublishResourceServiceFindParam.class;
    }
}

