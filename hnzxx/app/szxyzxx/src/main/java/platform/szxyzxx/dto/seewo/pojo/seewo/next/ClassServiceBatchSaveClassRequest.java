package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量新增班级
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassServiceBatchSaveClassRequest extends OpenApiRequest<ClassServiceBatchSaveClassParam, ClassServiceBatchSaveClassResult> {

    public ClassServiceBatchSaveClassRequest(ClassServiceBatchSaveClassParam param) {
        this();
        setBizModel(param);
    }

    public ClassServiceBatchSaveClassRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/class-service/batch-save-class");
        setHttpMethod("POST");
    }

    public Class<ClassServiceBatchSaveClassResult> getResponseClass() {
        return ClassServiceBatchSaveClassResult.class;
    }

    public Class<ClassServiceBatchSaveClassParam> getDomainClass() {
        return ClassServiceBatchSaveClassParam.class;
    }
}

