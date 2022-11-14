package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 条件查询新闻公告图片视频资源列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PublishResourceServiceListRequest extends OpenApiRequest<PublishResourceServiceListParam, PublishResourceServiceListResult> {

    public PublishResourceServiceListRequest(PublishResourceServiceListParam param) {
        this();
        setBizModel(param);
    }

    public PublishResourceServiceListRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/publish-resource-service/list");
        setHttpMethod("POST");
    }

    public Class<PublishResourceServiceListResult> getResponseClass() {
        return PublishResourceServiceListResult.class;
    }

    public Class<PublishResourceServiceListParam> getDomainClass() {
        return PublishResourceServiceListParam.class;
    }
}

