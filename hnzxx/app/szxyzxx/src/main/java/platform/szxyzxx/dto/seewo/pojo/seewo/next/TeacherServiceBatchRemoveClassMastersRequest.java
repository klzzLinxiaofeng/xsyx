package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量移除班主任
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceBatchRemoveClassMastersRequest extends OpenApiRequest<TeacherServiceBatchRemoveClassMastersParam, TeacherServiceBatchRemoveClassMastersResult> {

    public TeacherServiceBatchRemoveClassMastersRequest(TeacherServiceBatchRemoveClassMastersParam param) {
        this();
        setBizModel(param);
    }

    public TeacherServiceBatchRemoveClassMastersRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/teacher-service/batch-remove-class-masters");
        setHttpMethod("POST");
    }

    public Class<TeacherServiceBatchRemoveClassMastersResult> getResponseClass() {
        return TeacherServiceBatchRemoveClassMastersResult.class;
    }

    public Class<TeacherServiceBatchRemoveClassMastersParam> getDomainClass() {
        return TeacherServiceBatchRemoveClassMastersParam.class;
    }
}

