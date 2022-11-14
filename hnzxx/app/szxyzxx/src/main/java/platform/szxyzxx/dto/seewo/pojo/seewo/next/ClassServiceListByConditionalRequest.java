package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据条件分页查询学校班级列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassServiceListByConditionalRequest extends OpenApiRequest<ClassServiceListByConditionalParam, ClassServiceListByConditionalResult> {

    public ClassServiceListByConditionalRequest(ClassServiceListByConditionalParam param) {
        this();
        setBizModel(param);
    }

    public ClassServiceListByConditionalRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/class-service/list-by-conditional");
        setHttpMethod("POST");
    }

    public Class<ClassServiceListByConditionalResult> getResponseClass() {
        return ClassServiceListByConditionalResult.class;
    }

    public Class<ClassServiceListByConditionalParam> getDomainClass() {
        return ClassServiceListByConditionalParam.class;
    }
}

