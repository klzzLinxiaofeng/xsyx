package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据班级id获取班级数据
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassApiQueryClassByClassIdRequest extends OpenApiRequest<ClassApiQueryClassByClassIdParam, ClassApiQueryClassByClassIdResult> {

    public ClassApiQueryClassByClassIdRequest(ClassApiQueryClassByClassIdParam param) {
        this();
        setBizModel(param);
    }

    public ClassApiQueryClassByClassIdRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/class-api/query-class-by-class-id");
        setHttpMethod("POST");
    }

    public Class<ClassApiQueryClassByClassIdResult> getResponseClass() {
        return ClassApiQueryClassByClassIdResult.class;
    }

    public Class<ClassApiQueryClassByClassIdParam> getDomainClass() {
        return ClassApiQueryClassByClassIdParam.class;
    }
}

