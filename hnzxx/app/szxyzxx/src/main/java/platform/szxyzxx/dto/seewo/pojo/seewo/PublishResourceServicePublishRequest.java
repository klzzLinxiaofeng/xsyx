package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量发布新闻公告图片视频资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PublishResourceServicePublishRequest extends OpenApiRequest<PublishResourceServicePublishParam, PublishResourceServicePublishResult> {

    public PublishResourceServicePublishRequest(PublishResourceServicePublishParam param) {
        this();
        setBizModel(param);
    }

    public PublishResourceServicePublishRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/publish-resource-service/publish");
        setHttpMethod("POST");
    }

    public Class<PublishResourceServicePublishResult> getResponseClass() {
        return PublishResourceServicePublishResult.class;
    }

    public Class<PublishResourceServicePublishParam> getDomainClass() {
        return PublishResourceServicePublishParam.class;
    }
}

