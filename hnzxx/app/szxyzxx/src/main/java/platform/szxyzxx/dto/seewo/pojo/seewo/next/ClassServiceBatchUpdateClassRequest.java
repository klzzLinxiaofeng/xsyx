package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量修改班级
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassServiceBatchUpdateClassRequest extends OpenApiRequest<ClassServiceBatchUpdateClassParam, ClassServiceBatchUpdateClassResult> {

    public ClassServiceBatchUpdateClassRequest(ClassServiceBatchUpdateClassParam param) {
        this();
        setBizModel(param);
    }

    public ClassServiceBatchUpdateClassRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/class-service/batch-update-class");
        setHttpMethod("POST");
    }

    public Class<ClassServiceBatchUpdateClassResult> getResponseClass() {
        return ClassServiceBatchUpdateClassResult.class;
    }

    public Class<ClassServiceBatchUpdateClassParam> getDomainClass() {
        return ClassServiceBatchUpdateClassParam.class;
    }
}

