package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 查询学校资源栏目
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PublishResourceServiceListTagsRequest extends OpenApiRequest<PublishResourceServiceListTagsParam, PublishResourceServiceListTagsResult> {

    public PublishResourceServiceListTagsRequest(PublishResourceServiceListTagsParam param) {
        this();
        setBizModel(param);
    }

    public PublishResourceServiceListTagsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/publish-resource-service/list-tags");
        setHttpMethod("POST");
    }

    public Class<PublishResourceServiceListTagsResult> getResponseClass() {
        return PublishResourceServiceListTagsResult.class;
    }

    public Class<PublishResourceServiceListTagsParam> getDomainClass() {
        return PublishResourceServiceListTagsParam.class;
    }
}

