package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量设置班主任
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceBatchSetClassMastersRequest extends OpenApiRequest<TeacherServiceBatchSetClassMastersParam, TeacherServiceBatchSetClassMastersResult> {

    public TeacherServiceBatchSetClassMastersRequest(TeacherServiceBatchSetClassMastersParam param) {
        this();
        setBizModel(param);
    }

    public TeacherServiceBatchSetClassMastersRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/teacher-service/batch-set-class-masters");
        setHttpMethod("POST");
    }

    public Class<TeacherServiceBatchSetClassMastersResult> getResponseClass() {
        return TeacherServiceBatchSetClassMastersResult.class;
    }

    public Class<TeacherServiceBatchSetClassMastersParam> getDomainClass() {
        return TeacherServiceBatchSetClassMastersParam.class;
    }
}

