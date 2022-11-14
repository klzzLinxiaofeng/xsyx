package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量删除班级
 * 批量删除班级
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ClassApiDeleteClassesRequest extends OpenApiRequest<ClassApiDeleteClassesParam, ClassApiDeleteClassesResult> {

    public ClassApiDeleteClassesRequest(ClassApiDeleteClassesParam param) {
        this();
        setBizModel(param);
    }

    public ClassApiDeleteClassesRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/class-api/delete-classes");
        setHttpMethod("POST");
    }

    public Class<ClassApiDeleteClassesResult> getResponseClass() {
        return ClassApiDeleteClassesResult.class;
    }

    public Class<ClassApiDeleteClassesParam> getDomainClass() {
        return ClassApiDeleteClassesParam.class;
    }
}

