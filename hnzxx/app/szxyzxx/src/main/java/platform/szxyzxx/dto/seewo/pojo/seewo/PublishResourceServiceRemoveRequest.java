package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量删除资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PublishResourceServiceRemoveRequest extends OpenApiRequest<PublishResourceServiceRemoveParam, PublishResourceServiceRemoveResult> {

    public PublishResourceServiceRemoveRequest(PublishResourceServiceRemoveParam param) {
        this();
        setBizModel(param);
    }

    public PublishResourceServiceRemoveRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/publish-resource-service/remove");
        setHttpMethod("POST");
    }

    public Class<PublishResourceServiceRemoveResult> getResponseClass() {
        return PublishResourceServiceRemoveResult.class;
    }

    public Class<PublishResourceServiceRemoveParam> getDomainClass() {
        return PublishResourceServiceRemoveParam.class;
    }
}

