package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量添加或更新老师
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceBatchSaveOrUpdateTeacherRequest extends OpenApiRequest<TeacherServiceBatchSaveOrUpdateTeacherParam, TeacherServiceBatchSaveOrUpdateTeacherResult> {

    public TeacherServiceBatchSaveOrUpdateTeacherRequest(TeacherServiceBatchSaveOrUpdateTeacherParam param) {
        this();
        setBizModel(param);
    }

    public TeacherServiceBatchSaveOrUpdateTeacherRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/teacher-service/batch-save-or-update-teacher");
        setHttpMethod("POST");
    }

    public Class<TeacherServiceBatchSaveOrUpdateTeacherResult> getResponseClass() {
        return TeacherServiceBatchSaveOrUpdateTeacherResult.class;
    }

    public Class<TeacherServiceBatchSaveOrUpdateTeacherParam> getDomainClass() {
        return TeacherServiceBatchSaveOrUpdateTeacherParam.class;
    }
}

