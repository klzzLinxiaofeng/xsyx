package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量添加或更新学生家长
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ParentServiceBatchSaveOrUpdateParentsRequest extends OpenApiRequest<ParentServiceBatchSaveOrUpdateParentsParam, ParentServiceBatchSaveOrUpdateParentsResult> {

    public ParentServiceBatchSaveOrUpdateParentsRequest(ParentServiceBatchSaveOrUpdateParentsParam param) {
        this();
        setBizModel(param);
    }

    public ParentServiceBatchSaveOrUpdateParentsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/parent-service/batch-save-or-update-parents");
        setHttpMethod("POST");
    }

    public Class<ParentServiceBatchSaveOrUpdateParentsResult> getResponseClass() {
        return ParentServiceBatchSaveOrUpdateParentsResult.class;
    }

    public Class<ParentServiceBatchSaveOrUpdateParentsParam> getDomainClass() {
        return ParentServiceBatchSaveOrUpdateParentsParam.class;
    }
}

