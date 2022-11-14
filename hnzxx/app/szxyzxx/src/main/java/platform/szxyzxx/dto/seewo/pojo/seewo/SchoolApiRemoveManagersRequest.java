package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量删除学校管理员
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class SchoolApiRemoveManagersRequest extends OpenApiRequest<SchoolApiRemoveManagersParam, SchoolApiRemoveManagersResult> {

    public SchoolApiRemoveManagersRequest(SchoolApiRemoveManagersParam param) {
        this();
        setBizModel(param);
    }

    public SchoolApiRemoveManagersRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/school-api/remove-managers");
        setHttpMethod("POST");
    }

    public Class<SchoolApiRemoveManagersResult> getResponseClass() {
        return SchoolApiRemoveManagersResult.class;
    }

    public Class<SchoolApiRemoveManagersParam> getDomainClass() {
        return SchoolApiRemoveManagersParam.class;
    }
}

