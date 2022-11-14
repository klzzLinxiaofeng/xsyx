package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量移除管理员
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceBatchRemoveManagersRequest extends OpenApiRequest<TeacherServiceBatchRemoveManagersParam, TeacherServiceBatchRemoveManagersResult> {

    public TeacherServiceBatchRemoveManagersRequest(TeacherServiceBatchRemoveManagersParam param) {
        this();
        setBizModel(param);
    }

    public TeacherServiceBatchRemoveManagersRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/teacher-service/batch-remove-managers");
        setHttpMethod("POST");
    }

    public Class<TeacherServiceBatchRemoveManagersResult> getResponseClass() {
        return TeacherServiceBatchRemoveManagersResult.class;
    }

    public Class<TeacherServiceBatchRemoveManagersParam> getDomainClass() {
        return TeacherServiceBatchRemoveManagersParam.class;
    }
}

