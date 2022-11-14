package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据班级ID列表查询班级信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassServiceFindByIdsRequest extends OpenApiRequest<ClassServiceFindByIdsParam, ClassServiceFindByIdsResult> {

    public ClassServiceFindByIdsRequest(ClassServiceFindByIdsParam param) {
        this();
        setBizModel(param);
    }

    public ClassServiceFindByIdsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/class-service/find-by-ids");
        setHttpMethod("POST");
    }

    public Class<ClassServiceFindByIdsResult> getResponseClass() {
        return ClassServiceFindByIdsResult.class;
    }

    public Class<ClassServiceFindByIdsParam> getDomainClass() {
        return ClassServiceFindByIdsParam.class;
    }
}

