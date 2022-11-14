package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量添加学校管理员
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class SchoolApiAddManagersRequest extends OpenApiRequest<SchoolApiAddManagersParam, SchoolApiAddManagersResult> {

    public SchoolApiAddManagersRequest(SchoolApiAddManagersParam param) {
        this();
        setBizModel(param);
    }

    public SchoolApiAddManagersRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/school-api/add-managers");
        setHttpMethod("POST");
    }

    public Class<SchoolApiAddManagersResult> getResponseClass() {
        return SchoolApiAddManagersResult.class;
    }

    public Class<SchoolApiAddManagersParam> getDomainClass() {
        return SchoolApiAddManagersParam.class;
    }
}

