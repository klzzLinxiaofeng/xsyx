package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量添加管理员
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceBatchSaveManagersRequest extends OpenApiRequest<TeacherServiceBatchSaveManagersParam, TeacherServiceBatchSaveManagersResult> {

    public TeacherServiceBatchSaveManagersRequest(TeacherServiceBatchSaveManagersParam param) {
        this();
        setBizModel(param);
    }

    public TeacherServiceBatchSaveManagersRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/teacher-service/batch-save-managers");
        setHttpMethod("POST");
    }

    public Class<TeacherServiceBatchSaveManagersResult> getResponseClass() {
        return TeacherServiceBatchSaveManagersResult.class;
    }

    public Class<TeacherServiceBatchSaveManagersParam> getDomainClass() {
        return TeacherServiceBatchSaveManagersParam.class;
    }
}

